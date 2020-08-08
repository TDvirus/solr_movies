package com.limp.framework.boss.controller;

import com.limp.framework.boss.service.AuthService;
import com.limp.framework.core.bean.Result;
import com.limp.framework.core.constant.ExceptionEnum;
import com.limp.framework.utils.StrUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sso/auth")
public class AuthSSOController {

    private Logger log= LoggerFactory.getLogger(AuthSSOController.class);

    @Autowired
    AuthService authService;



    /**
     * 设置默认页面显示的条数
     */
    public static  final Integer DEFAULT_ROW=10;
    /**
     * 用户
     * @param account
     * @param password
//     * @param roleCode
//     * @param validateCode
     * @return
     */
    @RequestMapping(value = "/login")
    public String login(@RequestParam String account, @RequestParam String password) {
        log.debug("/***用户登陆*{}*/",account);
        //参数校验
        if(StrUtils.isBlank(account)|| StrUtils.isBlank(password)){
            return Result.getException(ExceptionEnum.ParamIllegal).getJson();
        }
        Result loginResult = authService.login(account,password,"");
        log.info("登录返回的结果集为:{}",loginResult.getJson());
        return loginResult.getJson();
    }
}
