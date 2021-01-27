window.androidObj = function AndroidClass(){};

var textContainer = document.createElement("p");
var nativeText = document.createTextNode("Android Text");
textContainer.appendChild(nativeText);

document.body.appendChild(textContainer);

function updateFromAndroid(message){
    nativeText.nodeValue = message;
}
