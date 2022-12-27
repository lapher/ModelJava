package com.system.groupBy.mapper;

import java.util.List;

import com.system.groupBy.bean.FrontierBookChatrs;
import com.system.groupBy.bean.FrontierBookFilter;

public interface FrontierBookGroupByMapper {
	
	
	//Filter
	List<FrontierBookFilter> groupByFFno();
	List<FrontierBookFilter> groupByAuther();
	List<FrontierBookFilter> groupBySeries();
	List<FrontierBookFilter> groupByTopic();
	
	//Count
	int count();
	FrontierBookChatrs countByPrice();// value: totalPrice; name: total of 0 price
	
	
	//Charts
	List<FrontierBookChatrs> chartsByAuther();
	List<FrontierBookChatrs> chartsBySeries();

}
