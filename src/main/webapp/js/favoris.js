
let getDestination=function(element){
    let destination=element.querySelector("span.destination_id").innerHTML;
};

let getUser=function(element){
    let user=element.querySelector("span.user_id").innerHTML;
};

let createFavoris=function(){
    let destination_id=getDestination();
    let user_id=getUser();
    let createRequest= new XMLHttpRequest();
    createRequest.open("POST","/favoris/create", true );
    createRequest.send();
};

let deleteFavoris=function(){
    let destination_id=getDestination();
    let createRequest= new XMLHttpRequest();
    createRequest.open("POST","/favoris/delete", true );
    createRequest.send();
};

window.onload = function(){
    document.querySelectorAll(".destination").forEach(function(element) {
        star = element.querySelector("i.star");
        star.onclick = function () {
            if (star.class.contains("unselected")) {
                createFavoris(element);
                star.setAttribute("class", "star selected");
            }
            ;
            if (star.class.contains("selected")) {
                deletefavoris(element);
                star.setAttribute("class", "star unselected");
            }
            ;
        };
    });

};