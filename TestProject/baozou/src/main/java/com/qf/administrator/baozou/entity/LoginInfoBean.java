package com.qf.administrator.baozou.entity;

/**
 * Created by Alden on 2017/5/9.
 */

public class LoginInfoBean {

    /**
     * userID : 58BC5AEF2F10FF7143A8B6A3E041A824
     * icon : http://q.qlogo.cn/qqapp/1106032335/58BC5AEF2F10FF7143A8B6A3E041A824/40
     * expiresTime : 1494270028745
     * token : 66E98D38F19A6AF78EEF7C01B41AE67A
     * nickname : Alden
     * secretType : 0
     * gender : 0
     * pf : desktop_m_qq-10000144-android-2002-
     * pay_token : 268C8898E10C64430E309C7892B4F3FA
     * secret :
     * iconQzone : http://qzapp.qlogo.cn/qzapp/1106032335/58BC5AEF2F10FF7143A8B6A3E041A824/100
     * pfkey : f6ba61dead3dccdceab7b148d2896401
     * expiresIn : 7776000
     */

    private String userID;
    private String icon;//头像url
    private long expiresTime;
    private String token;//token
    private String nickname;//name
    private String secretType;
    private String gender;
    private String pf;
    private String pay_token;
    private String secret;
    private String iconQzone;
    private String pfkey;
    private int expiresIn;

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSecretType() {
        return secretType;
    }

    public void setSecretType(String secretType) {
        this.secretType = secretType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf = pf;
    }

    public String getPay_token() {
        return pay_token;
    }

    public void setPay_token(String pay_token) {
        this.pay_token = pay_token;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getIconQzone() {
        return iconQzone;
    }

    public void setIconQzone(String iconQzone) {
        this.iconQzone = iconQzone;
    }

    public String getPfkey() {
        return pfkey;
    }

    public void setPfkey(String pfkey) {
        this.pfkey = pfkey;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }
}
