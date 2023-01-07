package com.system.tools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.tools.bean.Pagination;
import com.system.web.mapper.GamebasicService;

@Component
public class PageUtils {
	
	@Autowired GamebasicService gamebasicService;
	
	// 分頁Pagination 呈現
	public Pagination getGamePagination(int nowPage, int showItems) {
		Pagination pagination = new Pagination();
		
		//
		int count = gamebasicService.count();
		double totalPage = Math.ceil(count/showItems);
		
		// bean
		pagination.setTotalCount(count);
		pagination.setNowPage(nowPage);
		pagination.setTotalPage((int)totalPage);
		
		return pagination;
	}
	
}
