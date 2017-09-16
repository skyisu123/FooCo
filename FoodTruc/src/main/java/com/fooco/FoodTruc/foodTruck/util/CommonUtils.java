package com.fooco.FoodTruc.foodTruck.util;

import java.util.UUID;

// 파일명이 32글자로 랜덤한 문자열(숫자포함)만들어서 변환해주는 기능을 하는 클래스
// 추후, 여러기능이 추가될 때 쓰이는 클래스
public class CommonUtils {
	
	// 랜덤한 문자열 생성
	public static String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
	
}
