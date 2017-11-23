package com.orcaformation.calculetterci.entity;

import java.util.Arrays;

/**
 * Created by PC_MA22 on 03/10/2017.
 */

public class Marque {

    private String IdRefMarque;
    private String LibelleMarque;
    private String LogoMarque;
    private String DeltaTna;
    private String CreatdAt;
    private String UpdatedAt;
    private String DeletedAt;
    private com.orcaformation.calculetterci.entity.RefModeles[] RefModeles;

    public Marque() {
    }

    public Marque(String idRefMarque, String libelleMarque, String logoMarque, String deltaTna, String creatdAt, String updatedAt, String deletedAt, com.orcaformation.calculetterci.entity.RefModeles[] refModeles) {
        IdRefMarque = idRefMarque;
        LibelleMarque = libelleMarque;
        LogoMarque = logoMarque;
        DeltaTna = deltaTna;
        CreatdAt = creatdAt;
        UpdatedAt = updatedAt;
        DeletedAt = deletedAt;
        RefModeles = refModeles;
    }

    public String getIdRefMarque() {
        return IdRefMarque;
    }

    public void setIdRefMarque(String idRefMarque) {
        IdRefMarque = idRefMarque;
    }

    public String getLibelleMarque() {
        return LibelleMarque;
    }

    public void setLibelleMarque(String libelleMarque) {
        LibelleMarque = libelleMarque;
    }

    public String getLogoMarque() {
        return LogoMarque;
    }

    public void setLogoMarque(String logoMarque) {
        LogoMarque = logoMarque;
    }

    public String getDeltaTna() {
        return DeltaTna;
    }

    public void setDeltaTna(String deltaTna) {
        DeltaTna = deltaTna;
    }

    public String getCreatdAt() {
        return CreatdAt;
    }

    public void setCreatdAt(String creatdAt) {
        CreatdAt = creatdAt;
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

    public com.orcaformation.calculetterci.entity.RefModeles[] getRefModeles() {
        return RefModeles;
    }

    public void setRefModeles(com.orcaformation.calculetterci.entity.RefModeles[] refModeles) {
        RefModeles = refModeles;
    }

    @Override
    public String toString() {
        return "Marque{" +
                "IdRefMarque='" + IdRefMarque + '\'' +
                ", LibelleMarque='" + LibelleMarque + '\'' +
                ", LogoMarque='" + LogoMarque + '\'' +
                ", DeltaTna='" + DeltaTna + '\'' +
                ", CreatdAt='" + CreatdAt + '\'' +
                ", UpdatedAt='" + UpdatedAt + '\'' +
                ", DeletedAt='" + DeletedAt + '\'' +
                ", RefModeles=" + Arrays.toString(RefModeles) +
                '}';
    }
}
