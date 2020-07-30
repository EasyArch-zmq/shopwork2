const BASE_URL = 'http://121.199.21.197:63391'

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
                messages: {
                    // noResults:"按上下键进行选择",
                }
            });
        }
    })
});

$(document).ready(() => {
    $('#greg').click(() => {
        $('.signin_btn').click(() => {
            let username = $('#g_username').val()
            let password = $('#g_password').val()
            var pro = $("#province").val();
            var city = $("#city").val();
            var country = $("#country").val();
            var address = $("#address").val();
            let addressall = pro + "," + city + "," + country + "," + address;
            console.log(addressall)
            // let address = "北京市，朝阳区，来广营街道"
            $.ajax({
                url: `${BASE_URL}` + '/g_register',
                method: 'post',
                data: '{"username":"' + username + '","password":"' + password + '","address":"' + addressall + '"}',
                contentType: "application/json",
                success: (res) => {
                    console.log(res)
                    if (res==='"T"') {
                        alert("注册成功！")
                        window.location.href = "g_Login.html";
                    } else alert("注册失败！")
                }
            })
        })
    })
})