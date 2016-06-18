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
      county:"",
      agree:false
    },
    errors:{
      username:"",
      password:"",
      password_repeat:"",
      area:""
    },
    area:{},
    provinces:[],
    citys:[],
    countys:[],
    check:true
  },
  computed:{
    citys:function(){
      return this.area[this.user.province];
    },
    countys:function(){
    return this.user.city;
    }
  },
  created:function(){
      this.getArea();
  },
  methods:{
    register:register,
    getArea:getArea
  }

});




function getArea(){
    var _self=this;
    $.ajax({
              type: "GET",
              url: "test-data/area.json",
              dataType: "json",
              success: function(data) {
                  var  keys=[];
                  $.each(data,function(key,val){
                    keys.push(key);
                  })

                 _self.area=data;
                 _self.provinces=keys;
              },
              error: function(e) {
                console.log(e)
              }
          });
        

}



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

      if(!data.county){;
        this.errors.area="请选择县";
        return false;
      }


      if(data.province && data.city && data.county){
        this.errors.area="";
      }


      $.ajax({
          type: "GET",
          url: "../..//usercustom/register.do",
          data: data,
          dataType: "json",
          success: function(data) {
              if(data.success){
                window.location.href="user-identity.html";
              }else{
                alert("注册失败");
              }
          },
          error: function(e) {
            console.log(e)
          }
      });
    
}

















});