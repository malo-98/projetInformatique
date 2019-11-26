package hei.projets7.mobiliti.pojos;

public class Favoris {
    Integer id;
    private String name;
    private String city;
    private String country;
    private String domaine;
    Integer nbre_place;
    private String description;
    Integer id_eleve;

    public Favoris (Integer id, String name, String city, String country,String des, String domaine , Integer nbre_place, Integer id_eleve){
        this.id=id;
        this.name=name;
        this.city=city;
        this.country=country;
        this.description=des;
        this.domaine=domaine;
        this.nbre_place=nbre_place;
        this.id_eleve=id_eleve;
    }

    public int getId(){return this.id;}

    public String getName(){return this.name;}

    public String getCity(){return this.city;}

    public String getCountry(){return this.country;}

    public String getDomaine(){return this.domaine;}

    public String getDescription(){return this.description;}

    public int getPlace(){return this.nbre_place;}

    public int getIdEleve(){return  this.id_eleve;}

    public void setId(int id) {this.id=id;}

}
