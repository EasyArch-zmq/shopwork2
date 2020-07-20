const myChart = echarts.init(document.getElementById("main"));
const BASE_URL = '//'


$(document).ready(() => {
    $.post(`${BASE_URL} + /Administrators/Select`, (data) => {
        if (data) {
            let data = JSON.parse(data)
            let day = data.list.day
            let number = data.list.number
            let option = SettingOption(day,number)
            myChart.setOption(option)
        }
    })

    $('.input-daterange').datepicker({
        language:'zh-CN',
    })
})


var SettingOption = (Xdata, Ydata) => {
    return {
        // 标题
        title: {
            text: '风险防控图',
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
            data: ['12:00','14:00',"16:00","18:00","20:00","22:00"]
        },
        yAxis: {
            name: '客流量(人)',
            axisLine: {
                lineStyle: {
                    color: '#d14a61'
                }
            },
        },
        // 数据
        series: [{
            type: 'bar',
            data: [5, 20, 36, 10, 10, 20],
        }
        ]
    }
}

myChart.setOption(SettingOption(1,1))


$('#search').click(() => {
    let startVal = $('#startDate').val().split('-')
    let endVal = $('#endDate').val().split('-')
    let data = {
        "year1":`${startVal[0]}`,
        "month1":`${startVal[1]}`,
        "day1":`${startVal[2]}`,
        "year2":`${endVal[0]}`,
        "month2":`${endVal[1]}`,
        "day2":`${endVal[2]}`,
    }
    $.post(`${BASE_URL} + /Administrators/Select`,data,(res) => {
        if (data) {
                    let data = JSON.parse(data)
                    let day = data.list.day
                    let number = data.list.number
                    let option = SettingOption(day,number)
                    myChart.setOption(option)
                }
    })

})

$('#search-smoke').click(() => {
    window.location = 'yangan'
})



