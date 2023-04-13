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

/**
 *
 * @author tsiky
 */
@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable {

    private int idSource;
    private int idDestination;
    private int montant;

    public int getIdSource() {
        return idSource;
    }

    public void setIdSource(int idSource) {
        this.idSource = idSource;
    }

    public int getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(int idDestination) {
        this.idDestination = idDestination;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    @EJB
    GestionnaireCompte compteBancaireEJB;

    /**
     * Creates a new instance of ListeComptes
     */
    public Transfert() {
    }

    /**
     * Retourne la liste des clients pour affichage dans une DataTable.
     */
    public String transfertArgent() {
        compteBancaireEJB.transferer(compteBancaireEJB.findById(idSource), compteBancaireEJB.findById(idDestination), montant);
        return "listeComptes";
    }
}
