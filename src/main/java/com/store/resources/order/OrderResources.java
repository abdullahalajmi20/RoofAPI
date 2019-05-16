/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.resources.order;

import com.store.beans.OrderBean;
import com.store.validation.OrderValidation;
import java.util.ArrayList;
import javax.ejb.Stateful;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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

/**
 *
 * @author Abdullah
 */

@Stateful
@Path("/order")
public class OrderResources {
    
    /**
     * post Order
     * @param req
     * @param userID
     * @param totalAmount
     * @param notes
     * @param storeID
     * @param products
     * @param quantity
     * @param specialOrders
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/postOrder")
    public OrderBean postOrder(
                    @Context HttpServletRequest req,
                    @FormParam("userID") int userID,
                    @FormParam("totalAmount")  @NonNull Double totalAmount,
                    @FormParam("notes")  String notes,
                    @FormParam("storeID")  int storeID,
                    @FormParam("products")  String products,
                    @FormParam("quantity")  String quantity,
                    @FormParam("specialOrders")  String specialOrders
           ) throws Exception {
        OrderManager  opinionManager = new OrderManager();
        OrderBean orderBean = new OrderBean();
        orderBean.setUserID(userID);
        orderBean.setStoreID(storeID);
        orderBean.setTotalAmount(totalAmount);
        orderBean.setNotes(notes);
        
        OrderValidation orderValidation = new OrderValidation(orderBean);
        orderValidation.validatePostOrder();
        return  opinionManager.postOrder(orderBean, req, products,quantity,specialOrders);
    }
    
    /**
     * get Order Information
     * @param req
     * @param orderID
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getOrderInfo/{ORDER_ID}")
    public OrderBean getOrderInfo(
                        @Context HttpServletRequest req,
                        @PathParam("ORDER_ID") @NonNull Integer orderID
                               ) throws Exception {
        
        OrderManager  opinionManager = new OrderManager();
        OrderBean orderBean = new OrderBean();
        orderBean.setOrderID(orderID);
        
        OrderValidation orderValidation = new OrderValidation(orderBean);
        orderValidation.validateGetOrderInfo();
        
        return  opinionManager.getOrderInfo(orderBean, req);
    }
    
    /**
     * get customer Orders
     * @param req
     * @param status
     * @param userID
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getUserOrders")
    public OrderBean[]  getUserOrders(
      @Context HttpServletRequest req,
            @QueryParam("status") @NonNull String status,  //Pending,Delivered
        @QueryParam("userID") int userID
    ) throws Exception {
         ArrayList<OrderBean> ordersArray = null;
         OrderManager  opinionManager = new OrderManager();
         
         OrderBean orderBean = new OrderBean();
         orderBean.setUserID(userID);
         orderBean.setStatus(status);
        
        ordersArray = opinionManager.getUserOrders(orderBean, req);
        return  ordersArray.toArray(new OrderBean[ordersArray.size()]) ;
    }
    
    /**
     * get Store Orders
     * @param req
     * @param status
     * @param storeID
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getStoreOrders")
    public OrderBean[]  getStoreOrders(
      @Context HttpServletRequest req,
        @QueryParam("status") @NonNull String status,  //Pending,Delivered
        @QueryParam("storeID") int storeID
    ) throws Exception {
         ArrayList<OrderBean> ordersArray = null;
         OrderManager  opinionManager = new OrderManager();
         
         OrderBean orderBean = new OrderBean();
         orderBean.setStatus(status);
         orderBean.setStoreID(storeID);
         
        ordersArray = opinionManager.getStoreOrders(orderBean, req);
        return  ordersArray.toArray(new OrderBean[ordersArray.size()]) ;
    }
    
    /**
     * update Order Status
     * @param req
     * @param orderID
     * @param status
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/updateOrderStatus")
    public OrderBean updateOrderStatus(
                     @Context HttpServletRequest req,
                     @FormParam("orderID") @NonNull Integer orderID, 
                     @FormParam("status") @NonNull String status
           ) throws Exception {
        
        OrderManager  opinionManager = new OrderManager();
        OrderBean orderBean = new OrderBean();
        orderBean.setOrderID(orderID);
        orderBean.setStatus(status);
        
        OrderValidation orderValidation = new OrderValidation(orderBean);
        orderValidation.validateUpdateOrderStatus();

        return  opinionManager.updateOrderStatus(orderBean, req);
    }
    
    
}


