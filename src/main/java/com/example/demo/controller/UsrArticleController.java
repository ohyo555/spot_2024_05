package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ArticleService;
import com.example.demo.service.BoardService;
import com.example.demo.service.CommentService;
import com.example.demo.service.ReactionPointService;
import com.example.demo.vo.Article;
import com.example.demo.vo.Comment;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;
import com.example.demo.vo.Spot;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UsrArticleController {
	
	@Autowired
	private Rq rq;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;

	@Autowired
	private ReactionPointService reactionPointService;
	
	public UsrArticleController() {

	}

	// 액션 메서드
	@RequestMapping("/usr/article/list")
	public String showList(HttpServletRequest req, Model model, @RequestParam(defaultValue = "1") int boardId,
			@RequestParam(defaultValue = "1") int page,
			@RequestParam(defaultValue = "title,body") String searchKeywordTypeCode,
			@RequestParam(defaultValue = "") String searchKeyword) {

		Rq rq = (Rq) req.getAttribute("rq");
	
		List<Spot> spots = articleService.getForPrintSpots();
	
		model.addAttribute("spots", spots);

		return "usr/article/list";
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);
		
		ResultData usersReactionRd = reactionPointService.usersReaction(rq.getLoginedMemberId(), "article", id);
		
		if (usersReactionRd.isSuccess()) {
			model.addAttribute("userCanMakeReaction", usersReactionRd.isSuccess());
		}
		
		List<Comment> comments = commentService.getForPrintComments(rq.getLoginedMemberId(), "article", id);
		
//		ResultData usersCommentReactionRd = reactionPointService.usersReaction(rq.getLoginedMemberId(), "comment", id);
		
		if (usersReactionRd.isSuccess()) {
			model.addAttribute("userCanMakeReaction", usersReactionRd.isSuccess());
		}
		
		int commentsCount = comments.size();
	
		model.addAttribute("loginedMember", rq.getLoginedMemberId());
		model.addAttribute("loginedMemberNickname", rq.getLoginedMemberNickname());
		model.addAttribute("article", article);
		model.addAttribute("comments", comments);
		model.addAttribute("commentsCount", commentsCount);
		model.addAttribute("isAlreadyAddGoodRp",reactionPointService.isAlreadyAddGoodRp(rq.getLoginedMemberId(), id, "article"));
		model.addAttribute("isAlreadyAddBadRp",reactionPointService.isAlreadyAddBadRp(rq.getLoginedMemberId(), id, "article"));

		return "usr/article/detail";
	}

}