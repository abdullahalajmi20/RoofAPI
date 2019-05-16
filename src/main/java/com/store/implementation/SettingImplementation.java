/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.implementation;

import com.store.beans.CategoryBean;
import com.store.beans.KeyValueBean;
import static com.store.implementation.SuperImplementation.con;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
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
public class SettingImplementation extends SuperImplementation{
    
    public HttpServletRequest req;
    public String functionName;
    public KeyValueBean keyValueBean;
    public InputStream uploadedInputStream;

    public FormDataBodyPart body;
    private String language;
    private HttpSession session = null;
    public SettingImplementation() {
       super();
    }

    /**
     * Setting Implementation Constructor
     * @param req
     * @param functionName
     */
    public SettingImplementation(HttpServletRequest req, String functionName) {
        super();
        this.req = req;
        this.functionName = functionName;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language.equals(""))
            language="en-us";
    }
                                             
    /**
     * Setting Implementation Constructor
     * @param req
     * @param functionName
     * @param keyValueBean
     */
    public SettingImplementation(HttpServletRequest req, String functionName, KeyValueBean keyValueBean) {
        super();
        this.req = req;
        this.functionName = functionName;
        this.keyValueBean = keyValueBean;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language == null)
            language="en-us";
    }

    /**
     * Setting Implementation Constructor
     * @param uploadedInputStream
     * @param body
     * @param req
     * @param functionName
     * @param keyValueBean
     */
    public SettingImplementation(InputStream uploadedInputStream, FormDataBodyPart body,
                    HttpServletRequest req,String functionName, KeyValueBean keyValueBean) {
        super();
        this.uploadedInputStream = uploadedInputStream;
        this.body = body;
        this.req = req;
        this.functionName = functionName;
        this.keyValueBean = keyValueBean;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language.equals(""))
            language="en-us";
    }
    // get connection to database
    private void getConnection() {
        con = this.dbc.getMySQLConnection();
        Logger.getLogger(SettingImplementation.class.getName()).log(Level.SEVERE, "Connection Basic >>>>>" + con);
    }
    
    // Get governorate List
    private ArrayList<KeyValueBean> getGovernorate() throws SQLException, Exception {
        
        getConnection();
        ArrayList<KeyValueBean> result = new ArrayList<KeyValueBean>();
        
        String successMsg = "";
        int successId=0;
    //Call Database Stored Procedure
        statement = con.prepareCall("{call spGetGovernorate(?,?)}");

        statement.registerOutParameter(1, Types.INTEGER);
        statement.registerOutParameter(2, Types.VARCHAR);

        boolean hasResults = statement.execute();
        
        successId = statement.getInt(1);
        successMsg = statement.getString(2); 
        
         if(hasResults)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {                   
                   result.add(new KeyValueBean(rs.getInt("GovID"),rs.getString("GovName")) );
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
    
    // Get Product categories list
    private ArrayList<CategoryBean> getProCategories() throws SQLException, Exception {
        
        getConnection();
        ArrayList<CategoryBean> result = new ArrayList<CategoryBean>();
        
        String successMsg = "";
        int successId=0;
    //Call Database Stored Procedure
        statement = con.prepareCall("{call spGetProCategories(?,?)}");

        
        statement.registerOutParameter(1, Types.INTEGER);
        statement.registerOutParameter(2, Types.VARCHAR);

        boolean hasResults = statement.execute();
        
        successId = statement.getInt(1);
        successMsg = statement.getString(2); 
        
         if(hasResults)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {                   
                   result.add(new CategoryBean(rs.getInt("ProCatID"),rs.getString("CatName"),
                         rs.getString("PhotoURL")) );
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
     * Call Apis List
     * @return
     * @throws Exception
     */
    public Object implementSettingMethod() throws Exception {
        Object result;
        result = this.generalSkelton(this.req, (Callable) () -> {
            switch (functionName) {
                case "governorate":
                    return (ArrayList<KeyValueBean>) getGovernorate();
                case "productCat":
                    return (ArrayList<CategoryBean>) getProCategories();
                default:
                    return null;

            }
        });
        return result;
    }
}
