package com.fooco.FoodTruc.query.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.foodTruck.util.PagingUtil;
import com.fooco.FoodTruc.member.vo.SellerVO;
import com.fooco.FoodTruc.member.vo.UserVO;
import com.fooco.FoodTruc.query.service.SellerQueryService;
import com.fooco.FoodTruc.query.service.UserQueryService;
import com.fooco.FoodTruc.query.vo.ReplyQueryVO;
import com.fooco.FoodTruc.query.vo.SellerQueryVO;
import com.fooco.FoodTruc.query.vo.UserQueryVO;

@Controller
@RequestMapping("/query")
public class QueryController {
	
	@Autowired
	private UserQueryService userQueryService;
	
	@Autowired
	private SellerQueryService sellerQueryService;
	
	@RequestMapping("/queryList.do")
	public String queryList(HttpSession session, HttpServletRequest req, Model model
			, @RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage)
	{
		System.out.println("문의리스트 이동~");
		Object obj = session.getAttribute("memberInfo");
       	UserVO user = null;
        SellerVO seller = null; 
        if(obj instanceof UserVO){user = (UserVO)obj;}
        else{seller = (SellerVO)obj;}
        
        String delSuccess = (String)req.getAttribute("delSuccess") == null ? "실패" : (String)req.getAttribute("delSuccess");
        
        String id;
        String read = req.getParameter("read") == null ? "N" : req.getParameter("read");
        
        String listType = req.getParameter("listType") == null ? "send" : req.getParameter("listType");
        String sellerId = req.getParameter("id");
        
        model.addAttribute("listType", listType);
        model.addAttribute("read", read);
        model.addAttribute("req", req);
        model.addAttribute("delSuccess", delSuccess);
        
        if(seller != null)
        {
			id = seller.getSellerId();
			if(sellerId!=null)
			{
				int totalRecordCount = sellerQueryService.getReplyTotalCount(sellerId);
				System.out.println("총 레코드 수 : " + totalRecordCount);
				PagingUtil paging = new PagingUtil(currentPage, 5);	// 현재 페이지 번호와 페이지 당 보여줄 트럭 수 설정
				//해당 트럭의 인덱스를 구하는 변수(offset) 
			    int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage();
			    System.out.println(offset + "@@");
			    // 파라미터로 전달받은 위치에 해당하느 리스트들 가져온다.
			    List<ReplyQueryVO> list = sellerQueryService.showReplyQuery(id, offset);
			    
			    paging.setTotalRecordCount(totalRecordCount); // 전체 개수 셋팅
				paging.makePaging(); 	//페이징 생성
				
				if(totalRecordCount != 0){	// 트럭이 1개라도 존재할 경우
					
					// 가져온 리스트(위치별 조회의 의한)를 속성에 저장하여 뷰 이동
					model.addAttribute("list", list);
					model.addAttribute("paging", paging);
					model.addAttribute("urlPath", "queryList.do?id=" + id);
					model.addAttribute("reply", "reply");
					System.out.println("------------------- 리스트 페이징 끝 -----------------------");
				}
				else{
					System.out.println("종류별리스트 실패..");
					model.addAttribute("errorMsg", "종류별리스트 조회에 실패했습니다.");
				}
			}
			else if(listType.equals("send")){
				int totalRecordCount = sellerQueryService.getTotalCount(id);
				System.out.println("총 레코드 수 : " + totalRecordCount);
				PagingUtil paging = new PagingUtil(currentPage, 5);	// 현재 페이지 번호와 페이지 당 보여줄 트럭 수 설정
				//해당 트럭의 인덱스를 구하는 변수(offset) 
			    int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage();
			    System.out.println(offset + "@@");
			    // 파라미터로 전달받은 위치에 해당하느 리스트들 가져온다.
			    List<SellerQueryVO> list = sellerQueryService.showList(id, offset);	
			    
			    paging.setTotalRecordCount(totalRecordCount); // 전체 개수 셋팅
				paging.makePaging(); 	//페이징 생성
				
				if(totalRecordCount != 0){	// 트럭이 1개라도 존재할 경우
					
					// 가져온 리스트(위치별 조회의 의한)를 속성에 저장하여 뷰 이동
					model.addAttribute("list", list);
					model.addAttribute("paging", paging);
					model.addAttribute("urlPath", "queryList.do?listType=" + listType);
					System.out.println("------------------- 리스트 페이징 끝 -----------------------");
				}
				else{
					System.out.println("종류별리스트 실패..");
					model.addAttribute("errorMsg", "종류별리스트 조회에 실패했습니다.");
				}
							
			}
			else{
				int totalRecordCount = sellerQueryService.getReceviceTotalCount(id,read);
				System.out.println("총 레코드 수 : " + totalRecordCount);
				PagingUtil paging = new PagingUtil(currentPage, 5);	// 현재 페이지 번호와 페이지 당 보여줄 트럭 수 설정
				//해당 트럭의 인덱스를 구하는 변수(offset) 
			    int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage();
			    System.out.println(offset + "@@");
			    // 파라미터로 전달받은 위치에 해당하느 리스트들 가져온다.
			    List<UserQueryVO> list = sellerQueryService.showReceiveList(id,offset,read);
			    
			    paging.setTotalRecordCount(totalRecordCount); // 전체 개수 셋팅
				paging.makePaging(); 	//페이징 생성
				
				if(totalRecordCount != 0){	// 트럭이 1개라도 존재할 경우
					
					// 가져온 리스트(위치별 조회의 의한)를 속성에 저장하여 뷰 이동
					model.addAttribute("list", list);
					model.addAttribute("paging", paging);
					model.addAttribute("urlPath", "queryList.do?read=" + read+"&listType="+listType);
					System.out.println("------------------- 리스트 페이징 끝 -----------------------");
				}
				else{
					System.out.println("종류별리스트 실패..");
					model.addAttribute("errorMsg", "종류별리스트 조회에 실패했습니다.");
				}
			}
		}
		else
		{
			id = user.getmemberId();
			int totalRecordCount =userQueryService.getTotalCount(id,read);
			System.out.println("총 레코드 수 : " + totalRecordCount);
			PagingUtil paging = new PagingUtil(currentPage, 5);	// 현재 페이지 번호와 페이지 당 보여줄 트럭 수 설정
			//해당 트럭의 인덱스를 구하는 변수(offset) 
		    int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage();
		    System.out.println(offset + "@@");
		    // 파라미터로 전달받은 위치에 해당하느 리스트들 가져온다.
		    List<UserQueryVO> list = userQueryService.showList(id,offset,read);
		    
		    paging.setTotalRecordCount(totalRecordCount); // 전체 개수 셋팅
			paging.makePaging(); 	//페이징 생성
			
			if(totalRecordCount != 0){	// 트럭이 1개라도 존재할 경우
				
				// 가져온 리스트(위치별 조회의 의한)를 속성에 저장하여 뷰 이동
				model.addAttribute("list", list);
				model.addAttribute("paging", paging);
				model.addAttribute("urlPath", "queryList.do?read="+read);
				System.out.println("------------------- 리스트 페이징 끝 -----------------------");
			}
			else{
				System.out.println("종류별리스트 실패..");
				model.addAttribute("errorMsg", "종류별리스트 조회에 실패했습니다.");
			}
		}
        
		return "query/queryList";
	}
	
