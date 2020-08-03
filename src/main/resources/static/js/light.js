const BASE_URL = 'http://121.199.21.197:63391'

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
				   document.getElementById('address1').innerHTML = res.userAddress;
					var arry=[];
					arry=res.list;
				   // getaddress(res,"light-address");
					// getnum(res,"num");
					// getcolor(res,"img-light");
					for(var i=0;i<res.list.length;i++) {
						var container = document.createElement("div");
					container.className = "container1";
					container.style.cssFloat="left"
					var light_container = document.createElement("div");
					light_container.className = "light-container";
					var light_address = document.createElement("div");
					light_address.className = "light-address";
					light_address.innerHTML = arry[i].address;
					// light_address.innerHTML=arry[i].address;
					light_container.appendChild(light_address)
					var img_light = document.createElement("img");//根据不同的数据的颜色
					if(arry[i].number<=res.color.green)
						img_light.src = "../../static/assets/img/green.png";
					else
					   if(arry[i].number<=res.color.red)
						   img_light.src = "../../static/assets/img/yellow.png";
					   else
						   img_light.src = "../../static/assets/img/red.png";
						img_light.className = "img-light";
					light_container.appendChild(img_light);
					var level = document.createElement("div");
					level.className = "level1"
					var levelnum = document.createElement("span");
						levelnum.className="span1"
						if(arry[i].number<=res.color.green)
							levelnum.innerHTML = "活跃度：" +"低"+ "<br/>";
						else
						if(arry[i].number<=res.color.red)
							levelnum.innerHTML = "活跃度：" +"中"+ "<br/>";
						else
							levelnum.innerHTML = "活跃度：" +"高"+ "<br/>";
					level.appendChild(levelnum)
					var number = document.createElement("div");
					number.className = "number";
					var numberm = document.createElement("span");
						numberm.className="span1"
						numberm.innerHTML = "总人数：" + arry[i].number;
					// // numberm.innerHTML=arry[i].number;
					number.appendChild(numberm)
					var time = document.createElement("div");
					time.className = "time"
					var timeg = document.createElement("span");
						timeg.className="span1"
						timeg.innerHTML = "截至目前为止";
					time.appendChild(timeg)
					light_container.appendChild(level);
					light_container.appendChild(number);
					light_container.appendChild(time);
					container.appendChild(light_container);
					document.getElementById('ssx').appendChild(container);

				}}
			})
		})
function getaddress(res,aclass) {
	var arry=[];
	arry=res.list;
	var add_all=document.getElementsByClassName(aclass);
	for (var i=0;i<add_all.length;i++) {
		add_all[i].innerText=arry[i].address;
	}
}

function getnum(res,aclass) {
	var arry=[];
	arry=res.number;
	var add_all=document.getElementsByClassName(aclass);
	for (var i=0;i<add_all.length;i++) {
		add_all[i].innerText=arry[i].number;
	}
}

function getcolor(res,aclass) {
	var arry=[];
	arry=res.color;
	var add_all=document.getElementsByClassName(aclass);
	for (var i=0;i<add_all.length;i++) {
		if(arry[i].color.toString()=="red"){
			add_all[i].src="../../assets/img/red.png";
		}
		if(arry[i].color.toString()=="green"){
			add_all[i].src="../../assets/img/green.png";
		}
		if(arry[i].color.toString()=="yellow"){
			add_all[i].src="../../assets/img/yellow.png";
		}


	}


//showid（）是用来测试的，在light.html的body里调用了
// function showid() {
// 	var arry=[];
// 	arry=list;
// 	var add_all=document.getElementsByClassName("num");
// 	for (var i=0;i<add_all.length;i++) {
//
// 		// add_all.push(arry[i].uname);
// 	add_all[i].innerText=list[i].number;
// 		console.log(list)
// 		console.log(add_all);
// 	}

	// 图片测试
	// var arry=[];
	// arry=list;
	// var add_all=document.getElementsByClassName("img-light");
	// for (var i=0;i<add_all.length;i++) {
	// 	if(arry[i].color.toString()=="red"){
	// 		add_all[i].src="../../assets/img/red.png"
	// 		console.log(1)
	// 	}
	// 	if(arry[i].color.toString()=="green"){
	// 		add_all[i].src="../../assets/img/green.png";
	// 	}
	// 	if(arry[i].color.toString()=="yellow"){
	// 		add_all[i].src="../../assets/img/yellow.png";
	// 	}
	// }
}
var list = [
	{
		address: "森中古城☼",
		number:'5',
		color:"red",
	},
	{
		address: "森中古城☼",
		number:'10',
		color:"yellow",
	},
	{
		address: "森中古城☼",
		number:'5',
		color:"red",
			},
	{
		address: "森中古城☼",
		number:'5',
		color:"green",

	},]
