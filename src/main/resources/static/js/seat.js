
var map = L.map('map', { crs: L.CRS.Simple });

var bounds = [[0, 0], [480, 640]]; // ここでは画像の解像度をboundsに設定する。
L.imageOverlay('seat.png', bounds).addTo(map); // 背景画像を設定する。

function onButtonClick() {
    console.log("Button Clicked");
    var column = document.getElementById("column").value;
    var line = document.getElementById("line").value;
    var start_line = document.getElementById("start_line").value;

    for (var i = line; i > 0; i--) {
        console.log("hoge");
        for (var j = 0; j < column; j++) {
            L.circle([770 - ((start_line - 1) * 70) - i * 70, j * 70], { color: 'green', radius: 35 }).addTo(map)
                .bindTooltip("hoge", { permanent: true, direction: 'center' }).openTooltip()
                .bindPopup("hoge");
        }
    }
    map.fitBounds(bounds);
}

// // お誕生日席
// L.circle([500, 100], { color: 'blue', radius: 35 }).addTo(map)
//     .bindTooltip("古川技監", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("古川技監");
// L.circle([500, 450], { color: 'blue', radius: 35 }).addTo(map)
//     .bindTooltip("秋山常務", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("秋山常務");

// // 開発部1列目
// L.circle([395, 65], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("王(霊)", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("王(霊)");
// L.circle([395, 135], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("大矢", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("大矢");
// L.circle([325, 65], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("近藤", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("近藤");
// L.circle([325, 135], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("影山", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("影山");
// L.circle([255, 65], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("大須賀", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("大須賀");
// L.circle([255, 135], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("鈴木(佑)", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("鈴木(佑)");
// L.circle([185, 65], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("吉村", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("吉村");
// L.circle([185, 135], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("木内", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("木内");
// L.circle([115, 65], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("塗", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("塗");
// L.circle([115, 135], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("鈴木(康)", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("鈴木(康)");

// //開発部2列目
// L.circle([395, 240], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("梅谷", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("梅谷");
// L.circle([395, 310], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("深町", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("深町");
// L.circle([325, 240], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("千葉<br/>(繁雄)", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("千葉<br/>(繁雄)");
// L.circle([325, 310], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("大井<br/>俊弘", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("大井<br/>俊弘");
// L.circle([255, 240], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("津田", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("津田");
// L.circle([255, 310], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("雨宮", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("雨宮");
// L.circle([185, 240], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("後藤 格", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("後藤 格");
// L.circle([185, 310], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("桑野", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("桑野");
// L.circle([115, 240], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("高橋<br/>由光", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("高橋<br/>由光");
// L.circle([115, 310], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("野口<br/>健二", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("野口<br/>健二");

// //開発部3列目
// L.circle([395, 415], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("矢板", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("矢板");
// L.circle([395, 485], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("阿部", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("阿部");
// L.circle([325, 415], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("村山", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("村山");
// L.circle([325, 485], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("吉谷", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("吉谷");
// L.circle([255, 415], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("森(崇)", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("森(崇)");
// L.circle([255, 485], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("姜", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("姜");
// L.circle([185, 415], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("寺田(欣)", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("寺田(欣)");
// L.circle([185, 485], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("郝", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("郝");
// L.circle([115, 415], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("豊村", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("豊村");
// L.circle([115, 485], { color: 'green', radius: 35 }).addTo(map)
//     .bindTooltip("開発部<br/>共有マシン", { permanent: true, direction: 'center' }).openTooltip()
//     .bindPopup("開発部<br/>共有マシン");

//開発部4列目


