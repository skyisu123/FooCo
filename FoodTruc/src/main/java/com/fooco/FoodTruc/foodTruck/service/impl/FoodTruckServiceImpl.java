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
	
	//�Ǹ� ǪƮ�巰 ���
	@Override
	@Transactional
	public int insertTruck(MultipartHttpServletRequest multi, Map<String, Object> map, HttpSession session) throws Exception {
		String realPath = session.getServletContext().getRealPath("/");
		String imagePath = realPath + "resources\\bootstrap\\imageupload\\";
		
		map.put("truckStime", map.get("truckStime").toString().replace("T", " "));	//ġȯ �� �����
		map.put("truckEtime", map.get("truckEtime").toString().replace("T", " "));
		
		///////////// ���� ���ε� ��ü ���� /////////////
		File file = new File(imagePath);
		if(file.exists() == false) file.mkdirs();	// ���� ������ ��ο� �ش����� ������ ���� ����
		/////////////////////////////////////////
		
		String truckImage = "";	//�������� �̸�
		String originalFileExtension = "";	//�������� Ȯ����(JPG,jpg,png,Png ...)
		String storedFileName = "";		// ����� �����̸� (FileUtilsŬ�������� ��ȯ�ɰ���)
		
		Iterator<String> itr = multi.getFileNames();
		MultipartFile multipartFile = null;
		while(itr.hasNext()){	// ȫ�� �̹����� 1�常 ��ϵǹǷ� ���ͷ����Ͱ� �ʿ������.. ���� ���� ���� �� ���� ����.
	        multipartFile = multi.getFile(itr.next());
	        if(multipartFile.isEmpty() == false){
	            truckImage = multipartFile.getOriginalFilename();	// ���� �����̸��� ������ ����
	            originalFileExtension = truckImage.substring(truckImage.lastIndexOf("."));	// �����̸��� �� .jpg �� Ȯ���� �κб����� �߶󳻾� ����.
	            storedFileName = CommonUtils.getRandomString() + originalFileExtension;	// ������ ����� ������ �̸��� 32������ �����ѹ��ڿ� + �� Ȯ����
	        }
	    }
		map.put("truckNum", -1);	//map�� truckNum���� �ʱ�ȭ�Ͽ� �����������.
		// mapper���� selectKey���� insert��, max(truckNum)�� �ϴµ�, return���� int�� ����.
		// �̶�, ��ȯ�Ǵ� ���� int�� �ƴ϶� parameterType���� ���� map��ü�� truckNum Ű���� selectKey�� �������
		// �ڵ����� ���εǾ� ��ȯ�� �ȴ�. �׷��Ƿ� ���� ����. ��
		map.put("truckImage", storedFileName);	// ��ȯ�� ���ϸ��� map�� ����
		// ��ȯ�� ���ϸ� ������ ��ο� ���ϻ���
		file = new File(imagePath + storedFileName);
		try{
			multipartFile.transferTo(file);
		}catch(Exception e){System.out.println("ȫ���̹��� �������� ���� : " + e.getMessage());}
		
		return foodTruckDao.insertTruck(map);
	}

	
	////////////////////////////////// ��ġ�� /////////////////////////////////

	//���ڵ� �� ���� ���ϱ�
	@Override
	public int selectLocationTotalCount(String locationParam) {
		return foodTruckDao.selectLocationTotalCount(locationParam);
	}

	
	//���� ����Ʈ ��������
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
	
	//������ Ǫ��Ʈ�� ��������
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
	
	// Ǫ�������� ����Ʈ �ҷ����� (FoodTruckList Style) By SEO
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
		
	
	// =============��ŷǪ��Ʈ�� ���� By  ���� (����)
	
	@Override
	public int rankingTotalCount() {
		return foodTruckDao.rankingTotalCount();
	}
	
	//Ÿ���� �з��Ͽ� Dao����. By ���� 
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
	// ===================��ŷǪ��Ʈ�� ��

