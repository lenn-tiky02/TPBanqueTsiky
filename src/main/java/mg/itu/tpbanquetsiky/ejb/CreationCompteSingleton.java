/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.itu.tpbanquetsiky.ejb;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import mg.itu.tpbanquetsiky.entities.CompteBancaire;

/**
 *
 * @author tsiky
 */
@Singleton
@Startup
public class CreationCompteSingleton {

    @EJB
    GestionnaireCompte compteBancaireEJB;

    @PostConstruct
    public void init() {
        if (compteBancaireEJB.nbComptes() == 0) {
            compteBancaireEJB.creerCompte(new CompteBancaire("John Lennon", 150000));
            compteBancaireEJB.creerCompte(new CompteBancaire("Paul McCartney", 950000));
            compteBancaireEJB.creerCompte(new CompteBancaire("Ringo Starr", 20000));
            compteBancaireEJB.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        }
    }
}
