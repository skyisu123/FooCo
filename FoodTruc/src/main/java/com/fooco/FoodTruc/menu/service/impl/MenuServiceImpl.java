package com.fooco.FoodTruc.menu.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fooco.FoodTruc.foodTruck.util.CommonUtils;
import com.fooco.FoodTruc.foodTruck.util.FileUtils;
import com.fooco.FoodTruc.menu.dao.impl.MenuDaoImpl;
import com.fooco.FoodTruc.menu.service.MenuService;
import com.fooco.FoodTruc.menu.vo.MenuVO;
import com.fooco.FoodTruc.menuImage.service.MenuImageService;
import com.fooco.FoodTruc.menuImage.service.impl.MenuImageServiceImpl;

@Service("menuService")
public class MenuServiceImpl implements MenuService{
	
	@Autowired
	private MenuDaoImpl menuDao;
	@Autowired
	private MenuServiceImpl menuService;
	@Autowired
	private MenuImageServiceImpl menuImageService;
	
	// 메뉴 등록 처리
	@Override
	@Transactional
	public ModelAndView insertMenu(MultipartHttpServletRequest multi, List<Map<String, Object>> lists, Map<String, Object> map, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		String realPath = session.getServletContext().getRealPath("/");
		String imagePath = realPath + "resources\\bootstrap\\imageupload\\";
		System.out.println("realPath : " + imagePath);
		
		// 파일 업로드 처리
		///////////// 파일 업로드 객체 생성 /////////////
		//File file = new File(filePath);
		//if(file.exists() == false) file.mkdirs();	// 파일 저장할 경로에 해당폴더 없으면 폴더 생성
		File file = new File(imagePath);
		if(file.exists() == false) file.mkdirs();
		/////////////////////////////////////////
		
		String truckImage = "";	//원본파일 이름
		String originalFileExtension = "";	//원본파일 확장자(JPG,jpg,png,Png ...)
		String storedFileName = "";		// 저장될 파일이름 (FileUtils클래스에서 변환될것임)
		List<String> images = new ArrayList<String>();

		Iterator<String> itr = multi.getFileNames();
		MultipartFile multipartFile = null;
		while(itr.hasNext()){	
	        multipartFile = multi.getFile(itr.next());
	        if(multipartFile.isEmpty() == false){
	            truckImage = multipartFile.getOriginalFilename();	// 원본 파일이름을 변수에 대입
	            originalFileExtension = truckImage.substring(truckImage.lastIndexOf("."));	// 파일이름의 끝 .jpg 등 확장자 부분까지만 잘라내어 대입.
	            storedFileName = CommonUtils.getRandomString() + originalFileExtension;	// 실제로 저장될 파일의 이름은 32글자의 랜덤한문자열 + 뒤 확장자
	            images.add(storedFileName);	//List에 저장될 이미지 이름 저장
	            // 변환된 파일명 지정된 경로에 파일생성
	    		//file = new File(filePath + storedFileName);
	    		file = new File(imagePath + storedFileName);
	    		try{
	    			//multipartFile.transferTo(file);	//경로에 파일저장
	    			multipartFile.transferTo(file);
	    		}catch(Exception e){System.out.println("홍보이미지 파일저장 오류 : " + e.getMessage());}
	        }
	    }
		
		// 파라미터로 전달된 lists 가공, MenuTable에 우선삽입
		// 마지막으로 반환된 Menu 테이블의 PK값으로 MENU_IMAGE테이블에 참조키로 삽입.
		int menuCode = -1;
		for(Map<String, Object> mapList : lists){	//전달된 input태그의 갯수만큼 (추가버튼 누른 수만큼) insert실행.
			menuCode = menuDao.insertMenu(mapList);
		}
		int count = 1;	// 최종 성공유무 판단 변수
		
		if(menuCode > 0 ){	//메뉴정보 입력 성공시
			//매퍼에서 insert후 반환된 값을 마지막으로 insert된 값의 menu_code 값이 map의 menuCode에 있음.
			System.out.println("menuCode : " + map.get("menuCode"));
			// menu_image테이블에 업로드 한 이미지 파일들을 저장.
			menuCode = (Integer) map.get("menuCode");
			for(int i = 0; i < images.size(); i++){
				count++;
				map.put("imageName", images.get(i));
				menuDao.insertMenuImage(map);
			}
		}
		else{	//메뉴정보 입력 실패시
			
		}
		
		// 분기를 두어 ModelAndView 결정
		if(count != 1){
			mav.addObject("menuAddResult", "success");
			mav.setViewName("redirect:/truck/trucFoodTrucList.do");
		}
		else{
			mav.addObject("menuAddResult", "fail");
			mav.setViewName("redirect:/truck/trucFoodTrucList.do");
		}
		
		return mav;
	}

	// 메뉴 가져오기
	@Override
	public List<MenuVO> selectDetailMenu(int truckNum){
		List<MenuVO> list = menuDao.selectDetailMenu(truckNum);
		if(!list.isEmpty()){
			return list;
		}
		else return null;
		
	}
	
	@Override
	@Transactional
	public ModelAndView modifyMenu(MultipartHttpServletRequest multi, List<Map<String, Object>> lists,
			Map<String, Object> map, HttpSession session) throws Exception {
		int menuCode = -1;
		// MenuImage 삭제 전 menuCode를 알아야함
		List<MenuVO> menuList = menuService.selectDetailMenu((Integer)map.get("truckNum"));
		// 우선 MENU 테이블과 MENU_IMAGE 테이블삭제
		// 1. MENU 테이블삭제
		menuDao.deleteMenuModifyBefore((Integer)map.get("truckNum"));
		
		
		int temp = 0;
		for(int i = 0; i < menuList.size(); i++){
			// 메뉴코드 최대값 구하기
			temp = menuList.get(i).getMenuCode();
			if(menuCode < temp) menuCode = temp;
			temp = 0;
		}
		// 2. MENU_IMAGE테이블 삭제 
		menuImageService.deleteMenuModifyBefore(menuCode);
		
		// 다시 등록하듯이 테이블에 등록
		ModelAndView mav = insertMenu(multi, lists, map, session);
		mav.addObject("menuAddResult", "성공");
		mav.setViewName("redirect:/user/memberMyPage.do");
		
		return mav;
		
	}
}
