package com.fooco.FoodTruc.foodTruck.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.foodTruck.service.impl.FoodTruckServiceImpl;
import com.fooco.FoodTruc.foodTruck.util.GeoCorder;
import com.fooco.FoodTruc.foodTruck.util.PagingUtil;
import com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO;
import com.fooco.FoodTruc.likes.service.impl.LikesServiceImpl;
import com.fooco.FoodTruc.member.service.impl.SellerServiceImpl;
import com.fooco.FoodTruc.member.vo.SellerVO;
import com.fooco.FoodTruc.member.vo.UserVO;
import com.fooco.FoodTruc.menu.service.impl.MenuServiceImpl;
import com.fooco.FoodTruc.menu.vo.MenuVO;
import com.fooco.FoodTruc.menuImage.service.impl.MenuImageServiceImpl;
import com.fooco.FoodTruc.menuImage.vo.MenuImageVO;
import com.fooco.FoodTruc.replyTruck.service.impl.ReplyTruckServiceImpl;
import com.fooco.FoodTruc.replyTruck.vo.ReplyTruckVO;

@Controller
public class TruckController {

	@Autowired
	private FoodTruckServiceImpl foodTruckService;
	@Autowired
	private MenuServiceImpl menuService;
	@Autowired
	private MenuImageServiceImpl menuImageService;
	@Autowired
	private SellerServiceImpl sellerService;
	@Autowired
	private ReplyTruckServiceImpl replyTruckService;
	@Autowired
	private LikesServiceImpl likesService;
	/*****************************      S   E   O    s   t   a   r   t     *************************/
	
	// Ʈ������Ʈ �������� �̵� By SEO
	@RequestMapping("/truck/trucFoodTrucList.do")
	public ModelAndView trucFoodList(Model model) {
		System.out.println("TruckController Ʈ������Ʈ�� �̵� ~");
		
		ModelAndView mav = new ModelAndView();
		int start = 1;
		int end = 4;
		String setType = "FoodTruckList";
		String truckType = "�н�";
		String location = "����,������";
		
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
		Date currentTime = new Date ();
		String mTime = mSimpleDateFormat.format ( currentTime );
		String setOpenDate = mTime;
		
		List<FoodTruckVO> rankLists = foodTruckService.truckRankList(start, setType);
		List<FoodTruckVO> allLists = foodTruckService.list( start, end );
		List<FoodTruckVO> openDateLists = foodTruckService.selectOpenDateList( setOpenDate, start, end );
		List<FoodTruckVO> foodAssortativeList = foodTruckService.foodAssortativeList(truckType, start, end);
		List<FoodTruckVO> locationFoodList = foodTruckService.selectLocationList(location, start, end);
		
		mav.addObject("allLists", allLists);
		mav.addObject("rankLists", rankLists);
		mav.addObject("openDateLists", openDateLists);
		mav.addObject("foodAssortativeList", foodAssortativeList);
		mav.addObject("locationFoodList", locationFoodList);
		mav.setViewName("truck/FoodTrucList");
		
		return mav;
	}
	
	/*****************************      S   E   O      e   n    d     *************************/
	
	/////////////   S   E   O    s t a r t  ///////////////////////////////
	//allTruckList Page.
	@RequestMapping( "/truck/allTruckList.do" )
	public ModelAndView allTruckList(HttpServletRequest req) {
		System.out.println( "allTruckList join" );
		
		int nowPage = req.getParameter( "page" )==null ? 1 : Integer.parseInt(req.getParameter( "page" ));
		
		int start = ( nowPage-1 ) * 8 + 1 ;
		int end = nowPage * 8;
		
		List<FoodTruckVO> foodTruckVo = foodTruckService.list( start, end );
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName( "truck/allTruckList" );
		mav.addObject( "list", foodTruckVo );
				
		return mav;
	}
	
