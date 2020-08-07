

/**
 * 用户相关操作
 * @type {{initDomainTable: Function}}
 */
var goodsStore={
    clickTreeId:"",
    //汇总所有相关的静态变量，统一全局调用，便于修正ID
    cons:{
        //表格ID
        tableId:"carSearchTable",
        //分页id
        tablePageId:"carSearchTablePage"
    },
    handleSeach:function(){
        var param={};
        if(!system.isBlank($("#user_serach_account").val())){
            param['account']=$("#user_serach_account").val();
        }

        goodsStore.initDomainTable(param);
    }
    ,

    /**
     * 事件监听函数：用户监听绑定的事件
     */
    listenFun:function(){
        //工具栏事件绑定
        var tools_lp =new Vue({
            el:"#carSearchTableMain .table-tools",
            data:{},
            methods:{
                //新增操作
                handleSaveDomain:function(){
                    goodsStore.saveDomain();
                },
                //删除操作
                handleDelDomains:function(){
                    var  arrIds= $("#"+goodsStore.cons.tableId).LPTableCheckedIds();
                    if(arrIds.length==0){
                        system.tip("请选择删除的记录",0);
                        return;
                    }
                    //开始删除
                    system.confirm("",{title:"删除提示"},function(index){
                        system.post("/system/user/"+arrIds.join(","),{type:2},function(data){
                            if(data.code=="200") {
                                goodsStore.initDomainTable();
                                setTimeout(system.tip("删除成功",1),3000)
                            }else{
                                system.tip("删除失败，请重试！",0)
                            }
                        })
                    },function(index){ })
                },
                //更新操作
                updateSaveDomain:function(){
                    goodsStore.updateDomain();
                }
            }
        })

        //删除操作
        $("#"+goodsStore.cons.tableId).LPClickDelegate(".store-del",function(){
            var delId=$(this).attr("data-value");
            system.confirm("",{title:"删除提示"},function(index){
                system.post("/car/partStore/"+delId,{type:2},function(data){
                    if(data.code=="200") {
                        system.tip("删除成功",1);
                        goodsStore.initDomainTable()
                    }else{
                        system.tip("删除失败，请重试！",0)
                    }
                })
            },function(index){ })
        })
        //修改操作
        $("#"+goodsStore.cons.tableId).LPClickDelegate(".shop-btn",function(){
            var updId=$(this).attr("data-value");
            system.post("/car/addShopCar",{type:3,id:updId},function(data){
                if(data.code=="200") {
                    system.tip("已加入",1);
                    setTimeout(function(){
                        goodsStore.initDomainTable();
                    },2000)
                }else{
                    system.tip("加入失败，请重试！",0)
                }
            })

        })//修改操作

    },

    /**
     * 初始化用户信息
     */
    initDomainTable:function(params){
        var option={
            async:true,//action请求是否异步，true为为异步请求【默认为true异步请求】
            pageFlay:true,//是否分页，默认为true 进行分页；为false则不进行分页
            //【浏览器缓存】是否需要缓存  会缓存到system.cache[""]
            cache:false,
            //缓存的key值，取值方法 system.cache["id"]
            cacheKey:"id",
            //控制全局table样式，td【thStyle】和th【tdStyle】；优先级低于单个表格设置的样式【低于colModel中style和styleTh的样式】
            table:{thStyle:"background:#EEEEEE;text-align:center;white-space: nowrap;cursor:pointer",
				tdStyle:"text-align:center;background:#FCFCFC;"},
            colModel : [
                // {key :'id',name:"checkbox",width : 35},
                {key :'NO.',name:"序号",width : 25},
                {key :'vin',name : 'VIN',sort:"account",formatter:function(vin){
                    var vinStr=system.isBlank(vin)?"暂无":vin;
                    return '<span>'+vinStr+'</span>'
                }},
                {key :'partno',name : '零件编号',sort:"account",formatter:function(partno){
                    return '<span>'+partno+'</span>'
                }},
                {key :'partname',name : '零件名称'},
                {key :'subgestNo',name : '数量',formatter:function(price){
                    return '<span>'+5+'</span>'
                }},
                {key :'priceno',name : '4S参考价',sort:'name'},

                {key :'name',name : '价格',sort:'name',formatters:function(domain){
                    var spanStr='<span class="span-type">品牌一¥'+domain.price1+'</span><br>';
                    if(!system.isBlank(domain.price2)){
                        spanStr+='<span class="span-type">品牌二¥'+domain.price2+'</span>';
                    }if(!system.isBlank(domain.price3)){
                        spanStr+='<span class="span-type">品牌三¥'+domain.price3+'</span>';
                    }
                    return spanStr;
               }},
                {key :'OP',name : '操作',style:"text-align:center",formatters:function(domain){
                    var itemObj=$('<span data-value="'+domain.id+'"></span>');
                    itemObj.append('<a  title="加入购物车" class="shop-btn"  data-value="'+domain.id+'" href="javascript:;">加入购物车</a><br><br>');

                    itemObj.append('<a title="删除" class="store-del" href="javascript:;" data-value="'+domain.id+'" > 删除</a><br><br>')
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
            var curNum=$("#"+goodsStore.cons.tablePageId+" .current").attr("data-value");
            params['page']=system.isBlank(curNum)?1:curNum;
        }
        params['type']=4;
        $("#"+goodsStore.cons.tableId).LPTable("/car/myStores",params,option);


    },

    /**
     * 新增实体类
     */
    saveDomain:function(code){
        system.open("<div id='lp'>"+$('#tkAddUser').html()+"</div>",{title:"新增人员",area:["500px","450px"]},function(layern,index){
            layui.use(['form','jquery',"laydate"], function() {
                var form = layui.form;
                var laydate = layui.laydate;
                //需要延时
                setTimeout(function(){

                    var codeObj={};
                    //新增操作
                    if(system.isBlank(code)){
                        //点击了单位部门管理
                        if(goodsStore.clickTreeId){
                            var obj=system.cache[goodsStore.clickTreeId];
                            codeObj={unitcode:obj.code,parentName:obj.name}
                        }else {
                            codeObj={unitcode:"0",parentName:"一级目录"}
                        }
                    }else{
                        codeObj=system.cache[code];
                        var pname=system.isBlank(system.cache[codeObj.code])?"一级目录":system.cache[codeObj.parentId].name;
                        codeObj['parentName']=pname;
                    }
                    //重新渲染
                    form.render();
                    form.val("addFilter",codeObj);
                    // form.render(null, 'addFilter');

                    //执行一个laydate实例 ： /*指定元素*/
                    laydate.render({ elem: '#lp_expiredate'});

                    /******************自定义验证规则*************/
                    form.verify({
                        account: function(value) {
                            if(value.length < 5) {
                                return '账号至少得5个字符';
                            }
                        },
                        password: [/(.+){6,12}$/, '密码必须6到12位'],
                        repassword: function(value) {
                            if($('#lp_password').val() != $('#lp_repassword').val()) {
                                return '两次密码不一致';
                            }
                        }
                    });
                    //失去焦点时判断值为空不验证，一旦填写必须验证
                    $('input[name="email"]').blur(function(){
                        //这里是失去焦点时的事件
                        if($('input[name="email"]').val()){
                            $('input[name="email"]').attr('lay-verify','email');
                        }else{
                            $('input[name="email"]').removeAttr('lay-verify');
                        }
                    });
                    $('#lp_account').blur(function(){
                        //这里是失去焦点时的事件|todo验证用户是否存在
                        /*	if(!system.isBlank($('#lp_account').val())){
                                system.post("/system/users",{type:4,account:$('#lp_account').val()},function(data){
                                console.log(data);
                                })
                            }*/
                    });

                    /***********************监听提交操作【1、获取用户填写信息 2、组装信息 3、提交ajax请求 4、关闭弹框】*********************/
                    form.on('submit(addFilterSubmit)', function(data) {
                        //console.log(data.field);
                        var params = data.field;
                        params["type"]=1;
                        //checkbox swith开关处理：选中传递过来的为on此时变为1，其他的为0
                        params["rc3"]=params["rc3"]=="on"?1:0;
                        params["isautoexpire"]=params["isautoexpire"]=="on"?1:0;
                        //异步新增人员
                        system.post("/system/user",params,function(data){
                            if (data.code == "200") {
                                layer.close(index);
                                system.tip('新增成功!',1);
                            } else {
                                system.tip('操作失败，请刷新后重试!', 0);
                            }
                        });
                        //阻止提交表单
                        return false;
                    })

                },200)

            })
        });
    },
    /**
     *
     */
    updateDomain:function(domainID){
        var domainInfo=system.cache[domainID];
        //判断是否为空
        if(system.isBlank(domainInfo)){
            return;
        }
        system.open("<div id='lp'>"+$('#tkUpdateUser').html()+"</div>",
            {title:"修改人员"},function(layern,index) {
                layui.use(['form', 'jquery', "laydate"], function () {
                    var form = layui.form;
                    var laydate = layui.laydate;
                    //需要延时
                    setTimeout(function(){
                        form.render() //重新渲染
                        form.val("updateFilter", domainInfo);
                        laydate.render({ elem: '#lp_expiredate_1',format: 'yyyy-MM-dd HH:mm:ss'});
                        /*
                         $("#XXX").prop('checked',true);*/
                        form.on('submit(updateFilterSubmit)', function(data) {
                            var params = data.field;
                            params["type"]=3;
                            //checkbox swith开关处理：选中传递过来的为on此时变为1，其他的为0
                            params["rc3"]=params["rc3"]=="on"?1:0;
                            params["isautoexpire"]=params["isautoexpire"]=="on"?1:0;
                            //异步新增人员
                            console.log(params)
                            system.post("/system/user",params,function(data){
                                if (data.code == "200") {
                                    goodsStore.initDomainTable();
                                    layer.close(index);
                                    system.tip('修改成功!',1);
                                } else {
                                    system.tip('操作失败，请刷新后重试!', 0);
                                }
                            });
                            //阻止提交表单
                            return false;
                        })

                    },300)

                })
            })


    },
    setUserRoles:function(userId){

        var domainInfo=system.cache[userId];
        //判断是否为空
        if(system.isBlank(domainInfo)){
            return;
        }
        system.open("<div id='lp'>"+$('#tkSetUserRoles').html()+"</div>",
            {title:"设置角色",area:['500px','300px']},function(layern,index) {
                layui.use(['form', 'jquery'], function () {
                    var form = layui.form;
                    //加载角色
                    var roleIds="";
                    system.post("/system/user/"+userId+"/roles",{type:4},function(data){
                        if(data.code=="200"){
                            $("#roles-option").html(' ');
                            $("#roles-option").append('<option value="">请选择角色</option>');
                            for(var m in data.result){
                                var domain=data.result[m];
                                //var check=domain.checked
                                if(domain.checked){
                                    roleIds=domain.id;
                                    $("#roles-option").prepend('<option checked="'+domain.checked+'" value="'+domain.id+'">'+domain.name+'</option>');
                                }else{
                                    $("#roles-option").append('<option checked="'+domain.checked+'" value="'+domain.id+'">'+domain.name+'</option>');
                                }
                            }
                        }
                        domainInfo['roleIds']=roleIds;
                        form.val("setUserRoleFilter", domainInfo);
                        form.render() //重新渲染
                        /*
                         $("#XXX").prop('checked',true);*/
                        form.on('submit(setUserRoleFilterSubmit)', function(data) {
                            var params = data.field;
                            params["type"]=1;
                            system.post("/system/user/" + data.field.id + "/roles", params, function (data) {
                                if (data.code == "200") {
                                    system.tip('修改成功!', 1);
                                    layer.close(index);
                                } else {
                                    system.tip('操作失败，请刷新后重试!', 0);
                                }
                            });
                            //阻止提交表单
                            return false;
                        })

                    })
                })
            })
    },
    initDetail:function(detailId){
        var options=[
            [{key:"account",name:"账号"},{key:"name",name:"昵称"}],
            [{key:"isautoexpire",name:"自动到期"},{key:"expiredate",name:"过期时间"}],
            [{key:"phone",name:"手机"},{key:"email",name:"邮箱"}],
            [{key:"state",name:"用户状态"},{key:"email",name:"邮箱"}],
            [{key:"rc3",name:"是否加密"},{key:"utype",name:"用户类型"}],
            [{key:"icn",name:"身份标识"},{key:"unitcode",name:"单位编码"}],
            [{key:"conaccount",name:"关联账号"},{key:"keypath",name:"秘钥路径"}],
            [{key:"remarks",name:"备注"}],
        ];

        var endDiv=$('<div></div>');
        var div=$('<div class="weadmin-body"></div>');
        var form=$('<form class="layui-form" lay-filter="addFilter1"></form>');

        for(var m in options){
            var arr1=options[m];
            var item=$('<div class="layui-form-item"></div>');
            for(var n in arr1){
                var obj=arr1[n];
                item.append('<label  class="layui-form-label">'+obj.name+':</label>');
                item.append('<div class="layui-input-inline"><input type="text" style="background-color: #eee;"  disabled name="'+obj.key+'"  autocomplete="off" class="layui-input"></div>');
            }
            form.append(item);
        }

        div.append(form);
        endDiv.append(div);
        system.open(endDiv.html(),{title:"详细信息",area:["800px","450px"]},function(){
            layui.use(['form', 'jquery', "laydate"], function () {
                var form = layui.form;
                var laydate = layui.laydate;
                //需要延时
                setTimeout(function () {
                    form.render() //重新渲染
                    var data={account:"张三",name:"zs",class:"5年级",age:"23岁"}
                    form.val("addFilter1", system.cache[detailId]);
                }, 400)
            })

        })
    }
}
layui.use(['laydate','jquery','form'], function() {
    var $ = layui.jquery;
    var form = layui.form;

    //初始化表格数据
    goodsStore.initDomainTable();
    //绑定监听类方法
    goodsStore.listenFun();



})
