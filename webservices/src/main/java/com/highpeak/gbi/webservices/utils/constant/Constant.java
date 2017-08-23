package com.highpeak.gbi.webservices.utils.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author sandesha, Created on 23/08/17
 */
public class Constant {

    public static String EXCEPTION = "Exception";
    public static Integer PRIMARY_RADIUS=8000;
    public static Integer SECONDARY_RADIUS=12500;
    public static List<String> BG_MATCH_APLUS= Arrays.asList("A+", "A-", "O-", "O+");
    public static List<String> BG_MATCH_AMINUS= Arrays.asList("A-", "O-");
    public static List<String> BG_MATCH_BPLUS= Arrays.asList("B+", "B-", "O-", "O+");
    public static List<String> BG_MATCH_BMINUS= Arrays.asList("B-", "O-");
    public static List<String> BG_MATCH_ABMINUS= Arrays.asList("A-", "B-", "O-");
    public static List<String> BG_MATCH_OPLUS= Arrays.asList("O+", "O-");
    public static List<String> BG_MATCH_OMINUS= Arrays.asList("O-");

}
