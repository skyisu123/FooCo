package com.fooco.FoodTruc.menu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.menu.vo.MenuVO;

public interface MenuService {
	
	// �޴� ���ó��
	public ModelAndView insertMenu(MultipartHttpServletRequest multi, List<Map<String, Object>> lists, Map<String, Object> map, HttpSession session) throws Exception;

	// �޴� ��������
	public List<MenuVO> selectDetailMenu(int truckNum);	//Menu���̺� ��������
	
	
	// ���� �� �޴� ���̺�, �޴� �̹��� ���̺��� ����
	public ModelAndView modifyMenu(MultipartHttpServletRequest multi, List<Map<String, Object>> lists, Map<String, Object> map, HttpSession session) throws Exception;
}
