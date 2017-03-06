package com.ben.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class IndexController {

	/**
	 * 主页
	 */
	@RequestMapping("/dashboard")
	public String dashboard() {
		// Jedis jedis = RedisUtil.getJedis();
		// System.out.println(jedis.get("name"));
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password, HttpSession httpSession) {
		httpSession.setAttribute("username", username);
		return "redirect:/view/chat.jsp";
	}
}
