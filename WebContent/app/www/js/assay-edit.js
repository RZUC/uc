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
                technology: "",
                technology_second: "",
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
            beAble:true
        },
        created: function() {

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


        }

    });





    // assay.$data.assay.content=$("#editor").val();
    function submit(event){
      event.preventDefault();
      event.stopPropagation();


      var data=this.assay;


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


    function update(event){
        event.preventDefault();
        event.stopPropagation();
        var data=this.assay;

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

    function getCitys() {


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
                    _self.citys = data.data;
                    _self.countys = [];
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
                    _self.countys = data.data;
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
                    _self.provinces = data.data;
                } else {
                    alert(data.message);
                }

            },
            error: function(err) {
                console.log(err);
            }
        });
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
