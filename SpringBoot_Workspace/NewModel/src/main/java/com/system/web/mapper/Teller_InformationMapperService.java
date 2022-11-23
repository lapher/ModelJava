package com.system.web.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.web.bean.Teller_Information;



@Component
public class Teller_InformationMapperService {

	@Autowired
	Teller_InformationMapper teller_InformationMapper;

	// select
	public Teller_Information selectTeller_InformationMapperByTeller_No(String teller_No) {
		return teller_InformationMapper.selectTeller_InformationMapperByTeller_No(teller_No);
	}

	// select all
	public List<Teller_Information> selectBeanAll() {
		return teller_InformationMapper.selectBeanAll();
	}

	// insert
	public Boolean insertTeller_Information(Teller_Information bean) {
		// SQL
		Teller_Information checkBean = teller_InformationMapper
				.selectTeller_InformationMapperByTeller_No(bean.getTeller_No());

		// 避免重複新增
		if (checkBean == null) {
			teller_InformationMapper.insertTeller_InformationMapper(bean);
			// sqlSession.commit();
			return true;
		}

		return false;

	}

	// update
	public Boolean updateTeller_Information(Teller_Information bean) {
		// SQL
		Teller_Information checkBean = teller_InformationMapper
				.selectTeller_InformationMapperByTeller_No(bean.getTeller_No());

		// 避免重複更新 規則1
//		if(checkBean == null) {
//			teller_InformationMapper.updateTeller_InformationMapper(bean);
//			return true;
//		}
		// 避免重複更新 規則2
		if (checkBean.getTeller_No().equals(bean.getTeller_No())) {
			teller_InformationMapper.updateTeller_InformationMapper(bean);
			return true;
		}

		return false;

	}

	// delete
	public void deleteTeller_Information(String teller_No) {
		teller_InformationMapper.deleteTeller_InformationMapper(teller_No);

	}

}
