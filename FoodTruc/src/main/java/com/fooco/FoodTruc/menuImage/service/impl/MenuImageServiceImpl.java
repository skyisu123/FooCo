package com.fooco.FoodTruc.menuImage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fooco.FoodTruc.menu.dao.MenuDAO;
import com.fooco.FoodTruc.menuImage.dao.impl.MenuImageDaoImpl;
import com.fooco.FoodTruc.menuImage.service.MenuImageService;
import com.fooco.FoodTruc.menuImage.vo.MenuImageVO;

@Service("menuImageService")
public class MenuImageServiceImpl implements MenuImageService{
	@Autowired
	private MenuImageDaoImpl menuImageDao;
	
	// �޴� �̹��� ����Ʈ ��������
	@Override
	public List<MenuImageVO> selectDetailMenuImage(int menuCode) {
		List<MenuImageVO> list = menuImageDao.selectDetailMenuImage(menuCode);
		if(!list.isEmpty()){
			return list;
		}
		else return null;
	}
	
	// �޴� �̹����� ����
	@Override
	public void deleteMenuModifyBefore(int menuCode) {
		menuImageDao.selectDetailMenuImage(menuCode);
	}
}
