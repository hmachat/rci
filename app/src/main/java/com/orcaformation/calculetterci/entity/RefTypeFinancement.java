package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 10/10/2017.
 */

public class RefTypeFinancement {

    private String TypeFinancementId;
    private String TypeFinancementLibelle;
    private String TypeFinancementLibelleLong;
    private String TypeInformationId;
    private String Description;

    public RefTypeFinancement() {
    }

    public RefTypeFinancement(String typeFinancementId, String typeFinancementLibelle, String typeFinancementLibelleLong, String typeInformationId, String description) {
        TypeFinancementId = typeFinancementId;
        TypeFinancementLibelle = typeFinancementLibelle;
        TypeFinancementLibelleLong = typeFinancementLibelleLong;
        TypeInformationId = typeInformationId;
        Description = description;
    }

    public String getTypeFinancementId() {
        return TypeFinancementId;
    }

    public void setTypeFinancementId(String typeFinancementId) {
        TypeFinancementId = typeFinancementId;
    }

    public String getTypeFinancementLibelle() {
        return TypeFinancementLibelle;
    }

    public void setTypeFinancementLibelle(String typeFinancementLibelle) {
        TypeFinancementLibelle = typeFinancementLibelle;
    }

    public String getTypeFinancementLibelleLong() {
        return TypeFinancementLibelleLong;
    }

    public void setTypeFinancementLibelleLong(String typeFinancementLibelleLong) {
        TypeFinancementLibelleLong = typeFinancementLibelleLong;
    }

    public String getTypeInformationId() {
        return TypeInformationId;
    }

    public void setTypeInformationId(String typeInformationId) {
        TypeInformationId = typeInformationId;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    @Override
    public String toString() {
        return "RefTypeFinancement{" +
                "TypeFinancementId='" + TypeFinancementId + '\'' +
                ", TypeFinancementLibelle='" + TypeFinancementLibelle + '\'' +
                ", TypeFinancementLibelleLong='" + TypeFinancementLibelleLong + '\'' +
                ", TypeInformationId='" + TypeInformationId + '\'' +
                ", Description='" + Description + '\'' +
                '}';
    }
}
