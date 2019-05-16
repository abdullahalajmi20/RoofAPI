/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.implementation;

import com.store.beans.UserBean;
import com.store.utilities.Email;
import com.store.utilities.LocalProperties;
import com.store.utilities.RandomString;
import com.store.utilities.UploadFile;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

/**
 *
 * @author Abdullah
 */
public class UserImplementation extends SuperImplementation{
    
    public HttpServletRequest req;
    public String functionName;
    public UserBean userBean;
    public InputStream uploadedInputStream;
    public FormDataBodyPart body;
    private String language;
    private HttpSession session = null;
    DateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    
    /**
     * User Implementation Constructor
     */
    public UserImplementation() {
       super();
    }

    /**
     * User Implementation Constructor
     * @param req
     * @param functionName
     */
    public UserImplementation(HttpServletRequest req, String functionName) {
        super();
        this.req = req;
        this.functionName = functionName;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language.equals(""))
            language="en-us";
    }
                                             
    /**
     * User Implementation Constructor
     * @param req
     * @param functionName
     * @param userBean
     */
    public UserImplementation(HttpServletRequest req, String functionName, UserBean userBean) {
        super();
        this.req = req;
        this.functionName = functionName;
        this.userBean = userBean;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language == null)
            language="en-us";
    }
    
    /**
     * User Implementation Constructor
     * @param uploadedInputStream
     * @param body
     * @param req
     * @param functionName
     * @param userBean
     */
    public UserImplementation(InputStream uploadedInputStream, FormDataBodyPart body,
                    HttpServletRequest req,String functionName, UserBean userBean) {
        super();
        this.uploadedInputStream = uploadedInputStream;
        this.body = body;
        this.req = req;
        this.functionName = functionName;
        this.userBean = userBean;
        
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language.equals(""))
            language="en-us";
    }
    
    // get connection to database
    private void getConnection() {
        con = this.dbc.getMySQLConnection();
        Logger.getLogger(UserImplementation.class.getName()).log(Level.SEVERE, "Connection Basic >>>>>" + con);
    }
    
    // User login
    private UserBean userLogin() throws SQLException, Exception {

        getConnection();
        UserBean result = null;
        String successMsg = "";
        int successId = 0;
//Call Database Stored Procedure
        statement = con.prepareCall("{call spUserLogin(?,?,?,?,?)}");
        
        statement.setString(1, userBean.getUserName());
        statement.setString(2, userBean.getPassword());
        statement.setString(3, userBean.getToken());
        
        statement.registerOutParameter(4, Types.INTEGER);
        statement.registerOutParameter(5, Types.VARCHAR);

        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(4);
        successMsg = statement.getString(5); 

        if(successId > 0)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate"); 
                String createdDateStr = dateFormat.format(createdDate);
                    
                result = new UserBean(rs.getInt("UserID"),rs.getString("FullName"),rs.getInt("GovID"),
                        rs.getString("UserName"),rs.getString("Pass"),rs.getString("Email"),
                        rs.getInt("Mobile"),rs.getString("Address"),rs.getInt("Type"),
                        rs.getInt("Status"),rs.getString("StoreName"),rs.getInt("CatID"),rs.getString("PhotoURL"),rs.getString("Token"),createdDateStr);
                }
               
              hasResults = statement.getMoreResults();
            }
        }


       if(successId > 0){
           return result;
       } else{
           throw new Exception(successMsg);
       }
       
    }
    
    // Register New User
    private UserBean registerUser() throws SQLException, Exception {

        getConnection();
        UserBean result = null;
        String successMsg = "";
        int successId = 0;
        
        String uploadedPath = "";
        String uploadedPathRelative = "";
        String photoName = "";

        if( uploadedInputStream != null && body != null  ){
            String mimeType = body.getMediaType().toString();
            System.out.println("mimeType = "+mimeType);

            RandomString  r = new RandomString(20);
            photoName = r.nextString();
            photoName = photoName+".jpg";
            LocalProperties local = new LocalProperties();
            String path= local.getString("uploadedPath");
            uploadedPathRelative = local.getString("uploadedPathRelative");
            uploadedPath = path;  
       }
        
        if(!uploadedPath.equals("")){
            UploadFile upload = new UploadFile();
            upload.uploadFile(photoName, uploadedPath, uploadedInputStream);
            userBean.setPhotoURL(uploadedPathRelative+photoName);
        }
        //Call Database Stored Procedure
        statement = con.prepareCall("{call spRegisterUser(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
        
        statement.setString(1, userBean.getFullName());
        statement.setInt(2, userBean.getGovID());
        statement.setString(3, userBean.getUserName());
        statement.setString(4, userBean.getPassword());
        statement.setInt(5, userBean.getMobile());
        statement.setString(6, userBean.getEmail());
        statement.setString(7, userBean.getAddress());
        statement.setInt(8, userBean.getType());
        statement.setString(9, userBean.getPhotoURL());
        statement.setString(10, userBean.getStoreName());
        statement.setInt(11, userBean.getStoreCatID());
        statement.setString(12, userBean.getToken());
        
        statement.registerOutParameter(13, Types.INTEGER);
        statement.registerOutParameter(14, Types.VARCHAR);

        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(13);
        successMsg = statement.getString(14); 

        if(successId > 0)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("createdDate"); 
                String createdDateStr = dateFormat.format(createdDate);
                        
                result = new UserBean(rs.getInt("UserID"),rs.getString("FullName"),rs.getInt("GovID"),
                        rs.getString("UserName"),rs.getString("Pass"),rs.getString("Email"),
                        rs.getInt("Mobile"),rs.getString("Address"),rs.getInt("Type"),
                        rs.getInt("Status"),rs.getString("StoreName"),rs.getInt("CatID"),rs.getString("PhotoURL"),rs.getString("Token"),createdDateStr);
                }
               
              hasResults = statement.getMoreResults();
            }
        }

       if(successId > 0){
           return result;
       } else{
           throw new Exception(successMsg);
       }
       
    }
    
    
    // Update Users
    private UserBean updateUser() throws SQLException, Exception {

        getConnection();
        UserBean result = null;
        String successMsg = "";
        int successId = 0;

        String uploadedPath = "";
        String uploadedPathRelative = "";
        String photoName = "";

        if( uploadedInputStream != null && body != null  ){
            String mimeType = body.getMediaType().toString();
            System.out.println("mimeType = "+mimeType);

            RandomString  r = new RandomString(20);
            photoName = r.nextString();
            photoName = photoName+".jpg";
            LocalProperties local = new LocalProperties();
            String path= local.getString("uploadedPath");
            uploadedPathRelative = local.getString("uploadedPathRelative");
            uploadedPath = path;  
       }
        
        if(!uploadedPath.equals("")){
            UploadFile upload = new UploadFile();
            upload.uploadFile(photoName, uploadedPath, uploadedInputStream);
            userBean.setPhotoURL(uploadedPathRelative+photoName);
        }
        
        //Call Database Stored Procedure
        statement = con.prepareCall("{call spUpdateUser(?,?,?,?,?,?,?,?,?,?,?,?)}");

        statement.setInt(1, userBean.getUserID());
        statement.setString(2, userBean.getFullName());
        statement.setInt(3, userBean.getGovID());
        statement.setInt(4, userBean.getMobile());
        statement.setString(5, userBean.getEmail());
        statement.setString(6, userBean.getAddress());
        statement.setString(7, userBean.getPhotoURL());
        statement.setString(8, userBean.getStoreName());
        statement.setInt(9, userBean.getStoreCatID());
        statement.setString(10, userBean.getToken());
        
        statement.registerOutParameter(11, Types.INTEGER);
        statement.registerOutParameter(12, Types.VARCHAR);

        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(11);
        successMsg = statement.getString(12); 

        if(successId > 0)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate"); 

                String createdDateStr = dateFormat.format(createdDate);
                //String createdDateStr = (createdDate != null)? dateFormat.format(createdDate):null;
                        
                result = new UserBean(rs.getInt("UserID"),rs.getString("FullName"),rs.getInt("GovID"),
                        rs.getString("UserName"),rs.getString("Pass"),rs.getString("Email"),
                        rs.getInt("Mobile"),rs.getString("Address"),rs.getInt("Type"),
                        rs.getInt("Status"),rs.getString("StoreName"),rs.getInt("CatID"),rs.getString("PhotoURL"),rs.getString("Token"),createdDateStr);
                }
               
              hasResults = statement.getMoreResults();
            }
        }

       if(successId > 0){
           return result;
       } else{
           throw new Exception(successMsg);
       }
       
    }
    
    // Get User Info
    private UserBean getUserInfo() throws SQLException, Exception {

        getConnection();
        UserBean result = null;
        String successMsg = "";
        int successId = 0;

        //Call Database Stored Procedure
        statement = con.prepareCall("{call spGetUserInfo(?,?,?)}");
        
        statement.setInt(1, userBean.getUserID());
        
        statement.registerOutParameter(2, Types.INTEGER);
        statement.registerOutParameter(3, Types.VARCHAR);

        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(2);
        successMsg = statement.getString(3); 

        if(successId > 0)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate");
                String createdDateStr = dateFormat.format(createdDate);
           
                result = new UserBean(rs.getInt("UserID"),rs.getString("FullName"),rs.getInt("GovID"),
                        rs.getString("UserName"),rs.getString("Pass"),rs.getString("Email"),
                        rs.getInt("Mobile"),rs.getString("Address"),rs.getInt("Type"),
                        rs.getInt("Status"),rs.getString("StoreName"),rs.getInt("CatID"),rs.getString("PhotoURL"),rs.getString("Token"),createdDateStr);
                }
               
              hasResults = statement.getMoreResults();
            }
        }

       if(successId > 0){
           return result;
       } else{
           throw new Exception(successMsg);
       }
       
    }
    
    // Forgot user Password 
    private UserBean forgetPassword() throws SQLException, Exception {

        getConnection();
        UserBean result = null;
        String successMsg = "";
        int successId = 0;
        String password = "";
        String fullName = "";

        //Call Database Stored Procedure
        statement = con.prepareCall("{call spUserForgetPass(?,?,?)}");

        statement.setString(1, userBean.getEmail());
        
        statement.registerOutParameter(2, Types.INTEGER);
        statement.registerOutParameter(3, Types.VARCHAR);
         
        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(2);
        successMsg = statement.getString(3); 

        if(successId > 0)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate"); 

                String createdDateStr = dateFormat.format(createdDate);
                //String createdDateStr = (createdDate != null)? dateFormat.format(createdDate):null;
                password = rs.getString("Pass");
                fullName = rs.getString("FullName");
                
                result = new UserBean(rs.getInt("UserID"),rs.getString("FullName"),rs.getInt("GovID"),
                        rs.getString("UserName"),rs.getString("Pass"),rs.getString("Email"),
                        rs.getInt("Mobile"),rs.getString("Address"),rs.getInt("Type"),
                        rs.getInt("Status"),rs.getString("StoreName"),rs.getInt("CatID"),rs.getString("PhotoURL"),rs.getString("Token"),createdDateStr);
                }
               
              hasResults = statement.getMoreResults();
            }
            String message = "Welcome "+fullName+"<BR/>Your Autism Application Password : "+password;
            Email email = new Email();
            
            email.sendEmail(message, userBean.getEmail(),"Your Autism Application Password");
        }

       if(successId > 0){
           return result;
       } else{
           throw new Exception(successMsg);
       }
       
    }
    
    // Reset user password
    private UserBean resetPassword() throws SQLException, Exception {

        getConnection();
        UserBean result = null;
        String successMsg = "";
        int successId = 0;

        //Call Database Stored Procedure
        statement = con.prepareCall("{call spResetUserPassword(?,?,?,?)}");

        statement.setInt(1, userBean.getUserID());
        statement.setString(2, userBean.getPassword());
        
        statement.registerOutParameter(3, Types.INTEGER);
        statement.registerOutParameter(4, Types.VARCHAR);

        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(3);
        successMsg = statement.getString(4); 

        if(successId > 0)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate"); 
                String createdDateStr = dateFormat.format(createdDate);
                        
                result = new UserBean(rs.getInt("UserID"),rs.getString("FullName"),rs.getInt("GovID"),
                        rs.getString("UserName"),rs.getString("Pass"),rs.getString("Email"),
                        rs.getInt("Mobile"),rs.getString("Address"),rs.getInt("Type"),
                        rs.getInt("Status"),rs.getString("StoreName"),rs.getInt("CatID"),rs.getString("PhotoURL"),rs.getString("Token"),createdDateStr);
                }
               
              hasResults = statement.getMoreResults();
            }
        }

       if(successId > 0){
           return result;
       } else{
           throw new Exception(successMsg);
       }
       
    }
    
    // Search users
    private ArrayList<UserBean> searchUsers() throws SQLException, Exception {

        getConnection();
        ArrayList<UserBean> result = null;
        String successMsg = "";
        int successId = 0;

        //Call Database Stored Procedure
        statement = con.prepareCall("{call spSearchUsers(?,?,?)}");
        
        statement.setString(1, userBean.getFullName());
        
        statement.registerOutParameter(2, Types.INTEGER);
        statement.registerOutParameter(3, Types.VARCHAR);

        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(2);
        successMsg = statement.getString(3); 

        if(successId > 0)
        {
            result = new ArrayList<UserBean>();
            
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate"); 
                String createdDateStr = dateFormat.format(createdDate);
                        
                result.add(new UserBean(rs.getInt("UserID"),rs.getString("FullName"),rs.getInt("GovID"),
                        rs.getString("UserName"),rs.getString("Pass"),rs.getString("Email"),
                        rs.getInt("Mobile"),rs.getString("Address"),rs.getInt("Type"),
                        rs.getInt("Status"),rs.getString("StoreName"),rs.getInt("CatID"),rs.getString("PhotoURL"),rs.getString("Token"),createdDateStr) );
                }
               
              hasResults = statement.getMoreResults();
            }
        }

       if(successId > 0){
           return result;
       } else{
           throw new Exception(successMsg);
       }
       
    }
    
    // Get Stores list
    private ArrayList<UserBean> getStores() throws SQLException, Exception {

        getConnection();
        ArrayList<UserBean> result = null;
        String successMsg = "";
        int successId = 0;

        //Call Database Stored Procedure
        statement = con.prepareCall("{call spGetStores(?,?)}");
        
        statement.registerOutParameter(1, Types.INTEGER);
        statement.registerOutParameter(2, Types.VARCHAR);

        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(1);
        successMsg = statement.getString(2); 

        if(successId > 0)
        {
            result = new ArrayList<UserBean>();
            
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate"); 
                String createdDateStr = dateFormat.format(createdDate);
                        
                result.add(new UserBean(rs.getInt("UserID"),rs.getString("FullName"),rs.getInt("GovID"),
                        rs.getString("UserName"),rs.getString("Pass"),rs.getString("Email"),
                        rs.getInt("Mobile"),rs.getString("Address"),rs.getInt("Type"),
                        rs.getInt("Status"),rs.getString("StoreName"),rs.getInt("CatID"),rs.getString("PhotoURL"),rs.getString("Token"),createdDateStr) );
                }
               
              hasResults = statement.getMoreResults();
            }
        }

       if(successId > 0){
           return result;
       } else{
           throw new Exception(successMsg);
       }
       
    }
    
    // Delete User
    private ArrayList<UserBean> deleteUser() throws SQLException, Exception {

        getConnection();
        ArrayList<UserBean> result = null;
        String successMsg = "";
        int successId = 0;

        //Call Database Stored Procedure
        statement = con.prepareCall("{call spDeleteUser(?,?,?)}");
        
        statement.setInt(1, userBean.getUserID());
        
        statement.registerOutParameter(2, Types.INTEGER);
        statement.registerOutParameter(3, Types.VARCHAR);

        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(2);
        successMsg = statement.getString(3); 

        if(successId > 0)
        {
            result = new ArrayList<UserBean>();
            
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate"); 

                String createdDateStr = dateFormat.format(createdDate);
                        
                result.add(new UserBean(rs.getInt("UserID"),rs.getString("FullName"),rs.getInt("GovID"),
                        rs.getString("UserName"),rs.getString("Pass"),rs.getString("Email"),
                        rs.getInt("Mobile"),rs.getString("Address"),rs.getInt("Type"),
                        rs.getInt("Status"),rs.getString("StoreName"),rs.getInt("CatID"),rs.getString("PhotoURL"),rs.getString("Token"),createdDateStr) );
                }
               
              hasResults = statement.getMoreResults();
            }
        }

       if(successId > 0){
           return result;
       } else{
           throw new Exception(successMsg);
       }
       
    }
    
    /**
     * Call APIs Methods
     * @return
     * @throws Exception
     */
    public Object implementUserMethod() throws Exception {
        Object result;
        result = this.generalSkelton(this.req, (Callable) () -> {
            switch (functionName) {
                case "login":
                    return (UserBean) userLogin();
                case "register":
                    return (UserBean) registerUser();
                case "update":
                    return (UserBean) updateUser();
                case "info":
                    return (UserBean) getUserInfo();
                case "forget":
                    return (UserBean) forgetPassword();
                case "reset":
                    return (UserBean) resetPassword();
                case "search":
                    return (ArrayList<UserBean>) searchUsers();
                case "stores":
                    return (ArrayList<UserBean>) getStores();
                case "delete":
                    return (ArrayList<UserBean>) deleteUser();
                default:
                    return null;

            }
        });
        return result;
    }
}
