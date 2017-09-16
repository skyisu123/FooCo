package com.fooco.FoodTruc.query.dao.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import com.fooco.FoodTruc.query.dao.SellerQueryDAO;
import com.fooco.FoodTruc.query.dao.UserQueryDAO;
import com.fooco.FoodTruc.query.vo.ReplyQueryVO;
import com.fooco.FoodTruc.query.vo.SellerQueryVO;
import com.fooco.FoodTruc.query.vo.UserQueryVO;

import common.CommonDao;

@Repository
public class SellerQueryDAOImpl extends CommonDao implements SellerQueryDAO{
	
	
	@Override
	public void querySend(Map<String, Object> map) {
		getSqlSession().getMapper(SellerQueryDAO.class).
			querySend(map);
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
	public ReplyQueryVO replyList(String queryCode) {
		return getSqlSession().getMapper(SellerQueryDAO.class).replyList(queryCode);
	}
	
	@Override
	public List<ReplyQueryVO> showReplyQuery(Map<String, Object> paramMap) {
		return getSqlSession().getMapper(SellerQueryDAO.class).showReplyQuery(paramMap);
	}
		
	@Override
	public int getReplyTotalCount(String sellerId) {
		return getSqlSession().getMapper(SellerQueryDAO.class).getReplyTotalCount(sellerId);
	}
	
	@Override
	public int getTotalCount(String sellerId) {
		return getSqlSession().getMapper(SellerQueryDAO.class).getTotalCount(sellerId);
	}
	
	@Override
	public List<SellerQueryVO> showList(Map<String, Object> paramMap) {
		System.out.println("�������̴�");
		return getSqlSession().getMapper(SellerQueryDAO.class).showList(paramMap);
	}
	@Override
	public int getReceviceTotalCount(String sellerId, String read) {
		return getSqlSession().getMapper(SellerQueryDAO.class).getReceviceTotalCount(sellerId,read);
	}
	@Override
	public List<UserQueryVO> showReceiveList(Map<String, Object> paramMap) {
		return getSqlSession().getMapper(SellerQueryDAO.class).showReceiveList(paramMap);
	}
	
	@Override
	public UserQueryVO showReceiveView(String queryNum) {
		return getSqlSession().getMapper(SellerQueryDAO.class).showReceiveView(queryNum);
	}
	
	@Override
	public SellerQueryVO showView(String queryNum) {
		return getSqlSession().getMapper(SellerQueryDAO.class).showView(queryNum);
	}
	
	@Override
	public void deleteSellerQuery(String queryNum) {
		getSqlSession().getMapper(SellerQueryDAO.class).deleteSellerQuery(queryNum);
	}
	@Override
	public void deleteUserQuery(String queryNum) {
		getSqlSession().getMapper(SellerQueryDAO.class).deleteUserQuery(queryNum);
	}
	
	@Override
	public void answerAction(Map<String, Object> paramMap) {
		getSqlSession().getMapper(SellerQueryDAO.class).answerAction(paramMap);
	}
	
	@Override
	public void editSellerAction(Map<String, Object> paramMap) {
		getSqlSession().getMapper(SellerQueryDAO.class).editSellerAction(paramMap);
	}
	@Override
	public void editUserAction(Map<String, Object> paramMap) {
		getSqlSession().getMapper(SellerQueryDAO.class).editUserAction(paramMap);
	}
}
