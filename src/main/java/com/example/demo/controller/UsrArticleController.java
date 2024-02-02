package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.service.ArticleService;
import com.example.demo.util.Ut;
import com.example.demo.vo.Article;
import com.example.demo.vo.ResultData;
import com.example.demo.vo.Rq;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsrArticleController {

	@Autowired
	private ArticleService articleService;

	public UsrArticleController() {

	}

	// 액션 메서드
	@RequestMapping("/usr/article/list")
	public String showList(HttpSession httpSession, Model model) {
		
		List<Article> articles = articleService.getArticles();

		model.addAttribute("articles", articles);
		
		return "usr/article/list";
		
	}
	
	@RequestMapping("/usr/article/detail")
	public String showDetail(HttpServletRequest req, Model model, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		model.addAttribute("article", article);

		return "usr/article/detail";
	}
	
	@RequestMapping("/usr/article/modify")
	public String showModify(HttpServletRequest req, Model model, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		Article article = articleService.getForPrintArticle(rq.getLoginedMemberId(), id);

		model.addAttribute("article", article);

		return "usr/article/modify";
	}
	
	@RequestMapping("/usr/article/doWrite")
	@ResponseBody
	public ResultData<Article> doWrite(HttpServletRequest req, Model model, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		System.out.println("++++++++++++++++++++" + rq.getLoginedMemberId());
		
		if (rq.isLogined() == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}

		if (Ut.isNullOrEmpty(title)) {
			return ResultData.from("F-1", "제목을 입력해주세요");
		}
		if (Ut.isNullOrEmpty(body)) {
			return ResultData.from("F-2", "내용을 입력해주세요");
		}

		ResultData<Integer> writeArticleRd = articleService.writeArticle(rq.getLoginedMemberId(), title, body);
		
		int id = (int) writeArticleRd.getData1();

		Article article = articleService.getArticle(id);
		
		return ResultData.newData(writeArticleRd, "article", article);
	}

	// 로그인 체크 -> 유무 체크 -> 권한 체크 -> 수정
	@RequestMapping("/usr/article/doModify")
	@ResponseBody
	public ResultData<Integer> doModify(HttpServletRequest req, Model model, int id, String title, String body) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		if (rq.isLogined() == false) {
			return ResultData.from("F-A", "로그인 후 이용해주세요");
		}
		
		Article article = articleService.getArticle(id);

		if (article == null) {
			return ResultData.from("F-1", Ut.f("%d번 글은 존재하지 않습니다", id), "id", id);
		}

		ResultData loginedMemberCanModifyRd = articleService.userCanModify(rq.getLoginedMemberId(), article);

		if (loginedMemberCanModifyRd.isSuccess()) {
			articleService.modifyArticle(id, title, body);
		}

		return ResultData.from(loginedMemberCanModifyRd.getResultCode(), loginedMemberCanModifyRd.getMsg(), "id", id);
	}

	// 로그인 체크 -> 유무 체크 -> 권한 체크 -> 삭제
	@RequestMapping("/usr/article/doDelete")
	@ResponseBody
	public String doDelete(HttpServletRequest req, int id) {
		Rq rq = (Rq) req.getAttribute("rq");
		
		if (rq.isLogined() == false) {
			return Ut.jsReplace("F-A", "로그인 후 이용해주세요", "../member/login");
		}
	
		Article article = articleService.getArticle(id);

		if (article == null) {
			return Ut.jsHistoryBack("F-1", Ut.f("%d번 글은 존재하지 않습니다", id));
		}

		ResultData loginedMemberCanDeleteRd = articleService.userCanDelete(rq.getLoginedMemberId(), article);
		
		if (loginedMemberCanDeleteRd.isSuccess()) {
			articleService.deleteArticle(id);
		}
		
		return Ut.jsReplace(loginedMemberCanDeleteRd.getResultCode(), loginedMemberCanDeleteRd.getMsg(),
				"../article/list");
	}

}