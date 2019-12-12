let deleteFavoris=function(destination_id, user_id){
    if (confirm("Etes vous sûre de vouloir enlever la destination de vos favoris ?")) {
        let deleteRequest = new XMLHttpRequest();
        deleteRequest.open("POST", "ws/favoris/delete", true);
        deleteRequest.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        deleteRequest.onload=function(){
            window.location.reload();
        }
        deleteRequest.send("destination_id=" + destination_id + "&user_id=" + user_id);
    };
};

window.onload = function(){
    destinations= document.getElementsByClassName("destination");
    for (let destination of destinations ){
        star = destination.querySelector("i.star");
        star.onclick = function () {
            classes = star.classList;
            let destination_id=destination.querySelector("span.destination_id").innerHTML;
                let user_id=destination.querySelector("span.user_id").innerHTML;
                deleteFavoris(destination_id, user_id);
                //destination.querySelector("i.star").setAttribute("class", "fas fa-star unselected star");
                //classes.replace("selected", "unselected");*/
        };
    }


};