package com.fooco.FoodTruc.likes.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooco.FoodTruc.likes.dao.LikesDAO;
import com.fooco.FoodTruc.likes.service.LikesService;

@Service("likesService")
public class LikesServiceImpl implements LikesService{

	@Autowired
	private LikesDAO likesDao;
	
	@Override
	public int selectIsLikeExist(String memberId, int truckNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("truckNum", truckNum);
		
		return likesDao.selectIsLikeExist(map);
	}

	@Override
	public int insertLikes(String memberId, int truckNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("truckNum", truckNum);
		
		return likesDao.insertLikes(map);
	}

	@Override
	public void deleteLikes(String memberId, int truckNum) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberId", memberId);
		map.put("truckNum", truckNum);
		likesDao.deleteLikes(map);
	}
	
}
