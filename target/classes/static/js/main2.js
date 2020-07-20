const myChart = echarts.init(document.getElementById("main"));
// myChart.setOption(option)

// var option = {
//     xAxis: {
//         type: 'category',
//         data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
//     },
//     yAxis: {
//         type: 'value'
//     },
//     series: [{
//         data: [820, 932, 901, 934, 1290, 1330, 1320],
//         type: 'line'
//     }]
// };

var SettingOption = (Xdata, Ydata) => {
    return {
        // 标题
        title: {
            text: '烟雾浓度图',
            left: 'center',
        },
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },

        // x轴
        xAxis: {
            type: 'category',
            data: ['12:00',"14:00","16:00","18:00","20:00","22:00","24:00","02:00","04:00","06:00","08:00","10:00"]
        },
        yAxis: {
            name: '烟雾浓度(立方米)',
            axisLine: {
                lineStyle: {
                    color: '#d14a61'
                }
            },
        },
        // 数据
        series: [{
            type: 'line',
            data: [5, 20, 36, 10, 10, 20,5, 17, 36, 100, 10, 21],
        }
        ]
    }
}

myChart.setOption(SettingOption(1,1))