	@RequestMapping("/querySend.do")
	public String sellerQuery(HttpServletRequest req, Model model)
	{
		System.out.println("문의쓰기 이동~");
		String id = req.getParameter("id");
		System.out.println("id = "+id);
		model.addAttribute("id", id);
		return "query/querySend";
	}
	
	@RequestMapping(value = "/sellerQuerySendAction.do")
	public String userQuerySendAction(HttpServletRequest req)
	{
		System.out.println("판매자문의쓰기 액션~");

		sellerQueryService.querySend(req);			

		return "query/queryList";
	}
	
	@RequestMapping(value = "/userQuerySendAction.do")
	public String sellerQuerySendAction(HttpServletRequest req)
	{
		System.out.println("사용자문의쓰기 액션~");

		userQueryService.querySend(req);			

		return "query/queryList";
	}
	
	//판매자(사용자가보낸) 문의 상세보기
	@RequestMapping("/queryView.do")
	public String userQueryView(HttpSession session, HttpServletRequest req, Model model)
	{
		System.out.println("판매자(사용자가보낸) 문의 상세보기~");
		System.out.println(req.getParameter("queryNum"));
		
		Object obj = session.getAttribute("memberInfo");
       	UserVO user = null;
        SellerVO seller = null; 
        if(obj instanceof UserVO){user = (UserVO)obj;}
        else{seller = (SellerVO)obj;}
		
        String listType = req.getParameter("listType");
		if(seller!=null)
        {
			if(listType.equals("send"))
			{
				SellerQueryVO list = sellerQueryService.showView(req);
				model.addAttribute("list", list);
				model.addAttribute("listType", listType);
				return "query/queryView";
			}
			else
			{
				UserQueryVO list = sellerQueryService.showReceiveView(req);
				model.addAttribute("list", list);
				model.addAttribute("listType", listType);
				return "query/queryView";
			}
        }
		else
		{
			UserQueryVO list = sellerQueryService.showReceiveView(req);
			model.addAttribute("list", list);
			model.addAttribute("listType", listType);
			return "query/queryView";
		}
	}
	
