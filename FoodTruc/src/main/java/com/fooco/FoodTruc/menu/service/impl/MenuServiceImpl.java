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
	
	// �޴� ��� ó��
	@Override
	@Transactional
	public ModelAndView insertMenu(MultipartHttpServletRequest multi, List<Map<String, Object>> lists, Map<String, Object> map, HttpSession session) throws Exception {
		ModelAndView mav = new ModelAndView();
		String realPath = session.getServletContext().getRealPath("/");
		String imagePath = realPath + "resources\\bootstrap\\imageupload\\";
		System.out.println("realPath : " + imagePath);
		
		// ���� ���ε� ó��
		///////////// ���� ���ε� ��ü ���� /////////////
		//File file = new File(filePath);
		//if(file.exists() == false) file.mkdirs();	// ���� ������ ��ο� �ش����� ������ ���� ����
		File file = new File(imagePath);
		if(file.exists() == false) file.mkdirs();
		/////////////////////////////////////////
		
		String truckImage = "";	//�������� �̸�
		String originalFileExtension = "";	//�������� Ȯ����(JPG,jpg,png,Png ...)
		String storedFileName = "";		// ����� �����̸� (FileUtilsŬ�������� ��ȯ�ɰ���)
		List<String> images = new ArrayList<String>();

		Iterator<String> itr = multi.getFileNames();
		MultipartFile multipartFile = null;
		while(itr.hasNext()){	
	        multipartFile = multi.getFile(itr.next());
	        if(multipartFile.isEmpty() == false){
	            truckImage = multipartFile.getOriginalFilename();	// ���� �����̸��� ������ ����
	            originalFileExtension = truckImage.substring(truckImage.lastIndexOf("."));	// �����̸��� �� .jpg �� Ȯ���� �κб����� �߶󳻾� ����.
	            storedFileName = CommonUtils.getRandomString() + originalFileExtension;	// ������ ����� ������ �̸��� 32������ �����ѹ��ڿ� + �� Ȯ����
	            images.add(storedFileName);	//List�� ����� �̹��� �̸� ����
	            // ��ȯ�� ���ϸ� ������ ��ο� ���ϻ���
	    		//file = new File(filePath + storedFileName);
	    		file = new File(imagePath + storedFileName);
	    		try{
	    			//multipartFile.transferTo(file);	//��ο� ��������
	    			multipartFile.transferTo(file);
	    		}catch(Exception e){System.out.println("ȫ���̹��� �������� ���� : " + e.getMessage());}
	        }
	    }
		
		// �Ķ���ͷ� ���޵� lists ����, MenuTable�� �켱����
		// ���������� ��ȯ�� Menu ���̺��� PK������ MENU_IMAGE���̺� ����Ű�� ����.
		int menuCode = -1;
		for(Map<String, Object> mapList : lists){	//���޵� input�±��� ������ŭ (�߰���ư ���� ����ŭ) insert����.
			menuCode = menuDao.insertMenu(mapList);
		}
		int count = 1;	// ���� �������� �Ǵ� ����
		
		if(menuCode > 0 ){	//�޴����� �Է� ������
			//���ۿ��� insert�� ��ȯ�� ���� ���������� insert�� ���� menu_code ���� map�� menuCode�� ����.
			System.out.println("menuCode : " + map.get("menuCode"));
			// menu_image���̺� ���ε� �� �̹��� ���ϵ��� ����.
			menuCode = (Integer) map.get("menuCode");
			for(int i = 0; i < images.size(); i++){
				count++;
				map.put("imageName", images.get(i));
				menuDao.insertMenuImage(map);
			}
		}
		else{	//�޴����� �Է� ���н�
			
		}
		
		// �б⸦ �ξ� ModelAndView ����
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

	// �޴� ��������
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
		// MenuImage ���� �� menuCode�� �˾ƾ���
		List<MenuVO> menuList = menuService.selectDetailMenu((Integer)map.get("truckNum"));
		// �켱 MENU ���̺�� MENU_IMAGE ���̺����
		// 1. MENU ���̺����
		menuDao.deleteMenuModifyBefore((Integer)map.get("truckNum"));
		
		
		int temp = 0;
		for(int i = 0; i < menuList.size(); i++){
			// �޴��ڵ� �ִ밪 ���ϱ�
			temp = menuList.get(i).getMenuCode();
			if(menuCode < temp) menuCode = temp;
			temp = 0;
		}
		// 2. MENU_IMAGE���̺� ���� 
		menuImageService.deleteMenuModifyBefore(menuCode);
		
		// �ٽ� ����ϵ��� ���̺� ���
		ModelAndView mav = insertMenu(multi, lists, map, session);
		mav.addObject("menuAddResult", "����");
		mav.setViewName("redirect:/user/memberMyPage.do");
		
		return mav;
		
	}
}
