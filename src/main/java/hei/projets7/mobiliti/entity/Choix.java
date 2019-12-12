package hei.projets7.mobiliti.entity;

public class Choix {

    private Integer id_choix;
    private Integer id_eleve;
    private Integer id_destination;

    public Choix(Integer choix, Integer eleve, Integer destination){
        this.id_choix = choix;
        this.id_eleve=eleve;
        this.id_destination=destination;

    }

    public Integer getId_destination() {
        return id_destination;
    }

    public Integer getId_eleve() {
        return id_eleve;
    }

    public Integer getId_choix() {
        return id_choix;
    }

    public void setId_choix(Integer id_choix) {
        this.id_choix = id_choix;
    }
}
