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
    id:"",
    name:"",
    order:"",
    edit:true
  });

}


function editPolicy(event,list){
  list.edit=!list.edit;
}


function savePolicy(list){
  if(!list.name || isNaN(list.order)){
    alert("请真确填写相关字段");
    return false;
  }
  list.edit=!list.edit;


 var data=JSON.parse(JSON.stringify(list));
                        delete(data.edit);
                        delete(data.selected);

  
  $.ajax({
          url:"../../policyType/add.do",
          type:"POST",
            data:JSON.stringify(data),
            dataType: "json",
            contentType:"application/json",
          success:function(data){},
          error:function(error){
            console.log(error);
          }

      });
}

function deletePolicy(list){
  for(var i=0;i<this.lists.length;i++){
        console.log(this.lists[i].id)
      if(this.lists[i].id==list.id){
        this.lists.splice(i,1);
      }
  }

/*$.ajax({
        url:"test-data/policy-delete.json",
        type:"GET",
        dataType:"json",
        success:function(data){},
        error:function(error){
          console.log(error);
        }

    });*/

}
function policyLists(){
    var _self=this;


/*  private int pageSize;// 每页条数
  private int pageNum;// 当前页面
  private int totalPage;// 总页数
  private int totalSize;// 总条数*/
  var data={
    pageSize:20,
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
          if(!data.length){
            return ;
          }
          $.each(data,function(key,val){
                val.edit=false;
          });
          _self.lists=data;
        },
        error:function(error){
          console.log(error);
        }

    });


}


});