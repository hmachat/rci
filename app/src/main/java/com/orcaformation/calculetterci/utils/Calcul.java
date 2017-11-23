package com.orcaformation.calculetterci.utils;

import java.util.HashMap;

/**
 * Created by PC_MA22 on 13/11/2017.
 */

public class Calcul {

    public HashMap<String, String> calculCredit(String psHT_TTC,
                                         double pfPrixTotal_TTC,
                                         double pfDivers_TTC,
                                         double pfMontantApport_TTC,
                                         double pfTva_achat,
                                         double pfTva_interet,
                                         double pfTna,
                                         int piDuree,
                                         double piVr,
                                         int piDureeReport)
    {

        double mens;

        //Taux Nominal Mensuel
        double fTnm = pfTna / 1200.0;
        double fTnm_TvaInteret = (fTnm * (1 + (pfTva_interet / 100)));

        double pfPrix_HT = arrondi2chiffresPoint(pfPrixTotal_TTC / (1 + (pfTva_achat / 100)), 2);
        double pfDivers_HT = arrondi2chiffresPoint(pfDivers_TTC / (1 + (pfTva_achat / 100)), 2);

        double fMontantFinanceTTC = (pfPrixTotal_TTC + pfDivers_TTC) - pfMontantApport_TTC;

        double mathpow = Math.pow((1 + fTnm_TvaInteret), piDureeReport);

        if (fTnm == 0) //Crédit à 0% - Modif du 16/05
        {
            mens = (pfPrixTotal_TTC - pfMontantApport_TTC) / piDuree;
        } else
        {
            mens = ((fMontantFinanceTTC * mathpow) * (fTnm_TvaInteret)) / (1 - Math.pow(1 + fTnm_TvaInteret, -piDuree));
        }

        double maVr = 0;

        if (piVr > 0)
        {
            double fMontantFinanceBallon = fMontantFinanceTTC * (1 + fTnm_TvaInteret);
            if (piVr > 100)
            {
                maVr = piVr;
            } else {
                maVr = (fMontantFinanceBallon * (piVr / 100));
            }

            if (piDureeReport > 0) {

                mens = calculBallon(fTnm_TvaInteret, piDuree, -fMontantFinanceBallon, maVr, 0);

            } else {

                mens = calculBallon(fTnm_TvaInteret, piDuree, -fMontantFinanceTTC, maVr, 0);

            }
        }

        if (psHT_TTC.equals("HT"))
        {
            mens = mens / (1 + (pfTva_achat / 100));
        }else{
            mens = mens;
        }

        HashMap<String, String> resultCredit = new HashMap<>();
        resultCredit.put("prixTotal", String.valueOf(pfPrixTotal_TTC));
        resultCredit.put("apport", String.valueOf(pfMontantApport_TTC));
        resultCredit.put("montantCredit",String.valueOf(fMontantFinanceTTC));
        resultCredit.put("duree",String.valueOf(piDuree));
        resultCredit.put("tna",String.valueOf(pfTna));
        resultCredit.put("mensualite",String.valueOf(arrondi2chiffresPoint(mens,2)));
        resultCredit.put("vr",String.valueOf(piVr));
        resultCredit.put("mtVr",String.valueOf(arrondi2chiffresPoint(maVr,2)));

        return resultCredit;
    }


    public HashMap<String, String> calculLOA(String psHT_TTC,
                                                double fPrixTotal_TTC,
                                                double pfDivers_TTC,
                                                double pfTauxLoyerMajore,
                                                double pfTauxLoyer1,
                                                double pfTauxDepotGarantie,
                                                double pfTauxVR,
                                                double pfTva_achat,
                                                double pfTauxRend,
                                                int piDuree,
                                                double pfTva_financiere)
    {

        double Loyer;

        double fTnm = pfTauxRend / 1200.0;
        double fUnPlusTnm = fTnm + 1;

        double pfPrix_HT = arrondi2chiffresPoint(fPrixTotal_TTC / (1 + (pfTva_achat / 100)), 2);

        double fLoyerMajore_HT = pfPrix_HT * (pfTauxLoyerMajore / 100);

        double fLoyer1_HT = (pfPrix_HT * (pfTauxLoyer1 / 100));

        double fmontantDG = (pfTauxDepotGarantie) / 100 * fPrixTotal_TTC;
        double fVRHT = pfPrix_HT * (pfTauxVR / 100);

        double fmontantFinance = (pfPrix_HT - fLoyer1_HT - fmontantDG) * fUnPlusTnm;

        double fLoyerSuivantHTa = fmontantFinance - (fVRHT * (Math.pow(fUnPlusTnm, -piDuree)));
        double fLoyerSuivantHTb = ((1 - (Math.pow(fUnPlusTnm, -piDuree + 1))) / fTnm) + 1;
        double fLoyerSuivantHT = fLoyerSuivantHTa / fLoyerSuivantHTb;

        if (psHT_TTC.equals("TTC"))
        {
            Loyer = fLoyerSuivantHT * (1 + (pfTva_financiere / 100));
        } else
        {
            Loyer = fLoyerSuivantHT;
        }

        HashMap<String, String> resultCredit = new HashMap<>();
        resultCredit.put("prixTotal", String.valueOf(fPrixTotal_TTC));
        resultCredit.put("prixDivers", String.valueOf(pfDivers_TTC));
        resultCredit.put("tauxLoyerMajore", String.valueOf(pfTauxLoyerMajore));
        resultCredit.put("montantLoyerMajore", "");
        resultCredit.put("tauxLoyer1", String.valueOf(pfTauxLoyer1));
        resultCredit.put("montantLoyer1", String.valueOf(fLoyer1_HT * (1 + (pfTva_achat / 100))));
        resultCredit.put("tauxDG", String.valueOf(pfTauxVR));
        resultCredit.put("montantDG", String.valueOf(fmontantDG));
        resultCredit.put("tauxVR", "");
        resultCredit.put("montantVR", String.valueOf(arrondi2chiffresPoint(fVRHT * (1 + (pfTva_achat / 100)),2)));
        resultCredit.put("duree", String.valueOf(piDuree));
        resultCredit.put("taux", String.valueOf(pfTauxRend));
        resultCredit.put("montantFinance", String.valueOf(fmontantFinance));
        resultCredit.put("mensualite", String.valueOf(arrondi2chiffresPoint(Loyer,2)));

        return resultCredit;
    }

    public double arrondi2chiffresPoint(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    double calculBallon(double $tx, double $npm, double $va, double $vc, double $typeRembt) {
        double $txActuariel = Math.pow(1 + $tx, -$npm);
        if ((1 - $txActuariel) == 0) {
            return 0;
        }
        double $vpm = (($va + $vc * $txActuariel) * $tx / (1 - $txActuariel)) / (1 + $tx * $typeRembt);
        return -$vpm;
    }


}
