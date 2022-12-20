package com.system.web.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.web.bean.FrontierBook;

@Component
public class FrontierBookService {

	@Autowired
	FrontierBookMapper mapper;

	// select
	public FrontierBook selectID(FrontierBook bean) {
		return mapper.selectID(bean);
	}

	// select all
	public List<FrontierBook> selectAll() {
		return mapper.selectAll();
	}

	// insert
	public Boolean insert(FrontierBook bean) {
		// SQL
		FrontierBook checkBean = mapper.selectID(bean);

		// 規則: 如果不存在 才允許新增
		if (checkBean == null) {
			mapper.insert(bean);
			return true;
		}
		return false;
	}

	// update
	public Boolean update(FrontierBook bean) {
		// SQL
		FrontierBook checkBean = mapper.selectID(bean);

		// 避免重複更新 規則1
//		if(checkBean == null) {
//			teller_InformationMapper.updateTeller_InformationMapper(bean);
//			return true;
//		}
		// 規則: 如果ID存在 才允許新增
		if (checkBean.getName().equals(bean.getName())) {
			mapper.update(bean);
			return true;
		}

		return false;

	}

	// delete
	public void delete(FrontierBook bean) {
		mapper.delete(bean);

	}

}
