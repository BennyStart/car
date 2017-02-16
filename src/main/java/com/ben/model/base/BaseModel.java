package com.ben.model.base;

import java.util.Date;

public class BaseModel {

	private Integer id;
	private Date createTime;
	private Integer recStatus;
	private Integer recVer;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getRecStatus() {
		return recStatus;
	}

	public void setRecStatus(Integer recStatus) {
		this.recStatus = recStatus;
	}

	public Integer getRecVer() {
		return recVer;
	}

	public void setRecVer(Integer recVer) {
		this.recVer = recVer;
	}

}
