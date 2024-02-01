<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>

<table id ="tb" border="1">
	<thead>
		<tr>
			<th>번호</th>
			<th>날짜</th>
			<th>제목</th>
			<th>작성자</th>
		</tr>
	</thead>
	<tbody>
		<label class="checkbox checkbox-inline">
			<input type="checkbox" name="myBtn" data-toggle="checkbox" value="아이디">check
		</label>
		
		<c:if test="${articles.size() == 0 }">
			<tfoot>
				<tr>
					<td colspan="4">게시글 없어</td>
				</tr>
			</tfoot>
		</c:if>

		<c:forEach var="article" items="${articles }">
			<tr>
				<td>${article.id }</td>
				<td>${article.regDate.substring(0,10) }</td>
				<td><a href="detail?id=${article.id }">${article.title }</a></td>
				<td>${article.nickname }</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<%@ include file="../common/foot.jspf"%>