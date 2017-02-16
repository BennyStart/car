package com.ben.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ben.dao.CarInfoMapper;
import com.ben.model.domain.CarInfo;
import com.ben.model.query.CarInfoQueryCondition;
import com.ben.service.CarInfoService;

@Service
public class CarInfoServiceImpl implements CarInfoService {

	@Autowired
	private CarInfoMapper carInfoMapper;

	@Override
	public Long save(CarInfo entity) {
		// TODO Auto-generated method stub
		return carInfoMapper.save(entity);
	}

	@Override
	public Long deleteById(CarInfo entity) {
		// TODO Auto-generated method stub
		return carInfoMapper.deleteById(entity);
	}

	@Override
	public Long updateById(CarInfo entity) {
		// TODO Auto-generated method stub
		return carInfoMapper.updateById(entity);
	}

	@Override
	public CarInfo selectById(String id) {
		// TODO Auto-generated method stub
		return carInfoMapper.selectById(id);
	}

	@Override
	public Integer selectByIndexCount(CarInfo entity) {
		// TODO Auto-generated method stub
		return carInfoMapper.selectByIndexCount(entity);
	}

	@Override
	public List<CarInfo> selectByCondition(CarInfoQueryCondition condition) {
		// TODO Auto-generated method stub
		return carInfoMapper.selectByCondition(condition);
	}

}
