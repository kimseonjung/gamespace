$(function () {
    //값 입력 시
    $('.input-text').keyup(function () {
        updateInputCheck(['ID', 'Nickname', 'Name', 'Birthday',
            'Phone', 'Email', 'Zip code', 'Address', 'Address Detail', 'About Me']);
    });
});