package com.orcaformation.calculetterci.entity;

import java.util.Arrays;

/**
 * Created by PC_MA22 on 03/10/2017.
 */

public class RefModeles {

    private String ModeleId;
    private String ModeleLibelle;
    private String MarqueId;
    private String SegmentId;
    private String FinitionVersion;
    private String MotorisationId;
    private String GenreVehiculeId;
    private String ModelePhoto;
    private String OrdreAffichage;
    private String CreatedAt;
    private String UpdatedAt;
    private String DeletedAt;
    private LnkTarificationModeles[] LnkTarificationModeles;
    private TblVersions[] TblVersions;

    public RefModeles() {
    }

    public RefModeles(String modeleId, String modeleLibelle, String marqueId, String segmentId, String finitionVersion, String motorisationId, String genreVehiculeId, String modelePhoto, String ordreAffichage, String createdAt, String updatedAt, String deletedAt, com.orcaformation.calculetterci.entity.LnkTarificationModeles[] lnkTarificationModeles, com.orcaformation.calculetterci.entity.TblVersions[] tblVersions) {
        ModeleId = modeleId;
        ModeleLibelle = modeleLibelle;
        MarqueId = marqueId;
        SegmentId = segmentId;
        FinitionVersion = finitionVersion;
        MotorisationId = motorisationId;
        GenreVehiculeId = genreVehiculeId;
        ModelePhoto = modelePhoto;
        OrdreAffichage = ordreAffichage;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        DeletedAt = deletedAt;
        LnkTarificationModeles = lnkTarificationModeles;
        TblVersions = tblVersions;
    }

    public String getModeleId() {
        return ModeleId;
    }

    public void setModeleId(String modeleId) {
        ModeleId = modeleId;
    }

    public String getModeleLibelle() {
        return ModeleLibelle;
    }

    public void setModeleLibelle(String modeleLibelle) {
        ModeleLibelle = modeleLibelle;
    }

    public String getMarqueId() {
        return MarqueId;
    }

    public void setMarqueId(String marqueId) {
        MarqueId = marqueId;
    }

    public String getSegmentId() {
        return SegmentId;
    }

    public void setSegmentId(String segmentId) {
        SegmentId = segmentId;
    }

    public String getFinitionVersion() {
        return FinitionVersion;
    }

    public void setFinitionVersion(String finitionVersion) {
        FinitionVersion = finitionVersion;
    }

    public String getMotorisationId() {
        return MotorisationId;
    }

    public void setMotorisationId(String motorisationId) {
        MotorisationId = motorisationId;
    }

    public String getGenreVehiculeId() {
        return GenreVehiculeId;
    }

    public void setGenreVehiculeId(String genreVehiculeId) {
        GenreVehiculeId = genreVehiculeId;
    }

    public String getModelePhoto() {
        return ModelePhoto;
    }

    public void setModelePhoto(String modelePhoto) {
        ModelePhoto = modelePhoto;
    }

    public String getOrdreAffichage() {
        return OrdreAffichage;
    }

    public void setOrdreAffichage(String ordreAffichage) {
        OrdreAffichage = ordreAffichage;
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

    public com.orcaformation.calculetterci.entity.LnkTarificationModeles[] getLnkTarificationModeles() {
        return LnkTarificationModeles;
    }

    public void setLnkTarificationModeles(com.orcaformation.calculetterci.entity.LnkTarificationModeles[] lnkTarificationModeles) {
        LnkTarificationModeles = lnkTarificationModeles;
    }

    public com.orcaformation.calculetterci.entity.TblVersions[] getTblVersions() {
        return TblVersions;
    }

    public void setTblVersions(com.orcaformation.calculetterci.entity.TblVersions[] tblVersions) {
        TblVersions = tblVersions;
    }

    @Override
    public String toString() {
        return "RefModeles{" +
                "ModeleId='" + ModeleId + '\'' +
                ", ModeleLibelle='" + ModeleLibelle + '\'' +
                ", MarqueId='" + MarqueId + '\'' +
                ", SegmentId='" + SegmentId + '\'' +
                ", FinitionVersion='" + FinitionVersion + '\'' +
                ", MotorisationId='" + MotorisationId + '\'' +
                ", GenreVehiculeId='" + GenreVehiculeId + '\'' +
                ", ModelePhoto='" + ModelePhoto + '\'' +
                ", OrdreAffichage='" + OrdreAffichage + '\'' +
                ", CreatedAt='" + CreatedAt + '\'' +
                ", UpdatedAt='" + UpdatedAt + '\'' +
                ", DeletedAt='" + DeletedAt + '\'' +
                ", LnkTarificationModeles=" + Arrays.toString(LnkTarificationModeles) +
                ", TblVersions=" + Arrays.toString(TblVersions) +
                '}';
    }
}
