package com.fooco.FoodTruc.likes.dao;

import java.util.Map;

public interface LikesDAO {
	
	// �ش� Ʈ���� like ���� ���� Ȯ��
	public int selectIsLikeExist(Map<String, Object> map);
	// �ش� Ʈ�� ���ƿ��� ����� Ʈ��������ȣ ����
	public int insertLikes(Map<String, Object> map);
	// �ش� Ʈ�� ���ƿ� ��ҽ� ���� ����� ��ȣ�� �ش��ϴ� �÷� ����
	public void deleteLikes(Map<String, Object> map);
}
