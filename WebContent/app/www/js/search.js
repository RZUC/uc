$(function(){

var search=new Vue({
    el:"#search",
    data:{
      searchParams:{//查询参数
        keyword:"",
        technology:"",
        technologys_second:"",
        policy:"",
        province:"",
        city:"",
        county:"",
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
      current:1,//当前页数
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

    },
    methods:{
        search:search,
        next: next,
        previous: previous,
        go: go,
        getPolicyType:getPolicyType,
        getSearchParams:getSearchParams,
        getProvince:getProvince,
        getCitys:getCitys,
        getCountys:getCountys,
        getIndustry:getIndustry,
        getSeconds:getSeconds
    }

});

function getIndustry(){
   var _self = this;
        $.ajax({
            url: "../../industry/showLeveOne.do",
            type: "GET",
            dataType: "json",
            success: function(data) {
                  if(data.state){

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
                    fatherId:this.searchParams.technology.id
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
            fatherID:this.searchParams.province.id
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
            fatherID:this.searchParams.city.id
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

debugger

var data={


}

/*  
    

 keyword:"",
        technology:"",
        technologys_second:"",
        policy:"",
        province:"",
        city:"",
        county:"",
        page:1,//默认为1*/




    if(!data.keyword.trim().length){
      return false;
    }

    var _self=this;
      $.ajax({
        url:"test-data/search.json",
        type:"GET",
        data:data,
        dataType:"json",
        success:function(data){
            if(!data){
              return false;
            }
            _self.lists=data.lists;
            _self.total=data.total;
            _self.current=data.current;
        },
        error:function(err){
          console.log(err);
        }
      })
}

// 查询select 参数
function getSearchParams(){
  var _self=this;
  $.ajax({
    url:"test-data/search-params.json",
    type:"GET",
    dataType:"json",
    success:function(data){
          if(!data){
              return false;
            }
        _self.technologys=data.technology;
        _self.policys=data.policy;
        _self.areas=data.area;
    },
    error:function(err){
      console.log(err);
    }
  });
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
            console.log(page,this.current)
            this.search();
        }
    }





});