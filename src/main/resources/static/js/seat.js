var map = L.map('map', { crs: L.CRS.Simple });

var bounds = [[0, 0], [480, 640]];
L.imageOverlay('seat.png', bounds).addTo(map);
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

window.onload = function readJson() {
	//過去のJsonデータの読み込み
	var line = 0;
	var column = 0;
	var start_column = 0;
	var start_line = 0;
	var oc = false;
	var number = "";
	var name = "";
	var json = {column : column, line : line, start_column : start_column, start_line : start_line, oc : oc}
	var csv = {column : column, line : line, number : number, name : name}

	$.ajax({
		type : "GET",
		url : "http://localhost:8080/home/getJson",
		datatype : "json",
		success: function(jsonData) {
			viewSeat(jsonData);
		},
		error: function(jsonData) {
			console.log("json");
			alert("値はコンソール参照");
		}
	})

	function viewSeat(jsonData){
		console.log(jsonData);
		console.log(jsonData.length);
		for(let j = 0; j < jsonData.length; j++) {
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
		            L.circle([770 - ((start_lineValue - 1) * 70) - i * 70, start_columnValue * 70 + j * 70], { color: 'green', radius: 35 }).addTo(map)
		                .bindTooltip(String(count), { permanent: true, direction: 'center' }).openTooltip()
		                .bindPopup(String(count));
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



////////////////閲覧画面//////////////////////
//window.onload = function() {
//	var line = 0;
//	var column = 0;
//	var start_column = 0;
//	var start_line = 0;
//	var half = false;
//	var number = "";
//	var name = "";
//	var json = {column : column, line : line, start_column : start_column, start_line : start_line, half : half}
//	var csv = {column : column, line : line, number : number, name : name}
//
//	$.ajax({
//		type : "GET",
//		url : "http://localhost:8080/home/getJson",
//		datatype : "json",
//		success: function(jsonData) {
//			viewSeat(jsonData);
//		},
//		error: function(jsonData) {
//			console.log("json");
//			alert("値はコンソール参照");
//		}
//	})
	// $.ajax({
	// 	type : "GET",
	// 	url : "http://localhost:8080/home/getCsv",
	// 	datatype : "json",
	// 	success: function(csvData) {
	// 		console.log(csvData);
	// 		csv = csvData;
	// 	},
	// 	error: function(csvData) {
	// 		console.log("csv");
	// 		alert("値はコンソール参照");
	// 	}
	// });
//}

//function viewSeat(jsonData){
//	console.log(jsonData);
//}



// function viewSeat(jsonData, csvData) {
// 	for(let j = 0; j < Object.keys(jsonData).length; j++) {
//         console.log("view");
//         const columnValue = jsonData[j].get(0);
//         const lineValue = jsonData[j].get(1);
//         const start_lineValue = jsonData[j].get(2);
//         const start_columnValue = jsonData[j].get(3);
//         const half = jsonData[j].get(4);
//         if (seat.length == 0) {
//             seat = [
//                 {
//                     column: columnValue,
//                     line: lineValue,
//                     start_column: start_columnValue,
//                     start_line: start_lineValue
//                 }
//             ];
//         } else {
//             seat.push(
//                 {
//                     column: columnValue,
//                     line: lineValue,
//                     start_column: start_columnValue,
//                     start_line: start_lineValue
//                 }
//             );
//         }
//         console.log(seat);

//         for (let i = 0; i < data.size(); i++) {
//             L.circle([770 - ((start_lineValue - 1) * 70) - i * 70, start_columnValue * 70 + j * 70], { color: 'green', radius: 35 }).addTo(map)
//                 .bindTooltip(String(count), { permanent: true, direction: 'center' }).openTooltip()
//                 .bindPopup(String(count));
//             count++;
//         }
//         map.fitBounds(bounds);
//     }

//     return false;
//}