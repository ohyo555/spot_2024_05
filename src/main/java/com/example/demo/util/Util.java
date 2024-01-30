package com.example.demo.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class Util {

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

}