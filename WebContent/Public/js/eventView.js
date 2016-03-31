/**
 * Created by zhichaoshen on 2016/3/14.
 */

var mediaView={},netizenView={},spreadSource={},eventName;
var init=function(id){
    $.ajax({
        url:"event/show.do",
        type:"GET",
        dataType:"json",
        data:{id:id},
        success:function(e){
            $(".title").text(e.name);
            $(".eventDesc").text(e.content);
            eventName= e.name;
/*===============================================================================*/


//事件简介浮窗

            // $("#event_detail").mouseover(function(e){
            //     $("div.popup").animate({
            //         left: e.clientX
            //     },200).show();
            // }).mouseout(function(e){
            //     $("div.popup").hide(300);
            // });




//事件简介展示
            $("#event_detail").hover(
                function(){
                    $("div.popup").show(200);
                },
                function(){
                    $("div.popup").hide(300);
                }
            );




//媒体观点弹出编辑窗


            var inputGroup=' <li class="clearfix">\
                    <div class="form-group">\
                    <label>内容</label>\
                    <input type="text" class="form-control" name="title">\
                    </div>\
                    <div class="form-group">\
                    <label>占比：</label>\
                        <input type="number" min="0" max="1" step="0.01" class="form-control"  placeholder="0-1" name="rate">\
                    </div>\
                    <div class="form-group">\
                    <a  class="plus"><span class="glyphicon glyphicon-plus-sign"></span></a>\
                    <a  class="reduce"><span class="glyphicon glyphicon-minus-sign"></span></a>\
                    </div>\
                    </li>';

            $(".editForm").delegate(".plus","click",function(){
                $(this).parents(".new").append(inputGroup);
            });


            $(".editForm").delegate(".reduce","click",function(){
                $(this).parents("li").remove();

            });

//添加意见领袖
            $("#mediaModal button.sure").click(function(e){
                e.preventDefault();
                e.stopPropagation();
                var type="意见领袖";
                var title=[],rate=[];
                var  titles=$("#mediaModal input[name=title]");
                var  rates=$("#mediaModal input[name=rate]");
                var needValue=[];
                _.each(titles,function(e,i){
                    title.push($(e).val());
                    rate.push(parseFloat($(rates[i]).val()));
                });

                var va=0;
                _.each(rate,function(e){
                    va+= parseInt(e*100);
                });


                if((va/100)!=1){
                    alert("占比不为100%");
                    return ;
                }

                mediaView= _.object(title,rate);
                addData(mediaView,type,function(data){
                    $("#mediaModal").modal('hide');
                    loadData(id);
                    //.modal('hide')
                });



            });

//添加网民观点
            $("#netizenModal button.sure").click(function(e){

                e.preventDefault();
                e.stopPropagation();
                var type="网民";
                netizenView={};
                var title=[],rate=[];
                var  titles=$("#netizenModal input[name=title]");
                var  rates=$("#netizenModal input[name=rate]");
                _.each(titles,function(e,i){
                    title.push($(e).val());
                    rate.push(parseFloat($(rates[i]).val()));
                });

                var va=0;
                _.each(rate,function(e){
                    va+=e;
                });


                if(va!=1){
                    alert("占比不为100%");
                    return ;
                }



                netizenView= _.object(title,rate);
                addData(netizenView,type,function(data){
                    $("#netizenModal").modal('hide');
                    loadData(id);
                });

            });



            var addData=function(data,type,success){
                var post={
                    eventId:id,
                    type:type,
                    pointMap:data
                };

                $.ajax({
                    url:"point/updataPoint.do",
                    type:"POST",
                    data: JSON.stringify(post),
                    dataType: "json",
                    contentType: "application/json;charset=utf-8",
                    success:success
                })
            };







// 关键传播滚动条

            $("#keyContnetScroll").niceScroll({
                cursorcolor:"#1E66D0",
                cursoropacitymax:1,
                touchbehavior:true,
                cursorwidth:"5px",
                cursorborder:"0",
                cursorborderradius:"5px"
            });






//注解浮窗显示
            $("#media_popup").hover(
                function(){
                    $(".noteone").show(200);
                },
                function(){
                    $(".noteone").hide(300);
                }
            );

            $("#weixin_popup").hover(
                function(){
                    $(".notetwo").show(200);
                },
                function(){
                    $(".notetwo").hide(300);
                }
            );

            $("#weibo_popup").hover(
                function(){
                    $(".notethree").show(200);
                },
                function(){
                    $(".notethree").hide(300);
                }
            );


            var showHasMediaView=function(data){

                var html="";
                _.each(data,function(e,k){
                    html+=  '<li class="clearfix">\
                <div class="form-group">\
                <label>内容</label>';
                    html+='<input type="text" class="form-control" name="title" value="'+k+'">';
                    html+='</div>\
                <div class="form-group">\
                <label>占比：</label>';
                    html+='<input type="number" min="0" max="1" step="0.01" class="form-control"  placeholder="0-1" name="rate" value="'+parseFloat(e)+'">';
                    html+='</div>\
                <div class="form-group">\
                <a class="plus"><span class="glyphicon glyphicon-plus-sign"></span></a>\
                <a class="reduce"><span class="glyphicon glyphicon-minus-sign"></span></a>\
                </div>\
                </li>';
                });
                $("#viewMedia .new").html(html);

            };


            var showHasNetizenView=function(data){

                var html="";
                _.each(data,function(e,k){
                    html+=  '<li class="clearfix">\
                <div class="form-group">\
                <label>内容</label>';
                    html+='<input type="text" class="form-control" name="title" value="'+k+'">';
                    html+='</div>\
                <div class="form-group">\
                <label>占比：</label>';
                    html+='<input type="number" min="0" max="1" step="0.01" class="form-control"  placeholder="0-1" name="rate" value="'+e+'">';
                    html+='</div>\
                <div class="form-group">\
                <a  class="plus"><span class="glyphicon glyphicon-plus-sign"></span></a>\
                <a  class="reduce"><span class="glyphicon glyphicon-minus-sign"></span></a>\
                </div>\
                </li>';
                });
                $("#viewNetizen .new").html(html);
            };



            var loadData=function(id){
                var data={
                    eventId:id
                };

                $.get("point/show.do",data,function(data){
                    mediaView=data[0];
                    netizenView=data[1];
                    spreadSource=data[2];

                    if(netizenView){
                        netizenPoint(netizenView.pointMap);
                        showHasNetizenView(netizenView.pointMap);
                    }

                    if(mediaView){
                        mediaPoint(mediaView.pointMap);
                        showHasMediaView(mediaView.pointMap);
                    }

                    keySpread(spreadSource,'media');

                },"json");
            };


            loadData(id);



            $("#sourcePt li").click(function(){
                $(this).addClass("pt_tab_selected")
                    .siblings().removeClass("pt_tab_selected");
                var pt=$(this).attr("data-pt");
                keySpread(spreadSource,pt);
            });


//媒体观点
            var mediaPoint=function(data){
                if(data){
                    var needData=[];
                    var legends=[];
                    _.each(data,function(e,k){
                        needData.push({
                            "name":k,
                            "value":parseFloat(e)
                        }) ;
                        var names=[];
                        legends.push(k);
                    });


                    var myChart = echarts.init(document.getElementById('main6'));
                    var option = {
                        tooltip : {
                            show:true,
                            formatter:"{d}%",      //数值
                            trigger: 'item',
                            //backgroundColor:'rgba(255,255,255,0)' ,  //提示框无背景
                            textStyle:{
                                color:'#FFF',
                                fontSize:24,
                                fontWeight:700,
                                textAlign:'center'
                            },
                        },
                        toolbox:{
                            show:true,
                            feature : {      //启用功能
                                saveAsImage : {show: true}
                            }
                        },
                        legend: {
                            x:'right',
                            y:'center',
                            orient:'vertical',
                            show:true,
                            itemGap:25,
                            itemWidth:25,
                            itemHeight:15,
                            data:legends.splice(0,13,'"'),
                            textStyle:{
                                fontFamily:'Microsoft Yahei',
                                fontSize:14
                            },
                            formatter:function(e){

                                var names=[];
                                _.each(e,function(v,i){

                                    if(i%13==0  && (i!=0)){
                                        names.push(v+"\n");
                                    }else{
                                        names.push(v);
                                    }
                                })


                                return names.join("");
                            }
                        },
                        textStyle:{
                            fontFamily: "Microsoft Yahei"
                        },
                        color:[ '#1E66D0', '#FEBF29', '#94D057','#AC1DCE','#1956B3','#5E6C75'],
                        series : [
                            {
                                name:'观点',
                                type:'pie',
                                center:['30%','50%'],
                                radius : ['55%', '90%'],
                                width: '100%',
                                itemStyle : {
                                    normal : {
                                        label : {
                                            show : true,
                                            position:'inside',
                                            formatter:"{d} %"
                                        },
                                        labelLine : {
                                            show : false
                                        }
                                    },

                                },
                                data:needData
                            }
                        ]
                    };
                    myChart.setOption(option);
                }

            };
//网民观点
            var netizenPoint=function(data){
                if(data){
                    var needData=[];
                    var legends=[];
                    _.each(data,function(e,k){
                        needData.push({
                            "name":k,
                            "value":parseFloat(e)
                        }) ;
                        legends.push(k);
                    });

                    console.log(needData)
                    var myChart = echarts.init(document.getElementById('main7'));
                    var option = {
                        tooltip : {
                            show:true,
                            formatter:"{d}%",      //数值
                            trigger: 'item',
                            //backgroundColor:'rgba(255,255,255,0)' ,  //提示框无背景
                            textStyle:{
                                color:'#FFF',
                                fontSize:24,
                                fontWeight:700,
                                textAlign:'center'
                            },
                        },
                        toolbox:{
                            show:true,
                            feature : {      //启用功能
                                saveAsImage : {show: true}
                            }
                        },
                        legend: {
                            x:'right',
                            y:'center',
                            orient:'vertical',
                            show:true,
                            itemGap:12,
                            itemWidth:25,
                            itemHeight:15,
                            data:legends.splice(0,13,'"'),
                            textStyle:{
                                fontFamily:'Microsoft Yahei',
                                fontSize:14
                            }
                        },
                        color:[ '#1E66D0', '#FEBF29', '#94D057','#AC1DCE','#1956B3','#5E6C75'],
                        series : [
                            {
                                name:'观点',
                                type:'pie',
                                center:['30%','50%'],
                                radius : ['55%', '90%'],
                                width: '100%',
                                itemStyle : {
                                    normal : {
                                        label : {
                                            show : true,
                                            position:'inside',
                                            formatter:"{d} %"
                                        },
                                        labelLine : {
                                            show : false
                                        }
                                    },

                                },
                                data:needData
                            }
                        ]
                    };
                    myChart.setOption(option);
                }
            };

//关键传播源
            var keySpread=function(data,type){
                var nodes=[{
                    name:eventName,
                    value:1,
                    vH:1,
                    links:[]
                }];
                var edges=[];
                var values=[];
                if(data[type]){

                    if(!_.isEmpty(data[type])){
                        var i=0;
                        _.each(data[type],function(val,key){
                            console.log(val,key)
                            values.push(val.H);
                            nodes.push({
                                name:key,
                                value:val.list.length,
                                links:val.list,
                                vH:val.H
                            });
                            edges.push({
                                source:0,
                                target:++i
                            });

                        });
                    }
                }

               var nodeMax= _.max(nodes,function(e){
                   return e.vH;
               });

                console.log(values)






                var scaleR=d3.scale.linear()
                    .domain([1,d3.max(values)])
                    .range([20,50]);

                var scaleD=d3.scale.linear()
                    .domain([1,d3.max(values)])
                    .range([50,200]);

                var width = $("#main8").width();
                var height =$("#main8").height();

                $("#main8").html("");
                var svg = d3.select("#main8")
                    .append("svg")
                    .attr("width",width)
                    .attr("height",height);

                var force = d3.layout.force()
                    .nodes(nodes)       //指定节点数组
                    .links(edges)       //指定连线数组
                    .size([width,height])   //指定范围
                    .linkDistance(function(e){
                        return scaleD(e.target.vH)
                    })  //指定连线长度
                    .charge(-800);  //相互之间的作用力

                force.start();  //开始作用


                //添加连线
                var svg_edges = svg.selectAll("line")
                    .data(edges)
                    .enter()
                    .append("line")
                    .style("stroke","#ccc")
                    .style("stroke-width",1);

                //ae1cd1 紫色
                var color =['#1E66D0','#FEBF29','#94D057','#EB7B3A'];

                //添加节点

                var svg_nodes_g=svg.selectAll("g")
                    .data(nodes)
                    .enter()
                    .append("g")
                    .classed("cg",true)
                    .on("click",function(e){
                        var type=$("#sourcePt .pt_tab_selected").attr("data-pt");
                        console.log(e)
                        if(e.links){
                            showDetailsInfo(e.links,type, e.name);
                        }

                    })
                    .on("mouseover",function(e){
                        console.log(e);
                        var circle=d3.select(this).select("circle");
                        var color=circle.style("fill");
                        var text=d3.select(this).select("text");


                        circle.transition()
                            .attr({
                                "r":scaleR(e.vH)*1.5,
                                "stroke-width":5,
                                "stroke":d3.rgb(color).darker()
                            });
                        text.text(e.name);


                    })
                    .on("mouseout",function(e){
                        var circle=  d3.select(this).select("circle");
                        var color=circle.style("fill");
                        var text=d3.select(this).select("text");
                        console.log(color);
                        circle.transition()
                            .attr({
                                "r":scaleR(e.vH)*1,
                                "stroke-width":0,
                                "stroke":d3.rgb(color).brighter()
                            });
                        var name= e.name;
                        if(name.length>5){
                            name=name.substr(0,5)+"...";
                        }
                        if(e.index!=0){
                            name=name+" "+ e.value;
                        }
                        text.text(name);

                    });



                var svg_nodes = svg_nodes_g
                    .append("circle")
                    .classed({"circle":true})
                    .attr("r",function(e){
                        return scaleR(e.vH)

                    })
                    .style("fill",function(d,i){
                        if(i==0){
                            return "#ae1cd1";
                        }else{
                            return color[parseInt(4*Math.random())];
                        }

                    })
                    .call(force.drag);  //使得节点能够拖动

                //添加描述节点的文字
                var svg_texts = svg_nodes_g
                    .append("text")
                    .classed("text",true)
                    .style({
                        "fill":"black",
                        "text-anchor":"middle"
                    })
                    .attr("dy", 7)
                    .text(function(d,i){
                        var name= d.name;
                            if(name.length>5){
                                name=name.substr(0,5)+"...";
                            }
                            if(i==0){
                                return name;
                            }else{
                                return name+" "+ d.value;
                            }

                    });



                force.on("tick", function(){    //对于每一个时间间隔
                    //更新连线坐标
                    svg_edges.attr("x1",function(d){ return d.source.x; })
                        .attr("y1",function(d){ return d.source.y; })
                        .attr("x2",function(d){ return d.target.x; })
                        .attr("y2",function(d){ return d.target.y; });
                    //更新节点坐标
                    svg_nodes.attr("cx",function(d){ return d.x; })
                        .attr("cy",function(d){ return d.y; });
                    //更新文字坐标
                    svg_texts.attr("x", function(d){ return d.x; })
                        .attr("y", function(d){ return d.y; });
                });


                //点击圆更新事件信息


                var showDetailsInfo=function(urls,type,name){
                    //#今日头条#报道(按照时间降序)
                    $(".top_tab").text("#"+name+"#发布微博(按照时间升序)");
                    // $(".top_tab").html(
                    //         '<h5 class="white">#'+name+'#报道</h5>'+
                    //                  '<span>总的参与次数：8次</span>'+
                    //                  '<span>排序：'+
                    //                  '<a class="btn btn-default" href="#" role="button">时&nbsp&nbsp间</a>'+
                    //                  '<a class="btn btn-default" href="#" role="button">传播量</a>'+
                    //                  '<a href="#"></a>'+
                    //                  '</span>'
                    //     );
                    
                    var data={
                        "eventId":id,
                        "type":type,
                        "ids":urls
                    };
                    $("#keyContnetScroll").html("");
                    showLoading("#keyContnetScroll");



                    $.ajax({
                        url:"point/showInfo.do",
                        type:"POST",
                        data: JSON.stringify(data),
                        dataType: "json",
                        contentType: "application/json;charset=utf-8",
                        success:function(data){

                            if(!data.length){
                                $("#keyContnetScroll").html('<li class="new_detail">无数据</li>');
                            }else{

                                var html='';
                                if(type=="weibo"){
                                    _.each(data,function(e,k){
                                        html+=' <li class="new_detail">'+
                                            ' <h5><a href="'+ e.url+'" target="_blank" >'+(k+1)+"."+ e.title+'</a></h5>'+
                                            ' <div class="clearfix">'+
                                            '  <span><a href="'+ e.url+'">'+ e.time+'</a></span>'+
                                            '</div>'+
                                            '</li>';
                                    });
                                }
                                else if(type=="weixin"){
                                    _.each(data,function(e,k){
                                        html+=' <li class="new_detail">'+
                                            ' <h5><a href="'+ e.url+'" target="_blank" >'+(k+1)+"."+ e.title+'</a></h5>'+
                                            ' <div class="clearfix">'+
                                            '  <span><a href="'+ e.url+'">'+ e.time+'</a></span>'+
                                            '  <span>相似传播：'+ (e.num?e.num:0)+'</span>'+
                                            '</div>'+
                                            '</li>';
                                    });


                                    $(".top_tab").html(
                                            '<h5 class="white">#'+name+'#报道</h5>'+
                                                     '<span>总的参与次数：'+urls.length+'次</span>'+
                                                     '<span>排序：'+
                                                     '<a class="btn btn-default selected weixin-timebutton" role="button">时&nbsp&nbsp间</a>'+
                                                     '<a class="btn btn-default weixin-volumnbutton" role="button">传播量</a>'+
                                                     '<a id="mediacount_popup"></a>'+
                                                     '</span>'
                                        );



                                    $(".key_content .weixin-timebutton").click(function(){
                                        $(this).addClass("selected");
                                        $(".weixin-volumnbutton").removeClass("selected");
                                        $("#keyContnetScroll").html(html);
                                        $("#keyContnetScroll").scrollTop(0);
                                    });

                                    $(".key_content .weixin-volumnbutton").click(function(){

                                        $(this).addClass("selected");
                                        $(".weixin-timebutton").removeClass("selected");

                                        var dataa= _.sortBy(data,"num");
                                            dataa=dataa.reverse();
                                        var htmll="";
                                        _.each(dataa,function(e,k){
                                            htmll+=' <li class="new_detail">'+
                                                ' <h5><a href="'+ e.url+'" target="_blank" >'+(k+1)+"."+ e.title+'</a></h5>'+
                                                ' <div class="clearfix">'+
                                                '  <span><a href="'+ e.url+'">'+ e.time+'</a></span>'+
                                                '  <span>相似传播：'+ (e.num?e.num:0)+'</span>'+
                                                '</div>'+
                                                '</li>';
                                        });
                                        $("#keyContnetScroll").html(htmll);
                                        $("#keyContnetScroll").scrollTop(0);

                                    });



                                }
                                else{
                                    _.each(data,function(e,k){
                                        html+=' <li class="new_detail">'+
                                            ' <h5><a href="'+ e.url+'" target="_blank" >'+(k+1)+"."+ e.title+'</a></h5>'+
                                            ' <div class="clearfix">'+
                                            '  <span><a href="'+ e.url+'">'+ e.time+'</a></span>'+
                                            '  <span>相似传播：'+ (e.num?e.num:0)+'</span>'+
                                            '</div>'+
                                            '</li>';
                                    });


                                    $(".top_tab").html(
                                            '<h5 class="white">#'+name+'#报道</h5>'+
                                                     '<span>总的参与次数：'+urls.length+'次</span>'+
                                                     '<span>排序：'+
                                                     '<a class="btn btn-default media-timebutton selected" role="button">时&nbsp&nbsp间</a>'+
                                                     '<a class="btn btn-default media-volumnbutton" role="button">传播量</a>'+
                                                     '<a id="count_popup"></a>'+
                                                     '</span>'
                                        );


                                    $(".key_content .media-timebutton").click(function(){
                                        $(this).addClass("selected");
                                        $(".media-volumnbutton").removeClass("selected");
                                        $("#keyContnetScroll").html(html);
                                        $("#keyContnetScroll").scrollTop(0);
                                    });

                                    $(".key_content .media-volumnbutton").click(function(){

                                        $(this).addClass("selected");
                                        $(".media-timebutton").removeClass("selected");

                                        var dataa= _.sortBy(data,"num");
                                        dataa=dataa.reverse();
                                        var htmll="";
                                        _.each(dataa,function(e,k){
                                            htmll+=' <li class="new_detail">'+
                                                ' <h5><a href="'+ e.url+'" target="_blank" >'+(k+1)+"."+ e.title+'</a></h5>'+
                                                ' <div class="clearfix">'+
                                                '  <span><a href="'+ e.url+'">'+ e.time+'</a></span>'+
                                                '  <span>相似传播：'+ (e.num?e.num:0)+'</span>'+
                                                '</div>'+
                                                '</li>';
                                        });
                                        $("#keyContnetScroll").html(htmll);
                                        $("#keyContnetScroll").scrollTop(0);

                                    });


                                }


                                $("#keyContnetScroll").html(html);
                                $("#keyContnetScroll").scrollTop(0);

                                hideLoading("#keyContnetScroll");

                            }
                        }
                    })
                }
                showDetailsInfo(nodeMax.links,type, nodeMax.name);
            }

//计算浮窗显示

            $(".top_tab").delegate("#mediacount_popup","mouseover",function(){
                $(".col-md-5 div.tips:last-child").show(200);
            }).delegate("#mediacount_popup"," mouseout",function(){
                $(".col-md-5 div.tips:last-child").hide(300);
            });


            $(".top_tab").delegate("#count_popup","mouseover",function(){
                $(".col-md-5 div.tips:first-child").show(200);
            }).delegate("#count_popup"," mouseout",function(){
                $(".col-md-5 div.tips:first-child").hide(300);
            });






            /*==========================================================================================================*/

        },error:function(e){
            console.log(e)
        }
    })

};

init(id);


