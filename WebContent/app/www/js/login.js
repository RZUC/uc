$(function(){

var login=new Vue({
  el:"#login",
  data:{
    user:{
      username:"",
      password:"",
    },
    errors:{
      username:"",
      password:""
    }
  },
  methods:{
    login:login
  }
});




function login(event){


  event.preventDefault();
  var data=JSON.parse(JSON.stringify(this.user));
    if(data.username.length){
      this.errors.username="";
    }else{
      this.errors.username="请输入用户名";
      return false;
    }
      
    if(data.password.length){
        this.errors.password="";
    } else{
        this.errors.password="请输入密码";
      return false;
    } 
      
      var _self=this;
      $.ajax({
        url:"test-data/login-error.json",
        // url:"test-data/login.json",
        type:"get",//POST
        dataType:"json",
        success:function(data){
              if(data.success){
                //登录成功
                  if(data.user.hasIdentity){
                    window.location.href="user-home.html"
                  }else{
                    window.location.href="user-identity.html"
                  }
              }else{

              }
              _self.errors.username="用户名错误";
              _self.errors.password="用户密码错误";

        },
        error:function(err){
          console.log(err);
        }
      });


  }



});