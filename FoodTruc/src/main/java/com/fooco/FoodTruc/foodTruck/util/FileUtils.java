package com.fooco.FoodTruc.foodTruck.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

// 파일 처리위한 클래스
// 스프링 컨테이너가 관리해줄 것임.
@Component("fileUtils")
public class FileUtils {
	// 파일이 저장될 위치
	private static final String filePath = "C:\\dev\\file\\";
	
	public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        
        //클라이언트에서 전송된 파일 정보를 담아서 반환을 해줄 List. 다중 파일 전송 (메뉴 이미지들)
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null;
        
        // 1단계 등록 후 serviceImpl영역에서 전달해준 map에서 신규 생성되는
        // Truck_num 번호 값을 받아오도록 구현
        String truckIdx = (String)map.get("IDX");
        
        //파일을 저장할 경로에 해당폴더가 없으면 폴더를 생성. 
        File file = new File(filePath);
        if(file.exists() == false){
            file.mkdirs();
        }

        //파일의 정보를 받아서 새로운 이름으로 변경하는 부분
        //저 파일의 원본이름을 받아와서 해당 파일의 확장자를 알아낸 후, CommonUtils 클래스에  getRandomString() 메서드를 이용하여 
        //32자리의 랜덤한 파일이름을 생성하고 원본파일의 확장자를 다시 붙여줌.
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false){
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = CommonUtils.getRandomString() + originalFileExtension;
                 
                // 실제 파일을 저장
                // transferTo()로 지정된 경로에 파일을 생성
                file = new File(filePath + storedFileName);
                multipartFile.transferTo(file);
                // 위에서 만든 정보를 list에 추가
                listMap = new HashMap<String,Object>();
                listMap.put("BOARD_IDX", truckIdx);
                listMap.put("ORIGINAL_FILE_NAME", originalFileName);
                listMap.put("STORED_FILE_NAME", storedFileName);
                listMap.put("FILE_SIZE", multipartFile.getSize());
                list.add(listMap);
            }
        }
        return list;
    }
	
}
