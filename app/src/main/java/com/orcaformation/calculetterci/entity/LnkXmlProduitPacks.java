package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 10/10/2017.
 */

public class LnkXmlProduitPacks {

    private String XmlPackId;
    private String XmlProduitId;
    private com.orcaformation.calculetterci.entity.TblXmlProduit TblXmlProduit;

    public LnkXmlProduitPacks() {
    }

    public LnkXmlProduitPacks(String xmlPackId, String xmlProduitId, com.orcaformation.calculetterci.entity.TblXmlProduit tblXmlProduit) {
        XmlPackId = xmlPackId;
        XmlProduitId = xmlProduitId;
        TblXmlProduit = tblXmlProduit;
    }

    public String getXmlPackId() {
        return XmlPackId;
    }

    public void setXmlPackId(String xmlPackId) {
        XmlPackId = xmlPackId;
    }

    public String getXmlProduitId() {
        return XmlProduitId;
    }

    public void setXmlProduitId(String xmlProduitId) {
        XmlProduitId = xmlProduitId;
    }

    public com.orcaformation.calculetterci.entity.TblXmlProduit getTblXmlProduit() {
        return TblXmlProduit;
    }

    public void setTblXmlProduit(com.orcaformation.calculetterci.entity.TblXmlProduit tblXmlProduit) {
        TblXmlProduit = tblXmlProduit;
    }

    @Override
    public String toString() {
        return "LnkXmlProduitPacks{" +
                "XmlPackId='" + XmlPackId + '\'' +
                ", XmlProduitId='" + XmlProduitId + '\'' +
                ", TblXmlProduit=" + TblXmlProduit +
                '}';
    }
}