	//문의삭제
	@RequestMapping("/queryDelete.do")
	public String queryDelete(HttpSession session, HttpServletRequest req, Model model)
	{
		System.out.println("판매자(사용자가보낸) 문의 상세보기~");
		System.out.println(req.getParameter("queryNum"));
		
		Object obj = session.getAttribute("memberInfo");
       	UserVO user = null;
        SellerVO seller = null; 
        if(obj instanceof UserVO){user = (UserVO)obj;}
        else{seller = (SellerVO)obj;}
		
		if(seller!=null)
        {
			String listType = req.getParameter("listType");
			if(listType.equals("send"))
			{
				sellerQueryService.deleteSellerQuery(req);
				req.setAttribute("delSuccess", "성공");
				return "forward:queryList.do";
			}
			else
			{
				sellerQueryService.deleteUserQuery(req);
				req.setAttribute("delSuccess", "성공");
				return "forward:queryList.do";
			}
        }
		else
		{
			sellerQueryService.deleteUserQuery(req);
			req.setAttribute("delSuccess", "성공");
			return "forward:queryList.do";
		}
	}
	
	//판매자가 사용자답변하기
	@RequestMapping("/answerQuery.do")
	public String answerQuery(HttpServletRequest req, Model model)
	{
		UserQueryVO list = sellerQueryService.showReceiveView(req);
		model.addAttribute("list", list);
		return "query/answerQuery";
	}
	
	@RequestMapping("/answerQueryAction.do")
	public String answerQueryAction(HttpServletRequest req, Model model)
	{
		String queryNum = req.getParameter("queryNum");
		String queryTitle = req.getParameter("queryTitle");
		String queryContent = req.getParameter("queryContent");
		model.addAttribute("queryNum", queryNum);
		model.addAttribute("queryTitle", queryTitle);
		model.addAttribute("queryContent", queryContent);
		
		sellerQueryService.answerAction(model);
		return "query/queryList";
	}
	
	@RequestMapping("/editQuery.do")
	public String editQuery(HttpSession session, HttpServletRequest req, Model model)
	{
		System.out.println(req.getParameter("queryNum"));
		
		Object obj = session.getAttribute("memberInfo");
       	UserVO user = null;
        SellerVO seller = null; 
        if(obj instanceof UserVO){user = (UserVO)obj;}
        else{seller = (SellerVO)obj;}
        
        String listType = req.getParameter("listType");
		if(seller!=null)
        {
			SellerQueryVO list = sellerQueryService.showView(req);
			model.addAttribute("list", list);
			model.addAttribute("listType", listType);
			return "query/editQuery";

        }
		else
		{
			UserQueryVO list = sellerQueryService.showReceiveView(req);
			model.addAttribute("list", list);
			model.addAttribute("listType", listType);
			return "query/editQuery";
		}
	}
	
	
	@RequestMapping("/editQueryAction.do")
	public String editQueryAction(HttpSession session, HttpServletRequest req, Model model)
	{
		Object obj = session.getAttribute("memberInfo");
       	UserVO user = null;
        SellerVO seller = null; 
        if(obj instanceof UserVO){user = (UserVO)obj;}
        else{seller = (SellerVO)obj;}
        
		String queryNum = req.getParameter("queryNum");
		String queryTitle = req.getParameter("queryTitle");
		String queryContent = req.getParameter("queryContent");
		model.addAttribute("queryNum", queryNum);
		model.addAttribute("queryTitle", queryTitle);
		model.addAttribute("queryContent", queryContent);
		
		if(seller!=null)
		{
			sellerQueryService.editSellerAction(model);
		}
		else
		{
			sellerQueryService.editUserAction(model);			
		}
		return "query/queryList";
	}
	
	@RequestMapping("/replyView.do")
	public String replyView(HttpServletRequest req, Model model)
	{
		String queryCode = req.getParameter("queryCode");
		ReplyQueryVO list = sellerQueryService.replyList(queryCode);
		model.addAttribute("list", list);
		return "query/replyView";
	}
	
	//Ajax Show Seller_Alram (판매자가 이용시 판매자에게 문의나 한줄평등록될시 알람뜨는기능) By SEO
	@RequestMapping("/queryAlram.do")
	public ModelAndView sellerAlram(HttpSession session,
			@RequestParam(value="selectQuery", required=false, defaultValue="Y") String selectQuery) 
	{
		ModelAndView mav = new ModelAndView();
		Object obj = session.getAttribute("memberInfo");
       	UserVO user = null;
        SellerVO seller = null; 
        String sellerId = "";
        if(obj instanceof UserVO){user = (UserVO)obj; sellerId = user.getmemberId();}
        else{seller = (SellerVO)obj; sellerId = seller.getSellerId();}
		int result = userQueryService.getQueryCount(sellerId,selectQuery);
		
		if(result > 0){	result = 1;	}
		else{ result = 0; }
		
		mav.addObject("checkBoolean", result);
		mav.addObject("seller", "seller");
		mav.setViewName("truck/ajax/sellerAlram");
		
		return mav;
	}
	
}
