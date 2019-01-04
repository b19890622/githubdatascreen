package com.CrossCountry.Survey.commonservice.dongl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.dongl.RightSecurityFlawDao;
import com.CrossCountry.Survey.modelvo.dongl.NumOneAreaRightEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumThreeAreaRightEntity;
import com.CrossCountry.Survey.modelvo.dongl.NumTwoAreaRightEntity;
import com.CrossCountry.Survey.modelvo.dongl.RightSecurityFlawReplyVO;

@Component
public class RightSecurityFlawCommonService {

	@Autowired
	private RightSecurityFlawDao rightSecurityFlawDao;

	public RightSecurityFlawReplyVO getRightSecurityFlaw(Object object) {
		RightSecurityFlawReplyVO rightSecurityFlawReplyVO = new RightSecurityFlawReplyVO();
		List<NumOneAreaRightEntity> numOneAreaRightEntityList = rightSecurityFlawDao.getNumOneAreaRightList();
		List<NumTwoAreaRightEntity> numTwoAreaRightEntityList = rightSecurityFlawDao.getNumTwoAreaRightList();
		List<NumThreeAreaRightEntity> numThreeAreaRightEntityList = rightSecurityFlawDao.getThreeAreaRightList();
		rightSecurityFlawReplyVO.setNumOneAreaRightList(numOneAreaRightEntityList);
		rightSecurityFlawReplyVO.setNumTwoAreaRightList(numTwoAreaRightEntityList);
		rightSecurityFlawReplyVO.setNumThreeAreaRightList(numThreeAreaRightEntityList);
		return rightSecurityFlawReplyVO;
	}

}
