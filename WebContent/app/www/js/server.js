$(function(){







var serverList=new Vue({

  el:"#serverList",
  init:function(){
    
  },
  data:{
    tabs:[
        {name:"政策头条",type:'top'},
        {name:"政策通知",type:'2'},
        {name:"政策要闻",type:'3'},
        {name:"政策文件",type:'4'},
        {name:"政策解读",type:'5'}
    ],
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
            window.location.href="search.html?keyword="+this.keyword.trim();
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
      url: "../../policyInfo/showType.do",
      data: data,
      cache:true,
      dataType: "json",
      success: function(data) {
          if(data.state!=true){
            return false;
          }
          _self.lists=data.data;
      },
      error: function(e) {
        console.log(e)
      }
  });
}

//加载信息END


});



