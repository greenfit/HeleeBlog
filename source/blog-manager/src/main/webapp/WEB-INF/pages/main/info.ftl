<head>
    <link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
    <link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/style.css">
</head>
<body style="min-width: 1380px;">
<h1>服务器管理</h1>
<div id="blog-list">

</div>
<script src="https://static.heleeos.com/lib/jquery.min.js"></script>
<script src="https://static.heleeos.com/lib/vue.min.js"></script>
<script src="https://static.heleeos.com/lib/layui/lay/dest/layui.all.js"></script>
<script src="https://static.heleeos.com/js/vue-filter.js"></script>
<script type="text/javascript">
    $(function(){
        var vm = new Vue({
            el : "#blog-list",
            data: {
                load : true,
                error: false,
                beans: [],
                page : 1,
                rows : 10,
                count: 0
            },
            methods: {
                dispState: function(state) {
                    switch (state) {
                        case 0: return "正常状态";
                        case 1: return "删除状态";
                        case 2: return "草稿状态";
                        default: return "其他状态";
                    }
                },
                editor: function(id) {
                    parent.openDiv('', 'blog/add.html?id=' + id, loadBean);
                },
                changeIndex: function(id, change) {
                    $.post("/ajax/blog/changeIndex.json", { "id" : id, "change" : change}).done(function(res){
                        if(res.code === 200) {
                            parent.dispTip("修改成功");
                            vm.loadBean();
                        } else {
                            parent.dispTip(res.message.info);
                        }
                    }).fail(function(){
                        parent.dispMessage("调整位置", "接口请求失败", true);
                    })
                },
                changeState: function(id, state) {
                    $.post("/ajax/blog/changeState.json", { "id" : id, "state" : state}).done(function(res){
                        if(res.code === 200) {
                            parent.dispTip("修改成功");
                            vm.loadBean();
                        } else {
                            parent.dispTip(res.message.info);
                        }
                    }).fail(function(){
                        parent.dispMessage("修改状态", "接口请求失败", true);
                    })
                },
                loadBean: function() {
                    $.post("/ajax/blog/list.json").done(function(res){
                        if(res.code === 0) {
                            vm.beans = res.message.beans;
                            vm.page = res.message.page;
                            vm.rows = res.message.rows;
                            vm.count = res.message.count;
                            if(vm.loadr) vm.loadr = false;
                        }
                    }).fail(function(err){
                        vm.error = true;
                    });
                }
            }
        });
        vm.loadBean();
    });
</script>
</body>