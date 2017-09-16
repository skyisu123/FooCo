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
	
	// �Ǹ� Ʈ�� 2�ܰ� �޴����� �Է� ó��
	/*
	 * �Ǹ��ڰ� �޴� �߰� ��ư�� ���� input�±��� �̸��� Ȯ��ʿ� ����
	 * ������̼� �̿��Ͽ� �ش��ϴ� name���� List<> ���·� ������
	 * ���������� �ڵ����� ,�� �����Ͽ� ���� List�� ������ �ȴ�.
	 */
	@RequestMapping(value="truckMenuAddAction.do", method=RequestMethod.POST)
	public ModelAndView truckMenuAddAction(
			MultipartHttpServletRequest multi, @RequestParam("truckNum") int truckNum,
			@RequestParam("menuCategory") List<String> menuCategory, @RequestParam("menuName") List<String> menuName,
			@RequestParam("menuPrice") List<Integer> menuPrice, @RequestParam("inventory") List<Integer> inventory, HttpSession session) throws Exception{
		System.out.println("MenuController truckMenuAddAction���� �̵�~");
		ModelAndView mav = new ModelAndView();
		
		// List�� Map�� �־ service�� ����. ( service���� �ڵ尡 ������� ��Ʈ�ѷ����� �켱 ������ ���� )
		Map<String, Object> map = null;
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < menuCategory.size(); i++){
			map = new HashMap<String, Object>();
			map.put("menuCode", -1);	// �������� -1 �� �ʱ�ȭ.
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
		// List�� Map�� �־ service�� ����. ( service���� �ڵ尡 ������� ��Ʈ�ѷ����� �켱 ������ ���� )
		Map<String, Object> map = null;
		List<Map<String, Object>> lists = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < menuCategory.size(); i++){
			map = new HashMap<String, Object>();
			map.put("menuCode", -1);	// �������� -1 �� �ʱ�ȭ.
			map.put("truckNum", truckNum);
			map.put("menuCategory", menuCategory.get(i));
			map.put("menuName", menuName.get(i));
			map.put("menuPrice", menuPrice.get(i));
			map.put("inventory", inventory.get(i));
			
			lists.add(map);	// �� map���� list�� ����
		}
		
		mav = menuService.modifyMenu(multi, lists, map, session);
		
		
		return mav;
	}
}











