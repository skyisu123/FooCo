package com.fooco.FoodTruc.replyTruck.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.foodTruck.util.PagingUtil;
import com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO;
import com.fooco.FoodTruc.replyTruck.service.impl.ReplyTruckServiceImpl;
import com.fooco.FoodTruc.replyTruck.vo.ReplyTruckVO;

@Controller
public class ReplyTruckController {
	
	@Autowired
	private ReplyTruckServiceImpl replyTruckService;
	
	//////////////////////////// By Young Min //////////////////////////////////////
	
	@RequestMapping(value="/reply/replyTruckList.do", method = RequestMethod.GET)
	public ModelAndView replyTruckList(
				@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage,	//현재페이지
				@RequestParam(value="truckNum") int truckNum	// 조회할 트럭에 대한 한줄평 리스트의 truck_num
		)
	{
		int totalRecordCount = -1;
		ModelAndView mav = new ModelAndView();
		System.out.println("ReplyTruckController ajax이용 한줄평 리스트 가져오기~");
		
		System.out.println("================= 댓글 페이징 시작 ===============");
		System.out.println("현재 페이지 번호 >" + currentPage);
		System.out.println("트럭 번호 > " + truckNum);
		totalRecordCount = replyTruckService.selectReplyCount(truckNum);
		System.out.println("해당 트럭의 총 게시글 수 : " + totalRecordCount);
		PagingUtil paging = new PagingUtil(currentPage, 5);	// 현재 페이지 번호와 페이지 당 보여줄 댓글 수 설정
		int start = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage() + 1;
		System.out.println("start > " + start);
		List<ReplyTruckVO> replyTruckList = replyTruckService.selectReplyTruck(truckNum, start);
		paging.setTotalRecordCount(totalRecordCount); // 전체 개수 셋팅
		paging.makePaging(); 	//페이징 생성
		System.out.println("가져온 개수 > " + replyTruckList.size());
		System.out.println("================= 댓글 페이징 끝 =================");
		
		mav.addObject("replyTruckList", replyTruckList);
		mav.addObject("paging", paging);
		mav.addObject("urlPath", "../reply/replyTruckList.do?");
		mav.addObject("truckNum", truckNum);
		mav.setViewName("replyTruck/reply_truck_reviewPro");
		
		// 분기로 isEmpty처리하기
		if(!replyTruckList.isEmpty()){
			
		}
		else{
			mav.setViewName("replyTruck/reply_truck_reviewPro");
		}
		
		return mav;
	}
	
	// 리뷰 등록 처리
	@RequestMapping(value="/reply/insertReview.do", method = RequestMethod.POST)
	@ResponseBody	// 메시지 컨버터를 통해 바로 HTTP응답의 메시지 본문으로 전환됨. 단일 모델 객체, 메시지 컨버터가 뷰와같은 동작
	public String truckDetailView(@RequestParam(value="replyContent", required=false) String replyContent,
									@RequestParam(value="score", required=false, defaultValue="0") String score,
									@RequestParam(value="truckNum", required=false) int truckNum,
									@RequestParam(value="memberId", required=false) String memberId)
									{
		String data = "";
		System.out.println("ReplyTruckController ajax이용 한줄평 등록~");
		//System.out.println(replyContent + " " + score + " " + truckNum + " " + memberId);
		int result = replyTruckService.insertReview(replyContent, score, truckNum, memberId);
		if(result == 1) data="1";
		else data="0";
		
		return data;
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////
}















