package com.fooco.FoodTruc.menuImage.dao;

import java.util.List;

import com.fooco.FoodTruc.menuImage.vo.MenuImageVO;

public interface MenuImageDAO {
	public List<MenuImageVO> selectDetailMenuImage(int menuCode);	//MenuImage���̺� ��������
	// �޴� �̹����� ����
	public void deleteMenuModifyBefore(int menuCode);
}
