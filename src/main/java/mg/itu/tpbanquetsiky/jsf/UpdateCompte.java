/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquetsiky.jsf;

import jakarta.ejb.EJB;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import mg.itu.tpbanquetsiky.ejb.GestionnaireCompte;
import mg.itu.tpbanquetsiky.entities.CompteBancaire;
import mg.itu.tpbanquetsiky.util.Util;

/**
 *
 * @author tsiky
 */
@Named(value = "updateCompte")
@ViewScoped
public class UpdateCompte implements Serializable {

    private Integer id;
    
    private CompteBancaire compteBancaire;

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }

    @EJB
    GestionnaireCompte compteBancaireEJB;

    /**
     * Creates a new instance of ListeComptes
     */
    public UpdateCompte() {
    }

    public String action() {
        compteBancaireEJB.update(compteBancaire);
        
        Util.addFlashInfoMessage("Compte Modifié avec succès !");

        return "listeComptes?faces-redirect=true";
    }
    
    public String loadCompteBancaire() {
        this.compteBancaire = compteBancaireEJB.findById(id);
        return null;
    }
}
