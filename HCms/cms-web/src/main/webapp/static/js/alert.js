/**
 * 打开一个DIV, 并显示页面
 * 
 * title 标题
 * page  要打开的页面
 * call  回调函数
 */
var divCall = function(){};
function openDiv(title, page, call) {
  var index = layer.open({
    type: 2,
    title: title,
    shadeClose: true,
    shade: 0.8,
    area: ['80%', '90%'],
    content: page
  });
  divCall = function(){
    setTimeout(function(){layer.close(index);}, 1000);
    if(typeof(call) == 'function') {
      call();
    }
  };
}

/**
 * 显示提示信息
 * 
 * content 要显示的内容
 */
function dispTip(content) {
  layer.msg(content);
}

/**
 * 显示信息框
 * 
 * title   标题
 * content 内容
 * error   是否为错误信息
 */
function dispMessage(title, content, error) {
  var icon = error ? 2 : 1;
  layer.open({ "title": title, "content": content, "icon": icon });
}

if(typeof(layer) != "undefined") {
  window.alert = dispTip;
}