var map = L.map('map', { crs: L.CRS.Simple });

var bounds = [[0, 0], [480, 640]];
L.imageOverlay('seat.png', bounds).addTo(map);
let seat = [];
let count = 0;

window.onload = function () {
    console.log("window.onload");
}

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

function onSaveButtonClick() {
    //保存時の処理を書く
}