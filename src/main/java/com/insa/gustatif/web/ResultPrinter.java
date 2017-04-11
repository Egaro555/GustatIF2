/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.insa.gustatif.web;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.insa.gustatif.metier.modele.Client;
import com.insa.gustatif.metier.modele.Livreur;
import com.insa.gustatif.metier.modele.Produit;
import com.insa.gustatif.metier.modele.Restaurant;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author Benjamin
 */
class ResultPrinter {

    private PrintWriter out;

    ResultPrinter(PrintWriter writer) {
        out = writer;
    }

    void printRestaurantListAsJSON(List<Restaurant> restaurants) {

        JsonArray jsonListe = new JsonArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for (Restaurant r : restaurants) {
            JsonObject jsonRestaurant = new JsonObject();

            jsonRestaurant.addProperty("id", r.getId());
            jsonRestaurant.addProperty("denomination", r.getDenomination());
            jsonRestaurant.addProperty("description", r.getDescription());
            jsonRestaurant.addProperty("adresse", r.getAdresse());
            jsonRestaurant.addProperty("latitude", r.getLatitude());
            jsonRestaurant.addProperty("longitude", r.getLongitude());

            jsonListe.add(jsonRestaurant);
        }

        JsonObject container = new JsonObject();
        container.add("restaurants", jsonListe);
        String json = gson.toJson(container);
        out.println(json);
    }

    void printServiceInconnu() {

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Erreur</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Service inconnu</h1>");
        out.println("</body>");
        out.println("</html>");

    }

    void close() {

        out.close();

    }

    void printLivreurListAsJSON(List<Livreur> livreurs) {

        JsonArray jsonListe = new JsonArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();

        for (Livreur l : livreurs) {
            JsonObject jsonLivreur = new JsonObject();

            jsonLivreur.addProperty("id", l.getIdLivreur());
            jsonLivreur.addProperty("addresse", l.getAdresse());
            jsonLivreur.addProperty("chargeMaxi", l.getChargeMaxi());
            jsonLivreur.addProperty("cmdeEnCours", (l.getCmdeEnCours() != null) ? l.getCmdeEnCours().getNumCommande() : null);
            jsonLivreur.addProperty("latitude", l.getLatitude());
            jsonLivreur.addProperty("longitude", l.getLongitude());

            jsonListe.add(jsonLivreur);
        }

        JsonObject container = new JsonObject();
        container.add("livreurs", jsonListe);
        String json = gson.toJson(container);
        out.println(json);

    }

    void printClientListAsJSON(List<Client> clients) {

        JsonArray jsonListe = new JsonArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for (Client c : clients) {
            JsonObject jsonClient = new JsonObject();

            jsonClient.addProperty("id", c.getId());
            jsonClient.addProperty("addresse", c.getAdresse());
            jsonClient.addProperty("mail", c.getMail());
            jsonClient.addProperty("nom", c.getNom());
            jsonClient.addProperty("prenom", c.getPrenom());
            jsonClient.addProperty("latitude", c.getLatitude());
            jsonClient.addProperty("longitude", c.getLongitude());

            jsonListe.add(jsonClient);
        }

        JsonObject container = new JsonObject();
        container.add("clients", jsonListe);
        String json = gson.toJson(container);
        out.println(json);

    }

    void printDebug(String foo) {
        out.println(foo);
    }

    void printProduitsListAsJSON(List<Produit> produits) {

        JsonArray jsonListe = new JsonArray();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        for (Produit p : produits) {
            JsonObject jsonProduit = new JsonObject();

            jsonProduit.addProperty("id", p.getId());
            jsonProduit.addProperty("denomination", p.getDenomination());
            jsonProduit.addProperty("poids", p.getPoids());
            jsonProduit.addProperty("prix", p.getPrix());
            jsonProduit.addProperty("description", p.getDescription());

            jsonListe.add(jsonProduit);
        }

        JsonObject container = new JsonObject();
        container.add("produits", jsonListe);
        String json = gson.toJson(container);
        out.println(json);

    }

}
