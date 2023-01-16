$(function () {
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

                linkIconCheck(i);

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
});