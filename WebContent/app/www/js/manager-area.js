$(function() {

    var area_provinces = new Vue({
        el: "#area-province-manager",
        data: {
            provinces: [],
            province: ""
        },
        created: function() {
            this.getProvince();
        },
        methods: {
            getProvince: getProvince,
            selectProvince: selectProvince,
            addNewProvince:addNewProvince,
            editArea: editProvince,
            saveProvince: saveProvince,
            deleteProvince: deleteProvince
        }
    });

    var area_citys = new Vue({
        el: "#area-city-manager",
        data: {
            hasParent:false,
            citys: [],
            city: ""
        },
        methods: {
            selectCity: selectCity,
            addNewCity:addNewCity,
            editArea: editCity,
            saveCity: saveCity,
            deleteCity: deleteCity
        }
    });

    var area_countys = new Vue({
        el: "#area-county-manager",
        data: {
            hasParent:false,
            countys: [],
            county: ""
        },
        methods: {
            editArea: editCounty,
            saveCounty: saveCounty,
            addNewCounty:addNewCounty,
            deleteCounty: deleteCounty
        }
    });


    function addNewProvince(){
            this.provinces.unshift({
                abbreviation:"",
                dimensionality:0.0,
                id: "",
                level:1,
                locationName:"",
                longitude:0.0,
                fatherID:"0",
                order:1,
                edit:true,
                selected:false
            });
    }

    function addNewCity(){
            this.citys.unshift({
                abbreviation:"",
                dimensionality:0.0,
                id: "",
                level:2,
                locationName:"",
                longitude:0.0,
                fatherID:area_provinces.province.id,
                order:2,
                edit:true,
                selected:false
            });
    }

    function addNewCounty(){
            this.countys.unshift({
                abbreviation:"",
                dimensionality:0.0,
                id: "",
                level:3,
                locationName:"",
                longitude:0.0,
                fatherID:area_citys.city.id,
                order:3,
                edit:true,
                selected:false
            });
    }


    function selectProvince(prov, event) {
        event.preventDefault();
        event.stopPropagation();

        if(prov.id=="" || prov.edit==true){
            return false;
        }

        var  temp=[];
        area_citys.city="";
        area_countys.countys = [];
        getCity(prov.id,function(data){
                $.each(data.data,function(key,val){
                    temp.push({
                            abbreviation:val.abbreviation,
                            dimensionality:val.dimensionality,
                            fatherID:val.fatherID,
                            id: val.id,
                            level:val.level,
                            locationName:val.locationName,
                            longitude:val.longitude,
                            fatherID:val.fatherID,
                            order:val.order,
                            name:val.locationName,
                            edit: false,
                            selected: false
                        });
                });
                 area_citys.citys=temp;
                 area_citys.hasParent=true;
                 area_countys.hasParent=false;
        });

    


   
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
    }

    function selectCity(cit, event) {
        event.preventDefault();
        event.stopPropagation();

        if(cit.edit==true || cit.id==""){
            return false;
        }

        var  temp=[];
        area_countys.countys = [];
        getCounty(cit.id,function(data){
                $.each(data.data,function(key,val){
                    temp.push({
                            abbreviation:val.abbreviation,
                            dimensionality:val.dimensionality,
                            fatherID:val.fatherID,
                            id: val.id,
                            level:val.level,
                            locationName:val.locationName,
                            longitude:val.longitude,
                            fatherID:val.fatherID,
                            order:val.order,
                            edit: false,
                            selected: false
                        });
                });


                 area_countys.countys =temp;
                 area_countys.hasParent=true;


        });

     



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
    }

    function editProvince(area, event) {
        event.preventDefault();
        event.stopPropagation();
        area.edit = !area.edit;
        area.selected = false;
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

    function updateProvince(area){

        $.ajax({
            url: "../../location/update.do",
            type: "POST",
            data:JSON.stringify(area),
            contentType:"application/json",
            dataType: "json",
            success: function(data) {},
            error: function(error) {
                console.log(error);
            }
        });
    }

    function addProvince(area){
            $.ajax({
                url: "../../location/add.do",
                type: "POST",
                data:JSON.stringify(area),
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
    function saveProvince(area, event) {
        event.preventDefault();
        event.stopPropagation();


        if( !area.order|| !area.abbreviation.length || !area.locationName.length){
                alert("请填写相关字段！");
                return false;
        }

        if(isNaN(area.order)){
                alert("排序只能为数字！");
            return false;
        }

        area.edit = !area.edit;
        var data=JSON.parse(JSON.stringify(area));
            delete(data.edit);
            delete(data.selected);

        if(area.id==""){
                //add
             addProvince(data);
        }else{
                //update
             updateProvince(data);
        }       
    }

    function addCity(area){
            $.ajax({
                url: "../../location/add.do",
                type: "POST",
                data:JSON.stringify(area),
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

    function updateCity(area){
        $.ajax({
            url: "../../location/update.do",
            type: "POST",
            data:JSON.stringify(area),
            contentType:"application/json",
            dataType: "json",
            success: function(data) {
                    console.log(data)
            },
            error: function(error) {
                console.log(error);
            }
        });
    }
    function saveCity(area, event) {
        event.preventDefault();
        event.stopPropagation();

        if( !area.order|| !area.abbreviation.length || !area.locationName.length){
                alert("请填写相关字段！");
                return false;
        }

        if(isNaN(area.order)){
                alert("排序只能为数字！");
            return false;
        }


        area.edit = !area.edit;
        var data=JSON.parse(JSON.stringify(area));
            delete(data.edit);
            delete(data.selected);


        if(area.id==""){
                //add
             addCity(data);
        }else{
                //update
             updateCity(data);
        }  
    }

    function addCounty(area){
        $.ajax({
                url: "../../location/add.do",
                type: "POST",
                data:JSON.stringify(area),
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


    function updateCounty(area){
        $.ajax({
            url: "../../location/update.do",
            type: "POST",
            data:JSON.stringify(area),
            dataType: "json",
            contentType:"application/json",
            success: function(data) {
                    console.log(data);
            },
            error: function(error) {
                console.log(error);
            }
        });
    }


    function saveCounty(area, event) {
        event.preventDefault();
        event.stopPropagation();
        
            if( !area.order|| !area.abbreviation.length || !area.locationName.length){
                            alert("请填写相关字段！");
                            return false;
                    }

                    if(isNaN(area.order)){
                            alert("排序只能为数字！");
                        return false;
                    }


                    area.edit = !area.edit;
                    var data=JSON.parse(JSON.stringify(area));
                        delete(data.edit);
                        delete(data.selected);


                    if(area.id==""){
                            //add
                         addCounty(data);
                    }else{
                            //update
                         updateCounty(data);
                    }  


    }

    function deleteProvince(province,index,event) {
           event.preventDefault();
           event.stopPropagation(); 


            if(province.id==""){
                this.provinces.splice(index,1);
                return;
            }

       var data={
                    locationId:province.id
                };

       var _self=this;
            
        $.ajax({
            url:"../../location/del.do",
            type:"POST",
            data:data,
            dataType:"json",
            success:function(data){
                if(data.state){
                    console.log("删除成功");

                        for (var i = 0; i < _self.provinces.length; i++) {
                            if (_self.provinces[i].id == province.id) {
                                _self.provinces.splice(i, 1);
                            }
                        }

                }else{
                    alert("删除失败");
                }
            },
            error:function(error){
              console.log(error);
            }

        });
    }

    function deleteCity(city,index,event) {
           event.preventDefault();
           event.stopPropagation(); 
        if(city.id==""){
                this.citys.splice(index,1);
                return;
            }
            var data={
                locationId:city.id
            };
            var _self=this;
            $.ajax({
                url:"../../location/del.do",
                type:"POST",
                data:data,
                dataType:"json",
                success:function(data){

                    if(data.state){
                        console.log("删除成功");
                            for (var i = 0; i < _self.citys.length; i++) {
                                if (_self.citys[i].id == city.id) {
                                    _self.citys.splice(i, 1);
                                }
                            }
                    }else{
                        alert("删除失败");
                    }
                },
                error:function(error){
                    console.log(error);
                }
            });
    }

    function deleteCounty(county,index,event) {

           event.preventDefault();
           event.stopPropagation(); 

            if(county.id==""){
                this.countys.splice(index,1);
                return;
            }

         var data = {
         locationId: county.id
         }; 


         var _self=this;
         $.ajax({
             url: "../../location/del.do",
             type: "POST",
             data: data,
             dataType: "json",
             success: function(data){
                if(data.state){
                    for (var i = 0; i < _self.countys.length; i++) {
                        if (_self.countys[i].id == county.id) {
                            _self.countys.splice(i, 1);
                        }
                    }
                    console.log("删除成功");
                }else{
                    alert("删除失败");
                }
             },
             error: function(error) {
                 console.log(error);
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
                        console.log(data)
                var provinces = [];
                $.each(data.data, function(key, val) { 
                    provinces.push({
                        abbreviation:val.abbreviation,
                        dimensionality:val.dimensionality,
                        fatherID:val.fatherID,
                        id: val.id,
                        level:val.level,
                        locationName:val.locationName,
                        longitude:val.longitude,
                        fatherID:val.fatherID,
                        order:val.order,
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

    function getCity(id,success){
        var data={
            fatherID:id
        };
        var _self = this;
        $.ajax({
            url: "../../location/showLocationByFatherId.do",
            type: "GET",
            data:data,
            dataType: "json",
            success:success,
            error: function(err) {
                console.log(err);
            }
        });
    }

    function getCounty(id,success){
        var data={
            fatherID:id
        };
        var _self = this;
        $.ajax({
            url: "../../location/showLocationByFatherId.do",
            type: "GET",
            data:data,
            dataType: "json",
            success:success,
            error: function(err) {
                console.log(err);
            }
        });
    }


});
