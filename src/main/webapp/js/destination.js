
let deleteDestination = function (destination_id) {
    if (confirm("Are you sure you want to delete " + destination_id + "?")) {
        let deleteRequest = new XMLHttpRequest();
        deleteRequest.open("POST", "ws/destination/delete", true);
        deleteRequest.setRequestHeader("content-type", "application/x-www-form-urlencoded");
        deleteRequest.onload = function(){
            document.location.reload();
        };
        deleteRequest.send("id_destination="+destination_id);
    }
};

