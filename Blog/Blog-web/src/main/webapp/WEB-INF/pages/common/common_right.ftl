<div class="right hidden-xs col-sm-3 col-md-3 col-lg-2"> 
    <h3>公告</h3> 
    <div class="card">
        <div class="info">网站上线了</div>
    </div>
    <h3>公众号</h3> 
    <img src="https://static.heleeos.com/blog-web/image/qrcode.jpg">
    <h3>分类</h3>
    <div class="type">
        <ul class="type-list">
            <#list types as type>
                <li class="type-list-item">
                    <a class="type-list-link" href=""><i class="fa fa-caret-right"></i>${ type.name }<span class="type-list-count">(${ type.count })</span></a>
                </li>
            </#list>
        </ul>
    </div>
    <h3>标签</h3>
    <dic class="tags">
        <a href="">Spring</a>
        <a href="">Spring Bean</a>
        <a href="">Spring MVC</a>
        <a href="">MySQL</a>
        <a href="">Java8</a>
        <a href="">JVM</a>
        <a href="">AOP</a>
    </dic>
</div>