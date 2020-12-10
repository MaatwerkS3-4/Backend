package com.ProftaakS34.Opinion.helpers;

public class AuthenticationConstants {
    private AuthenticationConstants(){}
    public static final String SECRET = "Sup3RS3Cr3t@uth3NT1C@Ti0nK3Y";
    public static final long EXPIRATION_TIME = 864_000_000_000_000L; // 10_000_000 days
    public static final String TOKEN_PREFIX = "Bearer=";
    public static final String HEADER_STRING = "Authorization";
}
