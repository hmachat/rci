package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 03/10/2017.
 */

public class TblVersions {

    private String VersionId;
    private String ModeleId;
    private String VersionLib;
    private String PrixTtc;
    private String PrixHt;
    private String MontantTva;
    private String XmlProduitId;
    private String TauxTva;
    private com.orcaformation.calculetterci.entity.TblXmlProduit TblXmlProduit;

    public TblVersions() {
    }

    public TblVersions(String versionId, String modeleId, String versionLib, String prixTtc, String prixHt, String montantTva, String xmlProduitId, String tauxTva, com.orcaformation.calculetterci.entity.TblXmlProduit tblXmlProduit) {
        VersionId = versionId;
        ModeleId = modeleId;
        VersionLib = versionLib;
        PrixTtc = prixTtc;
        PrixHt = prixHt;
        MontantTva = montantTva;
        XmlProduitId = xmlProduitId;
        TauxTva = tauxTva;
        TblXmlProduit = tblXmlProduit;
    }

    public String getVersionId() {
        return VersionId;
    }

    public void setVersionId(String versionId) {
        VersionId = versionId;
    }

    public String getModeleId() {
        return ModeleId;
    }

    public void setModeleId(String modeleId) {
        ModeleId = modeleId;
    }

    public String getVersionLib() {
        return VersionLib;
    }

    public void setVersionLib(String versionLib) {
        VersionLib = versionLib;
    }

    public String getPrixTtc() {
        return PrixTtc;
    }

    public void setPrixTtc(String prixTtc) {
        PrixTtc = prixTtc;
    }

    public String getPrixHt() {
        return PrixHt;
    }

    public void setPrixHt(String prixHt) {
        PrixHt = prixHt;
    }

    public String getMontantTva() {
        return MontantTva;
    }

    public void setMontantTva(String montantTva) {
        MontantTva = montantTva;
    }

    public String getXmlProduitId() {
        return XmlProduitId;
    }

    public void setXmlProduitId(String xmlProduitId) {
        XmlProduitId = xmlProduitId;
    }

    public String getTauxTva() {
        return TauxTva;
    }

    public void setTauxTva(String tauxTva) {
        TauxTva = tauxTva;
    }

    public com.orcaformation.calculetterci.entity.TblXmlProduit getTblXmlProduit() {
        return TblXmlProduit;
    }

    public void setTblXmlProduit(com.orcaformation.calculetterci.entity.TblXmlProduit tblXmlProduit) {
        TblXmlProduit = tblXmlProduit;
    }

    @Override
    public String toString() {
        return "TblVersions{" +
                "VersionId='" + VersionId + '\'' +
                ", ModeleId='" + ModeleId + '\'' +
                ", VersionLib='" + VersionLib + '\'' +
                ", PrixTtc='" + PrixTtc + '\'' +
                ", PrixHt='" + PrixHt + '\'' +
                ", MontantTva='" + MontantTva + '\'' +
                ", XmlProduitId='" + XmlProduitId + '\'' +
                ", TauxTva='" + TauxTva + '\'' +
                ", TblXmlProduit=" + TblXmlProduit +
                '}';
    }
}
