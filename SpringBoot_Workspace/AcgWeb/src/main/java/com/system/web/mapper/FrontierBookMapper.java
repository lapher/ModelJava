package com.system.web.mapper;

import java.util.List;

import com.system.web.bean.FrontierBook;



public interface FrontierBookMapper {

	//Bean
	FrontierBook selectID(FrontierBook bean);
	List<FrontierBook> selectAll();
	void insert(FrontierBook bean);
	void update(FrontierBook bean);
	void delete(FrontierBook bean);

}