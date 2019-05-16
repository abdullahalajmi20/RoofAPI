/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.implementation;

/**
 *
 * @author Abdullah
 */
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
/**
 *
 * @author Abdullah
 */
public class ConnectionDatabase {

    /**
     *
     */
    public ConnectionDatabase() {
  }
  
    private static final String DB_CLASS = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://69.89.10.203:3306/roof?noAccessToProcedureBodies=true&useUnicode=yes&characterEncoding=UTF-8";
	
    /**
     *
     * @return
     */
    public static Connection getMySQLConnection() {
        byte tryCount = 0;
        Connection c = null;
        while (c == null) {
            try {
                Class.forName(DB_CLASS);
                c = DriverManager.getConnection(DB_URL,"roof","Roof@123");
                System.out.println("Database Connected");
            } catch (ClassNotFoundException cnfx) {
                    throw new RuntimeException(cnfx.getMessage());
            } catch (SQLException sqlx) {
                if (++tryCount > 5) {
                        sqlx.printStackTrace();
                } else {
                    c = null;
                    System.out.println("*** Unable to connect to database. Retrying in 5 seconds...");
                    try {
                            Thread.sleep(5000);
                    } catch (InterruptedException iex) {
                    }
                } 
            }
        }
            return c;
    }
        
    /**
     * Free Current Connection
     * @param c
     */
    public void freeConnection(Connection c){
    try {
      if (c!=null) c.close();
      Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, "Free Connection");
    } catch(SQLException ex) {
      Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
    /**
     * Free Current Statement
     * @param p
     */
    public void freePreparedStatement(PreparedStatement p) {
    try {
      if (p != null) p.close();
       Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, "Free Prepared Statement");
    } catch(Exception ex) {
      Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
    /**
     *Free Current CallableStatement
     * @param p
     */
    public void freeCallableStatement(CallableStatement p) {
    try {
      if (p != null) p.close();
       Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, "Free Callable Statement");
    } catch(Exception ex) {
      Logger.getLogger(ConnectionDatabase.class.getName()).log(Level.SEVERE, null, ex);
    }
  }  
}

