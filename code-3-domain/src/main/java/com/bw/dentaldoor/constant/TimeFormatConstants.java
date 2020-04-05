package com.bw.dentaldoor.constant;

import java.util.Arrays;
import java.util.List;

/**
 * @author Olaleye Afolabi <oafolabi@byteworks.com.ng>
 */
public class TimeFormatConstants {

    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    public static final String APP_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final List<String> SUPPORTED_FORMATS = Arrays.asList(DEFAULT_DATE_FORMAT, DEFAULT_DATE_TIME_FORMAT, APP_DATE_TIME_FORMAT, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd HH:mm:ss.SSS");
}