	//OpenDate Service FoodTruck List By SEO
	@RequestMapping( "/truck/truckOpenDateList.do" )
	public ModelAndView truckOpenDateList(
		@RequestParam( value="firstJoin", required=false, defaultValue="0" ) int firstJoin,
		@RequestParam( value="openDate", required=false ) String openDate,
		@RequestParam( value="currentPage", required=false, defaultValue="1" ) int currentPage,
		@RequestParam( value="setOpenDate", required=false, defaultValue="" ) String setOpenDate ) {
		
		ModelAndView mav = new ModelAndView();
		
		//first join = openDate button click 
		if( firstJoin == 1 ){
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat ( "yyyy-MM-dd", Locale.KOREA );
			Date currentTime = new Date ();
			String mTime = mSimpleDateFormat.format ( currentTime );
			setOpenDate = mTime;
		}
						
		else if(!(openDate.equals( setOpenDate ))){
			setOpenDate = openDate;
		}
		
		
		//get totalRecordCount	
		int totalRecordCount = foodTruckService.selectDateTotalCount( setOpenDate );
		
		//create instance paging 
		PagingUtil paging = new PagingUtil( currentPage, 16 );	
		 
		int start = ( paging.getCurrentPage() - 1 ) * paging.getRecordsPerPage() + 1;
		List<FoodTruckVO> openDateFoodList = foodTruckService.selectOpenDateList( setOpenDate, start );
		paging.setTotalRecordCount( totalRecordCount ); 
		paging.makePaging();
			
		if( !openDateFoodList.isEmpty() ){	
						
			mav.addObject( "openDateFoodList", openDateFoodList );
			mav.addObject( "paging", paging );
			mav.addObject( "urlPath", "truckOpenDateList.do?openDate=" + setOpenDate );
			mav.addObject("setOpenDate", setOpenDate );
			mav.setViewName( "truck/truckOpenDateList" );
					
		}
		else{
			
			mav.addObject( "errorMsg", "Your Choice Date Noting service FoodTruck ..." );
			mav.setViewName( "truck/truckOpenDateList" );
		}
						
		return mav;
	}
	
	//Ajax More Button Add List By SEO
	@RequestMapping( "/truck/allTruckListAdd.do" )
	public ModelAndView allTruckListAdd( HttpServletRequest req ) {
		int nowPage = req.getParameter( "page" )==null ? 1 : Integer.parseInt(req.getParameter( "page" ));
		
		int start = ( nowPage-1 ) * 8 + 1 ;
		int end = nowPage * 8;
		
		List<FoodTruckVO> foodTruckList = foodTruckService.list( start, end );
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName( "truck/ajax/allTruckListAdd" );
		mav.addObject( "list", foodTruckList );
		
		return mav;
	}
		
	
	//search function By SEO
	@RequestMapping(value="/truck/truckSearchList.do", method=RequestMethod.POST)
	public ModelAndView turckSearchFunction(
			@RequestParam(value="searchColumn", required=false, defaultValue="truck_name") String searchColumn,
            @RequestParam(value="searchText", required=false) String searchText,
            @RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
		
		if(searchColumn.equalsIgnoreCase("truckMenu")){
			searchColumn = "truck_menu";
		}
		else if(searchColumn.equalsIgnoreCase("truckName")){
			searchColumn = "truck_name";
		}
		ModelAndView mav = new ModelAndView();
	
		//get totalRecordCount	
		int totalRecordCount = foodTruckService.searchTotalCount( searchColumn, searchText );
		
		//create instance paging 
		PagingUtil paging = new PagingUtil( currentPage, 16 );	
		System.out.println( "currentPage = " + currentPage );
		int start = ( paging.getCurrentPage() - 1 ) * paging.getRecordsPerPage() + 1;
		
		paging.setTotalRecordCount( totalRecordCount ); 
		paging.makePaging();
		
			
		List<FoodTruckVO> searchLists = foodTruckService.searchLists( searchColumn, searchText, start );
		
		mav.addObject( "searchLists", searchLists );
		mav.addObject( "paging", paging );
		mav.setViewName("truck/truckSearchList");
		
		return mav;
	}
	
	
	//==================================================================================	
	// ��ŷ�� Ʈ�� ����Ʈ ���� By ���� With ���� With ����
	@RequestMapping("/truck/truckRankList.do")
	public ModelAndView truckRankList(
			@RequestParam(value="setType", required=false) String setType,
			@RequestParam(value="getType", required=false, defaultValue="RankList") String getType,
			@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
		
		ModelAndView mav = new ModelAndView();
		
		//get totalRecordCount	
		int totalRecordCount = foodTruckService.rankingTotalCount();
		
		//create instance paging 
		PagingUtil paging = new PagingUtil( currentPage, 16 );	
		
		int start = ( paging.getCurrentPage() - 1 ) * paging.getRecordsPerPage() + 1;
		
		paging.setTotalRecordCount( totalRecordCount ); 
		paging.makePaging();
		
		if(setType==null) setType = getType;
		
		if(!(setType.equals(getType))) setType = getType;
		
		List<FoodTruckVO> rankLists = foodTruckService.truckRankList(start, setType);
		
	
		if( !rankLists.isEmpty() ){	
						
			mav.addObject( "rankLists", rankLists );
			mav.addObject( "paging", paging );
			mav.addObject( "urlPath", "truckRankList.do?getType=" + setType );
			mav.addObject( "setType", setType );
			mav.setViewName( "truck/truckRankList" );
					
		}
		else{
			
			mav.addObject( "errorMsg", "Your Choice Date Noting service FoodTruck ..." );
			mav.setViewName( "truck/truckRankList" );
		}
			
		return mav;
	}


	//==================================================================================

	
	
	
	/////////////   S   E   O    e  n  d  ///////////////////////////////

	// ��ġ�� Ʈ�� ����Ʈ ����
	// ó�� ����, �������� ���õǾ� �� ����
	// �Ķ���� location�� �� ������ xx,xxx���� �ð���.
	// currentPage�� �Ķ���ͷ� �޾ƿ�. jsp���� �Ķ���� �ѱ涧 �Ķ���� �̸��� ���°��
	// ����Ʈ�� 1���� �Ҵ�. ==> ���� �������� 1�������� ��.
	@RequestMapping("/truck/truckLocationList.do")
	public ModelAndView truckLocationList(@RequestParam(value="location", required=false, defaultValue="����,������") String location,
							@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
		
		ModelAndView mav = new ModelAndView();
		
		// ���ڵ� �� ���� ���ϱ�
		int totalRecordCount = foodTruckService.selectLocationTotalCount(location);
		PagingUtil paging = new PagingUtil(currentPage, 16);	// ���� ������ ��ȣ�� ������ �� ������ Ʈ�� �� ����
		//�ش� Ʈ���� �ε����� ���ϴ� ����(offset) 
        int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage() + 1;
        // �Ķ���ͷ� ���޹��� ��ġ�� �ش��ϴ� ����Ʈ�� �����´�.
     	List<FoodTruckVO> locationFoodList = foodTruckService.selectLocationList(location, offset);
		paging.setTotalRecordCount(totalRecordCount); // ��ü ���� ����
		paging.makePaging(); 	//����¡ ����
		
		if(locationFoodList != null){	// Ʈ���� 1���� ������ ���
			
			// ������ ����Ʈ(��ġ�� ��ȸ�� ����)�� �Ӽ��� �����Ͽ� �� �̵�
			mav.addObject("locationFoodList", locationFoodList);
			mav.addObject("paging", paging);
			mav.addObject("urlPath", "truckLocationList.do?location=" + location);
			mav.setViewName("truck/truckLocationList");
		}
		else{
			mav.addObject("errorMsg", "��ġ�� Ʈ�� ����Ʈ ��ȸ�� �����߽��ϴ�.");
			mav.setViewName("truck/allTruckList");
		}
		
		return mav;
	}

