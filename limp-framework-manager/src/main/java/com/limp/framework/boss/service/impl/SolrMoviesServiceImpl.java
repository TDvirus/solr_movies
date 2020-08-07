package com.limp.framework.boss.service.impl;

import com.limp.framework.boss.domain.SolrMovies;
import com.limp.framework.boss.domain.SolrMoviesExample;
import com.limp.framework.boss.mapper.oracle.SolrMoviesMapper;
import com.limp.framework.boss.service.SolrMoviesService;
import com.limp.framework.core.bean.Pager;
import com.limp.framework.utils.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* 描述：SolrMovies 服务实现层
* @author Generate
* @date 2020-04-16 14:12:48
*/
@Service
public class SolrMoviesServiceImpl implements SolrMoviesService {
private static Logger log = LoggerFactory.getLogger(SolrMoviesServiceImpl.class);

    /**
     * solr服务
     */
    @Autowired
private SolrMoviesMapper solrMoviesMapper;

    /**
    * 描述：SolrMovies 新增接口
    * solrMovies : 新增对象
    * @author Generate
    * @date 2020-04-16 14:12:48
    */
    @Override
    public boolean save(SolrMovies solrMovies) {
        if(StrUtils.isBlank(solrMovies.getId())){
           solrMovies.setId(StrUtils.randomUUID());
        }
        log.debug("新增记录{};id->{}","solrMovies", solrMovies.getId());

        Date date = new Date();
        solrMovies.setIdt(date);
        return  solrMoviesMapper.insertSelective(solrMovies) == 1 ? true : false;

    }

    /**
    * 描述：SolrMovies 根据id查询
    * id ： 主键
    * @author Generate
    * @date 2020-04-16 14:12:48
    */
    @Override
    public SolrMovies get(String id){
        return solrMoviesMapper.selectByPrimaryKey(id);
    }

   /**
    * 描述：SolrMovies 修改接口
    * solrMovies ： 修改对象 需包含主键信息
    * @author Generate
    * @date 2020-04-16 14:12:48
    */
    @Override
    public boolean update(SolrMovies solrMovies){
//       solrMovies.setUdt(new Date());
       return solrMoviesMapper.updateByPrimaryKeySelective(solrMovies)==1?true:false;

    }
 /**
    * 描述：SolrMovies 删除接口
    * id ： 被删除对象id
    * @author Generate
    * @date 2020-04-16 14:12:48
    */
    @Override
    public boolean delete(String id){
        //应该逻辑删除:
        return solrMoviesMapper.deleteByPrimaryKey(id)== 1?true:false;
    }

    @Override
    public List<SolrMovies> getList(SolrMovies solrMovies){
        SolrMoviesExample example = new SolrMoviesExample();
    
        SolrMoviesExample.Criteria criteria = example.createCriteria();
        //查询条件组装---start----
    
    
    
        //查询条件组装---stop----
        List<SolrMovies>  solrMoviess  = solrMoviesMapper.selectByExample(example);
        return solrMoviess;
    }

    @Override
    public Pager<SolrMovies> getPageList(SolrMovies domain, Pager pager){
        log.debug("根据条件,查询{}列表【分页方法】","SolrMovies");
    
         //初始化Example
        SolrMoviesExample example = new SolrMoviesExample();
        //设置分页对象
        example.setPage(pager);
        //创建查询条件
        SolrMoviesExample.Criteria criteria = example.createCriteria();
        //查询条件组装---start----
    
        //标题模糊查询
        if (!StrUtils.isBlank(domain.getTitle())) {
            criteria.andTitleLike("%" + domain.getTitle() + "%");
        }
        if (!StrUtils.isBlank(domain.getIntro())) {
            criteria.andIntroLike("%" + domain.getIntro() + "%");
        }
        //分类
        if (!StrUtils.isBlank(domain.getCate())) {
            criteria.andCateLike("%" + domain.getCate() + "%");
        }
        //发布时间
        if (!StrUtils.isBlank(domain.getPubdate())) {
            criteria.andPubdateLike("%" + domain.getPubdate() + "%");
        }
        //地区
        if (!StrUtils.isBlank(domain.getCity())) {
            criteria.andCityEqualTo(domain.getCity());
        }

    //查询条件组装---stop----
        List<SolrMovies>  solrMoviess  = solrMoviesMapper.selectByExample(example);
        pager.setPagerInfo(solrMoviess,solrMoviesMapper.countByExample(example));
        return pager;
    }

}