<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE LIST"></c:set>
<%@ include file="../common/head.jspf"%>

<!-- <style>
	.tb {
	margin-left: auto;
	margin-right: auto;
	text-align: center;
	background-color: rgb(211, 232, 240);
	width: 400px;
}
</style> -->

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<table class="table-box-1" border="1">
		<colgroup>
		<col style="width:10%"/>
		<col style="width:20%"/>
		<col style="width:60%"/>
		<col style="width:10%"/>
		</colgroup>
		
			<thead>
				<tr>
					<th>번호</th>
					<th>날짜</th>
					<th>제목</th>
					<th>작성자</th>
				</tr>
			</thead>
			<tbody>
				
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
						<td>
							<a class="hover:underline" href="detail?id=${article.id }">${article.title }</a>
						</td>
						<td>${article.extra__writer }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</section>



<%@ include file="../common/foot.jspf"%>