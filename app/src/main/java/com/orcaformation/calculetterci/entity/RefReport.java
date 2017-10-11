package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 11/10/2017.
 */

public class RefReport {

    private String ReportId;
    private String ReportLibelle;
    private String ReportValeur;

    public RefReport() {
    }

    public RefReport(String reportId, String reportLibelle, String reportValeur) {
        ReportId = reportId;
        ReportLibelle = reportLibelle;
        ReportValeur = reportValeur;
    }

    public String getReportId() {
        return ReportId;
    }

    public void setReportId(String reportId) {
        ReportId = reportId;
    }

    public String getReportLibelle() {
        return ReportLibelle;
    }

    public void setReportLibelle(String reportLibelle) {
        ReportLibelle = reportLibelle;
    }

    public String getReportValeur() {
        return ReportValeur;
    }

    public void setReportValeur(String reportValeur) {
        ReportValeur = reportValeur;
    }

    @Override
    public String toString() {
        return "RefReport{" +
                "ReportId='" + ReportId + '\'' +
                ", ReportLibelle='" + ReportLibelle + '\'' +
                ", ReportValeur='" + ReportValeur + '\'' +
                '}';
    }
}
