package com.wan.cms.mapper;

import com.wan.cms.model.CmsTag;
import com.wan.cms.model.CmsTagExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CmsTagMapper {
    int countByExample(CmsTagExample example);

    int deleteByExample(CmsTagExample example);

    int deleteByPrimaryKey(Integer tagId);

    int insert(CmsTag record);

    int insertSelective(CmsTag record);

    List<CmsTag> selectByExample(CmsTagExample example);

    int updateByExampleSelective(@Param("record") CmsTag record, @Param("example") CmsTagExample example);

    int updateByExample(@Param("record") CmsTag record, @Param("example") CmsTagExample example);
}