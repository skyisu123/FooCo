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

// ���� ó������ Ŭ����
// ������ �����̳ʰ� �������� ����.
@Component("fileUtils")
public class FileUtils {
	// ������ ����� ��ġ
	private static final String filePath = "C:\\dev\\file\\";
	
	public List<Map<String,Object>> parseInsertFileInfo(Map<String,Object> map, HttpServletRequest request) throws Exception{
        MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
        Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
         
        MultipartFile multipartFile = null;
        String originalFileName = null;
        String originalFileExtension = null;
        String storedFileName = null;
        
        //Ŭ���̾�Ʈ���� ���۵� ���� ������ ��Ƽ� ��ȯ�� ���� List. ���� ���� ���� (�޴� �̹�����)
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null;
        
        // 1�ܰ� ��� �� serviceImpl�������� �������� map���� �ű� �����Ǵ�
        // Truck_num ��ȣ ���� �޾ƿ����� ����
        String truckIdx = (String)map.get("IDX");
        
        //������ ������ ��ο� �ش������� ������ ������ ����. 
        File file = new File(filePath);
        if(file.exists() == false){
            file.mkdirs();
        }

        //������ ������ �޾Ƽ� ���ο� �̸����� �����ϴ� �κ�
        //�� ������ �����̸��� �޾ƿͼ� �ش� ������ Ȯ���ڸ� �˾Ƴ� ��, CommonUtils Ŭ������  getRandomString() �޼��带 �̿��Ͽ� 
        //32�ڸ��� ������ �����̸��� �����ϰ� ���������� Ȯ���ڸ� �ٽ� �ٿ���.
        while(iterator.hasNext()){
            multipartFile = multipartHttpServletRequest.getFile(iterator.next());
            if(multipartFile.isEmpty() == false){
                originalFileName = multipartFile.getOriginalFilename();
                originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
                storedFileName = CommonUtils.getRandomString() + originalFileExtension;
                 
                // ���� ������ ����
                // transferTo()�� ������ ��ο� ������ ����
                file = new File(filePath + storedFileName);
                multipartFile.transferTo(file);
                // ������ ���� ������ list�� �߰�
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
