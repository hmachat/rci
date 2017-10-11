package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 11/10/2017.
 */

public class LnkXmlTarificationTypeClients {

    private String XmlTarificationId;
    private String TypeClientId;
    private RefTypeClient RefTypeClient;

    public LnkXmlTarificationTypeClients() {
    }

    public LnkXmlTarificationTypeClients(String xmlTarificationId, String typeClientId, com.orcaformation.calculetterci.entity.RefTypeClient refTypeClient) {
        XmlTarificationId = xmlTarificationId;
        TypeClientId = typeClientId;
        RefTypeClient = refTypeClient;
    }

    public String getXmlTarificationId() {
        return XmlTarificationId;
    }

    public void setXmlTarificationId(String xmlTarificationId) {
        XmlTarificationId = xmlTarificationId;
    }

    public String getTypeClientId() {
        return TypeClientId;
    }

    public void setTypeClientId(String typeClientId) {
        TypeClientId = typeClientId;
    }

    public com.orcaformation.calculetterci.entity.RefTypeClient getRefTypeClient() {
        return RefTypeClient;
    }

    public void setRefTypeClient(com.orcaformation.calculetterci.entity.RefTypeClient refTypeClient) {
        RefTypeClient = refTypeClient;
    }

    @Override
    public String toString() {
        return "LnkXmlTarificationTypeClients{" +
                "XmlTarificationId='" + XmlTarificationId + '\'' +
                ", TypeClientId='" + TypeClientId + '\'' +
                ", RefTypeClient=" + RefTypeClient +
                '}';
    }
}
