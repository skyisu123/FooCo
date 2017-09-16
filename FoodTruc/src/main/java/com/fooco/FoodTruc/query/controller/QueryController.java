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
		System.out.println("���Ǹ���Ʈ �̵�~");
		Object obj = session.getAttribute("memberInfo");
       	UserVO user = null;
        SellerVO seller = null; 
        if(obj instanceof UserVO){user = (UserVO)obj;}
        else{seller = (SellerVO)obj;}
        
        String delSuccess = (String)req.getAttribute("delSuccess") == null ? "����" : (String)req.getAttribute("delSuccess");
        
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
				System.out.println("�� ���ڵ� �� : " + totalRecordCount);
				PagingUtil paging = new PagingUtil(currentPage, 5);	// ���� ������ ��ȣ�� ������ �� ������ Ʈ�� �� ����
				//�ش� Ʈ���� �ε����� ���ϴ� ����(offset) 
			    int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage();
			    System.out.println(offset + "@@");
			    // �Ķ���ͷ� ���޹��� ��ġ�� �ش��ϴ� ����Ʈ�� �����´�.
			    List<ReplyQueryVO> list = sellerQueryService.showReplyQuery(id, offset);
			    
			    paging.setTotalRecordCount(totalRecordCount); // ��ü ���� ����
				paging.makePaging(); 	//����¡ ����
				
				if(totalRecordCount != 0){	// Ʈ���� 1���� ������ ���
					
					// ������ ����Ʈ(��ġ�� ��ȸ�� ����)�� �Ӽ��� �����Ͽ� �� �̵�
					model.addAttribute("list", list);
					model.addAttribute("paging", paging);
					model.addAttribute("urlPath", "queryList.do?id=" + id);
					model.addAttribute("reply", "reply");
					System.out.println("------------------- ����Ʈ ����¡ �� -----------------------");
				}
				else{
					System.out.println("����������Ʈ ����..");
					model.addAttribute("errorMsg", "����������Ʈ ��ȸ�� �����߽��ϴ�.");
				}
			}
			else if(listType.equals("send")){
				int totalRecordCount = sellerQueryService.getTotalCount(id);
				System.out.println("�� ���ڵ� �� : " + totalRecordCount);
				PagingUtil paging = new PagingUtil(currentPage, 5);	// ���� ������ ��ȣ�� ������ �� ������ Ʈ�� �� ����
				//�ش� Ʈ���� �ε����� ���ϴ� ����(offset) 
			    int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage();
			    System.out.println(offset + "@@");
			    // �Ķ���ͷ� ���޹��� ��ġ�� �ش��ϴ� ����Ʈ�� �����´�.
			    List<SellerQueryVO> list = sellerQueryService.showList(id, offset);	
			    
			    paging.setTotalRecordCount(totalRecordCount); // ��ü ���� ����
				paging.makePaging(); 	//����¡ ����
				
				if(totalRecordCount != 0){	// Ʈ���� 1���� ������ ���
					
					// ������ ����Ʈ(��ġ�� ��ȸ�� ����)�� �Ӽ��� �����Ͽ� �� �̵�
					model.addAttribute("list", list);
					model.addAttribute("paging", paging);
					model.addAttribute("urlPath", "queryList.do?listType=" + listType);
					System.out.println("------------------- ����Ʈ ����¡ �� -----------------------");
				}
				else{
					System.out.println("����������Ʈ ����..");
					model.addAttribute("errorMsg", "����������Ʈ ��ȸ�� �����߽��ϴ�.");
				}
							
			}
			else{
				int totalRecordCount = sellerQueryService.getReceviceTotalCount(id,read);
				System.out.println("�� ���ڵ� �� : " + totalRecordCount);
				PagingUtil paging = new PagingUtil(currentPage, 5);	// ���� ������ ��ȣ�� ������ �� ������ Ʈ�� �� ����
				//�ش� Ʈ���� �ε����� ���ϴ� ����(offset) 
			    int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage();
			    System.out.println(offset + "@@");
			    // �Ķ���ͷ� ���޹��� ��ġ�� �ش��ϴ� ����Ʈ�� �����´�.
			    List<UserQueryVO> list = sellerQueryService.showReceiveList(id,offset,read);
			    
			    paging.setTotalRecordCount(totalRecordCount); // ��ü ���� ����
				paging.makePaging(); 	//����¡ ����
				
				if(totalRecordCount != 0){	// Ʈ���� 1���� ������ ���
					
					// ������ ����Ʈ(��ġ�� ��ȸ�� ����)�� �Ӽ��� �����Ͽ� �� �̵�
					model.addAttribute("list", list);
					model.addAttribute("paging", paging);
					model.addAttribute("urlPath", "queryList.do?read=" + read+"&listType="+listType);
					System.out.println("------------------- ����Ʈ ����¡ �� -----------------------");
				}
				else{
					System.out.println("����������Ʈ ����..");
					model.addAttribute("errorMsg", "����������Ʈ ��ȸ�� �����߽��ϴ�.");
				}
			}
		}
		else
		{
			id = user.getmemberId();
			int totalRecordCount =userQueryService.getTotalCount(id,read);
			System.out.println("�� ���ڵ� �� : " + totalRecordCount);
			PagingUtil paging = new PagingUtil(currentPage, 5);	// ���� ������ ��ȣ�� ������ �� ������ Ʈ�� �� ����
			//�ش� Ʈ���� �ε����� ���ϴ� ����(offset) 
		    int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage();
		    System.out.println(offset + "@@");
		    // �Ķ���ͷ� ���޹��� ��ġ�� �ش��ϴ� ����Ʈ�� �����´�.
		    List<UserQueryVO> list = userQueryService.showList(id,offset,read);
		    
		    paging.setTotalRecordCount(totalRecordCount); // ��ü ���� ����
			paging.makePaging(); 	//����¡ ����
			
			if(totalRecordCount != 0){	// Ʈ���� 1���� ������ ���
				
				// ������ ����Ʈ(��ġ�� ��ȸ�� ����)�� �Ӽ��� �����Ͽ� �� �̵�
				model.addAttribute("list", list);
				model.addAttribute("paging", paging);
				model.addAttribute("urlPath", "queryList.do?read="+read);
				System.out.println("------------------- ����Ʈ ����¡ �� -----------------------");
			}
			else{
				System.out.println("����������Ʈ ����..");
				model.addAttribute("errorMsg", "����������Ʈ ��ȸ�� �����߽��ϴ�.");
			}
		}
        
		return "query/queryList";
	}
	
	@RequestMapping("/querySend.do")
	public String sellerQuery(HttpServletRequest req, Model model)
	{
		System.out.println("���Ǿ��� �̵�~");
		String id = req.getParameter("id");
		System.out.println("id = "+id);
		model.addAttribute("id", id);
		return "query/querySend";
	}
	
	@RequestMapping(value = "/sellerQuerySendAction.do")
	public String userQuerySendAction(HttpServletRequest req)
	{
		System.out.println("�Ǹ��ڹ��Ǿ��� �׼�~");

		sellerQueryService.querySend(req);			

		return "query/queryList";
	}
	
	@RequestMapping(value = "/userQuerySendAction.do")
	public String sellerQuerySendAction(HttpServletRequest req)
	{
		System.out.println("����ڹ��Ǿ��� �׼�~");

		userQueryService.querySend(req);			

		return "query/queryList";
	}
	
	//�Ǹ���(����ڰ�����) ���� �󼼺���
	@RequestMapping("/queryView.do")
	public String userQueryView(HttpSession session, HttpServletRequest req, Model model)
	{
		System.out.println("�Ǹ���(����ڰ�����) ���� �󼼺���~");
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
	
	//���ǻ���
	@RequestMapping("/queryDelete.do")
	public String queryDelete(HttpSession session, HttpServletRequest req, Model model)
	{
		System.out.println("�Ǹ���(����ڰ�����) ���� �󼼺���~");
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
				req.setAttribute("delSuccess", "����");
				return "forward:queryList.do";
			}
			else
			{
				sellerQueryService.deleteUserQuery(req);
				req.setAttribute("delSuccess", "����");
				return "forward:queryList.do";
			}
        }
		else
		{
			sellerQueryService.deleteUserQuery(req);
			req.setAttribute("delSuccess", "����");
			return "forward:queryList.do";
		}
	}
	
	//�Ǹ��ڰ� ����ڴ亯�ϱ�
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
	
	//Ajax Show Seller_Alram (�Ǹ��ڰ� �̿�� �Ǹ��ڿ��� ���ǳ� �������ϵɽ� �˶��ߴ±��) By SEO
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
