var express = require('express');
var router = express.Router();

var request = require('request');
var hbs = require('hbs');

var showdown  = require('showdown');
var converter = new showdown.Converter();
converter.setFlavor('github'); 

require("./common.js");
require("./hbs-helper.js");

//首页重定向到第一页, 代码重复, 为了对用户友好
router.get('/', function(req, res) {
	var url = baseUrl + "list.json?page=1";
	request(url, function (error, response, body) {
		if(body == undefined){
			res.render('error', { message: "服务器连接错误!" });
		} else {
			var info = JSON.parse(body);
		  	if (!error && response.statusCode == 200 && info.code == 200) {
			    res.render('index', { message: info.message });
			}else {
			  	res.render('error', { message: info.message });
			}
		}
	});
});

//博客列表
router.get('/page/:pg', function(req, res) {
	var url = baseUrl + "list.json?page=" + req.params.pg;
	request(url, function (error, response, body) {
		if(body == undefined){
			res.render('error', { message: "服务器连接错误!" });
		} else {
			var info = JSON.parse(body);
		  	if (!error && response.statusCode == 200 && info.code == 200) {
			    res.render('index', { message: info.message });
			}else {
			  	res.render('error', { message: info.message });
			}
		}
	});
});

//显示详情页
router.get('/blog/:dispURL.html', function(req, res) {
	var url = baseUrl + "blog.json?url=" + req.params.dispURL;
	request(url, function (error, response, body) {
		if(body == undefined) {
			res.render('error', { error: "服务器连接错误!" });
		} else {
			var info = JSON.parse(body)
		  	if (!error && response.statusCode == 200 && info.code == 200) {
		  		var context = { 
	  				blog: info.message.beans, 
	  				html: converter.makeHtml(info.message.beans.content), 
	  				types: info.message.types
		  		};
		  		writeFile(req.params.dispURL, context);
		  		res.render('blog', context);
			}else {
			  	res.render('error', { message: info.message });
			}
		}
	});
});

module.exports = router;