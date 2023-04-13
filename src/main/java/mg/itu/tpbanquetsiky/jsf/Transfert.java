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
        boolean erreur = false;
        CompteBancaire source = compteBancaireEJB.findById(idSource);
        CompteBancaire destination = compteBancaireEJB.findById(idDestination);
        if (source == null) {
            // Message d'erreur associé au composant source ; form:source est l'id client
            // si l'id du formulaire est "form" et l'id du champ de saisie de l'id de la source est "source"
            // dans la page JSF qui lance le transfert.
            Util.messageErreur("Aucun compte avec cet id !", "Aucun compte avec cet id !", "form:source");
            erreur = true;
        } else {
            if (source.getSolde() < montant) {
                Util.messageErreur("Solde insuffisant !", "Solde insuffisant !", "form:source");
                erreur = true;
            }
        }
        if (erreur) {
            // en cas d'erreur, rester sur la même page
            return null;
        }

        compteBancaireEJB.transferer(source, destination, montant);

        Util.addFlashInfoMessage("Transfert d'argent effectué avec succès ! Info: Source= "+ source.getNom() + ", Destination= "+ destination.getNom() +", Montant=" + montant);

        return "listeComptes?faces-redirect=true";
    }
}
