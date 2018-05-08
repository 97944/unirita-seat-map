var map = L.map('map', { crs: L.CRS.Simple });

var bounds = [[0, 0], [428, 608]];
var circle = [];
var baseMaps = {
	"6F": L.imageOverlay('', bounds),
	"7F": L.imageOverlay('', bounds),
	"head": L.imageOverlay('', bounds)
}
L.control.layers(baseMaps).addTo(map);

map.on("baselayerchange", (event) => {
	console.log(event.name);
	if (event.name == "6F") {
		console.log(circle);
		for (var i = 0; i < circle.length; i++) {
			console.log(circle[i]);
			map.removeLayer(circle[i]);
		}
		readJson("6");
	}
	if (event.name == "7F") {
		readJson("7");
		for (var i = 0; i < circle.length; i++) {
			console.log(circle[i]);
			map.removeLayer(circle[i]);
		}
		L.circle([0, 50], { color: 'blue', radius: 35 }).addTo(map)
			.bindTooltip(String(count), { permanent: true, direction: 'center' }).openTooltip()
			.bindPopup(String(count));
	}
	if (event.name == "head") {
		readJson("head");
		for (var i = 0; i < circle.length; i++) {
			console.log(circle[i]);
			map.removeLayer(circle[i]);
		}
		L.circle([0, 100], { color: 'gray', radius: 35 }).addTo(map)
			.bindTooltip(String(count), { permanent: true, direction: 'center' }).openTooltip()
			.bindPopup(String(count));
	}
});

let seat = [];
let count = 0;

//////////////////編集画面////////////////////////
function onCreateButtonClick() {
	console.log("Button Clicked");
	const columnValue = document.getElementById("column").value;
	const lineValue = document.getElementById("line").value;
	const start_lineValue = document.getElementById("start_line").value;
	const start_columnValue = document.getElementById("start_column").value;

	if (seat.length == 0) {
		seat = [
			{
				column: columnValue,
				line: lineValue,
				start_column: start_columnValue,
				start_line: start_lineValue
			}
		];
	} else {
		seat.push(
			{
				column: columnValue,
				line: lineValue,
				start_column: start_columnValue,
				start_line: start_lineValue
			}
		);
	}
	console.log(seat);

	for (let i = 0; i < lineValue; i++) {
		for (let j = 0; j < columnValue; j++) {
			L.circle([770 - ((start_lineValue - 1) * 70) - i * 70, start_columnValue * 70 + j * 70], { color: 'green', radius: 35 }).addTo(map)
				.bindTooltip(String(count), { permanent: true, direction: 'center' }).openTooltip()
				.bindPopup(String(count));
			count++;
		}
	}
	map.fitBounds(bounds);
	return false;
}

window.onload = function () {
	//過去のJsonデータの読み込み
	readJson("6");
}

function readJson(floor) {
	var line = 0;
	var column = 0;
	var start_column = 0;
	var start_line = 0;
	var oc = false;
	var number = "";
	var name = "";
	var json = { column: column, line: line, start_column: start_column, start_line: start_line, oc: oc }
	var csv = { column: column, line: line, number: number, name: name }

	var jsonUrl = "http://localhost:8080/home/getJson/" + floor;
	$.ajax({
		type: "GET",
		url: jsonUrl,
		datatype: "json",
		success: function (jsonData) {
			readCsv(jsonData);
//			viewSeat(jsonData,data);
		},
		error: function (jsonData) {
			console.log("json");
			alert("値はコンソール参照");
		}
	})

	function viewSeat(jsonData,data) {
		console.log(jsonData);
		console.log(jsonData.length);
		for (let j = 0; j < jsonData.length; j++) {
			const columnValue = jsonData[j].column;
			console.log(columnValue);
			const lineValue = jsonData[j].line;
			const start_columnValue = jsonData[j].start_column;
			const start_lineValue = jsonData[j].start_line;

			if (seat.length == 0) {
				seat = [
					{
						column: columnValue,
						line: lineValue,
						start_column: start_columnValue,
						start_line: start_lineValue
					}
				];
			} else {
				seat.push(
					{
						column: columnValue,
						line: lineValue,
						start_column: start_columnValue,
						start_line: start_lineValue
					}
				);
			}
			console.log(seat);

			for (let i = 0; i < lineValue; i++) {
				for (let j = 0; j < columnValue; j++) {
					circle.push(L.circle([770 - ((start_lineValue - 1) * 70) - i * 70, start_columnValue * 70 + j * 70], { color: 'green', radius: 35 }).addTo(map)
						.bindTooltip(String(count), { permanent: true, direction: 'center' }).openTooltip()
						//.bindPopup(String(count)));
						.bindPopup('<div class="container"  style="width: 550px"><div class="col-xs-8"><table class="table"><tr><td rowspan=4 align=center><img src="../image/'+97937+'.jpg" style="width:117px;height:auto;"></td><th width="120"><b>社員番号</b></th><td width="150">'+count+'</td></tr><tr><th width="124"><b>入社年</b></th><td width="150">'+count+'</td></tr><tr><th><b>氏名(フリガナ)</b></th><td>'+count+' '+count+'</td><tr><th width="120"><b>氏名(漢字)</b></th><td width="150">'+count+' '+count+'</td></tr><tr><th><b>部署</b></th><td colspan=2>'+count+'</td></tr><tr><th width="120"><b>携帯電話番号</b></th><td colspan=2>'+count+'</td><tr><th><b>メールアドレス</b></th><td colspan="2"><a href="mailto: '+count+'">'+count+'</a></td></tr></table></div><div class="col-xs-4"><div id="calendar"></div></div>    <input type="button" value="カレンダー" onclick="calendar()"></input><div>'));

					count++;
				}
			}
			map.fitBounds(bounds);
		}
	}
}

