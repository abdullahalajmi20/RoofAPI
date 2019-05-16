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
public class OrderBean {
    
    private int orderID;
    private int userID;
    private int mobile;
    private String fullName;
    private String email;
    private String address;
    private double totalAmount;
    private String notes;
    private String status;
    private int storeID;
    private String storeName;
    private String createdDate;

    /**
     *
     */
    public OrderBean() {
    }

    /**
     * Constructor
     * @param orderID
     * @param userID
     * @param mobile
     * @param fullName
     * @param email
     * @param address
     * @param totalAmount
     * @param notes
     * @param status
     * @param storeID
     * @param storeName
     * @param createdDate
     */
    public OrderBean(int orderID, int userID, int mobile, String fullName, String email,
            String address, double totalAmount, String notes, 
            String status, int storeID, String storeName, String createdDate) {
        this.orderID = orderID;
        this.userID = userID;
        this.mobile = mobile;
        this.fullName = fullName;
        this.email = email;
        this.address = address;
        this.totalAmount = totalAmount;
        this.notes = notes;
        this.status = status;
        this.storeID = storeID;
        this.storeName = storeName;
        this.createdDate = createdDate;
    }

    /**
     *
     * @return
     */
    public int getOrderID() {
        return orderID;
    }

    /**
     *
     * @param orderID
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
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
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     *
     * @param totalAmount
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     *
     * @return
     */
    public String getNotes() {
        return notes;
    }

    /**
     *
     * @param notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     *
     * @return
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     *
     * @return
     */
    public int getStoreID() {
        return storeID;
    }

    /**
     *
     * @param storeID
     */
    public void setStoreID(int storeID) {
        this.storeID = storeID;
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
