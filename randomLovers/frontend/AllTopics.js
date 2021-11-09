window.onscroll = function() {
  myFunction()
};

$(document).ready(function(){
  start();
 });


var start = function(){

  function successCallback(response) {
    console.log(response);
    populateDiv(response);
}
function errorCallback(request, status, error) {
   alert("error");
}
// perform an ajax http get request
$.ajax({
    url: 'http://192.168.1.21:8080/randomlovers/topics/all/',
    async: true,
    success: successCallback,
    error: errorCallback
});
}

var populateDiv = function(response){

    var element = $('#parentHotTopic1');

    var result= "";

    for(var i = 0; i < response.length; i++) {
    
      result += "<div id='childHotTopic" + i + "'> <a href='TopicPage.html?id=" + response[i].id + "'> <img id='veryHotPicture' src=" + response[i].imagePath + " alt='" + response[i].name + "'/>"
      + "<h4 id='imageLegend'>"
      + response[i].name + "</h4> </a> </div>";
    };
    console.log(result);
    $(element).append(result);
} 

var header = document.getElementById("header1");

var sticky = header.offsetTop;

function myFunction() {
  if (window.pageYOffset > sticky) {
    header.classList.add("sticky");
  } else {
    header.classList.remove("sticky");
  }
}
