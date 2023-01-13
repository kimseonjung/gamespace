// $('gameName').on('click',
function searchGameCodeList () {

    const gameCode = [];
    gameCode.push('tmp');
    $("input[name='game-tag']:checked").each(function (){
        let chk = $(this).val();
        gameCode.push(chk);
    });



    console.log(gameCode);

    $.ajax({
        url: "/news/tag",             // 요청할 서버url
        type : "post", // 타입은 뭘 쓸거니?
        datatype : "json",
        data: {
            gameCode : gameCode
        },

        success: function(data,status, xhr) {    // 결과 성공 콜백함수

            console.log(data.gameCodeNewsList[0].gameName);


            $('#table').html("");

            data.gameCodeNewsList.forEach(function(item) {
                let div = "<div class='lists'>";
                    div += "<div class='item'>";
                        div += "<div class='list'>";
                            div += "<a th:href=\"@{/news/newsDetail(newsCode=${news.newsCode})}\">";
                                div += "<div class='news-head'><p class='news-head-p'>"+item.newsTitle+"</p></div>";
                                div += "<div class='news-body'><p>"+item.newsContent+"</p></div>";
                                div += "<div class='news-foot'><p>"+item.newsDate+' '+item.memberName+' '+item.gameName+' '+item.newsView+"</p></div>";
                            div += "</a>"
                        div += "</div>";
                    div += "</div>";
                div += "</div>";
                $('#table').append(div);
            });


        },
        error: function(xhr, status, error) {   // 결과 에러 콜백함수
            console.log('error');
        }
    });
}
// )
;