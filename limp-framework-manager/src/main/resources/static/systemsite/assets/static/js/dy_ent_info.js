

/**
 * 地区信息相关操作
 * @type {{initDomainTable: Function}}
 */
var dyEntInfo={

    /**
     * 事件监听函数：用户监听绑定的事件
     */
    listenFun:function(){

        //更改状态
        $("#addr-list").LPClickDelegate(".addr-opt",function(){
            var detailId=$(this).attr("data-value");
            dyEntInfo.saveDomain(detailId)
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
                dyEntInfo.initBillTable(1);

            } else {
                //专票
                $("#bill-item .layui-form-item").each(function (item) {
                    $(this).show();
                })
                dyEntInfo.initBillTable(2);
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
                dyEntInfo.initDomainTable();
            }

        })

    },

    /**
     * 初始化地区信息
     */
    initDomainTable:function(code){
        system.post("/car/userEntInfo",{type:4},function(data){
            if(data.code=="200"){
                layui.use(['form','jquery'], function() {
                    var form = layui.form;
                    setTimeout(function(){
                        var entInfo={};
                        //新增操作
                        // if(system.isBlank(code)){
                            // dyEntInfo.initAreaProv(0,"proname")
                            entInfo = data.result;

                        console.log("-----")
                        console.log(entInfo)
                        form.render();
                        form.val("entFilter",entInfo);

                        /******************自定义验证规则*************/
                        form.verify({
                            name: function(value) {
                                if(system.isBlank(value)) {
                                    return '请输入收货人名称';
                                }
                            },
                            proname: function(value) {
                                if(system.isBlank(value)) {
                                    return '请选择省份';
                                }
                            },
                        });

                        /***********************监听提交操作【1、获取用户填写信息 2、组装信息 3、提交ajax请求 4、关闭弹框】*********************/
                        form.on('submit(entInfoSubmit)', function(data) {
                            var params = data.field;
                            console.log(params);
                                params["lptype"] = 3;
                                system.post("/car/updateEntInfo", params, function (data) {
                                    if (data.code == "200") {
                                        system.tip('保存成功!', 1);
                                        dyEntInfo.initDomainTable();
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
                        dyEntInfo.initAreaProv(system.cache[data.value],"xname")

                    })
                    //市联动
                    form.on('select(proname)',function(data){
                        dyEntInfo.initAreaProv(system.cache[data.value],"cityname")

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
    dyEntInfo.initDomainTable();
    dyEntInfo.initBillTable(2);

    dyEntInfo.listenFun();
    //dyEntInfo.saveDomain();
    //修改操作

    //监听提交
});
