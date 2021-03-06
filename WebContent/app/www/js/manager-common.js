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
      initUser:initLogin,
      loginOut:loginOut
    }
  });












function initLogin(){
   
    var user=$.cookie("admin");
    if(!user){
        return ;
    }
        user=JSON.parse(user);
        if(user){
             this.user.username=user.name;
             if(user.userTypeId==null){
                this.user.identity=false;
             }else{
                this.user.identity=true;
             }
             this.hasRegister=false;
        }
}


function loginOut(event){

    event.preventDefault();
    event.stopPropagation();
        $.ajax({
            url:"../../user/loginout.do",
            type:"GET",
            dataType:"json",
            success:function(data){
                    if(data.state){
                        $.cookie("user","");
                        window.location.reload();
                    }else{
                        alert(data.message);
                    }
            },
            error:function(err){
                console.log(err);
            }
        })

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


   
