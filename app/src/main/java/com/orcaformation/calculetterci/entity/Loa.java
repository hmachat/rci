package com.orcaformation.calculetterci.entity;

import java.util.Arrays;

/**
 * Created by PC_MA22 on 11/10/2017.
 */

public class Loa {

    private String XmlTarificationId;
    private String XmlTarificationLibelle;
    private String TypeBaremeId;
    private String TypeVehiculeId;
    private String TypeClientIdASupp;
    private String MarcheId;
    private String TypeFinancementId;
    private String ZoneId;
    private String CreatedAt;
    private String UpdatedAt;
    private String DeletedAt;
    private String IdRefMarque;
    private String ModeleId;
    private String NatureBienId;
    private String NatureMoteurId;
    private String MarqueDeltaTna;
    private LnkProduitTarifications[] LnkProduitTarifications;
    private RefTypeBareme RefTypeBareme;
    private TblXmlBaremes[] TblXmlBaremes;
    private LnkXmlTarificationTypeClients[] LnkXmlTarificationTypeClients;
    private LnkXmlTarificationReports[] LnkXmlTarificationReports;

    public Loa() {
    }

    public Loa(String xmlTarificationId, String xmlTarificationLibelle, String typeBaremeId, String typeVehiculeId, String typeClientIdASupp, String marcheId, String typeFinancementId, String zoneId, String createdAt, String updatedAt, String deletedAt, String idRefMarque, String modeleId, String natureBienId, String natureMoteurId, String marqueDeltaTna, com.orcaformation.calculetterci.entity.LnkProduitTarifications[] lnkProduitTarifications, com.orcaformation.calculetterci.entity.RefTypeBareme refTypeBareme, com.orcaformation.calculetterci.entity.TblXmlBaremes[] tblXmlBaremes, com.orcaformation.calculetterci.entity.LnkXmlTarificationTypeClients[] lnkXmlTarificationTypeClients, com.orcaformation.calculetterci.entity.LnkXmlTarificationReports[] lnkXmlTarificationReports) {
        XmlTarificationId = xmlTarificationId;
        XmlTarificationLibelle = xmlTarificationLibelle;
        TypeBaremeId = typeBaremeId;
        TypeVehiculeId = typeVehiculeId;
        TypeClientIdASupp = typeClientIdASupp;
        MarcheId = marcheId;
        TypeFinancementId = typeFinancementId;
        ZoneId = zoneId;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        DeletedAt = deletedAt;
        IdRefMarque = idRefMarque;
        ModeleId = modeleId;
        NatureBienId = natureBienId;
        NatureMoteurId = natureMoteurId;
        MarqueDeltaTna = marqueDeltaTna;
        LnkProduitTarifications = lnkProduitTarifications;
        RefTypeBareme = refTypeBareme;
        TblXmlBaremes = tblXmlBaremes;
        LnkXmlTarificationTypeClients = lnkXmlTarificationTypeClients;
        LnkXmlTarificationReports = lnkXmlTarificationReports;
    }

    public String getXmlTarificationId() {
        return XmlTarificationId;
    }

    public void setXmlTarificationId(String xmlTarificationId) {
        XmlTarificationId = xmlTarificationId;
    }

    public String getXmlTarificationLibelle() {
        return XmlTarificationLibelle;
    }

    public void setXmlTarificationLibelle(String xmlTarificationLibelle) {
        XmlTarificationLibelle = xmlTarificationLibelle;
    }

    public String getTypeBaremeId() {
        return TypeBaremeId;
    }

    public void setTypeBaremeId(String typeBaremeId) {
        TypeBaremeId = typeBaremeId;
    }

    public String getTypeVehiculeId() {
        return TypeVehiculeId;
    }

    public void setTypeVehiculeId(String typeVehiculeId) {
        TypeVehiculeId = typeVehiculeId;
    }

    public String getTypeClientIdASupp() {
        return TypeClientIdASupp;
    }

    public void setTypeClientIdASupp(String typeClientIdASupp) {
        TypeClientIdASupp = typeClientIdASupp;
    }

    public String getMarcheId() {
        return MarcheId;
    }

    public void setMarcheId(String marcheId) {
        MarcheId = marcheId;
    }

    public String getTypeFinancementId() {
        return TypeFinancementId;
    }

    public void setTypeFinancementId(String typeFinancementId) {
        TypeFinancementId = typeFinancementId;
    }

    public String getZoneId() {
        return ZoneId;
    }

