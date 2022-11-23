package com.system.fileSystem.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.system.fileSystem.bean.Exec;



public class ExecDao {

	// 取得MSSQL 資料表型態
	public List<Exec> findDesc(Connection conn, String tableName) throws SQLException {
		String sql = "exec sp_columns " + tableName;

		List<Exec> listbean = new ArrayList<Exec>();

		// Connection
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet result = stmt.executeQuery();

		// get DB data
		while (result.next()) {
			Exec bean = new Exec();
			bean.setTypeName(result.getString("TYPE_NAME"));
			listbean.add(bean);
		}

		return listbean;

	}

	// 取得 中文欄位(Excel 標題)
	public List<String> findColumnCHName(Connection conn, String tableName) throws SQLException {
		String sql = "select * from Z_AllCol_Name where Table_Name = ?";
		List<String> columnName = new ArrayList<String>();

		// Connection
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, tableName);
		ResultSet result = stmt.executeQuery();

		while (result.next()) {
			columnName.add(result.getString("Column_CH_Name"));
		}

		return columnName;
	}

	// 取得 英文欄位(table 標題)
	public List<String> findColumnENName(Connection conn, String tableName) throws SQLException {
		String sql = "select * from Z_AllCol_Name where Table_Name = ?";
		List<String> columnName = new ArrayList<String>();

		// Connection
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, tableName);
		ResultSet result = stmt.executeQuery();

		while (result.next()) {
			columnName.add(result.getString("Column_EN_Name"));
		}

		return columnName;
	}

}
