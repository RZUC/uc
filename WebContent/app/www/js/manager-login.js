/*
 if(!data.user){
          this.errors.username="请输入用户名";
          return;
        }else{
          this.errors.username="";
        }


        if(!data.user.password){
          this.errors.password="请输入用户密码";
          return false;
        }else{
          this.errors.password=""
        }
*/
function saveLogin(data){
  date = new Date();
  date.setTime( date.getTime() + ( 0.5 * 60 * 60 * 1000 ));
  $.cookie("admin",JSON.stringify(data),{ expires: date });
};



function login(evt){
  evt.preventDefault();
  evt.stopPropagation();

  var user=JSON.parse(JSON.stringify(this.user));

    $.ajax({
      url:"../../admin/login.do",
      data:$('#loginForm').serialize(),
      type:"POST",
      dataType:"json",
      success:function(data){
          if(!data.state){
            alert(data.message)
          }else{
            saveLogin(data.data);
            window.location.href="manager-policy-info.html"
          }
      },
      error(err){
          console.log(err)
      }
        })
}



new Vue({
  el:"#manager-login",
  data:{
    user:{
      username:"",
      password:""
    }
  },
  methods:{
    login:login
  }
})

