package com.fooco.FoodTruc.likes.service;


public interface LikesService {
	// �ش� Ʈ���� like ���� ���� Ȯ��
	public int selectIsLikeExist(String memberId, int truckNum);
	// �ش� Ʈ�� ���ƿ��� ����� Ʈ��������ȣ ����
	public int insertLikes(String memberId, int truckNum);
	// �ش� Ʈ�� ���ƿ� ��ҽ� ���� ����� ��ȣ�� �ش��ϴ� �÷� ����
	public void deleteLikes(String memberId, int truckNum);
}
