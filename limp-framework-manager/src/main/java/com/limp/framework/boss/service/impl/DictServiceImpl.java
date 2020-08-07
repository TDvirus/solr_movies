package com.limp.framework.boss.service.impl;

import com.limp.framework.boss.domain.SolrMovies;
import com.limp.framework.boss.service.DictService;
import com.limp.framework.utils.StrUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.CloudSolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @intro ：
 * @auth ： shinians
 * @time ： 2019/5/29 17:04
 * @website： www.shinians.com
 */
@Service
public class DictServiceImpl implements DictService {

    private  static  final String solrUrl="http://localhost:8983/solr/lp_film";

    /**
     * 根据查询条件获取bean集合
     * @param sql  查询语句
     * @param fqSql  过滤语句
     * @return
     */
    public List<SolrMovies> metaBeansList(String sql, String fqSql){
        //创建Solr的客户端链接对象
        SolrClient solrServer =  new HttpSolrClient.Builder(solrUrl).build();
        //创建solr的查询对象
        SolrQuery sq=new SolrQuery();
        //设置查询条件【1】这里*:*表示获取所有数据，第一个*对应field，第二个对应查询的条件。
        //sq.set("q","item_title:3 AND item_desc:东西  OR item_sell_point:好看" );
        sq.set("q",sql );

        //设置分页【注】
        sq.setStart(0);
        sq.setRows(50);

        //排序【注】   SolrQuery.ORDER.desc   SolrQuery.ORDER.asc
       // sq.addSort("sortFieldName ", SolrQuery.ORDER.asc);

        //设置过滤条件【2】
        if(!StrUtils.isBlank(fqSql)){
            sq.set("fq", fqSql);
//          sq.setFilterQueries("author_name:出山","filesize:[* TO 4000000]");
        }
        //【3】“fl” 只查询指定域  ；是solr返回数据时返回哪些字段;*表示返回所有存在字段，后面加上“score”就表示返回数据时同时返回该条数据匹配的score。
        sq.set("fl", "*,score");
        //设置高亮【4】
        //1.打开开关
        sq.setHighlight(true);
        //2.指定高亮域
        sq.addHighlightField("lp_title");
        //3.前缀
        sq.setHighlightSimplePre("<font>");
//        sq.setHighlightSimplePre("<font style='color:red'>");
        //后缀
        sq.setHighlightSimplePost("</font>");
        QueryResponse qr= null;
        try {
            //执行查询
            qr = solrServer.query(sq);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取查询结果
        SolrDocumentList sds=qr.getResults();
        //获取查询的记录数【5】
        long total=sds.getNumFound();
        System.out.println("数量："+total);

        //获取返回结果的最大得分【6】
        float scoreMax=sds.getMaxScore();
        System.out.println("score："+scoreMax);

        //获取返回结果的偏移量
        long start=sds.getStart();

        List<SolrMovies> metaBeanList =new ArrayList<>();
        for(SolrDocument sd:sds){//默认取出10条记录
            SolrMovies metaBean=new SolrMovies();
            //查询出的结果标题设置高亮
            Map<String, Map<String, List<String>>> highlighting =  qr.getHighlighting();
            List<String > list = highlighting.get(sd.get("id")).get("lp_title");
            String lp_title;
            if(list != null && list.size()>0){
                lp_title = list.get(0);
            }else {
                lp_title = (Object)sd.get("lp_title")+"";
            }
            /******************初始化实体对象****************************/

            String id=(String) sd.getFieldValue("id");
            metaBean.setId(id);
            metaBean.setImgUrl((String) sd.getFieldValue("lp_img_url"));
            metaBean.setCate((Object) sd.getFieldValue("lp_cate")+"");
            metaBean.setCity((Object) sd.getFieldValue("lp_city")+"");
            metaBean.setIntro((Object) sd.getFieldValue("lp_intro")+"");
            metaBean.setProducer((Object) sd.getFieldValue("lp_producer")+"");
            metaBean.setPubdate((Object) sd.getFieldValue("lp_pubdate")+"");
            metaBean.setRole((Object) sd.getFieldValue("lp_role")+"");
            metaBean.setTitle(lp_title);
            //添加到list集合
            metaBeanList.add(metaBean);
        }
        return  metaBeanList;
    }

    public static void main(String[] args) {
        DictServiceImpl dictService=new DictServiceImpl();
//        List<SolrMovies> solrMovies=dictService.metaBeansList("lp_title:*游戏*","");
        List<SolrMovies> solrMovies=dictService.metaBeansList("lp_search_key:*游戏*","");
        System.out.println(solrMovies);
    }
}
