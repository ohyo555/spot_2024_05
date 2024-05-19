<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="/resource/background.css" />
<%@ include file="../common/head.jspf"%>

<!-- flaticon 불러오기 -->
<link rel='stylesheet'
	href='https://cdn-uicons.flaticon.com/2.1.0/uicons-regular-rounded/css/uicons-regular-rounded.css'>

<style>
.board-container {
	max-width: 600px;
	background-color: white;
	position: relative; /* relative position 설정 */
}

section {
	justify-content: center;
	height:95%;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

tr {
	font-size: 13px; /* tr 안의 글자 크기 조절 */
	cursor: pointer; /* 클릭 가능하도록 포인터 변경 */
}

/* 테이블 헤더,셀의 스타일 */
th, td {
	padding: 10px;
	text-align: center;
	border-bottom: 1px solid #ddd;
}

/* 테이블 헤더 스타일 */
th {
	background-color: #f2f2f2;
}

/* 검색 바 스타일 */
.search-bar {
	max-width: 600px;
	margin: 0px auto;
	display: flex;
	justify-content: center;
	align-items: center;
}

.boardname {
	font-size: 1rem;
	font-weight: 600;
}

.find_btn {
	border: 1.5px solid rgba(102, 100, 100);
	border-radius: 5px;
	font-size: 12px;
	padding: 0 15px;
}
.find_btn:hover {
	color: skyblue;
	border: 1.5px solid skyblue;
	font-weight: 600;
}

.answer {
	max-width: 800px;
	margin: 0px auto;
	display: flex;
	justify-content: center;
	align-items: center;
	font-size: 1rem;
	font-weight: 400;
}


/* 목록 바 스타일 */
.list-bar {
	width: 800px;
	margin: 0px auto;
	display: flex;
	justify-content: space-between;
	align-items: center;
	background-color: white;
	height: 30px;
	background-color: rgb(251, 243, 238);
	border-radius: 10px;
}

.spot {
	width: 80px;
	height: 30px;
	margin: 0px auto;
	display: flex;
	justify-content: space-between;
	align-items: center;
	font-size: 1rem;
	text-align: center;
}

.list {
	margin-top: 40px;
	height: 700px;
}

/* 날씨창 */
.weather-info {
    display: none;
    margin-top: 20px;
    margin-left: 20px;
    padding: 10px;
    border: 1px solid #ddd;
    background-color: #f9f9f9;
    text-align: center;
    font-size: 16px;
    width: 250px;
    height: 200px;
    border-radius: 10px;
}

.temp, .place, .desc, .humidity, .wind{
    font-weight: 600; /* semi-bold */
    font-size: 18px;
    margin-top: 3px;
}

hr {
	color:gray;
}
/* Flexbox 컨테이너 */
.flex-container {
    display: flex;
    justify-content: center;
}

/* JavaScript를 통해 visibility를 조정할 스타일 */
.show {
    display: block;
}

/* 검색 바 스타일 */
.search-bar2 {
	max-width: 600px;
	display: block;
	height: 30px;
	margin-top: 15px;
	margin-left: 10px;
}

input {
margin-left: 5px;
}
</style>

<script>

document.addEventListener('DOMContentLoaded', function () {
    const rows = document.querySelectorAll('table tbody tr');
    const weatherInfo = document.querySelector('.weather-info');

    rows.forEach(row => {
        row.addEventListener('click', function () {
            const city = this.dataset.city;
            getWeatherByCoordinates(city);
            
        });
    });
});

const applyRandomColor = () => {
    const elements = document.querySelectorAll('.temp, .place, .desc,  .humidity, .wind');
    elements.forEach(element => {
        element.style.color = getRandomColor();
    });
}

const getRandomColor = () => {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
        color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
}

</script>

<script>
	
	function dofindanswer() {

 	    var input_keyword = $("#keyword").val();
 	    
 	    console.log(keyword);
 	   
 	    $.ajax({
 	        type: "POST",
 	        url: "/usr/article/dofind", 
 	        data: { keyword: input_keyword },
 	        success: function(data) {

 	        	console.log(data);
 	           	// 답변 정보 업데이트
 	        	$(".answer").empty(); // 기존 내용 지우기
 	            var modifiedData = data.map(function(item) {
 	                return "A: " + item; // 각 데이터 앞에 "A: "를 추가하여 새로운 배열 생성
 	            });
 	            var answerHtml = modifiedData.join("<br>"); // 각 데이터를 <br> 태그로 구분하여 연결
 	            $(".answer").html(answerHtml);
 	        },
 	        error: function(error) {
 	            console.error("Error during registration:", error);
 	        }
 	    });
 	    
 	}
</script>

<section class="text-xl px-4">
	<%-- <input type="hidden" name="id" value="${spot.id }" /> --%>
	
		<div class="search-bar">
				<p class="text-base">Q: </p><input id="keyword" value="" type="text" placeholder="검색어를 입력하세요" class="input input-bordered mr-3"
					style="font-size: 12px; height: 30px;" name="searchKeyword" />
				<button class="find_btn"onclick="dofindanswer();" style="background-color: rgba(255, 255, 255, 0.5)">검색</button>
		</div>
	    <div class="answer"></div>
		<div class="flex-container">
			<div class="board-container">
			<table>
				<colgroup>
					<col style="width: 10%" />
					<col style="width: 20%" />
					<col style="width: 35%" />
					<col style="width: 35%" />
				</colgroup>

				<thead>
					<tr>
						<th>번호</th>
						<th>도시</th>
						<th>관광지</th>
						<th>위치</th>
					</tr>
				</thead>
				<tbody>

					<c:if test="${spots.size() == 0 }">
						<tr>
							<td colspan="7">게시글 없습니다.</td>
						</tr>
					</c:if>

					<c:forEach var="spot" items="${spots }">
						<tr data-city="${spot.e_city}">
							<td>${spot.id }</td>			
							<td>${spot.city }</td>
							<td>${spot.spot_name }</td>
							<td>${spot.location }</td>
						</tr>
					</c:forEach>
				</tbody>
				</table>
			</div>
			<div class="search-bar2">
				<div>
					<input id="keyword2" value="" type="text" placeholder="도시명을 입력하세요" class="input input-bordered"
						style="font-size: 12px; height: 30px;" name="searchKeyword" />
					<button class="find_btn find_btn2" style="background-color: rgba(255, 255, 255, 0.5)">검색</button>
				</div>
				<!-- 	날씨 -->
				<div class="weather-info">
					<div class="font-semibold text-lg">날씨 정보</div>
					<hr>
					<div class="temp"></div>
					<div class="place"></div>
					<div class="desc"></div>
					<div class="humidity"></div>
					<div class="wind"></div>
				</div>
			</div>
			
		</div>
		

</section>

<!-- 날씨 api -->
<script>

const API_KEY = '6e84337a82c70b0fc9f917954828bc29';

const keyword2 = document.getElementById('keyword2').value;
console.log("keyword2: " + keyword2);
const getWeatherByCoordinates = (city) =>{
		const url = 'https://api.openweathermap.org/data/2.5/weather?q='+ city + '&appid='+ API_KEY + '&units=metric&lang=kr';
		console.log(url);
		fetch(url)
		.then(response => response.json())
		.then(data=>{
			console.log(data);
			
			const temperature = data.main.temp;
		    const place = data.name;
		    const description = data.weather[0].description;
		    const humidity = data.main.humidity;
		    const wind = data.wind.speed;
		    //const weatherIcon = data.weather[0].icon;
		    //const weatherIconAdrs = 'http://openweathermap.org/img/wn/' + weatherIcon + '@2x.png';
		    
		    // html 그리기
		    document.querySelector('.temp').innerText = '온도: ' + temperature + '°C';
		    document.querySelector('.place').innerText = '장소: ' + place;
		    document.querySelector('.desc').innerText = '날씨: ' + description;
		    document.querySelector('.humidity').innerText = '습도: ' + humidity + '%';
		    document.querySelector('.wind').innerText = '풍속: ' + wind + 'm/s';
		    //document.querySelector('.weatherIcon').setAttribute('src', weatherIconAdrs);
		    
		     // 날씨 정보 표시
	        const weatherInfo = document.querySelector('.weather-info');
	        if (!weatherInfo.classList.contains('show')) {
	            weatherInfo.classList.add('show');
	        }
	        
	        applyRandomColor();
		})
		
}

//검색 버튼 클릭 시 getWeatherByCoordinates 함수 호출
document.querySelector('.find_btn2').addEventListener('click', function() {
    const keyword = document.getElementById('keyword2').value;
    getWeatherByCoordinates(keyword);
});


</script>

<%@ include file="../common/foot.jspf"%>