<head>
    <link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
    <link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/style.css">
    <style>
        .info-table {
            width: 500px;
        }
    </style>
</head>
<body id="info-list" style="min-width: 1380px;">

<table class="layui-table info-table">
    <colgroup>
        <col width="150">
        <col width="350">
    </colgroup>
    <thead>
        <tr>
            <th>属性</th>
            <th>数值</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td colspan="2" style="text-align: center">虚拟机信息</td>
        </tr>
        <tr v-for="info in bean.jvmInfo">
            <td>{{info}}</td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">辅助</td>
        </tr>
    </tbody>
</table>

<script src="https://static.heleeos.com/lib/jquery.min.js"></script>
<script src="https://static.heleeos.com/lib/vue.min.js"></script>
<script src="https://static.heleeos.com/lib/layui/lay/dest/layui.all.js"></script>
<script src="https://static.heleeos.com/js/vue-filter.js"></script>
<script type="text/javascript">
    $(function(){
        var vm = new Vue({
            el : "#info-list",
            data: {
                bean : {}
            },
            methods: {
                loadInfo: function() {
                    $.post("ajax/main/systemInfo.json").done(function(res){
                        if(res.code === 0) {
                            vm.bean = res.message.bean;
                        }
                    });
                }
            }
        });
        vm.loadInfo();
    });
</script>
</body>