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
    public static int id;
    private String nom, prenom, email, telephone, adresse;

    Membre(){
        
    }
    
}