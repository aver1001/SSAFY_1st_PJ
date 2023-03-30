window.onload = setReset();


async function setReset() {
	const id = $.cookie("userId");
	let data = { sign: "info" }
	let response = await fetch('main', { method: 'post', body: JSON.stringify(data) });
	data = await response.json();
	
	if (data.error || !id) {
		alert(data.error);
		$.removeCookie("userId", {path:'/'});
		window.location.href = "login.html";
	}
	data = await JSON.parse(data.userInfo);
	document.getElementById("name").value = data.name.toString();
	document.getElementById("id").value = data.id.toString();
	document.getElementById("pw").value = data.pw.toString();
	document.getElementById("pwc").value = data.pw.toString();
	document.getElementById("email").value = data.email.toString();
	document.getElementById("loc").value = data.loc.toString();
}



async function editInfo() {
	
	let name = document.getElementById("name").value;
	let id = document.getElementById("id").value;
	let pw = document.getElementById("pw").value;
	let pwc = document.getElementById("pwc").value;
	let email = document.getElementById("email").value;
	let loc = document.getElementById("loc").value;

	if (name === "") {
		alert("이름을 채워주세요");
		return;
	} else if (id === "") {
		alert("아이디를 채워주세요");
		return;
	} else if (pw === "") {
		alert("비밀번호를 채워주세요");
		return;
	} else if (pwc === "") {
		alert("비밀번호확인을 채워주세요");
		return;
	} else if (email === "") {
		alert("이메일을 채워주세요");
		return;
	} else if (pwc === "") {
		alert("지역을 채워주세요");
		return;
	} else if (pw != pwc) {
		alert("비밀번호와 확인 비밀번호가 서로 다릅니다.");
		return;
	}
	
	let data = { sign: "updateInfo", name, id, pw, email, loc };
	const response = await fetch('main', { method: 'post', body: JSON.stringify(data) });
	data = await response.json();
	
	if (data.ok) {
		alert(name + "님 정보 수정이 완료되었습니다");
	} else {
		alert(data.error);
	}
	
	
}

function pwCheckText() {
	if (document.getElementById("pw").value != document.getElementById("pwc").value) {
		document.getElementById("pwCheckText").style.display = "";
	} else {
		document.getElementById("pwCheckText").style.display = "none";
	}
}

// 탈퇴하기 수행
async function deleteUser() {
	const result = confirm("정말 탈퇴하시겠습니까?");
	
	if (result) {
		let id = document.getElementById("id").value;
	let data = { sign: "deleteUser", id }
	let response = await fetch('main', { method: 'post', body: JSON.stringify(data) });
	data = await response.json();

	console.log(data);

	alert(document.getElementById("name").value + "님 탈퇴가 완료되었습니다.");

	// 로그인 쿠키 삭제 && logout
	$.removeCookie("userId", {path:'/'});

	// 메인 화면으로 이동
	window.location.href = "index.html";
	}
	
}
