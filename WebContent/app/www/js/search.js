$(function(){

var search=new Vue({
    el:"#search",
    data:{
      searchParams:{//查询参数
        keyword:"",
        technology:"",
        policy:"",
        area:"",
        page:1,//默认为1
      },
      technologys:[],//技术领域
      policys:[],//所有政策类型
      areas:[],//所在地域
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

     if(query.type){
      this.searchParams.policy=query.type;
     }

     this.getSearchParams();

    },
    methods:{
        search:search,
        next: next,
        previous: previous,
        go: go,
        getSearchParams:getSearchParams,

    }

});


// 查询
function search(){
  var data=JSON.parse(JSON.stringify(this.searchParams));

      data.page=this.current;
  console.log(data)
  console.log(this.current)
      for( obj in data){
          if(!data[obj]){
            delete data[obj];
          }
      }  

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