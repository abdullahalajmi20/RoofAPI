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
public class ProductBean {
    
    private int productID;
    private int userID;
    private String storeName;
    private String productName;
    private String description;
    private int catID; 
    private double price;
    private int quantity;
    private String photoURL;
    private int status;
    private int deliveryDuration;
    private String deliveryType;
    private String specialOrder;
    private String createdDate;

    /**
     * Constructor
     */
    public ProductBean() {
    }

    /**
     *  Constructor
     * @param productID
     * @param userID
     * @param storeName
     * @param productName
     * @param description
     * @param catID
     * @param price
     * @param quantity
     * @param photoURL
     * @param status
     * @param deliveryDuration
     * @param deliveryType
     * @param specialOrder
     * @param createdDate
     */
    public ProductBean(int productID,int userID,String storeName, String productName,
            String description, int catID,double price, int quantity,String photoURL, 
            int status,int deliveryDuration, String deliveryType, String specialOrder, String createdDate) {
        this.productID = productID;
        this.userID = userID;
        this.storeName = storeName;
        this.productName = productName;
        this.description = description;
        this.catID = catID;
        this.price = price;
        this.quantity = quantity;
        this.photoURL = photoURL;
        this.status = status;
        this.deliveryDuration = deliveryDuration;
        this.deliveryType = deliveryType;
        this.specialOrder = specialOrder;
        this.createdDate = createdDate;
    }

    /**
     *
     * @return
     */
    public int getProductID() {
        return productID;
    }

    /**
     *
     * @param productID
     */
    public void setProductID(int productID) {
        this.productID = productID;
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
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @param productName
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public int getCatID() {
        return catID;
    }

    /**
     *
     * @param catID
     */
    public void setCatID(int catID) {
        this.catID = catID;
    }

    /**
     *
     * @return
     */
    public double getPrice() {
        return price;
    }

    /**
     *
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     *
     * @return
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     *
     * @param quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
    public int getDeliveryDuration() {
        return deliveryDuration;
    }

    /**
     *
     * @param deliveryDuration
     */
    public void setDeliveryDuration(int deliveryDuration) {
        this.deliveryDuration = deliveryDuration;
    }

    /**
     *
     * @return
     */
    public String getDeliveryType() {
        return deliveryType;
    }

    /**
     *
     * @param deliveryType
     */
    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    /**
     *
     * @return
     */
    public String getSpecialOrder() {
        return specialOrder;
    }

    /**
     *
     * @param specialOrder
     */
    public void setSpecialOrder(String specialOrder) {
        this.specialOrder = specialOrder;
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
