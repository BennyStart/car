package com.ben.common;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.ben.service.CarInfoService;
import com.ben.util.RedisUtil;

import redis.clients.jedis.Jedis;

public class InitDataListener implements InitializingBean {

	@Autowired
	private CarInfoService carInfoService;

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}

	// 缓存登录信息到redis
	public void init() {
		System.out.println("init-----------");
		// CarInfoQueryCondition condition = new CarInfoQueryCondition();
		// List<CarInfo> carInfoList =
		// carInfoService.selectByCondition(condition);
		Jedis jedis = RedisUtil.getJedis();
		jedis.set("name", "java");
	}

}
