package com.fooco.FoodTruc.likes.dao;

import java.util.Map;

public interface LikesDAO {
	
	// 해당 트럭의 like 존재 유무 확인
	public int selectIsLikeExist(Map<String, Object> map);
	// 해당 트럭 좋아요한 멤버와 트럭고유번호 삽입
	public int insertLikes(Map<String, Object> map);
	// 해당 트럭 좋아요 취소시 기존 멤버와 번호에 해당하는 컬럼 삭제
	public void deleteLikes(Map<String, Object> map);
}
