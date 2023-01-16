window.addEventListener('DOMContentLoaded', function () {
    genderBtnResponse();
    registInputCheck(['ID', 'Password', 'Password Check', 'Nickname', 'Name', 'Birthday', 'Phone',
        'Email', 'Zip code', 'Address', 'Address Detail', 'About Me']);
})

/* ----- 회원 가입 전 검증 ----- */
function registVerify() {
    let pass = true;
    if(!checkIntroduce()) pass = false;
    if(!checkEmail('regist')) pass = false;
    if(!checkPhone()) pass = false;
    if(!checkBirthday()) pass = false;
    if(!checkName()) pass = false;
    if(!checkNickname('regist')) pass = false;
    if(!checkPwdCheck()) pass = false;
    if(!checkPwd()) pass = false
    if(!checkId('regist')) pass = false;
    if(!checkGooglereCAPTCHA()) pass = false;

    return pass;
}
function updateVerify() {
    let pass = true;
    if(!checkIntroduce()) pass = false;
    if(!checkEmail('regist')) pass = false;
    if(!checkPhone()) pass = false;
    if(!checkBirthday()) pass = false;
    if(!checkName('regist')) pass = false;
    if(!checkNickname('regist')) pass = false;

    return pass;
}

function findIdVerify() {
    let pass = true;
    if(!checkEmail('none')) pass = false;
    if(!checkName('none')) pass = false;
    if(pass && !findId()) pass = false;

    return pass;
}

function findPwdVerify() {
    let pass = true;
    if(!checkEmail('none')) pass = false;
    if(!checkId('none')) pass = false;
    if(pass && !findPwd()) pass = false;

    return pass;
}

