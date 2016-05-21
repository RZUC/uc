$(function(){

  var assay=new Vue({
    el:"#server-details",
    data:{
      title:"",
      content:""
    },
    methods:{
        getAssay:getAssay
    },
    created:function(){
      this.getAssay();
    },
    compiled:function(){
      createQrcode();
    }
  })



function getAssay(){

var _self=this;
var id=window.location.hash.substring(1);
var data={
        id:id
     };
$.ajax({
  url:"test-data/server-details.json",
  type:"GET",
  dataType:"json",
  data:data,
  success:function(data){
    if(!data){
      window.location.href="index.html";
      return;
    }
    _self.title=data.title;
    _self.content=data.content;
  },
  error:function(err){
    console.log(err);
  }
})



}


function createQrcode(){
 var url=window.location.href;
        $("#qrcode").qrcode({
          text:url,
          width:120,
          height:120
        });
}




});