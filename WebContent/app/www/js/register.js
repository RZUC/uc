$(function(){


var register=new Vue({

  el:"#register",
  data:{
    user:{
      username:"",
      password:"",
      password_repeat:"",
      province:"",
      city:"",
      downtown:"",
      agree:false
    },
    errors:{
      username:"",
      password:"",
      password_repeat:"",
      area:""
    },
    provinces:[],
    area:"",
    citys:[],
    countys:[],
    check:true
  },
  created:function(){
      this.getProvince();
  },
  methods:{
    register:register,
    getProvince:getProvince,
    getCitys:getCitys,
    getCountys:getCountys
  }

});




function getCitys(){

  if(!this.user.province){
    return;
  }

     var data={
            fatherID:this.user.province
        };
        var _self = this;
        $.ajax({
            url: "../../location/showLocationByFatherId.do",
            type: "GET",
            data:data,
            dataType: "json",
            success:function(data){
              if(data.state){
                _self.citys=data.data;
                _self.countys=[];
              }else{
                alert(data.message);
              }
            },
            error: function(err) {
                console.log(err);
            }
        });
}
function getCountys(){
     var data={
            fatherID:this.user.city
        };
        var _self = this;
        $.ajax({
            url: "../../location/showLocationByFatherId.do",
            type: "GET",
            data:data,
            dataType: "json",
            success:function(data){
              if(data.state){
                _self.countys=data.data;
              }else{
                alert(data.message);
              }
            },
            error: function(err) {
                console.log(err);
            }
        });
}
function getProvince() {
        var _self = this;
        $.ajax({
            url: "../../location/showProvince.do",
            type: "GET",
            dataType: "json",
            success: function(data) {
                if(data.state){
                  _self.provinces=data.data;
                }else{
                  alert(data.message);
                }
              
            },
            error: function(err) {
                console.log(err);
            }
        });
}


function saveLogin(data){
  date = new Date();
  date.setTime( date.getTime() + ( 0.5 * 60 * 60 * 1000 ));
  $.cookie("user",JSON.stringify(data),{ expires: date });
};




function register(event){

    event.preventDefault();
    var data=JSON.parse(JSON.stringify(this.user));


      if(!data.username.length){
        this.errors.username="请输入邮箱注册";
        return false;
      }else{
          if(!checkEmail(data.username)){
              this.errors.username="请输入正确的邮箱地址";
              return false;
          }else{
             this.errors.username="";
          }
      }



      if(!data.password.length){
          this.errors.password="请输入密码";
          return false;
      }else{
        if(data.password.length<6){
          this.errors.password="密码长度必须大于8位";
          return false;
        }else{
          this.errors.password="";
        }
      }



      if(data.password!=data.password_repeat){
        this.errors.password_repeat="两次输入密码不一样";
        return false;
      }else{
           this.errors.password_repeat="";
      }


      if(!data.province){
        this.errors.area="请选择省份";
        return false;
      }

      if(!data.city){
        this.errors.area="请选择城市";
        return false;
      }

      if(!data.downtown){;
        this.errors.area="请选择县";
        return false;
      }


      if(data.province && data.city && data.county){
        this.errors.area="";
      }

      data["name"]=data.username;
      delete(data.username);
      delete(data.agree);
      delete(password_repeat);

      $.ajax({
          type: "GET",
          url: "../../usercustom/register.do",
          data: data,
          dataType: "json",
          success: function(data) {
              if(data.state){
                saveLogin(data.data);
                window.location.href="user-identity.html";
              }else{
                saveLogin("");
                alert(data.message);
              }
          },
          error: function(e) {
            console.log(e)
          }
      });
    
}

















});