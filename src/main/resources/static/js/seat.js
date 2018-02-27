var map = L.map('map', { crs: L.CRS.Simple });

var bounds = [[0, 0], [480, 640]]; // ここでは画像の解像度をboundsに設定する。
L.imageOverlay('seat.png', bounds).addTo(map); // 背景画像を設定する。

function onButtonClick() {
    console.log("Button Clicked");
    var column = document.getElementById("column").value;
    var line = document.getElementById("line").value;
    var start_line = document.getElementById("start_line").value;
    var start_column = document.getElementById("start_column").value;

    for (var i = line; i > 0; i--) {
        for (var j = 0; j < column; j++) {
            L.circle([770 - ((start_line - 1) * 70) - i * 70, start_column * 70 + j * 70], { color: 'green', radius: 35 }).addTo(map)
                .bindTooltip("hoge", { permanent: true, direction: 'center' }).openTooltip()
                .bindPopup("hoge");
        }
    }
    map.fitBounds(bounds);
}