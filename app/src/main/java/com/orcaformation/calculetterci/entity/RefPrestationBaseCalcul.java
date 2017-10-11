package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 10/10/2017.
 */

public class RefPrestationBaseCalcul {

    private String PrestationBaseCalculId;
    private String PrestationBaseCalculLibelle;

    public RefPrestationBaseCalcul() {
    }

    public RefPrestationBaseCalcul(String prestationBaseCalculId, String prestationBaseCalculLibelle) {
        PrestationBaseCalculId = prestationBaseCalculId;
        PrestationBaseCalculLibelle = prestationBaseCalculLibelle;
    }

    public String getPrestationBaseCalculId() {
        return PrestationBaseCalculId;
    }

    public void setPrestationBaseCalculId(String prestationBaseCalculId) {
        PrestationBaseCalculId = prestationBaseCalculId;
    }

    public String getPrestationBaseCalculLibelle() {
        return PrestationBaseCalculLibelle;
    }

    public void setPrestationBaseCalculLibelle(String prestationBaseCalculLibelle) {
        PrestationBaseCalculLibelle = prestationBaseCalculLibelle;
    }

    @Override
    public String toString() {
        return "RefPrestationBaseCalcul{" +
                "PrestationBaseCalculId='" + PrestationBaseCalculId + '\'' +
                ", PrestationBaseCalculLibelle='" + PrestationBaseCalculLibelle + '\'' +
                '}';
    }
}
