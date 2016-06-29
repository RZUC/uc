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

function saveLogin(data){
  date = new Date();
  date.setTime( date.getTime() + ( 0.5 * 60 * 60 * 1000 ));
  $.cookie("user",JSON.stringify(data),{ expires: date });
};




function  checkLogin(){
    var user=$.cookie("user");
}





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
        url:"../../user/login.do",
        data:data,
        type:"POST",
        dataType:"json",
        success:function(data){

          if(data.state){
                  saveLogin(data.data);
                if(data.data.userTypeId!=null){
                    window.location.href="user-home.html"
                  }else{
                    window.location.href="user-category.html"
                  }
          }else{
             _self.errors.username=data.message;
          }     
        },
        error:function(err){
          console.log(err);
        }
      });


  }



});