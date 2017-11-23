package com.orcaformation.calculetterci.entity;

/**
 * Created by PC_MA22 on 11/10/2017.
 */

public class LnkBaremeTvaBiens {

    private String XmlBaremeId;
    private String ParamTvaId;
    private com.orcaformation.calculetterci.entity.TblParamTva TblParamTva;

    public LnkBaremeTvaBiens() {
    }

    public LnkBaremeTvaBiens(String xmlBaremeId, String paramTvaId, com.orcaformation.calculetterci.entity.TblParamTva tblParamTva) {
        XmlBaremeId = xmlBaremeId;
        ParamTvaId = paramTvaId;
        TblParamTva = tblParamTva;
    }

    public String getXmlBaremeId() {
        return XmlBaremeId;
    }

    public void setXmlBaremeId(String xmlBaremeId) {
        XmlBaremeId = xmlBaremeId;
    }

    public String getParamTvaId() {
        return ParamTvaId;
    }

    public void setParamTvaId(String paramTvaId) {
        ParamTvaId = paramTvaId;
    }

    public com.orcaformation.calculetterci.entity.TblParamTva getTblParamTva() {
        return TblParamTva;
    }

    public void setTblParamTva(com.orcaformation.calculetterci.entity.TblParamTva tblParamTva) {
        TblParamTva = tblParamTva;
    }

    @Override
    public String toString() {
        return "LnkBaremeTvaBiens{" +
                "XmlBaremeId='" + XmlBaremeId + '\'' +
                ", ParamTvaId='" + ParamTvaId + '\'' +
                ", TblParamTva=" + TblParamTva +
                '}';
    }
}
