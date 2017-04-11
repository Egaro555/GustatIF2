/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.gustatif.web;

import com.insa.gustatif.metier.modele.Produit;
import com.insa.gustatif.metier.modele.Restaurant;
import com.insa.gustatif.metier.service.ServiceMetier;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Benjamin
 */
class searchProduitsAction implements Action {

    List<Produit> foundProduits;

    @Override
    public void execute(HttpServletRequest request) {

        Restaurant r = null;

        for (Restaurant restaurant : ServiceMetier.findAllRestaurants()) {

            if (restaurant.getId() == Long.parseLong(request.getParameter("r"))) {
                r = restaurant;
            }

        }

        if (r != null) {
            foundProduits = ServiceMetier.searchProduits(request.getParameter("research"), r);
        }
    }

    public List<Produit> getFoundProduits() {
        return foundProduits;
    }

}
