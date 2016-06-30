


var App = Vue.extend({
  route:{
    activate:function(){},
    data:function(){
      this.all="XXXX"
    }
  },
  data:function(){
    return {
      all:"xxx"
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
      template:'  <div class="basic form-inline">\
                  <h3>基本信息</h3>\
                  <form>\
                  <div class="form-group">\
                    <label>用户名：</label><input type="text" class="form-control" v-model="user.name">\
                  </div>\
                  <div class="form-gruop">\
                  <label>手机号码：</label><input type="text" class="form-control" v-model="user.telephone">\
                  </div>\
                  <div class="form-gruop">\
                        <label>地区：</label><select name="" id="" class="form-control" ><option>省</option></select>\
                        <select name="" id="" class="form-control" ><option>市</option></select>\
                        <select name="" id="" class="form-control" ><option>县</option></select>\
                  </div>\
                  <div class="form-gruop">\
                        <label>技术领域：</label><select name="" id="" class="form-control" ><option>一级分类</option></select>\
                        <select name="" id="" class="form-control" ><option>二级分类</option></select>\
                  </div>\
                  <div class="form-gruop">\
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
      template:'<div>Details</div>'


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
