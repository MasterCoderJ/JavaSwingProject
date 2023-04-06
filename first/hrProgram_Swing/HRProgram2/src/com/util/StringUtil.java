package com.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {

	// 넘겨받은 숫자가 1자리 수 이면, 앞에 0을 붙임
	// 누군가가 이 메서드를 호출하면, 처리결과를 반환받는다
	public static String getNumString(int num) {
		String str = null;

		if (num < 10) {// 한자리수
			str = "0" + num;
		} else { // 두자리수
			str = Integer.toString(num); // Wrapper적용--> 인트형을 스트링형으로
		}
		return str;
	}

	// 확장자 추출하여 반환받기
	public static String getExtend(String filename) {

		int lastIndex = filename.lastIndexOf(".");
		
		return filename.substring(lastIndex+1,filename.length());
	}
	
	//비밀번호 암호화
	//자바의 보안과 관련된 기능을 지원하는 api가 모여있는 패키지가 java.security이다
	public static String getConvertedPassword(String pass) {
		//암호화 객체

		//String은 불변이다!! 따라서 그 값이 변경될 수 없다
		//따라서, String 객체는 반복문 횟수가 클때는 절대 누적식을 사용해서는 안된다
		//해결책) 변경가능한 분자열 객체를 지원하는 StringBuffer라는 클래스와 StringBuilder 등을 활용하자!!
		StringBuffer hexString = new StringBuffer();
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[]hash= digest.digest(pass.getBytes("UTF-8"));
			
			
			for(int i=0; i<hash.length;i++) {
				String hex = Integer.toHexString(0xff& hash[i]);
				if(hex.length()==1) {
					hexString.append("0");
				}
				
				hexString.append(hex);
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return hexString.toString();
	}
	
	//파일명 반환하기
	public static String createFileName(String url) {
		
		//파일명 만들기
		long time =  System.currentTimeMillis();
		//확장자 구하기
		String ext = StringUtil.getExtend(url);
		
		return time+"."+ext;
	}

	
	
	/*
	public static void main(String[] args) {
		getConvertedPassword("minzino");
	}*/
}
