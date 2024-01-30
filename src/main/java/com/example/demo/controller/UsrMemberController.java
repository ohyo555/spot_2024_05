package com.example.demo.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.vo.Member;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		Object id = 0;
		int cnt = memberService.findMember(loginId);
		
		if(cnt == 1) {
			return "중복된 아이디가 존재합니다.";
		}
		try {
			id = memberService.doJoin(loginId, loginPw, name, nickname, cellphoneNum, email);
		} catch (Exception e) {
			return "제대로 입력해";
		}
		Member member = memberService.getMember(id);
		return member;
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public Object doJoin(String loginId, String loginPw) {
		Member member = memberService.findMember(loginId);

		if(member != null) {
			member.getLoginPw().equals();
		}
		try {
			return "로그인됨" + member;
		} catch (Exception e) {
			return "제대로 입력해";
		}
	}
}