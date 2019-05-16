/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.resources.order;

import com.store.beans.OrderBean;
import com.store.implementation.OrderImplementation;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Abdullah
 */
public class OrderManager {
    
    /**
     * Post New Order
     * @param orderBean
     * @param req
     * @param products
     * @param quantity
     * @param specialOrders
     * @return
     * @throws Exception
     */
    public OrderBean postOrder(OrderBean orderBean, HttpServletRequest req, String products, String quantity, String specialOrders) throws Exception 
    {
        OrderImplementation orderImplementation = new OrderImplementation(req,"post", orderBean,products,quantity,specialOrders);
                                 
        return (OrderBean) orderImplementation.implementOpinionMethod();
    }
    
    /**
     * Get order information
     * @param orderBean
     * @param req
     * @return
     * @throws Exception
     */
    public OrderBean getOrderInfo(OrderBean orderBean, HttpServletRequest req) throws Exception 
    {
        OrderImplementation orderImplementation = new OrderImplementation(req,"info", orderBean);
                                 
        return (OrderBean) orderImplementation.implementOpinionMethod();
    }
    
    /**
     * Get Store Order
     * @param orderBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<OrderBean> getStoreOrders(OrderBean orderBean, HttpServletRequest req) throws Exception 
    {
        OrderImplementation orderImplementation = new OrderImplementation(req,"storeOrder", orderBean);
                                 
        return (ArrayList<OrderBean>) orderImplementation.implementOpinionMethod();
    }
    
    /**
     * Get Customer Orders
     * @param orderBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<OrderBean> getUserOrders(OrderBean orderBean, HttpServletRequest req) throws Exception 
    {
        OrderImplementation orderImplementation = new OrderImplementation(req,"userOrder", orderBean);
                                 
        return (ArrayList<OrderBean>) orderImplementation.implementOpinionMethod();
    }
    
    /**
     * Update order status
     * @param orderBean
     * @param req
     * @return
     * @throws Exception
     */
    public OrderBean updateOrderStatus(OrderBean orderBean, HttpServletRequest req) throws Exception 
    {
        OrderImplementation orderImplementation = new OrderImplementation(req,"update", orderBean);
                                 
        return (OrderBean) orderImplementation.implementOpinionMethod();
    }
}
