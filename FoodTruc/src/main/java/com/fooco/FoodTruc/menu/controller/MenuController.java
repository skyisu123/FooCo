package com.fooco.FoodTruc.menu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.menu.service.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired 
	private MenuService menuService;
	
	// 판매 트럭 2단계 메뉴정보 입력 처리
	/*
	 * 판매자가 메뉴 추가 버튼을 눌러 input태그의 이름이 확장됨에 따라
	 * 어노테이션 이용하여 해당하는 name값을 List<> 형태로 받으면
	 * 스프링에서 자동으로 ,로 구분하여 값이 List에 저장이 된다.
	 */
	@RequestMapping(value="truckMenuAddAction.do", method=RequestMethod.POST)
	public ModelAndView truckMenuAddAction(
			MultipartHttpServletRequest multi, @RequestParam("truckNum") int truckNum,
			@RequestParam("menuCategory") List<String> menuCategory, @RequestParam("menuName") List<String> menuName,
			@RequestParam("menuPrice") List<Integer> menuPrice, @RequestParam("inventory") List<Integer> inventory, HttpSession session) throws Exception{
		System.out.println("MenuController truckMenuAddAction으로 이동~");
		ModelAndView mav = new ModelAndView();
		
		// List에 Map을 넣어서 service로 전달. ( service에서 코드가 길어지니 컨트롤러에서 우선 데이터 가공 )
		Map<String, Object> map = null;
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < menuCategory.size(); i++){
			map = new HashMap<String, Object>();
			map.put("menuCode", -1);	// 시퀀스는 -1 로 초기화.
			map.put("truckNum", truckNum);
			map.put("menuCategory", menuCategory.get(i));
			map.put("menuName", menuName.get(i));
			map.put("menuPrice", menuPrice.get(i));
			map.put("inventory", inventory.get(i));
			lists.add(map);
		}
		mav = menuService.insertMenu(multi, lists, map, session);
		
		return mav;
	}
	
	@RequestMapping(value="truckMenuModifyAction.do", method=RequestMethod.POST)
	public ModelAndView truckMenuModifyAction(MultipartHttpServletRequest multi, @RequestParam("truckNum") int truckNum,
			@RequestParam("menuCategory") List<String> menuCategory, @RequestParam("menuName") List<String> menuName,
			@RequestParam("menuPrice") List<Integer> menuPrice, @RequestParam("inventory") List<Integer> inventory, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView();
		// List에 Map을 넣어서 service로 전달. ( service에서 코드가 길어지니 컨트롤러에서 우선 데이터 가공 )
		Map<String, Object> map = null;
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < menuCategory.size(); i++){
			map = new HashMap<String, Object>();
			map.put("menuCode", -1);	// 시퀀스는 -1 로 초기화.
			map.put("truckNum", truckNum);
			map.put("menuCategory", menuCategory.get(i));
			map.put("menuName", menuName.get(i));
			map.put("menuPrice", menuPrice.get(i));
			map.put("inventory", inventory.get(i));
			
			lists.add(map);	// 이 map들을 list에 저장
		}
		
		mav = menuService.modifyMenu(multi, lists, map, session);
		
		
		return mav;
	}
}











