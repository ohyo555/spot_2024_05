<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>

<style>

	.div1 {
	margin: 15px;
	margin-left: auto;
	margin-right: auto;
	text-align: center;
	}
	
	.div2 {
	display:block;
/* 	text-align: center; */
	}
	
</style>

	<div class="div1">
		<th>번호: ${article.id }</th><br>
		<th>날짜: ${article.regDate.substring(0,10) }</th><br>
		<th>제목: ${article.title }</th><br>
		<th>내용: ${article.body }</th><br>
		<th>작성자: ${article.nickname }</th>
	</div>
	
	<div class="div1 div2">
	<a href="list">목록</a>
	<a href="modify?id=${article.id }">수정</a>
	<a href="delete?id=${article.id }">삭제</a>
	</div>

<%@ include file="../common/foot.jspf"%>