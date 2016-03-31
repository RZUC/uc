// 路径配置
        require.config({
            paths: {
                echarts: '../echarts/echarts-2.2.7//build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/radar' // 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('main')); 
                
                var option = {
    tooltip : {
        show:false
    },
    color:['#1E66D0'],
    polar : [
       {
           indicator : [
               { text: '事件相关性',
                 axisLabel:{
                        show:true,
                        textStyle:{
                            align:'left'
                        }
                },max: 100},
               { text: '媒介渠道',max: 100},
               { text: '传播数量',max: 100}

            ],
         splitArea:{show:false},
         splitLine:{
            lineStyle:{
               color:'#000'
            }},
         axisLine:{show:false}
         
        }
    ],
    series : [
        {
            name: '事件影响力指数',
            type: 'radar',
            itemStyle:{
                normal:{
                    lineStyle:{
                        width:4
                    }
                }
            },
            data : [
                {
                    value : [38, 60, 80],
                    name : '事件影响力指数'
                }

            ]
        }
    ]
}; 

                    
                    
            
    
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );