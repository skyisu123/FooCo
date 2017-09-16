package com.fooco.FoodTruc.foodTruck.controller;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.foodTruck.service.FoodTruckService;
import com.fooco.FoodTruc.menu.service.MenuService;
import com.fooco.FoodTruc.menu.vo.MenuVO;
import com.fooco.FoodTruc.menuImage.service.MenuImageService;
import com.fooco.FoodTruc.menuImage.vo.MenuImageVO;



@Controller
@RequestMapping("/foodTruck")
public class FoodTruckController {

	@Autowired
	private FoodTruckService foodTruckService; 
	@Autowired
	private MenuService menuService;
	@Autowired
	private MenuImageService menuImageService;
	
	// �Ǹ��� Ǫ��Ʈ�� ���� �Է������� �̵�
	@RequestMapping(value="truckAdd.do", method={RequestMethod.GET, RequestMethod.POST})
	public void truckAdd(){System.out.println("FoodTruckController���� Ʈ�� ��������� �̵�~");}
	
	// �Ǹ� Ʈ�� �⺻���� �Է�ó��
	@RequestMapping(value="truckAddAction.do", method=RequestMethod.POST)
	public ModelAndView truckAddAction(MultipartHttpServletRequest multi, @RequestParam Map<String, Object> map, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		int insertResult = foodTruckService.insertTruck(multi, map, session);	// 1�ܰ� FoodTruck���̺� PK ������ insert�� ���������� map��ü�� ��ȯ�� ����
		if(insertResult > 0){
			mav.addObject("oneStepResult", "SUCCESS");
			mav.addObject("truckNum", map.get("truckNum"));
			mav.setViewName("forward:/foodTruck/truckAdd.do");
		}
		else{
			mav.addObject("oneStepResult", "FAIL");
		}
		
		return mav;
	}
	// 2�ܰ� �޴� ��� ó���� MenuController �� ..
	
	
	// ��ϵ� Ʈ�� ���� ���������� �̵�
	@RequestMapping(value="/truckModify.do")
	public ModelAndView truckModify(@RequestParam(value="truckNum") int truckNum){
		ModelAndView mav = new ModelAndView();
		mav.addObject("truckNum", truckNum);
		mav = foodTruckService.truckModify(truckNum, mav);
		mav.setViewName("foodTruck/truckModify");
		return mav;
	}
	
	// Ʈ�� ���� ���� ó�� 
	@RequestMapping(value="/truckModifyAction.do")
	public ModelAndView truckModifyAction(MultipartHttpServletRequest multi, @RequestParam Map<String, Object> map, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		mav = foodTruckService.updateTruck(multi, map, mav, session);
		
		return mav;
	}
	
	// ��ϵ� �޴� ���� ������ �̵�
	@RequestMapping(value="/truckMenuModify.do")
	public ModelAndView truckMenuModify(@RequestParam(value="truckNum") int truckNum){
		ModelAndView mav = new ModelAndView();
		int menuCode = -1; // �޴��ڵ� ��ȣ -1�� �ʱ�ȭ
		int menuImageSize = -1;
		
		mav.addObject("truckNum", truckNum);
		List<MenuVO> menuList = menuService.selectDetailMenu(truckNum);
		mav.addObject("menuList", menuList);
		
		int temp = 0;
		for(int i = 0; i < menuList.size(); i++){
			// �޴��ڵ� �ִ밪 ���ϱ�
			temp = menuList.get(i).getMenuCode();
			if(menuCode < temp) menuCode = temp;
			temp = 0;
		}
		List<MenuImageVO> menuImageList = menuImageService.selectDetailMenuImage(menuCode);
		mav.addObject("menuImageList", menuImageList);
		
		if(!menuImageList.isEmpty()) menuImageSize = menuImageList.size();
		else menuImageSize = 0;
		mav.addObject("menuSize", menuImageSize); //file��ư name�Ӽ��� �ε��� �Ҵ�����
		mav.setViewName("foodTruck/truckMenuModify");
		
		return mav;
	}
	
}















