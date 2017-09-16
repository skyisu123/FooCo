package com.fooco.FoodTruc.menu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.menu.vo.MenuVO;

public interface MenuService {
	
	// 메뉴 등록처리
	public ModelAndView insertMenu(MultipartHttpServletRequest multi, List<Map<String, Object>> lists, Map<String, Object> map, HttpSession session) throws Exception;

	// 메뉴 가져오기
	public List<MenuVO> selectDetailMenu(int truckNum);	//Menu테이블 가져오기
	
	
	// 수정 전 메뉴 테이블, 메뉴 이미지 테이블에서 삭제
	public ModelAndView modifyMenu(MultipartHttpServletRequest multi, List<Map<String, Object>> lists, Map<String, Object> map, HttpSession session) throws Exception;
}
