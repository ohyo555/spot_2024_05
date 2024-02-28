package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsrAPITestController {

	@RequestMapping("/usr/home/APITest")
	public String APITest() {

		return "/usr/home/APITest";
	}

}