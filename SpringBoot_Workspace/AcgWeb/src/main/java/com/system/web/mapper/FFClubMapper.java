package com.system.web.mapper;

import java.util.List;

import com.system.web.bean.FFClub;
import com.system.web.bean.FrontierBook;



public interface FFClubMapper {

	//Bean
//	FrontierBook selectID(FrontierBook bean);
	List<FFClub> selectAll();
	
}