package com.fooco.FoodTruc.query.dao.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fooco.FoodTruc.foodTruck.dao.FoodTruckDAO;
import com.fooco.FoodTruc.query.dao.SellerQueryDAO;
import com.fooco.FoodTruc.query.dao.UserQueryDAO;
import com.fooco.FoodTruc.query.vo.UserQueryVO;

import common.CommonDao;

@Repository
public class UserQueryDAOImpl extends CommonDao implements UserQueryDAO{
	@Override
	public int getTotalCount(String memberId,String read) {
		return getSqlSession().getMapper(UserQueryDAO.class).getTotalCount(memberId,read);
	}

	@Override
	public void querySend(Map<String, Object> map) {
		getSqlSession().getMapper(UserQueryDAO.class).querySend(map);
		System.out.println("성공적으로 처리완료");
	}	
	
	//페이징 처리하기
	@Override
	public String pagingImg(int totalRecordCount, int pageSize, int blockPage, int nowPage, String page)
	{
		String pagingStr = "";
		
		//1. 전체 페이지 구하기
		int totalPage = (int) (Math.ceil(((double)totalRecordCount/pageSize)));
		int intTemp = ((nowPage - 1) / blockPage) * blockPage + 1;
		
		//처음페이지 & 이전페이지를 위한 로직
		if(intTemp != 1){
			pagingStr += "<a href='"+page+"nowPage=1'><img src='../resources/bootstrap/img/pagingImg/paging1.gif'></a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='"+page+"nowPage="+(intTemp-blockPage)+"'><img src='../resources/bootstrap/img/pagingImg/paging2.gif'></a>";
		}
		//페이지 표시 제어를 위한 변수
		int blockCount = 1;
		
		//페이지를 뿌려주는 로직 : 블럭페이지 수만큼 혹은 
		//마지막 페이지가 될때까지 표시한다.
		while(blockCount <= blockPage && intTemp<=totalPage){
			//현재페이지인 경우
			if(intTemp == nowPage){
				pagingStr += "<span style='font-size:1.5em;color:blue;'><b>"+intTemp+"</b></span>";
			}
			else {
				pagingStr += "<a href='"+page+"nowPage="+intTemp+"'><span style='font-size:1.5em;color:blue;'>"+intTemp+"</a></span>";
			}
			intTemp = intTemp + 1;
			blockCount = blockCount + 1;
		}
			
		//다음 및 마지막 페이지를 위한 로직
		if(intTemp <= totalPage){
			pagingStr += "<a href='"+page+"nowPage="+intTemp+"'><img src='../resources/bootstrap/img/pagingImg/paging3.gif'></a>";
			pagingStr += "&nbsp;";
			
			pagingStr += "<a href='"+page+"nowPage="+totalPage+"'><img src='../resources/bootstrap/img/pagingImg/paging4.gif'></a>";
		
		}
	
		return pagingStr;
	
	}
	
	@Override
	public List<UserQueryVO> showList(Map<String, Object> map) {
		return getSqlSession().getMapper(UserQueryDAO.class).showList(map);
	}
	

	@Override
	public int getQueryCount(String sellerId,String read) {
		return getSqlSession().getMapper(UserQueryDAO.class).getQueryCount(sellerId,read);
	}
	
		
}
