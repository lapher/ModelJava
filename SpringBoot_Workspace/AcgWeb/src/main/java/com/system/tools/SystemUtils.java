package com.system.tools;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;

import javax.sql.rowset.serial.SerialBlob;

public class SystemUtils {

	// 檔案存放路徑
	final public static String PLACE_IMAGE_FOLDER = "C:\\images\\place";

	public static Blob inputStreamToBlob(InputStream is) {
		Blob blob = null;

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int len = 0;
			byte[] b = new byte[8192];
			while ((len = is.read(b)) != -1) {
				baos.write(b, 0, len); // data, start offest, number of bytes
			}

			byte[] data = baos.toByteArray();
			blob = new SerialBlob(data);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return blob;
	}

	// ex: return .png
	public static String getExFilename(String filename) {
		int n = filename.lastIndexOf(".");
		if (n >= 0) {
			return filename.substring(n);
		} else {
			return "";
		}
	}
}
