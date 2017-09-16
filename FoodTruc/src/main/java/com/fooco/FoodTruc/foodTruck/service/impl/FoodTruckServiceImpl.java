package com.fooco.FoodTruc.foodTruck.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.foodTruck.dao.impl.FoodTruckDaoImpl;
import com.fooco.FoodTruc.foodTruck.service.FoodTruckService;
import com.fooco.FoodTruc.foodTruck.util.CommonUtils;
import com.fooco.FoodTruc.foodTruck.vo.FoodTruckVO;
import com.fooco.FoodTruc.member.vo.SellerVO;
import com.fooco.FoodTruc.member.vo.UserVO;
import com.fooco.FoodTruc.replyTruck.dao.impl.ReplyTruckDaoImpl;
import com.fooco.FoodTruc.replyTruck.vo.ReplyTruckVO;

@Service("foodTruckService")
public class FoodTruckServiceImpl implements FoodTruckService{
	
	@Autowired
	private FoodTruckDaoImpl foodTruckDao;
	@Autowired
	private ReplyTruckDaoImpl replyTruckDao;
	
	//판매 푸트드럭 등록
	@Override
	@Transactional
	public int insertTruck(MultipartHttpServletRequest multi, Map<String, Object> map, HttpSession session) throws Exception {
		String realPath = session.getServletContext().getRealPath("/");
		String imagePath = realPath + "resources\\bootstrap\\imageupload\\";
		
		map.put("truckStime", map.get("truckStime").toString().replace("T", " "));	//치환 후 덮어쓰기
		map.put("truckEtime", map.get("truckEtime").toString().replace("T", " "));
		
		///////////// 파일 업로드 객체 생성 /////////////
		File file = new File(imagePath);
		if(file.exists() == false) file.mkdirs();	// 파일 저장할 경로에 해당폴더 없으면 폴더 생성
		/////////////////////////////////////////
		
		String truckImage = "";	//원본파일 이름
		String originalFileExtension = "";	//원본파일 확장자(JPG,jpg,png,Png ...)
		String storedFileName = "";		// 저장될 파일이름 (FileUtils클래스에서 변환될것임)
		
		Iterator<String> itr = multi.getFileNames();
		MultipartFile multipartFile = null;
		while(itr.hasNext()){	// 홍보 이미지는 1장만 등록되므로 이터레이터가 필요없긴함.. 다중 파일 구현 후 수정 예정.
	        multipartFile = multi.getFile(itr.next());
	        if(multipartFile.isEmpty() == false){
	            truckImage = multipartFile.getOriginalFilename();	// 원본 파일이름을 변수에 대입
	            originalFileExtension = truckImage.substring(truckImage.lastIndexOf("."));	// 파일이름의 끝 .jpg 등 확장자 부분까지만 잘라내어 대입.
	            storedFileName = CommonUtils.getRandomString() + originalFileExtension;	// 실제로 저장될 파일의 이름은 32글자의 랜덤한문자열 + 뒤 확장자
	        }
	    }
		map.put("truckNum", -1);	//map에 truckNum값을 초기화하여 설정해줘야함.
		// mapper에서 selectKey에서 insert후, max(truckNum)을 하는데, return값을 int로 설정.
		// 이때, 반환되는 값은 int가 아니라 parameterType으로 받은 map객체에 truckNum 키값에 selectKey의 결과값이
		// 자동으로 매핑되어 반환이 된다. 그러므로 필히 설정. ★
		map.put("truckImage", storedFileName);	// 변환된 파일명을 map에 저장
		// 변환된 파일명 지정된 경로에 파일생성
		file = new File(imagePath + storedFileName);
		try{
			multipartFile.transferTo(file);
		}catch(Exception e){System.out.println("홍보이미지 파일저장 오류 : " + e.getMessage());}
		
		return foodTruckDao.insertTruck(map);
	}

	
	////////////////////////////////// 위치별 /////////////////////////////////

	//레코드 총 개수 구하기
	@Override
	public int selectLocationTotalCount(String locationParam) {
		return foodTruckDao.selectLocationTotalCount(locationParam);
	}

	
	//지역 리스트 가져오기
	@Override
	public List<FoodTruckVO> selectLocationList(String locationParam, int offsetParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("location", locationParam);
		map.put("offset", offsetParam);
		if(offsetParam == 0) map.put("limit", 16);
		else map.put("limit", offsetParam+15);
		
		return foodTruckDao.selectLocationList(map);
	}


	
	/////////////////////////////////////////////////////////////////////////
	
