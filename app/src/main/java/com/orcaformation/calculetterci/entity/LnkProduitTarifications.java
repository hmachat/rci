package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 11/10/2017.
 */

public class LnkProduitTarifications {

    private String XmlProduitId;
    private String XmlTarificationId;
    private com.orcaformation.calculetterci.entity.TblXmlProduit TblXmlProduit;

    public LnkProduitTarifications() {
    }

    public LnkProduitTarifications(String xmlProduitId, String xmlTarificationId, com.orcaformation.calculetterci.entity.TblXmlProduit tblXmlProduit) {
        XmlProduitId = xmlProduitId;
        XmlTarificationId = xmlTarificationId;
        TblXmlProduit = tblXmlProduit;
    }

    public String getXmlProduitId() {
        return XmlProduitId;
    }

    public void setXmlProduitId(String xmlProduitId) {
        XmlProduitId = xmlProduitId;
    }

    public String getXmlTarificationId() {
        return XmlTarificationId;
    }

    public void setXmlTarificationId(String xmlTarificationId) {
        XmlTarificationId = xmlTarificationId;
    }

    public com.orcaformation.calculetterci.entity.TblXmlProduit getTblXmlProduit() {
        return TblXmlProduit;
    }

    public void setTblXmlProduit(com.orcaformation.calculetterci.entity.TblXmlProduit tblXmlProduit) {
        TblXmlProduit = tblXmlProduit;
    }

    @Override
    public String toString() {
        return "LnkProduitTarifications{" +
                "XmlProduitId='" + XmlProduitId + '\'' +
                ", XmlTarificationId='" + XmlTarificationId + '\'' +
                ", TblXmlProduit=" + TblXmlProduit +
                '}';
    }
}
