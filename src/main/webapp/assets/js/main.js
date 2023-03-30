function findLoc() {
  window.scrollTo(0, 800);
}

function makeInfo() {
  var Name = document.querySelector("#inputName").value; // 이름
  var ID = document.querySelector("#inputId").value; // 아이디
  var Email = document.querySelector("#inputEmail").value; // 이메일
  var Password = document.querySelector("#inputPassword").value; // 비밀번호
  var Address = document.querySelector("#inputAddress").value; // 주소
  var PhoneNumber = document.querySelector("#inputNumber").value; // 전화번호

  console.log("---회원정보---");
  console.log("이름 : " + Name);
  console.log("아이디 : " + ID);
  console.log("이메일 : " + Email);
  console.log("비밀번호 : " + Password);
  console.log("주소 : " + Address);
  console.log("전화번호 : " + PhoneNumber);

  if (localStorage.getItem(ID) != null) {
    alert("아이디 중복!");
    return;
  }

  if (!Name || !ID || !Email || !Password || !Address || !PhoneNumber) {
    alert("모든 내용을 입력하세요!");
    return;
  }

  // 입력 data를 이용하여 JSON객체 생성.
  var user_info = {
    User_name: Name,
    User_ID: ID,
    User_Email: Email,
    User_PW: Password,
    User_Address: Address,
    User_PhoneNum: PhoneNumber,
  };

  var user_json = JSON.stringify(user_info); // JSON객체를 문자열 변환.

  localStorage.setItem(ID, user_json); // localStorage에 넣기.

  alert("회원가입 되었습니다.");
  window.location.href = "./index.html";
}

function searchID() {
  var Name = document.querySelector("#inputName").value; // 이름
  var Email = document.querySelector("#inputEmail").value;
  // var PhoneNumber = document.querySelector("#inputNumber").value;

  if (!Name || !Email) {
    alert("모든 내용을 입력하세요!");
    return;
  }

  console.log("Name:" + Name + ", Email: " + Email);

  // ----- 아이디찾기 들어가야 함 -----

  // var user_info = localStorage.getItem(ID);
  // var info = JSON.parse(user_info);

  // if (info.User_name == Name && info.User_Email == Email) {
  //   alert("당신의 아이디는 " + info.User_PW + "입니다.");
  // }

  // window.location.href = "./index.html";
}

function searchPW() {
  var Name = document.querySelector("#inputName").value; // 이름
  var ID = document.querySelector("#inputId").value; // 아이디
  var Email = document.querySelector("#inputEmail").value;
  // var PhoneNumber = document.querySelector("#inputNumber").value;

  if (!Name || !ID || !Email) {
    alert("모든 내용을 입력하세요!");
    return;
  }

  console.log("Name:" + Name + ", ID: " + ID + ", Email: " + Email);

  // ----- 비밀번호찾기 들어가야 함 -----

  // var user_info = localStorage.getItem(ID);
  // var info = JSON.parse(user_info);

  // if (info.User_name == Name && info.User_Email == Email) {
  //   alert("당신의 비밀번호는 " + info.User_PW + "입니다.");
  // }

  // window.location.href = "./index.html";
}
