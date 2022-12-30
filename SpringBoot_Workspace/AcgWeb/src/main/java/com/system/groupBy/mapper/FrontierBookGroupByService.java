package com.system.groupBy.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.system.groupBy.bean.FrontierBookChatrs;
import com.system.groupBy.bean.FrontierBookFilter;

@Component
public class FrontierBookGroupByService {

	@Autowired
	FrontierBookGroupByMapper mapper;

	// Filter
	public List<FrontierBookFilter> groupByFFno() {
		return mapper.groupByFFno();
	}

	public List<FrontierBookFilter> groupByAuther() {
		return mapper.groupByAuther();
	}

	public List<FrontierBookFilter> groupBySeries() {
		return mapper.groupBySeries();
	}

	public List<FrontierBookFilter> groupByTopic() {
		return mapper.groupByTopic();
	}

	// Count
	public int count() {
		return mapper.count();
	}

	public FrontierBookChatrs countByPrice() {
		// value: totalPrice; name: total of 0 price
		return mapper.countByPrice();
	}

	// Charts
	public List<FrontierBookChatrs> chartsByAuther() {
		List<FrontierBookChatrs> chartsByAuther = mapper.chartsByAuther();

		
		// other Value
		int otherValue = getOtherValue(chartsByAuther);	
		if (otherValue != 0) {
			FrontierBookChatrs bean = new FrontierBookChatrs();
			bean.setName("other");
			bean.setValue(otherValue);
			chartsByAuther.add(bean);
		}

		return chartsByAuther;
	};

	/**
	 * 
	 * @return true: 需要顯示other false: 不要other
	 */
	public List<FrontierBookChatrs> chartsBySeries(Boolean isOtherValue) {
		List<FrontierBookChatrs> chartsBySeries = mapper.chartsBySeries();
		
		if(!isOtherValue) { // 如果不要Other Value 則直接回傳
			return chartsBySeries;
		}
		
		// other Value
		int otherValue = getOtherValue(chartsBySeries);		
		if (otherValue != 0) {
			FrontierBookChatrs bean = new FrontierBookChatrs();
			bean.setName("other");
			bean.setValue(otherValue);
			chartsBySeries.add(bean);
		}

		return chartsBySeries;
	};

	// 取得 Other Value 的值
	private int getOtherValue(List<FrontierBookChatrs> listFrontierBookChatrs) {
		int topValue = 0;
		int otherValue = 0;
		for (FrontierBookChatrs bean : listFrontierBookChatrs) {
			int value = bean.getValue();
			topValue = topValue + value;
		}

		int count = mapper.count();
		otherValue = count - topValue;

		return otherValue;
	}

}
