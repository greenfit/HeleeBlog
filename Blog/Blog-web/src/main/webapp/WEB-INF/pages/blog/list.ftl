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
                <#list beans as bean>
                    <div class="card">
	                    <h1 class="title"><a href="/${ bean.disp }.html">${ bean.title }</a></h1>
	                    <div class="meta">
	                        <div class="time"><i class="fa fa-calendar"></i>${ bean.time ? date }</div>
	                        <div class="type"><i class="fa fa-folder"></i>${ bean.type }</div>
	                    </div>
	                    <div class="info">${ bean.summary }</div>
	                    <hr>
	                    <div class="tags"><i class="fa fa-tags"></i>
                            <#list bean.tags?split(",") as tag>
                                <a href="">${ tag }</a>
                            </#list>
                        </div>
	                    <div class="readCount"><i class="fa fa-bookmark"></i>${ bean.count } 次</div>
	                </div>
                </#list>
                <#if end &gt; 1> 
                    <div class="page-nav">
	                <#if page != 1>
	                    <a class="page-number" href="/list/${page - 1}.html">« 上一页</a>
	                </#if>
	                <#if page - 1 &gt; 3>
                        <a class="page-number" href="/list/1.html">1</a>
                        <span class="space">…</span>
                    </#if>
	                <#list start..end as i>
	                    <#if i == page>
	                        <span class="page-number current">${i}</span>
	                    <#else>
	                        <a class="page-number" href="/list/${i}.html">${i}</a>
	                    </#if>
	                </#list>
	                <#if max - page &gt; 3>
	                    <span class="space">…</span>
                        <a class="page-number" href="/list/${max}.html">${max}</a>
                    </#if>
	                <#if page != max>
	                    <a class="page-number" href="/list/${page + 1}.html">下一页 »</a>
	                </#if>
	                </div>
                </#if>
            </div>
            <#include "/common/common_right.ftl">
        </div>
        <#include "/common/common_footer.ftl">
    </body>
</html>