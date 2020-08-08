package com.limp.framework.boss.domain;

import com.limp.framework.core.abs.AbstractModel;

import java.io.Serializable;

public class DicCode extends AbstractModel implements Serializable {
    private String id;

    private String pid;

    private String dictCate;

    private String dictName;

    private String dictValue;

    private String dictIntro;

    private String dictStatue;

    private String dictPvalue;

    private Integer dictSort;

    private String dictKey;

    private Integer dictLv;

    private String dictRc1;

    private String dictRc2;

    private String dictRc3;

    private Integer dictRc4;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getDictCate() {
        return dictCate;
    }

    public void setDictCate(String dictCate) {
        this.dictCate = dictCate == null ? null : dictCate.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    public String getDictIntro() {
        return dictIntro;
    }

    public void setDictIntro(String dictIntro) {
        this.dictIntro = dictIntro == null ? null : dictIntro.trim();
    }

    public String getDictStatue() {
        return dictStatue;
    }

    public void setDictStatue(String dictStatue) {
        this.dictStatue = dictStatue == null ? null : dictStatue.trim();
    }

    public String getDictPvalue() {
        return dictPvalue;
    }

    public void setDictPvalue(String dictPvalue) {
        this.dictPvalue = dictPvalue == null ? null : dictPvalue.trim();
    }

    public Integer getDictSort() {
        return dictSort;
    }

    public void setDictSort(Integer dictSort) {
        this.dictSort = dictSort;
    }

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey == null ? null : dictKey.trim();
    }

    public Integer getDictLv() {
        return dictLv;
    }

    public void setDictLv(Integer dictLv) {
        this.dictLv = dictLv;
    }

    public String getDictRc1() {
        return dictRc1;
    }

    public void setDictRc1(String dictRc1) {
        this.dictRc1 = dictRc1 == null ? null : dictRc1.trim();
    }

    public String getDictRc2() {
        return dictRc2;
    }

    public void setDictRc2(String dictRc2) {
        this.dictRc2 = dictRc2 == null ? null : dictRc2.trim();
    }

    public String getDictRc3() {
        return dictRc3;
    }

    public void setDictRc3(String dictRc3) {
        this.dictRc3 = dictRc3 == null ? null : dictRc3.trim();
    }

    public Integer getDictRc4() {
        return dictRc4;
    }

    public void setDictRc4(Integer dictRc4) {
        this.dictRc4 = dictRc4;
    }
}