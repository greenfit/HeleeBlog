<head>
	<link rel="stylesheet" href="https://static.heleeos.com/lib/layui/css/layui.css">
	<link rel="stylesheet" href="https://static.heleeos.com/blog-manager/css/style.css">
</head>
<body>
    <h1>博客管理</h1>
	<div id="blog-list">
		<table class="layui-table">
			<colgroup>
				<col width="150">
				<col width="200">
				<col>
			</colgroup>
			<thead>
				<tr>
					<th>ID</th>
					<th>标题</th>
					<th>发布时间</th>
					<th>修改时间</th>
					<th>标签</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
			    <tr v-for="bean in beans">
					<td>{{ bean.id }}</td>
					<td>{{ bean.title }}</td>
					<td>{{ bean.time | datetime }}</td>
					<td>{{ bean.lasttime | datetime }}</td>
					<td>{{ bean.tags }}</td>
					<td>{{ bean.isdelete }}{{ bean.istop }}</td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</div>
	<script src="https://static.heleeos.com/lib/jquery.js"></script>
	<script src="https://static.heleeos.com/lib/vue.js"></script>
    <script src="https://static.heleeos.com/lib/layui/lay/dest/layui.all.js"></script>
    <script src="https://static.heleeos.com/js/vue-filter.js"></script>
    <script type="text/javascript">
        $(function(){
        	var vm = new Vue({
                el : "#blog-list",
                data: {
                    loadr: true,
                    error: false,
                    beans: []
                }
            });
        	
            var data = { "module" : 1 };
            $.post("list.json", data).done(function(res){
                if(res.code == 0) {
                    vm.beans = res.message.beans;
                    if(vm.loadr) vm.loadr = false;
                }
            }).fail(function(err){
                vm.error = true;
            });
        });
    </script>
</body>