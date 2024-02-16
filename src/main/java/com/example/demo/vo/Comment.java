package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
	private int id;
	private String regDate;
	private String updateDate;
	private int memberId;
	private int level;
	private String body;
	private int relId;
	private int hitCount;
	
	private String extra__writer;

}