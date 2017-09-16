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
	
	// 판매자 푸드트럭 정보 입력페이지 이동
	@RequestMapping(value="truckAdd.do", method={RequestMethod.GET, RequestMethod.POST})
	public void truckAdd(){System.out.println("FoodTruckController에서 트럭 등록페이지 이동~");}
	
	// 판매 트럭 기본정보 입력처리
	@RequestMapping(value="truckAddAction.do", method=RequestMethod.POST)
	public ModelAndView truckAddAction(MultipartHttpServletRequest multi, @RequestParam Map<String, Object> map, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		int insertResult = foodTruckService.insertTruck(multi, map, session);	// 1단계 FoodTruck테이블 PK 시퀀스 insert후 시퀀스값이 map객체에 반환될 것임
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
	// 2단계 메뉴 등록 처리는 MenuController 로 ..
	
	
	// 등록된 트럭 정보 수정페이지 이동
	@RequestMapping(value="/truckModify.do")
	public ModelAndView truckModify(@RequestParam(value="truckNum") int truckNum){
		ModelAndView mav = new ModelAndView();
		mav.addObject("truckNum", truckNum);
		mav = foodTruckService.truckModify(truckNum, mav);
		mav.setViewName("foodTruck/truckModify");
		return mav;
	}
	
	// 트럭 정보 수정 처리 
	@RequestMapping(value="/truckModifyAction.do")
	public ModelAndView truckModifyAction(MultipartHttpServletRequest multi, @RequestParam Map<String, Object> map, HttpSession session) throws Exception{
		ModelAndView mav = new ModelAndView();
		
		mav = foodTruckService.updateTruck(multi, map, mav, session);
		
		return mav;
	}
	
	// 등록된 메뉴 수정 페이지 이동
	@RequestMapping(value="/truckMenuModify.do")
	public ModelAndView truckMenuModify(@RequestParam(value="truckNum") int truckNum){
		ModelAndView mav = new ModelAndView();
		int menuCode = -1; // 메뉴코드 번호 -1로 초기화
		int menuImageSize = -1;
		
		mav.addObject("truckNum", truckNum);
		List<MenuVO> menuList = menuService.selectDetailMenu(truckNum);
		mav.addObject("menuList", menuList);
		
		int temp = 0;
		for(int i = 0; i < menuList.size(); i++){
			// 메뉴코드 최대값 구하기
			temp = menuList.get(i).getMenuCode();
			if(menuCode < temp) menuCode = temp;
			temp = 0;
		}
		List<MenuImageVO> menuImageList = menuImageService.selectDetailMenuImage(menuCode);
		mav.addObject("menuImageList", menuImageList);
		
		if(!menuImageList.isEmpty()) menuImageSize = menuImageList.size();
		else menuImageSize = 0;
		mav.addObject("menuSize", menuImageSize); //file버튼 name속성의 인덱스 할당위해
		mav.setViewName("foodTruck/truckMenuModify");
		
		return mav;
	}
	
}















