package com.example.easyfood42.controleur;

public class TypePlat {
    private long idTP;
    private String libelleTP;

    public TypePlat(long idTP, String libelleTP){
        this.idTP=idTP;
        this.libelleTP=libelleTP;
    }

    public long getIdTP() {
        return idTP;
    }

    public void setIdTP(long idTP) {
        this.idTP = idTP;
    }

    public String getLibelleTP() {
        return libelleTP;
    }

    public void setLibelleTP(String libelleTP) {
        this.libelleTP = libelleTP;
    }
}
