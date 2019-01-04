package com.CrossCountry.Survey.commonservice.dongl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.dongl.LeftSecurityFlawDao;
import com.CrossCountry.Survey.modelvo.dongl.LeftSecurityFlawReplyVO;
import com.CrossCountry.Survey.modelvo.dongl.NumOneAreaLeftEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumThreeAreaLeftEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumTwoAreaLeftEntity;

@Component
public class LeftSecurityFlawCommonService {

	@Autowired
	private LeftSecurityFlawDao leftSecurityFlawDao;

	public LeftSecurityFlawReplyVO getLeftSecurityFlaw(Object object) {
		LeftSecurityFlawReplyVO leftSecurityFlawReplyVO = new LeftSecurityFlawReplyVO();
		List<NumOneAreaLeftEntity> numOneAreaLeftEntityList = leftSecurityFlawDao.getNumOneAreaLeftList();
		List<NumTwoAreaLeftEntity> numTwoAreaLeftEntityList = leftSecurityFlawDao.getNumTwoAreaLeftList();
		List<NumThreeAreaLeftEntity> numThreeAreaLeftEntityList = leftSecurityFlawDao.getThreeAreaLeftList();
		leftSecurityFlawReplyVO.setNumOneAreaLeftList(numOneAreaLeftEntityList);
		leftSecurityFlawReplyVO.setNumTwoAreaLeftList(numTwoAreaLeftEntityList);
		leftSecurityFlawReplyVO.setNumThreeAreaLeftList(numThreeAreaLeftEntityList);
		return leftSecurityFlawReplyVO;
	}

}
