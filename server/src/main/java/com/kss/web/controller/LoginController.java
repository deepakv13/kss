package com.kss.web.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kss.domain.User;
import com.kss.service.api.LoginService;
import com.kss.utils.JsonUtil;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public String checkAndGetUser(@RequestParam String soeId,@RequestParam(required = false) String password) throws JsonGenerationException, JsonMappingException, IOException {
		User user = loginService.getUser(soeId, password);
		if (user != null) {
			return JsonUtil.buildJsonFromObject(user);
		} else {
			return JsonUtil.buildJsonFromObject(new User());
		}
	}	
}	

