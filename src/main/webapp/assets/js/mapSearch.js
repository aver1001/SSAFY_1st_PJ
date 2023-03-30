fetch("main", { method: "post", body:JSON.stringify({sign:"searchOption"})})
  .then((response) => response.json())
  .then((data) => makeOption(data));

function makeOption(data) {
	data =  JSON.parse(data.sidoInfo)
  // console.log(areas);
  let sel = document.getElementById("search-area");
 	 data.forEach((area) => {
    let opt = document.createElement("option");
    opt.setAttribute("value", area.sido_code);
    opt.appendChild(document.createTextNode(area.sido_name));

    sel.appendChild(opt);
  });
}

async function searchMap(){
	let areaCode = document.getElementById("search-area").value;
 	let contentTypeId = document.getElementById("search-content-id").value;
 	let keyword = document.getElementById("search-keyword").value;
 	
	let data = {sign:"searchLoc",areaCode,contentTypeId,keyword}
	let response = await fetch("main",{method:"post",body:JSON.stringify(data)})
	data = await response.json()
	data = await JSON.parse(data.locations)
	makeList(data)
	
}

function makeList(data) {
  document.querySelector("table").setAttribute("style", "display: ;");
  if (data.length == 0){
	  document.getElementById("trip-list").innerHTML =
	  `해당되는 장소가 없습니다.`
	  
	  return
  }
  let tripList = ``;
  positions = [];
  data.forEach((area) => {
    tripList += `
     <tr onclick="moveCenter(${area.mapy}, ${area.mapx});">
       <td><img src="${area.firstimage}" width="100px"></td>
       <td>${area.title}</td>
       <td>${area.addr1} ${area.addr2}</td>
       <td>${area.mapy}</td>
       <td>${area.mapx}</td>
     </tr>
   `;

    let markerInfo = {
      title: area.title,
      latlng: new kakao.maps.LatLng(area.mapy, area.mapx),
    };
    positions.push(markerInfo);
  });
  document.getElementById("trip-list").innerHTML = tripList;
  displayMarker();
}


// 카카오지도
var mapContainer = document.getElementById("map"), // 지도를 표시할 div
  mapOption = {
    center: new kakao.maps.LatLng(37.500613, 127.036431), // 지도의 중심좌표
    level: 5, // 지도의 확대 레벨
  };

// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new kakao.maps.Map(mapContainer, mapOption);

let markerList = [];

function displayMarker() {
  // 마커 이미지의 이미지 주소입니다
  var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

  for (let i = 0; i < markerList.length; i++) {
    markerList[i].setMap(null);
  }

  marketList = [];

  for (var i = 0; i < positions.length; i++) {
    // 마커 이미지의 이미지 크기 입니다
    var imageSize = new kakao.maps.Size(24, 35);

    // 마커 이미지를 생성합니다
    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

    // 마커를 생성합니다
    var marker = new kakao.maps.Marker({
      map: map, // 마커를 표시할 지도
      position: positions[i].latlng, // 마커를 표시할 위치
      title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
      image: markerImage, // 마커 이미지
    });
    markerList.push(marker);
  }

  // 첫번째 검색 정보를 이용하여 지도 중심을 이동 시킵니다
  map.setCenter(positions[0].latlng);
}

function moveCenter(lat, lng) {
  map.setCenter(new kakao.maps.LatLng(lat, lng));
}
