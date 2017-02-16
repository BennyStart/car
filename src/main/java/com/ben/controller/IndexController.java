package com.ben.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ben.service.CarInfoService;

@Controller
@RequestMapping("/main")
public class IndexController {

	@Autowired
	private CarInfoService carInfoService;

	/**
	 * 主页
	 */
	@RequestMapping("/dashboard")
	public String dashboard() {
		// CarInfoQueryCondition condition = new CarInfoQueryCondition();
		// List<CarInfo> carInfoList =
		// carInfoService.selectByCondition(condition);
		return "index";
	}
}
