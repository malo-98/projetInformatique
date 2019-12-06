let listEleve = function () {
    let elevesRequest = new XMLHttpRequest();
    let url = "ws/listEleve";

    elevesRequest.open("GET", url, true);
    elevesRequest.responseType = "json";

    elevesRequest.onload = function () {
        let eleves = this.response;

    };

    elevesRequest.send();
};

let deleteEleve = function (eleve) {
    if (confirm("Are you sure you want to delete " + eleve.nom + " (" + eleve.id + ") ?")) {
        let deleteRequest = new XMLHttpRequest();
        deleteRequest.open("DELETE", "ws/listEleve/" + eleve.email, true);
        window.onload= function(){
            let  request= new XMLHttpRequest();
            request.open(GET, "/listEleve",true,null);
            request.send();
        }
        deleteRequest.send();
    }
};