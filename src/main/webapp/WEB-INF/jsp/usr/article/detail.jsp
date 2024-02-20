<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="ARTICLE DETAIL"></c:set>
<%@ include file="../common/head.jspf"%>

<!-- <iframe src="http://localhost:8081/usr/article/doIncreaseHitCountRd?id=372" frameborder="0"></iframe> -->
<style>
/* 2차메뉴 */

body, ul, li {
  margin:0;
  padding:0;
  list-style:none;
}

a {
  color:inherit;
  text-decoration:none;
}

.rr {
	width: 100%;
}

.rr >ul>li {
	margin-right: 0px;
	margin-left: auto;
	position: relative;
}

.comment ul>li>ul {
	display: none;
	position: absolute;
	left: auto;
	right: 20px;
	background-color: white;
}

.comment ul>li:hover>ul {
	display: block;
	width: 100%;
	white-space:nowrap;
}

.reaction {
	display: inline;
}
</style>

<!-- 변수 -->
<script>
	const params = {};
	params.id = parseInt('${param.id}');
	
	var isAlreadyAddGoodRp = ${isAlreadyAddGoodRp};
	var isAlreadyAddBadRp = ${isAlreadyAddBadRp};
</script>

<!-- 조회수 -->
<script>
	function ArticleDetail__doIncreaseHitCount() {
		const localStorageKey = 'article__' + params.id + '__alreadyView';

		if (localStorage.getItem(localStorageKey)) {
			return;
		}

		localStorage.setItem(localStorageKey, true);

		$.get('../article/doIncreaseHitCountRd', {
			id : params.id,
			ajaxMode : 'Y'
		}, function(data) {
			$('.article-detail__hit-count').empty().html(data.data1);
		}, 'json');
	}

	$(function() {
		// 		ArticleDetail__doIncreaseHitCount();
		setTimeout(ArticleDetail__doIncreaseHitCount, 2000);
	});
</script>

<!-- 좋아요 싫어요  -->
<script>
	<!-- 좋아요 싫어요 버튼	-->
	function checkRP() {
		if(isAlreadyAddGoodRp == true){
			$('#likeButton').toggleClass('btn-outline');
		}else if(isAlreadyAddBadRp == true){
			$('#DislikeButton').toggleClass('btn-outline');
		}else {
			return;
		}
	}


	function doGoodReaction(articleId) {
		
		$.ajax({
			url: '/usr/reactionPoint/doGoodReaction',
			type: 'POST',
			data: {relTypeCode: 'article', relId: articleId},
			dataType: 'json',
			success: function(data){
				console.log(data);
				console.log('data.data1Name : ' + data.data1Name);
				console.log('data.data1 : ' + data.data1);
				console.log('data.data2Name : ' + data.data2Name);
				console.log('data.data2 : ' + data.data2);
				if(data.resultCode.startsWith('S-')){
					var likeButton = $('#likeButton');
					var likeCount = $('#likeCount');
					var DislikeButton = $('#DislikeButton');
					var DislikeCount = $('#DislikeCount');
					
					if(data.resultCode == 'S-1'){
						likeButton.toggleClass('btn-outline');
						likeCount.text(data.data1);
					}else if(data.resultCode == 'S-2'){
						DislikeButton.toggleClass('btn-outline');
						DislikeCount.text(data.data2);
						likeButton.toggleClass('btn-outline');
						likeCount.text(data.data1);
					}else {
						likeButton.toggleClass('btn-outline');
						likeCount.text(data.data1);
					}
					
				}else {
					alert(data.msg);
				}
		
			},
			error: function(jqXHR,textStatus,errorThrown) {
				alert('좋아요 오류 발생 : ' + textStatus);

			}
			
		});
	}
	
	
	
	function doBadReaction(articleId) {
		
	 $.ajax({
			url: '/usr/reactionPoint/doBadReaction',
			type: 'POST',
			data: {relTypeCode: 'article', relId: articleId},
			dataType: 'json',
			success: function(data){
				console.log(data);
				console.log('data.data1Name : ' + data.data1Name);
				console.log('data.data1 : ' + data.data1);
				console.log('data.data2Name : ' + data.data2Name);
				console.log('data.data2 : ' + data.data2);
				if(data.resultCode.startsWith('S-')){
					var likeButton = $('#likeButton');
					var likeCount = $('#likeCount');
					var DislikeButton = $('#DislikeButton');
					var DislikeCount = $('#DislikeCount');
					
					if(data.resultCode == 'S-1'){
						DislikeButton.toggleClass('btn-outline');
						DislikeCount.text(data.data2);
					}else if(data.resultCode == 'S-2'){
						likeButton.toggleClass('btn-outline');
						likeCount.text(data.data1);
						DislikeButton.toggleClass('btn-outline');
						DislikeCount.text(data.data2);
		
					}else {
						DislikeButton.toggleClass('btn-outline');
						DislikeCount.text(data.data2);
					}
			
				}else {
					alert(data.msg);
				}
			},
			error: function(jqXHR,textStatus,errorThrown) {
				alert('싫어요 오류 발생 : ' + textStatus);
			}
			
		});
	}
	
	$(function() {
		checkRP();
	});
	
	/* 댓글 좋아요 */
		function doGoodCommentReaction(articleId, commentId) {
		
		$.ajax({
			url: '/usr/reactionPoint/doGoodCommentReaction',
			type: 'POST',
			data: {relTypeCode: 'comment', relId: articleId, Id: commentId},
			dataType: 'json',
			success: function(data){
				console.log(data);
				console.log('data.data1Name : ' + data.data1Name);
				console.log('data.data1 : ' + data.data1);
				console.log('data.data2Name : ' + data.data2Name);
				console.log('data.data2 : ' + data.data2);
				if(data.resultCode.startsWith('S-')){
					var likeButton = $('#likeButton');
					var likeCount = $('#likeCount');
					
					if(data.resultCode == 'S-1'){
						likeButton.toggleClass('btn-outline');
						likeCount.text(data.data1);
					}else if(data.resultCode == 'S-2'){
						likeButton.toggleClass('btn-outline');
						likeCount.text(data.data1);
					}else {
						likeButton.toggleClass('btn-outline');
						likeCount.text(data.data1);
					}
					
				}else {
					alert(data.msg);
				}
		
			},
			error: function(jqXHR,textStatus,errorThrown) {
				alert('좋아요 오류 발생 : ' + textStatus);

			}
			
		});
	}
