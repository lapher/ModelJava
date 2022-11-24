package com.system.fileSystem.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ReadTxt {

	public ArrayList<ArrayList<String>> readFile(File file, String splitName) throws IOException {
		ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();

		// 讀取 資料檔
		FileInputStream fis = new FileInputStream(file);
		InputStreamReader isr = new InputStreamReader(fis, "UTF8");
		try (BufferedReader br = new BufferedReader(isr)) {
			String line;

			while ((line = br.readLine()) != null) {
				// 避免分隔符號splitName 有特殊字元，所以前面加 \\
				String[] column = line.split("\\" + splitName);
				ArrayList<String> data = new ArrayList<String>();
				for (String dataUtil : column) {

					// 去掉 ""
					if (dataUtil.length() >= 2 && dataUtil.charAt(0) == '"'
							&& dataUtil.charAt(dataUtil.length() - 1) == '"') {
						dataUtil = dataUtil.substring(1, dataUtil.length() - 1);
					}

					data.add(dataUtil);
				}
				dataList.add(data);
			}
		}

		return dataList;
	}

	public ArrayList<ArrayList<String>> readFileSpec(File file, String splitName) throws IOException {
		File fileSpec = new File("C:\\Users\\user\\Desktop\\test2\\" + splitName);
		ArrayList<ArrayList<String>> dataList = new ArrayList<ArrayList<String>>();
		ArrayList<ArrayList<String>> dataListSpec = new ArrayList<ArrayList<String>>();

		// 讀取 規格檔
		FileInputStream fisSpec = new FileInputStream(fileSpec);
		InputStreamReader isrSpec = new InputStreamReader(fisSpec, "UTF8");
		try (BufferedReader brSpec = new BufferedReader(isrSpec)) {
			String line;
			while ((line = brSpec.readLine()) != null) {
				String[] column = line.split(",");
				ArrayList<String> data = new ArrayList<String>();
				if (column.length <= 1) { // 如果該行空白 需要跳過
					continue;
				}

				// 因為規格檔中，位置是0開頭，所以將0去掉
				for (String dataUtil : column) {
					char charAt = dataUtil.charAt(0);
					while (charAt == '0') {
						dataUtil = dataUtil.substring(1, dataUtil.length());
						charAt = dataUtil.charAt(0);
					}
					data.add(dataUtil);
				}
				dataListSpec.add(data);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		// 讀取 資料檔
		FileInputStream fis2 = new FileInputStream(file);
		InputStreamReader isr2 = new InputStreamReader(fis2, "UTF8");
		try (BufferedReader br2 = new BufferedReader(isr2)) {
			String line2;
			while ((line2 = br2.readLine()) != null) {
				ArrayList<String> data = new ArrayList<String>();

				for (ArrayList<String> dataSpec : dataListSpec) {
					int fisrt = Integer.parseInt(dataSpec.get(1));
					int end = Integer.parseInt(dataSpec.get(2));
					String dataUtil = line2.substring(fisrt - 1, end);
					data.add(dataUtil);
				}
				dataList.add(data);
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return dataList;
	}

}
