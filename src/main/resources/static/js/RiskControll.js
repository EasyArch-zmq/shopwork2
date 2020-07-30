const myChart = echarts.init(document.getElementById("bar"));
const BASE_URL = 'http://121.199.21.197:63391'
var people=[];
var time=[];
var resd;
let startVal,endVal;
window.onload=function() {
    // let username = $('#username').val();
    let username =window.localStorage.getItem('username')
    console.log(username)

    // let username = "ocbbc";
    $.ajax({
        url: `${BASE_URL}` + '/selectDefaultNumber',
        data: '{ "username": "' + username + '"}',
        method: 'post',
        contentType: "application/json",
        success: function (res) {
            console.log(res)
            resd=res;
            for (let i = 0; i < res[0].list.length; i++) {
                time.push(res[0].list[i].time);
            }
            for (let i = 0; i < res[0].list.length; i++) {
                people.push(res[0].list[i].num);
            }
            let option = SettingOption(time,people)
            myChart.setOption(option)
            for (var i = 0; i < res.length; i++) {
                var bt =document.createElement("button");
                bt.className="redbt";
                bt.id="redbt"+i;
                bt.style.marginTop=i*40;
                bt.style.marginLeft=i*40;
                // bt.onclick=a(0);
                $('#uu').append(bt)
            }
            for (let i = 0; i < resd.length; i++) {
                a(i)
            }
        }
    })
    $.ajax({
        url: `${BASE_URL}` + '/ProvinceAddress',
        method: 'post',
        contentType: "application/json",
        success: function (res) {
            console.log(res[0])
            console.log(res.length)
            let pro, options;
            for(let i=0; i<res.length; i++)
            {  pro =$("#province");
            options += '<option value="' + res[i] + '" >' + res[i]+ '</option>';}
            pro.append(options);
        }
    })

    $('#province').change(function(){
        var opt=$("#province").val();
        console.log(opt)
        // var d=[{Province:opt}]
        $.ajax({
            url: `${BASE_URL}` + '/CityAddress',
            // data: d,
            data: '{ "province": "'+opt+'"}',
            method: 'post',
            contentType: "application/json",
            success: function (res) {
                console.log(res);
                let pro, options;
                for(let i=0; i<res.length; i++)
                {  pro =$("#city");
                    options += '<option value="' + res[i] + '" >' + res[i]+ '</option>';}
                pro.append(options);
            }
        })
    });
    $("#city").change(function(){
        var city=$("#city").val();
        var pro=$("#province").val();
        $.ajax({
            url: `${BASE_URL}` + '/CountyAddress',
            data:'{"province":"'+pro+'","city":"'+city+'"}',
            method: 'post',
            contentType: "application/json",
            success: function (res) {
                console.log(res)
                let pro, options;
                for(let i=0; i<res.length; i++)
                {  pro =$("#country");
                    options += '<option value="' + res[i] + '" >' + res[i]+ '</option>';}
                pro.append(options);
            }
        })
    });
    $("#country").change(function(){
        var city=$("#city").val();
        var pro=$("#province").val();
        var coun=$("#country").val();
        $.ajax({
            url: `${BASE_URL}` + '/SpecificAddress',
            data:'{"province":"'+pro+'","city":"'+city+'","county":"'+coun+'"}',
            method: 'post',
            contentType: "application/json",
            success: function (res) {
                console.log(res)
                $("#address").autocomplete({
                    source: res,
                    width: 320,
                    max: 4,
                    highlight: false,
                    multiple: true,
                    multipleSeparator: "",
                    scroll: true,
                    scrollHeight: 300
                });
            }
        })
    });

}


