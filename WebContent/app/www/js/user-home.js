
function saveUser(data){
  date = new Date();
  date.setTime( date.getTime() + ( 0.5 * 60 * 60 * 1000 ));
  $.cookie("user",JSON.stringify(data),{ expires: date });
};


function save(evt){
      evt.preventDefault();
      var _self=this;
      var user=JSON.parse(JSON.stringify(this.user));
      $.ajax({
            url:'../../usercustom/update.do',
            type:'POST',
            dataType:'json',
            data:user,
            success:function(data){
                if(data.state){
                     saveUser(data.data);
                }else{
                    alert(data.message);
                }
            },
            error:function(err){
                console.log(err);
            }
        })
}



function getIndustry(){
   var _self = this;
        $.ajax({
            url: "../../industry/showLeveOne.do",
            type: "GET",
            dataType: "json",
            success: function(data) {
                  if(data.state){
                      if(!data.data.length){
                          return;
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


        if(!this.user.industryLevelOneId){
          return;
        }
        var _self=this;
        var data={
                    fatherId:this.user.industryLevelOneId
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


function getDepartment(){
      var _self=this;
      $.ajax({
            url: "../../parameter/department.do",
            type: "GET",
            dataType: "json",
            success: function(data) {
                if (data.state) {

                    _self.departments = data.data;

                } else {
                    alert(data.message);
                }

            },
            error: function(err) {
                console.log(err);
            }
        });
    }


function getMyCollection(){
  var _self = this;
  console.log(this.user)
        var data={
          uid:this.user.id
        };
        $.ajax({
            url: "../../usercustom/collectList.do",
            type: "GET",
            data:data,
            dataType: "json",
            success: function(data) {
                  if(data.state){
                      if(!data.data.length){
                            return;
                      }
                    _self.colls=data.data;


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
            user:{},
            provinces:[],
            citys:[],
            countys:[],
            technologys:[],//技术领域
            technologys_second:[],//技术领域
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
        getSeconds: getSeconds,
        save:save
      },
      template:'  <div class="basic">\
                  <h3>基本信息</h3>\
                  <form>\
                  <div class="form-group form-inline">\
                    <label>用户名：</label><input type="text" class="form-control" v-model="user.name">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>手机号码：</label><input type="text" class="form-control" v-model="user.mobilephone">\
                  </div>\
                  <div class="form-group form-inline">\
                        <label>地区：</label>\
                        <select name="" id="" class="form-control" v-model="user.province"  @change="getCitys"><option :value="0">全部</option><option v-for="province in provinces" :value="province.id"  :selected="user.province==province.id"  >{{province.abbreviation}}</option></select>\
                        <select name="" id="" class="form-control" v-model="user.city"    @change="getCountys"><option :value="0">全部</option><option v-for="city in citys" :value="city.id"  :selected="user.city==city.id"  >{{city.abbreviation}}</option></select>\
                        <select name="" id="" class="form-control" v-model="user.downtown" ><option :value="0">全部</option><option v-for="county in countys" :value="county.id"  :selected="user.downtown==county.id"  >{{county.abbreviation}}</option></select>\
                  </div>\
                  <div class="form-group form-inline">\
                        <label>技术领域：</label>\
                        <select name="industryLevelOneId" id="industryLevelOneId"  @change="getSeconds" v-model="user.industryLevelOneId" class="form-control" >\
                        <option :value="0" :selected="!user.industryLevelOneId">全部</option>\
                        <option v-for="technology in technologys" :value="technology.id" :selected="user.industryLevelOneId==technology.id">{{technology.name}}</option></select>\
                        <select name="industryLevelTwoId" id="industryLevelTwoId" class="form-control" v-model="user.industryLevelTwoId" >\
                        <option :selected="!user.industryLevelTwoId" :value="0">全部</option>\
                        <option v-for="tec in technologys_second" :value="tec.id" :selected="user.industryLevelTwoId==tec.id"  >{{tec.name}}</option></select>\
                  </div>\
                  <div class="form-group form-inline">\
                    <button class="btn btn-default" @click="save($event)">保存</button>\
                  </div>\
                    <form>\
                  </div>'



});
var Details=Vue.extend({
 route:{
        data:function(){
          
        }
      },
      data:function(){
        return{
            user:"",
            org:"org",
            com:"com",
            departments:[]
        }
      },
      computed:{
        userType:function(){
          var userType={
            "org":"机构用户",
            "com":"企业用户"
          };
           if(this.user.userTypeId=="org"){
               this.getDepartment();
            }
          if(this.user.userTypeId){
              return userType[this.user.userTypeId]
          }else{
            return ""
          }
        }
      },
      compiled:function(){
        this.user=JSON.parse($.cookie("user"));
      },
      methods:{
          save:save,
          getDepartment:getDepartment
      },
      template:'<div>\
                  <h3>详细信息</h3>\
                  <form>\
                  <div class="form-group form-inline">\
                  <label>用户名类别：</label><input class="form-control" type="text" readonly value="{{userType}}">\
                  </div>\
                  <div class="com" v-if="user.userTypeId==com">\
                  <div class="form-group form-inline">\
                  <label>企业名称：</label><input class="form-control" type="text" v-model="user.enterpriseName">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>企业地址：</label><input class="form-control" type="text" v-model="user.enterpriseAddress">\
                  </div>\
                  </div>\
                  <div class="org"  v-if="user.userTypeId==org">\
                  <div class="form-group form-inline">\
                  <label>机构名称：</label><input class="form-control" type="text" v-model="user.organizationName">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>机构类别：</label>  <select name="" id="" class="form-control" v-model="user.organizationType"><option :value="0">全部</option><option v-for="department in departments" :value="department.id"  :selected="user.organizationType==department.id"  >{{department.name}}</option></select>\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>服务区域：</label><input class="form-control" type="text" v-model="user.organizLocation">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>网址：</label><input class="form-control" type="text" v-model="user.url">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>简介：</label><textarea class="form-control" type="text" v-model="user.summary"></textarea>\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>可提供服务：</label><input class="form-control" type="text" v-model="user.serviceContent">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>服务内容描述：</label><input class="form-control" type="text" v-modle="user.serviceContentDescription">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>注册地址：</label><input class="form-control" type="text" v-model="user.regiseAddress">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>固定电话：</label><input class="form-control" type="text" v-model="user.telephone">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>门部：</label><input class="form-control" type="text" v-model="user.department">\
                  </div>\
                  <div class="form-group form-inline">\
                  <label>职务：</label><input class="form-control" type="text" v-model="user.job">\
                  </div> \
                  </div>\
                  <div class="form-group form-inline">\
                  <button class="btn btn-default" @click="save($event)">保存</button>\
                  </div>\
                  </div>'


});
var Collect=Vue.extend({
  route:{
    data:function(){}
  },
  data:function(){
    return {
        user:{},
        colls:[]
    }
  },
  compiled:function(){
       this.user=JSON.parse($.cookie("user"));
       this.getMyCollection();
  },
  methods:{
      getMyCollection:getMyCollection
  },
  template:'<div class="myCollect">\
              <h3>我的收藏</h3>\
              <ul className="list-unstyled">\
                <li v-for="coll in colls"><a href="server-details.html?assay={{coll.policyinfoId}}">{{coll.summary}}</a></li>\
              </ul>\
            </div>'
});

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
