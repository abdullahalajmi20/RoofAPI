/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.beans;

/**
 *
 * @author Abdullah
 */
public class TokenBean {
    private int userID;
    private String token;
    private int os;

    /**
     * Constructor
     */
    public TokenBean() {
    }

    /**
     * Constructor
     * @param userID
     * @param token
     * @param os
     */
    public TokenBean(int userID, String token, int os) {
        this.userID = userID;
        this.token = token;
        this.os = os;
    }

    /**
     *
     * @return
     */
    public int getUserID() {
        return userID;
    }

    /**
     *
     * @param userID
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     *
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     *
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     *
     * @return
     */
    public int getOs() {
        return os;
    }

    /**
     *
     * @param os
     */
    public void setOs(int os) {
        this.os = os;
    }
    
    
}
