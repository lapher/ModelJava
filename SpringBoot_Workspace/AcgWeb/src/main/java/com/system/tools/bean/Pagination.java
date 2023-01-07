package com.system.tools.bean;

public class Pagination {
	
	private int nowPage;
	private int totalCount;
	private int totalPage;
	
	
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	@Override
	public String toString() {
		return "Pagination [nowPage=" + nowPage + ", totalCount=" + totalCount + ", totalPage=" + totalPage + "]";
	}
	
	
	
}
