package de.seniorenheim.speedify.business.util;

import java.math.BigDecimal;

public class FinanceValues {

    private FinanceValues() {}

    public static final BigDecimal umsatzanteilArbeitnehmer = BigDecimal.valueOf(0.25);
    public static final BigDecimal fixgehaltArbeitnehmer = BigDecimal.valueOf(2000);
    public static final BigDecimal lohnsteuerMindestgehalt = BigDecimal.valueOf(1029000);

    public static final BigDecimal kfzSteuer =  BigDecimal.valueOf(1500);
    public static final BigDecimal kfzHaftpflicht = BigDecimal.valueOf(4000);
    public static final BigDecimal kfzVollkasko = BigDecimal.valueOf(3000);
    public static final BigDecimal kfzHauptuntersuchung = BigDecimal.valueOf(150);
    public static final BigDecimal berufskraftfahrerqualifikation = BigDecimal.valueOf(100);
    public static final BigDecimal euGemeinschaftslizenz = BigDecimal.valueOf(50);
    public static final BigDecimal euGemeinschaftslizenzKopie = BigDecimal.valueOf(7.5);

    public static final BigDecimal sozialversicherungsbeitragssatz = BigDecimal.valueOf(0.2);
    public static final BigDecimal uvMindestbeitrag = BigDecimal.valueOf(62);
    public static final BigDecimal uvBeitragssatz = BigDecimal.valueOf(0.013);

    public static final BigDecimal lohnsteuersatz = BigDecimal.valueOf(0.12);
    public static final BigDecimal[] einkommensteuersätze = { BigDecimal.valueOf(0.19), BigDecimal.valueOf(0.33), BigDecimal.valueOf(0.42), BigDecimal.valueOf(0.45) };
    public static final BigDecimal[] einkommensteuergrenzen = { BigDecimal.valueOf(290100), BigDecimal.valueOf(425125), BigDecimal.valueOf(1669000), BigDecimal.valueOf(6945625) };
    public static final BigDecimal umsatzsteuersatz = BigDecimal.valueOf(0.19);
    public static final BigDecimal koerperschaftssteuersatz = BigDecimal.valueOf(0.15);
    public static final BigDecimal gewerbesteuersatz = BigDecimal.valueOf(0.035);
    public static final BigDecimal gewerbesteuerEinkommensteuerHebesatz = BigDecimal.valueOf(3.8);
}