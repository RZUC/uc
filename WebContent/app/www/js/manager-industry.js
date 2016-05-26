$(function() {

    var industry_main = new Vue({
        el: "#industry-main-manager",
        data: {
            mains: [],
            main: ""
        },
        created: function() {
            this.getIndustry();
        },
        methods: {
            getIndustry: getMain,
            selectMain: selectMain,
            editMain: editMain,
            saveMain: saveMain,
            addNewMain:addNewMain,
            deleteMain: deleteMain
        }
    });

    var industry_seconds = new Vue({
        el: "#industry-secondary-manager",
        data: {
            hasParent:false,
            seconds: [],
            second: ""
        },
        methods: {
            addNewSecond:addNewSecond,
            editSecond: editSecond,
            saveSecond: saveSecond,
            deleteSecond: deleteSecond
        }
    });


        function addNewSecond(){
            this.seconds.unshift({
                    id:"",
                    name:"",
                    fatherId:"",
                    edit:true,
                    selected:false
                }) ;
        }


        function addNewMain(){
                this.mains.unshift({
                    id:"",
                    name:"",
                    fatherId:"",
                    edit:true,
                    selected:false
                }) ;               
        }


        function editSecond(second, event) {
            event.preventDefault();
            event.stopPropagation();
            second.edit = !second.edit;
            second.selected = false;
        }



        function saveSecond(second, event) {
                event.preventDefault();
                event.stopPropagation();
                if (!second.name.length) {
                    return false;
                }
                second.edit = !second.edit;
                $.ajax({
                    url: "test-data/area-edit.json",
                    type: "GET",
                    dataType: "json",
                    success: function(data) {},
                    error: function(error) {
                        console.log(error);
                    }
                });
            }


    function deleteSecond(second,event) {
           event.preventDefault();
           event.stopPropagation();
            for (var i = 0; i < this.seconds.length; i++) {
                if (this.seconds[i].id == second.id) {
                    this.seconds.splice(i, 1);
                }
            }
        /*$.ajax({
                    url:"test-data/area-delete.json",
                    type:"GET",
                    dataType:"json",
                    success:function(data){},
                    error:function(error){
                      console.log(error);
                    }
                });*/

    }


   



    function getMain() {
        var _self = this;
        $.ajax({
            url: "../../industry/show.do",
            type: "GET",
            dataType: "json",
            success: function(data) {
                var mains = [];
                $.each(data, function(key, val) {
                    mains.push({
                        name: val.name,
                        id: val.id,
                        fatherId:val.fatherId,
                        edit: false,
                        selected: false
                    });
                });

                _self.mains = mains;
            },
            error: function(err) {
                console.log(err);
            }
        });}

    function getSeconds(id,success){
        var data={
                    id:id
                };
            $.ajax({
                url: "../../industry/showLeveOne",
                type: "GET",
                data:data,
                dataType: "json",
                success:success,
                error: function(err) {
                    console.log(err);
                }
            });}
    }    


    function selectMain(main, event) {

            event.preventDefault();
            event.stopPropagation();

              var  temp=[];
                industry_seconds.second="";
                area_countys.second = [];


        }
    function editMain(main, event) {
        event.preventDefault();
        event.stopPropagation();
        main.edit = !main.edit;
        main.selected = false;
      }

    function updateMain(main){
        $.ajax({
            url: "../../industry/modify.do",
            type: "POST",
            data:JSON.stringify(main),
            contentType:"application/json",
            dataType: "json",
            success: function(data) {},
            error: function(error) {
                console.log(error);
            }
        });
    }   


    function addMain(main){
        $.ajax({
            url: "../../industry/add.do",
            type: "POST",
            data:JSON.stringify(main),
            dataType: "json",
            contentType:"application/json",
            success: function(data) {
                console.log(data)
            },
            error: function(error) {
                console.log(error);
            }
        });
    }        




    function saveMain(main, event) {
            event.preventDefault();
            event.stopPropagation();

           if( !main.name){
                alert("请填写行业名称！");
                return false;
        }

        main.edit = !main.edit;
        var data=JSON.parse(JSON.stringify(main));
            delete(data.edit);
            delete(data.selected);
        if(main.id==""){
                //add
             addMain(data);
        }else{
                //update
             updateMain(data);
        } 
            


        }
    function deleteMain(main,event) {
            event.preventDefault();
            event.stopPropagation();
            var _self=this;
            var data={
                id:main.id
            };
        
        $.ajax({
            url:"../../industry/del.do",
            type:"GET",
            data:data,
            dataType:"json",
            success:function(data){
                for (var i = 0; i < _selft.mains.length; i++) {
                    if (_self.mains[i].id == main.id) {
                        _self.mains.splice(i, 1);
                    }
                }
            },
            error:function(error){
              console.log(error);
            }

        });
    }
});
