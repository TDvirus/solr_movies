package com.limp.framework.boss.domain;

import com.limp.framework.core.bean.Pager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SolrMoviesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Pager page;

    public SolrMoviesExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setPage(Pager page) {
        this.page=page;
    }

    public Pager getPage() {
        return page;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNull() {
            addCriterion("IMG_URL is null");
            return (Criteria) this;
        }

        public Criteria andImgUrlIsNotNull() {
            addCriterion("IMG_URL is not null");
            return (Criteria) this;
        }

        public Criteria andImgUrlEqualTo(String value) {
            addCriterion("IMG_URL =", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotEqualTo(String value) {
            addCriterion("IMG_URL <>", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThan(String value) {
            addCriterion("IMG_URL >", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlGreaterThanOrEqualTo(String value) {
            addCriterion("IMG_URL >=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThan(String value) {
            addCriterion("IMG_URL <", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLessThanOrEqualTo(String value) {
            addCriterion("IMG_URL <=", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlLike(String value) {
            addCriterion("IMG_URL like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotLike(String value) {
            addCriterion("IMG_URL not like", value, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlIn(List<String> values) {
            addCriterion("IMG_URL in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotIn(List<String> values) {
            addCriterion("IMG_URL not in", values, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlBetween(String value1, String value2) {
            addCriterion("IMG_URL between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andImgUrlNotBetween(String value1, String value2) {
            addCriterion("IMG_URL not between", value1, value2, "imgUrl");
            return (Criteria) this;
        }

        public Criteria andUrlIsNull() {
            addCriterion("URL is null");
            return (Criteria) this;
        }

        public Criteria andUrlIsNotNull() {
            addCriterion("URL is not null");
            return (Criteria) this;
        }

        public Criteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");
            return (Criteria) this;
        }

        public Criteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");
            return (Criteria) this;
        }

        public Criteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");
            return (Criteria) this;
        }

        public Criteria andIntroIsNull() {
            addCriterion("INTRO is null");
            return (Criteria) this;
        }

        public Criteria andIntroIsNotNull() {
            addCriterion("INTRO is not null");
            return (Criteria) this;
        }

        public Criteria andIntroEqualTo(String value) {
            addCriterion("INTRO =", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotEqualTo(String value) {
            addCriterion("INTRO <>", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThan(String value) {
            addCriterion("INTRO >", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroGreaterThanOrEqualTo(String value) {
            addCriterion("INTRO >=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThan(String value) {
            addCriterion("INTRO <", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLessThanOrEqualTo(String value) {
            addCriterion("INTRO <=", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroLike(String value) {
            addCriterion("INTRO like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotLike(String value) {
            addCriterion("INTRO not like", value, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroIn(List<String> values) {
            addCriterion("INTRO in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotIn(List<String> values) {
            addCriterion("INTRO not in", values, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroBetween(String value1, String value2) {
            addCriterion("INTRO between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andIntroNotBetween(String value1, String value2) {
            addCriterion("INTRO not between", value1, value2, "intro");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("CITY is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("CITY is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("CITY =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("CITY <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("CITY >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("CITY >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("CITY <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("CITY <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("CITY like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("CITY not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("CITY in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("CITY not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("CITY between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("CITY not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andPubdateIsNull() {
            addCriterion("PUBDATE is null");
            return (Criteria) this;
        }

        public Criteria andPubdateIsNotNull() {
            addCriterion("PUBDATE is not null");
            return (Criteria) this;
        }

        public Criteria andPubdateEqualTo(String value) {
            addCriterion("PUBDATE =", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateNotEqualTo(String value) {
            addCriterion("PUBDATE <>", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateGreaterThan(String value) {
            addCriterion("PUBDATE >", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateGreaterThanOrEqualTo(String value) {
            addCriterion("PUBDATE >=", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateLessThan(String value) {
            addCriterion("PUBDATE <", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateLessThanOrEqualTo(String value) {
            addCriterion("PUBDATE <=", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateLike(String value) {
            addCriterion("PUBDATE like", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateNotLike(String value) {
            addCriterion("PUBDATE not like", value, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateIn(List<String> values) {
            addCriterion("PUBDATE in", values, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateNotIn(List<String> values) {
            addCriterion("PUBDATE not in", values, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateBetween(String value1, String value2) {
            addCriterion("PUBDATE between", value1, value2, "pubdate");
            return (Criteria) this;
        }

        public Criteria andPubdateNotBetween(String value1, String value2) {
            addCriterion("PUBDATE not between", value1, value2, "pubdate");
            return (Criteria) this;
        }

        public Criteria andCateIsNull() {
            addCriterion("CATE is null");
            return (Criteria) this;
        }

        public Criteria andCateIsNotNull() {
            addCriterion("CATE is not null");
            return (Criteria) this;
        }

        public Criteria andCateEqualTo(String value) {
            addCriterion("CATE =", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateNotEqualTo(String value) {
            addCriterion("CATE <>", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateGreaterThan(String value) {
            addCriterion("CATE >", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateGreaterThanOrEqualTo(String value) {
            addCriterion("CATE >=", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateLessThan(String value) {
            addCriterion("CATE <", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateLessThanOrEqualTo(String value) {
            addCriterion("CATE <=", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateLike(String value) {
            addCriterion("CATE like", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateNotLike(String value) {
            addCriterion("CATE not like", value, "cate");
            return (Criteria) this;
        }

        public Criteria andCateIn(List<String> values) {
            addCriterion("CATE in", values, "cate");
            return (Criteria) this;
        }

        public Criteria andCateNotIn(List<String> values) {
            addCriterion("CATE not in", values, "cate");
            return (Criteria) this;
        }

        public Criteria andCateBetween(String value1, String value2) {
            addCriterion("CATE between", value1, value2, "cate");
            return (Criteria) this;
        }

        public Criteria andCateNotBetween(String value1, String value2) {
            addCriterion("CATE not between", value1, value2, "cate");
            return (Criteria) this;
        }

        public Criteria andProducerIsNull() {
            addCriterion("PRODUCER is null");
            return (Criteria) this;
        }

        public Criteria andProducerIsNotNull() {
            addCriterion("PRODUCER is not null");
            return (Criteria) this;
        }

        public Criteria andProducerEqualTo(String value) {
            addCriterion("PRODUCER =", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerNotEqualTo(String value) {
            addCriterion("PRODUCER <>", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerGreaterThan(String value) {
            addCriterion("PRODUCER >", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerGreaterThanOrEqualTo(String value) {
            addCriterion("PRODUCER >=", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerLessThan(String value) {
            addCriterion("PRODUCER <", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerLessThanOrEqualTo(String value) {
            addCriterion("PRODUCER <=", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerLike(String value) {
            addCriterion("PRODUCER like", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerNotLike(String value) {
            addCriterion("PRODUCER not like", value, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerIn(List<String> values) {
            addCriterion("PRODUCER in", values, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerNotIn(List<String> values) {
            addCriterion("PRODUCER not in", values, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerBetween(String value1, String value2) {
            addCriterion("PRODUCER between", value1, value2, "producer");
            return (Criteria) this;
        }

        public Criteria andProducerNotBetween(String value1, String value2) {
            addCriterion("PRODUCER not between", value1, value2, "producer");
            return (Criteria) this;
        }

        public Criteria andRoleIsNull() {
            addCriterion("ROLE is null");
            return (Criteria) this;
        }

        public Criteria andRoleIsNotNull() {
            addCriterion("ROLE is not null");
            return (Criteria) this;
        }

        public Criteria andRoleEqualTo(String value) {
            addCriterion("ROLE =", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotEqualTo(String value) {
            addCriterion("ROLE <>", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThan(String value) {
            addCriterion("ROLE >", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE >=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThan(String value) {
            addCriterion("ROLE <", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLessThanOrEqualTo(String value) {
            addCriterion("ROLE <=", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleLike(String value) {
            addCriterion("ROLE like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotLike(String value) {
            addCriterion("ROLE not like", value, "role");
            return (Criteria) this;
        }

        public Criteria andRoleIn(List<String> values) {
            addCriterion("ROLE in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotIn(List<String> values) {
            addCriterion("ROLE not in", values, "role");
            return (Criteria) this;
        }

        public Criteria andRoleBetween(String value1, String value2) {
            addCriterion("ROLE between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andRoleNotBetween(String value1, String value2) {
            addCriterion("ROLE not between", value1, value2, "role");
            return (Criteria) this;
        }

        public Criteria andReadNumIsNull() {
            addCriterion("READ_NUM is null");
            return (Criteria) this;
        }

        public Criteria andReadNumIsNotNull() {
            addCriterion("READ_NUM is not null");
            return (Criteria) this;
        }

        public Criteria andReadNumEqualTo(Integer value) {
            addCriterion("READ_NUM =", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotEqualTo(Integer value) {
            addCriterion("READ_NUM <>", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumGreaterThan(Integer value) {
            addCriterion("READ_NUM >", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("READ_NUM >=", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumLessThan(Integer value) {
            addCriterion("READ_NUM <", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumLessThanOrEqualTo(Integer value) {
            addCriterion("READ_NUM <=", value, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumIn(List<Integer> values) {
            addCriterion("READ_NUM in", values, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotIn(List<Integer> values) {
            addCriterion("READ_NUM not in", values, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumBetween(Integer value1, Integer value2) {
            addCriterion("READ_NUM between", value1, value2, "readNum");
            return (Criteria) this;
        }

        public Criteria andReadNumNotBetween(Integer value1, Integer value2) {
            addCriterion("READ_NUM not between", value1, value2, "readNum");
            return (Criteria) this;
        }

        public Criteria andMvTypeIsNull() {
            addCriterion("MV_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andMvTypeIsNotNull() {
            addCriterion("MV_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andMvTypeEqualTo(String value) {
            addCriterion("MV_TYPE =", value, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTypeNotEqualTo(String value) {
            addCriterion("MV_TYPE <>", value, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTypeGreaterThan(String value) {
            addCriterion("MV_TYPE >", value, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTypeGreaterThanOrEqualTo(String value) {
            addCriterion("MV_TYPE >=", value, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTypeLessThan(String value) {
            addCriterion("MV_TYPE <", value, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTypeLessThanOrEqualTo(String value) {
            addCriterion("MV_TYPE <=", value, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTypeLike(String value) {
            addCriterion("MV_TYPE like", value, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTypeNotLike(String value) {
            addCriterion("MV_TYPE not like", value, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTypeIn(List<String> values) {
            addCriterion("MV_TYPE in", values, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTypeNotIn(List<String> values) {
            addCriterion("MV_TYPE not in", values, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTypeBetween(String value1, String value2) {
            addCriterion("MV_TYPE between", value1, value2, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTypeNotBetween(String value1, String value2) {
            addCriterion("MV_TYPE not between", value1, value2, "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTagIsNull() {
            addCriterion("MV_TAG is null");
            return (Criteria) this;
        }

        public Criteria andMvTagIsNotNull() {
            addCriterion("MV_TAG is not null");
            return (Criteria) this;
        }

        public Criteria andMvTagEqualTo(String value) {
            addCriterion("MV_TAG =", value, "mvTag");
            return (Criteria) this;
        }

        public Criteria andMvTagNotEqualTo(String value) {
            addCriterion("MV_TAG <>", value, "mvTag");
            return (Criteria) this;
        }

        public Criteria andMvTagGreaterThan(String value) {
            addCriterion("MV_TAG >", value, "mvTag");
            return (Criteria) this;
        }

        public Criteria andMvTagGreaterThanOrEqualTo(String value) {
            addCriterion("MV_TAG >=", value, "mvTag");
            return (Criteria) this;
        }

        public Criteria andMvTagLessThan(String value) {
            addCriterion("MV_TAG <", value, "mvTag");
            return (Criteria) this;
        }

        public Criteria andMvTagLessThanOrEqualTo(String value) {
            addCriterion("MV_TAG <=", value, "mvTag");
            return (Criteria) this;
        }

        public Criteria andMvTagLike(String value) {
            addCriterion("MV_TAG like", value, "mvTag");
            return (Criteria) this;
        }

        public Criteria andMvTagNotLike(String value) {
            addCriterion("MV_TAG not like", value, "mvTag");
            return (Criteria) this;
        }

        public Criteria andMvTagIn(List<String> values) {
            addCriterion("MV_TAG in", values, "mvTag");
            return (Criteria) this;
        }

        public Criteria andMvTagNotIn(List<String> values) {
            addCriterion("MV_TAG not in", values, "mvTag");
            return (Criteria) this;
        }

        public Criteria andMvTagBetween(String value1, String value2) {
            addCriterion("MV_TAG between", value1, value2, "mvTag");
            return (Criteria) this;
        }

        public Criteria andMvTagNotBetween(String value1, String value2) {
            addCriterion("MV_TAG not between", value1, value2, "mvTag");
            return (Criteria) this;
        }

        public Criteria andIdtIsNull() {
            addCriterion("IDT is null");
            return (Criteria) this;
        }

        public Criteria andIdtIsNotNull() {
            addCriterion("IDT is not null");
            return (Criteria) this;
        }

        public Criteria andIdtEqualTo(Date value) {
            addCriterion("IDT =", value, "idt");
            return (Criteria) this;
        }

        public Criteria andIdtNotEqualTo(Date value) {
            addCriterion("IDT <>", value, "idt");
            return (Criteria) this;
        }

        public Criteria andIdtGreaterThan(Date value) {
            addCriterion("IDT >", value, "idt");
            return (Criteria) this;
        }

        public Criteria andIdtGreaterThanOrEqualTo(Date value) {
            addCriterion("IDT >=", value, "idt");
            return (Criteria) this;
        }

        public Criteria andIdtLessThan(Date value) {
            addCriterion("IDT <", value, "idt");
            return (Criteria) this;
        }

        public Criteria andIdtLessThanOrEqualTo(Date value) {
            addCriterion("IDT <=", value, "idt");
            return (Criteria) this;
        }

        public Criteria andIdtIn(List<Date> values) {
            addCriterion("IDT in", values, "idt");
            return (Criteria) this;
        }

        public Criteria andIdtNotIn(List<Date> values) {
            addCriterion("IDT not in", values, "idt");
            return (Criteria) this;
        }

        public Criteria andIdtBetween(Date value1, Date value2) {
            addCriterion("IDT between", value1, value2, "idt");
            return (Criteria) this;
        }

        public Criteria andIdtNotBetween(Date value1, Date value2) {
            addCriterion("IDT not between", value1, value2, "idt");
            return (Criteria) this;
        }

        public Criteria andRc4IsNull() {
            addCriterion("RC4 is null");
            return (Criteria) this;
        }

        public Criteria andRc4IsNotNull() {
            addCriterion("RC4 is not null");
            return (Criteria) this;
        }

        public Criteria andRc4EqualTo(String value) {
            addCriterion("RC4 =", value, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc4NotEqualTo(String value) {
            addCriterion("RC4 <>", value, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc4GreaterThan(String value) {
            addCriterion("RC4 >", value, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc4GreaterThanOrEqualTo(String value) {
            addCriterion("RC4 >=", value, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc4LessThan(String value) {
            addCriterion("RC4 <", value, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc4LessThanOrEqualTo(String value) {
            addCriterion("RC4 <=", value, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc4Like(String value) {
            addCriterion("RC4 like", value, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc4NotLike(String value) {
            addCriterion("RC4 not like", value, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc4In(List<String> values) {
            addCriterion("RC4 in", values, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc4NotIn(List<String> values) {
            addCriterion("RC4 not in", values, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc4Between(String value1, String value2) {
            addCriterion("RC4 between", value1, value2, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc4NotBetween(String value1, String value2) {
            addCriterion("RC4 not between", value1, value2, "rc4");
            return (Criteria) this;
        }

        public Criteria andRc1IsNull() {
            addCriterion("RC1 is null");
            return (Criteria) this;
        }

        public Criteria andRc1IsNotNull() {
            addCriterion("RC1 is not null");
            return (Criteria) this;
        }

        public Criteria andRc1EqualTo(String value) {
            addCriterion("RC1 =", value, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc1NotEqualTo(String value) {
            addCriterion("RC1 <>", value, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc1GreaterThan(String value) {
            addCriterion("RC1 >", value, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc1GreaterThanOrEqualTo(String value) {
            addCriterion("RC1 >=", value, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc1LessThan(String value) {
            addCriterion("RC1 <", value, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc1LessThanOrEqualTo(String value) {
            addCriterion("RC1 <=", value, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc1Like(String value) {
            addCriterion("RC1 like", value, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc1NotLike(String value) {
            addCriterion("RC1 not like", value, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc1In(List<String> values) {
            addCriterion("RC1 in", values, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc1NotIn(List<String> values) {
            addCriterion("RC1 not in", values, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc1Between(String value1, String value2) {
            addCriterion("RC1 between", value1, value2, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc1NotBetween(String value1, String value2) {
            addCriterion("RC1 not between", value1, value2, "rc1");
            return (Criteria) this;
        }

        public Criteria andRc2IsNull() {
            addCriterion("RC2 is null");
            return (Criteria) this;
        }

        public Criteria andRc2IsNotNull() {
            addCriterion("RC2 is not null");
            return (Criteria) this;
        }

        public Criteria andRc2EqualTo(String value) {
            addCriterion("RC2 =", value, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc2NotEqualTo(String value) {
            addCriterion("RC2 <>", value, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc2GreaterThan(String value) {
            addCriterion("RC2 >", value, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc2GreaterThanOrEqualTo(String value) {
            addCriterion("RC2 >=", value, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc2LessThan(String value) {
            addCriterion("RC2 <", value, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc2LessThanOrEqualTo(String value) {
            addCriterion("RC2 <=", value, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc2Like(String value) {
            addCriterion("RC2 like", value, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc2NotLike(String value) {
            addCriterion("RC2 not like", value, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc2In(List<String> values) {
            addCriterion("RC2 in", values, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc2NotIn(List<String> values) {
            addCriterion("RC2 not in", values, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc2Between(String value1, String value2) {
            addCriterion("RC2 between", value1, value2, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc2NotBetween(String value1, String value2) {
            addCriterion("RC2 not between", value1, value2, "rc2");
            return (Criteria) this;
        }

        public Criteria andRc3IsNull() {
            addCriterion("RC3 is null");
            return (Criteria) this;
        }

        public Criteria andRc3IsNotNull() {
            addCriterion("RC3 is not null");
            return (Criteria) this;
        }

        public Criteria andRc3EqualTo(String value) {
            addCriterion("RC3 =", value, "rc3");
            return (Criteria) this;
        }

        public Criteria andRc3NotEqualTo(String value) {
            addCriterion("RC3 <>", value, "rc3");
            return (Criteria) this;
        }

        public Criteria andRc3GreaterThan(String value) {
            addCriterion("RC3 >", value, "rc3");
            return (Criteria) this;
        }

        public Criteria andRc3GreaterThanOrEqualTo(String value) {
            addCriterion("RC3 >=", value, "rc3");
            return (Criteria) this;
        }

        public Criteria andRc3LessThan(String value) {
            addCriterion("RC3 <", value, "rc3");
            return (Criteria) this;
        }

        public Criteria andRc3LessThanOrEqualTo(String value) {
            addCriterion("RC3 <=", value, "rc3");
            return (Criteria) this;
        }

        public Criteria andRc3Like(String value) {
            addCriterion("RC3 like", value, "rc3");
            return (Criteria) this;
        }

        public Criteria andRc3NotLike(String value) {
            addCriterion("RC3 not like", value, "rc3");
            return (Criteria) this;
        }

        public Criteria andRc3In(List<String> values) {
            addCriterion("RC3 in", values, "rc3");
            return (Criteria) this;
        }

        public Criteria andRc3NotIn(List<String> values) {
            addCriterion("RC3 not in", values, "rc3");
            return (Criteria) this;
        }

        public Criteria andRc3Between(String value1, String value2) {
            addCriterion("RC3 between", value1, value2, "rc3");
            return (Criteria) this;
        }

        public Criteria andRc3NotBetween(String value1, String value2) {
            addCriterion("RC3 not between", value1, value2, "rc3");
            return (Criteria) this;
        }

        public Criteria andUdtIsNull() {
            addCriterion("UDT is null");
            return (Criteria) this;
        }

        public Criteria andUdtIsNotNull() {
            addCriterion("UDT is not null");
            return (Criteria) this;
        }

        public Criteria andUdtEqualTo(Integer value) {
            addCriterion("UDT =", value, "udt");
            return (Criteria) this;
        }

        public Criteria andUdtNotEqualTo(Integer value) {
            addCriterion("UDT <>", value, "udt");
            return (Criteria) this;
        }

        public Criteria andUdtGreaterThan(Integer value) {
            addCriterion("UDT >", value, "udt");
            return (Criteria) this;
        }

        public Criteria andUdtGreaterThanOrEqualTo(Integer value) {
            addCriterion("UDT >=", value, "udt");
            return (Criteria) this;
        }

        public Criteria andUdtLessThan(Integer value) {
            addCriterion("UDT <", value, "udt");
            return (Criteria) this;
        }

        public Criteria andUdtLessThanOrEqualTo(Integer value) {
            addCriterion("UDT <=", value, "udt");
            return (Criteria) this;
        }

        public Criteria andUdtIn(List<Integer> values) {
            addCriterion("UDT in", values, "udt");
            return (Criteria) this;
        }

        public Criteria andUdtNotIn(List<Integer> values) {
            addCriterion("UDT not in", values, "udt");
            return (Criteria) this;
        }

        public Criteria andUdtBetween(Integer value1, Integer value2) {
            addCriterion("UDT between", value1, value2, "udt");
            return (Criteria) this;
        }

        public Criteria andUdtNotBetween(Integer value1, Integer value2) {
            addCriterion("UDT not between", value1, value2, "udt");
            return (Criteria) this;
        }

        public Criteria andIdLikeInsensitive(String value) {
            addCriterion("upper(ID) like", value.toUpperCase(), "id");
            return (Criteria) this;
        }

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(TITLE) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andImgUrlLikeInsensitive(String value) {
            addCriterion("upper(IMG_URL) like", value.toUpperCase(), "imgUrl");
            return (Criteria) this;
        }

        public Criteria andUrlLikeInsensitive(String value) {
            addCriterion("upper(URL) like", value.toUpperCase(), "url");
            return (Criteria) this;
        }

        public Criteria andIntroLikeInsensitive(String value) {
            addCriterion("upper(INTRO) like", value.toUpperCase(), "intro");
            return (Criteria) this;
        }

        public Criteria andCityLikeInsensitive(String value) {
            addCriterion("upper(CITY) like", value.toUpperCase(), "city");
            return (Criteria) this;
        }

        public Criteria andPubdateLikeInsensitive(String value) {
            addCriterion("upper(PUBDATE) like", value.toUpperCase(), "pubdate");
            return (Criteria) this;
        }

        public Criteria andCateLikeInsensitive(String value) {
            addCriterion("upper(CATE) like", value.toUpperCase(), "cate");
            return (Criteria) this;
        }

        public Criteria andProducerLikeInsensitive(String value) {
            addCriterion("upper(PRODUCER) like", value.toUpperCase(), "producer");
            return (Criteria) this;
        }

        public Criteria andRoleLikeInsensitive(String value) {
            addCriterion("upper(ROLE) like", value.toUpperCase(), "role");
            return (Criteria) this;
        }

        public Criteria andMvTypeLikeInsensitive(String value) {
            addCriterion("upper(MV_TYPE) like", value.toUpperCase(), "mvType");
            return (Criteria) this;
        }

        public Criteria andMvTagLikeInsensitive(String value) {
            addCriterion("upper(MV_TAG) like", value.toUpperCase(), "mvTag");
            return (Criteria) this;
        }

        public Criteria andRc4LikeInsensitive(String value) {
            addCriterion("upper(RC4) like", value.toUpperCase(), "rc4");
            return (Criteria) this;
        }

        public Criteria andRc1LikeInsensitive(String value) {
            addCriterion("upper(RC1) like", value.toUpperCase(), "rc1");
            return (Criteria) this;
        }

        public Criteria andRc2LikeInsensitive(String value) {
            addCriterion("upper(RC2) like", value.toUpperCase(), "rc2");
            return (Criteria) this;
        }

        public Criteria andRc3LikeInsensitive(String value) {
            addCriterion("upper(RC3) like", value.toUpperCase(), "rc3");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}