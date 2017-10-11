package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 11/10/2017.
 */

public class RefTypeBareme {

    private String TypeBaremeId;
    private String TypeBaremeLibelle;
    private String BaremeIsPromo;

    public RefTypeBareme() {
    }

    public RefTypeBareme(String typeBaremeId, String typeBaremeLibelle, String baremeIsPromo) {
        TypeBaremeId = typeBaremeId;
        TypeBaremeLibelle = typeBaremeLibelle;
        BaremeIsPromo = baremeIsPromo;
    }

    public String getTypeBaremeId() {
        return TypeBaremeId;
    }

    public void setTypeBaremeId(String typeBaremeId) {
        TypeBaremeId = typeBaremeId;
    }

    public String getTypeBaremeLibelle() {
        return TypeBaremeLibelle;
    }

    public void setTypeBaremeLibelle(String typeBaremeLibelle) {
        TypeBaremeLibelle = typeBaremeLibelle;
    }

    public String getBaremeIsPromo() {
        return BaremeIsPromo;
    }

    public void setBaremeIsPromo(String baremeIsPromo) {
        BaremeIsPromo = baremeIsPromo;
    }

    @Override
    public String toString() {
        return "RefTypeBareme{" +
                "TypeBaremeId='" + TypeBaremeId + '\'' +
                ", TypeBaremeLibelle='" + TypeBaremeLibelle + '\'' +
                ", BaremeIsPromo='" + BaremeIsPromo + '\'' +
                '}';
    }
}
