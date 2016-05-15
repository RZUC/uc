$(function(){







var serverList=new Vue({

  el:"#serverList",
  init:function(){
    
  },
  data:{
    types:{
        "top":"政策头条",
        "policy":"政策通知",
        "news":"政策要闻",
        "fiels":"政策文件",
        "reading":"政策解读"
    },
    keyword:"",
    active:"top",
    lists:[]
  },
  beforeCompile:function(){
   this.listInfo("top");
  },
  methods:{
    listInfo:listInfo,
    goSearch:goSearch
  }


});

function goSearch(){

   if((this.keyword.trim()).length){
            window.location.href="search.html?keyword="+this.keyword.trim()+"&type="+this.types[this.active];
         }else{
            return false;
         }

}



//加载信息Start
function listInfo(type,event){
  if(!type){
    return;
  }
  var data={
    type:type
    };
// 切换tab颜色
  this.active=type;
 

  var _self=this;
// 获取数据
  $.ajax({
      type: "GET",
      url: "test-data/server.json",
      data: data,
      dataType: "json",
      contentType: "application/json;charset=utf-8",
      success: function(data) {
          _self.lists=data;
      },
      error: function(e) {
        console.log(e)
      }
  });
}

//加载信息END


});



