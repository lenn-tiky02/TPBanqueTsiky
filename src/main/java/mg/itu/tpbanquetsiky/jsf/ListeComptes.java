/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquetsiky.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import mg.itu.tpbanquetsiky.ejb.GestionnaireCompte;
import mg.itu.tpbanquetsiky.entities.CompteBancaire;
import mg.itu.tpbanquetsiky.util.Util;

/**
 *
 * @author tsiky
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {

    private List<CompteBancaire> compteBancaireList;

    @EJB
    GestionnaireCompte compteBancaireEJB;

    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }

    /**
     * Retourne la liste des clients pour affichage dans une DataTable.
     */
    public List<CompteBancaire> getAllComptes() {
        if (compteBancaireList == null) {
            compteBancaireList = compteBancaireEJB.getAllComptes();
        }
        return compteBancaireList;
    }

    public String supprimerCompte(CompteBancaire compteBancaire) {
        compteBancaireEJB.supprimerCompte(compteBancaire);
        Util.addFlashInfoMessage("Compte de " + compteBancaire.getNom() + " supprimé");
        return "listeComptes?faces-redirect=true";
    }
    
    public String updateAction(int id) {
        return "updateCompte?id="+id+"&amp;faces-redirect=true";
    }
}
