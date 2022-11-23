package com.system.fileSystem.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {
	// 使用Apachi POI

	public ArrayList<ArrayList<String>> readFileXls(File file) throws IOException {

		Workbook workbook = null; // excel
		InputStream is = new FileInputStream(file);
		workbook = new HSSFWorkbook(is); // xls 格式
		ArrayList<ArrayList<String>> arrayList = getArrayList(workbook);
		return arrayList;

	}

	public ArrayList<ArrayList<String>> readFileXlsx(File file) throws IOException {
		Workbook workbook = null;
		InputStream is = new FileInputStream(file);
		workbook = new XSSFWorkbook(is); // xlsx 格式
		ArrayList<ArrayList<String>> arrayList = getArrayList(workbook);
		return arrayList;

	}

	public ArrayList<ArrayList<String>> getArrayList(Workbook workbook) {
		Sheet sheet = workbook.getSheetAt(0); // 第1個表單
		Row row = sheet.getRow(0); // 先取得Row
		int rownum = sheet.getPhysicalNumberOfRows(); // 最後1筆資料
		int colnum = row.getPhysicalNumberOfCells(); // 最後1個欄位
		int firstRow = 1; // 因為第一筆資料是欄位名稱，如果要取第二筆資料 則設定1，但這裡先保留
		int firstCol = 0;
		ArrayList<ArrayList<String>> list = new ArrayList<>();// 每筆資料集
		ArrayList<String> _inner; // 每筆資料

		for (int i = firstRow; i < rownum; i++) {
			row = sheet.getRow(i);
			_inner = new ArrayList<>();

			if (row != null) {
				for (int j = firstCol; j < colnum; j++) {
					_inner.add(readCell(row.getCell(j)));
				}
				list.add(_inner);
			} else {
				break;
			}

		}

		return list;
	}

	public String readCell(Cell cell) {
		return (String) getCellFormatValue(cell);
	}

	public Object getCellFormatValue(Cell cell) {
		// 判斷excel 欄位，將資料全部轉成String
		Object cellValue = null;
		if (cell != null) {
			switch (cell.getCellType()) {
			case NUMERIC:
				if (cell.getCellStyle().getDataFormatString().contains("%")) {
					Double value = cell.getNumericCellValue();
					BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
					BigDecimal bigDecimal_100 = new BigDecimal(100);
					BigDecimal bigDecimal_ = bigDecimal.multiply(bigDecimal_100);

					double doubleValue = bigDecimal_.doubleValue();
					if (doubleValue % 1.0 != 0) {
						BigDecimal bigDecimal_2 = new BigDecimal(Double.toString(doubleValue));
						cellValue = bigDecimal_2.toString() + "%";
					} else {
						cellValue = String.format("%.0f", doubleValue) + "%";
					}

				} else if (cell.getCellStyle().getDataFormatString().contains("m/d/yy")) {
					cellValue = cell.getLocalDateTimeCellValue().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

				} else {
					Double value = cell.getNumericCellValue();
					if (value % 1.0 != 0) {
						BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
						cellValue = bigDecimal.toString();
					} else {
						cellValue = String.format("%.0f", value);
					}
				}

				break;
			case FORMULA:
				if (DateUtil.isCellDateFormatted(cell)) {
					cellValue = cell.getDateCellValue();
				} else {
					cellValue = String.valueOf(cell.getNumericCellValue());
				}
				break;
			case STRING:
				cellValue = cell.getRichStringCellValue().getString();
				break;
			default:
				cellValue = cell.toString();
			}
		} else {
			cellValue = "";
		}
		return cellValue;

	}
}
