<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style>

</style>

<title>ARTICLE MODIFY PAGE</title>
</head>
<body>
	<h1>MODIFY</h1>

	<hr />

	<table id ="tb" border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>날짜</th>
				<th>제목</th>
				<th>내용</th>
				<th>작성자</th>
			</tr>
		</thead>
		<tbody>
			<c var="article" value="${article }">
				<tr>
					<td>${article.id }</td>
					<td>${article.regDate.substring(0,10) }</td>
					<td>${article.title }</a></td>
					<td>${article.body }</td>
					<td>${article.nickname }</td>
				</tr>
			</c >
		</tbody>
	</table>
	<div>
	<a href="modify?id=${article.id }">수정</a>
	<a href="delete?id=${article.id }">삭제</a>
	</div>
</body>
</html>