package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 11/10/2017.
 */

public class TblParamTva {

    private String ParamTvaId;
    private String TypeTvaId;
    private String TypeFinancementId;
    private String ValeurTva;
    private RefTypeTva RefTypeTva;

    public TblParamTva() {
    }

    public TblParamTva(String paramTvaId, String typeTvaId, String typeFinancementId, String valeurTva, com.orcaformation.calculetterci.entity.RefTypeTva refTypeTva) {
        ParamTvaId = paramTvaId;
        TypeTvaId = typeTvaId;
        TypeFinancementId = typeFinancementId;
        ValeurTva = valeurTva;
        RefTypeTva = refTypeTva;
    }

    public TblParamTva(String paramTvaId, String typeTvaId, String typeFinancementId, String valeurTva) {
        ParamTvaId = paramTvaId;
        TypeTvaId = typeTvaId;
        TypeFinancementId = typeFinancementId;
        ValeurTva = valeurTva;
    }

    public String getParamTvaId() {
        return ParamTvaId;
    }

    public void setParamTvaId(String paramTvaId) {
        ParamTvaId = paramTvaId;
    }

    public String getTypeTvaId() {
        return TypeTvaId;
    }

    public void setTypeTvaId(String typeTvaId) {
        TypeTvaId = typeTvaId;
    }

    public String getTypeFinancementId() {
        return TypeFinancementId;
    }

    public void setTypeFinancementId(String typeFinancementId) {
        TypeFinancementId = typeFinancementId;
    }

    public String getValeurTva() {
        return ValeurTva;
    }

    public void setValeurTva(String valeurTva) {
        ValeurTva = valeurTva;
    }

    public com.orcaformation.calculetterci.entity.RefTypeTva getRefTypeTva() {
        return RefTypeTva;
    }

    public void setRefTypeTva(com.orcaformation.calculetterci.entity.RefTypeTva refTypeTva) {
        RefTypeTva = refTypeTva;
    }

    @Override
    public String toString() {
        return "TblParamTva{" +
                "ParamTvaId='" + ParamTvaId + '\'' +
                ", TypeTvaId='" + TypeTvaId + '\'' +
                ", TypeFinancementId='" + TypeFinancementId + '\'' +
                ", ValeurTva='" + ValeurTva + '\'' +
                ", RefTypeTva=" + RefTypeTva +
                '}';
    }
}
