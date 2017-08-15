package com.wan.upms.rpc.server.impl;

import com.wan.upms.dao.mapper.UpmsSystemMapper;
import com.wan.upms.dao.model.UpmsSystem;
import com.wan.upms.dao.model.UpmsSystemExample;
import com.wan.upms.rpc.api.UpmsSystemService;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 实现
 * <p>
 * Created by w1992wishes on 2017/8/14.
 */
public class UpmsSystemServiceImpl implements UpmsSystemService {

    private Logger logger = LoggerFactory.getLogger(UpmsSystemServiceImpl.class);

    @Autowired
    private UpmsSystemMapper upmsSystemMapper;

    @Override
    public int countByExample(UpmsSystemExample upmsSystemExample) {
        return upmsSystemMapper.countByExample(upmsSystemExample);
    }

    @Override
    public int deleteByExample(UpmsSystemExample upmsSystemExample) {
        return upmsSystemMapper.deleteByExample(upmsSystemExample);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return upmsSystemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UpmsSystem upmsSystem) {
        return upmsSystemMapper.insert(upmsSystem);
    }

    @Override
    public int insertSelective(UpmsSystem upmsSystem) {
        return upmsSystemMapper.insertSelective(upmsSystem);
    }

    @Override
    public List<UpmsSystem> selectByExampleWithBLOBs(UpmsSystemExample upmsSystemExample) {
        return upmsSystemMapper.selectByExample(upmsSystemExample);
    }

    @Override
    public List<UpmsSystem> selectByExample(UpmsSystemExample upmsSystemExample) {
        return upmsSystemMapper.selectByExample(upmsSystemExample);
    }

    @Override
    public UpmsSystem selectByPrimaryKey(Integer id) {
        return upmsSystemMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(@Param("record") UpmsSystem upmsSystem, @Param("example") UpmsSystemExample upmsSystemExample) {
        return upmsSystemMapper.updateByExampleSelective(upmsSystem, upmsSystemExample);
    }

    @Override
    public int updateByExampleWithBLOBs(@Param("record") UpmsSystem upmsSystem, @Param("example") UpmsSystemExample upmsSystemExample) {
        return upmsSystemMapper.updateByExample(upmsSystem, upmsSystemExample);
    }

    @Override
    public int updateByExample(@Param("record") UpmsSystem upmsSystem, @Param("example") UpmsSystemExample upmsSystemExample) {
        return upmsSystemMapper.updateByExample(upmsSystem, upmsSystemExample);
    }

    @Override
    public int updateByPrimaryKeySelective(UpmsSystem upmsSystem) {
        return upmsSystemMapper.updateByPrimaryKeySelective(upmsSystem);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(UpmsSystem upmsSystem) {
        return upmsSystemMapper.updateByPrimaryKey(upmsSystem);
    }

    @Override
    public int updateByPrimaryKey(UpmsSystem upmsSystem) {
        return upmsSystemMapper.updateByPrimaryKey(upmsSystem);
    }

    // 批量删除
    @Override
    public int deleteByPrimaryKeys(String ids) {
        logger.info("批量删除：ids={}", ids);
        if (StringUtils.isBlank(ids)) {
            return 0;
        }
        String[] idArray = ids.split("-");
        int count = 0;
        for (String id : idArray) {
            if (StringUtils.isBlank(id)) {
                continue;
            }
            try {
                count += upmsSystemMapper.deleteByPrimaryKey(Integer.parseInt(id));
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return count;
    }

}
