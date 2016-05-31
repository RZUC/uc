$(function(){
  

var policy=new Vue({
  el:"#manager-policy",
  data:{
    edit:{
      id:"",
      type:""
    },
    lists:[]
  },
  created:function(){
      this.policyLists();
  },
  methods:{
    policyLists:policyLists,
    editPolicy:editPolicy,
    savePolicy:savePolicy,
    addPolicy:addPolicy,
    deletePolicy:deletePolicy
  }
});


function addPolicy(){

  this.lists.unshift({
    id:0,
    name:"",
    order:"",
    edit:true
  });

}


function editPolicy(event,list){
  list.edit=!list.edit;
}


function addNewPolicy(list,success){
    $.ajax({
         url: "../../policyType/add.do",
        type: "POST",
        data:JSON.stringify(list),
        dataType: "json",
        contentType:"application/json",
        success: success,
        error: function(error) {
            console.log(error);
        }
    });
}


function updatePolicy(list,success){

$.ajax({
         url: "../../policyType//modify.do",
        type: "POST",
        data:JSON.stringify(list),
        dataType: "json",
        contentType:"application/json",
        success: success,
        error: function(error) {
            console.log(error);
        }
    });


}

function savePolicy(list,event){
  event.preventDefault();
  event.stopPropagation();
  if(!list.name || isNaN(list.order)){
    alert("请真确填写相关字段");
    return false;
  }
  list.edit=!list.edit;

 var data=JSON.parse(JSON.stringify(list));
                        delete(data.edit);
                        delete(data.selected);
     data.order=parseInt(data.order, 10);                   
        if(list.id==""){
                //add
             addNewPolicy(data,function(data){
                if(data.state){
                    list.id=data.data.id;
                }else{
                    alert(data.message);
                }
                
             });
        }else{
                //update
             updatePolicy(data,function(data){
                  if(data.state!=true){
                      alert("更新失败！");
                      list.edit=true;
                      return false;
                  }
             });
        }  
}

function deletePolicy(list,index){
  var _self=this;


  if(list.id==""){
    this.lists.splice(index,1);
    return false;
  }
  var data={
       id:list.id
     };

    $.ajax({
            url:"../../policyType/del.do",
            type:"POST",
            data:data,
            dataType:"json",
            success:function(data){
                  if(data.state==true){
                    _self.lists.splice(index,1);
                  }else{
                    alert(data.message);
                  }
            },
            error:function(error){
              console.log(error);
            }

        });
}


function policyLists(){
    var _self=this;


/*  private int pageSize;// 每页条数
  private int pageNum;// 当前页面
  private int totalPage;// 总页数
  private int totalSize;// 总条数*/
  var data={
    pageSize:999,
    pageNum:1,
    totalPage:"",
    totalSize:""
  };
    $.ajax({
        url:"../../policyType/show.do",
        type:"POST",
        data:JSON.stringify(data),
        contentType:"application/json",
        dataType:"json",
        success:function(data){
          if(data.state!=true){
            return;
          }

          data=data.data;
          if(!data.length){
            return ;
          }
          $.each(data,function(key,val){
                val.edit=false;
          });
          console.log(data)
          _self.lists=data;
        },
        error:function(error){
          console.log(error);
        }

    });


}


});