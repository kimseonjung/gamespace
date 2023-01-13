$('#chooseFile').bind('change', function () {
    var filename = $("#chooseFile").val();
    if (/^\s*$/.test(filename)) {
        $(".file-upload").removeClass('active');
        $("#noFile").text("파일첨부");
    }
    else {
        $(".file-upload").addClass('active');
        $("#noFile").text(filename.replace("C:\\fakepath\\", ""));
    }
});

$('#title').on("change", function () {
    var title = $("#title").val();
    if (/^\s*$/.test(title)) {
        $(".news-insert").removeClass('active');
    }
    else {
        $(".news-insert").addClass('active');
    }
});

$('#insertNewsCom').on("change", function () {
    let insertNews = $("#insertNewsCom").val();
    if (/^\s*$/.test(insertNews)) {
        $(".detail-com-input").removeClass('active');
    }
    else {
        $(".detail-com-input").addClass('active');
    }
});

$('#textarea').on("change", function () {
    var title = $("#textarea").val();
    if (/^\s*$/.test(title)) {
        $(".textarea-wrap").removeClass('active');
    }
    else {
        $(".textarea-wrap").addClass('active');
    }
});
