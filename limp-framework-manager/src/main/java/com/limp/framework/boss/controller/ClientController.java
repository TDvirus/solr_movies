package com.limp.framework.boss.controller;

import com.limp.framework.boss.ApplicationContext;
import com.limp.framework.boss.domain.UserInfo;
import com.limp.framework.boss.service.UserInfoService;
import com.limp.framework.core.bean.Result;
import com.limp.framework.core.bean.ResultCode;
import com.limp.framework.core.constant.ResultMsg;
import com.limp.framework.core.service.CoreService;
import com.limp.framework.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/carAuth")
public class ClientController {
    @Autowired
    UserInfoService userInfoService;
    /**
     * 第2步骤：
     * 注入码表转换Servce ->CoreService
     */
    @Autowired
    CoreService coreService;

    /**
     * 获取用户登录信息
     * @return
     */
    @RequestMapping(value = "/loginUserInfo",method= RequestMethod.GET)
    public String loginUserInfo() {
        UserInfo user=ApplicationContext.getLoginUser();
        if(StrUtils.isBlank(user)){
            return Result.getInstance(ResultCode.ERROR.toString(), ResultMsg.ERROR,"","").getJson();
        }
        UserInfo userInfo=userInfoService.get(user.getId());
        userInfo.setPassword("");
        return Result.getInstance(ResultCode.SUCCESS.toString(), ResultMsg.SUCCESS,userInfo,"").getJson();
    }
    /**
     * 获取用户登录信息
     * @return
     */
    @RequestMapping(value = "/loginUserInfo",method= RequestMethod.PUT)
    public String updateloginUserInfo(UserInfo userInfo) {
        UserInfo user=ApplicationContext.getLoginUser();
        if(StrUtils.isBlank(user)){
            return Result.getInstance(ResultCode.ERROR.toString(), ResultMsg.ERROR,"","").getJson();
        }
        userInfo.setId(user.getId());
        Boolean flay=userInfoService.update(userInfo);
        if(!flay){
            return Result.getInstance(ResultCode.ERROR.toString(), ResultMsg.ERROR,"","").getJson();
        }
        return Result.getInstance(ResultCode.SUCCESS.toString(), ResultMsg.SUCCESS,userInfo,"").getJson();
    }
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        /**
         * 第一种方式：使用WebDataBinder注册一个自定义的编辑器，编辑器是日期类型
         * 使用自定义的日期编辑器，日期格式：yyyy-MM-dd,第二个参数为是否为空  true代表可以为空
         */
        binder.registerCustomEditor(Date.class, new CustomDateEditor(
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"), true));
    }
}
