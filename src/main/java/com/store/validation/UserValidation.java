/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.validation;

import com.store.beans.UserBean;
import com.store.utilities.Utilities;
import com.store.utilities.Validators;

/**
 *
 * @author Abdullah
 */
public class UserValidation {
    
    private UserBean userBean;

    /**
     *
     */
    public UserValidation() {
    }

    /**
     *
     * @param userBean
     */
    public UserValidation(UserBean userBean) {
        this.userBean = userBean;
    }

    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateUserLogin() throws Exception {
       
        if ( userBean.getUserName().trim().length() == 0 ){
                  throw new Exception("1068");
            }else if ( userBean.getPassword().trim().length() == 0 ){
                  throw new Exception("1069");
            }else if ( userBean.getToken().trim().length() == 0 ){
                  throw new Exception("1070");
            }
        
        return true;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateRegister() throws Exception {
       
        if ( userBean.getFullName().trim().length() == 0 ){
                  throw new Exception("1104");
            }else if ( userBean.getToken().trim().length() == 0 ){
                  throw new Exception("1070");
            }else if ( userBean.getEmail().trim().length() == 0 ){
                  throw new Exception("1051");
            }else if ( userBean.getUserName().trim().length() == 0 ){
                  throw new Exception("1068");
            }else if ( userBean.getPassword()!= null && userBean.getPassword().trim().length() == 0 ){
                  throw new Exception("1069");
            }else if ( (Integer)userBean.getMobile() != null && String.valueOf(userBean.getMobile()).trim().length() == 0 ){
                  throw new Exception("1107");
            }else if ( userBean.getAddress()!= null && userBean.getAddress().trim().length() == 0 ){
                  throw new Exception("1054");
            }
        
        if ( userBean.getEmail() != null && userBean.getEmail().trim().length() > 0){
            if( !Utilities.validateEmail(userBean.getEmail())) {
                  throw new Exception("1004");
            }
        }
        
        /*
        if( !Validators.isMobileNumber(String.valueOf(userBean.getMobile())) ){
            throw new Exception("1112");
        }
       */
        return true;
    }
     
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateUpdateUser() throws Exception {
       
        if ( (Integer)userBean.getUserID()!= null && String.valueOf(userBean.getUserID()).trim().length() == 0 ){
                  throw new Exception("1075");
            }else if ( userBean.getFullName() != null && userBean.getFullName().trim().length() == 0 ){
                  throw new Exception("1104");
            }else if ( userBean.getAddress()!= null && userBean.getAddress().trim().length() == 0 ){
                  throw new Exception("1054");
            }else if ( userBean.getToken().trim().length() == 0 ){
                  throw new Exception("1070");
            }else if ( userBean.getEmail().trim().length() == 0 ){
                  throw new Exception("1051");
            }else if ( (Integer)userBean.getMobile() != null && String.valueOf(userBean.getMobile()).trim().length() == 0 ){
                   throw new Exception("1107");
            }
        
        if ( userBean.getEmail() != null && userBean.getEmail().trim().length() > 0){
            if( !Utilities.validateEmail(userBean.getEmail())) {
                  throw new Exception("1004");
            }
        }
        return true;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateUserInfo() throws Exception {
       
        if ( (Integer)userBean.getUserID()!= null && String.valueOf(userBean.getUserID()).trim().length() == 0 ){
                  throw new Exception("1075");
            }
        
        return true;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateUserForgetPassword() throws Exception {
       
        
        if ( userBean.getEmail()!= null && userBean.getEmail().trim().length() == 0 ){
                  throw new Exception("1072");
        }
        
       if ( userBean.getEmail()!= null && userBean.getEmail().trim().length() > 0 ){
            if( !Utilities.validateEmail(userBean.getEmail())) {
                  throw new Exception("1004");
            }
        }
        
        return true;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateResetPassword() throws Exception {
        
        if ( userBean.getPassword()!= null && userBean.getPassword().trim().length() == 0 ){
                  throw new Exception("1069");
            }else if ( (Integer)userBean.getUserID()!= null && String.valueOf(userBean.getUserID()).trim().length() == 0 ){
                throw new Exception("1025");
            }
        
        return true;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateDeleteUser() throws Exception {
       
        if ( (Integer)userBean.getUserID()!= null && String.valueOf(userBean.getUserID()).trim().length() == 0 ){
                  throw new Exception("1075");
            }
        
        return true;
    }
    
    
}
