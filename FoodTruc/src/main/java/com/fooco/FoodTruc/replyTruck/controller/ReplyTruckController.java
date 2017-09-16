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
				@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage,	//����������
				@RequestParam(value="truckNum") int truckNum	// ��ȸ�� Ʈ���� ���� ������ ����Ʈ�� truck_num
		)
	{
		int totalRecordCount = -1;
		ModelAndView mav = new ModelAndView();
		System.out.println("ReplyTruckController ajax�̿� ������ ����Ʈ ��������~");
		
		System.out.println("================= ��� ����¡ ���� ===============");
		System.out.println("���� ������ ��ȣ >" + currentPage);
		System.out.println("Ʈ�� ��ȣ > " + truckNum);
		totalRecordCount = replyTruckService.selectReplyCount(truckNum);
		System.out.println("�ش� Ʈ���� �� �Խñ� �� : " + totalRecordCount);
		PagingUtil paging = new PagingUtil(currentPage, 5);	// ���� ������ ��ȣ�� ������ �� ������ ��� �� ����
		int start = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage() + 1;
		System.out.println("start > " + start);
		List<ReplyTruckVO> replyTruckList = replyTruckService.selectReplyTruck(truckNum, start);
		paging.setTotalRecordCount(totalRecordCount); // ��ü ���� ����
		paging.makePaging(); 	//����¡ ����
		System.out.println("������ ���� > " + replyTruckList.size());
		System.out.println("================= ��� ����¡ �� =================");
		
		mav.addObject("replyTruckList", replyTruckList);
		mav.addObject("paging", paging);
		mav.addObject("urlPath", "../reply/replyTruckList.do?");
		mav.addObject("truckNum", truckNum);
		mav.setViewName("replyTruck/reply_truck_reviewPro");
		
		// �б�� isEmptyó���ϱ�
		if(!replyTruckList.isEmpty()){
			
		}
		else{
			mav.setViewName("replyTruck/reply_truck_reviewPro");
		}
		
		return mav;
	}
	
	// ���� ��� ó��
	@RequestMapping(value="/reply/insertReview.do", method = RequestMethod.POST)
	@ResponseBody	// �޽��� �����͸� ���� �ٷ� HTTP������ �޽��� �������� ��ȯ��. ���� �� ��ü, �޽��� �����Ͱ� ��Ͱ��� ����
	public String truckDetailView(@RequestParam(value="replyContent", required=false) String replyContent,
									@RequestParam(value="score", required=false, defaultValue="0") String score,
									@RequestParam(value="truckNum", required=false) int truckNum,
									@RequestParam(value="memberId", required=false) String memberId)
									{
		String data = "";
		System.out.println("ReplyTruckController ajax�̿� ������ ���~");
		//System.out.println(replyContent + " " + score + " " + truckNum + " " + memberId);
		int result = replyTruckService.insertReview(replyContent, score, truckNum, memberId);
		if(result == 1) data="1";
		else data="0";
		
		return data;
	}
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////
}















