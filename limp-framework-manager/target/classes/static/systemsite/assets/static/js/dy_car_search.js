

/**
 * 用户相关操作
 * @type {{initDomainTable: Function}}
 */
var carSearch={
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

        carSearch.initDomainTable(param);
    }
,

    /**
	 * 事件监听函数：用户监听绑定的事件
	 */
	listenFun:function() {
        //工具栏事件绑定
        var tools_lp = new Vue({
            el: "#carSearchTableMain .table-tools",
            data: {},
            methods: {
                //新增操作
                handleSaveDomain: function () {
                    carSearch.saveDomain();
                },
                //删除操作
                handleDelDomains: function () {
                    var arrIds = $("#" + carSearch.cons.tableId).LPTableCheckedIds();
                    if (arrIds.length == 0) {
                        system.tip("请选择删除的记录", 0);
                        return;
                    }
                    //开始删除
                    system.confirm("", {title: "删除提示"}, function (index) {
                        system.post("/system/user/" + arrIds.join(","), {type: 2}, function (data) {
                            if (data.code == "200") {
                                carSearch.initDomainTable();
                                setTimeout(system.tip("删除成功", 1), 3000)
                            } else {
                                system.tip("删除失败，请重试！", 0)
                            }
                        })
                    }, function (index) {
                    })
                },
                //更新操作
                updateSaveDomain: function () {
                    carSearch.updateDomain();
                }
            }
        })

        //删除操作
        $("#" + carSearch.cons.tableId).LPClickDelegate(".del", function () {
            var delId = $(this).attr("data-value");
            system.confirm("", {title: "删除提示"}, function (index) {
                system.post("/system/user/" + delId, {type: 2}, function (data) {
                    if (data.code == "200") {
                        system.tip("删除成功", 1);
                        carSearch.initDomainTable()
                    } else {
                        system.tip("删除失败，请重试！", 0)
                    }
                })
            }, function (index) {
            })
        })
        //增加报价单
        $("#" + carSearch.cons.tableId).LPClickDelegate(".store-btn", function () {
            var updId = $(this).attr("data-value");
            var domain = system.cache[updId];
            if ($(this).hasClass("search-noclick-btn")) {
                system.tip("不可重复加入", 1);
                return;
            }
            $(this).addClass("search-noclick-btn");
            system.post("/car/partStore", {goodsId: updId, vin: domain.vin, storeType:1,type: 1}, function (data) {
                if (data.code == "200") {
                    system.tip("已加入", 1);

                }
            })
        })//修改操作
        $("#" + carSearch.cons.tableId).LPClickDelegate(".shop-btn", function () {
            var updId = $(this).attr("data-value");
            var domain = system.cache[updId];
            if ($(this).hasClass("search-noclick-btn")) {
                system.tip("不可重复加入", 1);
                return;
            }
            $(this).addClass("search-noclick-btn");
            system.post("/car/partStore", {goodsId: updId, vin: domain.vin, type: 1,storeType:2}, function (data) {
                if (data.code == "200") {
                    system.tip("已加入", 1);

                }
            })
        })//修改操作
    },
    /**
     * 加载部门信息管理 属性结构
     */
    initTree:function(){
        var setting = {
        	view:{showLine:false,
                showIcon:false },
            data: {
                key: {
                    title:"t"
                },
                simpleData: {
                    enable: true
                }
            },
            callback: {
                beforeClick: beforeClick,
                onClick: onClick,
                onRightClick: OnRightClick
            }
        };
        function OnRightClick(event, treeId, treeNode, clickFlag) {
            if(!system.isBlank(treeNode)){
                $("#mId").val(treeNode.id);
                //carSearch.initMenu(treeNode.id);
            }
            if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
                $.fn.zTree.getZTreeObj("treeDemo").cancelSelectedNode();
                showRMenu("root", event.clientX, event.clientY);
            } else if (treeNode && !treeNode.noR) {
                $.fn.zTree.getZTreeObj("treeDemo").selectNode(treeNode);
                showRMenu("node", event.clientX, event.clientY);
            }
        }
        function onBodyMouseDown(event){
            if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
                $("#rMenu").css({"visibility" : "hidden"});
            }
        }






        function beforeClick(treeId, treeNode, clickFlag) {
            console.log("[beforeClick ]" + treeNode.name );
            return (treeNode.click != false);
        }
        /**
         *clickFlag===1: 普通选中;clickFlag===0:取消选中 ;追加选中
         * @param event
         * @param treeId
         * @param treeNode
         * @param clickFlag
         */
        function onClick(event, treeId, treeNode, clickFlag) {
        	console.log("----------");
        	console.log(treeNode);
            if(1==clickFlag){
                //carSearch.initArea(main.areaData[treeNode.id]);
                carSearch.clickTreeId=treeNode.code;

                console.log(carSearch.clickTreeId);
                carSearch.initDomainTable({unitcode:treeNode.code});
            }
        }
        var nodes=new Array();
        system.post("/sys/codes",{dictCate:"0001",page:1,type:4,rows:1000},function(data){
            console.log("area--data")
            console.log(data)
            for(var i in data.result.dataList){
                var domain=data.result.dataList[i];
                //bak area数据
                system.cache[domain.id]=domain;
                var node={};
                var name=domain.dictName.length>8?domain.dictName.substr(0,8)+"...":domain.dictName;
                node["id"]=domain.id+"";
                node["pId"]=domain.pid+"";
                node["code"]=domain.dictKey+"";
                node["name"]=name+"";
                node["t"]=domain.dictName+"";
                node["open"]=false;
                if("0"==domain.pid){
                    node["open"]=true;
                }
                nodes.push(node);
            }
            carSearch.clickTreeId="";
            // zNodes=zNodes.concat(nodes)
            console.log("------------")
            console.log(nodes)
            $.fn.zTree.init($("#tree-list"), setting, nodes);
            $("#load_user").hide();
        })
    }  ,
	/**
	 * 初始化用户信息
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
			table:{thStyle:"text-align:center;white-space: nowrap;cursor:pointer;font-family: Microsoft YaHei;",tdStyle:"vertical-align:top;text-align:center;max-width:120px"},
			colModel : [
				// {key :'id',name:"checkbox",width : 35},
				// {key :'NO.',name:"序号",width : 25},
				{key :'partno',name : '零件编号'},
				{key :'partname',name : '零件名称'},
				{key :'subgestNo',name : '建议数量',formatter:function(price){
					var subgestNo=system.isBlank(price)?"暂无":price
                    return '<span>'+subgestNo+'</span>'
                }},
				{key :'priceno',name : '4s参考价格',formatter:function(price){
                    return '<span style="color: #EB0000">0</span>'
                }},
				{key :'name',name : '价格',sort:'name',formatters:function(domain){
					var spanStr='<span class="span-type">品牌一¥'+domain.price1+'</span><br>';
					if(!system.isBlank(domain.price2)){
                        spanStr+='<span class="span-type">品牌二¥'+domain.price2+'</span>';
					}if(!system.isBlank(domain.price3)){
                        spanStr+='<span class="span-type">品牌三¥'+domain.price3+'</span>';
					}
                    return spanStr;
                }},
                {key :'partExplain',name : '补充说明'},


				{key :'OP',name : '操作',style:"font-size: 12px;", formatters:function(domain){
					var itemObj=$('<span data-value="'+domain.id+'"></span>');
					itemObj.append('<a title="加入报价单" class="store-btn" href="javascript:;" data-value="'+domain.id+'" > 加入报价单</a><br><br>')
					itemObj.append('<a  title="编辑" class="shop-btn"  data-value="'+domain.id+'" href="javascript:;">加入购物车</a>');
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
			var curNum=$("#"+carSearch.cons.tablePageId+" .current").attr("data-value");
			params['page']=system.isBlank(curNum)?1:curNum;
		}
		params['type']=4;
		$("#"+carSearch.cons.tableId).LPTable("/car/partGoods",params,option);


	},
	initTreeTable:function(params){
		system.post("/sys/codes",{dictPvalue:"0001",page:1,type:4,rows:40},function(data){
			console.log("------------");
			$("#tree-list").html('');
			var items=$("#tree-list");
			for(var m in data.result.dataList){
				var  domain=data.result.dataList[m];
                items.append(' <li class="site-tree-noicon "><a href="#">' +
					'<cite>'+domain.dictdataName+'</cite><em>Getting Started</em></a></li>');
			}
			console.log(data);
		})


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
                            if(carSearch.clickTreeId){
                                var obj=system.cache[carSearch.clickTreeId];
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
									carSearch.initDomainTable();
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
	var searchKey=system.getQueryStringV(window.location.href,"searchKey");
	var param={};
	if(!system.isBlank(searchKey)&&searchKey.length>10){
        param["partno"]=searchKey;
	}
	console.log("rrrrrrrrrr")
	console.log(param)
	carSearch.initDomainTable(param);
	// carSearch.initTreeTable();
	//绑定监听类方法
	carSearch.listenFun();

	carSearch.initTree();


})
