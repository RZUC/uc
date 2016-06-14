

var App=Vue.extend({});

var Company=Vue.extend({
  template:'#company-tpl'
});

var Organization=Vue.extend({
  template:'#organization-tpl'
})



var router=new VueRouter();


    router.map({
      '/':{
        component:{
          template: '<div class="cats">'+
                      '<div class="type"><a v-link="{name:'+"'com'"+'}">企业用户</a></div>'+
                      '<div class="type"><a v-link="{name:'+"'org'"+'}">机构用户</a></div>'+
                    '</div> '
        }
      },
      '/company':{
        name:"com",
        component:Company
      },
      '/organization':{
        name:"org",
        component:Organization
      }
    });


    router.start(App, '#app')