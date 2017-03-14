<html>
    <head>
        <title>成长之旅 | Heleeos blog</title>
        <link rel="stylesheet" type="text/css" href="https://image.heleeos.com/lib/bootstrap/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="https://image.heleeos.com/lib/font-awesome/css/font-awesome.css">
        <link rel="stylesheet" type="text/css" href="https://image.heleeos.com/blog-web/css/style.css">
        <style type="text/css">

            .container-fluid {
                position: relative;
                top: 50px;
            }

            .left {
                display: block;
                float: left;
                max-width: 281px;
                min-width: 281px;
            }

            .main {
                display: inline;
                float: left;
            }

            .right {
                display: inline;
                float: left;
            }

            .card{
                overflow: hidden;
                background: #fff;
                -webkit-box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);
                box-shadow: 0 2px 5px 0 rgba(0,0,0,0.16), 0 2px 10px 0 rgba(0,0,0,0.12);
            }

            .title {
                padding: 20px 20px 0;
            }

            .title a {
                color: #009688;
                font-size: 32p
            }

            .card .profile-image {
                display: block;
                margin: 10px auto 20px;
                width: 128px;
                height: 128px;
                border-radius: 50%;
                padding: 4px;
                line-height: 1.42857143;
                background-color: #fff;
                border: 1px solid #ddd;
            }

            .card .profile-name {
                font-size: 30px;
                font-weight: 600;
                display: block;
                text-align: center;
            }

            .card .profile-info {
                display: block;
                text-align: center;
            }

            .card .profile-location {
                font-size: 14px;
                margin-top: 5px;
                color: #9a9ea3;
                display: block;
                text-align: center;
            }

            .card .profile-location i{
                margin-right: 5px;
            }

            .card .profile-button {
                color: #fff;
                width: 150px;
                height: 40px;
                display: block;
                font-size: 18px;
                text-align: center;
                margin: 20px auto 10px;
                background: #38b7ea;
                border-radius: 20px;
                -webkit-transition: 0.2s ease;
                -moz-transition: 0.2s ease;
                -ms-transition: 0.2s ease;
                transition: 0.2s ease;
            }

            .card .profile-other {
                height: 60px;
                padding: 15px 20px;
                border-top: 1px solid #eceff2;
            }

            .card .profile-other a {
                width: 26;
                height: 26;
                font-size: 30px;
                position: relative;
                -webkit-transition: 0.2s ease;
                -moz-transition: 0.2s ease;
                -ms-transition: 0.2s ease;
                transition: 0.2s ease;
                color: #898d92;
                margin-right: 10px;
            }

            .card .profile-other a:hover {
                color: #565a5f;
            }

        </style>
    </head>
    <body>
        <div class="header hidden-xs col-sm-12">
            <a href="/" class="logo">
                <i class="logo-image"></i>
                <span class="site-title">Heleeos</span>
            </a>

            <nav class="main-nav">
                <a class="main-nav-link" href="">主页</a>
                <a class="main-nav-link" href="">目录</a>
                <a class="main-nav-link" href="">技术干货</a>
                <a class="main-nav-link" href="">读后感</a>
                <a class="main-nav-link" href="">关于我</a>
            </nav>

            <nav class="profile-nav hidden-lg">
                <img class="profile-image" src="static/image/1.jpg">
                <a href="javascript:;"><i class="fa fa-caret-down"></i></a>
            </nav>

            
        </div>

        <div class="header visible-xs-block" style="height: 50px;">
            <nav class="mobile-nav">
                <a class="mobile-nav-link" href="">主页</a>
                <a class="mobile-nav-link" href="">目录</a>
                <a class="mobile-nav-link" href="">技术干货</a>
                <a class="mobile-nav-link" href="">读后感</a>
                <a class="mobile-nav-link" href="">关于我</a>
            </nav>
        </div>

        <div class="container-fluid">
            <div class="left visible-lg-inline-block col-lg-3">
                <div class="card">
                    <img class="profile-image" src="static/image/1.jpg">
                    <p class="profile-name">李瑜</p>
                    <p class="profile-info">info</p>
                    <p class="profile-location"><i class="fa fa-map-marker"></i>中国 - 上海</p>
                    <a class="profile-button btn">fork</a>
                    
                    <div class="profile-other">
                        <a href="" target="_blank"><i class="fa fa-github"></i></a>
                        <a href="" target="_blank"><i class="fa fa-git"></i></a>
                        <a href="" target="_blank"><i class="fa fa-free-code-camp"></i></a>
                        <a href="" target="_blank"><i class="fa fa-envelope"></i></a>
                        <a href="" target="_blank"><i class="fa fa-facebook"></i></a>
                    </div>
                </div>
            </div>

            <div class="main col-xs-12 col-sm-9 col-md-6 col-lg-6">
                <div class="card">
                    <h1 class="title"><a href="">标题</a></h1>
                    <p class="time">2017年3月14日</p>
                    <p class="type">分类</p>
                    <p class="info">摘要</p>
                    <hr>
                    <p class="tags">a,b,c,d</p>
                    <p class="readCount">58 阅读量</p>
                </div>
            </div>

            <div class="right hidden-xs col-sm-3 col-md-3 col-lg-3">
                公告
            </div>
        </div>
    </body>
</html>