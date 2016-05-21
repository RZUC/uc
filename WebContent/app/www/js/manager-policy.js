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
    "type":"",
    "edit":true
  });

}


function editPolicy(event,list){
  list.edit=!list.edit;
}


function savePolicy(list){
  if(!list.type.length){
    return false;
  }
  list.edit=!list.edit;

  var data={

  }

  
  $.ajax({
          url:"../../policyInfo/add.do",
          type:"GET",
          data:data,
          dataType:"json",
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
    $.ajax({
        url:"test-data/policys.json",
        type:"GET",
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