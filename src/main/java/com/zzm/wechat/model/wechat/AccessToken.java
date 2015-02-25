package com.zzm.wechat.model.wechat;

import org.codehaus.jackson.annotate.JsonProperty;

public class AccessToken {
    private String accessToken;
    private long expiresIn;
    private String error;
    private long recordAt;

    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("access_token")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    @JsonProperty("expires_in")
    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getError() {
        return error;
    }

    @JsonProperty("errmsg")
    public void setError(String error) {
        this.error = error;
    }

    public long getRecordAt() {
        return recordAt;
    }

    public void setRecordAt(long recordAt) {
        this.recordAt = recordAt;
    }

    @Override
    public String toString() {
        return "AccessToken{" +
                "recordAt=" + recordAt +
                ", error='" + error + '\'' +
                ", expiresIn=" + expiresIn +
                ", accessToken='" + accessToken + '\'' +
                '}';
    }
}
