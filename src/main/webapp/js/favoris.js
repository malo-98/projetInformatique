
/*let getDestination=function(){
    return  destinationId=document.getElementsByClassName("span.destination_id").innerHTML;
};

let getUser=function(){
    return userId=document.getElementsByClassName("span.user_id").innerHTML;
};*/

let createFavoris=function(destination_id, user_id, destination){
    let createRequest= new XMLHttpRequest();
    createRequest.open("POST","ws/favoris/create", true );
    createRequest.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    createRequest.onload=function(){
        destination.querySelector("i.star").setAttribute("class", "fas fa-star selected star");
    }
    createRequest.send("destination_id="+destination_id+"&user_id="+user_id);

};

/*let deleteFavoris=function(destination_id, user_id){
    let deleteRequest= new XMLHttpRequest();
    deleteRequest.open("POST","ws/favoris/delete", true );
    deleteRequest.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    deleteRequest.send("destination_id="+destination_id+"&user_id="+user_id);
};*/

window.onload = function(){
    destinations= document.getElementsByClassName("destination");
    for (let destination of destinations ){
        star = destination.querySelector("i.star");
        star.onclick = function () {
            classes = star.classList;
                let destination_id=destination.querySelector("span.destination_id").innerHTML;
                let user_id=destination.querySelector("span.user_id").innerHTML;
                createFavoris(destination_id, user_id, destination);
                //classes.replace("unselected", "selected");

                /*let destination_id=destination.querySelector("span.destination_id").innerHTML;
                let user_id=destination.querySelector("span.user_id").innerHTML;
                deleteFavoris(destination_id, user_id);
                destination.querySelector("i.star").setAttribute("class", "fas fa-star unselected star");
                //classes.replace("selected", "unselected");*/
        };
    }


};