	// Ǫ�������� Ʈ�� ����Ʈ ����
	@RequestMapping(value="/truck/truckFoodKindList.do")
	public String truckFoodKindList(HttpServletRequest req, Model model,
			@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
		String truckType = req.getParameter("truckType")== null ? "�н�" : req.getParameter("truckType") ;
		
		// ���ڵ� �� ���� ���ϱ�
		int totalRecordCount = foodTruckService.getTotalCount(truckType);
		PagingUtil paging = new PagingUtil(currentPage, 16);	// ���� ������ ��ȣ�� ������ �� ������ Ʈ�� �� ����
		//�ش� Ʈ���� �ε����� ���ϴ� ����(offset) 
	    int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage();
	    // �Ķ���ͷ� ���޹��� ��ġ�� �ش��ϴ� ����Ʈ�� �����´�.
	    List<FoodTruckVO> list = foodTruckService.typeSelect(truckType,offset);
	    
	    paging.setTotalRecordCount(totalRecordCount); // ��ü ���� ����
		paging.makePaging(); 	//����¡ ����
		
		if(totalRecordCount != 0){	// Ʈ���� 1���� ������ ���
			
			// ������ ����Ʈ(��ġ�� ��ȸ�� ����)�� �Ӽ��� �����Ͽ� �� �̵�
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("urlPath", "truckFoodKindList.do?truckType=" + truckType);
		}
		else{
			System.out.println("����������Ʈ ����..");
			model.addAttribute("errorMsg", "����������Ʈ ��ȸ�� �����߽��ϴ�.");
		}
		
		return "truck/truckFoodKindList";
	}

	///////////////////////////////////////////////////////////////////////

	// �ش� Ʈ�� �󼼺���
		// �� ���� ���� ��ü �ٸ� ����
		// �� ��ɺ� ������ �°� ���ȭ�� �޼ҵ� ȣ����.
		@RequestMapping("/truck/truckDetailView.do")
		public ModelAndView truckDetailView(@RequestParam(value="truckNum", required=false, defaultValue="0") int truckNum, 
				@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage, HttpSession session) {
			System.out.println("TruckController Ʈ�� �ڼ�������� �̵�~");
			ModelAndView mav = new ModelAndView();
			
			// �󼼺��� Ʈ�� ��ȸ�� ������Ʈ
			foodTruckService.updateDetailTruck(truckNum);
			
			// 0. ���� ������(����)�� �ش� Ʈ�� ��ȣ�� �����ߴ��� ���� �Ǵ�
			Object obj = session.getAttribute("memberInfo");
			UserVO user = null;
	        SellerVO seller = null;
	        String memberId = "";
	        if(obj instanceof UserVO){user = (UserVO)obj; memberId = user.getmemberId();}
	        else if(obj instanceof SellerVO){seller = (SellerVO)obj; memberId = seller.getSellerId();}
	        else memberId = "";
			int isLikeExist = likesService.selectIsLikeExist(memberId, truckNum);	// 0�̸� Like���� ����. 1�̸� Like����
			mav.addObject("isLikeExist", isLikeExist);
			
			// 1. FoodTruck���̺� ���� �޾ƿ���
	         FoodTruckVO foodTruckVo = foodTruckService.selectDetailTruck(truckNum);
	         /*System.out.println("location : " + foodTruckVo.getTruckLocation());*/
	         
	         if(foodTruckVo != null) {mav.addObject("foodTruckVo", foodTruckVo);}
	         else{mav.addObject("foodTruckVo", null);}

	         GeoCorder geoCorder = new GeoCorder();
	         Float[] coords = geoCorder.geoCoding(foodTruckVo.getTruckLocation());
	         mav.addObject("lat", coords[0]);
	         mav.addObject("lng", coords[1]);
			
			// 2. Menu���̺� ���� �޾ƿ���
			int menuCode = -1;	// �޴��ڵ� �ʱ�ȭ
			List<MenuVO> menuList = menuService.selectDetailMenu(foodTruckVo.getTruckNum());
			if(!menuList.isEmpty()) {
				mav.addObject("menuList", menuList);
				String mainMenu = "";
				for(int i = 0; i < menuList.size(); i++){
					// �� �޴� �̾ƿ���
					if(menuList.get(i).getInventory() == 1) { 
						mainMenu = menuList.get(i).getMenuName();
						mav.addObject("mainMenu", mainMenu);
					}
				}
				int temp = 0;
				for(int i = 0; i < menuList.size(); i++){
					// �޴��ڵ� �ִ밪 ���ϱ�
					temp = menuList.get(i).getMenuCode();
					if(menuCode < temp) menuCode = temp;
					temp = 0;
				}
			}
			else{mav.addObject("menuList", null);}
			
			// 3. menu_image ���̺� ��ȸ
			List<MenuImageVO> menuImageList = menuImageService.selectDetailMenuImage(menuCode);
			mav.addObject("menuImageList", menuImageList);
			
			// 4. seller_member ���̺� ��ȸ
			List<SellerVO> sellerList = sellerService.selectDetailMember(foodTruckVo.getSellerId());
			if(!sellerList.isEmpty()){
				mav.addObject("sellerVo", sellerList.get(0));
			}
			else{mav.addObject("sellerVo", null);}
			
			// 5. reply_truck ���̺� ��ȸ (ajax�� �����ϸ� & detail������ ������ �����ϸ� ���ָ��.)
			List<ReplyTruckVO> replyList = replyTruckService.selectDetailReplyTruck(truckNum);
			mav.addObject("replyList", replyList);

			mav.setViewName("truck/truckDetailView");
			return mav;
		}
		
