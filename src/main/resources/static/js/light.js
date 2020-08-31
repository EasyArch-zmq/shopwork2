const BASE_URL = 'http://121.199.21.197:63393'
var resd=[];
var arry1=[],arry=[];
	$(document).ready(() => {
			// let username = window.location.href.split('?')[1].split('=')[1]
			// let username = "makesi"
		let username =window.localStorage.getItem('p_username')
		document.getElementById('user').innerText=username;
		console.log(username)
			$.ajax({
				url:`${BASE_URL}` +"/selectAllNumber",
				method:'post',
				data:'{"username":"'+username+'"}',
				contentType:"application/json",
				success: (res) => {
					console.log(res)
					resd=res;
				   document.getElementById('address1').innerHTML = res.userAddress;
				   // document.getElementById('img1').src = res.piture_url;
					arry1=res.list_inAll;
					document.getElementById('address2').innerHTML=res.userAddress;
					var sel=document.getElementById('list');
					for(var i=0;i<arry1.length;i++) {
						sel.options.add(new Option(arry1[i].construction,arry1[i].construction));}
					boxloading(0);
				}
			})
		})
function boxloading(c) {
	$('#ssx').empty();
	arry=resd.list_inAll[c].list_inCons;
	for(let i=0;i<arry.length;i++) {
		var container = document.createElement("div");
		container.className = "container1";
		container.style.cssFloat = "left";
		container.id = arry[i].location;
		var light_container = document.createElement("div");
		light_container.className = "light-container";
		var light_address = document.createElement("div");
		light_address.className = "light-address";
		light_address.innerHTML = arry[i].location;
		// light_address.innerHTML=arry[i].address;
		light_container.appendChild(light_address)
		var img_light = document.createElement("img");//根据不同的数据的颜色
		if (arry[i].number <= arry[i].color.green)
			img_light.src = "../static/assets/img/green.png";
		else if (arry[i].number <= arry[i].color.red)
			img_light.src = "../static/assets/img/yellow.png";
		else
			img_light.src = "../static/assets/img/red.png";
		img_light.className = "img-light";
		light_container.appendChild(img_light);
		var level = document.createElement("div");
		level.className = "level1"
		var levelnum = document.createElement("span");
		levelnum.className = "span1"
		if (arry[i].number <= arry[i].color.green)
			levelnum.innerHTML = "活跃度：" + "低" + "<br/>";
		else if (arry[i].number <= arry[i].color.red)
			levelnum.innerHTML = "活跃度：" + "中" + "<br/>";
		else
			levelnum.innerHTML = "活跃度：" + "高" + "<br/>";
		level.appendChild(levelnum)
		// var number = document.createElement("div");
		// number.className = "number";
		// var numberm = document.createElement("span");
		// numberm.className = "span1"
		// numberm.innerHTML = "总人数：" + arry[i].number;
		// // // numberm.innerHTML=arry[i].number;
		// number.appendChild(numberm)
		// var rtier = document.createElement("div");
		// rtier.className = "number";
		// var tier = document.createElement("span");
		// tier.className = "span1"
		// tier.innerHTML = "" +
		// 	"盒子层级：" + arry[i].tier;
		// // // numberm.innerHTML=arry[i].number;
		// rtier.appendChild(tier)
		// var time = document.createElement("div");
		// time.className = "time"
		// var timeg = document.createElement("span");
		// timeg.className = "span1"
		// timeg.innerHTML = "截至目前为止";
		// time.appendChild(timeg)
		light_container.appendChild(level);
		// light_container.appendChild(number);
		// light_container.appendChild(rtier);
		// light_container.appendChild(time);
		container.appendChild(light_container);
		document.getElementById('ssx').appendChild(container);

	}
}
// var list = [
// 	{
// 		address: "森中古城☼",
// 		number:'5',
// 		color:"red",
// 	},
// 	{
// 		address: "森中古城☼",
// 		number:'10',
// 		color:"yellow",
// 	},
// 	{
// 		address: "森中古城☼",
// 		number:'5',
// 		color:"red",
// 			},
// 	{
// 		address: "森中古城☼",
// 		number:'5',
// 		color:"green",
//
// 	},]
//下拉选择建筑
$('#list').change(function () {
	var myselect=$('#list').val();
	for (let i=0;i<resd.list_inAll.length;i++)
	{
		if(myselect===resd.list_inAll[i].construction)
		{boxloading(i);
		break;}
		else
		{	$('#ssx').empty();
			continue;}

	}

})