package com.example.easyfood42.controleur;

public class Administrateur extends Utilisateur {

    public Administrateur(Utilisateur utilisateur) {
        super(utilisateur.getIdU(), utilisateur.getMailU(),  utilisateur.getPseudoU(),utilisateur.getPasswd(), utilisateur.getNomU(), utilisateur.getPrenomU(), utilisateur.getNumAdrU(), utilisateur.getNomAdrU(), utilisateur.getCpU(), utilisateur.getVilleU(), utilisateur.getIdTU());
    }
}
