package com.system.web.bean;

public class FrontierBook {

		private String name; 	// 名稱 key
		private String picDir;	// 圖片路徑 
		private int price;	// 價格
		private String ffno;	// FF No.
		private String topic;	// 主題 
		private String auther;	// 作者 
		private String series;	// 系列 
		private String other;	// 其他
		
		
		public String getPicDir() {
			return picDir;
		}
		public void setPicDir(String picDir) {
			this.picDir = picDir;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getPrice() {
			return price;
		}
		public void setPrice(int price) {
			this.price = price;
		}
		public String getFfno() {
			return ffno;
		}
		public void setFfno(String ffno) {
			this.ffno = ffno;
		}
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		public String getAuther() {
			return auther;
		}
		public void setAuther(String auther) {
			this.auther = auther;
		}
		public String getSeries() {
			return series;
		}
		public void setSeries(String series) {
			this.series = series;
		}
		public String getOther() {
			return other;
		}
		public void setOther(String other) {
			this.other = other;
		}
		@Override
		public String toString() {
			return "FrontierBook [picDir=" + picDir + ", name=" + name + ", price=" + price + ", ffno=" + ffno
					+ ", topic=" + topic + ", auther=" + auther + ", series=" + series + ", other=" + other + "]";
		}
		
		
		
}
