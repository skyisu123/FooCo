package com.fooco.FoodTruc;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.foodTruck.service.impl.FoodTruckServiceImpl;
import com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
   @Autowired
   private FoodTruckServiceImpl foodTruckService;

   // 프로젝트 실행 시 제일먼저 실행되는 메소드
   @RequestMapping(value = "/", method = RequestMethod.GET)
   public ModelAndView home(Model model) {
      System.out.println("index home method Join");
      ModelAndView mav = new ModelAndView();
      int start = 1;
      int end = 10;

      List<FoodTruckVO> rankLists = foodTruckService.truckRankList(start, end);

      if (!rankLists.isEmpty()) {

         for(FoodTruckVO vo : rankLists){
            System.out.println(vo.getRN());
            System.out.println(vo.getTruckName());
         }
         mav.addObject("rankLists", rankLists);
         mav.setViewName("index");

      } else {

         mav.addObject("errorMsg", "리스트를 불러올수 없거나 데이터가 비어있습니다.");
         mav.setViewName("index");
      }

      return mav;
   }

   // view 폴더 밑에 있는 jsp페이지에서 logo를 누르면 실행
   @RequestMapping(value = "/index.do")
   public ModelAndView index() {

      System.out.println("index Controller Join");
      ModelAndView mav = new ModelAndView();
      int start = 1;
      int end = 10;

      List<FoodTruckVO> rankLists = foodTruckService.truckRankList(start, end);

      if (!rankLists.isEmpty()) {

         for(FoodTruckVO vo : rankLists){
            System.out.println(vo.getRN());
            System.out.println(vo.getTruckName());
         }
         
         mav.addObject("rankLists", rankLists);
         mav.setViewName("index");

      } else {

         mav.addObject("errorMsg", "리스트를 불러올수 없거나 데이터가 비어있습니다.");
         mav.setViewName("index");
      }

      return mav;
   }

   // logo, url에 써넣으면 index.jsp 찾아감
   @RequestMapping("/foodtruc/index")
   public ModelAndView index(Model model) {

      System.out.println("index log, url Controller Join");
      ModelAndView mav = new ModelAndView();
      int start = 1;
      int end = 10;

      List<FoodTruckVO> rankLists = foodTruckService.truckRankList(start, end);

      if (!rankLists.isEmpty()) {

         for(FoodTruckVO vo : rankLists){
            System.out.println(vo.getRN());
            System.out.println(vo.getTruckName());
         }
         mav.addObject("rankLists", rankLists);
         mav.setViewName("index");

      } else {

         mav.addObject("errorMsg", "리스트를 불러올수 없거나 데이터가 비어있습니다.");
         mav.setViewName("index");
      }

      return mav;
   }

}