var express = require('express');
var router = express.Router();

var request = require('request');
var hbs = require('hbs');

var showdown  = require('showdown');
var converter = new showdown.Converter();
converter.setFlavor('github'); 

require("./common.js");
require("./hbs-helper.js");

//博客列表
router.get('(/page/:page)?', function(req, res) {
    var url = baseUrl + "list.json?page=" + req.params.page;
    request(url, function (error, response, body) {
        if(body == undefined){
            res.render('error', { message: "服务器连接错误!" });
        } else {
            var info = JSON.parse(body);
            var context = { 
                message: info.message, 
                title : "成长之旅 | 黑老李的博客", 
                keywords : "李瑜的博客,全栈开发工程师,Java入门,Spring精通,架构师", 
                description : "李瑜的个人博客,有Java入门系列内容,Spring精通系列,架构师之路系列的文章"
            };
            if (!error && response.statusCode == 200 && info.code == 200) {
                res.render('index', context);
            }else {
                res.render('error', context);
            }
        }
    });
});

//显示详情页
router.get('/blog/:dispURL.html', function(req, res) {
    var url = baseUrl + "blog.json?url=" + req.params.dispURL;
    request(url, function (error, response, body) {
        if(body === undefined) {
            res.render('error', { error: "服务器连接错误!" });
        } else {
            var info = JSON.parse(body);
            if (!error && response.statusCode === 200 && info.code === 200) {
                var context = { 
                    blog: info.message.beans, 
                    html: converter.makeHtml(info.message.beans.blogContent),
                    types: info.message.types,
                    title : info.message.beans.blogTitle,
                    keywords : info.message.beans.tags, 
                    description : info.message.beans.blogSummary
                };
                writeFile(req.params.dispURL, context);
                //console.log(context);
                res.render('blog', context);
            }else {
                res.render('error', { message: info.message });
            }
        }
    });
});

//显示时间线
router.get('/time/:page?', function(req, res) {
    var url = baseUrl + "list.json?page=" + req.params.page + "&rows=8";
    request(url, function (error, response, body) {
        if(body == undefined){
            res.render('error', { message: "服务器连接错误!" });
        } else {
            var info = JSON.parse(body);
            if (!error && response.statusCode == 200 && info.code == 200) {
                res.render('time', { message: info.message });
            }else {
                res.render('error', { message: info.message });
            }
        }
    });
});

//显示分类列表
router.get('/type/:type?/:page?', function(req, res) {
    res.render('type');
});

//显示标签列表
router.get('/tag/:tag?/:page?', function(req, res) {
    res.render('tag');
});

module.exports = router;