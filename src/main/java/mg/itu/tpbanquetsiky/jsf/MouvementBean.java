/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.itu.tpbanquetsiky.jsf;

import jakarta.ejb.EJB;
import jakarta.ejb.EJBException;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.OptimisticLockException;
import jakarta.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import mg.itu.tpbanquetsiky.ejb.GestionnaireCompte;
import mg.itu.tpbanquetsiky.entities.CompteBancaire;
import mg.itu.tpbanquetsiky.util.Util;

/**
 *
 * @author tsiky
 */
@Named(value = "mouvementBean")
@ViewScoped
public class MouvementBean implements Serializable {

    private int idCompteBancaire;
    private CompteBancaire compte;
    private String typeMouvement;

    @PositiveOrZero
    private int montant;

    public String getTypeMouvement() {
        return typeMouvement;
    }

    public void setTypeMouvement(String typeMouvement) {
        this.typeMouvement = typeMouvement;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    @EJB
    private GestionnaireCompte compteManager;

    public int getIdCompteBancaire() {
        return idCompteBancaire;
    }

    public void setIdCompteBancaire(int idCompteBancaire) {
        this.idCompteBancaire = idCompteBancaire;
    }

    /**
     * Retourne les détails du client courant (contenu dans l'attribut customer
     * de cette classe).
     */
    public CompteBancaire getCompte() {
        return compte;
    }

    /**
     * Action handler - met à jour dans la base de données les données du client
     * contenu dans la variable d'instance customer.
     *
     * @return la prochaine page à afficher, celle qui affiche la liste des
     * clients.
     */
    public String update() {
        // Modifie la base de données.
        // Il faut affecter à customer (sera expliqué dans le cours).
        compte = compteManager.update(compte);
        return "customerList";
    }

    public void loadCompteBancaire() {
        this.compte = compteManager.findById(idCompteBancaire);
    }

    public String enregistrerMouvement() {
        try {
            if ("ajout".equals(typeMouvement)) {
                compte.setSolde(compte.getSolde() + montant);
                compteManager.update(compte);

                Util.addFlashInfoMessage("Dépot d'argent effectué avec succès ! Info: Compte de= " + compte.getNom() + ", Montant=" + montant);

            } else {
                compte.setSolde(compte.getSolde() - montant);
                compteManager.update(compte);
                Util.addFlashInfoMessage("Retrait d'argent effectué avec succès ! Info: Compte de= " + compte.getNom() + ", Montant=" + montant);
            }

            return "listeComptes?faces-redirect=true";
        } catch (EJBException ex) {
            Throwable cause = ex.getCause();
            if (cause != null) {
                if (cause instanceof OptimisticLockException) {
                    Util.messageErreur("Le compte de " + compte.getNom()
                            + " a été modifié ou supprimé par un autre utilisateur !");
                } else { // Afficher le message de ex si la cause n'est pas une OptimisticLockException
                    Util.messageErreur(cause.getMessage());
                }
            } else { // Pas de cause attachée à l'EJBException
                Util.messageErreur(ex.getMessage());
            }
            return null; // pour rester sur la page s'il y a une exception
        }
    }
}
