<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE MODIFY"></c:set>
<%@ include file="../common/head.jspf"%>

<section class="mt-5 text-2xl px-4">
	<div class="mx-auto">
		<th>번호: ${article.id }</th><br>
		<th>날짜: ${article.regDate.substring(0,10) }</th><br>
		<form method="POST" action="doModify">
		<input type="hidden" value=${article.id } name="id" />
			<th>제목: <input type="text" name="title" placeholder="${article.title }"/ ></th><br>
			<th>내용: <textarea type="text" name="body" placeholder="${article.body }"></textarea></th><br>
			<th>작성자: ${article.extra__writer }</th>
		<div class="btns mt-5 text-base">
			<button type="button" onclick="history.back();">뒤로가기</button>
			<button type="submit" >수정</button>
		</div>
		</form>
	</div>
</section>


		
<%@ include file="../common/foot.jspf"%>