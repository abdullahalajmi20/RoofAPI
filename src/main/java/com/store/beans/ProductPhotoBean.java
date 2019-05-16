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
public class ProductPhotoBean {
    
    private int photoID;
    private int productID;
    private String photoURL;

    /**
     * Constructor
     */
    public ProductPhotoBean() {
    }

    /**
     * Constructor
     * @param photoID
     * @param productID
     * @param photoURL
     */
    public ProductPhotoBean(int photoID, int productID, String photoURL) {
        this.photoID = photoID;
        this.productID = productID;
        this.photoURL = photoURL;
    }

    /**
     *
     * @return
     */
    public int getPhotoID() {
        return photoID;
    }

    /**
     *
     * @param photoID
     */
    public void setPhotoID(int photoID) {
        this.photoID = photoID;
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

    

    
}
