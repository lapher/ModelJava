package com.system.fileSystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.fileSystem.bean.Exec;



public class MsSqlDao {

	// 製作SQL 語句 ex: insert into table values(?, ?, ?)
	public String getSQL(String tableName, List<Exec> execlist) {
		String sql = "INSERT INTO " + tableName + " VALUES";
		String sqlValues = "(";

		for (int x = 1; x < execlist.size(); x++) {
			sqlValues = sqlValues + "?,";
		}
		sql = sql + sqlValues + "?)";

		return sql;
	}

	// 刪除資料 (包含製作delete SQL)
	public Boolean deleteDB(String tableName, Connection conn) {
		// SQL
		String sql = "DELETE FROM " + tableName;

		// Delete table
		try {
			conn.setAutoCommit(false); // 關閉自動交易

			PreparedStatement ps = conn.prepareStatement(sql);
			int executeUpdate = ps.executeUpdate();
			conn.commit(); // 提交
			System.out.println("刪除筆數: " + executeUpdate);
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					System.out.println("===================");
					System.out.println("刪除發生問題 執行Roll Back");
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			return false;
		}

	}

	// 新增資料
	// 有使用addBatch 批次處理, 預設每300筆為一單位做新增
	public Boolean insertDB(String sql, Connection conn, ArrayList<ArrayList<String>> inputFileList,
			List<Exec> execlist, String[] userData) {
		int totalSum = 0;
		int sum = 0;
		int batchInt = 10; // 取得batchInt 預設300
		int dbColumn = execlist.size();

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			conn.setAutoCommit(false); // 關閉自動交易

			// 判斷MSSQL 資料型態
			for (ArrayList<String> dataList : inputFileList) {

				// 檢查資料是否全部為空白
				String checkNull = "";
				for (String data : dataList) {
					checkNull = checkNull + data;
				}
				if (checkNull.length() == 0) {
					continue;
				}

				// 使用者序號
				int userdataIndex = 0;

				// insert data
				for (int x = 1; x <= dbColumn; x++) {

					String dataUtil = "";
					String type = "";
					if (x <= dbColumn - 3) {
						dataUtil = dataList.get(x - 1);
						type = execlist.get(x - 1).getTypeName();
					} else { // 最後3欄資料 要放入使用者資訊
						dataUtil = userData[userdataIndex];
						type = "String";
						userdataIndex++;
					}

					// [數字]空值處理
					if (dataUtil == "" || dataUtil == null) {
						dataUtil = "0";
					}

					// Double&Float
					if (type.contains("float")) {
						if (dataUtil == "")
							dataUtil = "0";
						ps.setDouble(x, Double.parseDouble(dataUtil));
					} else if (type.contains("decimal")) {
						ps.setDouble(x, Double.parseDouble(dataUtil));
					} else if (type.contains("float")) {
						ps.setDouble(x, Double.parseDouble(dataUtil));
					} else if (type.contains("numeric")) {
						ps.setFloat(x, Float.parseFloat(dataUtil));
					}

					// Long
					else if (type.contains("bigint")) {
						ps.setLong(x, Long.parseLong(dataUtil));
					} else if (type.contains("money")) {
						ps.setLong(x, Long.parseLong(dataUtil));
					}

					// Int
					else if (type.contains("int")) {
						ps.setInt(x, Integer.parseInt(dataUtil));
					} else if (type.contains("smallmoney")) {
						ps.setInt(x, Integer.parseInt(dataUtil));
					} else if (type.contains("bit")) {
						ps.setInt(x, Integer.parseInt(dataUtil));
					}

					// 其餘當作String
					else {
						// [文字]空值處理
						if (dataUtil == "0") {
							dataUtil = "";
						}
						ps.setString(x, dataUtil);
					}

				}

				ps.addBatch();
				totalSum++;
				sum++;
				if (sum >= batchInt) {
					ps.executeBatch();
					conn.commit(); // 提交
					ps.clearParameters();
					sum = 0;
					System.out.println(totalSum + "筆/" + inputFileList.size() + "筆");
				}
			}

			if (sum != 0) {
				ps.executeBatch();
				conn.commit(); // 提交
				System.out.println(totalSum + "筆/" + inputFileList.size() + "筆");
			}

			conn.setAutoCommit(true); // 開啟自動交易
			System.out.println("新增筆數: " + totalSum);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			if (conn != null) {
				try {
					System.out.println("===================");
					System.out.println("新增發生問題 執行Roll Back");
					System.out.println("目前已將前 " + (totalSum - batchInt) + "筆新增進資料庫");
					conn.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	// 查詢資料
	public List<List<String>> selectDB(Connection conn, String tableName) throws SQLException {
		String sql = "select * from " + tableName;
		List<List<String>> dataList = new ArrayList<List<String>>();
		
		// 取得欄位
		ExecDao execDao = new ExecDao();
		List<String>titleName = execDao.findColumnCHName(conn, tableName);
		List<String>columnName = execDao.findColumnENName(conn, tableName);
		
		// Connection
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();
		
		// title
		List<String> title = new ArrayList<String>();
		for(String name: titleName) {
			title.add(name);
		}
		dataList.add(title);
		
		// get DB data
		while (result.next()) {
			List<String> data = new ArrayList<String>();		
			for(String name: columnName) {
				data.add(result.getString(name));
			}
			dataList.add(data);
		}
		
		
		return dataList;
	}
}


