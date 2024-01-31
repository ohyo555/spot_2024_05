package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.MemberService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Member;
import com.example.demo.vo.ResultData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrMemberController {

	@Autowired
	private MemberService memberService;
	private boolean islogined = false;

	@RequestMapping("/usr/member/doJoin")
	@ResponseBody
	public ResultData<Member> doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		if (Ut.isNullOrEmpty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(name)) {
			return ResultData.from("F-3", "이름을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(nickname)) {
			return ResultData.from("F-4", "닉네임을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(cellphoneNum)) {
			return ResultData.from("F-5", "전화번호를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(email)) {
			return ResultData.from("F-6", "이메일을 입력해주세요");
		}

		ResultData<Integer> joinRd = memberService.join(loginId, loginPw, name, nickname, cellphoneNum, email);

		if (joinRd.isFail()) {
			return (ResultData) joinRd;
		}

		Member member = memberService.getMember(joinRd.getData1());

		return ResultData.newData(joinRd, member);
	}
	
	@RequestMapping("/usr/member/doLogin" )
	@ResponseBody
	public ResultData<Member> doLogin(HttpServletRequest request, HttpServletResponse response, String loginId, String loginPw) {
		
		if (Ut.isNullOrEmpty(loginId)) {
			return ResultData.from("F-1", "아이디를 입력해주세요");
		}
		if (Ut.isNullOrEmpty(loginPw)) {
			return ResultData.from("F-2", "비밀번호를 입력해주세요");
		}

		ResultData<Member> member = memberService.login(loginId, loginPw);       
		//	
        HttpSession session = request.getSession();
//		session.setAttribute("loginedMemberId", member.getData1().getLoginId());
		session.setAttribute("loginedMemberLoginId",member.getData1().getLoginId());
		session.setAttribute("loginedMember", member.getData1().getLoginPw());
		islogined  = true;

		return member;
	}
	
	@RequestMapping("/usr/member/doLogout")
	@ResponseBody
	public ResultData<Member> doLogout(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        
        if (session.getAttribute("loginedMemberLoginId") == null) {
			return ResultData.from("F-1", "로그인 후 이용해주세요");
		}
        
//        session.removeAttribute("loginedMemberId");
		session.removeAttribute("loginedMemberLoginId");
		session.removeAttribute("loginedMember");

		return ResultData.from("S-1", "로그아웃 성공");
	}
}