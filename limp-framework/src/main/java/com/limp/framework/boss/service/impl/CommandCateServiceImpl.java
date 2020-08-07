package com.limp.framework.boss.service.impl;

import com.limp.framework.boss.domain.CommandCate;
import com.limp.framework.boss.domain.CommandCateExample;
import com.limp.framework.boss.mapper.oracle.CommandCateMapper;
import com.limp.framework.boss.service.CommandCateService;
import com.limp.framework.core.bean.Pager;
import com.limp.framework.core.constant.Constant;
import com.limp.framework.utils.JsonUtils;
import com.limp.framework.utils.StrUtils;
import com.limp.framework.utils.TextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author:shinians email:shiniandate@163.com
 * @description:
 * @date:Created in 13:42 2018/8/18
 * @modified By:
 */
@Service
public class CommandCateServiceImpl implements CommandCateService {

    private Logger log = LoggerFactory.getLogger(CommandCateServiceImpl.class);

    /**
     * 注入mapper
     */
    @Autowired
    private CommandCateMapper commandCateMapper;

    /**
     * 新增命令
     *
     * @param entry 需要保存的实体类
     * @return
     */
    @Override
    public boolean save(CommandCate entry) {
        if (StrUtils.isBlank(entry.getId())) {
            entry.setId(StrUtils.randomUUID());
        }
        log.debug("新增命令分类{}", entry.getId());
        entry.setState(Constant.NUMBER_1);
        entry.setIdt(new Date());
            return commandCateMapper.insertSelective(entry) == 1 ? true : false;
    }

    /**
     * 根据id查询命令
     *
     * @param id 主键id
     * @return
     */
    @Override
    public CommandCate get(String id) {
        if (StrUtils.isBlank(id)) {
            return null;
        }
        log.debug(TextUtils.format("根据命令Id{0},获取实体类信息", id));
        CommandCateExample commandExample = new CommandCateExample();
        commandExample.createCriteria().andIdEqualTo(id);
        List<CommandCate> list = commandCateMapper.selectByExample(commandExample);
        return list.size() > 0 ? list.get(0) : null;
    }

    /**
     * 更新命令
     *
     * @param entry 当前实体类信息
     * @return
     */
    @Override
    public boolean update(CommandCate entry) {
        if (StrUtils.isBlank(entry.getId())) {
            return false;
        }
        entry.setUdt(new Date());
        log.debug(TextUtils.format("根据Id{0},更新实体类信息", entry.getId()));
        return commandCateMapper.updateByPrimaryKeySelective(entry) == 1 ? true : false;  //To change body of implemented methods use File | Settings | File Templates.

    }

    /**
     * 根据id删除命令
     *
     * @param id 需要删除的Id
     * @return
     */
    @Override
    public boolean delete(String id) {
        log.debug(TextUtils.format("根据Id{0},删除实体命令信息", id));

        //逻辑删除
        CommandCate entry = new CommandCate();
        entry.setId(id);
        entry.setState(Integer.parseInt(Constant.STRING_DEL_STATE));
        //更新时间
        entry.setUdt(new Date());
        return commandCateMapper.updateByPrimaryKeySelective(entry) == 1 ? true : false;
    }

    /**
     * 查询所有命令
     *
     * @param entry 查询条件：精确查询|不带模糊查询条件
     * @return
     */
    @Override
    public List<CommandCate> getList(CommandCate entry) {
        //初始化Example
        CommandCateExample entryExample = new CommandCateExample();

        //创建查询条件
        CommandCateExample.Criteria criteria = entryExample.createCriteria();

        //如果命令状态不为空，则根据命令状态查询命令列表
        if (!StrUtils.isBlank(entry.getState())) {
            criteria.andStateEqualTo(entry.getState());
        }
        //根据id查询
        if (!StrUtils.isBlank(entry.getId())) {
            criteria.andIdEqualTo(entry.getId());
        }

        //根据名称模糊查询
        if (!StrUtils.isBlank(entry.getName())) {
            criteria.andNameLike(entry.getName());
        }

        return commandCateMapper.selectByExample(entryExample);
    }

    /**
     * 查询所有命令信息，并进行分页
     *
     * @param command
     * @param pager   分页信息
     * @return
     */
    @Override
    public Pager<CommandCate> getPageList(CommandCate command, Pager pager) {

        log.debug(TextUtils.format("根据条件,查询命令列表【分页方法】"));
        //初始化Example
        CommandCateExample entityExample = new CommandCateExample();
        //设置分页对象
        entityExample.setPage(pager);
        //创建查询条件
        CommandCateExample.Criteria criteria = entityExample.createCriteria();

        //如果命令状态不为空，则根据命令状态查询命令列表
        if (!StrUtils.isBlank(command.getState())) {
            criteria.andStateEqualTo(command.getState());
        }
        //根据id查询
        if (!StrUtils.isBlank(command.getId())) {
            criteria.andIdEqualTo(command.getId());
        }

        //根据名称模糊查询
        if (!StrUtils.isBlank(command.getName())) {
            criteria.andNameLike("%"+command.getName()+"%");
        }
        if(!StrUtils.isBlank(command.getLpsort())){
            entityExample.setOrderByClause(command.getLpsort());
        }
        criteria.andStateEqualTo(Constant.NUMBER_1);

        List<CommandCate> commands = commandCateMapper.selectByExample(entityExample);

        pager.setPagerInfo(commands, commandCateMapper.countByExample(entityExample));

        return pager;

    }


}
