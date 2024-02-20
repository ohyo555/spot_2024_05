<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MEMBER MODIFY"></c:set>
<%@ include file="../common/head.jspf"%>

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<form action="../member/doModify" method="POST">
			<table class="mypage-box table-box-1" border="1">
				<tbody>
					<tr>
						<th>아이디</th>
						<td>${member.loginId }</td>
					</tr>
					<tr>
						<th>비밀번호</th>
						<td>${member.loginPw }</td>
					</tr>
					<tr>
						<th>이름</th>
						<td>${member.name }</td>
					</tr>
					<tr>
						<th>닉네임</th>
						<td>${member.nickname }</td>
					</tr>
					<tr>
						<th>전화번호</th>
						<td>${member.cellphoneNum }</td>
					</tr>
					<tr>
						<th>이메일</th>
						<td>${member.email }</td>
					</tr>
				</tbody>
			</table>
		</form>
		<div class="btns mt-5">
			<button class="btn btn-outline" type="button" onclick="history.back();">뒤로가기</button>
			<a class="btn btn-outline" href="../member/modify">수정</a>
		</div>
	</div>
</section>



<%@ include file="../common/foot.jspf"%>