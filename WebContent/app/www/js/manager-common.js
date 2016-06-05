$(function(){


  var initUser=new Vue({
    el:"#initUser",
    data:{
      user:{
        username:"",
        password:""
      }
    },
    created:function(){
      this.initUser();
    },
    methods:{
      initUser:init
    }
  });


function init(){
    var _self=this;
    $.ajax({
        url:"test-data/manager-login.json",
        type:"GET",
        dataType:"json",
        success:function(data){
          if(!data.username){
            window.location.href="manager-login.html";
            return false;
          }
           _self.user.username=data.username;
        },
        error:function(err){
            console.log(err);
        }
    });


}








});







    function pages() {
        var pages = [];
        if (this.total > 10) {
            if (this.current > 5) {

                if (this.current < (this.total - 2)) {
                    pages.push(1);
                    pages.push(2);
                    pages.push("...");
                    pages.push(this.current - 2);
                    pages.push(this.current - 1);
                    pages.push(this.current);
                    pages.push(this.current + 1);
                    pages.push(this.current + 2);
                    pages.push("...");
                } else {
                    pages.push(1);
                    pages.push(2);
                    pages.push("...");
                    for (var i = this.total - 5; i < this.total; i++) {
                        pages.push(i + 1);
                    }
                }
            } else {

                for (var i = 0; i < 7; i++) {
                    pages.push(i + 1);
                }
                pages.push("...");
            }
        } else {
            for (var i = 0; i < this.total; i++) {
                pages.push(i + 1);
            }
        }
        return pages;
    }


   
