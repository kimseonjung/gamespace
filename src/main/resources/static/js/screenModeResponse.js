//페이지 로드 시 실행
window.addEventListener('load', function () {
    if(localStorage.getItem('screen-mode') == 'screen-dark') {
        document.getElementsByClassName('screen-mode-btn')[0]
            .checked = true;
    }
})

//토글 변화 감지
document.querySelector('.screen-mode-btn').addEventListener(
    'change', function (event) {
        localStorage.setItem('screen-mode', event.target.checked ?
        'screen-dark' : 'screen-light');
        switchScreenMode(localStorage.getItem('screen-mode'));
    }
);

//화면 다크모드 전환
function switchScreenMode(mode) {
    const beforeMode = mode=='screen-light' ? 'screen-dark' : 'screen-light';
    document.getElementsByClassName(beforeMode)[0]
        .classList.replace(beforeMode, mode);
}