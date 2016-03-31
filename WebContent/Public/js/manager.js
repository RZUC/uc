$(function(){



});

//正在分析中===================
var showAnaly = function(data) {
    var analy = data;

        analy=analy.sort(function(a,b){
            console.log(a,b)
            var atime=new Date(a.saveTime);
                atime= atime.getTime();
            var btime=new Date(b.saveTime);
                btime=btime.getTime();
            console.log(atime>btime)
            return atime<btime;
        });
    console.log(analy);

    var html = "";
    for (var i = 0; i < analy.length; i++) {
        html += '<li class="num new_detail" id="analy_' +analy[i].id + '">' + 
              '<h5>' + 
              ' <a href="eventAll.html?id='+analy[i].id+'" target="_blank">' +
               (i+1) +"."+ analy[i].name + '</a>' +
               ' <span class="red_now">NOW</span>' + 
              '</h5>' + 
              '<div class="col-md-9">' + 
               ' <div class="row">' + 
               ' <div class="col-md-6" style="padding-right:0px">' + 
               ' <span>事件影响力指数：</span>' + 
               ' <span class="top_blue">' + parseInt(analy[i].impact) + '</span>' + 
               '</div>' +
               '<div class="col-md-6">' + 
                 '<span>事件危机评级：</span>' + 
                 '<span class="top_blue">' + analy[i].eventLevel + '</span>' + 
               '</div>' + '</div>' + '</div>' + 
               '<div class="col-md-3">' + 
                '<div class="bs-example bs-example-padded-bottom col-md-4">' + 
                '<a class="analysis_lock lock" href="#" data-id="' + analy[i].id + '" ></a>' + 
                ' </div>' + 
                '<div class="bs-example bs-example-padded-bottom col-md-4 col-md-offset-3">' + 
                ' <a  class="analysis_del del" href="#" data-id="' + analy[i].id + '"  >' + ' </a>' + 
              '</div>' + '</div>' + ' </li>'
    };
    $("#analyzenow div.news:first-child>ul").html(html);
};


//已完成分析====================

  var CompleteEvent=function(data){


  var showComplete=data;
          var complete="";
          for (var i = 0; i < showComplete.length; i++) {
              complete += '<li class="num new_detail" id="analy_' + showComplete[i].id + '">' +
                      '<h5>' +
                           ' <a href="eventAll.html?id='+showComplete[i].id+'" target="_blank">' + 
                           (i+1)+"."+showComplete[i].name + '</a>'+
                       '</h5>' + 
                       '<div class="col-md-9">' + 
                         ' <div class="row">' + 
                           ' <div class="col-md-6" style="padding-right:0px">' + 
                             ' <span>事件影响力指数：</span>' + 
                             ' <span class="top_blue">' + parseInt(showComplete[i].impact) + '</span>' + 
                            '</div>' + 
                            '<div class="col-md-6">' + 
                             ' <span>事件危机评级：</span>' + 
                              '<span class="top_blue">' + showComplete[i].eventLevel + '</span>' + 
                            '</div>' +
                          '</div>' + 
                       '</div>' + 
                       ' <div class="col-md-3">' +
                          '<div class="bs-example bs-example-padded-bottom col-md-8 col-md-offset-6">' +
                           ' <a  class="analysis_del del" href="#" data-id="' + showComplete[i].id + '"  >' + ' </a>' +
                          '</div>' + 
                        '</div>' + 
                      ' </li>'
           };
        $("#analyzenow div.news:last-child>ul").html(complete);

        console.log(complete)
    
  };
  $.ajax({
      type: "GET",
      dataType: "json",
      url: "manager/showCompleteEvent.do",
      success: function(data) {
          var showComplete = data;
          CompleteEvent(showComplete);
      }
  });




//已有分类================
var showClassify = function(data) {
    var classify = "";
    for (var i = 0; i < data.length; i++) {
        classify += '<span class="spanGap" id="'+data[i].id+'">' + data[i].eventType + '<i class="glyphicon glyphicon-remove"></i></span>'
    }
    $("#classify").html(classify);
};
//危机等级================
var showGrade = function(data) {
    var grade = "";
    for (var i = 0; i < data.length; i++) {
        grade += '<span class="spanGap" id="'+data[i].id+'">' + data[i].eventLevel + '<i class="fa fa-times"></i></span>'
    }
    $("#grade").html(grade);
};
//时间选择
$(".start_datetime").datetimepicker({
    format: 'yyyy-mm-dd hh:00',
    autoclose: true,
    minView:1


});
$(".end_datetime").datetimepicker({
    format: 'yyyy-mm-dd hh:59',
    autoclose: true,
    minView:1
});


$(".start_datetime").on("change",function(){
    var s=$(this).val();
    $('.end_datetime').datetimepicker('setStartDate', s);
});

