package com.system.web.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.web.bean.FFClub;

@Component
public class FFClubService {

	@Autowired
	FFClubMapper mapper;

	// select
//	public FrontierBook selectID(FrontierBook bean) {
//		return mapper.selectID(bean);
//	}

	// select all
	public List<FFClub> selectAll() {
		return mapper.selectAll();
	}


}
