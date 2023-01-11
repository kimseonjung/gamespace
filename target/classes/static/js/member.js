function registCheck() {
    if(grecaptcha.getResponse().length == 0) {
        alert("reCAPTCHA 인증 실패");
        return false;
    }
    return true;
}

function infoCheck() {
    return true;
}

function loadProfile(value) {
    if(value.files && value.files[0]) {
        const reader = new FileReader();
        reader.onload = function (event) {
            document.getElementsByClassName("profile-image")
        }
    }
}