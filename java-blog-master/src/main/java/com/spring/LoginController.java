package com.spring;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/loginport")
	public String login() {
		return "/WEB-INF/jsp/loginport.jsp";
	}
}
