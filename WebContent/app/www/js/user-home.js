

function getIndustry(){
   var _self = this;
        $.ajax({
            url: "../../industry/showLeveOne.do",
            type: "GET",
            dataType: "json",
            success: function(data) {
                  if(data.state){
                      if(!data.data.length){

                      }
                    _self.technologys=data.data;


                  }else{
                    alert(data.message);
                  }
          
            },
            error: function(err) {
                console.log(err);
            }
        });
}

function getSeconds(){


        if(!this.user.technology){
          return;
        }
        var _self=this;
        var data={
                    fatherId:this.user.technology
                };
            $.ajax({
                url: "../../industry/show.do",
                type: "GET",
                data:data,
                dataType: "json",
                success:function(data){
                  if(data.state){
                    _self.technologys_second=data.data;
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




function getCitys(){
  console.log(this.user)
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




var App = Vue.extend({
  route:{
    activate:function(){},
    data:function(){
     
    }
  },
  data:function(){
    return {
      
    }
  },
  created:function(){
      $.ajax({
        url:'../../usercustom/navigation.do',
        type:'GET',
        dataType:'json',
        success:function(data){
          console.log(data)
        },
        error:function(err){
          console.log(err)
        }

      })
  }
});

var router = new VueRouter();


var Top=Vue.extend({
  template:"<div>top</div>"
});
var Notice=Vue.extend({});
var News=Vue.extend({});
var File=Vue.extend({});
var Reading=Vue.extend({});
var Basic=Vue.extend({
      route:{
        data:function(){
          

        }
      },
      data:function(){
        return{
            user:"",
            provinces:[],
            citys:[],
            countys:[],
            technologys:[],//技术领域
            technologys_seconds:[],//技术领域
        }
      },
      created:function(e){
         
      },
      compiled:function(){
         this.user=JSON.parse($.cookie("user"));
         this.getProvince();
         this.getCitys();
         this.getCountys();
         this.getIndustry();
         this.getSeconds();
      },
      methods:{
        getProvince:getProvince,
        getCitys:getCitys,
        getCountys:getCountys,
        getIndustry:getIndustry,
        getSeconds: getSeconds
      },
      template:'  <div class="basic">\
                  <h3>基本信息</h3>\
                  <form>\
                  <div class="form-group form-inline">\
                    <label>用户名：</label><input type="text" class="form-control" v-model="user.name">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>手机号码：</label><input type="text" class="form-control" v-model="user.telephone">\
                  </div>\
                  <div class="form-group form-inline">\
                        <label>地区：</label>\
                        <select name="" id="" class="form-control" v-model="user.province"  @change="getCitys"><option :value="0">全部</option><option v-for="province in provinces" :value="province.id"  :selected="user.province==province.id"  >{{province.abbreviation}}</option></select>\
                        <select name="" id="" class="form-control" v-model="user.city"    @change="getCountys"><option :value="0">全部</option><option v-for="city in citys" :value="city.id"  :selected="user.city==city.id"  >{{city.abbreviation}}</option></select>\
                        <select name="" id="" class="form-control" v-model="user.downtown" ><option :value="0">全部</option><option v-for="county in countys" :value="county.id"  :selected="user.downtown==county.id"  >{{county.abbreviation}}</option></select>\
                  </div>\
                  <div class="form-group form-inline">\
                        <label>技术领域：</label>\
                        <select name="technology" id="technology"  @change="getSeconds" v-model="user.industryLevelOneId" class="form-control" ><option :value="0" :selected="!user.industryLevelOneId">全部</option><option v-for="technology in technologys" >{{technology.name}}</option></select>\
                        <select name="technology_second" id="technology_second" class="form-control" v-model="user.industryLevelTwoId" ><option>全部</option></select>\
                  </div>\
                  <div class="form-group form-inline">\
                    <button class="btn btn-default">保存</button>\
                  </div>\
                    <form>\
                  </div>'



});
var Details=Vue.extend({
 route:{
        data:function(){
          this.user=JSON.parse($.cookie("user"));
        }
      },
      data:function(){
        return{
            user:""
        }
      },
      template:'<div>\
                  <h3>详细信息</h3>\
                  <form>\
                  <div class="form-group form-inline">\
                  <label>用户名类别：</label><input type="text" class="form-control">\
                  </div>\
                  <div class="com">\
                  <div class="form-group form-inline">\
                  <label>企业名称：</label><input class="form-control" type="text">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>企业地址：</label><input class="form-control" type="text">\
                  </div>\
                  </div>\
                  <div class="org">\
                  <div class="form-group form-inline">\
                  <label>机构名称：</label><input class="form-control" type="text">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>机构地址：</label><input class="form-control" type="text">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>服务区域：</label><input class="form-control" type="text">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>网址：</label><input class="form-control" type="text">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>简介：</label><textarea class="form-control" type="text"></textarea>\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>可提供服务：</label><input class="form-control" type="text">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>服务内容描述：</label><input class="form-control" type="text">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>注册地址：</label><input class="form-control" type="text">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>固定电话：</label><input class="form-control" type="text">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>门部：</label><input class="form-control" type="text">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>职务：</label><input class="form-control" type="text">\
                  </div> \
                  </div>\
                  <div class="form-group form-inline">\
                  <button class="btn btn-default">保存</button>\
                  </div>\
                  </div>'


});
var Collect=Vue.extend({});



router.map({
    '/':{
        name: "top",
        component: Top
    },
    '/top': {
        name: "top",
        component: Top
    },
    '/notice': {
        name: "notice",
        component: Notice
    },
    '/news':{
      name:"news",
      component:News
    },
    '/file':{
      name:"file",
      component:File
    },
    '/reading':{
      name:"reading",
      component:Reading
    },
    '/basic':{
      name:"basic",
      component:Basic
    },
    '/details':{
      name:"details",
      component:Details
    },
    '/collect':{
      name:"collect",
      component:Collect
    }
});


router.start(App, '#app')
