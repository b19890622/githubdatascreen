package com.CrossCountry.Survey.commonservice.dongl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.mapper.dongl.InputCommonDao;
import com.CrossCountry.Survey.modelvo.dongl.InputAreaEntity;
import com.CrossCountry.Survey.modelvo.dongl.InputAreaNumEntity;
import com.CrossCountry.Survey.modelvo.dongl.InputAreaPercentEntity;
import com.CrossCountry.Survey.modelvo.dongl.InputCommonReplyVO;

@Component
public class InputCommonService {

	@Autowired
	private InputCommonDao inputCommonDao;

	public InputCommonReplyVO getInput(Object object) {
		InputCommonReplyVO inputCommonReplyVO = new InputCommonReplyVO();
		InputAreaNumEntity inputAreaNumEntity = new InputAreaNumEntity();
		InputAreaNumEntity realNumEntity = new InputAreaNumEntity();
		InputAreaPercentEntity realPercentEntity = new InputAreaPercentEntity();
		InputAreaPercentEntity inputAreaPercentEntity = new InputAreaPercentEntity();
		List<InputAreaEntity> inputList = inputCommonDao.getInputAreaEntity();
		if (inputList == null) {
			inputList = new ArrayList<>();
		}
		for (InputAreaEntity i : inputList) {
			if (i.getSarea().equals("0")) {
				inputAreaNumEntity.setNumOneCount(i.getNumCount());
				inputAreaPercentEntity.setNumOnePercent(i.getRate());
			} else if (i.getSarea().equals("1")) {
				inputAreaNumEntity.setNumTwoCount(i.getNumCount());
				inputAreaPercentEntity.setNumTwoPercent(i.getRate());
			} else if (i.getSarea().equals("2")) {
				inputAreaNumEntity.setNumThreeCount(i.getNumCount());
				inputAreaPercentEntity.setNumThreePercent(i.getRate());
			}
		}

		realNumEntity.setNumOneCount(
				inputAreaNumEntity.getNumOneCount() == null ? "0" : inputAreaNumEntity.getNumOneCount());
		realNumEntity.setNumTwoCount(
				inputAreaNumEntity.getNumTwoCount() == null ? "0" : inputAreaNumEntity.getNumTwoCount());
		realNumEntity.setNumThreeCount(
				inputAreaNumEntity.getNumThreeCount() == null ? "0" : inputAreaNumEntity.getNumThreeCount());
		realPercentEntity.setNumOnePercent(
				inputAreaPercentEntity.getNumOnePercent() == null ? "0%" : inputAreaPercentEntity.getNumOnePercent());
		realPercentEntity.setNumTwoPercent(
				inputAreaPercentEntity.getNumTwoPercent() == null ? "0%" : inputAreaPercentEntity.getNumTwoPercent());
		realPercentEntity.setNumThreePercent(
				inputAreaPercentEntity.getNumThreePercent() == null ? "0%" : inputAreaPercentEntity.getNumThreePercent());
		// inputAreaPercentEntity.setNumOnePercent();

		inputCommonReplyVO.setInputAreaNumEntity(realNumEntity);
		inputCommonReplyVO.setInputAreaPercentEntity(realPercentEntity);
		return inputCommonReplyVO;
	}
}
