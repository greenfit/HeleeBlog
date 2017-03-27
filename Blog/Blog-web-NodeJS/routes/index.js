var express = require('express');
var router = express.Router();

var request = require('request');
var hbs = require('hbs');
var path = require('path');
var fs = require('fs');
var moment = require('moment');

var showdown  = require('showdown');
var converter = new showdown.Converter();
converter.setFlavor('github'); 

//数据接口
var baseUrl = "http://192.168.0.29:7070/";

var writeFile = function(url, context) {
	var input = path.join(__dirname, '../views/blogTemplate.hbs');
	var output = path.join(__dirname, '../html/' + url + ".html");

	fs.readFile(input, function(err,data){
		if(err) throw err;
		//console.log(data.toString());
		var template = hbs.handlebars.compile(data.toString());
		var html     = template(context);
		fs.writeFile(output, html, function(err){
	        if(err) throw err;
	        console.log('生成静态文件:' + output);
	    });
	});
}

//博客列表
router.get('/page/:pg', function(req, res) {
	var url = baseUrl + "list.json?page=" + req.params.pg;
	request(url, function (error, response, body) {
		if(body == "undefined") res.render('blog-error', { error: "服务器连接错误!" });
		var info = JSON.parse(body);
	  	if (!error && response.statusCode == 200 && info.code == 200) {
		    res.render('index', { beans: info.message.beans });
		}else {
		  	res.render('blog-error', { error: info.message });
		}
	});
});

//显示详情页
router.get('/blog/:dispURL', function(req, res) {
	var url = baseUrl + "blog.json?url=" + req.params.dispURL;
	request(url, function (error, response, body) {
		if(body == "undefined") res.render('blog-error', { error: "服务器连接错误!" });
		var info = JSON.parse(body)
	  	if (!error && response.statusCode == 200 && info.code == 200) {
	  		var context = { blog: info.message.beans, html: converter.makeHtml(info.message.beans.content) };
	  		context.blog.tags = context.blog.tags.split(",");
	  		writeFile(req.params.dispURL, context);
	  		res.render('blog', context);
		}else {
		  	res.render('blog-error', { error: info.message });
		}
	});
});

module.exports = router;

hbs.registerHelper("datatime", function(timestamp) {
	return moment(timestamp).format('YYYY-MM-DD HH:mm:ss');
});