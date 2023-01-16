$(function () {
    //값 입력 시
    $('.input-text').keyup(function () {
        registInputCheck(['Name', 'Email']);
    });
});

function findId() {
    const name = document.getElementsByName('userName')[0].value;
    const email = document.getElementsByName('userEmail')[0].value;
    $.ajax({
        url : '/member/findId',
        type : 'post',
        data : {
            memberName : name,
            memberEmail : email
        },
        success: function (data) {
            if(data.resultId == 'userNotFound') {
                document.getElementById('error-find-id').innerText = '사용자 정보를 찾을 수 없습니다.';
            } else {
                document.getElementById('error-find-id').innerText = '조회 결과 아이디는 [' + data.resultId + ']입니다.';
            }
        },
        error : function () {
            document.getElementById('error-find-id').innerText = '사용자 정보를 찾을 수 없습니다.';
            return false;
        }
    });
}