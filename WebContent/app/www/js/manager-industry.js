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
            getIndustry: getIndustry,
            selectMain: selectMain,
            editMain: editMain,
            saveMain: saveMain,
            deleteMain: deleteMain
        }
    });

    var industry_seconds = new Vue({
        el: "#industry-secondary-manager",
        data: {
            seconds: [],
            second: ""
        },
        methods: {
            editSecond: editSecond,
            saveSecond: saveSecond,
            deleteSecond: deleteSecond
        }
    });



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


   



    function getIndustry() {
        var _self = this;
        $.ajax({
            url: "test-data/manager-industry.json",
            type: "GET",
            dataType: "json",
            success: function(data) {
                var mains = [];
                $.each(data, function(key, val) {
                    mains.push({
                        name: val.name,
                        id: val.id,
                        secondary: val.secondary,
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
    function selectMain(main, event) {

            event.preventDefault();
            event.stopPropagation();

            /*=================================================*/
            var _self = this;
            $.each(this.main.secondary, function(key, val) {
                var d = _.findIndex(industry_seconds.seconds, {
                    id: val.id
                });

                if (d == -1) {
                    _self.main.secondary.splice(key, 1);
                } else {
                    if (industry_seconds.seconds[d].edit == false) {
                        _self.main.secondary[key].name =industry_seconds.seconds[d].name;
                    }
                }
            });
            industry_seconds.second="";
            /*=================================================*/
            if (main.edit == true) {
                return false;
            }

            $.each(this.mains, function(key, val) {
                val.selected = false;
            });

            main.selected = !main.selected;

            if (main.selected == true) {
                this.main = main;
            }

            var seconds = [];
            $.each(this.main.secondary, function(key, val) {
                seconds.push({
                    name: val.name,
                    id: val.id,
                    edit: false,
                    selected: false
                })
            });
            industry_seconds.seconds =seconds;
        }
    function editMain(main, event) {
        event.preventDefault();
        event.stopPropagation();
        main.edit = !main.edit;
        main.selected = false;
      }
    function saveMain(main, event) {
            event.preventDefault();
            event.stopPropagation();

            if (!main.name.length) {
                return false;
            }
            main.edit = !main.edit;
            $.ajax({
                url: "test-data/area-edit.json",
                type: "GET",
                dataType: "json",
                success: function(data) {},
                error: function(error) {
                    console.log(error);
                }

            });}
    function deleteMain(main,event) {
            event.preventDefault();
            event.stopPropagation();
            for (var i = 0; i < this.mains.length; i++) {
            if (this.mains[i].id == main.id) {
                this.mains.splice(i, 1);
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

                });*/}
});
