package hei.projets7.mobiliti.pojos;

public class Favoris {
    Integer id;
    Integer id_eleve;
    Integer id_destination;

    public Favoris (Integer id, Integer id_eleve, Integer id_destination){
        this.id=id;
        this.id_eleve=id_eleve;
        this.id_destination=id_destination;
    }

    public int getId(){return this.id;}
    public int getIdEleve(){return this.id_eleve;}
    public int getIdDestination(){return this.id_destination;}
    public void setId(Integer id_choix) {
        this.id = id_choix;
    }

}