</script>

<!-- 댓글 -->
	<script>
		var CommentWrite__submitDone = false;
		function CommentWrite__submit(form) {
			if (CommentWrite__submitDone) {
				alert('이미 처리중입니다');
				return;
			}
			console.log(123);
			
			console.log(form.body.value);
			
			if (form.body.value.length < 3) {
				alert('댓글은 3글자 이상 입력해');
				form.body.focus();
				return;
			}
			CommentWrite__submitDone = true;
			form.submit();
		}
	</script>


<!-- 댓글 수정, 삭제 -->
<script>
	function doModify(commentId) {
		if(isAlreadyAddGoodRp == true){
			$('#likeButton').toggleClass('btn-outline');
		}else if(isAlreadyAddBadRp == true){
			$('#DislikeButton').toggleClass('btn-outline');
		}else {
			return;
		}
	}

</script>

<section class="mt-8 text-xl px-4">
	<div class="mx-auto">
		<table class="table-box-1" border="1">
			<tbody>
				<tr>
					<th>번호</th>
					<td>${article.id }${goodRP}${badRP}</td>
				</tr>
				<tr>
					<th>작성날짜</th>
					<td>${article.regDate }</td>
				</tr>
				<tr>
					<th>수정날짜</th>
					<td>${article.updateDate }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${article.extra__writer }</td>
				</tr>
				<tr>
					<th>좋아요</th>
					<td id="likeCount">${article.goodReactionPoint }</td>
				</tr>
				<tr>
					<th>싫어요</th>
					<td id="DislikeCount">${article.badReactionPoint }</td>
				</tr>
				<tr>
					<th>추천</th>
					<%-- 	<td><c:choose>
							<c:when test="${isAlreadyAddGoodRp}">
								<a href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.currentUri}"
									class="reaction btn btn-success">▲</a>&nbsp;&nbsp; <a
									href="/usr/reactionPoint/doBadReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.currentUri}"
									class="reaction btn btn-outline btn-error">▼</a>
							</c:when>
							<c:when test="${isAlreadyAddBadRp}">
								<a href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.currentUri}"
									class="reaction btn btn-outline btn-success">▲</a>&nbsp;&nbsp; <a
									href="/usr/reactionPoint/doBadReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.currentUri}"
									class="reaction btn btn-error">▼</a>
							</c:when>
							<c:otherwise>
								<a href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.currentUri}"
									class="reaction btn btn-outline btn-success">▲</a>&nbsp;&nbsp; <a
									href="/usr/reactionPoint/doBadReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.currentUri}"
									class="reaction btn btn-outline btn-error">▼</a>
							</c:otherwise>
						</c:choose></td> --%>
					<td>
						<!-- href="/usr/reactionPoint/doGoodReaction?relTypeCode=article&relId=${param.id }&replaceUri=${rq.currentUri}" -->
						<button id="likeButton" class="btn btn-outline btn-success" onclick="doGoodReaction(${param.id})">▲</button>

						<button id="DislikeButton" class="btn btn-outline btn-error" onclick="doBadReaction(${param.id})">▼</button>
					</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${article.title }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${article.body }</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td><span class="article-detail__hit-count">${article.hitCount }</span></td>
				</tr>
			</tbody>
		</table>

		<div class="btns mt-5 text-base">
			<button class="btn btn-outline" type="button" onclick="history.back();">뒤로가기</button>
			<c:if test="${article.userCanModify }">
				<a class="btn btn-outline" href="../article/modify?id=${article.id }">수정</a>
			</c:if>
			<c:if test="${article.userCanDelete }">
				<a class="btn btn-outline" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;" href="../article/doDelete?id=${article.id }">삭제</a>
			</c:if>
		</div>
	</div>
