package com.zeroinon.chatterboard.dto;

public class TokenDTO {

    public String accessToken;
    public String refreshToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public static boolean hasNullParameterForRefreshToken(TokenDTO tokenDTO) {
        return tokenDTO.getRefreshToken() == null;
    }


}