//아이디 체크
function checkId(mode) {
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
    } else if(value == 'userNotFound') {    //예약어
        problem = '사용할 수 없는 아이디입니다.';
    }
    if(mode == 'regist') {
        if(problem != '') {
            idinput(problem);
            return false;
        }

        $.ajax({
            url : '/member/regist/idcheck',
            type : 'post',
            async : false,
            data : {
                userId : value
            },
            success: function (data) {
                if(data.canUse=='false') {
                    problem = '이미 사용중인 아이디입니다.';
                    idinput(problem);
                }
                return false;
            },
            error: function () {
                console.log('fail');
                return false;
            }
        });
    } else {
        idinput(problem);
    }

    return problem=='';
}
function idinput(problem) {
    document.getElementById('error-id').innerText = problem;
    if(problem=='') {
        document.getElementById("border-id").classList.remove('input-error');
        return true;
    } else {
        document.getElementById("border-id").classList.add('input-error');
        document.getElementsByName("userId")[0].focus();
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
        problem = '비밀번호는 최대 56자까지 입력 가능합니다.';
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
function checkNickname(mode) {
    let problem = '';
    const target = document.getElementsByName("userNickname")[0];
    const value = target.value+"";
    const size = getByteLengthOfUtf8String(value);
    if(value == '') {
        problem = '닉네임을 입력해주세요.';
    } else if(getByteLengthOfUtf8String(value) > 32) {
        problem = '입력할 수 있는 길이를 초과하였습니다. (' + size + '/32)';
    } else if(!value.match(/^[a-zA-Z0-9가-힣_]+$/)) {
        problem = '닉네임은 한글, 영어, 숫자, 언더바(_)만 사용 가능합니다.';
    }
    if(mode == 'regist') {
        if(problem != '') {
            nicknameinput(problem);
            return false;
        }

        $.ajax({
            url : '/member/regist/nicknamecheck',
            type : 'post',
            async : false,
            data : {
                userNickname : value
            },
            success: function (data) {
                if(data.canUse=='false') {
                    problem = '이미 사용중인 닉네임입니다.';
                    nicknameinput(problem);
                }
                return false;
            },
            error: function () {
                console.log('fail');
                return false;
            }
        });
    } else {
        nicknameinput(problem);
    }

    return problem=='';
}
function nicknameinput(problem) {
    document.getElementById('error-nickname').innerText = problem;
    if(problem=='') {
        document.getElementById("border-nickname").classList.remove('input-error');
        return true;
    } else {
        document.getElementById("border-nickname").classList.add('input-error');
        document.getElementsByName("userNickname")[0].focus();
        return false;
    }
}

//이름 체크
function checkName() {
    let problem = '';
    const target = document.getElementsByName("userName")[0];
    const value = target.value+"";
    const size = getByteLengthOfUtf8String(value);
    if(value == '') {
        problem = '이름을 입력해주세요.';
    } else if(size > 32) {
        problem = '입력할 수 있는 길이를 초과하였습니다. (' + size + '/32)';
    } else if(!value.match(/^[a-zA-Z가-힣0-9\s]+$/)) {
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

//생년월일 체크
function checkBirthday() {
    let problem = '';
    const target = document.getElementsByName("userBirthday")[0];
    let value = target.value+"";
    if(value == '') {
        problem = "생년월일을 입력해주세요.";
    } else if(!value.match(/^(19[0-9]{2}|2[0-9]{3})[\/\-.]?(0[1-9]|1[012])[\/\-.]?([123]0|[012][1-9]|31)$/)) {
        problem = "형식을 확인해주세요 : YYYYMMDD, YYYY-MM-DD, YYYY/MM/DD, YYYY.MM.DD";
    //형식 통일
    } else {
        console.log(value)
        // YYYY-MM-DD, YYYY/MM/DD, YYYY.MM.DD -> YYYYMMDD
        if(!value.match(/^[0-9]{8}$/)) {
            const tmp = value.slice(0, 4) + value.slice(5, 7) + value.slice(8, 10);
            value = tmp;
        }
        console.log(value)
        // YYYYMMDD -> YYYY-MM-DD
        const tmp = value.slice(0, 4) + '-' + value.slice(4, 6) + '-' + value.slice(6, 8);
        value = tmp;
        console.log(value)
    }

    document.getElementsByName("userBirthday")[0].value = value;
    document.getElementById('error-birthday').innerText = problem;
    if(problem=='') {
        document.getElementById("border-birthday").classList.remove('input-error');
        return true;
    } else {
        document.getElementById("border-birthday").classList.add('input-error');
        target.focus();
        return false;
    }
}

//전화번호 체크
function checkPhone() {
    let problem = '';
    const target = document.getElementsByName("userPhone")[0];
    let value = target.value+"";
    if(value == '') {
        value = '00000000000';
    } else if(!value.match(/^\d{3}(-\d{3,4}-|\d{3,4})\d{4}$/)) {
        problem = '연락처 정보를 다시 확인해주세요.';
    } else if(!value.match(/^\d{3}-\d{3,4}-\d{4}$/)) {
        // 000-0000-0000 -> 00000000000
        value.replace(/[^0-9]/, '');
    }

    document.getElementsByName("userPhone")[0].value = value;
    document.getElementById('error-phone').innerText = problem;
    if(problem=='') {
        document.getElementById("border-phone").classList.remove('input-error');
        return true;
    } else {
        document.getElementById("border-phone").classList.add('input-error');
        target.focus();
        return false;
    }
}

//이메일 체크
function checkEmail(mode) {
    let problem = '';
    const target = document.getElementsByName("userEmail")[0];
    const value = target.value+"";
    if(value == '') {
        problem = '이메일을 입력해주세요.';
    } else if(!value.match(/^([A-Z|a-z|0-9](\.|_){0,1})+[A-Z|a-z|0-9]\@([A-Z|a-z|0-9])+((\.){0,1}[A-Z|a-z|0-9]){2}\.[a-z]{2,3}$/)) {
        problem = '이메일 정보를 다시 확인해주세요.';
    }
    if(mode == 'regist') {
        if(problem != '') {
            emailinput(problem);
            return false;
        }

        $.ajax({
            url : '/member/regist/emailcheck',
            type : 'post',
            async : false,
            data : {
                userEmail : value
            },
            success: function (data) {
                if(data.canUse=='false') {
                    problem = '이미 사용중인 이메일입니다.';
                    emailinput(problem);
                }
                return false;
            },
            error: function () {
                console.log('fail');
                return false;
            }
        });
    } else {
        emailinput(problem);
    }

    return problem=='';
}
function emailinput(problem) {
    document.getElementById('error-email').innerText = problem;
    if(problem=='') {
        document.getElementById("border-email").classList.remove('input-error');
        return true;
    } else {
        document.getElementById("border-email").classList.add('input-error');
        document.getElementsByName("userEmail")[0].focus();
        return false;
    }
}

//Google reCAPTCHA
function checkGooglereCAPTCHA() {
    const userIsVerify = grecaptcha.getResponse().length > 0;

    if(userIsVerify) {
        document.getElementById('error-reCaptcha').innerText = "";
        return true;
    } else {
        document.getElementById('error-reCaptcha').innerText = "인증을 진행해주세요.";
        return false;
    }
}

//소개글 크기 체크
function checkIntroduce() {
    let problem = '';
    const target = document.getElementsByName("userIntroduce")[0];
    const value = target.value+"";
    const size = getByteLengthOfUtf8String(value);
    console.log(size);
    if(getByteLengthOfUtf8String(value) > 300) {
        problem = '입력할 수 있는 길이를 초과하였습니다. (' + size + '/300)';
    }

    document.getElementById('error-introduce').innerText = problem;
    if(problem=='') {
        document.getElementById("border-introduce").classList.remove('input-error');
        return true;
    } else {
        document.getElementById("border-introduce").classList.add('input-error');
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