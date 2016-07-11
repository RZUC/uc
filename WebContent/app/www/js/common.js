




var initUser=new Vue({
    el:"#initUser",
    data:{
        hasLogin:false,
        hasRegister:true,
        user:{
            username:"",
            identity:""
        }
    },
    created:function(){
        var tmp=window.location.pathname.split("/");
        var filename=tmp[tmp.length-1].split(".html")[0];
        if(filename=="register"){
            this.hasRegister=false;
        }

        this.initLogin();
    },
    methods:{
        initLogin:initLogin,
        loginOut:loginOut
    }
});



function initLogin(){
   
    var user=$.cookie("user");
    if(!user){
        if(window.location.href.indexOf("login.html")>-1 || window.location.href.indexOf("register.html")>-1){
            return;
        }else{
            window.location.href="login.html" 
            return ;
        }
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





















function checkEmail(email){
                var emailReg = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/ ;
                if (!emailReg.test(email)) {
                    //alert('你输入的邮箱格式不正确!');
                    return false ;
                } else{
                           return true ;
                }
             
      }




 function urlQuery(){
        var args={};
        var query=window.location.search.substring(1);
        var paris=query.split("&");
        for(var i=0;i<paris.length;i++){
            var pos=paris[i].indexOf("=");
            if(pos==-1){
              continue;
            }
            var name=paris[i].substring(0,pos);
            var value=paris[i].substring(pos+1);
                value=decodeURIComponent(value);
                args[name]=value;
        }
        return args;
    }







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


   














