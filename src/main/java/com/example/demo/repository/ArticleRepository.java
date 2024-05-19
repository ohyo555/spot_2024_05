package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.vo.Spot;


@Mapper
public interface ArticleRepository {

	@Select("""
			SELECT *
			FROM spot
			""")
	public List<Spot> getForPrintSpots();

/*	@Select("""
			SELECT answer
			FROM qna
			WHERE answer LIKE CONCAT('%',#{keyword},'%')
			""")
	public List<String> dofind(String keyword); */

	@Select("""
			SELECT answer
			FROM qna
			WHERE `key` = "${key}"
			""")
	public List<String> dofind(String keyword, String key);
	
}