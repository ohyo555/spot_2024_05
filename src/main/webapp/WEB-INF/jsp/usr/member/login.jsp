<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MEMBER LOGIN"></c:set>
<%@ include file="../common/head.jspf"%>

<style>
	section > div div {
		margin-bottom: 10px; 
	}
</style>

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form method="POST" action="doLogin"
		onsubmit="LoginForm__submit(this); return false;">
		<div>
			아이디 : <input autocomplete="off" type="text"
				placeholder="아이디를 입력해주세요" name="loginId" />
		</div>
		<div>
			비밀번호 : <input autocomplete="off" type="text"
				placeholder="비밀번호를 입력해주세요" name="loginPw" />
		</div>
		<!-- <button type="button" onclick="history.back();">뒤로가기</button> -->
		
		<div class = "text-base" >
			<button type="submit" >로그인</button>
			<a class="hover:underline" href="../member/join">회원가입</a>
		</div>
		</form>
	</div>
</section>



<%@ include file="../common/foot.jspf"%>