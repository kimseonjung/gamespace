$(function () {
    //값 입력 시
    $('.input-text').keyup(function () {
        registInputCheck(['Password', 'New Password', 'New Password Check']);
    });
});

function findPwd() {
    const id = document.getElementsByName('userId')[0].value;
    const email = document.getElementsByName('userEmail')[0].value;
    $.ajax({
        url : '/member/findPwd',
        type : 'post',
        data : {
            userId : id,
            userEmail : email
        },
        success: function (data) {
            if(data.resultEmail == 'userNotFound') {
                document.getElementById('error-find-user').innerText = '사용자 정보를 찾을 수 없습니다.';
            } else {
                document.getElementById('error-find-user').innerText = '조회된 이메일 [' + data.resultEmail + ']로 인증번호를 전송했습니다.';

                document.getElementsByClassName('disabled-placeholder')[0].classList.remove('disabled-placeholder');
                document.getElementsByClassName('disabled-text')[0].disabled = false;
                document.getElementsByClassName('disabled-text')[0].classList.replace('disabled-text', 'input-text');
                document.getElementsByClassName('login-buttons')[0].innerHTML = '';
                document.getElementById('findEmailCode').innerHTML = '<input type="submit" value="이메일 인증" class="login-btn btn-blue-default btn-response login-btn-text white-text" style="width: 320px">';
            }
            return false;
        },
        error : function () {
            document.getElementById('error-find-id').innerText = '사용자 정보를 찾을 수 없습니다.';
            return false;
        }
    });
}

function findPwdDirect() {
    let pass = false;
    const input = document.getElementsByName('userPwd')[0].value;
    $.ajax({
        url : '/member/findPwdDirect',
        type : 'post',
        data : {
            inputPass : input
        },
        async : false,
        success: function (data) {
            if(data.findResult == 'passwordNotMatch') {
                document.getElementById('error-pwd').innerText = '비밀번호가 일치하지 않습니다.';
                document.getElementById("border-pwd").classList.add('input-error');
                document.getElementsByName("userPwd")[0].focus();
            } else {
                document.getElementById('error-pwd').innerText = '';
                document.getElementById("border-pwd").classList.remove('input-error');
                pass = true;
            }
            return false;
        },
        error : function () {
            document.getElementById('error-find-user').innerText = '오류가 발생했습니다.';
            return false;
        }
    });
    return pass;
}