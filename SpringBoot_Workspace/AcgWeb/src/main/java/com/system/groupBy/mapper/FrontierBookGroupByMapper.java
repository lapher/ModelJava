package com.system.groupBy.mapper;

import java.util.List;

import com.system.groupBy.bean.FrontierBook_auther;
import com.system.groupBy.bean.FrontierBook_ffno;
import com.system.groupBy.bean.FrontierBook_series;
import com.system.groupBy.bean.FrontierBook_topic;

public interface FrontierBookGroupByMapper {
	
	
	//Bean
	List<FrontierBook_ffno> groupByFFno();
	List<FrontierBook_auther> groupByAuther();
	List<FrontierBook_series> groupBySeries();
	List<FrontierBook_topic> groupByTopic();

}
