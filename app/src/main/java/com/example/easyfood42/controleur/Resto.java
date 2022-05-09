package com.example.easyfood42.controleur;

public class Resto {
    private long idR;
    private String nomR;
    private String numAdrR;
    private String nomAdrR;
    private String cpR;
    private String villeR;
    private long idU;

    public Resto(long idR, String nomR, String numAdrR, String nomAdrR, String cpR, String villeR, long idU){
        this.idR=idR;
        this.nomR=nomR;
        this.numAdrR=numAdrR;
        this.nomAdrR=nomAdrR;
        this.cpR=cpR;
        this.villeR=villeR;
        this.idU=idU;
    }

    public long getIdR() {
        return idR;
    }

    public void setIdR(long idR) {
        this.idR = idR;
    }

    public String getNomR() {
        return nomR;
    }

    public void setNomR(String nomR) {
        this.nomR = nomR;
    }

    public String getNumAdrR() {
        return numAdrR;
    }

    public void setNumAdrR(String numAdrR) {
        this.numAdrR = numAdrR;
    }

    public String getNomAdrR() {
        return nomAdrR;
    }

    public void setNomAdrR(String nomAdrR) {
        this.nomAdrR = nomAdrR;
    }

    public String getCpR() {
        return cpR;
    }

    public void setCpR(String cpR) {
        this.cpR = cpR;
    }

    public String getVilleR() {
        return villeR;
    }

    public void setVilleR(String villeR) {
        this.villeR = villeR;
    }

    public long getIdU() {
        return idU;
    }

    public void setIdU(long idU) {
        this.idU = idU;
    }
}
