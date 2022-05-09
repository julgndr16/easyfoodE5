package com.example.easyfood42.controleur;

public class Contenir {
    private long idC;
    private long idP;
    private int qteComm;

    public Contenir(long idC,long idP,int qteComm){
        this.idC=idC;
        this.idP=idP;
        this.qteComm=qteComm;
    }

    public long getIdC() {
        return idC;
    }

    public void setIdC(long idC) {
        this.idC = idC;
    }

    public long getIdP() {
        return idP;
    }

    public void setIdP(long idP) {
        this.idP = idP;
    }

    public int getQteComm() {
        return qteComm;
    }

    public void setQteComm(int qteComm) {
        this.qteComm = qteComm;
    }
}
