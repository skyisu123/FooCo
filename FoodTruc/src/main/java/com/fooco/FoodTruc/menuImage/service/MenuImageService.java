package com.fooco.FoodTruc.menuImage.service;

import java.util.List;

import com.fooco.FoodTruc.menuImage.vo.MenuImageVO;

public interface MenuImageService {
	public List<MenuImageVO> selectDetailMenuImage(int menuCode);	//MenuImage���̺� ��������
	
	// �޴� �̹����� ����
	public void deleteMenuModifyBefore(int menuCode);
}
