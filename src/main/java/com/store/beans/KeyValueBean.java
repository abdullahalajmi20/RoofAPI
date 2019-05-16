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
public class KeyValueBean {
    
    private int serviceID;
    private String valueNameEn;

    /**
     * Constructor
     */
    public KeyValueBean() {
    }

    /**
     * Constructor
     * @param serviceID
     * @param valueNameEn
     */
    public KeyValueBean(int serviceID, String valueNameEn) {
        this.serviceID = serviceID;
        this.valueNameEn = valueNameEn;
    }

    /**
     *
     * @return
     */
    public int getServiceID() {
        return serviceID;
    }

    /**
     *
     * @param serviceID
     */
    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    /**
     *
     * @return
     */
    public String getValueNameEn() {
        return valueNameEn;
    }

    /**
     *
     * @param valueNameEn
     */
    public void setValueNameEn(String valueNameEn) {
        this.valueNameEn = valueNameEn;
    }
 
}
