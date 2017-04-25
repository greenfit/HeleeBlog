/**
 * ECharts 饼图功能封装.
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
var PieChart = function() {
	var chart, option, data ={x:[],y:[]};
	this.init = function(dom) {
		chart = echarts.init(dom);
		option = {
		    title: {
		        text: '饼图',
		        x:'center'
		    },
		    tooltip: {
		        trigger: 'item',
		        formatter: "{a} <br/>{b}: {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        x: 'left',
		        data: data.x
		    },
		    series: {
	            name:'数据',
	            type:'pie',
	            radius: ['30%', '70%'],
	            label: {
	                emphasis: {
	                    show: true,
	                    textStyle: {
	                        fontSize: '30',
	                        fontWeight: 'bold'
	                    }
	                }
	            },
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
	this.clearData = function(doFlush) {
		option.legend.data = [];
		option.series.data = [];
		if(doFlush) this.flush();
	};
	this.setData = function(newLegend, newData) {
		option.legend.data = newLegend;
		option.series.data = newData;
		this.flush();
	};
	this.pushData = function(x, y, doFlush) {
		option.legend.data.push(x);
		option.series.data.push(y);
        if(doFlush) this.flush();
	};
	this.setTitle = function(newText) {
		option.title.text = newText;
		this.flush();
	};
	this.setFormatter = function(newFormatter) {
		option.tooltip.formatter = newFormatter;
		this.flush();
	};
	this.getChart = function() {
		return chart;
	};
	this.setLabelName = function(newName) {
		option.series.name = newName;
		this.flush();
	}
}