package com.system.web.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.system.web.bean.Teller_Information;



public interface Teller_InformationMapper {

	//Bean
	Teller_Information selectTeller_InformationMapperByTeller_No(@Param("teller_No") String teller_No);
	List<Teller_Information> selectBeanAll();
	void insertTeller_InformationMapper(Teller_Information bean);
	void updateTeller_InformationMapper(Teller_Information bean);
	void deleteTeller_InformationMapper(@Param("teller_No") String teller_No);

}