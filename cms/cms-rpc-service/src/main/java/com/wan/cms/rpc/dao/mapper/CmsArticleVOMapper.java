package com.wan.cms.rpc.dao.mapper;

/**
 * 文章VOMapper
 *
 */
public interface CmsArticleVOMapper {

    int up(Integer articleId);

    int down(Integer articleId);

}