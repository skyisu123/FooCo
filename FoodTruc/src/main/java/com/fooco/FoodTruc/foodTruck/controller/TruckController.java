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
	
	// 트럭리스트 페이지로 이동 By SEO
	@RequestMapping("/truck/trucFoodTrucList.do")
	public ModelAndView trucFoodList(Model model) {
		System.out.println("TruckController 트럭리스트로 이동 ~");
		
		ModelAndView mav = new ModelAndView();
		int start = 1;
		int end = 4;
		String setType = "FoodTruckList";
		String truckType = "분식";
		String location = "서울,강남구";
		
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
	// 랭킹별 트럭 리스트 보기 By 형무 With 동섭 With 영민
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

	// 위치별 트럭 리스트 보기
	// 처음 서울, 강남구로 셋팅되어 뷰 셋팅
	// 파라미터 location은 각 지역별 xx,xxx으로 올것임.
	// currentPage를 파라미터로 받아옴. jsp에서 파라미터 넘길때 파라미터 이름이 없는경우
	// 디폴트로 1값을 할당. ==> 현재 페이지는 1페이지가 됨.
	@RequestMapping("/truck/truckLocationList.do")
	public ModelAndView truckLocationList(@RequestParam(value="location", required=false, defaultValue="서울,강남구") String location,
							@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
		
		ModelAndView mav = new ModelAndView();
		
		// 레코드 총 개수 구하기
		int totalRecordCount = foodTruckService.selectLocationTotalCount(location);
		PagingUtil paging = new PagingUtil(currentPage, 16);	// 현재 페이지 번호와 페이지 당 보여줄 트럭 수 설정
		//해당 트럭의 인덱스를 구하는 변수(offset) 
        int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage() + 1;
        // 파라미터로 전달받은 위치에 해당하느 리스트들 가져온다.
     	List<FoodTruckVO> locationFoodList = foodTruckService.selectLocationList(location, offset);
		paging.setTotalRecordCount(totalRecordCount); // 전체 개수 셋팅
		paging.makePaging(); 	//페이징 생성
		
		if(locationFoodList != null){	// 트럭이 1개라도 존재할 경우
			
			// 가져온 리스트(위치별 조회의 의한)를 속성에 저장하여 뷰 이동
			mav.addObject("locationFoodList", locationFoodList);
			mav.addObject("paging", paging);
			mav.addObject("urlPath", "truckLocationList.do?location=" + location);
			mav.setViewName("truck/truckLocationList");
		}
		else{
			mav.addObject("errorMsg", "위치별 트럭 리스트 조회에 실패했습니다.");
			mav.setViewName("truck/allTruckList");
		}
		
		return mav;
	}

	// 푸드종류별 트럭 리스트 보기
	@RequestMapping(value="/truck/truckFoodKindList.do")
	public String truckFoodKindList(HttpServletRequest req, Model model,
			@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage) {
		String truckType = req.getParameter("truckType")== null ? "분식" : req.getParameter("truckType") ;
		
		// 레코드 총 개수 구하기
		int totalRecordCount = foodTruckService.getTotalCount(truckType);
		PagingUtil paging = new PagingUtil(currentPage, 16);	// 현재 페이지 번호와 페이지 당 보여줄 트럭 수 설정
		//해당 트럭의 인덱스를 구하는 변수(offset) 
	    int offset = (paging.getCurrentPage() - 1) * paging.getRecordsPerPage();
	    // 파라미터로 전달받은 위치에 해당하느 리스트들 가져온다.
	    List<FoodTruckVO> list = foodTruckService.typeSelect(truckType,offset);
	    
	    paging.setTotalRecordCount(totalRecordCount); // 전체 개수 셋팅
		paging.makePaging(); 	//페이징 생성
		
		if(totalRecordCount != 0){	// 트럭이 1개라도 존재할 경우
			
			// 가져온 리스트(위치별 조회의 의한)를 속성에 저장하여 뷰 이동
			model.addAttribute("list", list);
			model.addAttribute("paging", paging);
			model.addAttribute("urlPath", "truckFoodKindList.do?truckType=" + truckType);
		}
		else{
			System.out.println("종류별리스트 실패..");
			model.addAttribute("errorMsg", "종류별리스트 조회에 실패했습니다.");
		}
		
		return "truck/truckFoodKindList";
	}

	///////////////////////////////////////////////////////////////////////

	// 해당 트럭 상세보기
		// ★ 각각 서비스 객체 다름 주의
		// 각 기능별 계층에 맞게 모듈화로 메소드 호출함.
		@RequestMapping("/truck/truckDetailView.do")
		public ModelAndView truckDetailView(@RequestParam(value="truckNum", required=false, defaultValue="0") int truckNum, 
				@RequestParam(value="currentPage", required=false, defaultValue="1") int currentPage, HttpSession session) {
			System.out.println("TruckController 트럭 자세히보기로 이동~");
			ModelAndView mav = new ModelAndView();
			
			// 상세보기 트럭 조회수 업데이트
			foodTruckService.updateDetailTruck(truckNum);
			
			// 0. 현재 접속자(세션)가 해당 트럭 번호를 좋아했는지 유무 판단
			Object obj = session.getAttribute("memberInfo");
			UserVO user = null;
	        SellerVO seller = null;
	        String memberId = "";
	        if(obj instanceof UserVO){user = (UserVO)obj; memberId = user.getmemberId();}
	        else if(obj instanceof SellerVO){seller = (SellerVO)obj; memberId = seller.getSellerId();}
	        else memberId = "";
			int isLikeExist = likesService.selectIsLikeExist(memberId, truckNum);	// 0이면 Like한적 없음. 1이면 Like했음
			mav.addObject("isLikeExist", isLikeExist);
			
			// 1. FoodTruck테이블 정보 받아오기
	         FoodTruckVO foodTruckVo = foodTruckService.selectDetailTruck(truckNum);
	         /*System.out.println("location : " + foodTruckVo.getTruckLocation());*/
	         
	         if(foodTruckVo != null) {mav.addObject("foodTruckVo", foodTruckVo);}
	         else{mav.addObject("foodTruckVo", null);}

	         GeoCorder geoCorder = new GeoCorder();
	         Float[] coords = geoCorder.geoCoding(foodTruckVo.getTruckLocation());
	         mav.addObject("lat", coords[0]);
	         mav.addObject("lng", coords[1]);
			
			// 2. Menu테이블 정보 받아오기
			int menuCode = -1;	// 메뉴코드 초기화
			List<MenuVO> menuList = menuService.selectDetailMenu(foodTruckVo.getTruckNum());
			if(!menuList.isEmpty()) {
				mav.addObject("menuList", menuList);
				String mainMenu = "";
				for(int i = 0; i < menuList.size(); i++){
					// 주 메뉴 뽑아오기
					if(menuList.get(i).getInventory() == 1) { 
						mainMenu = menuList.get(i).getMenuName();
						mav.addObject("mainMenu", mainMenu);
					}
				}
				int temp = 0;
				for(int i = 0; i < menuList.size(); i++){
					// 메뉴코드 최대값 구하기
					temp = menuList.get(i).getMenuCode();
					if(menuCode < temp) menuCode = temp;
					temp = 0;
				}
			}
			else{mav.addObject("menuList", null);}
			
			// 3. menu_image 테이블 조회
			List<MenuImageVO> menuImageList = menuImageService.selectDetailMenuImage(menuCode);
			mav.addObject("menuImageList", menuImageList);
			
			// 4. seller_member 테이블 조회
			List<SellerVO> sellerList = sellerService.selectDetailMember(foodTruckVo.getSellerId());
			if(!sellerList.isEmpty()){
				mav.addObject("sellerVo", sellerList.get(0));
			}
			else{mav.addObject("sellerVo", null);}
			
			// 5. reply_truck 테이블 조회 (ajax로 구현하면 & detail페이지 디자인 수정하면 없애면됨.)
			List<ReplyTruckVO> replyList = replyTruckService.selectDetailReplyTruck(truckNum);
			mav.addObject("replyList", replyList);

			mav.setViewName("truck/truckDetailView");
			return mav;
		}
		
		///////////////////////////////////////////////////////////////////////
	//이용자 , 판매자 회원가입 페이지로 이동
	@RequestMapping(value = "/user/MemberJoin.do", method = RequestMethod.GET)
	public String goJoin() {return "user/MemberJoin";}
	
	//로그인 처리 이동
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
	
	//////////////////////// 트럭 1단계 등록하다가 메뉴 등록할때 작성취소 누르면 //////////////////
	// 1단계 정보 삭제 (foodtruck 테이블 방금입력됬던거 삭제)
	@RequestMapping(value="/truck/truckCancelMenuAdd.do")
	public String deleteTruck(@RequestParam(value="truckNum") int truckNum){
		foodTruckService.deleteTruckByCancel(truckNum);
		return "/user/memberMyPage";
	}
	
	//////////////////////////////////////////////////////////////////////////////
}

















