//페이지 로드 시 실행
window.addEventListener('load', function (){
    if(localStorage.getItem('screen-mode') == null) {
        localStorage.setItem('screen-mode', 'screen-light');
    }
    document.querySelector('body').className = localStorage.getItem('screen-mode');
});