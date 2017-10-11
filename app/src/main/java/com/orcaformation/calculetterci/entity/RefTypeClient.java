package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 10/10/2017.
 */

public class RefTypeClient {

    private String TypeClientId;
    private String TypeClientLibelle;
    private String TypeClientLibelleLong;
    private String TypeClientCode;

    public RefTypeClient() {
    }

    public RefTypeClient(String typeClientId, String typeClientLibelle, String typeClientLibelleLong, String typeClientCode) {
        TypeClientId = typeClientId;
        TypeClientLibelle = typeClientLibelle;
        TypeClientLibelleLong = typeClientLibelleLong;
        TypeClientCode = typeClientCode;
    }

    public String getTypeClientId() {
        return TypeClientId;
    }

    public void setTypeClientId(String typeClientId) {
        TypeClientId = typeClientId;
    }

    public String getTypeClientLibelle() {
        return TypeClientLibelle;
    }

    public void setTypeClientLibelle(String typeClientLibelle) {
        TypeClientLibelle = typeClientLibelle;
    }

    public String getTypeClientLibelleLong() {
        return TypeClientLibelleLong;
    }

    public void setTypeClientLibelleLong(String typeClientLibelleLong) {
        TypeClientLibelleLong = typeClientLibelleLong;
    }

    public String getTypeClientCode() {
        return TypeClientCode;
    }

    public void setTypeClientCode(String typeClientCode) {
        TypeClientCode = typeClientCode;
    }

    @Override
    public String toString() {
        return "RefTypeClient{" +
                "TypeClientId='" + TypeClientId + '\'' +
                ", TypeClientLibelle='" + TypeClientLibelle + '\'' +
                ", TypeClientLibelleLong='" + TypeClientLibelleLong + '\'' +
                ", TypeClientCode='" + TypeClientCode + '\'' +
                '}';
    }
}
