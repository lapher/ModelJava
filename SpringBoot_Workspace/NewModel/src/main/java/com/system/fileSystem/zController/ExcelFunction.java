package com.system.fileSystem.zController;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import com.system.fileSystem.bean.Exec;
import com.system.fileSystem.connection.ConnectionUtil;
import com.system.fileSystem.file.ReadExcel;
import com.system.fileSystem.model.ExecDao;
import com.system.fileSystem.model.MsSqlDao;



@Component
public class ExcelFunction {

	// Excel >> DB
	public Boolean excelToMSDB(String fileName, File file, String tableName, String[] userData) {

		// 取得檔案資料
		ArrayList<ArrayList<String>> readFileList = null;
		String extfileName = fileName.substring(fileName.lastIndexOf("."));
		ReadExcel readExcel = new ReadExcel();

		// 判斷檔案資料副檔名
		try {
			if (extfileName.equals(".xls")) {
				readFileList = readExcel.readFileXls(file);
			} else if (extfileName.equals(".xlsx")) {
				readFileList = readExcel.readFileXlsx(file);
			} else if (extfileName.equals(".csv") || extfileName.equals(".txt")) {
				// 保留用...
			} else {
				System.out.println("發生錯誤 請重新輸入");
			}

			// Connection
			ConnectionUtil connectionUtil = new ConnectionUtil();
			Connection conn = connectionUtil.getConnection();

			// 取得MSSQL 資料表型態
			System.out.println("讀取資料庫...");
			ExecDao execDao = new ExecDao();
			List<Exec> findExec = execDao.findDesc(conn, tableName);

			// 製作SQL 語句 ex: insert into table values(?, ?, ?)
			MsSqlDao msSqlDao = new MsSqlDao();
			String sql = msSqlDao.getSQL(tableName, findExec);

			// 刪除既有的資料
			System.out.println("DB table刪除中...");
			Boolean deleteResult = msSqlDao.deleteDB(tableName, conn);

			// 新增資料
			// 有使用addBatch 批次處理, 每10筆為一單位做新增
			System.out.println("檔案輸入中...");
			if (deleteResult) {
				Boolean result = msSqlDao.insertDB(sql, conn, readFileList, findExec, userData);
			}

			// Connection 關閉
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// DB >> Excel
	@SuppressWarnings("resource")
	public Workbook MSDBtoExcel(String tableName) {

		try {
			// Connection
			ConnectionUtil connectionUtil = new ConnectionUtil();
			Connection conn = connectionUtil.getConnection();

			// 取得欄位 & 資料
			System.out.println("讀取資料庫...");
			MsSqlDao msSqlDao = new MsSqlDao();
			List<List<String>> datalist = msSqlDao.selectDB(conn, tableName);

			// 開新檔案
			System.out.println("設定Excel...");
			Workbook wb = new XSSFWorkbook();
			// Workbook wb = new HSSFWorkbook(); // .xls

			// 設定儲存格資料
			Sheet sheet = wb.createSheet();
			Row row = null;
			Cell cell = null;
			int r = 0;

			for (List<String> data : datalist) {
				row = sheet.createRow(r); // 每筆資料
				for (int c = 0; c < data.size(); c++) {
					cell = row.createCell(c); // 第n格資料
					cell.setCellValue(data.get(c)); // 每格資料
				}
				r++;
			}

			// Connection 關閉
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return wb;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 瀏覽器輸出資料
	public void setBrowser(HttpServletResponse response, Workbook workbook, String fileName) {
		System.out.println("輸出瀏覽器中...");
		try {
			// 清空response
			response.reset();
			// 設定response的Header
			response.setHeader("Content-Disposition", "attachment;fileName=" + 
					URLEncoder.encode(fileName + ".xlsx","utf8"));
			OutputStream os = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/vnd.ms-excel;charset=UTF-8");
			// 將excel寫入到輸出流中
			workbook.write(os);
			os.flush();
			os.close();
			// log.info("設定瀏覽器下載成功！");
		} catch (Exception e) {
			// log.info("設定瀏覽器下載失敗！");
			e.printStackTrace();
		}

	}

}
