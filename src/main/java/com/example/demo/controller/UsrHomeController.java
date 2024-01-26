package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UsrHomeController {
	
	int count = -1;
	int cnt;
	
	public UsrHomeController() {
		cnt = 0;
	}
	@RequestMapping("/usr/home/main")
	@ResponseBody
	public String showMain() {
		return "안녕";
	}
	
	@RequestMapping("/usr/home/main2")
	@ResponseBody
	public String showMain2() {
		return "잘가";
	}
	
	@RequestMapping("/usr/home/main3")
	@ResponseBody
	public int showMain3() {
		
		int a = 1;
		int b = 2;
		return a + b;
	}
	
	@RequestMapping("/usr/home/main4")
	@ResponseBody
	public int showMain4() {
//		==v1== 전역변수를 이용함
		count++;
		return count;
	}
	
	@RequestMapping("/usr/home/main5")
	@ResponseBody
	public int showMain5() {
//		==v2== 생성자를 이용함
		return cnt++;
	}
	
	@RequestMapping("/usr/home/main6")
	@ResponseBody
	public String showMain6() {
		cnt = 0;
		return "count의 값이 0으로 초기화 되었습니다.";
	}

	

}
