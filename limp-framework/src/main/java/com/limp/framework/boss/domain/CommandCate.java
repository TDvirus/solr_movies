package com.limp.framework.boss.domain;

import com.limp.framework.core.abs.AbstractModel;

import java.io.Serializable;
import java.util.Date;

public class CommandCate  extends AbstractModel implements Serializable {
    private String id;

    private String pid;

    private String name;

    private String intro;

    private String userid;

    private Integer state;

    private Date idt;

    private Date udt;

    private String rc1;

    private String rc2;

    private Integer rc3;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro == null ? null : intro.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getIdt() {
        return idt;
    }

    public void setIdt(Date idt) {
        this.idt = idt;
    }

    public Date getUdt() {
        return udt;
    }

    public void setUdt(Date udt) {
        this.udt = udt;
    }

    public String getRc1() {
        return rc1;
    }

    public void setRc1(String rc1) {
        this.rc1 = rc1 == null ? null : rc1.trim();
    }

    public String getRc2() {
        return rc2;
    }

    public void setRc2(String rc2) {
        this.rc2 = rc2 == null ? null : rc2.trim();
    }

    public Integer getRc3() {
        return rc3;
    }

    public void setRc3(Integer rc3) {
        this.rc3 = rc3;
    }
}