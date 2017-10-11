package com.orcaformation.calculetterci.entity;

import java.util.Arrays;

/**
 * Created by PC_MA22 on 10/10/2017.
 */

public class TblXmlProduits {

    private String XmlProduitId;
    private String XmlPrestationId;
    private String ZoneId;
    private String XmlProduitLibelle;
    private String XmlProduitCondition;
    private String XmlProduitCode;
    private String XmlProduitPrime;
    private String XmlProduitTaux;
    private String XmlProduitPlancher;
    private String XmlProduitPlafond;
    private String PrestationModeFacturationId;
    private String PrestationBaseCalculId;
    private String PrestationTypeId;
    private String PrestationObligatoire;
    private String PrestationChecked;
    private String PrestationDisabled;
    private String PrestationVisible;
    private String PrestationIsFd;
    private String TypeInformationId;
    private String Description;
    private RefPrestationBaseCalcul RefPrestationBaseCalcul;

    public TblXmlProduits() {
    }

    public TblXmlProduits(String xmlProduitId, String xmlPrestationId, String zoneId, String xmlProduitLibelle, String xmlProduitCondition, String xmlProduitCode, String xmlProduitPrime, String xmlProduitTaux, String xmlProduitPlancher, String xmlProduitPlafond, String prestationModeFacturationId, String prestationBaseCalculId, String prestationTypeId, String prestationObligatoire, String prestationChecked, String prestationDisabled, String prestationVisible, String prestationIsFd, String typeInformationId, String description, com.orcaformation.calculetterci.entity.RefPrestationBaseCalcul refPrestationBaseCalcul) {
        XmlProduitId = xmlProduitId;
        XmlPrestationId = xmlPrestationId;
        ZoneId = zoneId;
        XmlProduitLibelle = xmlProduitLibelle;
        XmlProduitCondition = xmlProduitCondition;
        XmlProduitCode = xmlProduitCode;
        XmlProduitPrime = xmlProduitPrime;
        XmlProduitTaux = xmlProduitTaux;
        XmlProduitPlancher = xmlProduitPlancher;
        XmlProduitPlafond = xmlProduitPlafond;
        PrestationModeFacturationId = prestationModeFacturationId;
        PrestationBaseCalculId = prestationBaseCalculId;
        PrestationTypeId = prestationTypeId;
        PrestationObligatoire = prestationObligatoire;
        PrestationChecked = prestationChecked;
        PrestationDisabled = prestationDisabled;
        PrestationVisible = prestationVisible;
        PrestationIsFd = prestationIsFd;
        TypeInformationId = typeInformationId;
        Description = description;
        RefPrestationBaseCalcul = refPrestationBaseCalcul;
    }

    public String getXmlProduitId() {
        return XmlProduitId;
    }

    public void setXmlProduitId(String xmlProduitId) {
        XmlProduitId = xmlProduitId;
    }

    public String getXmlPrestationId() {
        return XmlPrestationId;
    }

    public void setXmlPrestationId(String xmlPrestationId) {
        XmlPrestationId = xmlPrestationId;
    }

    public String getZoneId() {
        return ZoneId;
    }

    public void setZoneId(String zoneId) {
        ZoneId = zoneId;
    }

    public String getXmlProduitLibelle() {
        return XmlProduitLibelle;
    }

    public void setXmlProduitLibelle(String xmlProduitLibelle) {
        XmlProduitLibelle = xmlProduitLibelle;
    }

    public String getXmlProduitCondition() {
        return XmlProduitCondition;
    }

    public void setXmlProduitCondition(String xmlProduitCondition) {
        XmlProduitCondition = xmlProduitCondition;
    }

    public String getXmlProduitCode() {
        return XmlProduitCode;
    }

    public void setXmlProduitCode(String xmlProduitCode) {
        XmlProduitCode = xmlProduitCode;
    }

    public String getXmlProduitPrime() {
        return XmlProduitPrime;
    }

    public void setXmlProduitPrime(String xmlProduitPrime) {
        XmlProduitPrime = xmlProduitPrime;
    }

    public String getXmlProduitTaux() {
        return XmlProduitTaux;
    }

