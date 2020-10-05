const BASE_URL = 'http://121.199.21.197:63394'
$(document).ready(() => {
    $('#g_Login').click(() => {
        let username = $('#user_name').val()
        let password = $('#user_pwd').val()
        console.log(username+password)
        $.ajax({
            url: `${BASE_URL}`+'/g_login',
            data: '{ "username": "'+username+'", "password": "'+password+'"}',
            method: 'post',
            contentType: "application/json",
            success: function (res) {
                console.log(res)
                if(res === 'T'){
                    window.localStorage.setItem('g_username', $('#user_name').val());
                    window.location.href = "RiskControll.html" }
                else{
                    alert("登陆失败，用户名或密码错误！")}
            }
        })
    })
})

$(document).ready(() => {
    $('#p_Login').click(() => {
        let username = $('#user_name1').val()
        let password = $('#user_pwd1').val()
        console.log(username+password)
        $.ajax({
            url: `${BASE_URL}`+'/p_login',
            method: 'post',
            data: '{ "username": "' + username + '", "password": "' + password + '" }',
            contentType: "application/json",
            success: function (res) {
                if (res === 'T') {
                    window.localStorage.setItem('p_username', $('#user_name1').val());
                    window.location.href = "p_main.html"
                } else {
                    alert("登陆失败，用户名或密码错误！")
                }
            }
        })
    })
})
function PwdLogin() {
    var login = document.getElementsByClassName("login_con");
    login[0].classList.remove("hidden");
    login[0].classList.add("show");
    login[1].classList.remove("show");
    login[1].classList.add("hidden");
    var tags = document.getElementsByClassName("top_tag");
    tags[0].classList.add("active");
    tags[1].classList.remove("active");


    // ad.style.backgroundImage='url(https://static.zcool.cn/v1.1.43/passport4.0/images/login-ground.jpg)';
}
function QrcodeLogin() {
    var login = document.getElementsByClassName("login_con");
    login[0].classList.remove("show");
    login[0].classList.add("hidden");
    login[1].classList.remove("hidden");
    login[1].classList.add("show");
    var tags = document.getElementsByClassName("top_tag");
    tags[1].classList.add("active");
    tags[0].classList.remove("active");

}