    public void setZoneId(String zoneId) {
        ZoneId = zoneId;
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

    public String getIdRefMarque() {
        return IdRefMarque;
    }

    public void setIdRefMarque(String idRefMarque) {
        IdRefMarque = idRefMarque;
    }

    public String getModeleId() {
        return ModeleId;
    }

    public void setModeleId(String modeleId) {
        ModeleId = modeleId;
    }

    public String getNatureBienId() {
        return NatureBienId;
    }

    public void setNatureBienId(String natureBienId) {
        NatureBienId = natureBienId;
    }

    public String getNatureMoteurId() {
        return NatureMoteurId;
    }

    public void setNatureMoteurId(String natureMoteurId) {
        NatureMoteurId = natureMoteurId;
    }

    public String getMarqueDeltaTna() {
        return MarqueDeltaTna;
    }

    public void setMarqueDeltaTna(String marqueDeltaTna) {
        MarqueDeltaTna = marqueDeltaTna;
    }

    public com.orcaformation.calculetterci.entity.LnkProduitTarifications[] getLnkProduitTarifications() {
        return LnkProduitTarifications;
    }

    public void setLnkProduitTarifications(com.orcaformation.calculetterci.entity.LnkProduitTarifications[] lnkProduitTarifications) {
        LnkProduitTarifications = lnkProduitTarifications;
    }

    public com.orcaformation.calculetterci.entity.RefTypeBareme getRefTypeBareme() {
        return RefTypeBareme;
    }

    public void setRefTypeBareme(com.orcaformation.calculetterci.entity.RefTypeBareme refTypeBareme) {
        RefTypeBareme = refTypeBareme;
    }

    public com.orcaformation.calculetterci.entity.TblXmlBaremes[] getTblXmlBaremes() {
        return TblXmlBaremes;
    }

    public void setTblXmlBaremes(com.orcaformation.calculetterci.entity.TblXmlBaremes[] tblXmlBaremes) {
        TblXmlBaremes = tblXmlBaremes;
    }

    public com.orcaformation.calculetterci.entity.LnkXmlTarificationTypeClients[] getLnkXmlTarificationTypeClients() {
        return LnkXmlTarificationTypeClients;
    }

    public void setLnkXmlTarificationTypeClients(com.orcaformation.calculetterci.entity.LnkXmlTarificationTypeClients[] lnkXmlTarificationTypeClients) {
        LnkXmlTarificationTypeClients = lnkXmlTarificationTypeClients;
    }

    public com.orcaformation.calculetterci.entity.LnkXmlTarificationReports[] getLnkXmlTarificationReports() {
        return LnkXmlTarificationReports;
    }

    public void setLnkXmlTarificationReports(com.orcaformation.calculetterci.entity.LnkXmlTarificationReports[] lnkXmlTarificationReports) {
        LnkXmlTarificationReports = lnkXmlTarificationReports;
    }

    @Override
    public String toString() {
        return "Loa{" +
                "XmlTarificationId='" + XmlTarificationId + '\'' +
                ", XmlTarificationLibelle='" + XmlTarificationLibelle + '\'' +
                ", TypeBaremeId='" + TypeBaremeId + '\'' +
                ", TypeVehiculeId='" + TypeVehiculeId + '\'' +
                ", TypeClientIdASupp='" + TypeClientIdASupp + '\'' +
                ", MarcheId='" + MarcheId + '\'' +
                ", TypeFinancementId='" + TypeFinancementId + '\'' +
                ", ZoneId='" + ZoneId + '\'' +
                ", CreatedAt='" + CreatedAt + '\'' +
                ", UpdatedAt='" + UpdatedAt + '\'' +
                ", DeletedAt='" + DeletedAt + '\'' +
                ", IdRefMarque='" + IdRefMarque + '\'' +
                ", ModeleId='" + ModeleId + '\'' +
                ", NatureBienId='" + NatureBienId + '\'' +
                ", NatureMoteurId='" + NatureMoteurId + '\'' +
                ", MarqueDeltaTna='" + MarqueDeltaTna + '\'' +
                ", LnkProduitTarifications=" + Arrays.toString(LnkProduitTarifications) +
                ", RefTypeBareme=" + RefTypeBareme +
                ", TblXmlBaremes=" + Arrays.toString(TblXmlBaremes) +
                ", LnkXmlTarificationTypeClients=" + Arrays.toString(LnkXmlTarificationTypeClients) +
                ", LnkXmlTarificationReports=" + Arrays.toString(LnkXmlTarificationReports) +
                '}';
    }
}