</section>
<section>
	<c:if test="${rq.isLogined() }">
		<form action="../comment/doWrite" method="POST" onsubmit="ReplyWrite__submit(this); return false;">
			<input type="hidden" name="relTypeCode" value="article" /> <input type="hidden" name="relId" value="${article.id }" />
			<label class="form-control">
				<div class="comment dropdown dropdown-end ">
					<div tabindex="0" role="button" class="comment btn btn-ghost btn-circle avatar">
						<div class="w-10 rounded-full text-base">
							<img alt="Tailwind CSS Navbar component"
								src="https://health.chosun.com/site/data/img_dir/2023/07/17/2023071701753_0.jpg" />
						</div>
					</div>
					<%-- <div class="comment">${rq.loginedMemberNickname }</div> --%>
				</div>
				<div class="flex-none gap-2 m-3 ">
					<div class="form-control">
						<textarea name="comment" placeholder="댓글을 입력해주세요" class="textarea textarea-bordered h-24"></textarea>
					</div>
				</div>
				<button class="btn btn-outline m-3" type="submit">댓글등록</button>
			</label>
	</c:if>
	<c:if test="${!rq.isLogined() }">
		<a class="btn btn-outline btn-ghost" href="../member/login">LOGIN</a> 하고 댓글 써
	</c:if>
	<div class="comment">
		<c:forEach var="comments" items="${comments }">
			<div class="chat chat-start">
				<div class="chat-image avatar">
					<div class="w-10 rounded-full">
						<img alt="Tailwind CSS Navbar component"
							src="https://health.chosun.com/site/data/img_dir/2023/07/17/2023071701753_0.jpg" />
					</div>
				</div>
				<div class="chat-header">
					${comments.extra__writer }
					<time class="text-xs opacity-50">${comments.updateDate.substring(0,10) }</time>
				</div>
				<div class = "rr">
				<div class="chat-bubble">${comments.comment }</div>
				<button id="likeButton" onclick="doCommentGoodReaction(${param.id},)" style="color:#e0316e" class = "reaction text-xl">♡</button>
				<c:if test="${comments.goodReactionPoint > 0}"><div class = "reaction" style="color:#e0316e" >[${comments.sum }]</div></c:if>
				<c:if test="${comments.memberId == rq.loginedMemberId }">
				<div>${CommentGoodCnt }</div>
					<ul class="flex">
						<li><a class="hover:underline" href="#">···</a>
							<ul>
								<li><button onclick="doModify(${comments.id })">수정</button></li>
								<li><a onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;" href="../comment/doDelete?id=${comments.id }">삭제</a></li>
							</ul></li>
					</ul>
				</c:if>
				</div>
			</div>
		</c:forEach>
	</div>
</section>


</div>


<%@ include file="../common/foot.jspf"%>