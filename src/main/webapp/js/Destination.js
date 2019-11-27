var getDestinationDetails = function (destinationId) {
    let detailsRequest = new XMLHttpRequest();
    detailsRequest.open("GET", "destination?id_destination=" + destinationId, true);
    detailsRequest.responseType = "json";

    detailsRequest.onload = function () {
        let destination = this.response;
        console.log(destination);

        document.getElementById("id_destination").innerText = destination.id;
        document.getElementById("Nom").innerText = destination.nom;
        document.getElementById("Ville").innerText = destination.ville;
        document.getElementById("Pays").innerText = destination.pays;
        document.getElementById("description").innerText = destination.description;
        document.getElementById("nb_place").innerText = destination.place;
    };

    detailsRequest.send();
};