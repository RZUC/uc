new Vue({
    el:"#cats",
    data:function(){
        return{
            user:function(){
                var user=$.cookie("user");
                return JSON.parse(user);
            }()
        }
    },
    methods:{
        select:function(evt,type){
                evt.preventDefault();
                var _self=this;
              
              if(type==this.user.userTypeId){
                return;
              }

                $.ajax({
                    url:'../../usercustom/update.do',
                    type:'POST',
                    dataType:'json',
                    data:{
                        id:this.user.id,
                        userTypeId:type
                    },
                    success:function(data){
                        if(data.state){
                             _self.user.userTypeId=type
                             saveUser(data.data);
                             window.location.href="user-home.html"
                        }else{
                            alert(data.message);
                        }
                    },
                    error:function(err){
                        console.log(err);
                    }
                })
               
        }
    }
})



function saveUser(data){
  date = new Date();
  date.setTime( date.getTime() + ( 0.5 * 60 * 60 * 1000 ));
  $.cookie("user",JSON.stringify(data),{ expires: date });
};

