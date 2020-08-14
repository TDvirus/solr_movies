# solr_movies
一个由spring cloud搭建的solr电影检索项目。

***

## 运行环境：
* JDK8
* solr-8.5.1
* redis-3.0.504
* idea-2019.1.4

## 项目简介：
	该项目基于Spring Cloud的Finchley.SR2版本构建。模块分为limp-framework（后台管理）、limp-framework-core（工具包）、limp-framework-manager（solr电影检索）。
	该项目通过在输入框里输入关键字，检索之后返回数据库里符合相关条件的电影数据。检索结果还可以通过页面上的类型、年代、地区对搜索结果进行过滤筛选。
	该项目检索性能卓越，可对用户输入的关键词进行实时联想，页面展示示检索结果也在一秒以内。（数据库内存有29750条电影数据）
  （由于SQL文件过大，无法上传）

### 登录界面展示：
![登录界面](https://github.com/TDvirus/solr_movies/blob/master/pic/%E6%88%AA%E5%9B%BE00.png)

### 首页展示：
![首页](https://github.com/TDvirus/solr_movies/blob/master/pic/%E6%88%AA%E5%9B%BE01.png)

### 检索展示：
![检索结果](https://github.com/TDvirus/solr_movies/blob/master/pic/%E6%88%AA%E5%9B%BE02.png)
![检索结果](https://github.com/TDvirus/solr_movies/blob/master/pic/%E6%88%AA%E5%9B%BE03.png)