$(".end_datetime").on("change",function(){
    var e=$(this).val();
    $('.start_datetime').datetimepicker('setEndDate',e);
});

//切换正在分析/已完成分析
var $top_span=$(".top_nav >ul>li>span");
    $top_span.click(function(){
        $(this).addClass("selected")
             .siblings().removeClass("selected");

    var index=$top_span.index(this);   //获取当前单击的span元素在全部span元素中的索引
    $("#analyzenow div.news")
         .eq(index).show()           //比较：当div的索引与span相同时，显示
         .siblings().hide();
    });



//已完成分析滚动条
$("#completecroll").niceScroll({
                cursorcolor:"#1E66D0",
                cursoropacitymax:1,
                touchbehavior:true,
                cursorwidth:"5px",
                cursorborder:"0",
                cursorborderradius:"5px"
});



//添加事件关键词
var $add_key = $(".add_key>a");
var $key_body = $("ul.key_body");
var add_content = $("ul.key_body>li").clone(true).html();
    add_content = '<li class="row">' + add_content;
    add_content += '</li>';
    $add_key.click(function() {
    $key_body.append(add_content);
});
//关闭分析窗口
$("#analyzenow").delegate(".lock", "click", function() {
    $("#lockModal").show().addClass("in");
    var id = $(this).attr("data-id");
    $("#lockModal .sure").attr("data-target", id);
});
$("#lockModal .close").click(function() {
    $("#lockModal").hide();
});
$("#lockModal .cancle").click(function() {
    $("#lockModal").hide();
});
$("#lockModal .sure").click(function() {
    $("#lockModal").hide();
    var id = $(this).attr("data-target");
    $("#analy_" + id + " .analysis_lock").removeClass("lock").addClass("locked");
    stopAnaly(id) //ajax 后台停止
});
//关闭删除窗口
$("#analyzenow").delegate(".analysis_del", "click", function() {
    $("#delateModal").show().addClass("in");
    var id = $(this).attr("data-id");
    $("#delateModal .sure").attr("data-target", id);
});
$("#delateModal .close").click(function() {
    $("#delateModal").hide();
});
$("#delateModal .cancle").click(function() {
    $("#delateModal").hide();
});
$("#delateModal .sure").click(function() {
    $("#delateModal").hide();
    var id = $(this).attr("data-target");
    $("#analy_" + id).remove();
    deleteAnaly(id) //ajax 后台删除
});
//初始化显示=============================

// $("input[name=correlation]").on("change",function(){
//             if($(this).val()=="true"){
//                 $("input[name=correlationNum]").removeAttr("disabled");
//             }else{
//                 $("input[name=correlationNum]").attr("disabled","disabled");
//             }
// });


var getEventType=function(){
    $.ajax({
        type:"GET",
        dataType:"json",
        url:"manager/showType.do",
        success:function(data){
            var html="";
            _.each(data,function(e){
                html+='<option value="'+ e.id+'">'+ e.eventType+'</option>' ;
            });

            $("#eventType option:gt(1)").remove();
            $("#eventType").append(html);
        },
        error:function(e){
            console.log(e);
        }

    });
};



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
};




var loadInit=function(){
    var data = {
        "username": $.cookie("username")
    };
    $.ajax({
        type: "GET",
        data: data,
        dataType: "json",
        url: "manager/show.do",
        success: function(data) {
            var analy = data[0];
            showAnaly(analy);
            var eventType = data[1];
            showClassify(eventType);
            var grade = data[2];
            showGrade(grade);
            getEventType();
            getEventGrade();
        }
    });

};

/*初始化*/
loadInit();





