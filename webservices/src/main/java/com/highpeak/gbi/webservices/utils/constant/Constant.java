package com.highpeak.gbi.webservices.utils.constant;

import java.util.Arrays;
import java.util.List;

/**
 * Common constants which can be used across programs
 * 
 * @author sandesha, Created on 23/08/17
 */
public class Constant {

    public static final String EXCEPTION = "Exception";
    public static final Integer PRIMARY_RADIUS = 8000;
    public static final Integer SECONDARY_RADIUS = 12500;
    public static final List<String> BG_MATCH_APLUS = Arrays.asList("A+", "A-", "O-", "O+");
    public static final List<String> BG_MATCH_AMINUS = Arrays.asList("A-", "O-");
    public static final List<String> BG_MATCH_BPLUS = Arrays.asList("B+", "B-", "O-", "O+");
    public static final List<String> BG_MATCH_BMINUS = Arrays.asList("B-", "O-");
    public static final List<String> BG_MATCH_ABMINUS = Arrays.asList("A-", "B-", "O-", "AB-");
    public static final List<String> BG_MATCH_OPLUS = Arrays.asList("O+", "O-");
    public static final List<String> BG_MATCH_OMINUS = Arrays.asList("O-");
    public static final String INVALID_INPUT = "Invalid input";
    public static final String MANDATORY_PARAM_ERROR = "All mandatory parameters are required";
    public static final String DUPLICATE_DONAR = "User already registered as donar";
    public static final String ERROR_SAVING_USER = "Something went wrong while saving user";
    public static final String SOMETHING_WENT_WRONG = "Something went wrong";
    public static final String NO_DONAR_FOUND = "No potential donar found....searching again!!!!!";
    public static final String DONAR_FOUND = "Potential donar found";
    public static final String ERROR = "error";
    public static final String ERROR_SENDING_SMS = "Error while sending sms";

    private Constant()
    {
    }

}
