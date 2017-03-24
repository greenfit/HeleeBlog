var express = require('express');
var router = express.Router();

var request = require('request');

var showdown  = require('showdown');
var converter = new showdown.Converter();
converter.setFlavor('github'); 

//数据接口
var baseUrl = "http://192.168.0.29:8080/";

//首页列表
router.get('/', function(req, res) {
	var url = baseUrl + "list/1.json";
	request(url, function (error, response, body) {
		var info = JSON.parse(body)
	  	if (!error && response.statusCode == 200 && info.code == 200) {
		    res.render('index', { beans: info.message.beans });
		}else {
		  	res.render('blog-error', { error: info.message });
		}
	});
});

//分页
router.get('/page/:pg', function(req, res) {
	var url = baseUrl + "list/" + req.params.pg + ".json";
	request(url, function (error, response, body) {
		var info = JSON.parse(body)
	  	if (!error && response.statusCode == 200 && info.code == 200) {
		    res.render('index', { beans: info.message.beans });
		}else {
		  	res.render('blog-error', { error: info.message });
		}
	});
});

//显示详情页
router.get('/blog/:dispURL', function(req, res) {
	var url = baseUrl + req.params.dispURL + ".json";
	request(url, function (error, response, body) {
		var info = JSON.parse(body)
	  	if (!error && response.statusCode == 200 && info.code == 200) {
		    res.render('blog', { blog: info.message.beans, html: converter.makeHtml(info.message.beans.content) });
		}else {
		  	res.render('blog-error', { error: info.message });
		}
	});
});

module.exports = router;
