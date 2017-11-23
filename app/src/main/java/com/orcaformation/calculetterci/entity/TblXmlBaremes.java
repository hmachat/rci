package com.orcaformation.calculetterci.entity;

import java.util.Arrays;

/**
 * Created by PC_MA22 on 11/10/2017.
 */

public class TblXmlBaremes {

    private String XmlBaremeId;
    private String XmlTarificationId;
    private String XmlBaremeCode;
    private String TypeBaremeId;
    private String ParamTvaBienId;
    private String ParamTvaFiId;
    private String XmlBaremeNbEcheanceFd;
    private String XmlBaremeFd;
    private String XmlBaremeFdPlafond;
    private String XmlBaremeDateOuverture;
    private String XmlBaremeDateFermeture;
    private String XmlBaremeTnaDefault;
    private String PasDureeId;
    private String XmlBaremeDelegation;
    private String XmlBaremeTxPremierLoyer;
    private String XmlBaremeTxDg;
    private String XmlBaremeTxVr;
    private String CreatedAt;
    private String UpdatedAt;
    private String DeletedAt;
    private com.orcaformation.calculetterci.entity.TblXmlConditions[] TblXmlConditions;
    private com.orcaformation.calculetterci.entity.RefPasDuree RefPasDuree;
    private com.orcaformation.calculetterci.entity.LnkBaremeTvaBiens[] LnkBaremeTvaBiens;
    private com.orcaformation.calculetterci.entity.TblParamTva TblParamTva;

    public TblXmlBaremes() {
    }

    public TblXmlBaremes(String xmlBaremeId, String pasDureeId, String xmlBaremeTxVr) {
        XmlBaremeId = xmlBaremeId;
        PasDureeId = pasDureeId;
        XmlBaremeTxVr = xmlBaremeTxVr;
    }

    public TblXmlBaremes(String xmlBaremeId, String xmlTarificationId, String xmlBaremeCode, String typeBaremeId, String paramTvaBienId, String paramTvaFiId, String xmlBaremeNbEcheanceFd, String xmlBaremeFd, String xmlBaremeFdPlafond, String xmlBaremeDateOuverture, String xmlBaremeDateFermeture, String xmlBaremeTnaDefault, String pasDureeId, String xmlBaremeDelegation, String xmlBaremeTxPremierLoyer, String xmlBaremeTxDg, String xmlBaremeTxVr, String createdAt, String updatedAt, String deletedAt, com.orcaformation.calculetterci.entity.TblXmlConditions[] tblXmlConditions, com.orcaformation.calculetterci.entity.RefPasDuree refPasDuree, com.orcaformation.calculetterci.entity.LnkBaremeTvaBiens[] lnkBaremeTvaBiens, com.orcaformation.calculetterci.entity.TblParamTva tblParamTva) {
        XmlBaremeId = xmlBaremeId;
        XmlTarificationId = xmlTarificationId;
        XmlBaremeCode = xmlBaremeCode;
        TypeBaremeId = typeBaremeId;
        ParamTvaBienId = paramTvaBienId;
        ParamTvaFiId = paramTvaFiId;
        XmlBaremeNbEcheanceFd = xmlBaremeNbEcheanceFd;
        XmlBaremeFd = xmlBaremeFd;
        XmlBaremeFdPlafond = xmlBaremeFdPlafond;
        XmlBaremeDateOuverture = xmlBaremeDateOuverture;
        XmlBaremeDateFermeture = xmlBaremeDateFermeture;
        XmlBaremeTnaDefault = xmlBaremeTnaDefault;
        PasDureeId = pasDureeId;
        XmlBaremeDelegation = xmlBaremeDelegation;
        XmlBaremeTxPremierLoyer = xmlBaremeTxPremierLoyer;
        XmlBaremeTxDg = xmlBaremeTxDg;
        XmlBaremeTxVr = xmlBaremeTxVr;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        DeletedAt = deletedAt;
        TblXmlConditions = tblXmlConditions;
        RefPasDuree = refPasDuree;
        LnkBaremeTvaBiens = lnkBaremeTvaBiens;
        TblParamTva = tblParamTva;
    }

    public String getXmlBaremeId() {
        return XmlBaremeId;
    }

    public void setXmlBaremeId(String xmlBaremeId) {
        XmlBaremeId = xmlBaremeId;
    }

    public String getXmlTarificationId() {
        return XmlTarificationId;
    }

    public void setXmlTarificationId(String xmlTarificationId) {
        XmlTarificationId = xmlTarificationId;
    }

    public String getXmlBaremeCode() {
        return XmlBaremeCode;
    }

    public void setXmlBaremeCode(String xmlBaremeCode) {
        XmlBaremeCode = xmlBaremeCode;
    }

    public String getTypeBaremeId() {
        return TypeBaremeId;
    }

    public void setTypeBaremeId(String typeBaremeId) {
        TypeBaremeId = typeBaremeId;
    }

    public String getParamTvaBienId() {
        return ParamTvaBienId;
    }

    public void setParamTvaBienId(String paramTvaBienId) {
        ParamTvaBienId = paramTvaBienId;
    }

    public String getParamTvaFiId() {
        return ParamTvaFiId;
    }

    public void setParamTvaFiId(String paramTvaFiId) {
        ParamTvaFiId = paramTvaFiId;
    }

    public String getXmlBaremeNbEcheanceFd() {
        return XmlBaremeNbEcheanceFd;
    }

    public void setXmlBaremeNbEcheanceFd(String xmlBaremeNbEcheanceFd) {
        XmlBaremeNbEcheanceFd = xmlBaremeNbEcheanceFd;
    }

    public String getXmlBaremeFd() {
        return XmlBaremeFd;
    }

    public void setXmlBaremeFd(String xmlBaremeFd) {
        XmlBaremeFd = xmlBaremeFd;
    }