	//종류별 푸드트럭 가져오기
	@Override
	public List<FoodTruckVO> typeSelect(String truckType, int offsetParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("truckType", truckType);
		map.put("offset", offsetParam);
		if(offsetParam == 0) map.put("limit", 16);
		else map.put("limit", offsetParam*2);
		
		return foodTruckDao.typeSelect(map);
	}

	@Override
	public int getTotalCount(String truckType) {
		return foodTruckDao.getTotalCount(truckType);
	}


	////////////////S E O s t a r t //////////////////////
	// openDate totalCount by SEO
	@Override
	public int selectDateTotalCount(String dateParam) {
		return foodTruckDao.selectDateTotalCount(dateParam);
	}
	
	@Override
	public List<FoodTruckVO> selectOpenDateList(String setOpenDateParam, int startParam, int endParam){
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("openDate", setOpenDateParam);
		map.put("start", startParam);
		map.put("end", endParam);
		
		return foodTruckDao.selectOpenDateList(map);
	}
	
	// get openDate List By SEO
	@Override
	public List<FoodTruckVO> selectOpenDateList(String setOpenDateParam, int startParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("openDate", setOpenDateParam);
		map.put("start", startParam);
		if (startParam == 0)
		map.put("end", 16);
		else
		map.put("end", startParam + 15);
		
		return foodTruckDao.selectOpenDateList(map);
	}
	
	// get all record list foodtruck By SEO
	@Override
	public List<FoodTruckVO> list(int start, int end) {
		List<FoodTruckVO> foodTruckVO = foodTruckDao.list(start, end);
		
		return foodTruckVO;
	}
	
	// 푸드종류별 리스트 불러오기 (FoodTruckList Style) By SEO
	@Override
	public List<FoodTruckVO> foodAssortativeList(String truckTypeParam, int startParam, int endParam){
	
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("truckType", truckTypeParam);
		map.put("offset", startParam);
		map.put("limit", endParam);
		
		List<FoodTruckVO> lists = foodTruckDao.typeSelect(map);
		
		return lists;
	}
	
	@Override
	public List<FoodTruckVO> selectLocationList(String locationParam, int startParam, int endParam){
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("location", locationParam);
		map.put("offset", startParam);
		map.put("limit", endParam);
		
		return foodTruckDao.selectLocationList(map);
	
	}
	
	//get searchList By SEO
	@Override
	public List<FoodTruckVO> searchLists( String searchOptionParam, String searchTextParam, int startParam){
		System.out.println("service search function Join");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("searchOptionParam", searchOptionParam);
		map.put("searchTextParam", searchTextParam);
		map.put("startParam", startParam);
		if (startParam == 0) map.put("endParam", 16);
		else map.put("endParam", startParam + 15);
		
		return foodTruckDao.searchLists(map);
	}
	
	//get searchTotalCount By SEO
	@Override
	public int searchTotalCount( String searchOptionParam, String searchTextParam ){
		System.out.println("service searchTotalCount function Join");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("searchOptionParam", searchOptionParam);
		map.put("searchTextParam", searchTextParam);
		return foodTruckDao.searchTotalCount(map);
	}
	//////////////// S E O e n d //////////////////////
		
	
	// =============랭킹푸드트럭 시작 By  형무 (동섭)
	
	@Override
	public int rankingTotalCount() {
		return foodTruckDao.rankingTotalCount();
	}
	
	//타입을 분류하여 Dao연결. By 형무 
	@Override
	public List<FoodTruckVO> truckRankList(int startParam, String typeParam) {

		Map<String, Object> map = new HashMap<String, Object>();
		List<FoodTruckVO> lists = null;
		map.put("start", startParam);
		
		
		if (startParam == 0)
			map.put("end", 16);
		else
			map.put("end", startParam + 15);
		
		
		if (typeParam.equals("RankList")) {
			lists = foodTruckDao.trukRankList(map);
			
		} else if (typeParam.equals("RankingLike")) {
			lists = foodTruckDao.selectRankingLike(map);

		} else if (typeParam.equals("RankingScore")) {
			lists = foodTruckDao.selectRankingScore(map);
		} else if (typeParam.equals("RankingReply")) {
			lists = foodTruckDao.selectRankingReply(map);
		} else if (typeParam.equals("FoodTruckList")){
			/*map.put("start", 1);*/
			map.put("end", 4);
			lists = foodTruckDao.trukRankList(map); 
		}
		
		return lists;
	}
	
