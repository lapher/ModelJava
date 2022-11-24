package com.system.web.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.web.bean.ColOptions;

@Component
public class CommonUseMapperService {

	@Autowired
	CommonUseMapper commonUseMapper;

	public List<ColOptions> selectColOptions(ColOptions bean) {
		List<ColOptions> selectColOptions = commonUseMapper.selectColOptions(bean);
//		System.out.println(selectColOptions);
		return selectColOptions;
	};

}
