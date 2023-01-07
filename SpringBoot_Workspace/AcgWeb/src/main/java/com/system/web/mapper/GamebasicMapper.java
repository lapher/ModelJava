package com.system.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.web.bean.Gamebasic;

public interface GamebasicMapper {

	// Bean
	Gamebasic selectID(Gamebasic bean);
	List<Gamebasic> selectAll();
	List<Gamebasic> selectByPage(@Param("nowPage") int nowPage, @Param("page") int showGameItems);
	void insert(Gamebasic bean);
	void update(Gamebasic bean);
	void delete(Gamebasic bean);

	// Filter
	List<Gamebasic> selectFilterAll(Gamebasic bean);
	List<Gamebasic> selectFilterByPage(Gamebasic bean, @Param("nowPage") int nowPage, @Param("page") int showGameItems);

	// Count
	int count();
	
}
