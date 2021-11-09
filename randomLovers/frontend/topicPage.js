$(document).ready(function () {
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
    start1();
});
var id = decodeURIComponent(window.location.search);
var start1 = function(){
    id = id.substring(4);
    function successCallback(response) {
        createPage(response);
    }
    function errorCallback1(request, status, error) {
        
        //window.location.replace("500error.html");
       //window.location.href="500error.html";
    }
    // perform an ajax http get request
    $.ajax({
        url: 'http://192.168.1.21:8080/randomlovers/topics/' + id,
        async: true,
        success: successCallback,
        error: errorCallback1
    });
    fetchComments();
}
var fetchComments = function(){
    function successCallbackComments(response) {
        console.log("inside comments")
        console.log(response);
        createComments(response);
    }
    function errorCallbackComments(request, status, error) {
        //window.location.replace("500error.html");
       //window.location.href="500error.html";
    }
    // perform an ajax http get request
    $.ajax({
        url: 'http://192.168.1.21:8080/randomlovers/comments/' + id,
        async: true,
        success: successCallbackComments,
        error: errorCallbackComments
    });
}
var createPage = function(response){
    var title = $("#header2");
    $(title).text( response.name);
    var question = $("#question1");
    $(question).text(response.description);
    var optionA = $(".form-check-label3");
    $(optionA).text(response.optionAName);
    var optionB = $(".form-check-label4");
    $(optionB).text(response.optionBName);
    var picture = $("#childHotTopic3");
    result = "<img id='picture' src=" + response.imagePath + " alt='hot topic'/>";
    console.log(result);
    $(picture).append(result);
}
var createComments = function(response){
    var comments = $("#commentdiv");
    for(var i=0; i < response.length;i++){
        result = "<div id='previousComment'><img id='userPicture' src='resources/boneco.png' alt='User Picture'/>" +
        "<p id='usernameP'>" + response[i].user.username + "</p>" + "<p id='previousComment'>" + response[i].content +"</p>" +
        "</div>"
        $(comments).append(result);
    }
}
function validateForm() {
    var radios = document.getElementsByName("group1");
    var formValid = false;
    var i = 0;
    while (!formValid && i < radios.length) {
        if (radios[i].checked) formValid = true;
        i++;
    }
    if (!formValid) alert("Do you really do not have an opinion ?!");
    return formValid;
}
function persist(){
    displayRadioValue();
    function successCallbackSubmit(response) {
        var element = $(".form-check");
        result = "<h4>"+ response.optionAName + " : " + response.optionA +"</h4>" +
        "<p></p><h4>"+ response.optionBName + " : " + response.optionB + "</h4>"
        console.log(answer);
        $(element).replaceWith(result);
    }
    function errorCallbackSubmit(request, status, error) {
        //window.location.replace("500error.html");
       //window.location.href="500error.html";
    }
    // perform an ajax http get request
    $.ajax({
        url: 'http://192.168.1.21:8080/randomlovers/topics/' + answer + "/" + id,
        type: 'POST',
        async: true,
        success: successCallbackSubmit,
        error: errorCallbackSubmit
    });
    var answer;
    function displayRadioValue() {
        var ele = document.getElementsByName('group1');
        for(i = 0; i < ele.length; i++) {
            if(ele[i].checked)
            answer = ele[i].value;
            console.log(answer)
        }
    }
}