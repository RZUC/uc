$(function(){


  var initUser=new Vue({
    el:"#initUser",
    data:{
      user:{
        username:"",
        password:""
      }
    },
    created:function(){
      this.initUser();
    },
    methods:{
      initUser:init
    }
  });


function init(){
    var _self=this;
    $.ajax({
        url:"test-data/manager-login.json",
        type:"GET",
        dataType:"json",
        success:function(data){
          if(!data.username){
            window.location.href="manager-login.html";
            return false;
          }
           _self.user.username=data.username;
        },
        error:function(err){
            console.log(err);
        }
    });


}











});