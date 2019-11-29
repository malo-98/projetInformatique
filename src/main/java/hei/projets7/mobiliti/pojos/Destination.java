package hei.projets7.mobiliti.pojos;

public class Destination {
    Integer id;
    private String name;
    private String city;
    private String country;
    private String domaine;
    Integer nbre_place;
    private String description;

    public Destination (Integer id, String name, String city, String country,String des, String domaine , Integer nbre_place){
        this.id=id;
        this.name=name;
        this.city=city;
        this.country=country;
        this.description=des;
        this.domaine=domaine;
        this.nbre_place=nbre_place;
    }


    public int getId(){return this.id;}

    public String getName(){return this.name;}

    public String getCity(){return this.city;}

    public String getCountry(){return this.country;}

    public String getDomaine(){return this.domaine;}

    public String getDescription(){return this.description;}

    public int getPlace(){return this.nbre_place;}

    public void setId(int id) {this.id=id;}

    public void setName(String name){this.name=name;}

    public void setCity(String city){this.city=city;}

    public void setCountry(String country){this.country=country;}

    public void setDomaine(String domaine){this.domaine=domaine;}

    public void setDescription(String description){this.description=description;}

    public void setNbre_place(int place){this.nbre_place=place;}

}
