/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.resources.user;

import com.store.beans.UserBean;
import com.store.validation.UserValidation;
import java.io.InputStream;
import java.util.ArrayList;
import javax.ejb.Stateful;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import lombok.NonNull;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author Abdullah
 */

@Stateful
@Path("/user")
public class UserResources {
    
    /**
     *
     * @param req
     * @param userName
     * @param password
     * @param token
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/login")
    public UserBean loginUser(
                    @Context HttpServletRequest req,
                    @FormParam("userName") @NonNull String userName,
                    @FormParam("password") @NonNull String password,
                    @FormParam("token") @NonNull String token
           ) throws Exception {
         
        UserManager  userManager = new UserManager();
        UserBean userBean = new UserBean();
        userBean.setUserName(userName);
        userBean.setPassword(password);
        userBean.setToken(token);
        
        UserValidation userValidation = new UserValidation(userBean);
        userValidation.validateUserLogin();
        
        return  userManager.loginUser(userBean, req);
    }

    /**
     *
     * @param req
     * @param fullName
     * @param govID
     * @param address
     * @param userName
     * @param password
     * @param mobile
     * @param email
     * @param type
     * @param storeName
     * @param storeCatID
     * @param uploadedInputStream
     * @param body
     * @param token
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/registerUser")
    public UserBean registerUser(
                    @Context HttpServletRequest req,
                    @FormDataParam("fullName") @NonNull String fullName,
                    @FormDataParam("govID") @NonNull Integer govID,
                    @FormDataParam("address") @NonNull String address,
                    @FormDataParam("userName") @NonNull String userName,
                    @FormDataParam("password") @NonNull String password,
                    @FormDataParam("mobile") @NonNull Integer mobile,
                    @FormDataParam("email") @NonNull String email,
                    @FormDataParam("type") @NonNull Integer type, //1-User, 2-Store
                    @FormDataParam("storeName")  String storeName,
                    @FormDataParam("storeCatID") int storeCatID,
                    @FormDataParam("photo") InputStream uploadedInputStream,
                    @FormDataParam("photo") final FormDataBodyPart body,
                    @FormDataParam("token") @NonNull String token
           ) throws Exception {
        UserManager  userManager = new UserManager();
        UserBean userBean = new UserBean();
        userBean.setFullName(fullName);
        userBean.setUserName(userName);
        userBean.setPassword(password);
        userBean.setEmail(email);
        userBean.setType(type);
        userBean.setMobile(mobile);
        userBean.setAddress(address);
        userBean.setStoreName(storeName);
        userBean.setStoreCatID(storeCatID);
        userBean.setGovID(govID);
        userBean.setToken(token);
        
        UserValidation userValidation = new UserValidation(userBean);
        userValidation.validateRegister();
        
        return  userManager.registerUser(userBean, req,uploadedInputStream,body);
    }
    
    /**
     *
     * @param req
     * @param userID
     * @param fullName
     * @param govID
     * @param address
     * @param email
     * @param mobile
     * @param storeName
     * @param storeCatID
     * @param uploadedInputStream
     * @param body
     * @param token
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/updateUser")
    public UserBean updateUser(
                    @Context HttpServletRequest req,
                    @FormDataParam("userID") @NonNull Integer userID,
                    @FormDataParam("fullName") @NonNull String fullName,
                    @FormDataParam("govID") @NonNull Integer govID,
                    @FormDataParam("address") @NonNull String address,
                    @FormDataParam("email") @NonNull String email,
                    @FormDataParam("mobile") @NonNull Integer mobile,
                    @FormDataParam("storeName")  String storeName,
                    @FormDataParam("storeCatID") int storeCatID,
                    @FormDataParam("photo") InputStream uploadedInputStream,
                    @FormDataParam("photo") final FormDataBodyPart body,
                    @FormDataParam("token") @NonNull String token
           ) throws Exception {
                
        UserManager  userManager = new UserManager();
        UserBean userBean = new UserBean();
        userBean.setUserID(userID);
        userBean.setGovID(govID);
        userBean.setFullName(fullName);
        userBean.setMobile(mobile);
        userBean.setEmail(email);
        userBean.setStoreName(storeName);
        userBean.setStoreCatID(storeCatID);
        userBean.setToken(token);
        
        UserValidation userValidation = new UserValidation(userBean);
        userValidation.validateUpdateUser();
        
        return  userManager.updateUser(userBean, req,uploadedInputStream,body);
    }
    
    /**
     *
     * @param req
     * @param userID
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getUserInfo/{USER_ID}")
    public UserBean getUserInfo(
                        @Context HttpServletRequest req,
                        @PathParam("USER_ID") @NonNull Integer userID
                               ) throws Exception {
        
        UserManager  userManager = new UserManager();
        UserBean userBean = new UserBean();
        userBean.setUserID(userID);
        
        UserValidation userValidation = new UserValidation(userBean);
        userValidation.validateUserInfo();
        
        return userManager.getUserInfo(userBean, req);
    }
    
    /**
     *
     * @param req
     * @param email
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/forgetPassword")
    public UserBean forgetPassword(
                    @Context HttpServletRequest req,
                    @FormParam("email") @NonNull String email
           ) throws Exception {
        UserManager  userManager = new UserManager();
        UserBean userBean = new UserBean();
        userBean.setEmail(email);
       
        UserValidation userValidation = new UserValidation(userBean);
        userValidation.validateUserForgetPassword();
        
        return  userManager.forgetPassword(userBean, req);
    }
    
    /**
     *
     * @param req
     * @param userID
     * @param newPassword
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/resetPassword")
    public UserBean resetPassword(
                    @Context HttpServletRequest req,
                    @FormParam("userID") @NonNull Integer userID,
                    @FormParam("newPassword") @NonNull String newPassword
           ) throws Exception {
        UserManager  userManager = new UserManager();
        UserBean userBean = new UserBean();
        userBean.setUserID(userID);
        userBean.setPassword(newPassword);
       
        
        UserValidation userValidation = new UserValidation(userBean);
        userValidation.validateResetPassword();
        
        return  userManager.resetPassword(userBean, req);
    }
    
    /**
     *
     * @param req
     * @param fullName
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/searchUser")
    public UserBean[]  searchUser(
      @Context HttpServletRequest req,
      @QueryParam("fullName") @NonNull String fullName
    ) throws Exception {
         ArrayList<UserBean> usersArray = null;
        UserManager  userManager = new UserManager();
        UserBean userBean = new UserBean();
        userBean.setFullName(fullName.trim());
        usersArray = userManager.searchUsers(userBean, req);
        return  usersArray.toArray(new UserBean[usersArray.size()]) ;
    }
    
    /**
     *
     * @param req
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getStores")
    public UserBean[]  getStores(
      @Context HttpServletRequest req
    ) throws Exception {
         ArrayList<UserBean> usersArray = null;
        UserManager  userManager = new UserManager();

        usersArray = userManager.getStores(null, req);
        return  usersArray.toArray(new UserBean[usersArray.size()]) ;
    }
    
    /**
     *
     * @param req
     * @param userID
     * @return
     * @throws Exception
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/deleteUser/{USER_ID}")
    public UserBean[] deleteUser(
                     @Context HttpServletRequest req,
                     @PathParam("USER_ID") @NonNull Integer userID    
           ) throws Exception {
        
        ArrayList<UserBean> usersArray = null;
        
        UserManager  userManager = new UserManager();
        UserBean userBean = new UserBean();
        userBean.setUserID(userID);
       
        UserValidation userValidation = new UserValidation(userBean);
        userValidation.validateDeleteUser();
        
        usersArray = userManager.deleteUser(userBean, req);
        return  usersArray.toArray(new UserBean[usersArray.size()]) ;
    }
    
    
}
