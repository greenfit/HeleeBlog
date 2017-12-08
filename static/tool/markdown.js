var converter = new showdown.Converter();
converter.setFlavor('github');     
   
    vm = new Vue({
        el: '#markdownEdit',
    data: {
        mark: readData()
    },
    computed: {
        html: function() {
            return converter.makeHtml(this.mark);
        }
    }
});

var inputScroll = false, outputScroll = false;
$("#inputText").scroll(function(event){
    if(inputScroll) return;
    outputScroll = true;
    $("#outputHtml").stop().animate({ scrollTop : $("#inputText").scrollTop() }, 800, function(){outputScroll = false;});
});

$("#outputHtml").scroll(function(event){
    if(outputScroll) return;
    inputScroll = true;
    $("#inputText").stop().animate({ scrollTop : $("#outputHtml").scrollTop() }, 800, function(){inputScroll = false;});
});

function resizeWindow() {
    var tempHeight = $(window).height() - 20;
    if($(window).width() < 768) {
        $("#inputText").height(tempHeight / 2);
        $("#outputHtml").height(tempHeight / 2);
    } else {
        $("#inputText").height(tempHeight);
        $("#outputHtml").height(tempHeight);
    }
}
$(window).resize(resizeWindow);
resizeWindow();

function readData() {
    return "# 欢迎使用\n\n**Heleeos Markdown**是一款轻量级Markdown在线编辑器。使用的技术框架：\n \n- **VueJS** ：是一套构建用户界面的渐进式框架， [查看更多][1]；\n- **ShowdowJS** ：是一个JavaScript编写的Markdown到HTML转换器，[查看更多][2]；\n\n-------------------\n\n## Markdown简介\n\n> Markdown 是一种轻量级标记语言，它允许人们使用易读易写的纯文本格式编写文档，然后转换成格式丰富的HTML页面。    —— [维基百科](https://zh.wikipedia.org/wiki/Markdown)\n\n正如您在阅读的这份文档，它使用简单的符号标识不同的标题，将某些文字标记为**粗体**或者*斜体*，创建一个[链接](http://www.example.com)或一个脚注[^demo]。下面列举了几个高级功能，更多语法请按`Ctrl + /`查看帮助。 \n\n### 代码块\n```\n<script type=\"text/javascript\">\n    var converter = new showdown.Converter();\n    converter.setFlavor('github');       \n   \n    vm = new Vue({\n        el: '#markdownEdit',\n        data: {\n            mark: ''\n        },\n        computed: {\n            html: function() {\n                return converter.makeHtml(this.mark);\n            }\n        }\n    });\n</script>\n```\n\n### 表格\n| 模块名称 | 模块描述 |\n| :-------- | :--------|\n| spring-aop| 基于代理的AOP |\n| spring-aspects| 基于AspectJ的切面 |\n| spring-beans| Bean支持，包括Groovy |\n| spring-context| 应用程序上下文运行时，包括调度和远程抽象 |\n| spring-context-support| 支持将常见的第三方库集成到Spring应用程序上下文中的类 |\n| spring-core| 核心实用程序，由许多其他Spring模块使用 |\n| spring-expression| Spring表达式语言（SpEL） |\n| spring-instrument| JVM引导的工具代理 |\n| spring-instrument-tomcat| Tomcat的工具代理 |\n| spring-jdbc| JDBC支持包，包括DataSource设置和JDBC访问支持 |\n| spring-jms| JMS支持包，包括用于发送和接收JMS消息的助手类 |\n| spring-messaging| 支持消息架构和协议 |\n| spring-orm| 对象/关系映射，包括JPA和Hibernate支持 |\n| spring-oxm| 对象/ XML映射 |\n| spring-test| 支持单元测试和集成测试Spring组件 |\n| spring-tx| 事务基础，包括DAO支持和JCA集成 |\n| spring-web| Web支持包，包括客户端和Web远程处理 |\n| spring-webmvc| Web应用程序的REST Web服务和模型 - 视图 - 控制器实现 |\n| spring-webmvc-portlet| 将在Portlet环境中使用的MVC实现 |\n| spring-websocket| WebSocket和SockJS实现，包括STOMP支持 |\n\n<br>\n\n### 提示信息\n> 这是一个提示**提示**信息。\n\n### 复选框\n\n使用 `- [ ]` 和 `- [x]` 语法可以创建复选框，实现 todo-list 等功能。例如：\n\n- [x] 已完成事项\n- [ ] 待办事项1\n- [ ] 待办事项2\n\n### 图片\n\n使用 `![](url)` 语法可以创建一个图片。例如：\n![](https://static.heleeos.com/blog-web/image/profile.jpg) \nhttps://heleeos.com/\n\n---------\n\n  [1]: https://github.com/vuejs/vue\n  [2]: https://github.com/showdownjs/showdown\n";
}