    public String getXmlBaremeFdPlafond() {
        return XmlBaremeFdPlafond;
    }

    public void setXmlBaremeFdPlafond(String xmlBaremeFdPlafond) {
        XmlBaremeFdPlafond = xmlBaremeFdPlafond;
    }

    public String getXmlBaremeDateOuverture() {
        return XmlBaremeDateOuverture;
    }

    public void setXmlBaremeDateOuverture(String xmlBaremeDateOuverture) {
        XmlBaremeDateOuverture = xmlBaremeDateOuverture;
    }

    public String getXmlBaremeDateFermeture() {
        return XmlBaremeDateFermeture;
    }

    public void setXmlBaremeDateFermeture(String xmlBaremeDateFermeture) {
        XmlBaremeDateFermeture = xmlBaremeDateFermeture;
    }

    public String getXmlBaremeTnaDefault() {
        return XmlBaremeTnaDefault;
    }

    public void setXmlBaremeTnaDefault(String xmlBaremeTnaDefault) {
        XmlBaremeTnaDefault = xmlBaremeTnaDefault;
    }

    public String getPasDureeId() {
        return PasDureeId;
    }

    public void setPasDureeId(String pasDureeId) {
        PasDureeId = pasDureeId;
    }

    public String getXmlBaremeDelegation() {
        return XmlBaremeDelegation;
    }

    public void setXmlBaremeDelegation(String xmlBaremeDelegation) {
        XmlBaremeDelegation = xmlBaremeDelegation;
    }

    public String getXmlBaremeTxPremierLoyer() {
        return XmlBaremeTxPremierLoyer;
    }

    public void setXmlBaremeTxPremierLoyer(String xmlBaremeTxPremierLoyer) {
        XmlBaremeTxPremierLoyer = xmlBaremeTxPremierLoyer;
    }

    public String getXmlBaremeTxDg() {
        return XmlBaremeTxDg;
    }

    public void setXmlBaremeTxDg(String xmlBaremeTxDg) {
        XmlBaremeTxDg = xmlBaremeTxDg;
    }

    public String getXmlBaremeTxVr() {
        return XmlBaremeTxVr;
    }

    public void setXmlBaremeTxVr(String xmlBaremeTxVr) {
        XmlBaremeTxVr = xmlBaremeTxVr;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return DeletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        DeletedAt = deletedAt;
    }

    public com.orcaformation.calculetterci.entity.TblXmlConditions[] getTblXmlConditions() {
        return TblXmlConditions;
    }

    public void setTblXmlConditions(com.orcaformation.calculetterci.entity.TblXmlConditions[] tblXmlConditions) {
        TblXmlConditions = tblXmlConditions;
    }

    public com.orcaformation.calculetterci.entity.RefPasDuree getRefPasDuree() {
        return RefPasDuree;
    }

    public void setRefPasDuree(com.orcaformation.calculetterci.entity.RefPasDuree refPasDuree) {
        RefPasDuree = refPasDuree;
    }

    public com.orcaformation.calculetterci.entity.LnkBaremeTvaBiens[] getLnkBaremeTvaBiens() {
        return LnkBaremeTvaBiens;
    }

    public void setLnkBaremeTvaBiens(com.orcaformation.calculetterci.entity.LnkBaremeTvaBiens[] lnkBaremeTvaBiens) {
        LnkBaremeTvaBiens = lnkBaremeTvaBiens;
    }

    public com.orcaformation.calculetterci.entity.TblParamTva getTblParamTva() {
        return TblParamTva;
    }

    public void setTblParamTva(com.orcaformation.calculetterci.entity.TblParamTva tblParamTva) {
        TblParamTva = tblParamTva;
    }

    @Override
    public String toString() {
        return "TblXmlBaremes{" +
                "XmlBaremeId='" + XmlBaremeId + '\'' +
                ", XmlTarificationId='" + XmlTarificationId + '\'' +
                ", XmlBaremeCode='" + XmlBaremeCode + '\'' +
                ", TypeBaremeId='" + TypeBaremeId + '\'' +
                ", ParamTvaBienId='" + ParamTvaBienId + '\'' +
                ", ParamTvaFiId='" + ParamTvaFiId + '\'' +
                ", XmlBaremeNbEcheanceFd='" + XmlBaremeNbEcheanceFd + '\'' +
                ", XmlBaremeFd='" + XmlBaremeFd + '\'' +
                ", XmlBaremeFdPlafond='" + XmlBaremeFdPlafond + '\'' +
                ", XmlBaremeDateOuverture='" + XmlBaremeDateOuverture + '\'' +
                ", XmlBaremeDateFermeture='" + XmlBaremeDateFermeture + '\'' +
                ", XmlBaremeTnaDefault='" + XmlBaremeTnaDefault + '\'' +
                ", PasDureeId='" + PasDureeId + '\'' +
                ", XmlBaremeDelegation='" + XmlBaremeDelegation + '\'' +
                ", XmlBaremeTxPremierLoyer='" + XmlBaremeTxPremierLoyer + '\'' +
                ", XmlBaremeTxDg='" + XmlBaremeTxDg + '\'' +
                ", XmlBaremeTxVr='" + XmlBaremeTxVr + '\'' +
                ", CreatedAt='" + CreatedAt + '\'' +
                ", UpdatedAt='" + UpdatedAt + '\'' +
                ", DeletedAt='" + DeletedAt + '\'' +
                ", TblXmlConditions=" + Arrays.toString(TblXmlConditions) +
                ", RefPasDuree=" + RefPasDuree +
                ", LnkBaremeTvaBiens=" + Arrays.toString(LnkBaremeTvaBiens) +
                ", TblParamTva=" + TblParamTva +
                '}';
    }
}
