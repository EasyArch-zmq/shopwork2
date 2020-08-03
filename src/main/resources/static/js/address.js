// const BASE_URL = 'http://121.199.21.197:63391'
$(document).ready(() => {

    $.ajax({
        url: `${BASE_URL}` + '/ProvinceAddress',
        method: 'post',
        contentType: "application/json",
        success: function (res) {
            console.log(res[0])
            console.log(res.length)
            let pro, options;
            for (let i = 0; i < res.length; i++) {
                pro = $("#province");
                options += '<option value="' + res[i] + '" >' + res[i] + '</option>';
            }
            pro.append(options);
        }
    })
    $('#province').change(function () {
        var opt = $("#province").val();
        console.log(opt)
        // var d=[{Province:opt}]
        $.ajax({
            url: `${BASE_URL}` + '/CityAddress',
            // data: d,
            data: '{ "province": "' + opt + '"}',
            method: 'post',
            contentType: "application/json",
            success: function (res) {
                console.log(res);
                let pro, options;
                for (let i = 0; i < res.length; i++) {
                    pro = $("#city");
                    options += '<option value="' + res[i] + '" >' + res[i] + '</option>';
                }
                pro.append(options);
            }
        })
    });
    $("#city").change(function () {
        var city = $("#city").val();
        var pro = $("#province").val();
        $.ajax({
            url: `${BASE_URL}` + '/CountyAddress',
            data: '{"province":"' + pro + '","city":"' + city + '"}',
            method: 'post',
            contentType: "application/json",
            success: function (res) {
                console.log(res)
                let pro, options;
                for (let i = 0; i < res.length; i++) {
                    pro = $("#country");
                    options += '<option value="' + res[i] + '" >' + res[i] + '</option>';
                }
                pro.append(options);
            }
        })
    });
    $("#country").change(function () {
        var city = $("#city").val();
        var pro = $("#province").val();
        var coun = $("#country").val();
        $.ajax({
            url: `${BASE_URL}` + '/SpecificAddress',
            data: '{"province":"' + pro + '","city":"' + city + '","county":"' + coun + '"}',
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

    $.ajax({
        url: `${BASE_URL}` + '/ProvinceAddress',
        method: 'post',
        contentType: "application/json",
        success: function (res) {
            console.log(res[0])
            console.log(res.length)
            let pro, options;
            for (let i = 0; i < res.length; i++) {
                pro = $("#province1");
                options += '<option value="' + res[i] + '" >' + res[i] + '</option>';
            }
            pro.append(options);
        }
    })
    $('#province1').change(function () {
        var opt = $("#province1").val();
        console.log(opt)
        // var d=[{Province:opt}]
        $.ajax({
            url: `${BASE_URL}` + '/CityAddress',
            // data: d,
            data: '{ "province": "' + opt + '"}',
            method: 'post',
            contentType: "application/json",
            success: function (res) {
                console.log(res);
                let pro, options;
                for (let i = 0; i < res.length; i++) {
                    pro = $("#city1");
                    options += '<option value="' + res[i] + '" >' + res[i] + '</option>';
                }
                pro.append(options);
            }
        })
    });
    $("#city1").change(function () {
        var city = $("#city1").val();
        var pro = $("#province1").val();
        $.ajax({
            url: `${BASE_URL}` + '/CountyAddress',
            data: '{"province":"' + pro + '","city":"' + city + '"}',
            method: 'post',
            contentType: "application/json",
            success: function (res) {
                console.log(res)
                let pro, options;
                for (let i = 0; i < res.length; i++) {
                    pro = $("#country1");
                    options += '<option value="' + res[i] + '" >' + res[i] + '</option>';
                }
                pro.append(options);
            }
        })
    });
    $("#country1").change(function () {
        var city = $("#city1").val();
        var pro = $("#province1").val();
        var coun = $("#country1").val();
        $.ajax({
            url: `${BASE_URL}` + '/SpecificAddress',
            data: '{"province":"' + pro + '","city":"' + city + '","county":"' + coun + '"}',
            method: 'post',
            contentType: "application/json",
            success: function (res) {
                console.log(res)
                $("#address1").autocomplete({
                    source: res,
                    messages: {
                        // noResults:"按上下键进行选择",
                    }
                });
            }
        })
    });
})