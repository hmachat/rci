package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 10/10/2017.
 */

public class LnkPackTypeClients {

    private String XmlPackId;
    private String XmlTypeClientId;
    private RefTypeClient RefTypeClient;

    public LnkPackTypeClients() {
    }

    public LnkPackTypeClients(String xmlPackId, String xmlTypeClientId, com.orcaformation.calculetterci.entity.RefTypeClient refTypeClient) {
        XmlPackId = xmlPackId;
        XmlTypeClientId = xmlTypeClientId;
        RefTypeClient = refTypeClient;
    }

    public String getXmlPackId() {
        return XmlPackId;
    }

    public void setXmlPackId(String xmlPackId) {
        XmlPackId = xmlPackId;
    }

    public String getXmlTypeClientId() {
        return XmlTypeClientId;
    }

    public void setXmlTypeClientId(String xmlTypeClientId) {
        XmlTypeClientId = xmlTypeClientId;
    }

    public com.orcaformation.calculetterci.entity.RefTypeClient getRefTypeClient() {
        return RefTypeClient;
    }

    public void setRefTypeClient(com.orcaformation.calculetterci.entity.RefTypeClient refTypeClient) {
        RefTypeClient = refTypeClient;
    }

    @Override
    public String toString() {
        return "LnkPackTypeClients{" +
                "XmlPackId='" + XmlPackId + '\'' +
                ", XmlTypeClientId='" + XmlTypeClientId + '\'' +
                ", RefTypeClient=" + RefTypeClient +
                '}';
    }
}
