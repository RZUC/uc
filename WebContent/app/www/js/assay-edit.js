/**
 * @license Copyright (c) 2003-2016, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

/* exported initSample */







$(function() {


    CKEDITOR.replace('editor', {
        removePlugins: 'bidi,dialogadvtab,div,save,print,filebrowser,flash,format,forms,horizontalrule,iframe,pagebreak,showborders,stylescombo,table,tabletools,templates',
        on: {
            instanceReady: function() {
                //SHOW TEXT AREA FOR DEV PURPOSES
                // this.element.show();
            },
            change: function() {
                // Sync textarea.
                this.updateElement();
                assay.$data.assay.content = $("#editor").val();
            }

        }
    });

    CKEDITOR.config.height = 450;




    CKEDITOR.on("change", function() {
        console.log("")
    })


    var assay = new Vue({
        el: "#assay-edit",
        data: {
            assay: { //查询参数
                id:0,
                title: "",
                sourceUrl: "",
                department: 0,
                technology: 0,
                technology_second: 0,
                policyType: 0,
                province: 0,
                city: 0,
                downtown: 0,
                content: "",
                location:"",
                topState:0,
                order:0,
                topStateEndTime:"",
                resourceList:"",
                createTime:"",
                lastUpdateTime:"",
                releaseTime:""  ,
                industry:0
            },
            departments:[],
            technologys: [], //技术领域
            technologys_second: [], //技术领域
            policys: [], //所有政策类型
            provinces: [], //所在地域
            citys: [],
            countys: [],
            lists: [], //结果列表
            total: 1, //查询结果总页数
            current: 1, //当前页数
            beAble:false
        },
        created: function() {


              var query=urlQuery();

               if(query.assay){
                  this.getDetails(query.assay);
               }else{
                  if(!query.add){
                     window.location.href="manager-policy-info.html";
                  }

                 
               }


            this.getPolicyType();
            this.getProvince();
            this.getIndustry();
            this.getDepartment();




        },
        compiled: function() {



        },
        methods: {
            getPolicyType: getPolicyType,
            getProvince: getProvince,
            getCitys: getCitys,
            getCountys: getCountys,
            getIndustry: getIndustry,
            getSeconds: getSeconds,
            getDepartment:getDepartment,
            submit:submit,
            update:update,
            checking:checking,
            getDetails:getDetails


        }

    });


    function getDetails(id){

      var data={
            id:id
          };
          var _self=this;
        $.ajax({
          url:"../../policyInfo/showDetail.do",
          type:"GET",
          data:data,
          dataType:"json",
          success:function(data){
            console.log(data)
            if(data.state){

                var assay=data.data;
                _self.assay.id=assay.id;
  
                _self.assay.province=assay.province;
                _self.getCitys(_self.getCountys);
                _self.assay.city=assay.city;

             
                _self.assay.downtown=assay.downtown;


     
                _self.assay.title=assay.title;
                _self.assay.content=assay.content;
                _self.assay.order=assay.order;
                _self.assay.sourceUrl=assay.sourceUrl;
                _self.assay.department=assay.department;
                _self.assay.policyType=assay.policyType






            }else{
              alert(data.message);
            }

          },
          error:function(err){
            console.log(err);
          }
        })


    }

    function checking(){


          var assay=JSON.parse(JSON.stringify(this.assay));
          console.log(JSON.stringify(this.assay))

            if(assay.technology_second!=0){
              assay.industry=assay.technology_second;
              delete(assay.technology)
              delete(assay.technology_second)
            }else{
               assay.industry=0;
               if(assay.technology!=0){
                    assay.industry=assay.technology;
               }else{
                    assay.industry=0;
               }
                 delete(assay.technology)
               delete(assay.technology_second)
            }


            assay.downtown=assay.county;
            delete(assay.county);

          if(assay.title=="" ||
             assay.sourceUrl=="" ||
             assay.department==0 ||
             assay.policyType==0 ||
             assay.industry==0 ||
             assay.provicne==0 ||
             assay.city==0 ||
             assay.downtown==0 ||
             assay.content==""
            ){
            return false;
          }else{
            return assay;
          }

    }


    // assay.$data.assay.content=$("#editor").val();
    function submit(event){
      event.preventDefault();
      event.stopPropagation();


      var data=this.checking();
      if(!data){
        return false;
      }

        $.ajax({
          url:"../../policyInfo/add.do",
          type: "POST",
          data:data,
          dataType: "json",
          success:function(data){

          },
          error:function(){

          }
        })


    }


    function update(event){
        event.preventDefault();
        event.stopPropagation();
        var data=this.checking();


        if(!data){
          return false;
        }


        $.ajax({
          url:"../../policyInfo/add.do",
          type: "POST",
          data:JSON.stringify(data),
          dataType: "json",
          contentType:"application/json",
          success:function(data){

          },
          error:function(){

          }
        })
    }

    function getDepartment(){
      var _self=this;
      $.ajax({
            url: "../../parameter/department.do",
            type: "GET",
            dataType: "json",
            success: function(data) {
                if (data.state) {

                    _self.departments = data.data;

                } else {
                    alert(data.message);
                }

            },
            error: function(err) {
                console.log(err);
            }
        });
    }

    function getIndustry() {
        var _self = this;
        $.ajax({
            url: "../../industry/showLeveOne.do",
            type: "GET",
            dataType: "json",
            success: function(data) {
                if (data.state) {

                    _self.technologys = data.data;

                } else {
                    alert(data.message);
                }

            },
            error: function(err) {
                console.log(err);
            }
        });
    }

    function getSeconds() {


        if (!this.assay.technology) {
            return;
        }
        var _self = this;
        var data = {
            fatherId: this.assay.technology
        };
        $.ajax({
            url: "../../industry/show.do",
            type: "GET",
            data: data,
            dataType: "json",
            success: function(data) {
                if (data.state) {
                    _self.technologys_second = data.data;
                } else {
                    alert(data.message);
                }
            },
            error: function(err) {
                console.log(err);
            }
        });

    }

    function getCitys(callback) {


        if (!this.assay.province) {
            return;
        }

        var data = {
            fatherID: this.assay.province
        };
        var _self = this;
        $.ajax({
            url: "../../location/showLocationByFatherId.do",
            type: "GET",
            data: data,
            dataType: "json",
            success: function(data) {
                if (data.state) {
                     assay.$set("citys",data.data);
                     assay.$set("countys",[]);
                     if(callback && (typeof(callback)=="function")){
                          callback();
                     }
                } else {
                    alert(data.message);
                }
            },
            error: function(err) {
                console.log(err);
            }
        });
    }


    function getCountys() {
        var data = {
            fatherID: this.assay.city
        };
        var _self = this;
        $.ajax({
            url: "../../location/showLocationByFatherId.do",
            type: "GET",
            data: data,
            dataType: "json",
            success: function(data) {
                if (data.state) {
                
                    assay.$set("countys",data.data);
    
                } else {
                    alert(data.message);
                }
            },
            error: function(err) {
                console.log(err);
            }
        });
    }

    function getProvince() {
        var _self = this;
        $.ajax({
            url: "../../location/showProvince.do",
            type: "GET",
            dataType: "json",
            success: function(data) {
                if (data.state) {
                     assay.$set("provinces",data.data);
                } else {
                    alert(data.message);
                }

            },
            error: function(err) {
                console.log(err);
            }
        });
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



    function getPolicyType() {
        var data = {
            pageSize: 999,
            pageNum: 1,
            totalPage: "",
            totalSize: ""
        };
        var _self = this;
        $.ajax({
            url: "../../policyType/show.do",
            type: "POST",
            data: JSON.stringify(data),
            contentType: "application/json",
            dataType: "json",
            success: function(data) {
                if (data.state) {
                    _self.policys = data.data;
                } else {
                    alert(data.message);
                }
            },
            error: function(err) {
                console.log(err);
            }
        })

    }






});
