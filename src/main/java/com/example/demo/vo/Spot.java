package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spot {
	private int id;
	private String city;
	private String spot_name;
	private String location;
	private String initialDate;
	private String updateDate;
	private String area;
	private String etc;
}