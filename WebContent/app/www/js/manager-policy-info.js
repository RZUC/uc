$(function() {


    var policyInfoList = new Vue({
        el: "#policyInfo",
        init: function() {

        },
        data: {
            tabs: [
                { name: "政策通知", type: '2' },
                { name: "政策要闻", type: '3' },
                { name: "政策文件", type: '4' },
                { name: "政策解读", type: '5' }
            ],
            active: "2",
            activeName:"政策通知",
            lists: [],
            total: 1,
            current: 1
        },
        computed: {
            pages: function() {
                return pages.call(this);
            }
        },
        beforeCompile: function() {
            this.listInfo("2");
        },
        methods: {
            listInfo: listInfo,
            next: next,
            previous: previous,
            go: go,
            edit:edit,
            deleteAssay:deleteAssay,
            top:top,
            unTop:unTop
        }
    });


    function edit(data,event){

        event.preventDefault();
        event.stopPropagation();
        var url='assay-edit.html?assay='+data.key;
        window.open(url);

    }

    function deleteAssay(data,index,event){
      event.preventDefault();
      event.stopPropagation();

        var data={
              id:data.key
            };
        var _self=this;    
        $.ajax({
          url:"../../policyInfo/del.do",
          data:data,
          type:"POST",
          success:function(data){
            if(data.state){
                _self.lists.splice(index,1);
            }else {
              alert(data.message);
            }
          }

        });


    }


    function top(data,event){
        event.preventDefault();
        event.stopPropagation();
         var data={
              id:data.key
            };
        var _self=this;    
        $.ajax({
          url:"../../policyInfo/top.do",
          data:data,
          type:"POST",
          success:function(data){
            if(data.state){
               
            }else {
              alert(data.message);
            }
          }

        });
    }

    function unTop(data,event){
        event.preventDefault();
        event.stopPropagation();
          var _self=this;  
         var data = {
              id: data.key
          };

         $.ajax({
          url:"../../policyInfo/untop.do",
          data:data,
          type:"POST",
          success:function(data){
            if(data.state){
               
            }else {
              alert(data.message);
            }
          }

        });

    }



    //加载信息Start
    function listInfo(type, page,event) {

        if (!type) {
            return;
        }


        if(type!==this.active){
          this.active=type;
      
          var tab=_.find(this.tabs,function(d){
            return d.type==type
          });
              this.activeName=tab.name;
        }

        if(event){
          event.preventDefault();
          event.stopPropagation();
        }

        var data = {
            type: type,
            pageNum: page || 1,
            pageSize:10
        };

        var _self = this;
        // 获取数据
        $.ajax({
            type: "GET",
            url: "../../policyInfo/show.do",
            data: data,
            dataType: "json",
            contentType: "application/json;charset=utf-8",
            success: function(data) {
                if(data.state){
                    _self.lists = data.data;
                    _self.total = data.totalPage;
                    _self.current = data.currentPage;
                }else{
                    alert(data.message)
                }
                
            },
            error: function() {

            }
        });
    }

    //加载信息END


    function next(event) {
        event.preventDefault();
        this.current++;
        if (this.current > this.total) {
            this.current = this.total;
        }
        this.go(this.active, this.current,event);

    }

    function previous(event) {
        event.preventDefault();
        this.current--;
        if (this.current < 1) {
            this.current = 1;
        }
        this.go(this.active, this.current,event);
    }

    function go(page, event) {

        event.preventDefault();
        if (isNaN(page)) {
            return;
        } else {
            if (this.current == page) {
                return;
            }
            this.current = page;
            this.listInfo(this.active, page,event);
        }
    }


});