//
// var list = [
//     {
//         num: 1,
//         time: "00"
//     },
//     {
//         num: 2,
//         time: "02"
//     },
//     {
//         num: 2,
//         time: "04"
//     },
//     {
//         num: 2,
//         time: "06"
//     },
//     {
//         num: 2,
//         time: "08"
//     },
//     {
//         num: 2,
//         time: "10"
//     },
//     {
//         num: 13,
//         time: "12"
//     }]
// for(var i=0;i<list.length;i++) {
//     time.push(list[i].time);
// }
// for(var i=0;i<list.length;i++) {
//     people.push(list[i].num);
// }

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
            name:'时间',
            axisLine: {
                lineStyle: {
                    color: '#000000'
                }
            },
            data: time
            // data: ['12:00','13:00',"14:00","15:00","16:00","17:00"]
        },
        yAxis: {
            name: '客流量(人)',
            axisLine: {
                lineStyle: {
                    color: '#000000'
                }
            },
        },
        // 数据
        series: [{
            type: 'bar',
            data: people,
            // data: [5, 20, 36, 10, 10, 20],
            itemStyle:{
                normal: {
                    //每根柱子颜色设置
                    color: function(params) {
                        let colorList = [
                            "#91eeee",
                            "#42d3e7",
                            "#5ca8db",
                            "#2394b1",
                            "#386cad",
                            "#4173a0",
                        ];
                        return colorList[params.dataIndex];
                    }
                }
            },
        }
        ]
    }
}

myChart.setOption(SettingOption(1,1))


$('#search').click(() => {
    let startVal = $('#startDate').val().split('-')
    let endVal = $('#endDate').val().split('-')
    // console.log(startVal[0])
    if(startVal[0]>endVal[0])
        alert("结束时间不能再开始时间之前！")
    if(startVal[0]===endVal[0])
        if(startVal[1]>endVal[1])
            alert("结束时间不能再开始时间之前！")
        else
        if(startVal[1]===endVal[1])
            if(startVal[2]>endVal[2])
                alert("结束时间不能再开始时间之前！")
    var pro=$("#province").val();
    var city=$("#city").val();
    var country=$("#country").val();
    var address=$("#address").val();
    let data = {
        "year1":`${startVal[0]}`,
        "month1":`${startVal[1]}`,
        "day1":`${startVal[2]}`,
        "year2":`${endVal[0]}`,
        "month2":`${endVal[1]}`,
        "day2":`${endVal[2]}`,
        "address":pro+","+city+","+country+","+address,
    }

    console.log(data)
    $.ajax({
        url: `${BASE_URL}` + '/selecDataNumber',
        method: 'post',
        contentType: "application/json",
        success: function (res) {
            console.log(res)
            for (let i = 0; i < res[0].list.length; i++) {
                time.push(res[0].list[i].time);
            }
            for (let i = 0; i < res[0].list.length; i++) {
                people.push(res[0].list[i].num);
            }
            let option = SettingOption(time,people)
            myChart.setOption(option)
        }
    })

})

//点击查看烟感数据
$('#search-smoke').click(() => {
    window.location = 'smoke.html'
})

//点击活跃度范围设置弹窗
// $("#revise").click(function(){
//     $(".dialog").show(100)
// })

$("#keep").click(function(){
    $('.dialog').hide();
})


//设置范围值
function  fanwei() {

    let red=document.getElementById("name1").value;
    let green=document.getElementById("name").value;
    $.ajax({
        url: `${BASE_URL}`+"/insertColorValue",
        method:'post',
        data:'{"red":"'+red+'","green":"'+green+'"}',
        contentType:"application/json",
        success: (res) => {
            // console.log("ssssssssssokla")
            // alert("ok")
            document.getElementById('state').innerHTML = "保存成功！";
            // $('#state').hidden
        }
    })

    console.log('{"red":"'+red+'","green":"'+green+'"}');
}

function a(c){
$(document).on("click", "#redbt"+c, function(){
    // 这里的this指向触发点击事件的p元素(Element)
    time=[];
    for (let i = 0; i < resd[c].list.length; i++) {
        time.push(resd[c].list[i].time);
    }
    people=[]
    for (let i = 0; i < resd[c].list.length; i++) {
        people.push(resd [c].list[i].num);
    }
    let option = SettingOption(time,people)
    myChart.setOption(option)
});}
