package com.limp.framework.boss.service;

import com.limp.framework.core.bean.Result;

/**
 * @author:shinians email:shiniandate@163.com
 * @description:
 * @date:Created in 13:40 2018/8/18
 * @modified By:
 */
public interface AuthService  {

     Result login(String account, String password, String roleCode);
}
