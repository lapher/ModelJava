package com.system.web.bean;

public class FFClub {
	
	private String clubName; 
	private String rank;
	private String product;
	private String pic;
	public String getClubName() {
		return clubName;
	}
	public void setClubName(String clubName) {
		this.clubName = clubName;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	@Override
	public String toString() {
		return "FFClub [clubName=" + clubName + ", rank=" + rank + ", product=" + product + ", pic=" + pic + "]";
	}
	
	
	
	
}
