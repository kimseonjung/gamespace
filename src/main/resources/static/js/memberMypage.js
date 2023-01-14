window.addEventListener('DOMContentLoaded', function () {
    //사이트 아이콘 갱신
    for(let i = 0; i < 6; i++) linkIconCheck(i);
})

//사이트 아이콘 갱신
function linkIconCheck(i) {
    const targetLink = document.getElementsByClassName("social-btn")[i].href;
    console.log("linkIconCheck(" + i + ") - " + targetLink);
    document.getElementsByClassName('social-icon')[i].src = "/image/icon_light/globe.svg";
    if(targetLink.match(/^https:\/\/github\.com\/[A-Za-z\d](?:[A-Za-z0-9]|-(?=[A-Za-z0-9])){0,38}$/)) {
        document.getElementsByClassName('social-icon')[i].src = "/image/icon_light/github.svg";
    }
    if(targetLink.match(/^https?:\/\/(www\.)?youtube\.com\/(c|user|channel)\/[A-Za-z0-9_-]+$/)) {
        document.getElementsByClassName('social-icon')[i].src = "/image/icon_light/youtube.svg";
    }
    if(targetLink.match(/^https?:\/\/(www\.)?twitch\.tv\/[a-zA-Z0-9_]{4,25}$/)) {
        document.getElementsByClassName('social-icon')[i].src = "/image/icon_light/twitch.svg";
    }
    if(targetLink.match(/^https?:\/\/(www\.)?twitch\.tv\/[a-zA-Z0-9_]{4,25}$/)) {
        document.getElementsByClassName('social-icon')[i].src = "/image/icon_light/twitch.svg";
    }
    if(targetLink.match(/^https?:\/\/(www\.)?(facebook|fb)\.com\/(([A-Za-z0-9.]{5,50})|(profile\.php\?id=\d+))$/)) {
        document.getElementsByClassName('social-icon')[i].src = "/image/icon_light/facebook.svg";
    }
    if(targetLink.match(/^https:\/\/twitter\.com\/[a-zA-Z0-9_]{1,15}$/)) {
        document.getElementsByClassName('social-icon')[i].src = "/image/icon_light/twitter.svg";
    }
    if(targetLink.match(/^https?:\/\/(www\.)?instagram\.com\/[A-Za-z0-9-_.]{1,255}\/?$/)) {
        document.getElementsByClassName('social-icon')[i].src = "/image/icon_light/insta.svg";
    }
    if(targetLink.match(/^https?:\/\/steamcommunity\.com\/(?:profiles|id)\/[a-zA-Z0-9_-]+\/?$/)) {
        document.getElementsByClassName('social-icon')[i].src = "/image/icon_light/steam.svg";
    }
    if(targetLink.match(/^https?:\/\/stackoverflow\.com\/users\/\d+\/[A-Za-z0-9-]+$/)) {
        document.getElementsByClassName('social-icon')[i].src = "/image/icon_light/stackoverflow.svg";
    }
}

//팔로우 버튼 상태 갱신
function followRefresh(status) {
    console.log(status);
    const target = document.getElementsByClassName('follow-btn')[0];
    if(status) {
        target.classList.replace('followable', 'following');
    } else {
        target.classList.replace('following', 'followable');
    }
}

//팔로우 갱신
$(function () {
    $('.follow-btn').on('click', function () {
        let isFollow = $('#isFollow').val()=='true' ? 'T' : 'F';
        const userCode = $('#userCode').val();
        const beforeFollow = $('#beforeFollow').val();
        console.log(isFollow);
        $.ajax({
            url: '/member/myPage',
            type: 'post',
            data: {
                isFollow : isFollow,
                userCode : userCode,
                beforeFollow : beforeFollow
            },
            success : function (data) {
                isFollow = $('#isFollow').val()=='true';    //기존 팔로우 상태
                $('#isFollow').val(!isFollow);  //팔로우 상태 토글
                followRefresh(!isFollow);   //버튼 새로고침

                let afterFollow = (Number(data.beforeFollow) + (isFollow ? -1 : 1));
                $('#beforeFollow').val(afterFollow);  //안보이는 값 새로고침
                $('#follow-count').text(afterFollow);  //팔로워 값 새로고침

                return false;
            },
            error : function () {
                console.log('fail');
                return false;
            }
        });
    });
});