// const BASE_URL = 'http://121.199.21.197:63391'
$(document).ready(() => {
    $('#g_res').click(() => {
        let username = $('#user_name1').val()
        let password = $('#user_pwd1').val()
        var pro = $("#province").val();
        var city = $("#city").val();
        var country = $("#country").val();
        var address = $("#address").val();
        let addressall = pro + "," + city + "," + country + "," + address;
        console.log(username+password)
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
                    window.location.href = "Login.html";
                } else alert("注册失败！")
            }
        })
    })
})
$('#p_res').click(() => {
    let username = $('#user_name2').val()
    let password = $('#user_pwd2').val()
    var pro = $("#province1").val();
    var city = $("#city1").val();
    var country = $("#country1").val();
    var address = $("#address1").val();
    let addressall = pro + "," + city + "," + country + "," + address;
    console.log(username+password)
    console.log(addressall)
    // let address = "北京市，朝阳区，来广营街道"
    $.ajax({
        url: `${BASE_URL}` + '/p_register',
        method: 'post',
        data: '{"username":"' + username + '","password":"' + password + '","address":"' + addressall + '"}',
        contentType: "application/json",
        success: (res) => {
            console.log(res)
            if (res==='"T"') {
                alert("注册成功！")
                window.location.href = "Login.html";
            } else alert("注册失败！")
        }
    })
})
