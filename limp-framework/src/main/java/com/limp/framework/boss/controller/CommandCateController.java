package com.limp.framework.boss.controller;

import com.limp.framework.boss.domain.CommandCate;
import com.limp.framework.boss.service.CommandCateService;
import com.limp.framework.core.annotation.Access;
import com.limp.framework.core.bean.Pager;
import com.limp.framework.core.bean.Result;
import com.limp.framework.core.bean.ResultCode;
import com.limp.framework.core.constant.Constant;
import com.limp.framework.core.constant.OPERATION;
import com.limp.framework.core.constant.ResultMsg;
import com.limp.framework.utils.StrUtils;
import com.limp.framework.utils.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 命令相关controller cate分类
 * @author:shinians email:shiniandate@163.com
 * @description:
 * @date:Created in 22:21 2018/8/18
 * @modified By:
 * @RestController：是controller和ResponseBody的集合
 * 支持跨域访问
 */
@RestController
@RequestMapping("/system")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CommandCateController extends BaseController {

    private Logger loger= LoggerFactory.getLogger(CommandCateController.class);

    @Autowired
    CommandCateService commandCateService;
    /**
     * 设置默认页面显示的条数
     */
    public static  final Integer DEFAULT_ROW=10;


    /**
     * 新增命令分类信息
     * @param command
     * @return
     */

    @RequestMapping(value = "/commandCate",method= RequestMethod.POST)
    @Access(privilege =true,login = true,operationLog = OPERATION.INSERT,operationIntro = "新增命令信息")
    public String saveCommandCate(CommandCate commandCate) {
        loger.debug("/***新增命令信息**/");
        Boolean flay= commandCateService.save(commandCate);
        if(flay){
            return Result.Success().getJson();
        }
        return Result.Error().getJson();
    }

    /**
     * 删除命令
     * 为了管理员易于操作支持批量操作
     * @param id
     * @return
     */
    @RequestMapping(value = "/commandCate/{id}",method= RequestMethod.DELETE)
    @Access(privilege =true,login = true,operationLog = OPERATION.DELETE,operationIntro = "删除命令信息")
    public String delCommandCate( @PathVariable("id") String id) {
        loger.debug(TextUtils.format("/***根据命令id{0}，删除命令记录**/", id));
        String []ids=id.split(Constant.DHAO);
        if(id.split(Constant.DHAO).length>DEFAULT_ROW){
            return Result.getInstance(ResultCode.ERROR.toString(), ResultMsg.DEL_ERROR_IDS_TOO_MANY,"","").getJson();
        }
        Boolean flay=true;
        for(String did:ids){
            if(!commandCateService.delete(did)){
                flay=false;
            };
        }
        if(flay){
            return Result.Success().getJson();
        }
        return Result.Error().getJson();
    }
    /**
     * 更新命令信息
     * @param command command
     * @return
     */

    @RequestMapping(value = "/commandCate",method= RequestMethod.PUT)
    @Access(privilege =true,login = true,operationLog = OPERATION.UPDATE,operationIntro = "更新命令信息")
    public String updateCommandCate(CommandCate commandCate) {
        loger.debug(TextUtils.format("/***更新命令信息**/"));
        Boolean flay= commandCateService.update(commandCate);
        if(flay){
            return Result.getInstance(ResultCode.SUCCESS.toString(), ResultMsg.UPDATE_SUCCESS, "", "").getJson();
        }
        return Result.getInstance(ResultCode.ERROR.toString(), ResultMsg.UPDATE_ERROR, "", "").getJson();
    }

    /**
     * 获取命令信息根据命令id
     * @param id 查询的id
     * @return
     */
    @RequestMapping(value = "/commandCate/{id}",method= RequestMethod.GET)
    public String selectCommandCate(@PathVariable("id") String id) {
        loger.debug(TextUtils.format("/***根据命令id{0}，获取命令基本信息**/", id));
        CommandCate command= commandCateService.get(id);
        return Result.getInstance(ResultCode.SUCCESS.toString(), ResultMsg.SUCCESS,command,"").getJson();
    }
    /**
     * 查询用于列表
     * @param command
     * @return
     */

    @RequestMapping(value = "/commandCates",method= RequestMethod.GET)
    public String selectCommandCateList(CommandCate commandCate) {
        loger.debug(TextUtils.format("/***查询命令,返回命令列表**/"));
        List<CommandCate> list= commandCateService.getList(commandCate);
        return Result.getInstance(ResultCode.SUCCESS.toString(), ResultMsg.SUCCESS,list,"").getJson();
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
