$(function(){

var search=new Vue({
    el:"#search",
    data:{
      searchParams:{//查询参数
        keyword:"",
        technology:0,
        technology_second:0,
        startTime:"",
        endTime:"",
        policy:0,
        province:0,
        city:0,
        county:0,
        page:1,//默认为1
      },
      technologys:[],//技术领域
      technologys_second:[],//技术领域
      policys:[],//所有政策类型
      provinces:[],//所在地域
      citys:[],
      countys:[],
      lists:[],//结果列表
      total:1,//查询结果总页数
      current:1,//当前页数,
      noResult:false
    },
    computed:{
      pages:function(){
      return pages.call(this);
      }
    },
    created:function(){
     var query=urlQuery();
     if(query.keyword){
        this.searchParams.keyword=query.keyword;
     }
      this.getPolicyType();
      this.getProvince();
      this.getIndustry();
      this.search();
    },
    compiled:function(){

        $(".start-time").datetimepicker({
            minView:2,
            autoclose: true


        }).on("changeDate",function(ev){
        
           $(".end-time").datetimepicker('setStartDate', ev.date)

        });
        $(".end-time").datetimepicker({
             minView:2,
            autoclose: true
        }).on("changeDate",function(ev){
         
           $(".start-time").datetimepicker('setEndDate', ev.date)

        });    

    },
    methods:{
        search:search,
        next: next,
        previous: previous,
        go: go,
        getPolicyType:getPolicyType,
        getProvince:getProvince,
        getCitys:getCitys,
        getCountys:getCountys,
        getIndustry:getIndustry,
        getSeconds:getSeconds,
        noRes:noRes
    }

});

function noRes(){
  this.noResult=false;
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


        if(!this.searchParams.technology){
          return;
        }
        var _self=this;
        var data={
                    fatherId:this.searchParams.technology
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




function getCitys(){

  if(!this.searchParams.province){
    return;
  }

     var data={
            fatherID:this.searchParams.province
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
            fatherID:this.searchParams.city
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



function getPolicyType(){
  var data={
    pageSize:999,
    pageNum:1,
    totalPage:"",
    totalSize:""
  };
  var _self=this;
  $.ajax({
        url:"../../policyType/show.do",
        type:"POST",
        data:JSON.stringify(data),
        contentType:"application/json",
        dataType:"json",
        success:function(data){
            if(data.state){
                _self.policys=data.data;
            }else{
              alert(data.message);
            }
        },  
        error:function(err){
          console.log(err);
        }
  })

}



// 查询
function search(){

        var keyword=this.searchParams.keyword;
        var technology=this.searchParams.technology?this.searchParams.technology.id:0;
        var technologys_second=this.searchParams.technology_second?this.searchParams.technology_second:0;
        var startTime=this.searchParams.startTime;
        var endTime=this.searchParams.endTime;
        var policy=this.searchParams.policy?this.searchParams.policy:0;
        var province=this.searchParams.province?this.searchParams.province:0;
        var city=this.searchParams.city?this.searchParams.city:0;
        var county=this.searchParams.county?this.searchParams.county:0;
        var page=this.searchParams.page;




    
    var data={
         word:keyword,
         startTime:startTime,
         endTime:endTime,
         policyTypeId:policy,
         province:province,
         city:city,
         downtown:county,
         pageNum:page,
         industryLeveOneId:technology,
         industryLeveTwoeId:technologys_second,
         pageSize:20
    };


    var _self=this;

      $.ajax({
        url:"../../searchpolicyInfo/search.do",
        type:"POST",
        data:data,
        dataType:"json",
        success:function(data){
          if(data.state){
               _self.lists=data.data;
               _self.total=data.totalPage;
               _self.current=data.currentPage;

               if(data.data.length){
                      _self.noResult=false;
                 }else{
                     _self.noResult=true;
                 }
          }else{
            alert(data.message);
          }
           
        },
        error:function(err){
          console.log(err);
        }
      })
}



    function next(event) {
        event.preventDefault();
        this.current++;
        if (this.current > this.total) {
            this.current = this.total;
        }
        this.go(this.type, this.current);

    }

    function previous(event) {
        event.preventDefault();
        this.current--;
        if (this.current < 1) {
            this.current = 1;
        }
        this.go(this.type, this.current);
    }

    function go(page, event) {

        event.preventDefault();
        if (isNaN(page)) {
            return;
        } else {
            if (this.current == page) {
                return;
            }
       
            this.current= page;
            this.searchParams.page=this.current;
            console.log(page,this.current)
            this.search();
        }
    }





});