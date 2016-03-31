var timeGroup,allTimeGroup,points;//事件传播峰值图



//加载舆情性质对比图===========================================================================================================================
var loadCompare=function(data){

    $.get("voice/showNature.do",data,function(data){

        var pfu=data.Point.FUtype ;
        var pzheng=data.Point.ZHENGtype;
        var pzhong=data.Point.ZHONGtype;


        /*需要判断是否为空*/
        var fu=data.Trend.FUtype.time_num;
        var zheng=data.Trend.ZHENGtype.time_num;
        var zhong=data.Trend.ZHONGtype.time_num;

        var times= _.keys(data.Trend.FUtype.time_num);
        //console.log(times)

        var startTime= _.first(times);
        var endTime= _.last(times);



        $('#compareTime').datetimepicker('setStartDate', startTime);
        $('#compareTime').datetimepicker('setEndDate', endTime);


        if(pfu){
            fu=getCompareData(fu,pfu.time_content);
        }else{
            fu=getCompareData(fu,[]);
        }

        if(pzheng){
            zheng=getCompareData(zheng,pzheng.time_content);
        }else{
            zheng=getCompareData(zheng,[]);
        }

        if(pzhong){
            zhong=getCompareData(zhong,pzhong.time_content);
        }else{
            zhong=getCompareData(zhong,[]);
        }


        console.log(fu,zheng,zhong)
        feelingsContrast(fu,zheng,zhong);
        showCompareKeyInfo(data.Point);


    },"json");

};




