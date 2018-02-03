package com.github.alexvishneuski.mywebview;

class VKAccessToken {

    static final String ACCESS_TOKEN = "access_token";
    static final String EXPIRES_IN = "expires_in";
    static final String CREATED = "created";

    private String mAccessToken;
    private int mExpiresIn;
    private long mCreated;

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String pAccessToken) {
        mAccessToken = pAccessToken;
    }

    public int getExpiresIn() {
        return mExpiresIn;
    }

    public void setExpiresIn(int pExpiresIn) {
        mExpiresIn = pExpiresIn;
    }

    public long getCreated() {
        return mCreated;
    }

    public void setCreated(long pCreated) {
        mCreated = pCreated;
    }
}
