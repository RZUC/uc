$(function(){

  var assay=new Vue({
    el:"#server-details",
    data:{
      assay:{},
      login:function(){

          var user=$.cookie("user");

            if(!user){
              return false
            }else{
              return JSON.parse(user)  
            }

      }()
    },
    methods:{
        getAssay:getAssay,
        collect:collect
    },
    created:function(){
      this.getAssay();
    },
    compiled:function(){

    }
  })


function collect(evt){
evt.preventDefault();

var data={
    uid:this.login.id,
    policyInfoId:this.assay.id,
    summary:""
};

$.ajax({
  url:'../../usercustom/collect.do',
  type:"GET",
  data:data,
  dataType:"json",
  success:function(data){
      if(data.state){

      }else{
        alert(data.message)
      }
  },
  error:function(err){
    console.log(err)   
  }
})





}


function getAssay(){

var _self=this;
var params={};

$.each(window.location.search.slice(1).split("&"),function(key,val){
    params[val.split("=")[0]]=val.split("=")[1];
});    


if(!params.assay){
  window.location.href="server.html";
}
var data={
        id:params.assay
    };

$.ajax({
  url:"../../policyInfo/showDetail.do",
  type:"GET",
  dataType:"json",
  data:data,
  success:function(data){
    if(data.state){
      console.log(data.data);
      _self.assay=data.data;
    }else{
       alert(data.message);
    }
  },
  error:function(err){
    console.log(err);
  }
})



}



});