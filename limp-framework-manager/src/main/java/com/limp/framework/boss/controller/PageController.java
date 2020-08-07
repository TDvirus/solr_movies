package com.limp.framework.boss.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户相关controller
 * @author:shinians email:shiniandate@163.com
 * @description:
 * @date:Created in 22:21 2018/6/2
 * @modified By:
 * @RestController：是controller和ResponseBody的集合
 */
@Controller
@RequestMapping("/page")
public class PageController {
    private Logger loger= LoggerFactory.getLogger(PageController.class);


    /*********************业务层*******************************/

    /**
     * servies列表
     * @return
     */
    @RequestMapping(value = "/car/search")
    public String index() {
        loger.debug("visit main ...");
        return "car/pages/client/car_search";
    }
    @RequestMapping(value = "/car/orderList")
    public String orderList() {
        loger.debug("visit main ...");
        return "car/pages/client/order_list";
    }
    @RequestMapping(value = "/car/detail")
    public String detail() {
        loger.debug("visit main ...");
        return "car/pages/client/car_search_detail";
    }
    @RequestMapping(value = "/car/set")
    public String set() {
        loger.debug("visit main ...");
        return "car/pages/set/set_index";
    }
    @RequestMapping(value = "/car/user/info")
    public String userInfo() {
        loger.debug("visit main ...");
        return "car/pages/client/user_info";
    }
    @RequestMapping(value = "/car/user/list")
    public String userlist() {
        loger.debug("visit main ...");
        return "car/pages/client/user_list";
    }

    /**
     * 购物车
     * @return
     */
    @RequestMapping(value = "/car/shop")
    public String carShop() {
        loger.debug("visit main ...");
        return "car/pages/client/shop_list";
    }

    /**
     * 提交订单
     * @return
     */
    @RequestMapping(value = "/car/order/pay")
    public String orderBuy() {
        loger.debug("visit main ...");
        return "car/pages/client/order_pay";
    }
    @RequestMapping(value = "/car/goods/store")
    public String goodsStore() {
        loger.debug("visit main ...");
        return "car/pages/client/goods_store";
    }
    @RequestMapping(value = "/addr/list")
    public String addrInfo() {
        loger.debug("visit main ...");
        return "car/pages/client/addr_list";
    }
    @RequestMapping(value = "/car/user/score")
    public String userScore() {
        loger.debug("visit main ...");
        return "car/pages/client/user_score";
    }
    @RequestMapping(value = "/car/user/secure")
    public String userSecure() {
        loger.debug("visit main ...");
        return "car/pages/client/user_secure";
    }
    @RequestMapping(value = "/car/user/coupon")
    public String userCoupon() {
        loger.debug("visit main ...");
        return "car/pages/client/user_coupon";
    }
    @RequestMapping(value = "/car/ent/info")
    public String entInfo() {
        loger.debug("visit main ...");
        return "car/pages/client/ent_info";
    }
    /**
     * servies列表
     * @return
     */
    @RequestMapping(value = "services")
    public String main() {
        loger.debug("visit main ...");
        return "uums/pages/services/services_list";
    }
    /**
     * servies列表
     * @return
     */
    @RequestMapping(value = "/services/port")
    public String port() {
        loger.debug("visit main ...");
        return "uums/pages/services/services_port";
    }
    /**
     * order列表
     * @return
     */
    @RequestMapping(value = "/order")
    public String order() {
        loger.debug("visit main ...");
        return "uums/pages/order/order_list";
    }
    /**
     * servies列表
     * @return
     */
    @RequestMapping(value = "/car/orderService")
    public String orderService() {
        loger.debug("visit main ...");
        return "car/pages/client/order_service";
    }
    @RequestMapping(value = "/car/billList")
    public String billList() {
        loger.debug("visit main ...");
        return "car/pages/client/bill_list";
    }
    /****************************************************/

}
