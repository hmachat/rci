package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 03/10/2017.
 */

public class LnkTarificationModeles {

    private String ModeleId;
    private String XmlTarificaionId;

    public LnkTarificationModeles() {
    }

    public LnkTarificationModeles(String modeleId, String xmlTarificaionId) {
        ModeleId = modeleId;
        XmlTarificaionId = xmlTarificaionId;
    }

    public String getModeleId() {
        return ModeleId;
    }

    public void setModeleId(String modeleId) {
        ModeleId = modeleId;
    }

    public String getXmlTarificaionId() {
        return XmlTarificaionId;
    }

    public void setXmlTarificaionId(String xmlTarificaionId) {
        XmlTarificaionId = xmlTarificaionId;
    }

    @Override
    public String toString() {
        return "LnkTarificationModeles{" +
                "ModeleId='" + ModeleId + '\'' +
                ", XmlTarificaionId='" + XmlTarificaionId + '\'' +
                '}';
    }
}
