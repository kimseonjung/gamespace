function searchGameCodeList () {

   if($("input[name='gameName']").is(":checked")){
       const gameCode = [];
       gameCode.push('tmp');
       $("input[name='gameName']:checked").each(function (){
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


               $('#item').html("");

               data.gameCodeNewsList.forEach(function(item) {
                   let div = "<div class='list'>";

                   div += "<a href='/news/newsDetail?newsCode="+item.newsCode+"'>";
                   div += "<div class='news-head'><p class='news-head-p'>"+item.newsTitle+"</p></div>";
                   div += "<div class='news-body'><p>"+item.newsContent+"</p></div>";
                   div += "<div class='news-foot'><p>"+item.newsDate+' '+item.memberName+' '+item.gameName+' '+item.newsView+"</p></div>";
                   div += "</a>"

                   div += "</div>";
                   $('#item').append(div);
               });




           },
           error: function(xhr, status, error) {   // 결과 에러 콜백함수
               console.log('error');
           }
       });
   }else{
       location.reload();
   }
};


// function getCheckboxValue () {
//     const gameCode = [];
//     gameCode.push('tmp');
//     $('input[name="gameName"]:checked').each(function (){
//         let chk = $(this).val();
//         gameCode.push(chk);
//     });
//
//
//
//     console.log(gameCode);
//
//     $.ajax({
//         url: "/news/tag",             // 요청할 서버url
//         type : "post", // 타입은 뭘 쓸거니?
//         datatype : "json",
//         data: {
//             gameCode : gameCode
//         },
//
//         success: function(data,status, xhr) {    // 결과 성공 콜백함수
//
//             console.log(data.gameCodeNewsList[0].gameName);
//
//
//
//
//             // data.gameCodeNewsList.forEach(function(item) {
//             //     let div = " ";
//             //     div += "<p class='tag-gameName'>"+item.gameName+"</p>";
//             //     $('#tag-result').append(div);
//             // });
//
//             let result = '';
//             data.gameCodeNewsList.forEach(function(item) {
//            result += "<p class='tag-gameName'>"+item.gameName+"</p>";
//            });
//
//             if (gameCode == 'tmp' || gameCode == ''){
//                 document.getElementById('tag-result').innerHTML
//                     = '';
//             } else {
//                 document.getElementById('tag-result').innerHTML
//                     = result;
//             };
//
//         },
//         error: function(xhr, status, error) {   // 결과 에러 콜백함수
//             console.log('error');
//         }
//     })};



/* 접었다펴기 */
$(document).ready(function(){
    $('.more').click(function(){
        if($('.more').hasClass('more')){
            $('.more').addClass('close').removeClass('more');
            $('.board').css('display', '');
        }else if($('.close').hasClass('close')){
            $('.close').addClass('more').removeClass('close');
            $('.board').css('display', 'none');
        }
    });
});

$(document).ready(function(){
    $('.mores').click(function(){
        if($('.mores').hasClass('mores')){
            $('.mores').addClass('closes').removeClass('mores');
            $('.tags').css('max-height', '1000px');
        }else if($('.closes').hasClass('closes')){
            $('.closes').addClass('mores').removeClass('closes');
            $('.tags').css('max-height', '110px');
        }
    });
});