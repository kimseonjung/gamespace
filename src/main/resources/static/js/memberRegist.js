//입력값 있을 시 폼 변경
$(function () {
    //값 입력 시
    $('.input-text').keyup(function () {
        const content = ['ID', 'Password', 'Password Check', 'Nickname', 'Name', 'Birthday', 'Phone',
            'Email', 'Zip code', 'Address', 'Address Detail', 'About Me'];
        registInputCheck(content);
    });
});