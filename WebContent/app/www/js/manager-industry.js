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
                    fatherId: industry_main.main.fatherId,
                    edit:true
                }) ;
        }


        function addNewMain(){
                this.mains.unshift({
                    id:"",
                    name:"",
                    fatherId:0,
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


        function addSecond(second,success){
                 $.ajax({
                     url: "../../industry/add.do",
                    type: "POST",
                    data:JSON.stringify(second),
                    dataType: "json",
                    contentType:"application/json",
                    success: success,
                    error: function(error) {
                        console.log(error);
                    }
                });
        }

        function updateSecond(second){
             $.ajax({
                url: "../../industry/modify.do",
                type: "POST",
                data:JSON.stringify(second),
                contentType:"application/json",
                dataType: "json",
                success: function(data) {},
                error: function(error) {
                    console.log(error);
                }
            });
        }


        function saveSecond(second, event) {
                event.preventDefault();
                event.stopPropagation();
                if (!second.name.length) {
                    return false;
                }
                second.edit = !second.edit;




                        if( !second.name){
                                alert("请填写行业名称！");
                                return false;
                        }


                        second.edit = !second.edit;
                        var data=JSON.parse(JSON.stringify(second));
                            delete(data.edit);
                        if(second.id==""){
                                //add
                             addSecond(data,function(data){
                                second.id=data.id;
                             });
                        }else{
                                //update
                             updateSecond(data);
                        }  


               


            }


    function deleteSecond(second,event,index) {
           event.preventDefault();
           event.stopPropagation();
           var data={
                    id:second.id
                };
            var _self=this;    
            $.ajax({
                     url:"../../industry/del.do",
                    type:"POST",
                    data:data,
                    dataType:"json",
                    success:function(data){
                            _self.seconds.splice(index,1);
                    },
                    error:function(error){
                      console.log(error);
                       _self.seconds.splice(index,1);
                    }
                });

    }


   



    function getMain() {
        var _self = this;
        $.ajax({
            url: "../../industry/showLeveOne.do",
            type: "GET",
            dataType: "json",
            success: function(data) {
                var mains = [];
                data=data.data;
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
                    fatherId:id
                };
            $.ajax({
                url: "../../industry/show.do",
                type: "GET",
                data:data,
                dataType: "json",
                success:success,
                error: function(err) {
                    console.log(err);
                }
            });
    }    


    function selectMain(main, event) {
            event.preventDefault();
            event.stopPropagation();

            if(main.id=="" || main.edit==true){
                return false;
            }

            if(this.main.id!=main.id){
                this.main=main; //选择不同
                 $.each(this.mains, function(key, val) {
                                val.selected= false;
                            });

                 main.selected=!main.selected;

                if(main.selected==true){
                    getSeconds(main.id,function(data){
                        industry_seconds.seconds=data.data; 
                        industry_seconds.hasParent=true;
                    });
                }


            }else{
                //选择相同



                main.selected=!main.selected;

                if(main.selected==false){
                     industry_seconds.seconds=[]; 
                     industry_seconds.hasParent=false;
                }else{
                    if(main.selected==true){
                    getSeconds(main.id,function(data){
                        industry_seconds.seconds=data.data; 
                        industry_seconds.hasParent=true;
                    });
                }
                }
               
            }

          


           


            



            


           




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


    function addMain(main,success){
        $.ajax({
            url: "../../industry/add.do",
            type: "POST",
            data:JSON.stringify(main),
            dataType: "json",
            contentType:"application/json",
            success:success,
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
             addMain(data,function(data){
                main.id=data.id;
             });
        }else{
                //update
             updateMain(data);
        } 
            


        }
    function deleteMain(main,event,index) {
            event.preventDefault();
            event.stopPropagation();
            var _self=this;
            var data={
                id:main.id
            };
        
        $.ajax({
            url:"../../industry/del.do",
            type:"POST",
            data:data,
            dataType:"json",
            success:function(data){
                console.log(data);
                _self.mains.splice(index,1);

            },
            error:function(error){
            _self.mains.splice(index,1);
              console.log(error);
            }

        });
    }
});
