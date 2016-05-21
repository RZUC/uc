$(function() {
















            var area_provinces= new Vue({
                el: "#area-province-manager",
                data: {
                    provinces: [],
                    province: ""
                },
                created: function() {
                    this.getArea();
                },
                methods: {
                    getArea: getArea,
                    getProvince: getProvince,
                    editArea: editProvince,
                    saveArea: saveCounty,
                    deleteProvince: deleteProvince
                }
            });

            var area_citys=new Vue({
                el: "#area-city-manager",
                data: {
                    citys:[],
                    city: ""
                },
                methods: {
                    getCity: getCity,
                    editArea: editCity,
                    saveArea: saveCity,
                    deleteCity: deleteCity
                }
            });

            var area_countys=new Vue({
                el: "#area-county-manager",
                data: {
                    countys: [],
                    county: ""
                },
                methods: {
                    getCounty: getCounty,
                    editArea: editCounty,
                    saveArea: saveCounty,
                    deleteCounty: deleteCounty
                }
            });


            function getProvince(prov,event) {
              event.preventDefault();
              event.stopPropagation();
              if(prov.edit==true){
                return false;
              }

              $.each(this.provinces,function(key,val){
                  val.selected=false;
              });
              prov.selected=!prov.selected;
              if(prov.selected==true){
                this.province=prov;
              }


              var citys=[];
              $.each(this.province.citys,function(key,val){
                  citys.push({
                    name:val.name,
                    id:val.id,
                    countys:val.countys,
                    edit:false,
                    selected:false
                  })
              }); 

              area_citys.citys=citys;
            }

            function getCity(cit,event) {


              event.preventDefault();
              event.stopPropagation();
              if(cit.edit==true){
                return false;
              }


              $.each(this.citys,function(key,val){
                  val.selected=false;
              });

              cit.selected=!cit.selected;
              if(cit.selected==true){
                this.city=cit;
              }



            }

            function getCounty() {



            }



            function editProvince(area, event){
               event.preventDefault();
               event.stopPropagation();
               area.edit = !area.edit;
               area.selected=false;
               console.log(area.edit)
            }

            function editCity(area, event){
               event.preventDefault();
               event.stopPropagation();
               area.edit = !area.edit;
               area.selected=false;
            }

            function editCounty(area, event){
               event.preventDefault();
               event.stopPropagation();
               area.edit = !area.edit;
               area.selected=false;
               console.log(area.edit)
            }


            function saveProvince(area, event) {
               event.preventDefault();
               event.stopPropagation();

                if (!area.name.length) {
                    return false;
                }
                area.edit = !area.edit;

                $.ajax({
                        url:"test-data/area-edit.json",
                        type:"GET",
                        dataType:"json",
                        success:function(data){},
                        error:function(error){
                          console.log(error);
                        }

                    });
              }



              function saveCity(area, event) {
               event.preventDefault();
               event.stopPropagation();
               console.log("XX")

                if (!area.name.length) {
                    return false;
                }

                area.edit = !area.edit;


                  var p=_.where(area_provinces,{
                    name:area_provinces.province.name
                  });

              



                $.ajax({
                        url:"test-data/area-edit.json",
                        type:"GET",
                        dataType:"json",
                        success:function(data){},
                        error:function(error){
                          console.log(error);
                        }

                    });
              }

              function saveCounty(area, event) {
               event.preventDefault();
               event.stopPropagation();

                if (!area.name.length) {
                    return false;
                }
                area.edit = !area.edit;

                $.ajax({
                        url:"test-data/area-edit.json",
                        type:"GET",
                        dataType:"json",
                        success:function(data){},
                        error:function(error){
                          console.log(error);
                        }

                    });
              }

              function deleteProvince(province) {

              for(var i=0;i<this.provinces.length;i++){
                console.log(this.provinces[i].id)
                  if(this.provinces[i].id==province.id){
                    this.provinces.splice(i,1);
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


               function deleteCity(city) {
                console.log("XX")
                for(var i=0;i<this.citys.length;i++){
                    if(this.citys[i].id==city.id){
                      this.citys.splice(i,1);
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


               function deleteCounty(county) {

              for(var i=0;i<this.county.length;i++){
                console.log(this.county[i].id)
                  if(this.county[i].id==county.id){
                    this.county.splice(i,1);
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

                function getArea() {
                    var _self = this;
                    $.ajax({
                        url: "test-data/manager-area.json",
                        type: "GET",
                        dataType: "json",
                        success: function(data) {

                            var provinces= [];
                            $.each(data, function(key, val) {
                               provinces.push({
                                    name: val.name,
                                    id: val.id,
                                    citys:val.citys,
                                    edit: false,
                                    selected:false
                                });
                            });
                        
                            _self.provinces = provinces;
                        },
                        error: function(err) {
                            console.log(err);
                        }
                    });

                }


            });
