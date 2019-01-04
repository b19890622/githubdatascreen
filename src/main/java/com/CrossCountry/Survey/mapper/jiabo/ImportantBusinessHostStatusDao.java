package com.CrossCountry.Survey.mapper.jiabo;

import com.CrossCountry.Survey.modelvo.jiabo.ImportantBusinessHostStatusPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ImportantBusinessHostStatusDao {
    @Select("SELECT \n" +
            "        ID AS id,\n" +
            "        IP AS ip,\n" +
            "        NAME  AS name,\n" +
            "        STATE AS status,\n" +
            "        BELONGBUSINESS AS businessId,\n" +
            "        BUSINESSNAME AS businessName,\n" +
            "        BUSINESSTITLE AS businessTitle\n" +
            "FROM \n" +
            "        LSV_IMPORTANTSVRFLAG_VIEW \n" +
            "where \n" +
            "        SECURITYAREA = 0")
    public List<ImportantBusinessHostStatusPo> getImportantBusinessHostStatusOne();
    @Select("SELECT \n" +
            "        ID AS id,\n" +
            "        IP AS ip,\n" +
            "        NAME  AS name,\n" +
            "        STATE AS status,\n" +
            "        BELONGBUSINESS AS businessId,\n" +
            "        BUSINESSNAME AS businessName,\n" +
            "        BUSINESSTITLE AS businessTitle\n" +
            "FROM \n" +
            "        LSV_IMPORTANTSVRFLAG_VIEW \n" +
            "where \n" +
            "        SECURITYAREA = 1")
    public List<ImportantBusinessHostStatusPo> getImportantBusinessHostStatusTwo();
    @Select("SELECT \n" +
            "        ID AS id,\n" +
            "        IP AS ip,\n" +
            "        NAME  AS name,\n" +
            "        STATE AS status,\n" +
            "        BELONGBUSINESS AS businessId,\n" +
            "        BUSINESSNAME AS businessName,\n" +
            "        BUSINESSTITLE AS businessTitle\n" +
            "FROM \n" +
            "        LSV_IMPORTANTSVRFLAG_VIEW \n" +
            "where \n" +
            "        SECURITYAREA = 2")
    public List<ImportantBusinessHostStatusPo> getImportantBusinessHostStatusThree();
}
