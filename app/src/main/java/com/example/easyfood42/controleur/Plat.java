package com.example.easyfood42.controleur;

public class Plat {
    private long idP;
    private String nomP;
    private double prixFournisseurP;
    private double prixClientP;
    private boolean platVisible;
    private String photoP;
    private String descriptionP;
    private long idR;
    private long idTP;

    public Plat(long idP,String nomP,double prixFournisseurP,double prixClientP,boolean platVisible,String photoP,String descriptionP,long idR,long idTP){
        this.idP=idP;
        this.nomP=nomP;
        this.prixFournisseurP=prixFournisseurP;
        this.prixClientP=prixClientP;
        this.platVisible=platVisible;
        this.photoP=photoP;
        this.descriptionP=descriptionP;
        this.idR=idR;
        this.idTP=idTP;
    }

    public long getIdP() {
        return idP;
    }

    public void setIdP(long idP) {
        this.idP = idP;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public double getPrixFournisseurP() {
        return prixFournisseurP;
    }

    public void setPrixFournisseurP(double prixFournisseurP) {
        this.prixFournisseurP = prixFournisseurP;
    }

    public double getPrixClientP() {
        return prixClientP;
    }

    public void setPrixClientP(double prixClientP) {
        this.prixClientP = prixClientP;
    }

    public boolean isPlatVisible() {
        return platVisible;
    }

    public void setPlatVisible(boolean platVisible) {
        this.platVisible = platVisible;
    }

    public String getPhotoP() {
        return photoP;
    }

    public void setPhotoP(String photoP) {
        this.photoP = photoP;
    }

    public String getDescriptionP() {
        return descriptionP;
    }

    public void setDescriptionP(String descriptionP) {
        this.descriptionP = descriptionP;
    }

    public long getIdR() {
        return idR;
    }

    public void setIdR(long idR) {
        this.idR = idR;
    }

    public long getIdTP() {
        return idTP;
    }

    public void setIdTP(long idTP) {
        this.idTP = idTP;
    }
}
