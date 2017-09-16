package com.fooco.FoodTruc.likes.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fooco.FoodTruc.likes.dao.LikesDAO;

import common.CommonDao;

@Repository
public class LikesDaoImpl extends CommonDao implements LikesDAO{
	// �ش� Ʈ���� like�� ��� �ִ��� Ȯ�� 
	@Override
	public int selectIsLikeExist(Map<String, Object> map) {
		return getSqlSession().getMapper(LikesDAO.class).selectIsLikeExist(map);
	}

	@Override
	public int insertLikes(Map<String, Object> map) {
		
		return getSqlSession().getMapper(LikesDAO.class).insertLikes(map);
	}

	@Override
	public void deleteLikes(Map<String, Object> map) {
		getSqlSession().getMapper(LikesDAO.class).deleteLikes(map);
	}
}