/////////////////////////////// Ʈ�� �󼼺��� /////////////////////////////////
	@Override
	public FoodTruckVO selectDetailTruck(int truckNum) {	//FoodTruck���̺� ��������
		List<FoodTruckVO> list = foodTruckDao.selectDetailTruck(truckNum);
		FoodTruckVO foodTruckVo = null;
		if(!list.isEmpty()){	//list�� ������� ������ list���� �ϳ��� ����Ǿ������״�, 0��° ��ü�� ������ VO�� �Ҵ�
			foodTruckVo = list.get(0);
		}
		
		return foodTruckVo; 
	}
	// ��ȸ�� ������Ʈ
	@Override
	public void updateDetailTruck(int truckNum) {
		foodTruckDao.updateDetailTruck(truckNum);
	}
	// like ������Ʈ
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
	//����� ������ ī����
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
   
   @Override //���ƿ� �Ǹ���
	public ModelAndView likeCountSeller( ModelAndView mav, HttpSession session) {
		SellerVO idx = (SellerVO)session.getAttribute("memberInfo");
		String sellerId = idx.getSellerId();
		FoodTruckVO likeCountSeller = foodTruckDao.likeCountSeller(sellerId);
		int myTruckNumber = foodTruckDao.selectMyTruckNumber(sellerId);
		
		if(likeCountSeller==null) {
			mav.addObject("likeCountSeller", null);
		} else{
			mav.addObject("likeCountSeller", likeCountSeller);
			mav.addObject("myTruckNumber", myTruckNumber); // ���� Ʈ�� ���� ��������
		}
		return mav;
	}
   
   
   //����� ������ ����Ʈ �����ֱ�
	@Override
	public ModelAndView CountContentUserList( ModelAndView mav,HttpSession session) {
		UserVO idx =(UserVO)session.getAttribute("memberInfo");
		String id = idx.getmemberId();
		List<ReplyTruckVO> CountContentUserList = replyTruckDao.CountContentUserList(id);
		mav.addObject("CountContentUserList", CountContentUserList);
		return mav;
	}
	
   
   
   @Override //������� �Ǹ���
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
   
	//���ƿ� �ҷ�����
	@Override
	public  ModelAndView likeCountUserList (ModelAndView mav,HttpSession session) {
		UserVO idx =(UserVO)session.getAttribute("memberInfo");
		String id = idx.getmemberId();
		List<FoodTruckVO>likeCountUserList = foodTruckDao.likeCountUserList(id);
		mav.addObject("likeCountUserList", likeCountUserList);
		return mav;
	}
	
	// �� Ʈ�� ����Ʈ �ҷ�����
	@Override
	public ModelAndView selectMyTruckList(ModelAndView mav, HttpSession session) {
		SellerVO idx = (SellerVO)session.getAttribute("memberInfo");
	    String sellerId = idx.getSellerId();
		List<FoodTruckVO> myTruckList = foodTruckDao.selectMyTruckList(sellerId);
	    mav.addObject("myTruckList", myTruckList);
		return mav;
	}
	
	// ��Ʈ�� �����ϱ�
	@Override
	public void deleteTruck(int truckNum) {
		foodTruckDao.deleteTruck(truckNum);
	}
	
	// ��Ʈ�� �����ϱ�
	@Override
	public ModelAndView truckModify(int truckNum, ModelAndView mav) {
		List<FoodTruckVO> list = foodTruckDao.selectDetailTruck(truckNum);
		FoodTruckVO foodTruckVo = list.get(0);
		mav.addObject("foodTruckVo", foodTruckVo);
		return mav;
	}
	
	// �� Ʈ�� ����ó��
	@Override
	@Transactional
	public ModelAndView updateTruck(MultipartHttpServletRequest multi, Map<String, Object> map, ModelAndView mav, HttpSession session) throws Exception {
		String realPath = session.getServletContext().getRealPath("/");
		String imagePath = realPath + "resources\\bootstrap\\imageupload\\";
		map.put("truckStime", map.get("truckStime").toString().replace("T", " "));	//ġȯ �� �����
		map.put("truckEtime", map.get("truckEtime").toString().replace("T", " "));
		
		if(map.get("existFile").equals("not")){	//���������� ������ ���ε� �̹����� ����������
			///////////// ���� ���ε� ��ü ���� /////////////
			File file = new File(imagePath);
			if(file.exists() == false) file.mkdirs();	// ���� ������ ��ο� �ش����� ������ ���� ����
			/////////////////////////////////////////
			
			String truckImage = "";	//�������� �̸�
			String originalFileExtension = "";	//�������� Ȯ����(JPG,jpg,png,Png ...)
			String storedFileName = "";		// ����� �����̸� (FileUtilsŬ�������� ��ȯ�ɰ���)
			
			Iterator<String> itr = multi.getFileNames();
			MultipartFile multipartFile = null;
			while(itr.hasNext()){	
		        multipartFile = multi.getFile(itr.next());
		        if(multipartFile.isEmpty() == false){
		            truckImage = multipartFile.getOriginalFilename();	// ���� �����̸��� ������ ����
		            originalFileExtension = truckImage.substring(truckImage.lastIndexOf("."));	// �����̸��� �� .jpg �� Ȯ���� �κб����� �߶󳻾� ����.
		            storedFileName = CommonUtils.getRandomString() + originalFileExtension;	// ������ ����� ������ �̸��� 32������ �����ѹ��ڿ� + �� Ȯ����
		        }
		    }
			map.put("truckImg", storedFileName);	// ��ȯ�� ���ϸ��� map�� ����
			// ��ȯ�� ���ϸ� ������ ��ο� ���ϻ���
			file = new File(imagePath + storedFileName);
			try{
				multipartFile.transferTo(file);
			}catch(Exception e){System.out.println("ȫ���̹��� �������� ���� : " + e.getMessage());}
		}
		else{	// ������ ȫ���̹����� ���ٸ�
			map.put("truckImg", map.get("existFile"));	//hidden�� ���� ������ �װ��� truckImg�� map�� ����
		}
		
		int result = foodTruckDao.updateTruck(map);
		if(result == 1){
			mav.addObject("successMsg", "����");
			mav.setViewName("forward:/user/myTruckListPage.do");
		}
		return mav;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////

	////////////////////////Ʈ�� 1�ܰ� ����ϴٰ� �޴� ����Ҷ� �ۼ���� ������ //////////////////
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























