
let deleteDestination = function (id_destination) {
    if (confirm("Are you sure you want to delete " + id_destination + "?")) {
        let deleteRequest = new XMLHttpRequest();
        deleteRequest.open("DELETE", "/destination?id=" + id_destination, true);
        deleteRequest.onload = function(){
            document.location.reload();
        };
        deleteRequest.send();
    }
};

