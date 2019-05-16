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
public class ExtraErrorsBean {
    
    private String domain;
    private String reason;
    private String message;
    private String locationType;
    private String location;
    private String extendedHelp;
    private String sendReport;

    /**
     * Constructor
     * @param domain
     * @param reason
     * @param message
     * @param locationType
     * @param location
     * @param extendedHelp
     * @param sendReport
     */
    public ExtraErrorsBean(String domain, String reason, String message, String locationType, String location, String extendedHelp, String sendReport) {
        this.domain = domain;
        this.reason = reason;
        this.message = message;
        this.locationType = locationType;
        this.location = location;
        this.extendedHelp = extendedHelp;
        this.sendReport = sendReport;
    }

    /**
     *
     * @return
     */
    public String getDomain() {
        return domain;
    }

    /**
     *
     * @param domain
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     *
     * @return
     */
    public String getReason() {
        return reason;
    }

    /**
     *
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     *
     * @return
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return
     */
    public String getLocationType() {
        return locationType;
    }

    /**
     *
     * @param locationType
     */
    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    /**
     *
     * @return
     */
    public String getLocation() {
        return location;
    }

    /**
     *
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     *
     * @return
     */
    public String getExtendedHelp() {
        return extendedHelp;
    }

    /**
     *
     * @param extendedHelp
     */
    public void setExtendedHelp(String extendedHelp) {
        this.extendedHelp = extendedHelp;
    }

    /**
     *
     * @return
     */
    public String getSendReport() {
        return sendReport;
    }

    /**
     *
     * @param sendReport
     */
    public void setSendReport(String sendReport) {
        this.sendReport = sendReport;
    }
    
    
}
