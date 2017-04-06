var hbs = require('hbs');
var moment = require('moment');

hbs.registerHelper("date", function(timestamp) {
	return moment(timestamp).format('YYYY-MM-DD');
});

hbs.registerHelper("datetime", function(timestamp) {
	return moment(timestamp).format('YYYY-MM-DD HH:mm:ss');
});

hbs.registerHelper("splitTags", function(tag) {
	var str = "";
	var tags = tag.split(",");
	for(var index in tags) {
		str = str + '<a href="">' + tags[index] + '</a>'
	}
	return str;
});

hbs.registerHelper("page", function(url, start, end, max, page) {
	var str = "";
	if(end < 1) return str;
	str += '<div class="page-nav">';
	if(page != 1) {
		str += '<a class="page-number" href="/' + url + '/' + (page - 1) + '">« 上一页</a>';
	}
	if(page - 1 > 3) {
		str += '<a class="page-number" href="/' + url + '/1">1</a><span class="space">…</span>';
	}
	for(var i = start; i <= end; i++) {
		if(i == page) {
			str += '<span class="page-number current">' + i + '</span>'; 
		} else {
			str += '<a class="page-number" href="/' + url + '/' + i + '">' + i + '</a>';
		}
	}
	if(max - page > 3) {
		str += '<span class="space">…</span><a class="page-number" href="/page/' + max + '">' + max + '</a>';
	}
	if(page != max) {
		str += '<a class="page-number" href="/' + url + '/' + (page + 1) + '">下一页 »</a>';
	}
	str += '</div>';
	return str;
});