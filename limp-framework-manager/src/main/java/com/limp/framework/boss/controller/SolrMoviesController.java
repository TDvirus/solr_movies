package com.limp.framework.boss.controller;


import com.limp.framework.boss.domain.SolrMovies;
import com.limp.framework.boss.service.DictService;
import com.limp.framework.boss.service.SolrMoviesService;
import com.limp.framework.core.bean.Pager;
import com.limp.framework.core.bean.Result;
import com.limp.framework.core.bean.ResultCode;
import com.limp.framework.core.constant.ResultMsg;
import com.limp.framework.utils.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* 描述：SolrMovies控制层
* @author Generate
* @date 2020-04-16 14:12:48
*/
@RestController
@RequestMapping("/solr")
public class SolrMoviesController {
    private static Logger log = LoggerFactory.getLogger(SolrMoviesController.class);

    //默认页数
    private static final Integer DEFAULT_ROW = 100;

    @Autowired
    private SolrMoviesService solrMoviesService;

    @Autowired
    DictService dictService;
    /**
    * Title: POST saveSolrMovies
    * Description: SolrMovies 保存接口
    * @param @see SolrMovies
    * @param @return: success or error
    * @author Generate
    * date 2020-04-16 14:12:48
    */
    @RequestMapping(value = "/solrMovies",method= RequestMethod.POST)
    public String saveSolrMovies(@RequestBody SolrMovies solrMovies) {
        log.debug("save SolrMovies");
        if(solrMoviesService.save(solrMovies)){
            return Result.Success().getJson();
        }
         return Result.Error( ResultMsg.ERROR).getJson();
    }

    /**
    * Title: PUT updateSolrMovies
    * Description: SolrMovies 修改接口
    * @param @see SolrMovies
    * @param @return: success or error
    * @author Generate
    * date 2020-04-16 14:12:48
    */
    @RequestMapping(value = "/solrMovies",method= RequestMethod.PUT)
    public String updateSolrMovies(SolrMovies solrMovies) {
        log.debug("update SolrMovies");
        if(solrMoviesService.update(solrMovies)){
             return Result.Success().getJson();
        }
         return Result.Error( ResultMsg.ERROR).getJson();
    }

    /**
    * Title: DELETE deleteSolrMovies
    * Description: SolrMovies 删除接口
    * @param id ： 主键信息
    * @param @return: success or error
    * @author Generate
    * date 2020-04-16 14:12:48
    */
    @DeleteMapping("/solrMovies/{id}")
    public String deleteSolrMovies(@PathVariable("id") String id) {
        log.debug("delete SolrMovies -->" + id);
        return solrMoviesService.delete(id) ? Result.getInstance(ResultCode.SUCCESS.toString(), "success", "", "").getJson()
            : Result.getInstance(ResultCode.ERROR.toString(), "error", "", "").getJson();
    }

    /**
    * Title: GET findSolrMovies
    * Description: SolrMovies 根据id查询接口
    * @param id ： 主键信息
    * @param @return: @see SolrMovies
    * @author Generate
    * date 2020-04-16 14:12:48
    */
    @GetMapping("/solrMovies/{id}")
    public String findSolrMoviesById(@PathVariable("id") String id) {
        log.debug("findById SolrMovies -->" + id);
        SolrMovies solrMovies = solrMoviesService.get(id);
        return Result.getInstance(ResultCode.SUCCESS.toString(), "success", solrMovies, "").getJson();
    }

    /**
    * Title: GET selectSolrMoviesPageList
    * Description: SolrMovies条件查询/分页接口
    * @param solrMovies @see SolrMovies
    * @param pager 分页对象 默认每页显示10条 @see Pager
    * @param @return: @see SolrMovies
    * @author Generate
    * date 2020-04-16 14:12:48
    */
    @RequestMapping(value = "/solrMoviess",method= RequestMethod.GET)
    public String selectSolrMoviesPageList(SolrMovies solrMovies, Pager pager) {
        log.debug("selectSolrMoviesPageList");
        pager = new Pager(pager.getPage(), !StrUtils.isBlank(pager.getRows())
            && 0 != pager.getRows() ? pager.getRows() : DEFAULT_ROW);
        Pager<SolrMovies> solrMoviesPager = solrMoviesService.getPageList(solrMovies, pager);
        return Result.getInstance(ResultCode.SUCCESS.toString(), "success", solrMoviesPager, "").getJson();
    }

    /**
     * solr查询
     * @param solrMovies
     * @param pager
     * @return
     */
    @RequestMapping(value="solrMoviesS" ,method= RequestMethod.GET)
    public String searchDic(SolrMovies solrMovies, Pager pager) {
        //定义查询语句
        String sql = "";
        String fqSql = "";
        if (!StrUtils.isBlank(solrMovies.getTitle())) {
            sql = "lp_title:*" + solrMovies.getTitle()+"*";
        }
        //筛选 |类型
        if (!StrUtils.isBlank(solrMovies.getCate())) {
            fqSql = "lp_cate:" + solrMovies.getCate();
        }
        //年代
        if (!StrUtils.isBlank(solrMovies.getPubdate())) {
            fqSql = "lp_pubdate:*" + solrMovies.getPubdate()+"*";
        }
        //地区
        if (!StrUtils.isBlank(solrMovies.getCity())) {
            fqSql = "lp_city:*" + solrMovies.getCity()+"*";
        }
        //md_name加权展示
        List<SolrMovies> metaBeanList = dictService.metaBeansList(sql , fqSql);
        //如果为空，则在copy中查询
        if (metaBeanList.size() == 0) {
            metaBeanList = dictService.metaBeansList("lp_search_key:" + solrMovies.getTitle(), fqSql);
        }
        pager.setDataList(metaBeanList);
        return Result.getInstance(ResultCode.SUCCESS.toString(), "success", pager, "").getJson();
    }

}
