package com.fooco.FoodTruc.menuImage.dao;

import java.util.List;

import com.fooco.FoodTruc.menuImage.vo.MenuImageVO;

public interface MenuImageDAO {
	public List<MenuImageVO> selectDetailMenuImage(int menuCode);	//MenuImage테이블 가져오기
	// 메뉴 이미지들 삭제
	public void deleteMenuModifyBefore(int menuCode);
}
