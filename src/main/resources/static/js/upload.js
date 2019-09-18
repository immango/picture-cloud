
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();

    $("#y").click(function(){
        if($("#y").attr("src").indexOf("GY") >= 0) {
            $("#y").attr("src","/static/img/AY.png");
        }else {
            $("#y").attr("src","/static/img/GY.png");
        }
    });

    $.get("/imageCount",function (data, status) {
        $("#imageCount").text(data);
    });

    $.get("/nowTime",function (data, status) {
        $("#nowTime").text(data);
    })

});