package com.goodee.ex12.util;

public class SecurityUtils {

	// new SecurityUtils 없이 메소드 호출하기 위해서 static 메소드로 구현
	
	public static String XSS(String s) {
		s = s.replaceAll("<", "&lt;");
		s = s.replaceAll(">", "&gt;");
		s = s.replaceAll("\"", "&quot;");
		s = s.replaceAll("\'", "&#x27");
		return s;
	}
	
	
}
