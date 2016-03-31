



$(function(){





    var init=function(id){
        $.ajax({
            url:"event/show.do",
            type:"GET",
            dataType:"json",
            data:{id:id},
            success:function(e){
                showEventDesc(e.name, e.content);
                showImpact(e.impact,[e.correlationNum, e.channelNum, e.spread]);
                crisisRate(e.eventLevel);
                hotWord(e.topWord);
                showCompare({
                    eventType: e.eventType
                });

                getEventType(e.eventType);


            },error:function(e){
                console.log(e)
            }
        })

    };

    init(id);
//事件简介
    var showEventDesc=function(title,content){
        $(".eventDesc .title").text(title);
        $(".eventDesc .title").attr("title",title);
        $(".eventDesc .eventContent").append(content);
    };
//事件影响力指数
    var showImpact=function(index,data){

        $(".eventIndex .index").text(parseInt(index));
        influenceIndex(data);
    };
    var influenceIndex=function(data){
        //事件相关性/媒介渠道/传播数量
        var myChart = echarts.init(document.getElementById('main1'));
        var  width=$("#main1").width();
        var radius=100;
            if(width<300){
                radius=80;
            }
        var option = {
            tooltip : {
                formatter:function(e){
                    console.log(e);
                    var res="";
                    res+="事件相关性: "+ parseInt(e.value[0]);
                    res+="<br/>媒介渠道: "+parseInt( e.value[1]);
                    res+="<br/>传播数量: "+parseInt(e.value[2]);
                    return res;
                }
            },
            color:['#1E66D0'],
            polar : [
                {
                    indicator : [
                        { text: '事件相关性',
                            axisLabel:{
                                show:true,
                                textStyle:{
                                    align:'center'
                                }
                            },
                            max: 100},
                        { text: '媒介渠道',max: 100},
                        { text: '传播数量',max: 100}

                    ],
                    center : ['50%', "60%"],
                    radius :radius,
                    name : {                  //这里设置了标签字体
                        formatter:'{value}',
                        textStyle: {
                            fontSize:16,
                            align:'center',
                            fontFamily:'Microsoft Yahei',
                            color:'#666'
                        }
                    },
                    splitArea:{show:false},
                    splitLine:{
                        lineStyle:{
                            color:'#666'
                        }},
                    axisLine:{show:false}

                }
            ],
            series : [
                {
                    name: '事件影响力指数',
                    type: 'radar',
                    //symbolSize:[0.5,0.5],   //数据标志大小:正常-悬浮
                    itemStyle:{
                        normal:{
                            lineStyle:{
                                width:2
                            }
                        }
                    },
                    data : [
                        {
                            value : data,
                            name : '事件影响力指数'
                        }
                    ]
                }
            ]
        };
        myChart.setOption(option);
    }
//危机评级
    var crisisRate=function(level){

        var color=[];
        if(level=='Ⅰ级'){
            color=["#ff0000","#ffffff","#ffffff"];
        }else if(level=='ⅠⅠ级'){
            color=["#ffffff","#ff0000","#ffffff"];
        }else{
            color=["#ffffff","#ffffff","#ff0000"];
        }



        var myChart = echarts.init(document.getElementById('main2'));
        var option = {
            color:color,
            //弹窗提示：当前事件危机等级
            tooltip : {
                trigger: 'item',
                formatter:function(e){
                    console.log(e);
                    return "当前事件危机等级为"+level;
                }
            },
            series : [
                {
                    name:'漏斗图',
                    type:'funnel',
                    x: 0,
                    y: 40,
                    width: '100%',
                    minSize: '0%',
                    maxSize: '100%',
                    sort : 'descending', // 'ascending', 'descending'
                    itemStyle: {
                        normal: {
                            borderColor: '#1E66D0',
                            borderWidth: 1,
                            label: {
                                show: true,
                                position: 'inside',
                                textStyle: {
                                    color:['#000'],
                                    fontFamily:'Microsoft Yahei',
                                    fontSize:14
                                }
                                // 默认使用全局文本样式，详见TEXTSTYLE
                            }
                        }
                    },
                    data:[
                        {value:60, name:'Ⅰ级'},
                        {value:40, name:'Ⅱ级'},
                        {value:20, name:'Ⅲ级'}
                    ]
                }
            ]
        };
        // 为echarts对象加载数据
        myChart.setOption(option);
    };
//全局热议词

    var hotWord=function(word){

        var words=[];

        _.each(word,function(v,k){
            words.push({
                text:k,
                size:v
            });
        });

    
      






        var max=d3.max(words.map(function(e){

            return e.size;
        }));
        var min=d3.min(words.map(function(e){
            return e.size;
        }));

        	words=_.sortBy(words,"size");
        	words=words.reverse();


        var scale=d3.scale.linear()
            .domain([min,max])
            .range([18,80]);

        var width=$("#main3").width();
        var height=$("#main3").height();

        var fill = d3.scale.category20();
        console.log(~~(Math.random() * 2) * 180)
        var layout = cloud()
            .size([width, height])
            .words(words.slice(0,50))
            .padding(1)
            .rotate(function() { return ~~(Math.random() * 2) * 45; })
            .font("Microsoft Yahei")
            .fontSize(function(d) { return scale(d.size); })
            .on("end", draw);

        layout.start();
        function draw(words) {
            d3.select("#main3").append("svg")
                .attr("width", layout.size()[0])
                .attr("height", layout.size()[1])
                .append("g")
                .attr("transform", "translate(" + layout.size()[0] / 2 + "," + layout.size()[1] / 2 + ")")
                .selectAll("text")
                .data(words)
                .enter().append("text").classed("textcloud",true)
                .style("font-size", function(d) { return d.size + "px"; })
                .style("font-family", "Microsoft Yahei")
                .style("fill", function(d, i) { return fill(i); })
                .attr("text-anchor", "middle")

                .attr("transform", function(d) {

                    return "translate(" + [d.x, d.y] + ")rotate(" + d.rotate + ")";
                })
                .text(function(d) { return d.text; })
                .on("click",function(e){
                    console.log(e);
                    findHotWord(e.text);



                })
                .on("mouseover",function(e){

                    d3.select(this).attr("stroke","#000000")
                                   .attr("stroke-width",1)
                        .style({
                            "font-weight":"bold"
                        })
                })
                .on("mouseout",function(e){

                    d3.select(this).attr("stroke","#000000")
                        .attr("stroke-width",0)
                        .style({
                            "font-weight":"normal"
                        })

                });
        }
    }
    

//热词查询

var findHotWord=function(word){
    var data={
        eventId:id,
        word:word
    };
    $(".hotDitchScroll").html("");
    showLoading(".hotDitchScroll");
    $.get("event/search.do",data,function(data){
        console.log(data);
        hideLoading(".hotDitchScroll");
        showTypeInfo(data.weixin,"weixin");
        showTypeInfo(data.weibo,"weibo");
        showTypeInfo(data.media,"media");

    },"json");

};


var showTypeInfo=function(data,type){


    var html="";

    if(data.length){

        if(type=="media"){
            _.each(data,function(e,k){
                html+= ' <li class="new_detail">'+
                    ' <h5><a href="'+ e.info.url+'" target="_blank">'+ e.info.title+'</a></h5>'+
                    ' <div class="clearfix">'+
                    ' <span>'+ e.info.source+'</span>'+
                    '  <span>'+ e.info.time+'</span>'+
                    '</div><div class="clearfix">'+
                    '<span>渠道影响力:<span class="top_blue">'+ parseInt(e.h)+'</span></span>'+
                    '</div></li> ';

            });
        }else if(type=="weixin"){
            _.each(data,function(e,k){
                html+= ' <li class="new_detail">'+
                    ' <h5><a href="'+e.info.url+'" target="_blank">'+ e.info.title+'</a></h5>'+
                    ' <div class="clearfix">'+
                    '  <span>'+ e.info.time+'</span>'+
                    '</div><div class="clearfix">'+
                    '<span>渠道影响力:<span class="top_blue">'+ parseInt(e.h)+'</span></span>'+
                    '</div></li> ';
            });
        }else{
            _.each(data,function(e,k){
                html+= ' <li class="new_detail">'+
                    ' <h5><a href="'+ e.info.url+'" target="_blank">'+ e.info.text+'</a></h5>'+
                    ' <div class="clearfix">'+
                    '  <span>'+ e.info.username +'</span>'+
                    '  <span>'+ e.info.time+'</span>'+
                    '</div><div class="clearfix">'+
                    '<span>渠道影响力:<span class="top_blue">'+ parseInt(e.h)+'</span></span>'+
                    '</div></li> ';
            });
        }

    }else{
        html='<li style="padding: 5px;text-align: center"> 此时间段'+type+'平台上暂无相关数据</li>';
    }

    $("#side-"+type).html(html);


};




//全局热议词信息平台选择
$("#ptLists  a").click(function(){
    $(this).addClass("active").siblings().removeClass("active");

    var type=$(this).attr("data-pt");
        $(".hotDitchScroll").hide();
        $("#side-"+type).show();


});

//全局热议词信息平台里的滑动条
$(".hotDitchScroll").niceScroll({
    cursorcolor:"#1E66D0",
    cursoropacitymax:1,
    touchbehavior:false,
    cursorwidth:"5px",
    cursorborder:"0",
    cursorborderradius:"5px"
});


//同类事件对比

    $("input[name=eventTypeCheck]").on("change",function(){

        if($(this).is(":checked")){
            $("select[name=eventType]").removeAttr("disabled");
        }else{
            $("select[name=eventType]").attr("disabled","disabled");
            $("select[name=eventType]").val("");
            $("select[name=eventType] option:first").attr("selected","selected");

        }
    });


    $("input[name=eventLevelCheck]").on("change",function(){
        if($(this).is(":checked")){
            $("select[name=eventLevel]").removeAttr("disabled");
        }else{
            $("select[name=eventLevel]").attr("disabled","disabled");
            $("select[name=eventLevel]").val("");
            $("select[name=eventLevel] option:first").attr("selected","selected");


        }
    });


    $("#apply").click(function(){

        var eventType=$("select[name=eventType]").val();
        var eventLevel=$("select[name=eventLevel]").val();

        var data={
            eventType:eventType,
            eventLevel:eventLevel
        };
        showCompare(data);

    });


//事件分类
    var getEventType=function(type){
        $.ajax({
            type:"GET",
            dataType:"json",
            url:"manager/showType.do",
            success:function(data){
                var html="";
                _.each(data,function(e){
                    if(type==e.id){
                        html+='<option value="'+ e.id+'" selected>'+ e.eventType+'</option>' ;
                     
                    }else{
                        html+='<option value="'+ e.id+'">'+ e.eventType+'</option>' ;
                    }
                // console.log("e:"+e);
                // console.info("-------------------");
                // console.log("type:"+type);
                });

                $("#eventType option:gt(1)").remove();
                $("#eventType").append(html);
            },
            error:function(e){
                console.log(e);
            }

        });
    };

//事件等级
    var getEventGrade=function(){
        $.ajax({
            type:"GET",
            dataType:"json",
            url:"manager/showLevel.do",
            success:function(data){
                var html="";
                _.each(data,function(e){
                    html+='<option value="'+ e.id+'">'+ e.eventLevel+'</option>' ;
                });
                $("#eventLevel option:gt(1)").remove();
                $("#eventLevel").append(html);
            },
            error:function(e){
                console.log(e);
            }
        });
    }();


    var showCompare=function(data){
        var html="";
        $.ajax({
            url:"event/than.do",
            type:"GET",
            dataType:"json",
            data:data,
            success:function(data){
                if(data.length){
                    _.each(data,function(e){
                        html+='<div>'+
                            ' <h5>'+ e.name+'</h5>'+
                            ' <div class="valuewrap">'+
                            ' <div class="progress-bar value " role="progressbar" aria-valuenow="'+ parseInt(e.impact)+'" aria-valuemin="0" aria-valuemax="100" style="width:'+ parseInt(e.impact)+'%;">'+
                            '      <span class="font_liquid">'+ parseInt(e.impact)+'</span>'+
                            '</div></div></div>';
                    });
                }else{
                    html='<p class="none">无该事件分类或危机等级下的对比事件</p>';
                }
                $("#compareList").html(html);
            },
            error:function(){

            }
        })
    };

//同类事件对比







});



