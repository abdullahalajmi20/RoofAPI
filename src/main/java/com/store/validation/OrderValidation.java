/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.validation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.store.beans.OrderBean;
import com.store.utilities.Utilities;

/**
 *
 * @author Abdullah
 */
public class OrderValidation {
    private OrderBean orderBean;
    
    /**
     *
     */
    public OrderValidation() {
    }

    /**
     *
     * @param orderBean
     */
    public OrderValidation(OrderBean orderBean) {
        this.orderBean = orderBean;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validatePostOrder() throws Exception {
       
        if ( (Double)orderBean.getTotalAmount()!= null && String.valueOf(orderBean.getTotalAmount()).trim().length() == 0 ){
                throw new Exception("1015");
            }
        
        return true;
    }
   
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateGetOrderInfo() throws Exception {
       
        if ( (Integer)orderBean.getOrderID()!= null && String.valueOf(orderBean.getOrderID()).trim().length() == 0 ){
                throw new Exception("1015");
            }
        
        return true;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateUpdateOrderStatus() throws Exception {
       
        if ( (Integer)orderBean.getOrderID()!= null && String.valueOf(orderBean.getOrderID()).trim().length() == 0 ){
                throw new Exception("1015");
            }else if ( orderBean.getStatus().trim().length() == 0 ){
                  throw new Exception("1075");
            }
        
        return true;
    }
    

}

