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
import mg.itu.tpbanquetsiky.entities.OperationBancaire;

/**
 *
 * @author tsiky
 */
@Named(value = "operations")
@ViewScoped
public class Operations implements Serializable {

    private List<OperationBancaire> operationBancaireList;

    private int id;

    @EJB
    GestionnaireCompte compteBancaireEJB;

    /**
     * Creates a new instance of ListeComptes
     */
    public Operations() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OperationBancaire> getOperationsBancaires() {
        return compteBancaireEJB.findById(id).getOperations();
    }

}
