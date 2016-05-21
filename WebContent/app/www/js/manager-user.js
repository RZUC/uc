$(function() {


    var manager_user = new Vue({
        el: "#manager-user",
        data: {
            edit: {
                id: "",
                name: "",
                position: "",
                part: ""
            },
            users: []
        },
        created: function() {
            this.userLists();
        },
        methods: {
          addUser:addUser,
          saveUser:saveUser,
          editUser:editUser,
          deleteUser:deleteUser,
          userLists:userLists
        }
    });


    function addUser() {

        this.users.unshift({
            "name": "",
            "position": "",
            "part": "",
            "edit": true
        });

    }


    function editUser(event, user) {
        user.edit = !user.edit;
    }


    function saveUser(user) {
        if (!user.name.length || !user.position.length || !user.part.length) {
            return false;
        }
        user.edit = !user.edit;
        /*$.ajax({
                url:"test-data/policy-user.json",
                type:"GET",
                dataType:"json",
                success:function(data){},
                error:function(error){
                  console.log(error);
                }

            });*/
    }

    function deleteUser(user) {
        for (var i = 0; i < this.users.length; i++) {
            if (this.users[i].id == user.id) {
                this.users.splice(i, 1);
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

    function userLists() {
        var _self = this;
        $.ajax({
            url: "test-data/manager-user.json",
            type: "GET",
            dataType: "json",
            success: function(data) {
                if (!data.length) {
                    return;
                }
                $.each(data, function(key, val) {
                    val.edit = false;
                });
                _self.users = data;
            },
            error: function(error) {
                console.log(error);
            }

        });


    }


});
