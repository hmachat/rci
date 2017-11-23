package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 03/10/2017.
 */

public class DateFinValidite {

    private String annee;
    private String mois;
    private String jour;

    public DateFinValidite() {
    }

    public DateFinValidite(String annee, String mois, String jour) {
        this.annee = annee;
        this.mois = mois;
        this.jour = jour;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    @Override
    public String toString() {
        return "DateFinValidite{" +
                "annee='" + annee + '\'' +
                ", mois='" + mois + '\'' +
                ", jour='" + jour + '\'' +
                '}';
    }
}
