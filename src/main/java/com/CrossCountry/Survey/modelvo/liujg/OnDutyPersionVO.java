package com.CrossCountry.Survey.modelvo.liujg;

public class OnDutyPersionVO {
	
	private String position;//员工职位 对应 OMS_DISPATCHERS position
	  
	private String name;//员工姓名 对应 OMS_DISPATCHERS name
	   
	private String photoUrl; //员工图片名称 OMS_PERSONPHOTOREL PHOTOURL

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	

}
