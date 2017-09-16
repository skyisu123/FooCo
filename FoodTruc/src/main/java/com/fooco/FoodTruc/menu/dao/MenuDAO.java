package com.fooco.FoodTruc.menu.dao;

import java.util.List;
import java.util.Map;

import com.fooco.FoodTruc.menu.vo.MenuVO;

public interface MenuDAO {
	
	// �޴� ��� ó��
	public int insertMenu(Map<String, Object> map);
	// �޴� �̹��� ��� ó��
	public int insertMenuImage(Map<String, Object> map);
	// �޴� ��������
	public List<MenuVO> selectDetailMenu(int truckNum);		// Menu���̺� ��������
	// �޴� delete
	public void deleteMenuModifyBefore(int truckNum);
}
