window.onload=function(){
    listEleve();
}

let listEleve = function () {
    let elevesRequest = new XMLHttpRequest();
    let url = "ws/eleve";

    elevesRequest.open("GET", url, true);
    elevesRequest.responseType = "json";

    elevesRequest.onload = function () {
        let eleves = this.response;

        let bodyElement=document.getElementById("listEleve");
        bodyElement.innerHTML = "";
        eleves.forEach(function(eleve){
            let newLine=createEleveLine(eleve.id_eleve, eleve.nom, eleve.prenom, eleve.email,eleve.domaine,eleve.password);
            bodyElement.appendChild(newLine);
            console.log(eleve.id_eleve);
            let deleteCell=document.getElementsByTagName('tr')[eleves.indexOf(eleve)+1].cells[4];
            deleteCell.onclick = function(){
                deleteEleve(eleve.email);
            }
        });
    };

    elevesRequest.send();
};

window.onload=function(){
    listEleve();
};

function createEleveLine(id_eleve,nom,prenom,email,domaine,password){

    var Line = document.createElement("tr");
    var eleveNomCell = document.createElement("td");
    var elevePrenomCell = document.createElement("td");
    var eleveDomaineCell=document.createElement("td");
    var eleveEmailCell = document.createElement("td");
    var deleteCell=document.createElement("td");

    eleveNomCell.innerHTML=nom;
    elevePrenomCell.innerHTML=prenom;
    eleveEmailCell.innerHTML=email;
    eleveDomaineCell.innerHTML=domaine;
    deleteCell.innerHTML="<i class=\"fa fa-remove\" style=\"font-size:24px\"></i>";


    Line.appendChild(eleveNomCell);
    Line.appendChild(elevePrenomCell);
    Line.appendChild(eleveDomaineCell);
    Line.appendChild(eleveEmailCell);
    Line.appendChild(deleteCell);

    return Line;
}

let deleteEleve = function (email) {
    if (confirm("Are you sure you want to delete " + email)) {
        let deleteRequest = new XMLHttpRequest();
        deleteRequest.open("DELETE", "ws/eleve/" + email, true);
        deleteRequest.onload= function(){
            listEleve();
        }
        deleteRequest.send();
    }
};
