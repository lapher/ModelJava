package com.system.groupBy.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.groupBy.bean.FrontierBook_auther;
import com.system.groupBy.bean.FrontierBook_ffno;
import com.system.groupBy.bean.FrontierBook_series;
import com.system.groupBy.bean.FrontierBook_topic;

@Component
public class FrontierBookGroupByService {

	@Autowired
	FrontierBookGroupByMapper mapper;

	public List<FrontierBook_ffno> groupByFFno() {
		return mapper.groupByFFno();
	}

	public List<FrontierBook_auther> groupByAuther() {
		return mapper.groupByAuther();
	}

	public List<FrontierBook_series> groupBySeries() {
		return mapper.groupBySeries();
	}

	public List<FrontierBook_topic> groupByTopic() {
		return mapper.groupByTopic();
	}

}
