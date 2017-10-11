package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 11/10/2017.
 */

public class LnkXmlTarificationReports {

    private String XmlTarificationId;
    private String ReportId;
    private RefReport RefReport;

    public LnkXmlTarificationReports() {
    }

    public LnkXmlTarificationReports(String xmlTarificationId, String reportId, com.orcaformation.calculetterci.entity.RefReport refReport) {
        XmlTarificationId = xmlTarificationId;
        ReportId = reportId;
        RefReport = refReport;
    }

    public String getXmlTarificationId() {
        return XmlTarificationId;
    }

    public void setXmlTarificationId(String xmlTarificationId) {
        XmlTarificationId = xmlTarificationId;
    }

    public String getReportId() {
        return ReportId;
    }

    public void setReportId(String reportId) {
        ReportId = reportId;
    }

    public com.orcaformation.calculetterci.entity.RefReport getRefReport() {
        return RefReport;
    }

    public void setRefReport(com.orcaformation.calculetterci.entity.RefReport refReport) {
        RefReport = refReport;
    }

    @Override
    public String toString() {
        return "LnkXmlTarificationReports{" +
                "XmlTarificationId='" + XmlTarificationId + '\'' +
                ", ReportId='" + ReportId + '\'' +
                ", RefReport=" + RefReport +
                '}';
    }
}
