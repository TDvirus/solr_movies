package com.limp.framework.boss.service.impl;

import com.limp.framework.auth.AuthUtils;
import com.limp.framework.boss.domain.LoginLog;
import com.limp.framework.boss.domain.UserInfo;
import com.limp.framework.boss.mapper.oracle.UserInfoMapper;
import com.limp.framework.boss.service.AuthService;
import com.limp.framework.boss.service.LoginLogService;
import com.limp.framework.boss.service.UserInfoService;
import com.limp.framework.core.bean.Result;
import com.limp.framework.core.bean.ResultCode;
import com.limp.framework.core.constant.Constant;
import com.limp.framework.core.constant.ExceptionEnum;
import com.limp.framework.core.constant.ResultMsg;
import com.limp.framework.utils.StrUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {
    /**
     * 记录登录日志
     */
    @Autowired
    private LoginLogService loginLogService;
    /**
     * 用户Service
     */

    @Autowired
    UserInfoService userInfoService;

    @Autowired
    UserInfoMapper  userInfoMapper;

    @Override
    public Result login(String account, String password, String roleCode) {
        /**************用户【账号、密码】登录认证************/

        //支持邮箱和账号登录，判断条件是是否含有@
        UserInfo userParam=new UserInfo();
        //判断是否是邮箱登录
        if(account.indexOf(Constant.FLAY_EMAIL)>-1){
            userParam.setEmail(account);
        }else{
            userParam.setAccount(account);
        }
        //登录用户列表
        List<UserInfo> loginUserList=userInfoService.getList(userParam);
        if(loginUserList.size()==0){
            //用户不存在
            return  Result.getException(ExceptionEnum.UserNotFond);
        }
        //登录用户
        UserInfo loginUser=loginUserList.get(0);

        //用户密码错误1
        if(!loginUser.getPassword().equals(AuthUtils.encodeMD5(password, ""))){
            loginLogService.insert(getLogin(loginUser,"",Constant.NUMBER_2));
            return  Result.getException(ExceptionEnum.UserPwdError);
        }

        //账户冻结
        if (loginUser.getState()!=null&&loginUser.getState() == Constant.NUMBER_2) {
            loginLogService.insert(getLogin(loginUser,"",Constant.NUMBER_3));
            return  Result.getException(ExceptionEnum.UserSTAENoAuth);
        }
        //账号已经删除不可用：注销状态 -1
        if (loginUser.getState()!=null&&loginUser.getState() == Constant.STATE_DELETE) {
            return Result.getException(ExceptionEnum.UserSTAEDel);
        }
        //用户待审核
        if(loginUser.getState()!=null&&Constant.NUMBER_1!=loginUser.getState()){
            loginLogService.insert(getLogin(loginUser, "", Constant.NUMBER_5));
            return Result.getException(ExceptionEnum.UserStateError);
        }
        //验证是否过期
        if(validateExpired(loginUser)){
            loginLogService.insert(getLogin(loginUser, "", Constant.NUMBER_5));
            return Result.getException(ExceptionEnum.UserExpired);
        }

//        //角色 判断
//        List<Map<String,String>> listMap=userInfoMapper.selectUsersByUserIdAndRoleCode(loginUser.getId(),roleCode);
//        if(StrUtils.isBlank(listMap)||listMap.size()==0){
//            return Result.getException(ExceptionEnum.UserRoleNotFind);
//        }

        //登录成功
        Map<String,Object> loginMap = new HashMap<String,Object>();
        //创建token
//        loginMap.put("token", JwtUtil.getInstance().generateToken(loginUser));
//        loginMap.put("url", "#");
//        loginMap.put("name",loginUser.getName());
//        loginMap.put("account",loginUser.getAccount());
//        loginMap.put("roleCode",roleCode);
//        loginMap.put("id",loginUser.getId());
        /*******************test*****************************/

        //记录登录日志
        try {
            loginLogService.insert(getLogin(loginUser,"", Constant.NUMBER_1));
        } catch (Exception e) {
            e.printStackTrace();
        }
        loginUser.setPassword(" ");
        return Result.getInstance(ResultCode.SUCCESS.toString(), ResultMsg.LOGIN_SUCCESS,loginUser,"");
    }
    /**
     * 实例化登录日志
     * @param user
     * @param ip
     * @param logType
     * @return
     */
    public LoginLog getLogin(UserInfo user, String ip, Integer logType) {
        //初始化日志信息
        LoginLog loginLog=new LoginLog();
        loginLog.setId(StrUtils.randomUUID());
        //登录账号
        loginLog.setAccount(user.getAccount());
        loginLog.setIp(ip);
        loginLog.setMsg(logType==Constant.NUMBER_1?"登陆成功":"登录失败");
        //操作类型 登录
        loginLog.setType(Constant.NUMBER_1);
        loginLog.setLogtype(logType);
        loginLog.setState(Constant.NUMBER_1);
        loginLog.setIdt(new Date());
        return   loginLog;
    }

    /**
     * 验证是否过期
     * @param user
     * @return
     */
    public boolean validateExpired(UserInfo user) {
        //是否过期
        Integer usrIsautoexpire = user.getIsautoexpire();
        if (usrIsautoexpire != null && usrIsautoexpire == 1) {
            Date expire = user.getExpiredate();
            if (expire == null) {
                return false;
            }
            return new Date().after(expire);
        }
        return false;
    }

}
