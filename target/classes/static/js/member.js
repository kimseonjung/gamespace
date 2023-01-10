function registCheck() {
    if(grecaptcha.getResponse().length == 0) {
        alert("reCAPTCHA 인증 실패");
        return false;
    }
    return true;
}