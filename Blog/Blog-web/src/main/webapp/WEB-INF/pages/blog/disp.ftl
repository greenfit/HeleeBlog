<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
        <title>成长之旅 | Heleeos blog</title>
        <link rel="stylesheet" type="text/css" href="https://static.heleeos.com/lib/bootstrap/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="https://static.heleeos.com/lib/font-awesome/css/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="https://static.heleeos.com/blog-web/css/common.css">
        <link rel="stylesheet" type="text/css" href="https://static.heleeos.com/blog-web/css/style.css">
    </head>
    <body>
        <#include "/common/common_header.ftl">
        <div class="container-fluid">
            <#include "/common/common_left.ftl">
            <div class="main col-xs-12 col-sm-9 col-md-9 col-lg-7">
                <div id="dispBLog" class="card">
                    <h1 class="title"><a>{{ bean.title }}</a></h1>
                    <div class="meta">
                        <div class="time"><i class="fa fa-calendar"></i>{{ bean.time | date }}</div>
                        <div class="type"><i class="fa fa-folder"></i>分类</div>
                        <div class="type"><i class="fa fa-tags"></i><a href="">a</a><a href="">b</a><a href="">c</a></div>
                        <div class="type"><i class="fa fa-bookmark"></i>{{ bean.count }} 次</div>
                    </div>
                    <div class="info">摘要:{{ bean.summary }}</div>
                    <hr>
                    <div class="info" v-html="html"></div>
                </div>
            </div>
            <#include "/common/common_right.ftl">
        </div>
        <#include "/common/common_footer.ftl">
        <script src="https://static.heleeos.com/lib/jquery.js"></script>
        <script src="https://static.heleeos.com/lib/vue.js"></script>
        <script src="https://static.heleeos.com/lib/vue-filter.js"></script>
        <script src="https://static.heleeos.com/lib/showdown.min.js"></script>
        <script type="text/javascript">
	        var converter = new showdown.Converter();
	        converter.setFlavor('github');     
        
            var vm = new Vue({
                el : '#dispBLog',
                data : {
                    bean : ''
                },
	            computed: {
	                html: function() {
	                    return converter.makeHtml(this.bean.content);
	                }
	            }
            });
            
            $.get("${dispURL}.json").done(function(res){
                if(res.code == 200) {
                    vm.bean = res.message.beans;
                }
            }).fail(function(err){
                console.log(err);
            });
        </script>
    </body>
</html>