package com.orcaformation.calculetterci.app;

/**
 * Created by PC_MA22 on 02/10/2017.
 */

public class AppConfig {
    // Server user login url
    public static String URL_RCI = "http://rci-bo-pp.orcaformation.com";
    public static String URL_LOGIN = "http://rci-bo-pp.orcaformation.com/ws.php/data/authJson/login/";
    public static String URL_SERVICES = "http://rci-bo-pp.orcaformation.com/ws.php/data/getJsonsREST"; // + ?login=adminrci&password=adminrci
    public static String URL_MARQUE = "http://rci-bo-pp.orcaformation.com/json/marques.json";
    public static String URL_PRESTATION = "http://rci-bo-pp.orcaformation.com/json/prestation.json";
    public static String URL_PACK = "http://rci-bo-pp.orcaformation.com/json/pack.json";
    public static String URL_CREADIT = "http://rci-bo-pp.orcaformation.com/json/12599/credit.json";
    public static String URL_LOA = "http://rci-bo-pp.orcaformation.com/json/12599/loa.json";
    public static String URL_LEASING = "http://rci-bo-pp.orcaformation.com/json/12599/leasing.json";
    public static String URL_FIPE = "http://rci-bo-pp.orcaformation.com/ws.php/data/getFipeREST";
}
