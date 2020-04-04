package com.start.stockdata.util.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UriPath {

    public static final String COMPANIES_PATH = "/companies";

    public static final long JWT_ACCESS_TOKEN_VALIDITY_MILLISECONDS = 18_000_000;// 5 hours
    public static final String SIGNING_KEY = "start12345";
    public static final String TOKEN_PREFIX_WITH_SPACE = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
