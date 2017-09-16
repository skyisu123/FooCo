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

   // ������Ʈ ���� �� ���ϸ��� ����Ǵ� �޼ҵ�
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

         mav.addObject("errorMsg", "����Ʈ�� �ҷ��ü� ���ų� �����Ͱ� ����ֽ��ϴ�.");
         mav.setViewName("index");
      }

      return mav;
   }

   // view ���� �ؿ� �ִ� jsp���������� logo�� ������ ����
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

         mav.addObject("errorMsg", "����Ʈ�� �ҷ��ü� ���ų� �����Ͱ� ����ֽ��ϴ�.");
         mav.setViewName("index");
      }

      return mav;
   }

   // logo, url�� ������� index.jsp ã�ư�
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

         mav.addObject("errorMsg", "����Ʈ�� �ҷ��ü� ���ų� �����Ͱ� ����ֽ��ϴ�.");
         mav.setViewName("index");
      }

      return mav;
   }

}