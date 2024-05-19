package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.vo.Spot;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrArticleController {
	
	@Autowired
	private ArticleService articleService;

	// 액션 메서드
	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, Model model) {
	
		List<Spot> spots = articleService.getForPrintSpots();
	
		model.addAttribute("spots", spots);

		return "usr/article/list";
	}

	@RequestMapping("/usr/article/dofind")
	@ResponseBody
	public List<String> dofind(String keyword, HttpServletRequest req) {
		
		String key = null;
		
		 if (keyword.contains("로그인")) {
		        key = "로그인";
		    } else if (keyword.contains("로그아웃")) {
		        key = "로그아웃";
		    } else if (keyword.contains("회원가입")) {
		        key = "회원가입";
		    } else if (keyword.contains("회원탈퇴")) {
		        key = "회원탈퇴";
		    } else if (keyword.contains("회원정보수정") || (keyword.contains("회원정보") && keyword.contains("수정"))) {
		        key = "회원정보수정";
		    }  else if (keyword.contains("회원정보")) {
		        key = "회원정보";
		    } else if (keyword.contains("수정")) {
		        key = "수정";
		    } else if (keyword.contains("삭제")) {
		        key = "삭제";
		    }
		    
		    List<String> answer = key != null ? articleService.dofind(keyword, key) : new ArrayList<>();
		    if (answer.isEmpty()) {
		        answer.add("일치하는 답변이 없습니다.");
		    }
		    
		    return answer;
		 
		// List<String> answer = articleService.dofind(keyword);
		
		//return answer;		
	}
	
}
	