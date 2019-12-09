
/*let getDestination=function(){
    return  destinationId=document.getElementsByClassName("span.destination_id").innerHTML;
};

let getUser=function(){
    return userId=document.getElementsByClassName("span.user_id").innerHTML;
};*/

let createFavoris=function(destination_id, user_id){
    let createRequest= new XMLHttpRequest();
    createRequest.open("POST","ws/favoris/create", true );
    createRequest.setRequestHeader("content-type", "application/x-www-form-urlencoded")
    createRequest.send("destination_id="+destination_id+"&user_id="+user_id);
};

let deleteFavoris=function(destination_id, user_id){
    let createRequest= new XMLHttpRequest();
    createRequest.open("DELETE","ws/favoris/delete", true );
    createRequest.setRequestHeader("content-type", "application/x-www-form-urlencoded");
    createRequest.send("destination_id="+destination_id+"$user_id="+user_id);
};

window.onload = function(){
    var destinations= document.getElementsByClassName("destination");
    for (let destination of destinations ){
        star = destination.querySelector("i.star");
        star.onclick = function () {
            classes = star.classList;
            if (classes.contains("unselected")) {
                let destination_id=destination.querySelector("span.destination_id").innerHTML;
                let user_id=destination.querySelector("span.user_id").innerHTML;
                createFavoris(destination_id, user_id);
                classes.replace("unselected", "selected");
            }
<<<<<<< HEAD

            if (classes.contains("selected")) {
                deleteFavoris(element);
                classes.replace("selected", "unselected")
            }

        };
=======
            else if (classes.contains("selected")) {
                let destination_id=destination.querySelector("span.destination_id").innerHTML;
                let user_id=destination.querySelector("span.user_id").innerHTML;
                deleteFavoris(destination_id, user_id);
                classes.replace("selected", "unselected");
            }
        };
    };
>>>>>>> 1448b16334b137730fe648dd23b1e341f937e899

            document.getElementById("delete").onclick = function(){
                let destination_id= element.querySelector("span.destination_id").innerHTML;
                deleteDestination(destination_id);

        };
    })
};