	@Override
	public List<FoodTruckVO> truckRankList(int startParam, int endParam) {
		Map<String, Object> map = new HashMap<String, Object>();
		List<FoodTruckVO> lists = null;
		map.put("start", startParam);
		map.put("end", endParam);
		
		lists = foodTruckDao.trukRankList(map);
		
		return lists;
	}
	// ===================랭킹푸드트럭 끝

/////////////////////////////// 트럭 상세보기 /////////////////////////////////
	@Override
	public FoodTruckVO selectDetailTruck(int truckNum) {	//FoodTruck테이블 가져오기
		List<FoodTruckVO> list = foodTruckDao.selectDetailTruck(truckNum);
		FoodTruckVO foodTruckVo = null;
		if(!list.isEmpty()){	//list가 비어있지 않으면 list에는 하나만 저장되어있을테니, 0번째 객체를 꺼내어 VO에 할당
			foodTruckVo = list.get(0);
		}
		
		return foodTruckVo; 
	}
	// 조회수 업데이트
	@Override
	public void updateDetailTruck(int truckNum) {
		foodTruckDao.updateDetailTruck(truckNum);
	}
	// like 업데이트
	@Override
	public int updateTruckLike(int truckNum) {
		int result = foodTruckDao.updateTruckLike(truckNum);
		if(result != 0){
			result = foodTruckDao.selectTruckLike(truckNum);
		}
		else result = -1;
		return result;
	}
	
	//////////////////////////////////////My Page Edit By Hyoung Moo ///////////////////////////////////
	//사용자 한줄평 카운팅
	@Override
	public ModelAndView CountContentUser( ModelAndView mav,HttpSession session) {
		UserVO idx =(UserVO)session.getAttribute("memberInfo");
		String id = idx.getmemberId();
		int CountContentUser = replyTruckDao.CountContentUser(id);
		if(CountContentUser>0) {
			mav.addObject("CountContentUser", CountContentUser);
		}
		else {
			mav.addObject("CountContentUser", "0");
		}
		return mav;
	}
   
   @Override //좋아요 판매자
	public ModelAndView likeCountSeller( ModelAndView mav, HttpSession session) {
		SellerVO idx = (SellerVO)session.getAttribute("memberInfo");
		String sellerId = idx.getSellerId();
		FoodTruckVO likeCountSeller = foodTruckDao.likeCountSeller(sellerId);
		int myTruckNumber = foodTruckDao.selectMyTruckNumber(sellerId);
		
		if(likeCountSeller==null) {
			mav.addObject("likeCountSeller", null);
		} else{
			mav.addObject("likeCountSeller", likeCountSeller);
			mav.addObject("myTruckNumber", myTruckNumber); // 나의 트럭 갯수 가져오기
		}
		return mav;
	}
   
   
   //사용자 한줄평 리스트 보여주기
	@Override
	public ModelAndView CountContentUserList( ModelAndView mav,HttpSession session) {
		UserVO idx =(UserVO)session.getAttribute("memberInfo");
		String id = idx.getmemberId();
		List<ReplyTruckVO> CountContentUserList = replyTruckDao.CountContentUserList(id);
		mav.addObject("CountContentUserList", CountContentUserList);
		return mav;
	}
	
   
   
   @Override //한줄평수 판매자
   public ModelAndView CountContentSeller( ModelAndView mav, HttpSession session) {
	   SellerVO idx = (SellerVO)session.getAttribute("memberInfo");
	   String sellerId = idx.getSellerId();
	   int countContentSeller = -1; 
	   countContentSeller = replyTruckDao.countContentSeller(sellerId);
	   if(countContentSeller==0) {
		   mav.addObject("countContentSeller", "0");  
	   } else {
		   mav.addObject("countContentSeller", countContentSeller);
	   }
	   return mav;
   }
   
	//좋아요 불러오기
	@Override
	public  ModelAndView likeCountUserList (ModelAndView mav,HttpSession session) {
		UserVO idx =(UserVO)session.getAttribute("memberInfo");
		String id = idx.getmemberId();
		List<FoodTruckVO>likeCountUserList = foodTruckDao.likeCountUserList(id);
		mav.addObject("likeCountUserList", likeCountUserList);
		return mav;
	}
	
