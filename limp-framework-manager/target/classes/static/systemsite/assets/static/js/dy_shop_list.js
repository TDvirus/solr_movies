

/**
 * 用户相关操作
 * @type {{initDomainTable: Function}}
 */
var shopList={
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
		//工具栏事件绑定
        //删除操作
        $("#"+shopList.cons.tableId).LPClickDelegate(".del",function(){
            var delId=$(this).attr("data-value");
            system.confirm("",{title:"删除提示"},function(index){
                system.post("/car/partStore/"+delId,{type:2},function(data){
                    if(data.code=="200") {
                        system.tip("删除成功",1);
                        shopList.initDomainTable()
                    }else{
                        system.tip("删除失败，请重试！",0)
                    }
                })
            },function(index){ })
        })
        $("#"+shopList.cons.tableId).LPClickDelegate(".jia",function(){
            var dataId=$(this).parent().attr("data-id");
            var ivalue=$(this).parent().find("input").attr("value");
            //赋值
            var endV=parseInt(ivalue)+1;

            $(this).parent().find("input").attr("value",endV);

            $(this).parent().find("input").attr("value",endV);
            system.post("/car/goodsNumVer",{storeId:dataId,type:3,num:endV},function(data){
                if(data.code=="200") {
                    console.log(data)
					$("#"+dataId+"_price").html(data.result);
                    shopList.initPrice();

                }else{
                    system.tip("失败，请重试！",0)
                }
            })
        })
        $("#"+shopList.cons.tableId).LPClickDelegate(".jian",function(){
        	//操作零件ID
            var dataId=$(this).parent().attr("data-id");
            //input的数值
            var ivalue=$(this).parent().find("input").attr("value");
            if(ivalue==1){
            	system.tip("数量最低为1",0)
            	return;
			}
            //赋值
			var endV=parseInt(ivalue)-1;
            $(this).parent().find("input").attr("value",endV);
			system.post("/car/goodsNumVer",{storeId:dataId,type:3,num:endV},function(data){
				if(data.code=="200") {
                    $("#"+dataId+"_price").html(data.result);
                    shopList.initPrice();


                }else{
					system.tip("失败，请重试！",0)
				}
			})
        })


		//修改操作
		$("#"+shopList.cons.tableId).LPClickDelegate(".update",function(){
			var updId=$(this).attr("data-value");
			shopList.updateDomain(updId);
		})
		//选中取消
		$("#"+shopList.cons.tableId).LPClickDelegate(".layui-unselect",function(){
			//发送 单价（品质） * 数量（整数）* 小计划
			//选中的ID
            // var optId=$(this).attr("data-id");
                //取消操作
			setTimeout(function(){
                var  arrIds= $("#"+shopList.cons.tableId).LPTableCheckedIds();
                console.log(arrIds)
                if(arrIds.length==0){
                    arrIds="";
                }else{
                    arrIds=arrIds.join(",");
                }
                system.post("/car/goodsCheck", {storeIds:arrIds,type:3}, function (data) {
                    if(data.code=="200"){
                        // 变动价格
						console.log(1);
                        shopList.initPrice();
                    }
                })
			},1000)

			 var updId=$(this).attr("data-id");
			console.log(updId)
		})
		//提交订单信息|前后端进行验证
		$("#order-btn-form").LPClickDelegate(".order-btn",function(){
            var  arrIds= $("#"+shopList.cons.tableId).LPTableCheckedIds();

            console.log(arrIds);
            var params={};
			params["type"]=3;
			// system.post("/system/user",params,function(data){
			// 	if (data.code == "200") {
			// 		system.tip('修改成功!',1);
			// 	} else {
			// 		system.tip('操作失败，请刷新后重试!', 0);
			// 	}
			// });
		})
	},

	/**
	 * 初始化用户信息
	 */
	initDomainTable:function(params){
		var option={
			async:true,//action请求是否异步，true为为异步请求【默认为true异步请求】
			pageFlay:false,//是否分页，默认为true 进行分页；为false则不进行分页
			//【浏览器缓存】是否需要缓存  会缓存到system.cache[""]
			cache:true,
			//缓存的key值，取值方法 system.cache["id"]
			cacheKey:"id",
			//控制全局table样式，td【thStyle】和th【tdStyle】；优先级低于单个表格设置的样式【低于colModel中style和styleTh的样式】
			table:{thStyle:"background:#EEEEEE;text-align:left;white-space: nowrap;cursor:pointer",tdStyle:"text-align:left;background:#FCFCFC"},
			colModel : [
				{key :'id',name:"checkbox",width : 35},
				// {key :'NO.',name:"序号",width : 25},
				{key :'partno',name : '零件编号',style:'max-width:100px',formatter:function(partno){
                    return '<span ">'+partno+'</span>'
                }},
				{key :'partname',name : '零件名称',style:'max-width:100px',formatter:function(partname){
                    return '<span>'+partname+'</span>'
                }},
				{key :'phone',name : '品质',formatter:function(price){
                    return '<span>默认价格</span>'
                }},

				// {key :'name',name : '数量',sort:'name'},

                {key :'choosePrice',name : '单价',formatter:function(price){
                    return '<span>'+price+'</span>'
                }},

				{key :'name',name : '购买数量',sort:'name',formatters:function(domain){
					return '<div class="order-num-div" data-id="'+domain.id+'">' +
						'<a href="javascript:void(0);"  class="jian">-</a>' +
						'<input autocomplete="off" type="text" class="order-changnum" value="'+domain.buyNum+'">' +
						'<a href="javascript:void(0);" class="jia" >+</a>' +
						'</div>';
				}},
                {key :'name',name : '小计',sort:'name',formatters:function(domain){
                	var id=domain.id+"_price";
                	var totalPrice=parseInt(domain.price1)*parseInt(domain.buyNum);
                    return '<span style="color: #F0811C;"id="'+id+'">'+totalPrice+'</span>'
                }},

				{key :'OP',name : '操作',formatters:function(domain){
					var itemObj=$('<span data-value="'+domain.id+'"></span>');
					itemObj.append('<a title="删除" class="del"  href="javascript:;" data-value="'+domain.id+'" > 删除</a><br><br>')
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
			var curNum=$("#"+shopList.cons.tablePageId+" .current").attr("data-value");
			params['page']=system.isBlank(curNum)?1:curNum;
		}
		params['type']=4;
		$("#"+shopList.cons.tableId).LPTable("/car/myShopCar",params,option);

		//加载选中状态|||||
		setTimeout(function(){
            $("#" + shopList.cons.tableId + " .layui-form-checkbox").each(function () {
                console.log(check)

                var shopId=$(this).attr("data-id");
                var check=system.cache[shopId];
                if(check!=undefined&&check.checked==1){
                    $(this).addClass("layui-form-checked");
                }else{
                    $(this).removeClass("layui-form-checked");

                }

            });

        },1000)


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
                            if(shopList.clickTreeId){
                                var obj=system.cache[shopList.clickTreeId];
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
    initPrice:function(page){
        system.post("/car/myShopCar",{type:4},function(data){
                $("#goodsNum").html(data.ext.totalNum);
                $("#goodsPrice").html("¥"+data.ext.totalPrice);
            }

        )
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
									shopList.initDomainTable();
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


	}

}
layui.use(['laydate','jquery','form'], function() {
	var $ = layui.jquery;
	var form = layui.form;

	//初始化表格数据
	shopList.initDomainTable();
	//绑定监听类方法
	shopList.listenFun();
	shopList.initPrice();

	// shopList.initTree();


})
