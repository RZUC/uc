$(function() {

    var serverMore = new Vue({
        el: "#server-more",
        data: {
            types: {
                "top": "政策头条",
                "policy": "政策通知",
                "news": "政策要闻",
                "fiels": "政策文件",
                "reading": "政策解读"
            },
            lists: [],
            total: 1,
            current: 1
        },
        computed: {
            pages: function() {
                return pages.call(this);
            }
        },
        created: function() {
            var type = window.location.hash.substr(1);
            this.type = type;
            this.listInfo(type);
        },
        methods: {
            listInfo: listInfo,
            next: next,
            previous: previous,
            go: go
        }
    });


    

    function next(event) {
        event.preventDefault();
        this.current++;
        if (this.current > this.total) {
            this.current = this.total;
        }
        this.go(this.type, this.current);

    }

    function previous(event) {
        event.preventDefault();
        this.current--;
        if (this.current < 1) {
            this.current = 1;
        }
        this.go(this.type, this.current);
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
            this.listInfo(this.type, page);
        }
    }



    //加载信息Start
    function listInfo(type, page) {
        if (!type) {
            return;
        }

        var data = {
            type: type,
            pageNum: page || 1,
            pageSize:20
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
                _self.lists = data.data;
                _self.total = data.total;
                _self.current = data.current;
            },
            error: function() {

            }
        });
    }

    //加载信息END



});
