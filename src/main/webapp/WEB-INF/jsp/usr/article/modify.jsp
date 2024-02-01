<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE MODIFY"></c:set>
<%@ include file="../common/head.jspf"%>

<section class="mt-5 text-2xl px-4">
	<div class="mx-auto">
		<div class="div1">
			<th>번호: ${article.id }</th><br>
			<th>날짜: ${article.regDate.substring(0,10) }</th><br>
			<th>제목: <input type="text" name="title" value="${article.title }"/ ></th><br>
			<th>내용: <textarea type="text" name="body">${article.body }></textarea></th><br>
			<th>작성자: ${article.nickname }</th>
		</div>
	</div>
</section>

<%@ include file="../common/foot.jspf"%>