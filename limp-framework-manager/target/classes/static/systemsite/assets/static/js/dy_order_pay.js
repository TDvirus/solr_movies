/**
 * 订单支付相关操作
 * @type {{initDomainTable: Function}}
 */
var orderPay={
    clickTreeId:"",
	//汇总所有相关的静态变量，统一全局调用，便于修正ID
	cons:{
		//表格ID
		tableId:"carSearchTable",
		//分页id
		tablePageId:"carSearchTablePage"
	}
,

    /**
	 * 事件监听函数：用户监听绑定的事件
	 */
	listenFun:function(){

		//选择地址
		$("#pay-addr-form").LPClickDelegate(".addr-item",function(){
			// 定义点击id
			var dataId=$(this).attr("data-id");
			//变更地址
			var domain=system.cache[dataId];
                $("#order-addr1").html(domain.proname+domain.cityname+domain.addr);
                $("#order-addr2").html(domain.name+domain.telno);
                $("#addr-id").html(domain.id);

            //切换选中状态
			$("#pay-addr-form .addr-item").each(function(){
                $(this).removeClass("click-addr");
            })
			if($(this).hasClass("click-addr")){
                $(this).removeClass("click-addr")
			}else{
                $(this).addClass("click-addr")
            }

		})

		//提交订单
		$("#order-form").LPClickDelegate(".order-btn",function(){
			// var updId=$(this).attr("data-value");
			var addrId=$("#addr-id").html();
			console.log(addrId)
			//TODO
            // orderInit
			// console.log(updId);
			var price=$("#tprice2").html();
			 price=price.replace("¥","");
			system.post("/car/orderInit",{type:1,receiveId:addrId,totalPrice:price},function(data){
				if(data.code=="200"){
					window.location.href='/page/car/orderList'
				}
			})
            // click-addr

		})

	},

	/**
	 * 初始化订单信息
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
				// {key :'id',name:"checkbox",width : 35},
				// {key :'NO.',name:"序号",width : 25},
                {key :'partno',name : '零件编号',sort:"account",formatter:function(partno){
                    return '<span>'+partno+'</span>'
                }},
                {key :'partname',name : '零件名称'},


                {key :'choosePrice',name : '单价',formatter:function(price){
                    return '<span>'+price+'</span>'
                }},

				{key :'name',name : '购买数量',sort:'name',formatters:function(domain){
					return domain.buyNum;
				}},
                {key :'name',name : '小计',sort:'name',formatters:function(domain){
                	var id=domain.id+"_price";
                	var totalPrice=parseInt(domain.price1)*parseInt(domain.buyNum);
                    return '<span style="color: #F0811C;"id="'+id+'">'+totalPrice+'</span>'
                }}


			]

		};

		if(system.isBlank(params)){
			params={state:1};
		}
		if(system.isBlank(params['page'])){
			//获取当前页数
			var curNum=$("#"+orderPay.cons.tablePageId+" .current").attr("data-value");
			params['page']=system.isBlank(curNum)?1:curNum;
		}
		params['type']=4;
		params['checkedType']=1;
		$("#"+orderPay.cons.tableId).LPTable("/car/myShopCar",params,option);

		//加载选中状态|||||
		setTimeout(function(){
            $("#" + orderPay.cons.tableId + " .layui-form-checkbox").each(function () {
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


	//加载地区
    initAddr:function(page){
		system.post("/car/addrInfo",{type:4,limit:3,page:1},function(data){
			console.log(data)
			$("#addr-list").html(" ");
			var item=$("#addr-list");

			for(var  m in data.result.dataList){
				var domain=data.result.dataList[m];
				system.cache[domain.id]=domain;
				var cla=domain.noone==1 ? '<span style="float: right;color: #F0811C">默认地址</span>':'';
				var click=domain.noone==1 ? 'click-addr':'';
				item.append('<div class="layui-col-xs4">' +
					'<div data-id="'+domain.id+'" class="addr-item '+click+'" >' +
					'<div class="addr-name"><span>'+domain.name+'</span> '+cla+'</div>\n' +
                    '                                        <div class="addr-info-choose">\n' +
                    '                                            <div>'+domain.proname+domain.cityname+'' +
                    '                                             <div>'+domain.addr+'</div>\n' +
                    '                                              <div>'+domain.telno+'</div>\n' +
                    '                                        </div>\n' +
                    '\n' +
                    '                                    </div>\n' +
                    '                                    </div>\n' +
                    '                                </div>');
				if(domain.noone==1){
                    $("#order-addr1").html(domain.proname+domain.cityname+domain.addr);
                    $("#order-addr2").html(domain.name+domain.telno);
                    $("#addr-id").html(domain.id);
				}
			}
            }

		)
},
    /**
	 * 显示总价
     * @param page
     */
    initPrice:function(page){
		system.post("/car/myShopCar",{type:4,checkedType:1},function(data){
			$("#tprice1").html("¥"+data.ext.totalPrice);
			$("#tprice2").html("¥"+data.ext.totalPrice);
            }

		)
}
	/**
	 *
	 */

}
layui.use(['laydate','jquery','form'], function() {
	var $ = layui.jquery;
	var form = layui.form;

	//初始化表格数据
	orderPay.initDomainTable();
	orderPay.initAddr();
	orderPay.initPrice();
	//绑定监听类方法
	orderPay.listenFun();



})
