package com.limp.framework.boss.service;


import com.limp.framework.boss.domain.SolrMovies;

import java.util.List;

/**
 * @intro ：维护Solr 电影服务
 * @auth ： shinians
 * @time ： 2019/5/29 17:02
 * @website： www.shinians.com
 */
public interface DictService {

    /**
     * 根据条件查询MetaBean集合
     * @param sql
     * @param fqSql
     * @return
     */
    public List<SolrMovies> metaBeansList(String sql, String fqSql);
}
