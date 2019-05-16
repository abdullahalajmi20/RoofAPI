/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.beans;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Abdullah
 */

@XmlRootElement
public class ErrorBean {
    
    private String apiVersion;
    private ErrorDetailsBean error;

    /**
     * Constructor
     */
    public ErrorBean() {
    }

    /**
     * Constructor
     * @param apiVersion
     * @param error
     */
    public ErrorBean(String apiVersion, ErrorDetailsBean error) {
        this.apiVersion = apiVersion;
        this.error = error;
    }

    /**
     *
     * @return
     */
    public String getApiVersion() {
        return apiVersion;
    }

    /**
     *
     * @param apiVersion
     */
    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    /**
     *
     * @return
     */
    public ErrorDetailsBean getError() {
        return error;
    }

    /**
     *
     * @param error
     */
    public void setError(ErrorDetailsBean error) {
        this.error = error;
    }
    
    
    
}
