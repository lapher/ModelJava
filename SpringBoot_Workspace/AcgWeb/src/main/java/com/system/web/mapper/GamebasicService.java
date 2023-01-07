package com.system.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.web.bean.Gamebasic;

@Component
public class GamebasicService {
	
	
	@Autowired GamebasicMapper mapper;
	
	// Bean
	public Gamebasic selectID(Gamebasic bean) {
		return mapper.selectID(bean);
	}
	public List<Gamebasic> selectAll() {
		return mapper.selectAll();
	}
	public List<Gamebasic> selectByPage(int nowPage, int showGameItems) {
		return mapper.selectByPage(nowPage, showGameItems);
	}
	public void insert(Gamebasic bean) {
		mapper.insert(bean);
	}
	public void update(Gamebasic bean) {
		mapper.insert(bean);
	}
	public void delete(Gamebasic bean) {
		mapper.delete(bean);
	}

	// Filter
	public List<Gamebasic> selectFilterAll(Gamebasic bean) {
		return mapper.selectFilterAll(bean);
	}
	public List<Gamebasic> selectFilterByPage(Gamebasic bean, int nowPage, int showGameItems) {
		return mapper.selectFilterByPage(bean, nowPage, showGameItems);
	}

	// Count
	public int count() {
		return mapper.count();
	}
}
