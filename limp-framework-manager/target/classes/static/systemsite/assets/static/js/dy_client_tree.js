

/**
 * 地区信息相关操作
 * @type {{initDomainTable: Function}}
 */
var clientTree={

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
                handleDelDomains:function(){
                    var  arrIds= $("#"+sysArea.cons.tableId).LPTableCheckedIds();
                    if(arrIds.length==0){
                        system.tip("请选择删除的记录",0);
                        return;
                    }
                    //开始删除
                    system.confirm("",{title:"删除提示"},function(index){
                        system.post("/sys/area/"+arrIds.join(","),{type:2},function(data){
                            if(data.code=="200") {
                                sysArea.initDomainTable();
                                setTimeout(system.tip("删除成功",1),3000)
                            }else{
                                system.tip("删除失败，请重试！",0)
                            }
                        })
                    },function(index){ })
                }
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
        //删除操作
        $("#"+sysArea.cons.tableId).LPClickDelegate(".del",function(){
            var delId=$(this).attr("data-value");
            system.confirm("",{title:"删除提示"},function(index){
                system.post("/sys/area/"+delId,{type:2},function(data){
                    if(data.code=="200") {
                        system.tip("删除成功",1);
                        sysArea.initDomainTable({parentId:system.isBlank(sysArea.clickTreeId)?"0":sysArea.clickTreeId});
                        //重新加载树形结构
                        sysArea.initTree();
                    }else{
                        system.tip("删除失败，请重试！",0)
                    }
                })
            },function(index){ })
        })
        //修改操作
        $("#"+sysArea.cons.tableId).LPClickDelegate(".update",function(){
            var updId=$(this).attr("data-value");
            sysArea.saveDomain(updId);
        })//修改操作


        //更改状态
        $("#"+sysArea.cons.tableId).LPClickDelegate(".state",function(){
            var detailId=$(this).parent().attr("data-value");
            var params={};
            params["type"]=3;
            system.post("/sys/area/"+detailId+"/state/1",params,function(data){
                if (data.code == "200") {
                    sysArea.initDomainTable();
                    system.tip('修改成功!',1);
                } else {
                    system.tip('操作失败，请刷新后重试!', 0);
                }
            });
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
            console.log("area--data")
            console.log(data)

        })
    }  ,
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
            cacheKey:"code",
            //控制全局table样式，td【thStyle】和th【tdStyle】；优先级低于单个表格设置的样式【低于colModel中style和styleTh的样式】
            table:{thStyle:"text-align:left;white-space: nowrap;cursor:pointer",tdStyle:"text-align:left;"},
            colModel : [
                {key :'NO.',name : '序号'},
                {key :'name',name : '地区名称'},
                {key :'code',name : '地区编码',sort:'code'},
                {key :'pcode',name : '上级编码',sort:'pcode'},

                {key :'OP',name : '操作',formatters:function(domain){
                    var itemObj=$('<span data-value="'+domain.id+'"></span>');
                    if(domain.state==1){
                        itemObj.append('<a title="删除" class="del" href="javascript:;" data-value="'+domain.code+'" > <i class="layui-icon">&#xe640;</i> </a>')
                    }
                    itemObj.append('<a  title="编辑" class="update"  data-value="'+domain.code+'" href="javascript:;"> <i class="layui-icon">&#xe642;</i> </a>');
                    return itemObj;
                }
                }
            ]

        };

        if(system.isBlank(params)){
            params={};
        }
        if(system.isBlank(params['page'])){
            //获取当前页数
            var curNum=$("#"+sysArea.cons.tablePageId+" .current").attr("data-value");
            params['page']=system.isBlank(curNum)?1:curNum;
        }
        params['type']=4;
        $("#"+sysArea.cons.tableId).LPTable("/sys/areas",params,option);


    },

    /**
     * code为则为新增操作，有值则为修改操作
     * 新增/修改 实体类
     */
    saveDomain:function(code){
        system.open("<div id='lp'>"+$('#tkAddArea').html()+"</div>",{title:system.isBlank(code)?"新增地区信息":"修改地区信息",area:["630px","450px"]},function(layern,index){
            layui.use(['form','jquery'], function() {
                var form = layui.form;
                setTimeout(function(){
                    console.log(sysArea.clickTreeId);
                    //重新渲染

                    var areaObj={};
                    //新增操作
                    if(system.isBlank(code)){
                        //点击了行政区划管理
                        if(sysArea.clickTreeId){
                            var obj=system.cache[sysArea.clickTreeId];
                            console.log(obj);
                            areaObj={pcode:obj.code,parentName:obj.name}
                        }else {
                            areaObj={pcode:"0",parentName:"一级地区等级"}
                        }
                    }else{
                        areaObj=system.cache[code];
                        var pname=system.isBlank(system.cache[areaObj.pcode])?"一级地区等级":system.cache[areaObj.pcode].name;
                        areaObj['parentName']=pname;
                    }
                    form.render();
                    form.val("addFilter",areaObj);

                    /******************自定义验证规则*************/
                    form.verify({
                        name: function(value) {
                            if(system.isBlank(value)) {
                                return '请输入地区名称';
                            }
                        }
                    });

                    /***********************监听提交操作【1、获取用户填写信息 2、组装信息 3、提交ajax请求 4、关闭弹框】*********************/
                    form.on('submit(addFilterSubmit)', function(data) {
                        var params = data.field;
                        if(system.isBlank(code)) {
                            params["lptype"] = 1;
                            // params["name"]="zs";
                            system.post("/sys/area", params, function (data) {
                                if (data.code == "200") {
                                    layer.close(index);
                                    system.tip('新增成功!', 1);
                                    sysArea.initDomainTable({parentId:system.isBlank(sysArea.clickTreeId)?"0":sysArea.clickTreeId});
                                    //重新加载树形结构
                                    sysArea.initTree();
                                } else {
                                    system.tip('操作失败，请刷新后重试!', 0);
                                }
                            });
                        }
                        //修改操作
                        else{
                            params["lptype"] = 3;
                            system.post("/sys/area", params, function (data) {
                                if (data.code == "200") {
                                    layer.close(index);
                                    system.tip('修改成功!', 1);
                                    sysArea.initDomainTable({parentId:system.isBlank(sysArea.clickTreeId)?"0":sysArea.clickTreeId});
                                    //重新加载树形结构
                                    sysArea.initTree();
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
    // clientTree.initDomainTable({pcode:"0"});
    //绑定监听类方法
    // sysArea.listenFun();
    clientTree.initTree();

     $("#yy").load("A.html");


});
