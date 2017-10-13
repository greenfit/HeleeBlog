var path = require('path');
var fs = require('fs');
var hbs = require('hbs');

var baseUrl = "http://127.0.0.1:8080/"; //数据接口

var writeFile = function(url, context) {
    var input = path.join(__dirname, '../views/blogTemplate.hbs');
    var output = path.join(__dirname, '../blog/' + url + ".html");

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

global.baseUrl = baseUrl;
global.writeFile = writeFile;