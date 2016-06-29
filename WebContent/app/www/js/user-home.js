


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
var Basic=Vue.extend({});
var Details=Vue.extend({});
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
