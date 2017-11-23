package com.orcaformation.calculetterci.entity;

import java.util.Arrays;

/**
 * Created by PC_MA22 on 10/10/2017.
 */

public class Pack {

    private String XmlPackId;
    private String TypeFinancementId;
    private String XmlPackLibelle;
    private String XmlPackTaux;
    private String XmlPackDescreption;
    private String XmlPackEntretien;
    private String CreatedAt;
    private String UpdatedAt;
    private String DeletedAt;
    private com.orcaformation.calculetterci.entity.RefTypeFinancement RefTypeFinancement;
    private com.orcaformation.calculetterci.entity.LnkXmlProduitPacks[] LnkXmlProduitPacks;
    private com.orcaformation.calculetterci.entity.LnkPackTypeClients[] LnkPackTypeClients;


    public Pack() {
    }

    public Pack(String xmlPackId, String typeFinancementId, String xmlPackLibelle, String xmlPackTaux, String xmlPackDescreption, String xmlPackEntretien, String createdAt, String updatedAt, String deletedAt, com.orcaformation.calculetterci.entity.RefTypeFinancement refTypeFinancement, com.orcaformation.calculetterci.entity.LnkXmlProduitPacks[] lnkXmlProduitPacks, com.orcaformation.calculetterci.entity.LnkPackTypeClients[] lnkPackTypeClients) {
        XmlPackId = xmlPackId;
        TypeFinancementId = typeFinancementId;
        XmlPackLibelle = xmlPackLibelle;
        XmlPackTaux = xmlPackTaux;
        XmlPackDescreption = xmlPackDescreption;
        XmlPackEntretien = xmlPackEntretien;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        DeletedAt = deletedAt;
        RefTypeFinancement = refTypeFinancement;
        LnkXmlProduitPacks = lnkXmlProduitPacks;
        LnkPackTypeClients = lnkPackTypeClients;
    }

    public String getXmlPackId() {
        return XmlPackId;
    }

    public void setXmlPackId(String xmlPackId) {
        XmlPackId = xmlPackId;
    }

    public String getTypeFinancementId() {
        return TypeFinancementId;
    }

    public void setTypeFinancementId(String typeFinancementId) {
        TypeFinancementId = typeFinancementId;
    }

    public String getXmlPackLibelle() {
        return XmlPackLibelle;
    }

    public void setXmlPackLibelle(String xmlPackLibelle) {
        XmlPackLibelle = xmlPackLibelle;
    }

    public String getXmlPackTaux() {
        return XmlPackTaux;
    }

    public void setXmlPackTaux(String xmlPackTaux) {
        XmlPackTaux = xmlPackTaux;
    }

    public String getXmlPackDescreption() {
        return XmlPackDescreption;
    }

    public void setXmlPackDescreption(String xmlPackDescreption) {
        XmlPackDescreption = xmlPackDescreption;
    }

    public String getXmlPackEntretien() {
        return XmlPackEntretien;
    }

    public void setXmlPackEntretien(String xmlPackEntretien) {
        XmlPackEntretien = xmlPackEntretien;
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

    public com.orcaformation.calculetterci.entity.RefTypeFinancement getRefTypeFinancement() {
        return RefTypeFinancement;
    }

    public void setRefTypeFinancement(com.orcaformation.calculetterci.entity.RefTypeFinancement refTypeFinancement) {
        RefTypeFinancement = refTypeFinancement;
    }

    public com.orcaformation.calculetterci.entity.LnkPackTypeClients[] getLnkPackTypeClients() {
        return LnkPackTypeClients;
    }

    public void setLnkPackTypeClients(com.orcaformation.calculetterci.entity.LnkPackTypeClients[] lnkPackTypeClients) {
        LnkPackTypeClients = lnkPackTypeClients;
    }

    public com.orcaformation.calculetterci.entity.LnkXmlProduitPacks[] getLnkXmlProduitPacks() {
        return LnkXmlProduitPacks;
    }

    public void setLnkXmlProduitPacks(com.orcaformation.calculetterci.entity.LnkXmlProduitPacks[] lnkXmlProduitPacks) {
        LnkXmlProduitPacks = lnkXmlProduitPacks;
    }

    @Override
    public String toString() {
        return "Pack{" +
                "XmlPackId='" + XmlPackId + '\'' +
                ", TypeFinancementId='" + TypeFinancementId + '\'' +
                ", XmlPackLibelle='" + XmlPackLibelle + '\'' +
                ", XmlPackTaux='" + XmlPackTaux + '\'' +
                ", XmlPackDescreption='" + XmlPackDescreption + '\'' +
                ", XmlPackEntretien='" + XmlPackEntretien + '\'' +
                ", CreatedAt='" + CreatedAt + '\'' +
                ", UpdatedAt='" + UpdatedAt + '\'' +
                ", DeletedAt='" + DeletedAt + '\'' +
                ", RefTypeFinancement=" + RefTypeFinancement +
                ", LnkXmlProduitPacks=" + Arrays.toString(LnkXmlProduitPacks) +
                ", LnkPackTypeClients=" + Arrays.toString(LnkPackTypeClients) +
                '}';
    }
}
