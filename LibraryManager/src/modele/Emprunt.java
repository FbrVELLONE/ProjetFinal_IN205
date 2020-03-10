package modele;

import java.time.LocalDate;
/**
 * Emprunt
 */
public class Emprunt {
    private static int id = 0;
    private int idMembre, idLivre;
    private LocalDate dateEmprunt, dateRetour;

    public Emprunt(int idMembre, int idLivre, LocalDate dateEmprunt, LocalDate dateRetour){
        this.idLivre = idLivre;
        this.idMembre = idMembre;
        this.dateEmprunt = dateEmprunt;
        this.dateRetour = dateRetour;
        id++;
    }
    
}