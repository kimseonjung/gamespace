function rangeChange(idx) {
    console.log(idx);
    const ban = [1, 3, 7, 15, 30, 90, 36500];
    const level = document.getElementsByClassName('banDateBar')[idx].value;
    const display = document.getElementsByClassName('banDate-val')[idx];
    if(level < 6) {
        display.innerText = ban[level] + "일";
    } else {
        display.innerText = "영구";
    }
};