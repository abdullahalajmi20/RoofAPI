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
public class CategoryBean {
    
    private int catID;
    private String catName;
    private String photoURL;

    /**
     * Constructor
     * @param catID
     * @param catName
     * @param photoURL
     */
    public CategoryBean(int catID, String catName, String photoURL) {
        this.catID = catID;
        this.catName = catName;
        this.photoURL = photoURL;
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
    public String getCatName() {
        return catName;
    }

    /**
     *
     * @param catName
     */
    public void setCatName(String catName) {
        this.catName = catName;
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
