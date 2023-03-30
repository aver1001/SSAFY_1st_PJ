window.onload = function() {
	const id = $.cookie("userId");
	if(id){	// 로그인이 되어 있을 때
	    document.getElementById("header").innerHTML +=
	    '<li><a href="login.html" onClick = "logout()" >LogOut</a></li><li><a href="user_info.html">Info</a></li>';
	} else {
		document.getElementById("header").innerHTML +=
	    '<li><a href="login.html">Login</a></li><li><a href="signup.html">SingUp</a></li>';
	}
}


async function login() {
	const id = document.getElementById("id").value;
	const pw = document.getElementById("pw").value;
	let data = {sign:"login", id, pw};
	
	console.log(data);
	const response = await fetch('main', {method:'post', body:JSON.stringify(data)});
	data = await response.json();
	console.log(data);
	
	if(data.ok) {
		$.cookie('userId', id, {path:'/'});
		window.location.href = "index.html"; 
	} else {
		alert(data.error);
	}
}

async function logout() {
	let data = {sign:"logout"};
	
	await fetch('main', {method:'post', body:JSON.stringify(data)});
	
	$.removeCookie("userId", {path:'/'});
	alert("로그아웃 되었습니다.");
	window.location.href = "index.html";
}