function readCsv(jsonData) {

	var priority = "";
	var memberId = "";
	var lastNameKana = "";
	var firstNameKana = "";
	var lastName = "";
	var firstName = "";
	var division = "";
	var department = "";
	var section = "";
	var team = "";
	var position = "";
	var entryYear = "";
	var phoneNumber = "";
	var gmailAddress = "";
	var phoneAddress = "";
	var sfa = "";
	var member = "";

	var data = {priority : priority, memberId : memberId, lastNameKana : lastNameKana, firstNameKana : firstNameKana, lastName : lastName, firstName : firstName, division : division, department : department, section : section, team : team, position : position, entryYear : entryYear, phoneNumber : phoneNumber, gmailAddress : gmailAddress, phoneAddress : phoneAddress, sfa : sfa};

	        $.ajax({
	            type        : "POST",
	            url         : "http://localhost:8080/home/home",
	            data        : JSON.stringify(data),
	            minTimeout  : 1000,
	            multiplier  : 1,
	            contentType : 'application/JSON',
	            success     : function(data) {
								viewSeat(jsonData,data)
	                          },
	            error       : function(XMLHttpRequest, textStatus, errorThrown) {
	                            error();
	                          }
	        });

	function viewSeat(jsonData,data) {
		console.log(jsonData);
		console.log(jsonData.length);
		for (let j = 0; j < jsonData.length; j++) {
			const columnValue = jsonData[j].column;
			console.log(columnValue);
			const lineValue = jsonData[j].line;
			const start_columnValue = jsonData[j].start_column;
			const start_lineValue = jsonData[j].start_line;

			if (seat.length == 0) {
				seat = [
					{
						column: columnValue,
						line: lineValue,
						start_column: start_columnValue,
						start_line: start_lineValue
					}
				];
			} else {
				seat.push(
					{
						column: columnValue,
						line: lineValue,
						start_column: start_columnValue,
						start_line: start_lineValue
					}
				);
			}
			console.log(seat);

			for (let i = 0; i < lineValue; i++) {
				for (let j = 0; j < columnValue; j++) {
					circle.push(L.circle([770 - ((start_lineValue - 1) * 70) - i * 70, start_columnValue * 70 + j * 70], { color: 'green', radius: 35 }).addTo(map)
						.bindTooltip(data[i].lastName+' '+data[i].firstName, { permanent: true, direction: 'center' }).openTooltip()
						//.bindPopup(String(count)));
						//.bindPopup('<div class="container"  style="width: 550px"><div class="col-xs-8"><table class="table"><tr><td rowspan=4 align=center><img src="../image/'+data[343].memberId+'.jpg" style="width:117px;height:auto;"></td><th width="120"><b>社員番号</b></th><td width="150">'+data[343].memberId+'</td></tr><tr><th width="124"><b>入社年</b></th><td width="150">'+data[343].entryYear+'</td></tr><tr><th><b>氏名(フリガナ)</b></th><td>'+data[343].lastNameKana+' '+data[343].firstNameKana+'</td><tr><th width="120"><b>氏名(漢字)</b></th><td width="150">'+data[343].lastName+' '+data[343].firstName+'</td></tr><tr><th><b>部署</b></th><td colspan=2>'+data[343].division+'</td></tr><tr><th width="120"><b>携帯電話番号</b></th><td colspan=2>'+data[343].phoneNumber+'</td><tr><th><b>メールアドレス</b></th><td colspan="2"><a href="mailto: '+data[343].gmailAddress+'">'+data[343].gmailAddress+'</a></td></tr></table></div><div class="col-xs-4"><div id="calendar"></div></div>    <input type="button" value="カレンダー" onclick="calendar()"></input><div>'));
						.bindPopup('<div class="container"  style="width: 550px"><div class="col-xs-8"><table class="table"><tr><td rowspan=4 align=center><img src="../img/employee/'+data[i].memberId+'.jpg" style="width:117px;height:auto;"></td><th width="120"><b>社員番号</b></th><td width="150">'+data[i].memberId+'</td></tr><tr><th width="124"><b>入社年</b></th><td width="150">'+data[i].entryYear+'</td></tr><tr><th><b>氏名(フリガナ)</b></th><td>'+data[i].lastNameKana+' '+data[i].firstNameKana+'</td><tr><th width="120"><b>氏名(漢字)</b></th><td width="150">'+data[i].lastName+' '+data[i].firstName+'</td></tr><tr><th><b>部署</b></th><td colspan=2>'+data[i].division+'</td></tr><tr><th width="120"><b>携帯電話番号</b></th><td colspan=2>'+data[i].phoneNumber+'</td><tr><th><b>メールアドレス</b></th><td colspan="2"><a href="mailto: '+data[i].gmailAddress+'">'+data[i].gmailAddress+'</a></td></tr></table></div><div class="col-xs-4"><div id="calendar"></div></div>    <input type="button" value="カレンダー" onclick="calendar()"></input><div>'));

					count++;
				}
			}
			map.fitBounds(bounds);
		}
	}
};


