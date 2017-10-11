package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 11/10/2017.
 */

public class RefTypeTva {

    private String TypeTvaId;
    private String TypeTvaLibelle;
    private String TvaStatique;

    public RefTypeTva() {
    }

    public RefTypeTva(String typeTvaId, String typeTvaLibelle, String tvaStatique) {
        TypeTvaId = typeTvaId;
        TypeTvaLibelle = typeTvaLibelle;
        TvaStatique = tvaStatique;
    }

    public String getTypeTvaId() {
        return TypeTvaId;
    }

    public void setTypeTvaId(String typeTvaId) {
        TypeTvaId = typeTvaId;
    }

    public String getTypeTvaLibelle() {
        return TypeTvaLibelle;
    }

    public void setTypeTvaLibelle(String typeTvaLibelle) {
        TypeTvaLibelle = typeTvaLibelle;
    }

    public String getTvaStatique() {
        return TvaStatique;
    }

    public void setTvaStatique(String tvaStatique) {
        TvaStatique = tvaStatique;
    }

    @Override
    public String toString() {
        return "RefTypeTva{" +
                "TypeTvaId='" + TypeTvaId + '\'' +
                ", TypeTvaLibelle='" + TypeTvaLibelle + '\'' +
                ", TvaStatique='" + TvaStatique + '\'' +
                '}';
    }
}
