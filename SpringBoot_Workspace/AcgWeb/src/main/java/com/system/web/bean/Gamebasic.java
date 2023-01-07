package com.system.web.bean;

public class Gamebasic {
	
	private String name;	
	private String tag;
	private String type;	
	private String club;
	private String voice;	
	private String date;	
	private String grading;	
	private String language;	
	private String resource;
	private String content;	
	private int price;
	private String picDirHead;	
	private String picDirKey;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getClub() {
		return club;
	}
	public void setClub(String club) {
		this.club = club;
	}
	public String getVoice() {
		return voice;
	}
	public void setVoice(String voice) {
		this.voice = voice;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getGrading() {
		return grading;
	}
	public void setGrading(String grading) {
		this.grading = grading;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPicDirHead() {
		return picDirHead;
	}
	public void setPicDirHead(String picDirHead) {
		this.picDirHead = picDirHead;
	}
	public String getPicDirKey() {
		return picDirKey;
	}
	public void setPicDirKey(String picDirKey) {
		this.picDirKey = picDirKey;
	}
	
	@Override
	public String toString() {
		return "Gamebasic [name=" + name + ", tag=" + tag + ", type=" + type + ", club=" + club + ", voice=" + voice
				+ ", date=" + date + ", grading=" + grading + ", language=" + language + ", resource=" + resource
				+ ", content=" + content + ", price=" + price + ", picDirHead=" + picDirHead + ", picDirKey="
				+ picDirKey + "]";
	}	
	
	

}
