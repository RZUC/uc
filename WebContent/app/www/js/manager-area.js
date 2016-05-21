$(function() {

    var area_provinces = new Vue({
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
            selectProvince: selectProvince,
            editArea: editProvince,
            saveArea: saveCounty,
            deleteProvince: deleteProvince
        }
    });

    var area_citys = new Vue({
        el: "#area-city-manager",
        data: {
            citys: [],
            city: ""
        },
        methods: {
            selectCity: selectCity,
            editArea: editCity,
            saveArea: saveCity,
            deleteCity: deleteCity
        }
    });

    var area_countys = new Vue({
        el: "#area-county-manager",
        data: {
            countys: [],
            county: ""
        },
        methods: {
            editArea: editCounty,
            saveArea: saveCounty,
            deleteCounty: deleteCounty
        }
    });


    function selectProvince(prov, event) {
        event.preventDefault();
        event.stopPropagation();

/*===============================================================*/

            if (area_citys.city.countys!=undefined) {
              if(area_citys.city!=undefined){
                $.each(area_citys.city.countys, function(key, val) {
                      var d = _.findIndex(area_countys.countys, {
                          id: val.id
                      });
                      if (d == -1) {
                          area_citys.city.countys.splice(key, 1);
                      } else {
                          if (area_countys.countys[d].edit == false) {
                              area_citys.city.countys[key].name = area_countys.countys[d].name;
                          }
                      }
                  });
              }
            }
/*===============================================================*/

        /*=================================================*/
        var _self = this;
        $.each(this.province.citys, function(key, val) {
            var d = _.findIndex(area_citys.citys, {
                id: val.id
            });

            if (d == -1) {
                _self.province.citys.splice(key, 1);
            } else {
                if (area_citys.citys[d].edit == false) {
                    _self.province.citys[key].name = area_citys.citys[d].name;
                }
            }
        });
        area_citys.city="";
        area_countys.countys = [];
        /*=================================================*/
        if (prov.edit == true) {
            return false;
        }

        $.each(this.provinces, function(key, val) {
            val.selected = false;
        });
        prov.selected = !prov.selected;
        if (prov.selected == true) {
            this.province = prov;
        }

        var citys = [];
        $.each(this.province.citys, function(key, val) {
            citys.push({
                name: val.name,
                id: val.id,
                countys: val.countys,
                edit: false,
                selected: false
            })
        });
        area_citys.citys = citys;


    }



    function selectCity(cit, event) {



        /*=================================================*/

        if (this.city.countys!=undefined) {
            var _self = this;
            $.each(this.city.countys, function(key, val) {
                var d = _.findIndex(area_countys.countys, {
                    id: val.id
                });
                if (d == -1) {
                    _self.city.countys.splice(key, 1);
                } else {
                    if (area_countys.countys[d].edit == false) {
                        _self.city.countys[key].name = area_countys.countys[d].name;
                    }
                }
            });
        }

        /*=====================================================================================*/



          area_provinces.province.citys=this.citys;


        /*=================================================*/


        /*=================================================*/
       
        /*=================================================*/





        event.preventDefault();
        event.stopPropagation();
        if (cit.edit == true) {
            return false;
        }


        $.each(this.citys, function(key, val) {
            val.selected = false;
        });

        cit.selected = !cit.selected;
        if (cit.selected == true) {
            this.city = cit;
        }

        var countys = [];
        $.each(this.city.countys, function(key, val) {
            countys.push({
                name: val.name,
                id: val.id,
                edit: false,
                selected: false
            })
        });

        area_countys.countys = countys;

    }





    function editProvince(area, event) {
        event.preventDefault();
        event.stopPropagation();
        area.edit = !area.edit;
        area.selected = false;
        console.log(area.edit)
    }

    function editCity(area, event) {
        event.preventDefault();
        event.stopPropagation();
        area.edit = !area.edit;
        area.selected = false;
    }

    function editCounty(area, event) {
        event.preventDefault();
        event.stopPropagation();
        area.edit = !area.edit;
        area.selected = false;
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
            url: "test-data/area-edit.json",
            type: "GET",
            dataType: "json",
            success: function(data) {},
            error: function(error) {
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

    function saveCounty(area, event) {
        event.preventDefault();
        event.stopPropagation();

        if (!area.name.length) {
            return false;
        }
        area.edit = !area.edit;

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

    function deleteProvince(province) {

        for (var i = 0; i < this.provinces.length; i++) {
            console.log(this.provinces[i].id)
            if (this.provinces[i].id == province.id) {
                this.provinces.splice(i, 1);
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
        for (var i = 0; i < this.citys.length; i++) {
            if (this.citys[i].id == city.id) {
                this.citys.splice(i, 1);
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

        for (var i = 0; i < this.countys.length; i++) {
            console.log(this.countys[i].id)
            if (this.countys[i].id == county.id) {
                this.countys.splice(i, 1);
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
            url: "../../location/showProvince.do",
            type: "GET",
            dataType: "json",
            success: function(data) {

                var provinces = [];
                $.each(data, function(key, val) {
                	 
                    provinces.push({
                        name: val.abbreviation,
                        id: val._id,
                        citys: val.citys,
                        edit: false,
                        selected: false
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
