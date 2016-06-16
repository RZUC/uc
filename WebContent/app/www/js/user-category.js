var App = Vue.extend({});









function getIndustryLevelOne(){
   var _self = this;
        $.ajax({
            url: "../../industry/showLeveOne.do",
            type: "GET",
            dataType: "json",
            success: function(data) {
                  if(data.state){

                    _self.industryLevelOnes=data.data;

                  }else{
                    alert(data.message);
                  }
          
            },
            error: function(err) {
                console.log(err);
            }
        });
}

function getIndustryLevelTwo(){


        if(!this.company.industryLeveOneId){
          return;
        }
        var _self=this;
        var data={
                    fatherId:this.company.industryLeveOneId
                };
            $.ajax({
                url: "../../industry/show.do",
                type: "GET",
                data:data,
                dataType: "json",
                success:function(data){
                  if(data.state){
                    _self.industryLevelTwos=data.data;
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


  if(!this.company.province){
    return;
  }

     var data={
            fatherID:this.company.province
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
            fatherID:this.company.city
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




var Company = Vue.extend({
    data: function() {
        return {
            company: {
                username: "",
                password: "",
                companyName: "",
                companyAddress: "",
                industryLeveOneId: 0,
                industryLeveTwoId: 0,
                concat: "",
                phone: "",
                email: "",
                province: 0,
                city: 0,
                downtown: 0
            },
            provinces:[],
            citys:[],
            countys:[],
            industryLevelOnes:[],
            industryLevelTwos:[]
        }
    },
    created:function(){

        this.getProvince();
        this.getIndustryLevelOne();




    },
    methods:{
        getProvince:getProvince,
        getCitys:getCitys,
        getCountys:getCountys,
        getIndustryLevelOne:getIndustryLevelOne,
        getIndustryLevelTwo:getIndustryLevelTwo
    },
    template: '#company-tpl'
});

var Organization = Vue.extend({
    template: '#organization-tpl'
})



var router = new VueRouter();


router.map({
    '/': {
        component: {
            template: '<div class="cats">' +
                '<div class="type"><a v-link="{name:' + "'com'" + '}">企业用户</a></div>' +
                '<div class="type"><a v-link="{name:' + "'org'" + '}">机构用户</a></div>' +
                '</div> '
        }
    },
    '/company': {
        name: "com",
        component: Company
    },
    '/organization': {
        name: "org",
        component: Organization
    }
});


router.start(App, '#app')
