


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
          this.user=JSON.parse($.cookie("user"));
        }
      },
      data:function(){
        return{
            user:""
        }
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
                        <label>地区：</label><select name="" id="" class="form-control" ><option>全部</option></select>\
                        <select name="" id="" class="form-control" ><option>全部</option></select>\
                        <select name="" id="" class="form-control" ><option>全部</option></select>\
                  </div>\
                  <div class="form-group form-inline">\
                        <label>技术领域：</label><select name="" id="" class="form-control" ><option>全部</option></select>\
                        <select name="" id="" class="form-control" ><option>全部</option></select>\
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
