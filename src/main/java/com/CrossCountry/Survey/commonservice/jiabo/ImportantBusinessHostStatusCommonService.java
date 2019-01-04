package com.CrossCountry.Survey.commonservice.jiabo;

import java.util.ArrayList;
import java.util.List;

import com.CrossCountry.Survey.mapper.jiabo.ImportantBusinessHostStatusDao;
import com.CrossCountry.Survey.modelvo.jiabo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.CrossCountry.Survey.utils.Log4jUtil;

@Component
public class ImportantBusinessHostStatusCommonService {

    @Autowired
    private ImportantBusinessHostStatusDao importantBusinessHostStatusDao;

    public List<ImportantBusinessHostStatusVO> getImportantBusinessHostStatus(Object object) {
        ImportantBusinessHostStatusVO importantBusinessHostStatusVOOne = new ImportantBusinessHostStatusVO();
        ImportantBusinessHostStatusVO importantBusinessHostStatusVOTwo = new ImportantBusinessHostStatusVO();
        ImportantBusinessHostStatusVO importantBusinessHostStatusVOThree = new ImportantBusinessHostStatusVO();
        List<ImportantBusinessHostStatusVO> importantBusinessHostStatusVOList = new ArrayList<ImportantBusinessHostStatusVO>();
        try {
            List<ImportantBusinessHostStatusPo> importantBusinessHostStatusPoOne = importantBusinessHostStatusDao.getImportantBusinessHostStatusOne();
            importantBusinessHostStatusVOOne.setImportantBusinessHostStatusPoList(importantBusinessHostStatusPoOne);
            List<ImportantBusinessHostStatusPo> importantBusinessHostStatusPoTwo = importantBusinessHostStatusDao.getImportantBusinessHostStatusTwo();
            importantBusinessHostStatusVOTwo.setImportantBusinessHostStatusPoList(importantBusinessHostStatusPoTwo);
            List<ImportantBusinessHostStatusPo> importantBusinessHostStatusPoThree = importantBusinessHostStatusDao.getImportantBusinessHostStatusThree();
            importantBusinessHostStatusVOThree.setImportantBusinessHostStatusPoList(importantBusinessHostStatusPoThree);
            importantBusinessHostStatusVOList.add(importantBusinessHostStatusVOOne);
            importantBusinessHostStatusVOList.add(importantBusinessHostStatusVOTwo);
            importantBusinessHostStatusVOList.add(importantBusinessHostStatusVOThree);
        } catch (Exception e) {
            e.printStackTrace();
            Log4jUtil.error(ImportantBusinessHostStatusCommonService.class, e.getMessage(), e);
        }
        return importantBusinessHostStatusVOList;
    }
}
