
let getDestination=function(element){
    let destination=element.querySelector("span.destination_id").innerHTML;
};

let getUser=function(element){
    let user=element.querySelector("span.user_id").innerHTML;
};

let createFavoris=function(element){
    let destination_id=getDestination(element);
    let user_id=getUser(element);
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
    document.getElementsByClassName("destination").forEach(function(element){
        star = element.querySelector("i.star");
        star.onclick = function () {
            classes = star.classList;
            if (classes.contains("unselected")) {
                createFavoris(element);
                classes.replace("unselected","selected")
            }

            if (classes.contains("selected")) {
                deleteFavoris(element);
                classes.replace("selected", "unselected")
            }

        };

            document.getElementById("delete").onclick = function(){
                let destination_id= element.querySelector("span.destination_id").innerHTML;
                deleteDestination(destination_id);

        };
    })
};