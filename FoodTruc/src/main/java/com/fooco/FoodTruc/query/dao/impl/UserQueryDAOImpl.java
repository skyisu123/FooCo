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
		System.out.println("���������� ó���Ϸ�");
	}	
	
	//����¡ ó���ϱ�
	@Override
	public String pagingImg(int totalRecordCount, int pageSize, int blockPage, int nowPage, String page)
	{
		String pagingStr = "";
		
		//1. ��ü ������ ���ϱ�
		int totalPage = (int) (Math.ceil(((double)totalRecordCount/pageSize)));
		int intTemp = ((nowPage - 1) / blockPage) * blockPage + 1;
		
		//ó�������� & ������������ ���� ����
		if(intTemp != 1){
			pagingStr += "<a href='"+page+"nowPage=1'><img src='../resources/bootstrap/img/pagingImg/paging1.gif'></a>";
			pagingStr += "&nbsp;";
			pagingStr += "<a href='"+page+"nowPage="+(intTemp-blockPage)+"'><img src='../resources/bootstrap/img/pagingImg/paging2.gif'></a>";
		}
		//������ ǥ�� ��� ���� ����
		int blockCount = 1;
		
		//�������� �ѷ��ִ� ���� : �������� ����ŭ Ȥ�� 
		//������ �������� �ɶ����� ǥ���Ѵ�.
		while(blockCount <= blockPage && intTemp<=totalPage){
			//������������ ���
			if(intTemp == nowPage){
				pagingStr += "<span style='font-size:1.5em;color:blue;'><b>"+intTemp+"</b></span>";
			}
			else {
				pagingStr += "<a href='"+page+"nowPage="+intTemp+"'><span style='font-size:1.5em;color:blue;'>"+intTemp+"</a></span>";
			}
			intTemp = intTemp + 1;
			blockCount = blockCount + 1;
		}
			
		//���� �� ������ �������� ���� ����
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
