package modele;

/**
 * Membres
 */
public class Membre {

    enum abonnement{
        BASIC,
        PREMIUM,
        VIP
    }
    private static int id = 0;
    private String nom, preNom, email, telephone, adresse;

    public Membre(String nom, String preNom, String email, String telephone, String adresse){
        this.nom = nom;
        this.preNom = preNom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        id++;
    }
    
        //Getters
    public int getId(){
        return id;
    }

    public String getNom(){
        return nom;
    }

    public String getPreNom(){
        return preNom;
    }

    public String getEmail(){
        return email;
    }

    public String getTelephone(){
        return telephone;
    }
    public String getAdresse(){
        return adresse;
    }

        //setters
    public void setNom(String nom){
        this.nom = nom;
    }

    public void setPreNom(String preNom){
        this.preNom = preNom;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setTelephone(String telephone){
        this.telephone = telephone;
    }
    public void setAdresse(String adresse){
        this.adresse = adresse;
    }

}