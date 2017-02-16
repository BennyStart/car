package com.ben.dao;

import java.util.List;

import com.ben.common.BaseMapper;
import com.ben.model.domain.CarInfo;
import com.ben.model.query.CarInfoQueryCondition;

public interface CarInfoMapper extends BaseMapper<CarInfo> {

	public List<CarInfo> selectByCondition(CarInfoQueryCondition condition);
}
