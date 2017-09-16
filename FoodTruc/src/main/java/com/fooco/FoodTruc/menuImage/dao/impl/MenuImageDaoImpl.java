package com.fooco.FoodTruc.menuImage.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.fooco.FoodTruc.foodTruck.dao.FoodTruckDAO;
import com.fooco.FoodTruc.menuImage.dao.MenuImageDAO;
import com.fooco.FoodTruc.menuImage.vo.MenuImageVO;

import common.CommonDao;

@Repository
public class MenuImageDaoImpl extends CommonDao implements MenuImageDAO{

	@Override
	public List<MenuImageVO> selectDetailMenuImage(int menuCode) {
		return getSqlSession().getMapper(MenuImageDAO.class).selectDetailMenuImage(menuCode);
	}
	
	@Override
	public void deleteMenuModifyBefore(int menuCode) {
		getSqlSession().getMapper(MenuImageDAO.class).deleteMenuModifyBefore(menuCode);
	}
	
}
