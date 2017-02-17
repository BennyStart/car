package com.ben.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ben.util.RedisUtil;

import redis.clients.jedis.Jedis;

@Controller
@RequestMapping("/main")
public class IndexController {

	/**
	 * 主页
	 */
	@RequestMapping("/dashboard")
	public String dashboard() {
		Jedis jedis = RedisUtil.getJedis();
		System.out.println(jedis.get("name"));
		return "index";
	}
}
