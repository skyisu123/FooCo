package com.fooco.FoodTruc.member.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.fooco.FoodTruc.member.dao.SellerDAO;
import com.fooco.FoodTruc.member.vo.SellerVO;

import common.CommonDao;

@Repository
public class SellerDaoImpl extends CommonDao  implements SellerDAO{

	@Override
	public int insertSeller(SellerVO sellerVo) {
		return getSqlSession().getMapper(SellerDAO.class).insertSeller(sellerVo);
	}
	
	//�α��� ó��
	@Override
	public List<SellerVO> sellerLogin(Map<String, Object> parameterMap) {
		return getSqlSession().getMapper(SellerDAO.class).sellerLogin(parameterMap);
	}
	
	@Override
	public List<SellerVO> selectDetailMember(String sellerId) {
		return getSqlSession().getMapper(SellerDAO.class).selectDetailMember(sellerId);
	}
	
	//==============================
		//ȸ������ ����
	@Override
		public int updateInfoSeller(SellerVO sellerVo) {
			return getSqlSession().getMapper(SellerDAO.class).updateInfoSeller(sellerVo);
		}
	//==============================
	
	///////////////////////////////// Find Id By Young Min //////////////////////////////////////
		
	@Override
	public String selectFindIdByPhone(Map<String, Object> map) {
		return getSqlSession().getMapper(SellerDAO.class).selectFindIdByPhone(map);
	}
	
	@Override
	public String selectFindIdByEmail(Map<String, Object> map) {
		return getSqlSession().getMapper(SellerDAO.class).selectFindIdByEmail(map);
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////

		
	///////////////////////////////// ��й�ȣ ã�� 9/3 //////////////////////////////////////
	
	@Override
	public String selectFindPw(Map<String, Object> map) {
	return getSqlSession().getMapper(SellerDAO.class).selectFindPw(map);
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public int selectCheckPw(String memberId, String memberPw) {
	return getSqlSession().getMapper(SellerDAO.class).selectCheckPw(memberId,memberPw);
	}
	
	@Override
	public int UpdateNewPw(String memberId, String newMemberPw) {
	return getSqlSession().getMapper(SellerDAO.class).UpdateNewPw(memberId, newMemberPw);
	}




}
