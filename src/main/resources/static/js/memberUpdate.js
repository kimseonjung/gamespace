$(function () {
    //값 입력 시
    $('.input-text').keyup(function () {
        registInputCheck(['ID', 'Nickname', 'Name', 'Birthday',
            'Phone', 'Email', 'Zip code', 'Address', 'Address Detail', 'About Me']);
    });
});