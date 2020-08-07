

/**
 * 地区信息相关操作
 * @type {{initDomainTable: Function}}
 */
var dyAddrList={

    /**
     * 事件监听函数：用户监听绑定的事件
     */
    listenFun:function(){
        //工具栏事件绑定
        var tools_lp =new Vue({
            el:"#areaTableMain .table-tools",
            data:{},
            methods:{
                //新增操作
                handleSaveDomain:function(){
                    sysArea.saveDomain();
                },
                //删除操作
            }
        });
        //搜索
        var search_lp =new Vue({
            el:"#areaTableMain .table-search",
            data:{},
            methods:{
                //提交
                handleSeach:function(){
                    var param={};
                    if(!system.isBlank($("#perms_serach_code").val())){
                        param['code']=$("#perms_serach_code").val();
                    }

                    if(!system.isBlank($("#perms_serach_name").val())){
                        param['name']=$("#perms_serach_name").val();
                    }

                    sysArea.initDomainTable(param);
                }
            }
        })


        //更改状态
        $("#addr-list").LPClickDelegate(".addr-opt",function(){
            var detailId=$(this).attr("data-value");
            dyAddrList.saveDomain(detailId)

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
                dyAddrList.initDomainTable();
            }

        })

    },

    /**
     * 初始化地区信息
     */
    initDomainTable:function(params){
        system.post("/car/addrInfo",{type:4},function(data){
            if(data.code=="200"){
                //初始化 form表单
                layui.use(['laydate','jquery','form'], function() {
                    var $ = layui.jquery;
                    var form = layui.form;

                    $(".addr-list").html("");
                    var items=$(".addr-list");
                    for(var m in data.result.dataList){
                        var domain=data.result.dataList[m];
                        system.cache[domain.id]=domain;
                        var firstAddr=domain.noone=='1'?'<span class="layui-badge" >默认地址</span>':'';
                        items.append('<div class="addr-item"><div class="addr-name">' +
                            '<span>'+domain.name+'</span>'+firstAddr+'' +
                            '<span class="addr-opt" data-value="'+domain.id+'">修改</span> <span class="addr-no" onclick=dyAddrList.setfisrtAddr("'+domain.id+'")>设为默认地址</span> </div><div class="addr-phone">'+domain.telno+'</div>' +
                            '<div class="addr-info">'+domain.proname+domain.cityname+domain.xname+domain.addr+'</div></div>');
                    }
                    form.val('userFilter', {
                        "phone": data.result.phone // "name": "value"
                        , "email": data.result.email
                        , "id": data.result.id
                        , "rc3":data.result.rc3+""
                    });
                    form.render();
                })
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
                        dyAddrList.initAreaProv(system.cache[data.value],"xname")

                    })
                    //市联动
                    form.on('select(proname)',function(data){
                        dyAddrList.initAreaProv(system.cache[data.value],"cityname")

                    })

                })
        })
    },

    /**
     * code为则为新增操作，有值则为修改操作
     * 新增/修改 实体类
     */
    saveDomain:function(code){
        var  addrList=$("#addr-list .addr-item");
        console.log(addrList.length)

        if(addrList.length==1||addrList.length>1){
            system.tip("只可添加一个地址",1);
            return ;
        }
        system.open("<div id='lp'>"+$('#tkAddArea').html()+"</div>",{title:system.isBlank(code)?"新增收货地址":"修改收货地址",area:["470px","360px"]},function(layern,index){
            layui.use(['form','jquery'], function() {
                var form = layui.form;
                setTimeout(function(){
                    var addrInfo={};
                    //新增操作
                    if(system.isBlank(code)){
                        dyAddrList.initAreaProv(0,"proname")
                    }else {
                        addrInfo = system.cache[code];
                    }
                        console.log("-----")
                        console.log(code)
                        console.log(addrInfo)
                    form.render();
                    form.val("addFilter",addrInfo);

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
                    form.on('submit(addFilterSubmit)', function(data) {
                        var params = data.field;
                        if(system.isBlank(code)) {
                            params["lptype"] = 1;
                            system.post("/car/addrInfo", params, function (data) {
                                if (data.code == "200") {
                                    layer.close(index);
                                    system.tip('新增成功!', 1);
                                    dyAddrList.initDomainTable();
                                } else {
                                    system.tip('操作失败，请刷新后重试!', 0);
                                }
                            });
                            return false;
                        }
                        //修改操作
                        else{
                            params["lptype"] = 3;
                            system.post("/car/addrInfo", params, function (data) {
                                if (data.code == "200") {
                                    layer.close(index);
                                    system.tip('修改成功!', 1);
                                    dyAddrList.initDomainTable();
                                } else {
                                    system.tip('操作失败，请刷新后重试!', 0);
                                }
                            });
                        }

                        //阻止提交表单
                        return false;
                    })
                },200)

            })
        });
    }

}
layui.use(['laydate','jquery','form'], function() {
    var $ = layui.jquery;
    var form = layui.form;
    //初始化表格数据
    dyAddrList.initDomainTable();
    dyAddrList.initAreaProv("0","proname")

    dyAddrList.listenFun();
    //dyAddrList.saveDomain();
    //修改操作

    //监听提交
    form.on('submit(demo1)', function(data){
        var param=data.field;
        param["type"]=3;
        console.log(param);
        system.post("/carAuth/loginUserInfo",param,function(data) {
            if(data.code=="200"){
                system.tip("保存成功",1)
            }
        })
            return false;
    });
});
