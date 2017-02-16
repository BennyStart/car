package com.ben.service;

import java.util.List;

import com.ben.common.BaseService;
import com.ben.model.domain.CarInfo;
import com.ben.model.query.CarInfoQueryCondition;

public interface CarInfoService extends BaseService<CarInfo> {

	public List<CarInfo> selectByCondition(CarInfoQueryCondition condition);
}
