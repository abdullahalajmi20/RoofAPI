/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.implementation;


import com.store.beans.ErrorBean;
import com.store.utilities.Utilities;
import com.store.utilities.Validators;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Abdullah
 */
public class SuperImplementation {

    /**
     * Get connection to database 
     */
    protected static Connection con = null;
     ConnectionDatabase dbc=new ConnectionDatabase();
     CallableStatement statement = null;
     ResultSet rs = null;
     
    /**
     *
     */
    public SuperImplementation(){
         dbc=new ConnectionDatabase();
         statement = null;
         rs = null;
     }

    /**
     *
     * @param req
     * @param func
     * @return
     * @throws Exception
     */
    public Object  generalSkelton(HttpServletRequest req,Callable func) throws Exception {
        ErrorBean errorBean = null;
         try {
               return func.call();
         }catch(Exception ex){
            
            Logger.getLogger(SuperImplementation.class.getName()).log(Level.SEVERE, null, ex);
            String errorExceptionCode = ex.getMessage();
            Logger.getLogger(SuperImplementation.class.getName()).log(Level.SEVERE,
                "{0}"+ ">>>>>" + "  ", errorExceptionCode);
            errorBean = this.handleError(errorExceptionCode, req);
            int errorCode = (errorBean.getError()!=null)?errorBean.getError().getCode():5000;
            Logger.getLogger(SuperImplementation.class.getName()).log(Level.SEVERE,
                "{0}"+ "errorCode>>>>>" + "  ", errorCode);
            throw new Exception(String.valueOf(errorCode));
         }
         finally{
           
            try {
                if(rs!=null)
                  rs.close();
            } catch (Exception ex) {
                
                Logger.getLogger(SuperImplementation.class.getName()).log(Level.SEVERE, null, ex);
            }
            dbc.freeCallableStatement(statement);
            dbc.freeConnection(con);
            System.out.println("End freeConnection");
         }
    }
      
    // Handle error messages
     private ErrorBean   handleError(String errorExceptionCode,HttpServletRequest req){
          HttpSession session = req.getSession();
          
          Logger.getLogger(SuperImplementation.class.getName()).log(Level.SEVERE,"errorExceptionCode>>>>>"+errorExceptionCode);
           if(!Validators.isNumber(errorExceptionCode.trim()))//error in code
              errorExceptionCode ="500";
           else if(Integer.parseInt(errorExceptionCode)==0)//error in DB
              errorExceptionCode="5000";
           
            session.setAttribute("ERROR_MESSAGE",errorExceptionCode);
            session.setAttribute("ERROR_CODE",errorExceptionCode);
            
            return Utilities.getErrorBean(req, errorExceptionCode,Integer.parseInt(errorExceptionCode.trim())); 
         
     }
    
    
}
