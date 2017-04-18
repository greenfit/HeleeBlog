/**
 * ECharts 柱状图功能封装.
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
var BarChart = function() {
	var chart, option, data ={x:[],y:[]};
	this.init = function(dom) {
		chart = echarts.init(dom);
		option = {
		   color: ['#3398DB'],
           title: {
               text: '柱状图'
           },
           tooltip : {
        	   trigger: 'axis',
        	   axisPointer : {            // 坐标轴指示器，坐标轴触发有效
        		   type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        	   }
	       },
	       grid: {
	           left: '3%',
	           right: '4%',
	           bottom: '3%',
	           containLabel: true
	       },
           xAxis: {
        	   type : 'category',
               data: data.x,
               axisTick: {
                   alignWithLabel: true
               }
           },
           yAxis: {},
           series: {
               name: '图例',
               type:'bar',
               barWidth: '60%',
               data: data.y
           }
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
		option.series.data = newData.y;
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
	this.pushData = function(x, y, doFlush) {
		option.xAxis.data.push(x);
		option.series.data.push(y);
        if(doFlush) this.flush();
	};
	this.shiftData = function(doFlush) {
		option.xAxis.data.shift();
		option.series.data.shift();
		if(doFlush) this.flush();
	}
}