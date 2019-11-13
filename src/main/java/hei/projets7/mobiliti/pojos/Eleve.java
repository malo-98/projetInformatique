package hei.projets7.mobiliti.pojos;

public class Eleve {

    private String nom;
    private String prenom;
    private String email;
    private String password;
    private String domaine;

    public Eleve(String nom, String prenom, String email, String password, String domaine){
        this.nom=nom;
        this.prenom=prenom;
        this.email=email;
        this.password=password;
        this.domaine=domaine;

    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }
}