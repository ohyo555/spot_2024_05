package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;
	private boolean islogined = false;
	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		if (Ut.isNullOrEmpty(loginId)) {
			ResultData.from("F-1", "loginId를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(loginPw)) {
			System.out.println("djkd");
			ResultData.from("F-2", "loginPw를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(name)) {
			ResultData.from("F-3", "이름을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(nickname)) {
			ResultData.from("F-4", "별명을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(cellphoneNum)) {
			ResultData.from("F-5", "전화번호를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(email)) {
			ResultData.from("F-6", "email을 입력해주세요");
		}

		int id = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (id == -1) {
			return ResultData.from("F-7", Ut.f("이미 사용중인 아이디(%s)입니다", loginId));
		}
		
		if (id == -2) {
			return ResultData.from("F-8", Ut.f("이미 사용중인 이름(%s)과 이메일(%s)입니다", name, email));
		}

		Member member = memberService.getMember(id);

		return ResultData.from("S-1", Ut.f("%s님 회원가입이 완료되었습니다.", loginId), member);
	}
	
	@RequestMapping("/usr/member/doLogin")
	@ResponseBody
	public Object doLogin(String loginId, String loginPw) {
		
		if (Ut.isNullOrEmpty(loginId)) {
			return "아이디를 입력해주세요";
		}
		if (Ut.isNullOrEmpty(loginPw)) {
			return "비밀번호를 입력해주세요";
		}

		Object member = memberService.login(loginId, loginPw);

		if (member == null) {
			return "해당 아이디는 존재하지 않습니다.";
		} else if (member.equals(0)) {
			return "비밀번호가 일치하지 않습니다.";
		}
		
		islogined = true;
		return "로그인 성공";
	}
}