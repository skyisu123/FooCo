package com.fooco.FoodTruc.menu.dao.impl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fooco.FoodTruc.menu.dao.MenuDAO;
import com.fooco.FoodTruc.menu.vo.MenuVO;

import common.CommonDao;

@Repository
public class MenuDaoImpl extends CommonDao implements MenuDAO{

	@Override
	public int insertMenu(Map<String, Object> map) {
		return getSqlSession().getMapper(MenuDAO.class).insertMenu(map);
	}
	// 메뉴이미지 등록처리
	@Override
	public int insertMenuImage(Map<String, Object> map) {
		return getSqlSession().getMapper(MenuDAO.class).insertMenuImage(map);
	}
	
	@Override
	public List<MenuVO> selectDetailMenu(int truckNum){
		return getSqlSession().getMapper(MenuDAO.class).selectDetailMenu(truckNum);
	}
	
	@Override
	public void deleteMenuModifyBefore(int truckNum) {
		getSqlSession().getMapper(MenuDAO.class).deleteMenuModifyBefore(truckNum);
	}
}