		///////////////////////////////////////////////////////////////////////
	//�̿��� , �Ǹ��� ȸ������ �������� �̵�
	@RequestMapping(value = "/user/MemberJoin.do", method = RequestMethod.GET)
	public String goJoin() {return "user/MemberJoin";}
	
	//�α��� ó�� �̵�
	@RequestMapping(value="/truck/user/userLogin.do", method=RequestMethod.POST)
	public String userLogin(HttpServletRequest req){
		return "forward:../user/userLogin.do";
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////// like + /////////////////////////////
	@RequestMapping(value="/truck/updateTruckLike.do")
	@ResponseBody
	public String updateTruckLike(@RequestParam(value="truckNum") int truckNum, HttpSession session){
		int result = foodTruckService.updateTruckLike(truckNum);
		Object obj = session.getAttribute("memberInfo");
		UserVO user = null;
		SellerVO seller = null;
		String memberId = "";
		if(obj instanceof UserVO){user = (UserVO)obj; memberId = user.getmemberId();}
		else{seller = (SellerVO)obj; memberId = seller.getSellerId();}
		int insertLikes = likesService.insertLikes(memberId, truckNum);
		return String.valueOf(result);
	}
	
	// like -
	@RequestMapping(value="/truck/deleteTruckLike.do")
	@ResponseBody
	public String deleteTruckLike(@RequestParam(value="truckNum") int truckNum, HttpSession session){
		int result = foodTruckService.minusLike(truckNum);
		Object obj = session.getAttribute("memberInfo");
		UserVO user = null;
        SellerVO seller = null;
        String memberId = "";
        if(obj instanceof UserVO){user = (UserVO)obj; memberId = user.getmemberId();}
        else{seller = (SellerVO)obj; memberId = seller.getSellerId();}
		likesService.deleteLikes(memberId, truckNum);
		return String.valueOf(result);
	}
	
	//////////////////////// Ʈ�� 1�ܰ� ����ϴٰ� �޴� ����Ҷ� �ۼ���� ������ //////////////////
	// 1�ܰ� ���� ���� (foodtruck ���̺� ����Է���� ����)
	@RequestMapping(value="/truck/truckCancelMenuAdd.do")
	public String deleteTruck(@RequestParam(value="truckNum") int truckNum){
		foodTruckService.deleteTruckByCancel(truckNum);
		return "/user/memberMyPage";
	}
	
	//////////////////////////////////////////////////////////////////////////////
}

















