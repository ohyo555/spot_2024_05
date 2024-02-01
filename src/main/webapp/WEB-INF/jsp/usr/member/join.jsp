<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MEMBER JOIN"></c:set>
<%@ include file="../common/head.jspf"%>

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<div>
			아이디 : <input autocomplete="off" type="text"
				placeholder="아이디를 입력해주세요" name="loginId" />
		</div>
		<div>
			비밀번호 : <input autocomplete="off" type="text"
				placeholder="비밀번호를 입력해주세요" name="loginPw" />
		</div>
		<div>
			비밀번호 확인 : <input autocomplete="off" type="text"
				placeholder="비밀번호를 입력해주세요" name="loginPw_n" />
		</div>
		<div>
			닉네임 : <input autocomplete="off" type="text"
				placeholder="닉네임을 입력해주세요" name="nickname" />
		</div>
		<div>
			전화번호 : <input autocomplete="off" type="text"
				placeholder="전화번호를 입력해주세요" name="cellphonenum" />
		</div>
		<div>
			이메일 : <input autocomplete="off" type="text"
				placeholder="이메일을 입력해주세요" name="e-mail" />
		</div>
		
		<div class = "text-base" >
			<button type="button" onclick="history.back();">뒤로가기</button>
		</div>
		
	</div>
</section>



<%@ include file="../common/foot.jspf"%>