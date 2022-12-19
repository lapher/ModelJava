package com.system.fileSystem.zController;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.system.aShiro.bean.Account;
import com.system.fileSystem.bean.ExcelOption;
import com.system.tools.SystemUtils;

@Controller
@RequestMapping("/Pic")
public class PicController {

	@Autowired
	private MessageSource messageSource;
	Locale locale = LocaleContextHolder.getLocale();

	@Autowired
	private ExcelFunction excelFunction;

	// 上傳首頁
	@GetMapping("/upload")
	public String mainPage(Model model) {
		model.addAttribute("mainPage", "excel_page/picUpload");
		return "model/dashboard";
	}

	// 下載首頁
	@GetMapping("/download")
	public String mainPageDownload(Model model) {
		model.addAttribute("mainPage", "excel_page/picUpload");
		return "model/dashboard";
	}

	// select Options
	@GetMapping(value = "/getOptions")
	public @ResponseBody Map<String, Object> getBeanAll() {
		Map<String, Object> map = new HashMap<>();

		List<ExcelOption> excelOption = new ArrayList<ExcelOption>();

		// data{Name, Value}
		String[][] data = { { "FF39", "FF39" },
							{ "FF40", "FF40" }	

		};

		for (int x = 0; x < data.length; x++) {
			ExcelOption bean = new ExcelOption(data[x][0], data[x][1]);
			excelOption.add(bean);
		}

		map.put("options", excelOption);

		return map;
	}

	// insert
	@PostMapping(value = "/insertFile", consumes = { "multipart/form-data; charset=UTF-8" })
	@ResponseBody
	public Map<String, Object> insertFile(@RequestParam MultipartFile picfile, @RequestParam String tableName,
			HttpSession httpSession) {
		Map<String, Object> map = new HashMap<>();

		// 使用者資料
		Account account = (Account) httpSession.getAttribute("account");
		String username = account.getUsername();
		String nowData = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String nowTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
		String[] userData = { username, nowData, nowTime };

		// Blob, MimeType 處理
		Blob blob = null;
		MultipartFile placeImage = picfile;
		String mimeType = "";
		String name = "";

		try {
			InputStream is = placeImage.getInputStream();
			blob = SystemUtils.inputStreamToBlob(is);
			name = placeImage.getOriginalFilename(); // ex: peko.png
//			mimeType = context.getMimeType(name); // ex: image/png
//			place.setPicture(blob);
//			place.setMimeType(mimeType);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// upload file
		try {
			// 儲存位置 C:\images\place\images
			File imageFolder = new File(SystemUtils.PLACE_IMAGE_FOLDER, "images");

			if (!imageFolder.exists()) {
				imageFolder.mkdirs(); // 檔案不在，則自動建立
			}
			// 儲存位置+檔名 C:\images\place\images\MemverImage_ID.png
			File file = new File(imageFolder, "MemverImage_" + nowData + nowTime + SystemUtils.getExFilename(name));
			placeImage.transferTo(file); // Mvc IO 上傳檔案
		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("result", true);

		return map;
	}

	// get Excel
	@GetMapping(value = "/getExcel")
	@ResponseBody
	public void downloadGet(HttpServletResponse response, @RequestParam String tableName,
			@RequestParam String fileName) {
		// DB > Excel
		Workbook excel = excelFunction.MSDBtoExcel(tableName);
		// 輸出到瀏覽器
		excelFunction.setBrowser(response, excel, fileName);
	}

}