	// 내 트럭 리스트 불러오기
	@Override
	public ModelAndView selectMyTruckList(ModelAndView mav, HttpSession session) {
		SellerVO idx = (SellerVO)session.getAttribute("memberInfo");
	    String sellerId = idx.getSellerId();
		List<FoodTruckVO> myTruckList = foodTruckDao.selectMyTruckList(sellerId);
	    mav.addObject("myTruckList", myTruckList);
		return mav;
	}
	
	// 내트럭 삭제하기
	@Override
	public void deleteTruck(int truckNum) {
		foodTruckDao.deleteTruck(truckNum);
	}
	
	// 내트럭 수정하기
	@Override
	public ModelAndView truckModify(int truckNum, ModelAndView mav) {
		List<FoodTruckVO> list = foodTruckDao.selectDetailTruck(truckNum);
		FoodTruckVO foodTruckVo = list.get(0);
		mav.addObject("foodTruckVo", foodTruckVo);
		return mav;
	}
	
	// 내 트럭 수정처리
	@Override
	@Transactional
	public ModelAndView updateTruck(MultipartHttpServletRequest multi, Map<String, Object> map, ModelAndView mav, HttpSession session) throws Exception {
		String realPath = session.getServletContext().getRealPath("/");
		String imagePath = realPath + "resources\\bootstrap\\imageupload\\";
		map.put("truckStime", map.get("truckStime").toString().replace("T", " "));	//치환 후 덮어쓰기
		map.put("truckEtime", map.get("truckEtime").toString().replace("T", " "));
		
		if(map.get("existFile").equals("not")){	//수정폼에서 변경할 업로드 이미지를 선택했으면
			///////////// 파일 업로드 객체 생성 /////////////
			File file = new File(imagePath);
			if(file.exists() == false) file.mkdirs();	// 파일 저장할 경로에 해당폴더 없으면 폴더 생성
			/////////////////////////////////////////
			
			String truckImage = "";	//원본파일 이름
			String originalFileExtension = "";	//원본파일 확장자(JPG,jpg,png,Png ...)
			String storedFileName = "";		// 저장될 파일이름 (FileUtils클래스에서 변환될것임)
			
			Iterator<String> itr = multi.getFileNames();
			MultipartFile multipartFile = null;
			while(itr.hasNext()){	
		        multipartFile = multi.getFile(itr.next());
		        if(multipartFile.isEmpty() == false){
		            truckImage = multipartFile.getOriginalFilename();	// 원본 파일이름을 변수에 대입
		            originalFileExtension = truckImage.substring(truckImage.lastIndexOf("."));	// 파일이름의 끝 .jpg 등 확장자 부분까지만 잘라내어 대입.
		            storedFileName = CommonUtils.getRandomString() + originalFileExtension;	// 실제로 저장될 파일의 이름은 32글자의 랜덤한문자열 + 뒤 확장자
		        }
		    }
			map.put("truckImg", storedFileName);	// 변환된 파일명을 map에 저장
			// 변환된 파일명 지정된 경로에 파일생성
			file = new File(imagePath + storedFileName);
			try{
				multipartFile.transferTo(file);
			}catch(Exception e){System.out.println("홍보이미지 파일저장 오류 : " + e.getMessage());}
		}
		else{	// 기존의 홍보이미지로 쓴다면
			map.put("truckImg", map.get("existFile"));	//hidden의 값을 꺼내서 그값을 truckImg로 map에 저장
		}
		
		int result = foodTruckDao.updateTruck(map);
		if(result == 1){
			mav.addObject("successMsg", "성공");
			mav.setViewName("forward:/user/myTruckListPage.do");
		}
		return mav;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////트럭 1단계 등록하다가 메뉴 등록할때 작성취소 누르면 //////////////////
	@Override
	public void deleteTruckByCancel(int truckNum) {
		foodTruckDao.deleteTruckByCancel(truckNum);
	}


	@Override
	public int minusLike(int truckNum) {
		int result = foodTruckDao.minusLike(truckNum);
		if(result != 0){
			result = foodTruckDao.selectTruckLike(truckNum);
		}
		else result = -1;
		return result;
	}
	
	//////////////////////////////////////////////////////////////////////////////
}