    public void setXmlProduitTaux(String xmlProduitTaux) {
        XmlProduitTaux = xmlProduitTaux;
    }

    public String getXmlProduitPlancher() {
        return XmlProduitPlancher;
    }

    public void setXmlProduitPlancher(String xmlProduitPlancher) {
        XmlProduitPlancher = xmlProduitPlancher;
    }

    public String getXmlProduitPlafond() {
        return XmlProduitPlafond;
    }

    public void setXmlProduitPlafond(String xmlProduitPlafond) {
        XmlProduitPlafond = xmlProduitPlafond;
    }

    public String getPrestationModeFacturationId() {
        return PrestationModeFacturationId;
    }

    public void setPrestationModeFacturationId(String prestationModeFacturationId) {
        PrestationModeFacturationId = prestationModeFacturationId;
    }

    public String getPrestationBaseCalculId() {
        return PrestationBaseCalculId;
    }

    public void setPrestationBaseCalculId(String prestationBaseCalculId) {
        PrestationBaseCalculId = prestationBaseCalculId;
    }

    public String getPrestationTypeId() {
        return PrestationTypeId;
    }

    public void setPrestationTypeId(String prestationTypeId) {
        PrestationTypeId = prestationTypeId;
    }

    public String getPrestationObligatoire() {
        return PrestationObligatoire;
    }

    public void setPrestationObligatoire(String prestationObligatoire) {
        PrestationObligatoire = prestationObligatoire;
    }

    public String getPrestationChecked() {
        return PrestationChecked;
    }

    public void setPrestationChecked(String prestationChecked) {
        PrestationChecked = prestationChecked;
    }

    public String getPrestationDisabled() {
        return PrestationDisabled;
    }

    public void setPrestationDisabled(String prestationDisabled) {
        PrestationDisabled = prestationDisabled;
    }

    public String getPrestationVisible() {
        return PrestationVisible;
    }

    public void setPrestationVisible(String prestationVisible) {
        PrestationVisible = prestationVisible;
    }

    public String getPrestationIsFd() {
        return PrestationIsFd;
    }

    public void setPrestationIsFd(String prestationIsFd) {
        PrestationIsFd = prestationIsFd;
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

    public com.orcaformation.calculetterci.entity.RefPrestationBaseCalcul getRefPrestationBaseCalcul() {
        return RefPrestationBaseCalcul;
    }

    public void setRefPrestationBaseCalcul(com.orcaformation.calculetterci.entity.RefPrestationBaseCalcul refPrestationBaseCalcul) {
        RefPrestationBaseCalcul = refPrestationBaseCalcul;
    }



    @Override
    public String toString() {
        return "TblXmlProduits{" +
                "XmlProduitId='" + XmlProduitId + '\'' +
                ", XmlPrestationId='" + XmlPrestationId + '\'' +
                ", ZoneId='" + ZoneId + '\'' +
                ", XmlProduitLibelle='" + XmlProduitLibelle + '\'' +
                ", XmlProduitCondition='" + XmlProduitCondition + '\'' +
                ", XmlProduitCode='" + XmlProduitCode + '\'' +
                ", XmlProduitPrime='" + XmlProduitPrime + '\'' +
                ", XmlProduitTaux='" + XmlProduitTaux + '\'' +
                ", XmlProduitPlancher='" + XmlProduitPlancher + '\'' +
                ", XmlProduitPlafond='" + XmlProduitPlafond + '\'' +
                ", PrestationModeFacturationId='" + PrestationModeFacturationId + '\'' +
                ", PrestationBaseCalculId='" + PrestationBaseCalculId + '\'' +
                ", PrestationTypeId='" + PrestationTypeId + '\'' +
                ", PrestationObligatoire='" + PrestationObligatoire + '\'' +
                ", PrestationChecked='" + PrestationChecked + '\'' +
                ", PrestationDisabled='" + PrestationDisabled + '\'' +
                ", PrestationVisible='" + PrestationVisible + '\'' +
                ", PrestationIsFd='" + PrestationIsFd + '\'' +
                ", TypeInformationId='" + TypeInformationId + '\'' +
                ", Description='" + Description + '\'' +
                ", RefPrestationBaseCalcul=" + RefPrestationBaseCalcul +
                '}';
    }
}
