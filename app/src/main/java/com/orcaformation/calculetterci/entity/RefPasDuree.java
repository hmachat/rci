package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 11/10/2017.
 */

public class RefPasDuree {

    private String PasDureeId;
    private String PasDureeValeur;

    public RefPasDuree() {
    }

    public RefPasDuree(String pasDureeId, String pasDureeValeur) {
        PasDureeId = pasDureeId;
        PasDureeValeur = pasDureeValeur;
    }

    public String getPasDureeId() {
        return PasDureeId;
    }

    public void setPasDureeId(String pasDureeId) {
        PasDureeId = pasDureeId;
    }

    public String getPasDureeValeur() {
        return PasDureeValeur;
    }

    public void setPasDureeValeur(String pasDureeValeur) {
        PasDureeValeur = pasDureeValeur;
    }

    @Override
    public String toString() {
        return "RefPasDuree{" +
                "PasDureeId='" + PasDureeId + '\'' +
                ", PasDureeValeur='" + PasDureeValeur + '\'' +
                '}';
    }
}
