

/**
 * 地区信息相关操作
 * @type {{initDomainTable: Function}}
 */
var dyOrderList={
    clickTreeId:"",
    //汇总所有相关的静态变量，统一全局调用，便于修正ID
    cons:{
        //表格ID
        tableId:"carSearchTable",
        //分页id
        tablePageId:"carSearchTablePage"
    },

    /**
     * 事件监听函数：用户监听绑定的事件
     */
    listenFun:function(){

        //更改状态
        $("#carSearchTable").LPClickDelegate(".order-pay",function(){
            var detailId=$(this).attr("data-value");
            system.post("/car/orderPay",{type:1,orderId:detailId},function(data){
                if(data.code=="200"){
                    system.tip("支付成功",1);
                    dyOrderList.initDomainTable();
                }
            })
            dyOrderList.saveDomain(detailId)
        })   //更改状态
        $("#carSearchTable").LPClickDelegate(".order-cancel",function(){
            var detailId=$(this).attr("data-value");
            system.post("/car/orderCancel",{type:1,orderId:detailId},function(data){
                if(data.code=="200"){
                    system.tip("取消成功",1);
                    dyOrderList.initDomainTable();
                }
            })
            dyOrderList.saveDomain(detailId)
        })   //更改状态
        $("#bill-btn").LPClickDelegate(".layui-btn",function() {
            //点击的样例
            var detailId = $(this).attr("data-value");
            $("#bill-btn .layui-btn").each(function (item) {
                $(this).addClass("btn-default");
            })
            $(this).removeClass("btn-default");

            //点击专票/操作 显示隐藏，form
            if (detailId == 1) {
                $("#bill-item .layui-form-item").each(function (item) {
                    $(this).hide();
                })
                $("#bill-item .bill-item-1").each(function (item) {
                    $(this).show();
                })
                dyOrderList.initBillTable(1);

            } else {
                //专票
                $("#bill-item .layui-form-item").each(function (item) {
                    $(this).show();
                })
                dyOrderList.initBillTable(2);
            }

        })
    },
    /**
     * 加载地区信息管理 属性结构
     */
    initTree:function(){
        system.post("/system/perm/getMenuById",{type:4,id:"81d2c5c4902a471b8cf5ef9c85fd3efb"},function(data){
           if(data.code=="200"){
               $("#user-tree").html("");
               var ul=$("#user-tree");
               var hrefUrl=window.location.href;
               for(var m in data.result){
                   var domain=data.result[m];
                   if(system.isBlank(domain.url)){
                       ul.append('<li class="site-tree-noicon  cate-name">'+domain.name+'</li>');
                   }else{
                       var check=hrefUrl.indexOf(domain.url)>-1? "y-color":"";
                       ul.append('<li class="site-tree-noicon"> <a href="'+domain.url+'" class="'+check+'"><cite>'+domain.name+'</cite></a></li>');
                   }
               }
           }
        })
    }  ,
    //设置默认地址
    setfisrtAddr:function(id){
        system.post("/car/setOneAddr",{type:3,id:id},function(data){
            if(data.code=="200"){
                //初始化 form表单
                system.tip("设置成功",1);
                dyOrderList.initDomainTable();
            }

        })

    },

    /**
     * 初始化地区信息
     */
    initDomainTable:function(params){
        var option={
            async:true,//action请求是否异步，true为为异步请求【默认为true异步请求】
            pageFlay:true,//是否分页，默认为true 进行分页；为false则不进行分页
            //【浏览器缓存】是否需要缓存  会缓存到system.cache[""]
            cache:true,
            //缓存的key值，取值方法 system.cache["id"]
            cacheKey:"id",
            //控制全局table样式，td【thStyle】和th【tdStyle】；优先级低于单个表格设置的样式【低于colModel中style和styleTh的样式】
            table:{thStyle:"text-align:center;white-space: nowrap;cursor:pointer;font-family: Microsoft YaHei;",tdStyle:"vertical-align:center;text-align:center;max-width:120px; " +
            "border: 1px solid rgba(238,238,238,1); padding: 0px 10px 0px 10px;",tdAppend:"<br>"},
            colModel : [
                // {key :'id',name:"checkbox",width : 35},
                // {key :'NO.',name:"序号",width : 25},

                {key :'partno',name : '零件编号',formatters:function(domain){
                var partNoStr='';
                var orderClass=domain.orderGoodsList.length>1?'order-line':'';

                for(var m in domain.orderGoodsList){
                    var order=domain.orderGoodsList[m];
                    partNoStr+='<div class="'+orderClass+'">'+order.partno+'</div>';
                }
                return partNoStr;
                }},
                {key :'partname',name : '零件名称',formatters:function(domain){
                    var partNoStr='';
                    var orderClass=domain.orderGoodsList.length>1?'order-line':'';

                    for(var m in domain.orderGoodsList){
                        var order=domain.orderGoodsList[m];
                        partNoStr+='<div class="'+orderClass+'">'+order.partname+'</div>';
                    }
                    return partNoStr;
                }},
                {key :'subgestNo',name : '单价',formatters:function(domain){
                    var partNoStr='';
                    var orderClass=domain.orderGoodsList.length>1?'order-line':'';

                    for(var m in domain.orderGoodsList){
                        var order=domain.orderGoodsList[m];
                        partNoStr+='<div class="'+orderClass+'">1</div>';
                    }
                    return partNoStr;
                }},
                {key :'buyno',name : '数量',formatters:function(domain){
                    var partNoStr='';

                    var orderClass=domain.orderGoodsList.length>1?'order-line':'';

                    for(var m in domain.orderGoodsList){
                        var order=domain.orderGoodsList[m];
                        partNoStr+='<div class="'+orderClass+'">'+order.buyno+'</div>';
                    }
                    return partNoStr;
                }},
                {key :'orderno',name : '订单号',formatter:function(orderno){

                    return '<a title="'+orderno+'" style="cursor:pointer;">详情</a>'
                }},

                {key :'payState',name : '商品操作',sort:'name',formatter:function(payState){
                    var payTip="再次购买";
                    if(payState==2){
                        payTip="无";
                    }else if(payState==3){
                        payTip="退货/换货";
                    }else if(payState==4){
                        payTip="退货/换货";
                    }
                    return payTip;
                }},
                {key :'totalno',name : '订单总额',formatter:function(totalno){
                    return '<span class="order-price">¥'+totalno+'</span>';
                }},
                {key :'payState',name : '交易状态',formatter:function(payState){
                    var payTip="交易成功";
                    var clasName='';
                    if(payState=="2"){
                        payTip="待付款";
                        clasName='order-red';
                    }else if(payState=="3"){
                        payTip="待发货";
                    }else if(payState=="4"){
                        payTip="待收货";
                    }
                    return '<span class="'+clasName+'">'+payTip+'</span>';
                }},

                {key :'OP',name : '交易操作',style:"font-size: 12px;", formatters:function(domain){
                    var itemObj=$('<span data-value="'+domain.id+'"></span>');
                    if(domain.payState==2){
                        itemObj.append('<a title="立即付款" class="order-pay store-btn " href="javascript:;" data-value="'+domain.id+'" > 立即付款</a><br><br>')
                        itemObj.append('<a  title="取消订单" class="order-cancel shop-btn"  data-value="'+domain.id+'" href="javascript:;">取消订单</a>');
                    }else if(domain.payState==3){
                        itemObj.append('<a title="加入报价单" class="store-btn" href="javascript:;" data-value="'+domain.id+'" > 修改地址</a><br><br>')
                    }else if(domain.payState==4){
                        itemObj.append('<a title="加入报价单" class="store-btn" href="javascript:;" data-value="'+domain.id+'" > 确认收货</a><br><br>')
                    }else if(domain.payState==5){
                        itemObj.append('<a title="加入报价单" class="store-btn" href="javascript:;" data-value="'+domain.id+'" > 删除订单</a><br><br>')
                    }
                         return itemObj;
                }
                }
            ]

        };

        if(system.isBlank(params)){
            params={state:1};
        }
        if(system.isBlank(params['page'])){
            //获取当前页数
            var curNum=$("#"+dyOrderList.cons.tablePageId+" .current").attr("data-value");
            params['page']=system.isBlank(curNum)?1:curNum;
        }
        params['type']=4;
        $("#"+dyOrderList.cons.tableId).LPTable("/car/orderAll",params,option);

         // todo 给td加上tr
        setTimeout(function(){
            $("#carSearchTable tr").each(function(){
                console.log("99999999999999")
                console.log(this)
                $(this)
            })
        },3000)


    },


    initBillTable:function(taxtype){
        system.post("/car/userBillInfo",{type:4,taxtype:taxtype},function(data){
            if(data.code=="200"){
                layui.use(['form','jquery'], function() {
                    var form = layui.form;
                    setTimeout(function(){
                        var billInfo={billtitle:"",taxno:"",id:""};
                        //新增操作

                        // billInfo = data.result
                        for(var i in data.result){
                            billInfo[i]=data.result[i];
                        }
                        // billInfo.add(data.result);
                        billInfo["taxtype"]=taxtype;
                        console.log("-----")
                        console.log(billInfo)
                        form.render();
                        form.val("billFilter",billInfo);



                        /***********************监听提交操作【1、获取用户填写信息 2、组装信息 3、提交ajax请求 4、关闭弹框】*********************/
                        form.on('submit(billFilterSubmit)', function(data) {
                            var params = data.field;
                            console.log(params);
                                params["lptype"] = 3;
                                system.post("/car/updateBillInfo", params, function (data) {
                                    if (data.code == "200") {
                                        system.tip('保存成功!', 1);
                                    } else {
                                        system.tip('操作失败，请刷新后重试!', 0);
                                    }
                                });

                            //阻止提交表单
                            return false;
                        })
                    },200)

                });


            }

        })
    },

    initAreaProv:function(pcode,optionId){
        system.post("/sys/areasList",{type:4,pcode:pcode},function(data){
            $("#"+optionId).html("");
            var optionList=$("#"+optionId)
            //初始化选项
            if(optionId=="proname"){
                optionList.append('<option value="">请选择省</option>');
            }else if(optionId=="cityname"){
                optionList.append('<option value="">请选择市</option>');
            }else{
                optionList.append('<option value="">请选择县/区</option>');
            }
            //添加选项
            for(var m in data.result){
                var domain=data.result[m];
                system.cache[domain.name]=domain.code;
                optionList.append(' <option data-value="'+domain.code+'" value="'+domain.name+'">'+domain.name+'</option>')
            }
                //重新渲染，添加监听！！！！
                layui.use(['laydate','jquery','form'], function() {
                    var $ = layui.jquery;
                    var form = layui.form;
                    form.render();
                    //县联动
                    form.on('select(cityname)',function(data){
                        console.log(system.cache[data.value])
                        dyOrderList.initAreaProv(system.cache[data.value],"xname")

                    })
                    //市联动
                    form.on('select(proname)',function(data){
                        dyOrderList.initAreaProv(system.cache[data.value],"cityname")

                    })

                })
        })
    },

    /**
     * code为则为新增操作，有值则为修改操作
     * 新增/修改 实体类
     */
    saveDomain:function(code){
    }

}
layui.use(['laydate','jquery','form'], function() {
    var $ = layui.jquery;
    var form = layui.form;
    //初始化表格数据
    dyOrderList.initDomainTable();
    dyOrderList.initBillTable(2);
    // dyOrderList.initAreaProv("0","proname")

    dyOrderList.listenFun();
    //dyOrderList.saveDomain();
    //修改操作

    //监听提交
});
