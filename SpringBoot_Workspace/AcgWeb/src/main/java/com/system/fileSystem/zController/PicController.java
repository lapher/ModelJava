package com.system.fileSystem.zController;

import java.io.File;
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

import com.system.fileSystem.bean.ExcelOption;
import com.system.groupBy.mapper.FrontierBookGroupByService;
import com.system.tools.SystemUtils;
import com.system.web.bean.FrontierBook;
import com.system.web.mapper.FrontierBookService;

import net.coobird.thumbnailator.Thumbnails;

@Controller
@RequestMapping("/Pic")
public class PicController {
	

	
	@Autowired
	private MessageSource messageSource;
	Locale locale = LocaleContextHolder.getLocale();

	@Autowired
	private ExcelFunction excelFunction;
	@Autowired
	private FrontierBookService frontierBookService;
	@Autowired
	private FrontierBookGroupByService frontierBookGroupByService;
	

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

//		List<ExcelOption> excelOption = new ArrayList<ExcelOption>();

		// data{Name, Value}
//		String[][] data = { { "FF39", "FF39" }, { "FF40", "FF40" }
//
//		};
//
//		for (int x = 0; x < data.length; x++) {
//			ExcelOption bean = new ExcelOption(data[x][0], data[x][1]);
//			excelOption.add(bean);
//		}
		
		
		map.put("ffnoOptions", frontierBookGroupByService.groupByFFno());
		map.put("autherOptions", frontierBookGroupByService.groupByAuther());
		map.put("seriesOptions", frontierBookGroupByService.groupBySeries());
		map.put("topicOptions", frontierBookGroupByService.groupByTopic());

		return map;
	}

	// insert
	@PostMapping(value = "/insertFile", consumes = { "multipart/form-data; charset=UTF-8" })
	@ResponseBody
	public Map<String, Object> insertFile(@RequestParam MultipartFile picfile, 
			@RequestParam String ffno,
			@RequestParam String auther,
			@RequestParam String bookName,
			@RequestParam String price,
			@RequestParam String topic,
			@RequestParam String series,
			@RequestParam String other,
			HttpSession httpSession) {
		Map<String, Object> map = new HashMap<>();

		// 資料處理
		String nowData = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String nowTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));


		// Blob, MimeType 處理
//		Blob blob = null;
		MultipartFile placeImage = picfile;
//		String mimeType = "";
		String name = "";

		try {
//			InputStream is = placeImage.getInputStream();
//			blob = SystemUtils.inputStreamToBlob(is);
			name = placeImage.getOriginalFilename(); // ex: peko.png
//			mimeType = context.getMimeType(name); // ex: image/png
//			place.setPicture(blob);
//			place.setMimeType(mimeType);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			// upload file
//			File imageFolder = new File(SystemUtils.PLACE_IMAGE_FOLDER, "images"); // 儲存位置 C:\images\place\images
			
			// 正式用
			String nowpath = System.getProperty("user.dir"); // \apache-tomcat-9.0.45\bin
			String[] split = nowpath.split("bin");
			String picPath = split[0] + "webapps"; // \apache-tomcat-9.0.45\webapps
			
			// 測試用
//			String picPath = "C:\\_java\\git\\ModelJava\\SpringBoot_Workspace\\AcgWeb\\src\\main\\resources\\static";
//			String picPath = "C:\\_Git\\ModelJava\\ModelJava\\SpringBoot_Workspace\\AcgWeb\\src\\main\\resources\\static";
			
			File imageFolder = new File(picPath, "/pic/frontierBook"); // 儲存位置
			File thumbnailsFolder = new File(picPath, "/pic/frontierBook/Thumbnails"); // 儲存位置
			
			if (!imageFolder.exists()) { // 檔案不在，則自動建立
				imageFolder.mkdirs(); 
			}
			if (!thumbnailsFolder.exists()) { // 檔案不在，則自動建立
				thumbnailsFolder.mkdirs(); 
			}
			
			// class + bookName + time
			String fileName = "frontierBook_" + bookName + "_" + nowData + nowTime + SystemUtils.getExFilename(name);
			File file = new File(imageFolder, fileName); // 儲存位置+檔名 C:\images\place\images\MemverImage_ID.png
			File thumbnailsfile = new File(thumbnailsFolder, fileName); // 儲存位置+檔名 C:\images\place\images\MemverImage_ID.png
			placeImage.transferTo(file); // Mvc IO 上傳檔案
			
			// 將原始倘案壓縮
			Thumbnails.of(file).scale(0.2f).outputQuality(0.5f).toFile(thumbnailsfile);
			
			// upload DB
			FrontierBook bean = new FrontierBook();
			bean.setName("test" + nowTime);
			bean.setAuther(auther);
			bean.setFfno(ffno);
			bean.setName(bookName);
			bean.setOther(other);
			bean.setPrice(Integer.valueOf(price));
			bean.setSeries(series);
			bean.setTopic(topic);
			bean.setPicDir(fileName);
			frontierBookService.insert(bean);

		} catch (Exception e) {
			e.printStackTrace();
		}

		map.put("result", true);
		map.put("alertMsg", messageSource.getMessage("alert.pic.ok", null, locale));
		
		
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