var stopAnaly = function(id) {
    //ajax 停止分析事件
    data = {
        eventId: id
    };
    $.ajax({
        url: 'manager/updataEvent.do',
        type: "POST",
        data: data,
        success: function(data) {
        //        停止分析。。。。。。
        },
        error: function(e) {
            console.log(e);
        }
    });
};
var deleteAnaly = function(id) {
    //ajax 删除事件
    console.log("执行删除分析", id);
    data = {
        eventId: id
    };

    $.ajax({
        url: 'manager/deleteEvent.do',
        type: "POST",
        data: data,
        dataType:"json",
        success: function(data) {
            $("#addEvent .submit").removeAttr("disabled");

        },
        error: function(e) {
            console.log(e);
        }
    });
};
//=====================Add========================
//提交添加事件
var getEventDate = function() {
    var name = $("input[name=name]").val();
    var eventType = $("select[name=eventType]").val();
    var eventLevel = $("select[name=eventLevel]").val();
    var correlation = $("input[name=correlation]:checked").val();
    var correlationNum=$("input[name=correlationNum]").val();
    var endTime = $("input[name=endTime]").val();
    var startTime = $("input[name=startTime]").val();
    var content = $("textarea[name=content]").val();
    var key1s = $("input[name=event_key1]");
    var key2s = $("input[name=event_key2]");
    var relatives = $("select[name=relative]");
    var word ="";

    if(!name){
       alert("请填写事件名称");
       return ;
   }
    if(!eventType){
        alert("请选择事件分类");
        return;
    }
    if(!eventLevel){
        alert("请选择危机等级");
        return;
    }
    if(!correlationNum){
        alert("请填写事件相关性分值");
    }else{
        if(parseInt(correlationNum)>100 || parseInt(correlationNum)<0 ){
           alert("事件相关性分值应在0-100之间!")
           return;
        }
    }
   

    if(!key1s.length){
        alert("请填写事件关键词");
        return ;
    }

    if(!content){
        alert("请填写事件简介");
        return;
    }
    _.each(key1s, function(e, index) {


    	if(index==0 || (index==key1s.lenght-1)){
            word+="(";
    	}else{
            word+="|(";
    	}
        var k1 = $(e).val();
        var k2 = $(key2s[index]).val();
        var r =  $(relatives[index]).val();
        word+=(k1+r+k2);
        word+=")";
       
    });

    return {
        "name":  name,
        "eventType": eventType,
        "eventLevel": eventLevel,
        "correlation": correlation,
        "correlationNum": correlationNum,
        "startTime": startTime,
        "endTime": endTime,
        "word":  word,
        "content": content,
        "state":"1"//默认值
    };
};

var submitTips=function(title,content){
    $("#submitTips h4").text(title);
    $("#submitTips .modal-body").text(content);
    $("#submitTips").modal("show");
}




$("#addEvent button.submit").click(function(e) {
    e.preventDefault();
    e.stopPropagation();
    var data = getEventDate();
    if(data){
        $.ajax({
            type: "POST",
            url: " manager/addEvent.do ",
            data: JSON.stringify(data),
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function(data) {
                if(data.state==true){
                    submitTips("危机事件案例库","事件添加成功");
                    loadInit();
                //    事件添加成功
                }else{
                //    事件添加失败
                    submitTips("危机事件案例库","事件添加失败");
                    alert("事件已满，请删除后再添加！");
                    $("#addEvent .submit").attr("disabled","disabled");
                }

            },
            error: function() {}
        });
    }


});
/*事件等级分类*/
$("#addType .sure").click(function(e) {
    e.preventDefault();
    e.stopPropagation();

    var eventType = $("input[name=eventType]").val();
    if(!eventType){
        return;
    }
    var data = {
        type: eventType
    };

    $.ajax({
        type: "POST",
        url: " manager/addType.do ",
        dataType: "json",
        data: data,
        success: function(data) {
           if(data.state){
               submitTips("危机事件案例库",("事件分类:"+eventType+"添加成功!"));
               loadInit();
           }else{
               submitTips("危机事件案例库",("事件分类:"+eventType+"添加失败!"));
           }
        },
        error: function(e) {
            console.log(e);
        }
    });
});
/*危机登记 禁用*/
$("#addLevel .sure").click(function(e) {
    e.preventDefault();
    e.stopPropagation();
    var eventLevel = $("input[name=eventLevel]").val();
    var data = {
        level: eventLevel
    };
    if(!eventLevel){
        return;
    }
    $.ajax({
        type: "POST",
        url: " manager/addLevel.do ",
        dataType: "json",
        data: data,
        success: function(data) {
            console.log(data);
        },
        error: function() {}
    });
});



//删除分类


$("#classify").delegate(".spanGap","click",function(e){
    $("#classify .spanGap").removeClass("active");
    if($(this).hasClass("active")){
        $(this).removeClass("active");
    }else{
        $(this).addClass("active");
    }
});


$("#classify").delegate("i","click",function(e){
    e.stopPropagation();
    e.preventDefault();

    var catId=$(this).parent(".spanGap").attr("id");
        cat=$(this).parent(".spanGap").text();
        showDeleteCat(cat,catId);
});

var showDeleteCat=function(cat,catId){
    $("#deleteCat .modal-title span").text(cat);
    $("#deleteCat .sure").attr("data-id",catId);
    $("#deleteCat").modal("show");
};


$("#deleteCat .sure").click(function(){


    var catId=$(this).attr("data-id");
    var data={
        typeId:catId
    };
    deleteCat(data);
});


var deleteCat=function(data){

    $.ajax({
       url:"manager/deleteType.do",
       type:"POST",
       data:data,
       dataType:"json",
        success:function(e){
            console.log("删除成功");
            $("#"+data.typeId).remove();
            $("#deleteCat").modal("hide");
            getEventType();
        },
        error:function(e){
            console.log(e);
        }
    });

};