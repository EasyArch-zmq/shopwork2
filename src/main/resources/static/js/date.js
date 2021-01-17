    numtitle = "xx区风险防控图";
    var val = '4';
    var myDate = new Date(), Y = myDate.getFullYear(), M = myDate.getMonth() + 1, D = myDate.getDate();
    $('#NianYue').hide();
    $('#ri').hide();
    $('#shi').show();
    $('#NianYue1').hide();
    $('#ri1').hide();
    $('#shi1').show();
    $('#continuation1').hide();
    for (var i = myDate.getFullYear() + 10; i > myDate.getFullYear() - 10; i--) {
        document.getElementById('year1').options.add(new Option(i, i))
        document.getElementById('year2').options.add(new Option(i, i))
        document.getElementById('year11').options.add(new Option(i, i))
        document.getElementById('year21').options.add(new Option(i, i))
    }
    for (var i = 1; i <= 12; i++) {
        document.getElementById('month1').options.add(new Option(i, i))
        document.getElementById('month2').options.add(new Option(i, i))
        document.getElementById('month11').options.add(new Option(i, i))
        document.getElementById('month21').options.add(new Option(i, i))
    }

    if((M + '').length == 1){
        M = '0' + (M + '');
    }
//处理日是一位的情况
    if((D + '').length == 1){
        D = '0' + (D + '')
    }
    var curDay = Y + '-' + M + '-' + D;
    console.log(curDay)
    $('.calt').val(curDay + 'T12:00:00')
    $('.cal').val(curDay)

    let sub=0;

$('#ways').change(function () {
    val=$('#ways').val();
    if(val==='1') {$('#NianYue').show();$('#year1').show();$('#year2').show();$('#month1').hide();$('#month2').hide();$('#heng').show();$('#ri').hide();$('#shi').hide();}
    if(val==='2') {$('#NianYue').show();$('#year1').show();$('#year2').show();$('#month1').show();$('#month2').show();$('#heng').show();$('#ri').hide();$('#shi').hide();}
    if(val==='3') {$('#NianYue').hide();$('#ri').show();$('#shi').hide();}
    if(val==='4') {$('#NianYue').hide();$('#ri').hide();$('#shi').show();}
}),
    $('#ways1').change(function () {
        val=$('#ways1').val();
        if(val==='1') {$('#NianYue1').show();$('#year1').show();$('#year21').show();$('#month11').hide();$('#month21').hide();$('#heng1').show();$('#ri1').hide();$('#shi1').hide();}
        if(val==='2') {$('#NianYue1').show();$('#year11').show();$('#year21').show();$('#month11').show();$('#month21').show();$('#heng1').show();$('#ri1').hide();$('#shi1').hide();}
        if(val==='3') {$('#NianYue1').hide();$('#ri1').show();$('#shi1').hide();}
        if(val==='4') {$('#NianYue1').hide();$('#ri1').hide();$('#shi1').show();}
    })

