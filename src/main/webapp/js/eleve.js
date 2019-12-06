let deleteEleve = function (eleve) {
    if (confirm("Are you sure you want to delete " + eleve.nom + " (" + eleve.id + ") ?")) {
        let deleteRequest = new XMLHttpRequest();
        deleteRequest.open("DELETE", "ws/listEleve/" + eleve.email, true);
        window.onload= function(){

        }
        deleteRequest.send();
    }
};