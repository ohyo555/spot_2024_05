package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReactionPoint {
	private int id;
	private int regDate;
	private int updateDate;
	private int memberId;
	private int relTypeCode;
	private int relId;
	private int point;
}