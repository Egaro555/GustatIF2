/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.gustatif.web;

import com.insa.gustatif.metier.modele.Commande;
import com.insa.gustatif.metier.modele.Produit;
import com.insa.gustatif.metier.modele.ProduitCommande;
import com.insa.gustatif.metier.modele.Restaurant;
import com.insa.gustatif.metier.service.ServiceMetier;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Benjamin
 */
class addProduitCommandeAction implements Action {

    boolean result;

    @Override
    public void execute(HttpServletRequest request) {

        result = false;
        
        Commande c = null;

        for (Commande commande : ServiceMetier.getCommandesEnCours()) {

            if (commande.getNumCommande() == Long.parseLong(request.getParameter("c"))) {
                c = commande;
            }
        }

        Produit p = null;

        for (Restaurant r : ServiceMetier.findAllRestaurants()) {
            for (Produit produit : ServiceMetier.searchProduits("", r)) {

                if (produit.getId() == Long.parseLong(request.getParameter("p"))) {
                    p = produit;
                    break;
                }
            }

            if (p != null) {
                break;
            }
        }

        if (p != null) {
            
            ProduitCommande pc = new ProduitCommande(p, Integer.parseInt(request.getParameter("qte")));

            ServiceMetier.addProduitCommande(c, pc);

            result = true;
        }

    }

    public boolean getResult() {
        return result;
    }
    
    

}
