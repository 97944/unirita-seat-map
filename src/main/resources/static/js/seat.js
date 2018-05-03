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
			viewSeat(jsonData);
		},
		error: function (jsonData) {
			console.log("json");
			alert("値はコンソール参照");
		}
	})

	function viewSeat(jsonData) {
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
						.bindPopup(String(count)));
					count++;
				}
			}
			map.fitBounds(bounds);
		}
	}
}

function tempSave() {
	//一時的にJsonデータを保存する
}


function onSaveButtonClick() {
	//保存時の処理を書く
}