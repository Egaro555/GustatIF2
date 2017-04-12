/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.gustatif.web;

import com.insa.gustatif.dao.JpaUtil;
import com.insa.gustatif.metier.modele.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hugue
 */
public class ActionServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public ActionServlet() {
        JpaUtil.init();
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String[] uri = request.getRequestURI().split("/");

        ResultPrinter resultPrinter = new ResultPrinter(response.getWriter());

        boolean auth = true;

        if (uri.length == 3 && uri[1].equals("service")) {

            String serviceName = uri[2];

            if (serviceName.equals("connexionClientEmail")) {

            } else if (serviceName.equals("creerClient") && request.getParameter("nom") != null && request.getParameter("prenom") != null && request.getParameter("mail") != null && request.getParameter("adresse") != null) {

                creerClientAction action = new creerClientAction();
                action.execute(request);

                boolean result = action.getResult();

                resultPrinter.printBooleanResultAsJSON(result);

            } else if (serviceName.equals("deconnexionClient")) {

            } else if (serviceName.equals("searchRestaurants") && request.getParameter("research") != null) {

                searchRestaurantsAction action = new searchRestaurantsAction();
                action.execute(request);

                List<Restaurant> foundRestaurants = action.getFoundRestaurants();

                resultPrinter.printRestaurantListAsJSON(foundRestaurants);

            } else if (serviceName.equals("searchProduits") && request.getParameter("research") != null && request.getParameter("r") != null) {

                searchProduitsAction action = new searchProduitsAction();
                action.execute(request);

                List<Produit> foundProduits = action.getFoundProduits();

                resultPrinter.printProduitsListAsJSON(foundProduits);

            } else if (serviceName.equals("addProduitCommande") && request.getParameter("c") != null && request.getParameter("p") != null && request.getParameter("qte") != null) {
                
                addProduitCommandeAction action = new addProduitCommandeAction();
                action.execute(request);

                boolean result = action.getResult();

                resultPrinter.printBooleanResultAsJSON(result);

            } else if (serviceName.equals("removeProduitCommande")) {

            } else if (serviceName.equals("modifierQuantiteProduit")) {

            } else if (serviceName.equals("removeProduitCommande")) {

            } else if (serviceName.equals("traiterCommande")) {

            } else if (serviceName.equals("cloturerCommande")) {

            } else if (serviceName.equals("findAllLivreurs")) {

                findAllLivreursAction action = new findAllLivreursAction();
                action.execute(request);

                List<Livreur> allLivreurs = action.getAllLivreurs();

                if (allLivreurs == null) {
                    allLivreurs = new ArrayList();
                }

                resultPrinter.printLivreurListAsJSON(allLivreurs);

            } else if (serviceName.equals("findAllVelos")) {

                findAllVelosAction action = new findAllVelosAction();
                action.execute(request);

                List<Livreur> allVelos = action.getAllVelos();

                resultPrinter.printLivreurListAsJSON(allVelos);

            } else if (serviceName.equals("findAllDrones")) {

                findAllDronesAction action = new findAllDronesAction();
                action.execute(request);

                List<Livreur> allDrones = action.getAllDrones();

                resultPrinter.printLivreurListAsJSON(allDrones);

            } else if (serviceName.equals("findAllClients")) {

                findAllClientsAction action = new findAllClientsAction();
                action.execute(request);

                List<Client> allClients = action.getAllClients();

                resultPrinter.printClientListAsJSON(allClients);

            } else if (serviceName.equals("findAllRestaurants")) {

                findAllRestaurantsAction action = new findAllRestaurantsAction();
                action.execute(request);

                List<Restaurant> allRestaurants = action.getAllRestaurants();

                resultPrinter.printRestaurantListAsJSON(allRestaurants);

            } else if (serviceName.equals("getCommandesEnCours")) {

                getCommandesEnCoursAction action = new getCommandesEnCoursAction();
                action.execute(request);

                List<Commande> commandesEnCours = action.getCommandesEnCours();

                resultPrinter.printCommandeListAsJSON(commandesEnCours);

            } else if (serviceName.equals("creerLivreurDrone")  && request.getParameter("chargeMaxi") != null && request.getParameter("adr") != null && request.getParameter("vitesseMoy") != null) {

                creerLivreurDroneAction action = new creerLivreurDroneAction();
                action.execute(request);

                boolean result = true;

                resultPrinter.printBooleanResultAsJSON(result);

            } else if (serviceName.equals("creerLivreurVelo")  && request.getParameter("chargeMaxi") != null && request.getParameter("adr") != null && request.getParameter("nom") != null && request.getParameter("prenom") != null && request.getParameter("mail") != null) {

                creerLivreurVeloAction action = new creerLivreurVeloAction();
                action.execute(request);

                boolean result = true;

                resultPrinter.printBooleanResultAsJSON(result);

            } else if (serviceName.equals("creerRestaurant") && request.getParameter("denomination") != null && request.getParameter("description") != null && request.getParameter("adresse") != null) {
                
                creerRestaurantAction action = new creerRestaurantAction();
                action.execute(request);

                boolean result = true;

                resultPrinter.printBooleanResultAsJSON(result);                

            } else if (serviceName.equals("updateRestaurant") && request.getParameter("r") != null) {
                
                updateRestaurantAction action = new updateRestaurantAction();
                action.execute(request);

                boolean result = action.getResult();

                resultPrinter.printBooleanResultAsJSON(result); 

            } else if (serviceName.equals("deleteRestaurant") && request.getParameter("r") != null) {
                
                deleteRestaurantAction action = new deleteRestaurantAction(); //NOTE: bug ?
                action.execute(request);

                boolean result = action.getResult();

                resultPrinter.printBooleanResultAsJSON(result);                   

            } else if (serviceName.equals("creerProduit") && request.getParameter("denomination") != null && request.getParameter("description") != null && request.getParameter("poids") != null && request.getParameter("prix") != null) {
                
                creerProduitAction action = new creerProduitAction();
                action.execute(request);

                boolean result = true;

                resultPrinter.printBooleanResultAsJSON(result);                   

            } else if (serviceName.equals("connexionLivreur")) {

            } else if (serviceName.equals("traiterCommande")) {

            } else if (serviceName.equals("cloturerCommandeLivreur")) {

            } else {

                resultPrinter.printServiceInconnu();

            }

        } else {

            resultPrinter.printServiceInconnu();

        }

        resultPrinter.close();

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
