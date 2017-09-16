package com.fooco.FoodTruc.menu.dao;

import java.util.List;
import java.util.Map;

import com.fooco.FoodTruc.menu.vo.MenuVO;

public interface MenuDAO {
	
	// 메뉴 등록 처리
	public int insertMenu(Map<String, Object> map);
	// 메뉴 이미지 등록 처리
	public int insertMenuImage(Map<String, Object> map);
	// 메뉴 가져오기
	public List<MenuVO> selectDetailMenu(int truckNum);		// Menu테이블 가져오기
	// 메뉴 delete
	public void deleteMenuModifyBefore(int truckNum);
}
