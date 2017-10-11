package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 03/10/2017.
 */

public class Url {

    private DateFinValidite dateFinValidite;
    private String marques;
    private String prestations;
    private String packs;
    private String credit;
    private String loa;
    private String leasing;

    public Url() {
    }

    public Url(DateFinValidite dateFinValidite, String marques, String prestations, String packs, String credit, String loa, String leasing) {
        this.dateFinValidite = dateFinValidite;
        this.marques = marques;
        this.prestations = prestations;
        this.packs = packs;
        this.credit = credit;
        this.loa = loa;
        this.leasing = leasing;
    }

    public DateFinValidite getDateFinValidite() {
        return dateFinValidite;
    }

    public void setDateFinValidite(DateFinValidite dateFinValidite) {
        this.dateFinValidite = dateFinValidite;
    }

    public String getMarques() {
        return marques;
    }

    public void setMarques(String marques) {
        this.marques = marques;
    }

    public String getPrestations() {
        return prestations;
    }

    public void setPrestations(String prestations) {
        this.prestations = prestations;
    }

    public String getPacks() {
        return packs;
    }

    public void setPacks(String packs) {
        this.packs = packs;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getLoa() {
        return loa;
    }

    public void setLoa(String loa) {
        this.loa = loa;
    }

    public String getLeasing() {
        return leasing;
    }

    public void setLeasing(String leasing) {
        this.leasing = leasing;
    }

    @Override
    public String toString() {
        return "Url{" +
                "dateFinValidite=" + dateFinValidite +
                ", marques='" + marques + '\'' +
                ", prestations='" + prestations + '\'' +
                ", packs='" + packs + '\'' +
                ", credit='" + credit + '\'' +
                ", loa='" + loa + '\'' +
                ", leasing='" + leasing + '\'' +
                '}';
    }
}
