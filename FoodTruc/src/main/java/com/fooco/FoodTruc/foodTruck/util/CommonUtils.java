package com.fooco.FoodTruc.foodTruck.util;

import java.util.UUID;

// ���ϸ��� 32���ڷ� ������ ���ڿ�(��������)���� ��ȯ���ִ� ����� �ϴ� Ŭ����
// ����, ��������� �߰��� �� ���̴� Ŭ����
public class CommonUtils {
	
	// ������ ���ڿ� ����
	public static String getRandomString(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
	
}
