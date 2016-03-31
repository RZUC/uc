
    //初始化显示============================= 
    $.ajax({
  	  type:"GET",
  	  dataType:"json",
  	  url:"search/show.do",
  	  success:function(data){
          var recommand=data[0];//推荐搜索
          var hot=data[1];//近期热点
          var cat=data[2];//事件分类
          var list=data[3];//分类事件列表
            showHot(hot);
            showCat(cat);
            showCatContent(list);
            showRecommand(recommand);

  	  }


    });
    
    
  //近期热点事件================
    var showHot=function(data){
    		  var hotEvents="";
            _.each(data,function(e,index){

    			  hotEvents+='<li class="num new_detail">\
                                   <h5><a href="eventAll.html?id='+e.id+'" target="_blank">'+(index+1)+"."+e.name+'</a></h5>\
                                   <div class="col-md-9">\
                                        <div class="row">\
                                        <div class="col-md-6" style="padding-right:0px">\
                                            <span>事件影响力指数：</span>\
                                            <span class="top_blue">'+parseInt(e.impact)+'</span>\
                                        </div>\
                                        <div class="col-md-6">\
                                            <span>事件危机评级：</span>\
                                            <span class="top_blue">'+e.eventLevel+'</span>\
                                        </div>\
                                    </div>\
                                    </div>\
                                    <div class="col-md-3">\
                                      <div class="state">';

                                    //"2016-01-26 00:00:00

                var t=new Date(e.startTime);
                var t= t.getTime();
                var nowD=new Date();
                    nowD=nowD.getTime();


                                      if(e.state=="0"){
                                           if(nowD-t<=1000*60*60*24*15) {
                                               hotEvents += '<span class="stateNew">NEW</span>';
                                           }

                                      }else{
                                            hotEvents+= '<span class="stateNow">NOW</span>';
                                      }
                                             hotEvents+= '</div></div></li>';
    		  
    		   });
          $(".hotEvent .bottom_box ul").html(hotEvents);
    	
    };


    $("#demoWords_toe").delegate("a","click",function(){

      var words=$(this).text();
          search(words);
    });




    var showRecommand=function(data){
        var html="";
        _.each(data,function(e){
          html+='<a class="item">'+e+'</a>';
        });
        $("#demoWords_toe").append(html);
    };




    
    /*展示事件分类*/


    var showCat=function(data){

  

       var cat=$("#eventCatTpl").html();
       var tpl=_.template(cat);
       var html=tpl({cat:data});
       $("#eventCat").html(html);

    };



    var showCatContent=function(data){
      console.log(data)
      var type=$("#typeTpl").html();
      var tpl=_.template(type);
      var html=tpl({lists:data});
      $("#eventList").html(html);


    };



$("#eventCat").delegate("li.cat","click",function(){
    if($(this).hasClass("dropdown")){

    }else{
        var type=$(this).attr("id");
        changeCatContent(type);
    }
      $(this).addClass("selected").siblings().removeClass("selected");

});
    

  var changeCatContent=function(type){

      $.ajax({
        url:"search/showEventsByType.do",
        type:"GET",
        data:{eventType:type},
        dataType:"json",
        success:function(data){
          console.log(data);


           showCatContent(data.slice(0,5));
        },
        error:function(e){
          console.log(e);
        }
      });


  }; 







    /*
    
    
    
    
    
    
     //分平台切换
       var $nav_li=$(".pf_nav >ul >li");
       $nav_li.click(function(){
           $(this).addClass("selected")
                .siblings().removeClass("selected");
       });

      //搜索结果滚动条
        $(function(){
            $("#searchScroll").niceScroll({  
            cursorcolor:"#1E66D0",  
            cursoropacitymax:1,  
            touchbehavior:false,  
            cursorwidth:"5px",  
            cursorborder:"0",  
            cursorborderradius:"5px"  
            }); 
        });

  
        $("#demoWords_toe a.item").click(function(){
            $(".crisisplatform").hide();
            $(".search_content").show();
        });
 

    搜索=====================================================================


*/

        var search=function(words){
          if(words.length){
                $(".crisisplatform").hide();
                $(".search_content").show();
            }

            $("#schword").val(words);

            var data={
              word:words
            };
            $.ajax({
              url:"search/searchEvents.do",
              type:"GET",
              dataType:"json",
              data:data,
              success:function(data){

                    if(data.length){
                        var type=$("#searchContentTpl").html();
                        var tpl= _.template(type);
                        var html=tpl({data:data});
                        $("#searchScroll").html(html);
                    }else{

                        $("#searchScroll").html('<p class="none">无结果……</p>');
                    }







              },
              error:function(e){
                console.log(e);
              }
            });
          };


         $("#gosearch").click(function(e){
            e.preventDefault();
            e.stopPropagation();

            var words=$("#schword").val();
            search(words);


            
         });