$('#fixed').click(function () {
    $('#continuation1').hide();
    $('#fixed1').show();
    $('#continuation').css("color","#000")
    $('#fixed').css("color","#0FB9EF")
    sub=0;
})
$('#continuation').click(function () {
    $('#continuation1').show();
    $('#fixed1').hide();
    $('#continuation').css("color","#0FB9EF")
    $('#fixed').css("color","#000")
    sub=1;

})
$('#search').click(() => {
    var year1=null,year2=null, month1=null,month2=null, day1=null,day2=null, time1="00:00:00",time2="00:00:00";
    if (sub === 0) {
        if (val === '1') {
            year1 = $('#year1').val();
            year2 = $('#year2').val();
        }
        if (val === '2') {
            year1 = $('#year1').val();
            year2 = $('#year2').val();
            month1 = $('#month1').val();
            month2 = $('#month2').val();
        }
        if (val === '3') {
            // console.log()
            var startdate = $('#startDate').val().split('-');
            var enddate = $('#endDate').val().split('-');
            year1 = startdate[0];
            year2 = enddate[0];
            month1 = startdate[1];
            month2 = enddate[1];
            day1 = startdate[2];
            day2 = enddate[2];
        }
        if (val === '4') {
            var startdate = $('#starttimeDate').val().split('-');
            var enddate = $('#endtimeDate').val().split('-');
            year1 = startdate[0];
            year2 = enddate[0];
            month1 = startdate[1];
            month2 = enddate[1];
            day1 = startdate[2].split('T')[0];
            day2 = enddate[2].split('T')[0];
            var a= $('#starttimeDate').val().split('T');time1=a[1];
            var a1= $('#endtimeDate').val().split('T');time2=a1[1];

        }
    }
    else {
        if (val === '1') {
            year1 = $('#year11').val();
            year2 = $('#year21').val();
        }
        if (val === '2') {
            year1 = $('#year11').val();
            year2 = $('#year21').val();
            month1 = $('#month11').val();
            month2 = $('#month21').val();
        }
        if (val === '3') {
            var startdate = $('#startDate1').val().split('-');
            var enddate = $('#endDate1').val().split('-');
            year1 = startdate[0];
            year2 = enddate[0];
            month1 = startdate[1];
            month2 = enddate[1];
            day1 = startdate[2];
            day2 = enddate[2];
        }
        if (val === '4') {
            var startdate = $('#starttimeDate1').val().split('-');
            var enddate = $('#endtimeDate1').val().split('-');
            year1 = startdate[0];
            year2 = enddate[0];
            month1 = startdate[1];
            month2 = enddate[1];
            day1 = startdate[2].split('T')[0];
            day2 = enddate[2].split('T')[0];
            var a = $('#starttimeDate1').val().split('T');time1 = a[1]
            var a1 = $('#endtimeDate1').val().split('T');time2=a1[1]
        }
    }
    if(time1.length!==8)
        time1=time1+":00"
    if(time2.length!==8)
        time2=time2+":00"
    if(year1!==null)
        if(year2===null){alert("请选择结束年份")}
        else
        if(month1!==null) if(month2===null){alert("请选择结束月份")}
        else
        if(day1!==null) if(day2===null){alert("请选择结束日")}
    if(year1>year2) alert("结束时间不能再开始时间之前！")
    else
    if(year1===year2)
        if(month1>month2)
            alert("结束时间不能再开始时间之前！")
        else
        if(month1===month2)
            if(day1>day2)
                alert("结束时间不能再开始时间之前！")
    var city = $("#city").val();
    var county = $("#country").val();
    var town = $("#town").val();
    var street = $("#street").val();
    var special_address = $("#special_address").val() === "" ? "null" : $("#special_address").val();
    var box = $('#box').val();
    let data = JSON.stringify({
        "year1": year1,
        "month1": month1,
        "day1": day1,
        "year2": year2,
        "month2": month2,
        "day2": day2,
        "time1": time1,
        "time2": time2,
        "address": city + "," + county + "," + street + "," + special_address,
        "mac_address":box
    })
    // document.getElementById('time').innerHTML="搜索的时间为："+year1+"/"+month1+"/"+day1+" "+time1+"——"+
    //     year2+"/"+month2+"/"+day2+" "+time2;
    console.log(data)
    let url;
    // // alert(box)
    if(sub===0){
        url= `${BASE_URL}` + '/selectDateNumber_2';
    }else{url= `${BASE_URL}` + '/selectDateNumber_1';}
    // alert(url)
    console.log(data);
    $.ajax({
        url: url,
        method: 'post',
        data:data,
        contentType:"application/json;charset=utf-8",
        beforeSend: function(request) {
            request.setRequestHeader("token",window.localStorage.getItem("token") );
        },
        success: function (res) {
            console.log(res.length)
            if(box==="null"){
                numtitle=res.address;
            }
            else{
                numtitle=res.mac_address;
            }
            time=[];
            people=[];
            for (let i  = 0; i < res.list.length; i++) {
                time.push(res.list[i].time);
            }
            console.log(time)
            for (let i = 0; i <res.list.length; i++) {
                people.push(res.list[i].num);
            }
            let option = SettingOption(time,people)
            myChart.setOption(option)
        }
    })

})

