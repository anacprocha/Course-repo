$(document).ready(function() {
    start();
});

var start = function(){

    function successCallback(response) {
        console.log(response);
        populateDiv(response);
    }
    
    function errorCallback(request, status, error) {
       window.location.href="500error.html";
    }
    
    // perform an ajax http get request
    $.ajax({
        url: 'http://192.168.1.21:8080/randomlovers/topics/hot',
        async: true,
        success: successCallback,
        error: errorCallback
    });
}

var populateDiv = function(response) {

    for(var i = 0; i < 6; i++){

        console.log("inside loop")

        result="";
        
        var element = $('#childHotTopic' + i);

        result = "<a href='TopicPage.html?id=" + response[i].id + "'><img id='veryHotPicture' src=" + response[i].imagePath + " alt='hot topic'/></a>" + 
        "<a href='TopicPage.html?id=" + response[i].id + "'><h4 id='imageLegend'>" + response[i].name + "</h4></a>";

        
        
        $(element).append(result);
        console.log(result);

    }

    

}




