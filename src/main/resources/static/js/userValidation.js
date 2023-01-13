/* ----- 회원 가입 전 검증 ----- */
function registVerify() {
    let pass = true;
    if(!checkId()) pass = false;
    if(!checkPwd()) pass = false
    if(!checkPwdCheck()) pass = false;
    if(!checkNickname()) pass = false;
    if(!checkName()) pass = false;

    //reCAPTCHA
    const userIsVerify = grecaptcha.getResponse().length > 0;

    if(userIsVerify) {
        document.getElementById('error-reCaptcha').innerText = "";
    } else {
        document.getElementById('error-reCaptcha').innerText = "인증을 진행해주세요.";
        pass = false;
    }

    return pass;
}

//아이디 체크
function checkId() {
    let problem = '';
    const target = document.getElementsByName("userId")[0];
    const value = target.value+"";
    if(value == '') {
        problem = '아이디를 입력해주세요.';
    } else if(value.length > 10) {
        problem = '아이디는 최대 10자까지입니다.';
    } else if(value.match(/^[0-9]+/)) {
        problem = '아이디는 숫자로 시작할 수 없습니다.';
    } else if(!value.match(/^[a-zA-Z]+[a-zA-Z0-9]*$/)) {
        problem = '사용할 수 없는 아이디입니다.';
    }

    document.getElementById('error-id').innerText = problem;
    if(problem=='') {
        document.getElementById("border-id").classList.remove('input-error');
        return true;
    } else {
        document.getElementById("border-id").classList.add('input-error');
        target.focus();
        return false;
    }
}

//비밀번호 체크
function checkPwd() {
    let problem = '';
    const target = document.getElementsByName("userPwd")[0];
    const value = target.value+"";
    if(value == '') {
        problem = '비밀번호를 입력해주세요.';
    } else if(value.length < 8) {
        problem = '비밀번호는 8자 이상 입력해주세요.';
    } else if(value.length > 56) {
        problem = '비밀번호는 최대 56자까지입니다.';
    } else if(!value.match(/((?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*\W).{8,56})/)) {
        problem = '비밀번호는 영문자 대문자와 소문자, 숫자, 특수문자를 포함해야 합니다.';
    }

    document.getElementById('error-pwd').innerText = problem;
    if(problem=='') {
        document.getElementById("border-pwd").classList.remove('input-error');
        return true;
    } else {
        document.getElementById("border-pwd").classList.add('input-error');
        target.focus();
        return false;
    }
}

//비밀번호 확인 체크
function checkPwdCheck() {
    let problem = '';
    const target = document.getElementsByName("userPwd-check")[0];
    const value = target.value+"";
    if(value == '') {
        problem = '비밀번호를 다시 한 번 입력해주세요.';
    } else if(value != document.getElementsByName("userPwd")[0].value+"") {
        problem = '비밀번호가 일치하지 않습니다';
    }

    document.getElementById('error-pwd-check').innerText = problem;
    if(problem=='') {
        document.getElementById("border-pwd-check").classList.remove('input-error');
        return true;
    } else {
        document.getElementById("border-pwd-check").classList.add('input-error');
        target.focus();
        return false;
    }
}

//닉네임 체크
function checkNickname() {
    let problem = '';
    const target = document.getElementsByName("userNickname")[0];
    const value = target.value+"";
    if(value == '') {
        problem = '닉네임을 입력해주세요.';
    } else if(getByteLengthOfUtf8String(value) > 32) {
        problem = '닉네임 글자수는 한글10자, 영어30자까지입니다.';
    } else if(!value.match(/^[a-zA-Z0-9가-힣_]+$/)) {
        problem = '닉네임은 한글, 영어, 숫자, 밑줄(_)만 사용 가능합니다.';
    }

    document.getElementById('error-nickname').innerText = problem;
    if(problem=='') {
        document.getElementById("border-nickname").classList.remove('input-error');
        return true;
    } else {
        document.getElementById("border-nickname").classList.add('input-error');
        target.focus();
        return false;
    }
}

//이름 체크
function checkName() {
    let problem = '';
    const target = document.getElementsByName("userName")[0];
    const value = target.value+"";
    if(value == '') {
        problem = '이름을 입력해주세요.';
    } else if(getByteLengthOfUtf8String(value) > 32) {
        problem = '입력할 수 있는 길이를 초과하였습니다.';
    } else if(!value.match(/^[a-zA-Z가-힣]+$/)) {
        problem = '이름에 사용할 수 없는 문자가 포함되어있습니다.';
    }

    document.getElementById('error-name').innerText = problem;
    if(problem=='') {
        document.getElementById("border-name").classList.remove('input-error');
        return true;
    } else {
        document.getElementById("border-name").classList.add('input-error');
        target.focus();
        return false;
    }
}

/* ----- 회원 가입 전 검증 끝 ----- */

/* ----- string byte size ---- */
function getByteLengthOfUtf8String(s) {
    if(s != undefined && s != "") {
        for(b=i=0;c=s.charCodeAt(i++);b+=c>>11?3:c>>7?2:1);
        return b;
    } else {
        return 0;
    }
}

/* ----- string byte size 끝 ---- */