/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.resources.user;

import com.store.beans.UserBean;
import com.store.implementation.UserImplementation;
import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

/**
 *
 * @author Abdullah
 */
public class UserManager {
    
    /**
     *
     * @param userBean
     * @param req
     * @return
     * @throws Exception
     */
    public UserBean loginUser(UserBean userBean, HttpServletRequest req) throws Exception 
    {
        UserImplementation userImplementation = new UserImplementation(req,"login", userBean);
                                 
        return (UserBean) userImplementation.implementUserMethod();
    }
    
    /**
     *
     * @param userBean
     * @param req
     * @param uploadedInputStream
     * @param body
     * @return
     * @throws Exception
     */
    public UserBean registerUser(UserBean userBean, HttpServletRequest req,InputStream uploadedInputStream, FormDataBodyPart body) throws Exception 
    {
        UserImplementation userImplementation = new UserImplementation(uploadedInputStream,body,req,"register", userBean);
                                 
        return (UserBean) userImplementation.implementUserMethod();
    }
    
    /**
     *
     * @param userBean
     * @param req
     * @param uploadedInputStream
     * @param body
     * @return
     * @throws Exception
     */
    public UserBean updateUser(UserBean userBean, HttpServletRequest req,InputStream uploadedInputStream, FormDataBodyPart body) throws Exception 
    {
        UserImplementation userImplementation = new UserImplementation(uploadedInputStream,body, req,"update", userBean);
                                 
        return (UserBean) userImplementation.implementUserMethod();
    }
    
    /**
     *
     * @param userBean
     * @param req
     * @return
     * @throws Exception
     */
    public UserBean getUserInfo(UserBean userBean, HttpServletRequest req) throws Exception 
    {
        UserImplementation userImplementation = new UserImplementation(req,"info", userBean);
                                 
        return (UserBean) userImplementation.implementUserMethod();
    }
    
    /**
     *
     * @param userBean
     * @param req
     * @return
     * @throws Exception
     */
    public UserBean forgetPassword(UserBean userBean, HttpServletRequest req) throws Exception 
    {
        UserImplementation userImplementation = new UserImplementation(req,"forget", userBean);
                                 
        return (UserBean) userImplementation.implementUserMethod();
    }
    
    /**
     *
     * @param userBean
     * @param req
     * @return
     * @throws Exception
     */
    public UserBean resetPassword(UserBean userBean, HttpServletRequest req) throws Exception 
    {
        UserImplementation userImplementation = new UserImplementation(req,"reset", userBean);
                                 
        return (UserBean) userImplementation.implementUserMethod();
    }
    
    /**
     *
     * @param userBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<UserBean> searchUsers(UserBean userBean, HttpServletRequest req) throws Exception 
    {
        UserImplementation userImplementation = new UserImplementation(req,"search", userBean);
                                 
        return (ArrayList<UserBean>) userImplementation.implementUserMethod();
    }
    
    /**
     *
     * @param userBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<UserBean> getStores(UserBean userBean, HttpServletRequest req) throws Exception 
    {
        UserImplementation userImplementation = new UserImplementation(req,"stores", userBean);
                                 
        return (ArrayList<UserBean>) userImplementation.implementUserMethod();
    }
    
    /**
     *
     * @param userBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<UserBean> deleteUser(UserBean userBean, HttpServletRequest req) throws Exception 
    {
        UserImplementation userImplementation = new UserImplementation(req,"delete", userBean);
                                 
        return (ArrayList<UserBean>) userImplementation.implementUserMethod();
    }
    
    
}
