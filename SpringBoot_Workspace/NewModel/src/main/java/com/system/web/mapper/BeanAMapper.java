package com.system.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.web.bean.BeanA;



public interface BeanAMapper {

	//Bean
	BeanA selectID(BeanA bean);
	List<BeanA> selectAll();
	void insert(BeanA bean);
	void update(BeanA bean);
	void delete(BeanA bean);

}