//舆情对比图数据构造
var getCompareData=function(p,key){
    console.log(p,key)
    var a=[];
    var b=[];
    var labelNum=0;
    var labelName=["A","B","C","D","E","F","G","H","I","K","I","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];
    //value:val.length,
    if(key){
        _.each(p,function(v,k){
            if(key[k]){
                b.push({
                    value:v,
                    symbol: 'emptypin',  // 数据级个性化拐点图形
                    symbolSize : 10,
                    data:key[k],
                    itemStyle: {        // 数据级个性化折线样式
                        normal: {
                            label : {
                                show: true,
                                formatter:function(e){
                                    return labelName[labelNum++];
                                },
                                position: 'inside',
                                textStyle : {
                                    fontSize : '14'
                                }
                            }
                        }
                    }
                });
            }else{
                b.push(v);
            }
            a.push(k);
        });
    }else{
        _.each(p,function(v,k){
            a.push(k);
            b.push(v);
        });
    }
    return[a,b];
};

// 舆情对比图
var feelingsContrast=function(fu,zheng,zhong){
    //console.log(fu[0],fu[1],zheng[1],zhong[1]);
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main5'));
    var option = {
        tooltip : {     //提示框
            trigger: 'axis',
            axisPointer:{
                type: 'line',
                lineStyle:{
                    color:"#000"
                }
                /*crossStyle: {
                    color: '#337ab7',
                    width: 1,
                    type: 'solid'
                },*/
            },
            formatter : function (params) {
                console.log(params)
                var res="";
                res+=params[0].name+"  "+getWeek(params[0].name);
                _.each(params,function(v,k){
                    res+="<br/>"+v[0]+" ："+(v.value.toFixed(2))+"%";
                });
                return res;
            }
        },
        legend: {
            data:['正面','负面','中性']
        },
        toolbox: {     //工具箱
            show : true,
            feature : {
                saveAsImage : {show: true}
            }
        },
        grid:{
            borderWidth:0
        },
        dataZoom: {
            show: true,
            height:30,
            //start:20,
            //end:50,
            backgroundColor:'#DFDFDF',
            handleColor:'#1E66D0',
            fillerColor:'rgba(144,197,237,0.2)'
        },
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data :fu[0],
                axisTick:{
                    show:true,
                    inside:true,
                    lineStyle:{
                        color:'#b6b6b6'
                    }
                },
                splitLine:{
                    show:false
                },
                splitArea:{
                    show:false
                },
                axisLine:{
                    show:true,
                    lineStyle:{
                        color:'#b6b6b6',
                        width:1
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel:{
                    formatter:function(v){
                        return v+"%";
                    }
                },
                splitLine:{
                    show:false
                },
                splitArea:{
                    show:false
                },
                axisLine:{
                    show:false
                }
            }
        ],
        series : [
            {
                name:'正面',
                type:'line',
                smooth:true,
                showAllSymbol: true,
                symbolSize: 0,
                itemStyle:{
                    normal:{
                        color:'#34C640',
                    }
                },
                data:zheng[1],
            },
            {
                name:'负面',
                type:'line',
                smooth:true,   //平滑曲线
                showAllSymbol: true,
                symbolSize:0,
                itemStyle:{
                    normal:{
                        color:'#FDA125',
                    }
                },
                data:fu[1]
            },
            {
                name:'中性',
                type:'line',
                smooth:true,   //平滑曲线
                showAllSymbol: true,
                symbolSize:0,
                itemStyle:{
                    normal:{
                        color:'#1e66d0',
                    }
                },
                data:zhong[1]
            }
        ]
    };
    // 为echarts对象加载数据
    myChart.setOption(option);
    myChart.on(echarts.config.EVENT.CLICK,function(e){

        var width=$("#main5").width();
        if(_.isObject(e.data)){
            var html="" ;
            var x= e.event.offsetX+50;
            if(x>width/2){
                x= e.event.offsetX-250;
            }
            var y= e.event.offsetY;
            if(_.isArray(e.data.data)){

                _.each(e.data.data,function(list){
                    html+='  <li class="list-group-item">'+list+'</li>'
                });

                $(".compareLabelTips ul").html(html);
                $(".compareLabelTips").animate({
                    left:x,
                    top:y
                });
                $(".compareLabelTips").show();
            }

        }
    });
};
$(".closeCompareTips").click(function(){
    $(".compareLabelTips").hide();
});

//添加舆情对比图关键转折点
$("#keyCompareSubmit").click(function(){
    var time= $("input[name=compareTime]").val();
    var content=  $("input[name=compareContent]").val();
    var nature= $("input[name=nature]:checked").val();

    if(!content || !time){
        return;
    }

    var data={
        eventId:id,
        type:nature,
        time:time,
        content:content
    };

    addNaturePoint(data);

});



//添加同类事件对比
var addNaturePoint=function(data){
    $.ajax({
        url:"voice/addNaturePoint.do",
        type:"POST",
        data:data,
        //data: JSON.stringify(data),
        dataType: "json",
        //contentType: "application/json;charset=utf-8",
        success:function(data){

            if(data.state){
                loadCompare({
                    eventId:id
                });
            }else{
                alert("关键转折点添加失败");
            }
        }
    })
};

//显示舆情对比图添加的关键转折点
var showCompareKeyInfo=function(p){
    console.log(p);
    var fuLists=[];
    var zhongLists=[];
    var zhengLists=[];
    if(p){
        var fu= p.FUtype || [];
        var zheng= p.ZHENGtype || [];
        var zhong= p.ZHONGtype || [];
        _.each(fu.time_content,function(e,k){
            if(k!="type"){
                _.each(e,function(val,key){
                    fuLists.push({
                        type:2,
                        content:val,
                        time:k
                    })
                })
            }
        });

        _.each(zheng.time_content,function(e,k){
            if(k!="type"){
                _.each(e,function(val,key){
                    zhengLists.push({
                        type:1,
                        content:val,
                        time:k
                    })
                })
            }
        });

        _.each(zhong.time_content,function(e,k){
            if(k!="type"){
                _.each(e,function(val,key){
                    zhongLists.push({
                        type:3,
                        content:val,
                        time:k
                    })
                })
            }
        });

        var lists=zhengLists.concat(fuLists,zhongLists);

        var html="";
        var types=[{"class":"key_green","text":"正面倾向"},{"class":"key_orange","text":"负面倾向"},{"class":"key_blue","text":"中性倾向"}];
        _.each(lists,function(e,k){
            console.log(types[e.type-1])
            html+='<li>\
                    <div class="row">\
                            <div class="col-md-6"><span>'+(k+1)+'.&nbsp</span><span>'+ e.time+'</span></div>\
                        <div class="col-md-4"><span class="'+types[e.type-1].class+'">'+types[e.type-1].text+'</span></div>\
                        <div class="col-md-2"><button class="del btn btn-default" data-type="'+e.type+'" data-time="'+ e.time+'"><span class="glyphicon glyphicon-minus-sign"></span></button></div>\
                            </div>\
                            <div class="row">\
                            <div class="col-md-12">\
                            <p class="infoContent">'+ e.content+'</p>\
                        </div>\
                        </div>\
                        </li>';
        });


        $("#keyNicescroll .new").html(html);
    }
};


//删除舆情对比关键转折点act
$("#contrastModal").delegate(".del","click",function(){
    var time=$(this).attr("data-time");
    var type=$(this).attr("data-type");
    var content=$(this).parents("li").find(".infoContent").text();
    $(this).parents("li").remove();
    var data={
        time:time,
        content:content,
        eventId:id,
        type:type
    };
    delKeyCompare(data);
});
var delKeyCompare=function(data){
    $.ajax({
        url:"voice/deleteNaturePoint.do",
        type:"POST",
        data:data,
        dataType:"json",
        success:function(data){
            if(data.state){
                //$("#redactModal").modal("hide");
                loadCompare({
                    eventId:id
                });
            }else{
                alert("舆情对比图关键转折点删除失败！");
            }
        }
    });
};


//加载舆情性质对比图===========================================================================================================================









var init=function(id){
    $.ajax({
        url:"event/show.do",
        type:"GET",
        dataType:"json",
        data:{id:id},
        success:function(e){

            $(".title").text(e.name);
            $(".eventDesc").text(e.content);


            if(e.correlation){
                loadCompare({
                    eventId:id
                });
            }else{
               $(".compareChart").hide();
            }

        },error:function(e){
            console.log(e)
        }
    })

};

init(id);



//事件简介浮窗

$("#event_detail").mouseover(function(e){
    $("div.popup").animate({
        left: e.clientX
    },200).show();
}).mouseout(function(e){
    $("div.popup").hide(300);
});



//平台选择
$("#platforms li").click(function(){
    $(this).addClass("pt_tab_selected")
        .siblings().removeClass("pt_tab_selected");
    var pt=$(this).attr("data-pt");
        loadSpread(id,pt);
        if(pt=="all"){
            $("#ptLists a").addClass("showpt");
        }else{

            $("#ptLists a").removeClass("showpt");
            $("#ptLists .nav"+pt).addClass("showpt");
            $(".speardDitchScroll").removeClass("active");
            $("#side-"+pt).addClass("active");
        }
});




//日期选择点击事件绑定
$("#selectTime").delegate(".timeTab","click",function(){
    $("#selectTime .timeTab").removeClass("pt_tab_selected");
    $(this).addClass("pt_tab_selected");

    if($(this).parents(".dropdown").length){
        $("#selectTime .dropdown").addClass("pt_tab_selected");
    }else{
        $("#selectTime .dropdown").removeClass("pt_tab_selected");
    }


    var time=$(this).attr("data-time");
    var data={};
    if(time=="all"){
        data=allTimeGroup;
    }else{
        _.each(allTimeGroup,function(e,k,i){
            if(k.indexOf(time)>-1){
                data[k]=e;
            }
        });
    }



    spreadPeak(data);

});
//信息平台选择
$("#ptLists  a").click(function(){
    $(this).addClass("active").siblings().removeClass("active");

    var type=$(this).attr("data-pt");
    $(".speardDitchScroll").removeClass("active");
    $("#side-"+type).addClass("active");

});
//时间选择器
$("#keyTrendTime").datetimepicker({
    format: 'yyyy-mm-dd hh',
    autoclose: true,
    minView:1
});
$("#compareTime").datetimepicker({
    format: 'yyyy-mm-dd hh',
    autoclose: true,
    minView:1
});




//选择时期显示
var showTimeSelect=function(e){
    timeGroup= _.groupBy(e,function(v,k){
        return k.substr(0,10);
    });
    var html="";
        var dates=[];
            dates.push('<li class="pt_tab_selected timeTab" data-time="all" >全部</li>');
        _.each(timeGroup,function(v,k){
            dates.push('<li class="timeTab" data-time="'+k+'">'+k+'</li>');
        });
        if(dates.length<3){
            html+=dates.join("");
        }else{
            var showDate=dates.splice(0,3);
            html+=showDate.join("");
            html+='<li class="dropdown">\
                        <span class="more" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">更多<span class="caret"></span></span>\
                        <ul class="dropdown-menu" aria-labelledby="dLabel1">';
            html+=dates.join("");
            html+='</ul></li>';
        }
    $("#selectTime").html(html);
};



//加载事件传播峰值图
var loadSpread=function(id,pt){
    var data={
        eventId:id,
        type:pt
    };
    $(".spreadLabelTips").hide();
    $.get("voice/showEvent.do",data,function(data){

        var d=data.Trend;
        if(d){
            points=data.Point;

            if(_.isObject(points)){
                spreadPeak(d.time_num,points.time_content);
                showTrendKeyPoint(points.time_content);
            }else{
                spreadPeak(d.time_num,{});
                showTrendKeyPoint({});
            }
            allTimeGroup= d.time_num;
            //日期选择限制
            if(pt=="all"){
                var startTime= _.first(_.keys(allTimeGroup));
                var endTime= _.last(_.keys(allTimeGroup));
                $('#keyTrendTime').datetimepicker('setStartDate', startTime);
                $('#keyTrendTime').datetimepicker('setEndDate', endTime);
            }
            showTimeSelect(d.time_num);
        }
    },"json");
};



loadSpread(id,"all");
//事件关键转折点提交
$("#keyTrendSubmit").click(function(){
    var time=$("input[name=keyTrendTime]").val();
    var content=$("input[name=keyTrendContent]").val();
    if(!time || !content){
        return;
    }
    var data={
        eventId:id,
        type:"all",
        time: time,
        content:content
    };
    addKeyPoint(data);
});




//添加事件转折点
var addKeyPoint=function(data){
    $.ajax({
        url:"voice/addEventPoint.do",
        type:"POST",
        //data: JSON.stringify(data),
        data: data,
        dataType: "json",
        //contentType: "application/json;charset=utf-8",
        success:function(data){
            if(data.state){
                //$("#redactModal").modal("hide");
                loadSpread(id,"all");
            }else{
                alert("事件关键转折点添加失败！");
            }

        }
    })
};



//传播图弹窗里的滑动条
$(".speardDitchScroll").niceScroll({
    cursorcolor:"#1E66D0",
    cursoropacitymax:1,
    touchbehavior:false,
    cursorwidth:"5px",
    cursorborder:"0",
    cursorborderradius:"5px"
});

//删除事件关键转折点
$("#spreadNicescroll").delegate(".del","click",function(e){

    var time=$(this).attr("data-id");
    var content=$(this).parents("li").find("p").text();
        $(this).parents("li").remove();
        var data={
            eventId:id,
            type:"all",
            time:time,
            content:content
        };
    delKeyTrend(data);
});
//删除事件关键转折点act
var delKeyTrend=function(data){
    $.ajax({
        url:"voice/deleteEventPoint.do",
        type:"POST",
        data:data,
        dataType:"json",
        success:function(data){
                if(data.state){
                    //$("#redactModal").modal("hide");
                    loadSpread(id,"all");
                }else{
                    alert("事件关键转折点删除失败！");
                }
        }
    });
};


//显示添加的事件转折点
var showTrendKeyPoint=function(data){
    var html="";
    var i=1;
    _.each(data,function(v,k){
        _.each(v,function(val,key){

            html+='<li class="clearfix">'+
                ' <div class="col-md-4"><span>'+(i++)+'.&nbsp</span><span>'+ k+'</span></div>'+
                ' <div class="col-md-6"><p>'+val+'</p></div><div class="col-md-2"><button class="del btn btn-default" data-id="'+k+'"><span class="glyphicon glyphicon-minus-sign"></span></button></div></li>';
        });

    });
    $("#spreadNicescroll .new").html(html);
};

//传播峰值图数据构造
var spreadPeak=function(e,p){
    var data=[];
    var times=[];
    var values=[];
    var labelNum=0;
    var labelName=["A","B","C","D","E","F","G","H","I","K","I","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"];

    if(_.isObject(p)){
        _.each(e,function(val,key){
            if(p[key]){
                values.push({
                    data:p[key],
                    value:val.length,
                    symbol: 'emptypin',  // 数据级个性化拐点图形
                    symbolSize : 10,
                    itemStyle: {        // 数据级个性化折线样式
                        normal: {
                            color: 'yellowgreen',
                            label : {
                                show: true,
                                formatter:function(e){
                                    return labelName[labelNum++];
                                },
                                position: 'inside',
                                textStyle : {
                                    fontSize : '14'
                                }
                            }
                        }
                    }
                });
            }else{
                values.push(val.length);
            }
            times.push(key);
        });
    }else{
        _.each(e,function(val,key){
            values.push(val.length);
            times.push(key);
        });
    }

    chartTrend(times,values);

};

//传播峰值图显示

var getWeek=function(t){

    var t= t.replace(" ","-");
        t= t.split("-");
        t= _.map(t,function(e){
            return parseInt(e);
        });
        var time=new Date(t[0],t[1]-1,t[2],t[3],0,0);
        var week=time.getDay();
        var weeks=["星期日","星期一","星期二" ,"星期三","星期四","星期五","星期六"];
        return weeks[week];
};

var chartTrend=function(times,values){
    // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main4'));
    var option = {
        tooltip : {
            trigger: 'axis',
            axisPointer:{
                type: 'line',
                lineStyle: {
                    color:"#000"
                }
            },
            formatter : function (params) {
                var res="";
                    res+=params[0].name+"  "+getWeek(params[0].name);
                    res+="<br/>"+ "事件传播量："+params[0].value;
                return res;
            }
        },
        toolbox: {
            show : true,
            feature : {
                saveAsImage : {show: true}
            }
        },
        dataZoom: {
            show: true,
            height:30,
            //start:20,
            //end:50,
            backgroundColor:'#DFDFDF',
            handleColor:'#1E66D0',
            fillerColor:'rgba(144,197,237,0.2)'
        },
        grid:{
            borderWidth:0
        },
        xAxis : [
            {
                //type : 'time',
                type : 'category',
                splitNumber:10,
                data : times,
                axisTick:{
                    show:true,
                    inside:true,
                    lineStyle:{
                        color:'#b6b6b6'
                    }
                },
                splitLine:{
                    show:false
                },
                splitArea:{
                    show:false
                },
                axisLine:{
                    show:true,
                    lineStyle:{
                        color:'#b6b6b6',
                        width:1
                    }
                }
            }
        ],
        yAxis : [
            {
                type : 'value',
                splitLine:{
                    show:false
                },
                splitArea:{
                    show:false
                },
                axisLine:{
                    show:false
                }
            }
        ],
        series : [
            {
                name: 'total',
                type: 'line',
                smooth:true,   //平滑曲线
                showAllSymbol: true,
                symbolSize: function (value){
                    return 1;
                },
                itemStyle:{
                    normal:{
                        color:'#1e66d0',
                    },
                    emphasis:{
                       color:'#03A9F4',
                        borderWidth:5,
                        borderColor:'#03A9F4',
                    }
                },
                data: values
            }
        ]
    };
    // 为echarts对象加载数据
    myChart.setOption(option);
    myChart.on(echarts.config.EVENT.CLICK,function(e){
            console.log(e);
            var type=$("#platforms .pt_tab_selected").attr("data-pt");
            var data={
                time: e.name,
                eventId:id
            };
        loadSideInfoList(data,type);



        var width=$("#main4").width();
        if(_.isObject(e.data)){
           var html="" ;
            var x= e.event.offsetX+50;
            if(x>width/2){
                x= e.event.offsetX-250;
            }

           var y= e.event.offsetY;
            if(_.isArray(e.data.data)){

                _.each(e.data.data,function(list){
                    html+='  <li class="list-group-item">'+list+'</li>'
                });

                $(".spreadLabelTips ul").html(html);
                $(".spreadLabelTips").animate({
                    left:x,
                    top:y
                });
                $(".spreadLabelTips").show();
            }

        }else{
            $(".buttonTab").addClass("active");
            $(".spread_content").animate({
                right:0
            });
        }
    });
};

$(".spreadLabelTips .closeSpreadTips").click(function(e){
    $(".spreadLabelTips").hide();
});


//加载侧边栏信息
var loadSideInfoList=function(data,type){
    $(".speardDitchScroll").html("");
    showLoading(".speardDitchScroll");
    $.ajax({
        url:"voice/showImpactTop10.do",
        type:"GET",
        data: data,
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        success:function(data){
            var media=data.media,
                weixin=data.weixin,
                weibo=data.weibo;

            hideLoading(".speardDitchScroll");

            switch (type){
                case "all":
                    sideInfo(media,"media");
                    sideInfo(weixin,"weixin");
                    sideInfo(weibo,"weibo");
                break;
                case "media":
                    sideInfo(media,"media");
                break;
                case "weixin":
                    sideInfo(weixin,"weixin");
                break;
                case "weibo":
                    sideInfo(weibo,"weibo");
                break;
            }







        }
    })
};
var sideInfo=function(data,type){

var html="";

    if(data.length){


        if(type=="media"){
            _.each(data,function(e,k){
                html+= ' <li class="new_detail">'+
                    ' <h5><a href="'+ e.url+'" target="_blank">'+ e.title+'</a></h5>'+
                    ' <div class="clearfix">'+
                    ' <span>'+ e.source+'</span>'+
                    '  <span>'+ e.time+'</span>'+
                    '</div><div class="clearfix">'+
                    '<span>渠道影响力:<span class="top_blue">'+ parseInt(e.impact)+'</span></span>'+
                    '<span>相似传播:<span class="top_blue">'+ (e.similarity?e.similarity:0)+'</span></span> </div></li> ';

            });
        }else if(type=="weixin"){
            _.each(data,function(e,k){
                html+= ' <li class="new_detail">'+
                    ' <h5><a href="'+e.url+'" target="_blank">'+ e.title+'</a></h5>'+
                    ' <div class="clearfix">'+
                    '  <span>'+ e.Username+'</span>'+
                    '  <span>'+ e.time+'</span>'+
                    '</div><div class="clearfix">'+
                    '<span>渠道影响力:<span class="top_blue">'+ parseInt(e.impact)+'</span></span>'+
                    '<span>相似传播:<span class="top_blue">'+ (e.similarity?e.similarity:0)+'</span></span> </div></li> ';
            });
        }else{
            _.each(data,function(e,k){
                html+= ' <li class="new_detail">'+
                    ' <h5><a href="'+e.url+'" target="_blank">'+ e.title+'</a></h5>'+
                    ' <div class="clearfix">'+
                    '  <span>'+ e.username +'</span>'+
                    '  <span>'+ e.time+'</span>'+
                    '</div><div class="clearfix">'+
                    '<span>渠道影响力:<span class="top_blue">'+ parseInt(e.impact)+'</span></span>'+
                    '</div></li> ';
            });
        }

    }else{
        html='<li style="padding: 5px;text-align: center"> 此时间段'+type+'平台上暂无相关数据</li>';
    }

 $("#side-"+type).html(html);


};






//隐藏显示侧边栏

$(".spread_content .buttonTab").click(function(){

    if($(this).hasClass("active")){
        $(this).removeClass("active");
        $(".spread_content").animate({
            right:-300
        });
    }else{
        $(this).addClass("active");
        $(".spread_content").animate({
            right:0
        });
    }

});