function tempSave() {
	//一時的にJsonデータを保存する
}


function onSaveButtonClick() {
	//保存時の処理を書く
}


function calendar(){
    $('#calendar').fullCalendar({
        header: {
            left: 'prev,next,today',
            center: 'title',
            right: 'month,agendaWeek,agendaDay,listMonth'
          },
          titleFormat:"YYYY年MM月DD日(ddd)のスケジュール",
          defaultView: 'agendaDay',
//          timeFormat: 'H:mm{ - H:mm}',
          timeFormat: ' ',
          timezone: 'Asia/Tokyo',
          monthNames: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
          monthNamesShort: ['1月', '2月', '3月', '4月', '5月', '6月', '7月', '8月', '9月', '10月', '11月', '12月'],
          dayNames: ['日曜日', '月曜日', '火曜日', '水曜日', '木曜日', '金曜日', '土曜日'],
          dayNamesShort: ['日', '月', '火', '水', '木', '金', '土'],
          eventLimit: true,
          editable: true,
          slotEventOverlap: true,
          selectable: true,
          selectHelper: true,
          selectMinDistance: 1,
//        googleCalendarApiKey: 'AIzaSyAGJZ1NSOkTcRkH-QHItbT4rZVXusRpsXE',
        googleCalendarApiKey: 'AIzaSyCm0gS_6J3DnRQjkokILN89REWu3sTcGmE',
        events: {
            googleCalendarId: 'unirita.co.jp_cdih7v3hvkjddn3bgf22u1jfe4@group.calendar.google.com'
        }
    });

    $('#calbtn').hide();
    $('#fc-time').hide();

}

//function calendar(){
//
//	var kind = "";
//	var etag = "";
//	var summary = "";
//	var updated = "";
//	var timeZone = "";
//	var accessRole = "";
//	var defaultReminders = {method : method, minutes : minutes};
//	var nextPageToken = "";
//	var items = "";
//	var team = "";
//	var position = "";
//	var entryYear = "";
//	var phoneNumber = "";
//	var gmailAddress = "";
//	var phoneAddress = "";
//	var sfa = "";
//	var member = "";
//
//	var data = {kind : kind, etag : etag, summary : summary, updated : updated, timeZone : timeZone, accessRole : accessRole, defaultReminders : defaultReminders, nextPageToken : nextPageToken, items : items, team : team, position : position, entryYear : entryYear, phoneNumber : phoneNumber, gmailAddress : gmailAddress, phoneAddress : phoneAddress, sfa : sfa};
//
//	        $.ajax({
//		            type        : "POST",
//		            url         : "https://www.googleapis.com/calendar/v3/calendars/takahito_mori%40unirita.co.jp/events/?key=AIzaSyAGJZ1NSOkTcRkH-QHItbT4rZVXusRpsXE",
//		            data        : JSON.stringify(data),
//		            minTimeout  : 1000,
//		            multiplier  : 1,
//		            contentType : 'application/JSON',
//		            success     : function(data) {
//									alert(data[0]);
//		                          },
//		            error       : function(XMLHttpRequest, textStatus, errorThrown) {
//		                            error();
//		                          }
//		        });
//}
