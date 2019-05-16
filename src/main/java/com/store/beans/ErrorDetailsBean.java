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
public class ErrorDetailsBean {
    
    private int code;
    private String message;
    private ExtraErrorsBean[] errors;

    /**
     * Constructor
     * @param code
     * @param message
     * @param errors
     */
    public ErrorDetailsBean(int code, String message, ExtraErrorsBean[] errors) {
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    /**
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     *
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
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
    public ExtraErrorsBean[] getErrors() {
        return errors;
    }

    /**
     *
     * @param errors
     */
    public void setErrors(ExtraErrorsBean[] errors) {
        this.errors = errors;
    }
    
    
}
