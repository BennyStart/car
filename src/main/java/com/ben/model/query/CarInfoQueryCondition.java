package com.ben.model.query;

import java.util.List;

import com.ben.model.domain.CarInfo;

public class CarInfoQueryCondition extends CarInfo {

	private List<String> idList;

	public List<String> getIdList() {
		return idList;
	}

	public void setIdList(List<String> idList) {
		this.idList = idList;
	}

}
