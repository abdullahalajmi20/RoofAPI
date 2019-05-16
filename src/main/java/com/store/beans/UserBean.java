/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.beans;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abdullah
 */

@ManagedBean
@XmlRootElement

public class UserBean implements Serializable{
    private int userID;
    private String fullName;
    private int govID;
    private String userName;
    private String password;
    private String email;
    private int mobile;
    private String address;
    private int type;      // 1-User, 2 Store
    private int status; // 0-Not Active, 1-Active
    private String storeName;
    private int storeCatID;
    private String token; 
    private String photoURL;
    private String createdDate;

    /**
     * Constructor
     */
    public UserBean() {
    }

    /**
     * Constructor
     * @param userID
     * @param fullName
     * @param govID
     * @param userName
     * @param password
     * @param email
     * @param mobile
     * @param address
     * @param type
     * @param status
     * @param storeName
     * @param storeCatID
     * @param photoURL
     * @param token
     * @param createdDate
     */
    public UserBean(int userID, String fullName, int govID, String userName, String password, 
            String email, int mobile, String address, int type, int status, String storeName,
            int storeCatID, String photoURL, String token, String createdDate) {
        this.userID = userID;
        this.fullName = fullName;
        this.govID = govID;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.address = address;
        this.type = type;
        this.status = status;
        this.storeName = storeName;
        this.storeCatID = storeCatID;
        this.photoURL = photoURL;
        this.token = token;
        this.createdDate = createdDate;
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
    public String getFullName() {
        return fullName;
    }

    /**
     *
     * @param fullName
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     *
     * @return
     */
    public int getGovID() {
        return govID;
    }

    /**
     *
     * @param govID
     */
    public void setGovID(int govID) {
        this.govID = govID;
    }

    /**
     *
     * @return
     */
    public String getUserName() {
        return userName;
    }

    /**
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public int getMobile() {
        return mobile;
    }

    /**
     *
     * @param mobile
     */
    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     *
     * @return
     */
    public int getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     *
     * @param storeName
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     *
     * @return
     */
    public int getStoreCatID() {
        return storeCatID;
    }

    /**
     *
     * @param storeCatID
     */
    public void setStoreCatID(int storeCatID) {
        this.storeCatID = storeCatID;
    }

    /**
     *
     * @return
     */
    public String getPhotoURL() {
        return photoURL;
    }

    /**
     *
     * @param photoURL
     */
    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
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
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     *
     * @param createdDate
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    
    
}
