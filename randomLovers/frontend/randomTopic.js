$(document).ready(function() {
    $('#sidebarCollapse').on('click', function () {
        $('#sidebar').toggleClass('active');
    });
    start();
});
var id;
var start = function(){
    function successCallback(response) {
        console.log(response);
        createRandomPage(response);
        id=response.id;
        fetchCommentsRandom(response.id);
    }
    function errorCallback(request, status, error) {
       //window.location.href="error.html";
       
       //window.location.replace("error.html");
    }
    // perform an ajax http get request
    $.ajax({
        url: 'http://192.168.1.21:8080/randomlovers/topics/random',
        async: true,
        success: successCallback,
        error: errorCallback
    });
}
var fetchCommentsRandom = function(id){
    function successCallbackCommentsRandom(response) {
        console.log("inside comments")
        console.log(response);
        createComments(response);
    }
    function errorCallbackCommentsRandom(request, status, error) {
        //window.location.replace("500error.html");
       //window.location.href="500error.html";
    }
    // perform an ajax http get request
    $.ajax({
        url: 'http://192.168.1.21:8080/randomlovers/comments/' + id,
        async: true,
        success: successCallbackCommentsRandom,
        error: errorCallbackCommentsRandom
    });
}
var createRandomPage = function(response){
    console.log("hello")
    var title = $("#header2");
    $(title).text( response.name);
    var question = $("#question");
    $(question).text(response.description);
    var optionA = $(".form-check-label1");
    $(optionA).text(response.optionAName);
    var optionB = $(".form-check-label2");
    $(optionB).text(response.optionBName);
    var picture = $("#childHotTopic2");
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
function validateForm1() {
    var radios = document.getElementsByName("group2");
    var formValid = false;
    var i = 0;
    while (!formValid && i < radios.length) {
        if (radios[i].checked) formValid = true;
        i++;
    }
    if (!formValid) alert("Do you really do not have an opinion ?!");
    return formValid;
}
function randomPersist(){
    displayRadioValue();
    function successCallbackSubmitRandom(response) {
        var element = $(".form-check");
        result = "<h4>"+ response.optionAName + " : " + response.optionA +"</h4>" +
        "<p></p><h4>"+ response.optionBName + " : " + response.optionB + "</h4>"
        console.log(answer);
        $(element).replaceWith(result);
    }
    function errorCallbackSubmitRandom(request, status, error) {
        console.log("error")
        //window.location.replace("500error.html");
       //window.location.href="500error.html";
    }
    // perform an ajax http get request
    $.ajax({
        url: 'http://192.168.1.21:8080/randomlovers/topics/' + answer + "/" +id,
        type: 'POST',
        async: true,
        success: successCallbackSubmitRandom,
        error: errorCallbackSubmitRandom
    });
    var answer;
    function displayRadioValue() {
        var ele = document.getElementsByName('group2');
        for(i = 0; i < ele.length; i++) {
            if(ele[i].checked)
            answer = ele[i].value;
            console.log(answer)
        }
    }
}
function validateRandomComment() {

    console.log("Inside Validation")

    if (document.form3.comment.value == "") {
       alert("Don't be shy ! Speak for yourself !!")
        return false;
    }
}

    
   
function randomCommentPersist(){
    var commentAnswer;
    function successCallbackSubmitRandomComment(response) {
        var element = $("#commentdiv");

        result = "<div id='previousComment'><img id='userPicture' src='boneco.png' alt='User Picture'/>" +
        "<p id='usernameP'>" + "Anonymous" + "</p>" + "<p id='previousComment'>" + response[i].content +"</p>" +
        "</div>"
        $(element).append(result);
    }
    function errorCallbackSubmitRandomComment(request, status, error) {
        console.log("error")
    }
    // perform an ajax http get request
    $.ajax({
        url: 'http://192.168.1.21:8080/randomlovers/comments/add/' +id,
        type: 'POST',
        data: JSON.stringify({
            content: commentAnswer
            }),
        async: true,
        contentType: 'application/json',
        success: successCallbackSubmitRandomComment,
        error: errorCallbackSubmitRandomComment
        });

      
        commentAnswer = document.form3.comment.value 
        console.log("persistence");
        console.log(commentAnswer);
}