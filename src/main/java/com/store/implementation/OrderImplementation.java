/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.implementation;

import com.store.beans.OrderBean;
import com.store.beans.ProductBean;
import static com.store.implementation.SuperImplementation.con;
import com.store.utilities.Email;
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

/**
 *
 * @author Abdullah
 */
public class OrderImplementation extends SuperImplementation{
    
    public HttpServletRequest req;

    public String functionName;

    public OrderBean orderBean;
    private String language;
    public String products;
    public String quantity;
    public String specialOrders;
    
    private HttpSession session = null;
    DateFormat dateFormat = null;
    
    /**
     * Default Constructor
     */
    public OrderImplementation() {
       super();
       dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    }

    /**
     * Overloaded Constructor
     * @param req
     * @param functionName
     */
    public OrderImplementation(HttpServletRequest req, String functionName) {
        super();
        this.req = req;
        this.functionName = functionName;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language.equals(""))
            language="en-us";
        
        dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    }
                                             
    /**
     *Overloaded Constructor
     * @param req
     * @param functionName
     * @param orderBean
     */
    public OrderImplementation(HttpServletRequest req, String functionName, OrderBean orderBean) {
        super();
        this.req = req;
        this.functionName = functionName;
        this.orderBean = orderBean;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language == null)
            language="en-us";
        
        dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    }
    
    /**
     * Overloaded Constructor
     * @param req
     * @param functionName
     * @param orderBean
     * @param products
     * @param quantity
     * @param specialOrders
     */
    public OrderImplementation(HttpServletRequest req, String functionName, OrderBean orderBean, String products, String quantity, String specialOrders) {
        super();
        this.req = req;
        this.functionName = functionName;
        this.orderBean = orderBean;
        this.products = products;
        this.quantity = quantity;
        this.specialOrders = specialOrders;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language == null)
            language="en-us";
        
        dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    }
    //Get New Connection to database
    private void getConnection() {
        con = this.dbc.getMySQLConnection();
        Logger.getLogger(OrderImplementation.class.getName()).log(Level.SEVERE, "Connection Basic >>>>>" + con);
    }
    //Post new Order
    private OrderBean postOrder() throws SQLException, Exception {

        getConnection();
        OrderBean result = null;
        String successMsg = "";
        int successId = 0;
        statement = con.prepareCall("{call spPostOrder(?,?,?,?,?,?,?,?,?)}");
        
        statement.setInt(1, orderBean.getUserID());
        statement.setDouble(2, orderBean.getTotalAmount());
        statement.setString(3, orderBean.getNotes());
        statement.setString(4, products);
        statement.setInt(5, orderBean.getStoreID());
        statement.setString(6, quantity); 
        statement.setString(7, specialOrders);
        
        statement.registerOutParameter(8, Types.INTEGER);
        statement.registerOutParameter(9, Types.VARCHAR);

        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(8);
        successMsg = statement.getString(9); 

        if(successId > 0)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate"); 

                String createdDateStr = dateFormat.format(createdDate);
                String emailAdd = rs.getString("Email");
                result = new OrderBean(rs.getInt("OrderID"),rs.getInt("UserID"),rs.getInt("Mobile"),
                                    rs.getString("FullName"),emailAdd,rs.getString("Address"),
                        rs.getDouble("TotalAmount"),rs.getString("Notes"),rs.getString("Status"),rs.getInt("StoreID"),rs.getString("StoreName"),createdDateStr);
                
                String message = "Welcome To Monasabat Online Store. <BR/>Your order has been submitted. We'll start processing your order right away.";
                Email email = new Email();

                email.sendEmail(message, emailAdd,"Monasabat Order Confirmation.");
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
    
    // Get order Information
    private OrderBean getOrderInfo() throws SQLException, Exception {

        getConnection();
        OrderBean result = null;
        String successMsg = "";
        int successId = 0;

        statement = con.prepareCall("{call spGetOrderInfo(?,?,?)}");
        
        statement.setInt(1, orderBean.getOrderID());
 
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
                        
                result = new OrderBean(rs.getInt("OrderID"),rs.getInt("UserID"),rs.getInt("Mobile"),
                                    rs.getString("FullName"),rs.getString("Email"),rs.getString("Address"),
                        rs.getDouble("TotalAmount"),rs.getString("Notes"),rs.getString("Status"),rs.getInt("StoreID"),rs.getString("StoreName"),createdDateStr);
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
    
    // get Store Orders
    private ArrayList<OrderBean> getStoreOrders() throws SQLException, Exception {

        getConnection();
        ArrayList<OrderBean> result = new ArrayList<OrderBean>();
        String successMsg = "";
        int successId = 0;
        
        statement = con.prepareCall("{call spGetStoreOrders(?,?,?,?)}");
        
        statement.setString(1, orderBean.getStatus());
        statement.setInt(2, orderBean.getStoreID());
        
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
                
                  result.add(new OrderBean(rs.getInt("OrderID"),rs.getInt("UserID"),rs.getInt("Mobile"),
                            rs.getString("FullName"),rs.getString("Email"),rs.getString("Address"),
                        rs.getDouble("TotalAmount"),rs.getString("Notes"),rs.getString("Status"),rs.getInt("StoreID"),rs.getString("StoreName"),createdDateStr) );
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
    
    // get User Orders
    private ArrayList<OrderBean> getUserOrders() throws SQLException, Exception {

        getConnection();
        ArrayList<OrderBean> result = new ArrayList<OrderBean>();
        String successMsg = "";
        int successId = 0;
        //Call Database Stored Procedure
        statement = con.prepareCall("{call spGetUserOrders(?,?,?,?)}");
        
        statement.setString(1, orderBean.getStatus());
        statement.setInt(2, orderBean.getUserID());
        
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
                
                  result.add(new OrderBean(rs.getInt("OrderID"),rs.getInt("UserID"),rs.getInt("Mobile"),
                                    rs.getString("FullName"),rs.getString("Email"),rs.getString("Address"),
                        rs.getDouble("TotalAmount"),rs.getString("Notes"),rs.getString("Status"),rs.getInt("StoreID"),rs.getString("StoreName"),createdDateStr) );
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
    
    // Update order status
    private OrderBean updateOrderStatus() throws SQLException, Exception {

        getConnection();
        OrderBean result = null;
        String successMsg = "";
        int successId = 0;
//Call Database Stored Procedure
        statement = con.prepareCall("{call spUpdateOrderStatus(?,?,?,?)}");
        
        statement.setInt(1, orderBean.getOrderID());
        statement.setString(2, orderBean.getStatus());
        
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
                
                  result = new OrderBean(rs.getInt("OrderID"),rs.getInt("UserID"),rs.getInt("Mobile"),
                        rs.getString("FullName"),rs.getString("Email"),rs.getString("Address"),
                        rs.getDouble("TotalAmount"),rs.getString("Notes"),rs.getString("Status"),rs.getInt("StoreID"),rs.getString("StoreName"),createdDateStr);
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
    public Object implementOpinionMethod() throws Exception {
        Object result;
        result = this.generalSkelton(this.req, (Callable) () -> {
            switch (functionName) {
                case "info":
                    return (OrderBean) getOrderInfo();
                case "post":
                    return (OrderBean) postOrder();
                case "storeOrder":
                    return (ArrayList<OrderBean>) getStoreOrders();
                case "userOrder":
                    return (ArrayList<OrderBean>) getUserOrders();
                case "update":
                    return (OrderBean) updateOrderStatus();
                default:
                    return null;

            }
        });
        return result;
    }
    
}
