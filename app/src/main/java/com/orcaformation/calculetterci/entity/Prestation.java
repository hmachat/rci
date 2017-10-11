package com.orcaformation.calculetterci.entity;

import java.util.Arrays;

/**
 * Created by PC_MA22 on 10/10/2017.
 */

public class Prestation {

    private String XmlPrestationId;
    private String XmlPrestationLibelle;
    private String XmlPrestationCode;
    private String XmlPrestationLibelleComplement;
    private String XmlPrestationMention;
    private String XmlPrestationOrdre;
    private String TypeFinancementId;
    private TblXmlProduits[] TblXmlProduits;


    public Prestation() {
    }

    public Prestation(String xmlPrestationId, String xmlPrestationLibelle, String xmlPrestationCode, String xmlPrestationLibelleComplement, String xmlPrestationMention, String xmlPrestationOrdre, String typeFinancementId, com.orcaformation.calculetterci.entity.TblXmlProduits[] tblXmlProduits) {
        XmlPrestationId = xmlPrestationId;
        XmlPrestationLibelle = xmlPrestationLibelle;
        XmlPrestationCode = xmlPrestationCode;
        XmlPrestationLibelleComplement = xmlPrestationLibelleComplement;
        XmlPrestationMention = xmlPrestationMention;
        XmlPrestationOrdre = xmlPrestationOrdre;
        TypeFinancementId = typeFinancementId;
        TblXmlProduits = tblXmlProduits;
    }

    public String getXmlPrestationId() {
        return XmlPrestationId;
    }

    public void setXmlPrestationId(String xmlPrestationId) {
        XmlPrestationId = xmlPrestationId;
    }

    public String getXmlPrestationLibelle() {
        return XmlPrestationLibelle;
    }

    public void setXmlPrestationLibelle(String xmlPrestationLibelle) {
        XmlPrestationLibelle = xmlPrestationLibelle;
    }

    public String getXmlPrestationCode() {
        return XmlPrestationCode;
    }

    public void setXmlPrestationCode(String xmlPrestationCode) {
        XmlPrestationCode = xmlPrestationCode;
    }

    public String getXmlPrestationLibelleComplement() {
        return XmlPrestationLibelleComplement;
    }

    public void setXmlPrestationLibelleComplement(String xmlPrestationLibelleComplement) {
        XmlPrestationLibelleComplement = xmlPrestationLibelleComplement;
    }

    public String getXmlPrestationMention() {
        return XmlPrestationMention;
    }

    public void setXmlPrestationMention(String xmlPrestationMention) {
        XmlPrestationMention = xmlPrestationMention;
    }

    public String getXmlPrestationOrdre() {
        return XmlPrestationOrdre;
    }

    public void setXmlPrestationOrdre(String xmlPrestationOrdre) {
        XmlPrestationOrdre = xmlPrestationOrdre;
    }

    public String getTypeFinancementId() {
        return TypeFinancementId;
    }

    public void setTypeFinancementId(String typeFinancementId) {
        TypeFinancementId = typeFinancementId;
    }

    public com.orcaformation.calculetterci.entity.TblXmlProduits[] getTblXmlProduits() {
        return TblXmlProduits;
    }

    public void setTblXmlProduits(com.orcaformation.calculetterci.entity.TblXmlProduits[] tblXmlProduits) {
        TblXmlProduits = tblXmlProduits;
    }

    @Override
    public String toString() {
        return "Prestation{" +
                "XmlPrestationId='" + XmlPrestationId + '\'' +
                ", XmlPrestationLibelle='" + XmlPrestationLibelle + '\'' +
                ", XmlPrestationCode='" + XmlPrestationCode + '\'' +
                ", XmlPrestationLibelleComplement='" + XmlPrestationLibelleComplement + '\'' +
                ", XmlPrestationMention='" + XmlPrestationMention + '\'' +
                ", XmlPrestationOrdre='" + XmlPrestationOrdre + '\'' +
                ", TypeFinancementId='" + TypeFinancementId + '\'' +
                ", TblXmlProduits=" + Arrays.toString(TblXmlProduits) +
                '}';
    }
}
