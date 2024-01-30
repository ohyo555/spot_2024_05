package com.example.demo.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.MemberRepository;
import com.example.demo.vo.Member;

@Service
public class MemberService {
	@Autowired
	private MemberRepository memberRepository;

	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}

	public Object doJoin(String loginId, String loginPw, String name, String nickname, String cellphoneNum,
			String email) {
		
		memberRepository.join(loginId, loginPw, name, nickname, cellphoneNum, email);
		return memberRepository.getLastInsertId();

	}

	public Member getMember(Object id) {
		return memberRepository.getMember(id);
	}

	public int findMember(String loginId) {
		return memberRepository.findMember(loginId);
	}
	
	public Member findloginMember(String loginId) {
		return memberRepository.findloginMember(loginId);
	}

	public Object doLogin(String loginId, String loginPw) {
		
		memberRepository.login(loginId, loginPw);
		return memberRepository.login(loginId, loginPw);
	}

}