package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.CommentRepository;
import com.example.demo.util.Ut;
import com.example.demo.vo.Comment;
import com.example.demo.vo.ResultData;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;

	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	public List<Comment> getForPrintComments(int loginedMemberId, String relTypeCode, int relId) {
		return commentRepository.getForPrintComments(loginedMemberId, relTypeCode, relId);
	}

	public ResultData<Integer> writeComment(int loginedMemberId, String relTypeCode, int relId, String comment) {
		commentRepository.writeComment(loginedMemberId, relTypeCode, relId, comment);

		int id = commentRepository.getLastInsertId();

		return ResultData.from("S-1", Ut.f("%d번 댓글이 생성되었습니다", id), "id", id);
	}

	public Comment getComment(int id) {
		return commentRepository.getComment(id);
	}

	public ResultData userCommentCanModify(int loginedMemberId, String comment) {
		// TODO Auto-generated method stub
		return null;
	}

	public void modifyComment(int id, String comment) {
		commentRepository.modifyComment(id, comment);
	}
}