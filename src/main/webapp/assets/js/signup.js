let usage = false;
async function idCheck() {
	const id = document.getElementById("id").value;
	if (id==="") {
		alert("아이디를 채워주세요");
		return;
	}
	let data = { sign:"idCheck", id};
	const response = await fetch('main', {method:'post', body:JSON.stringify(data)});
	data = await response.json();
	console.log(data);
	
	if(data.ok) {
		alert("사용가능한 아이디입니다.");
		document.getElementById("idCheck").disabled = true;
		usage = true;
	} else {
		alert(data.error);
	}
}

function idChange() {
	document.getElementById("idCheck").disabled = false;
	usage = false;
}

async function signup() {
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
	} else if (!usage) {
		alert("아이디 중복확인을 해주세요");
		return;
	}

	//회원가입 로직 진행
	let data = { sign:"memberInsert", name, id, pw, email, loc };
	console.log(data);
	const response = await fetch('main', {method:'post', body:JSON.stringify(data)});
	data = await response.json();
	console.log(data);
	
	if(data.ok) {
		alert(name + "님 회원가입 축하드립니다.");
		window.location.href = "login.html";
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

async function emailTest(){
	 let regex = await new RegExp("([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\"\(\[\]!#-[^-~ \t]|(\\[\t -~]))+\")@([!#-'*+/-9=?A-Z^-~-]+(\.[!#-'*+/-9=?A-Z^-~-]+)*|\[[\t -Z^-~]*])");
	 if(regex.test(document.getElementById("email").value)){
		 document.getElementById("emailCheckText").style.display = "";
		 
	 } else {
		 document.getElementById("emailCheckText").style.display = "none";
	 }
}
