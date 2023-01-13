function infoCheck() {
    return true;
}

$profileClear = document.getElementById("profile-clear");
// $profileClear.onClick = () => removeProfile(0);

/* 페이지 로드 시 실행 */
window.onload = function () {
    registInputCheck(['ID', 'Password', 'Password Check', 'Nickname', 'Name', 'Birthday', 'Phone',
        'Email', 'Zip code', 'Address', 'Address Detail', 'About Me']);
    genderBtnResponse();
}

/* ----------------------- */

/* ----- 회원 가입 폼 ----- */
//입력값 있을 시 폼 변경
$(function () {
    //값 입력 시
    $('.input-text').keyup(function () {
        const content = ['ID', 'Password', 'Password Check', 'Nickname', 'Name', 'Birthday', 'Phone',
                        'Email', 'Zip code', 'Address', 'Address Detail', 'About Me'];
        registInputCheck(content);
    });
    // //포커스 될 시
    // $('.input-text').focus(function () {
    //
    // })
    // //포커스를 잃었을 시
    // $('.input-text').blur(function () {
    //     const content = document.getElementsByClassName('input-text');
    //     for(let i = 0; i < content.length; i++) {
    //         if(content[i].value=='') {
    //             document.getElementsByClassName('input-placeholder')[i].classList.remove('input-filled');
    //         }
    //     }
    // })
});

function registInputCheck(content) {
    const targetList = document.getElementsByClassName('input-text');
    const textList = document.getElementsByClassName('input-head');
    // const borderList = document.getElementsByClassName('input-placeholder');
    for(let i = 0; i < targetList.length; i++) {
        const target = targetList[i];
        const text = textList[i];
        // const border = borderList[i];
        if(target.value == "") {
            text.innerHTML = "";
        } else {
            text.innerHTML = `<p class="input-title">${content[i]}</p>`;
            // border.classList.add('input-filled');
        }
    }
}
/* ----- 회원 가입 폼 끝 ----- */

/* ----- 성별 버튼 반응 ----- */
$(function () {
    $("input[name=userGender]").on("click", () => genderBtnResponse());
});

function genderBtnResponse() {
    const gender = $('input[name="userGender"]:checked').val();
    const target = document.getElementsByClassName("gender-btn");
    const find = gender=='M' ? 0 : (gender=='F' ? 1 : 2);
    console.log(gender);
    for(let i = 0; i < 3; i++) {
        target[i].classList.replace("btn-blue-default", "btn-white-default");
    }
    target[find].classList.replace("btn-white-default", "btn-blue-default");
}

/* ----- 성별 버튼 반응 끝 ----- */

/* ----- 회원 가입 전 검증 ----- */
function registVerify() {
    const userId = document.getElementsByName("userId")[0];
    const userPwd = document.getElementsByName("userPwd")[0];
    const userPwdChk = document.getElementsByName("userPwd-check")[0];
    const userNickname = document.getElementsByName("userNickname")[0];
    const userName = document.getElementsByName("userName")[0];
    const userBirthday = document.getElementsByName("userBirthday")[0];
    const userPhone = document.getElementsByName("userPhone")[0];
    const userEmail = document.getElementsByName("userEmail")[0];
    const userIsVerify = grecaptcha.getResponse().length == 0;

    //ID
    // if()
    //reCAPTCHA
    if(userIsVerify) {
        document.getElementById('error-reCaptcha').innerText = "인증을 진행해주세요.";
        return false;
    } else {
        document.getElementById('error-reCaptcha').innerText = "";
    }

    return true;

}
/* ----- 회원 가입 전 검증 끝 ----- */

/* ----- 링크 수정 ----- */
$(function () {
    for (let i = 0; i < 6; i++) {
        $('.link-modify-submit').eq(i).on('click', function () {
            const memberCode = $(".memberCode").eq(0).val();
            const linkCode = i + 1;
            const modifyLink = $(".modifyLink").eq(i).val();
            console.log(modifyLink);
            $.ajax({
                url : '/member/userSetting',
                type : 'post',
                data : {
                    memberCode : memberCode,
                    linkCode : linkCode,
                    modifyLink : modifyLink
                },
                success : function (data) {
                    console.log('list-' + data.linkCode);
                    const target = document.getElementsByClassName("list-" + data.linkCode);
                    target[0].classList.replace('list-hide', 'list-show');
                    target[1].classList.replace('list-show', 'list-hide');
                    document.getElementsByClassName("social-btn")[i].href = modifyLink;
                    document.getElementsByClassName("text-link")[i].innerHTML = modifyLink;
                    return false;
                },
                error : function () {
                    console.log('fail');
                    return false;
                }
            });
        });

        $('.link-unchecked').eq(i).on('click', function () {
            for(let j = 1; j <= 6; j++) {
                const target = document.getElementsByClassName("list-" + j);
                target[1].classList.replace('list-show', 'list-hide');
                target[0].classList.replace('list-hide', 'list-show');
            }
            const linkCode = i + 1;
            $.ajax({
                url : '/member/userSetting/initLinkModify',
                type : 'post',
                data : {
                    linkCode : linkCode
                },
                success : function (data) {
                    console.log('list-' + data.linkCode)
                    const target = document.getElementsByClassName("list-" + data.linkCode);
                    target[0].classList.replace('list-show', 'list-hide');
                    target[1].classList.replace('list-hide', 'list-show');
                    const focusTarget = document.getElementsByClassName("modifyLink")[i];

                    //포커스 및 커서 마지막으로 이동
                    const tmp = focusTarget.value;
                    focusTarget.value = '';
                    focusTarget.focus();
                    focusTarget.value = tmp;
                    focusTarget.scrollLeft = 10000;
                    return false;
                },
                error : function () {
                    console.log('fail');
                    return false;
                }
            });
        });
    }
});

/* ----- 링크 수정 끝 ----- */

/* ----- 링크 버튼 갱신 ----- */

function refreshLink(index, newURL) {
    index -= 1;
    const target = document.getElementsByClassName("social-btn")[index];

}

/* ----- 링크 버튼 갱신 끝 ----- */

/* ----- 프로필 수정 ----- */

function loadProfile(targetObj) {
    const profile = targetObj.files[0];
    const preview = document.getElementsByClassName(".profile-change")[0];

    if(!profile.type.match(/image.*/)) return;


}

function removeProfile(index) {
}

/* ----- 프로필 수정 끝 ----- */

/* ----- 팔로우 버튼 ----- */
$(function () {

})