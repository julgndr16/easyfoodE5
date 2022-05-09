package com.example.easyfood42.controleur;

public class Evaluer {
    private String commentaire;
    private boolean commentaireVisible;
    private boolean commentaireModere;
    private int respectRecette;
    private int esthetiquePlat;
    private int cout;
    private int qualiteNourriture;
    private long idR;
    private long idU;

    public Evaluer(String commentaire, int respectRecette, int esthetiquePlat, int cout, int qualiteNourriture,long idR,long idU) {
        this.commentaire = commentaire;
        this.commentaireVisible = false;
        this.commentaireModere = false;
        this.respectRecette = respectRecette;
        this.esthetiquePlat = esthetiquePlat;
        this.cout = cout;
        this.qualiteNourriture = qualiteNourriture;
        this.idR=idR;
        this.idU=idU;
    }
    public Evaluer(String commentaire, int respectRecette, int esthetiquePlat, int cout, int qualiteNourriture) {
        this.commentaire = commentaire;
        this.commentaireVisible = false;
        this.commentaireModere = false;
        this.respectRecette = respectRecette;
        this.esthetiquePlat = esthetiquePlat;
        this.cout = cout;
        this.qualiteNourriture = qualiteNourriture;
    }


    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public boolean isCommentaireVisible() {
        return commentaireVisible;
    }

    public void setCommentaireVisible(boolean commentaireVisible) {
        this.commentaireVisible = commentaireVisible;
    }

    public boolean isCommentaireModere() {
        return commentaireModere;
    }

    public void setCommentaireModere(boolean commentaireModere) {
        this.commentaireModere = commentaireModere;
    }

    public int getRespectRecette() {
        return respectRecette;
    }

    public void setRespectRecette(int respectRecette) {
        this.respectRecette = respectRecette;
    }

    public int getEsthetiquePlat() {
        return esthetiquePlat;
    }

    public void setEsthetiquePlat(int esthetiquePlat) {
        this.esthetiquePlat = esthetiquePlat;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public int getQualiteNourriture() {
        return qualiteNourriture;
    }

    public void setQualiteNourriture(int qualiteNourriture) {
        this.qualiteNourriture = qualiteNourriture;
    }

    public long getIdR() {
        return idR;
    }

    public void setIdR(long idR) {
        this.idR = idR;
    }

    public long getIdU() {
        return idU;
    }

    public void setIdU(long idU) {
        this.idU = idU;
    }
}
