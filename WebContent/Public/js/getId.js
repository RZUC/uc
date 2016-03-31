/**
 * Created by zhichaoshen on 2016/3/17.
 */


var id=window.location.href.split("?id=")[1];
var all="eventAll.html?id="+id;
var voice="eventVoice.html?id="+id;
var view="eventView.html?id="+id;
$("#eventAll").attr("disabled","disabled");
$("#eventVoice").attr("disabled","disabled");
$("#eventView").attr("disabled","disabled");
$(function(){
    $("#eventAll").attr("href",all);
    $("#eventVoice").attr("href",voice);
    $("#eventView").attr("href",view);
    $("#eventAll").removeAttr("disabled");
    $("#eventVoice").removeAttr("disabled");
    $("#eventView").removeAttr("disabled");
    var username= $.cookie("username");
    $("#dLabel1 .username").text(username);

    $("#logout").click(function(){
        $.ajax({
            url:"loginout.do",
            type:"GET",
            dataType:"json",
            success:function(data){
                window.location.href="login.html";
            },
            error:function(e){
                console.log(e);
                window.location.href="login.html";
            }
        });
    });

});











