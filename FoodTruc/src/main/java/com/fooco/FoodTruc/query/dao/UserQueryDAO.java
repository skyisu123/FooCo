package com.fooco.FoodTruc.query.dao;

import java.util.List;
import java.util.Map;

import com.fooco.FoodTruc.query.vo.UserQueryVO;

public interface UserQueryDAO {
	public int getTotalCount(String memberId,String read);
	
	//페이징처리를 위한 메소드
	public String pagingImg(int totalRecordCount, int pageSize, int blockPage, int nowPage, String page);
	
	public List<UserQueryVO> showList(Map<String, Object> map);
	
	public void querySend(Map<String, Object> map);
	
	public int getQueryCount(String sellerId,String read);
		
}
