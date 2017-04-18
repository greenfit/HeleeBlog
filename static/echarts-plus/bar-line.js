/**
 * ECharts 柱状图和折线图功能封装.
 * 
 * init(dom)      - 根据dom初始化
 * flush()        - 刷新图表
 * setOption()    - 设置新的配置
 * setData()      - 设置数据
 * setTitle()     - 设置标题
 * setFormatter() - 设置提示信息格式化方式
 * pushData()     - 追加一个数据点
 * shiftData()    - 删除头部一个数据点
 * 
 * @author liyu
 */
var BarLineChart = function() {
	var chart, option, data ={x:[],y:[],z:[]};
	this.init = function(dom) {
		chart = echarts.init(dom);
		option = {
		   color: ['#7CB5EC', '#FF5722'],
           title: {
               text: '柱状和折线'
           },
           tooltip: {
   	          trigger: 'axis',
   	          axisPointer: {
   	              type: 'cross',
   	              crossStyle: {
   	                  color: '#999'
   	              }
   	          }
   	       },
	       xAxis: {
	   	       type: 'category',
	   	       data: data.x,
	   	       axisPointer: {
	   	           type: 'shadow'
	   	       }
	   	   },
		   	yAxis: [
		        {
		           
		        },
		        {
		           
		        }
		   ],
           series: [{
	            name : '柱状',
	            type : 'bar',
	            data : data.y
	       },{
	            name :'折线',
	            type :'line',
	            data : data.z,
	            yAxisIndex: 1
	       }]
        };
	};
	this.flush = function() {
		chart.setOption(option);
	};
	this.setOption = function(newOption) {
		option = newOption;
		this.flush();
	};
	this.setData = function(newData) {
		option.xAxis.data = newData.x;
		option.series[0].data = newData.y;
		option.series[1].data = newData.z;
		this.flush();
	};
	this.setTitle = function(newText) {
		option.title.text = newText;
		this.flush();
	};
	this.setFormatter = function(newFormatter) {
		option.tooltip.formatter = newFormatter;
		this.flush();
	};
	this.pushData = function(x, data1, data2, doFlush) {
		option.xAxis.data.push(x);
		option.series[0].data.push(data1);
		option.series[1].data.push(data2);
        if(doFlush) this.flush();
	};
	this.shiftData = function(doFlush) {
		option.xAxis.data.shift();
		option.series[0].data.shift();
		option.series[1].data.shift();
		if(doFlush) this.flush();
	}
	this.setName = function(name1, name2) {
		option.series[0].name = name1;
		option.series[1].name = name2;
		this.flush();
	};
}