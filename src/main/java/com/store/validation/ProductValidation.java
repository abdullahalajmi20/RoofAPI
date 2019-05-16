/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.validation;

import com.store.beans.ProductBean;
import com.store.beans.ProductPhotoBean;
import com.store.utilities.Utilities;

/**
 *
 * @author Abdullah
 */
public class ProductValidation {
    private ProductBean productBean;
    private ProductPhotoBean eventPhotoBean;

    /**
     *
     */
    public ProductValidation() {
    }

    /**
     *
     * @param productBean
     */
    public ProductValidation(ProductBean productBean) {
        this.productBean = productBean;
    }

    /**
     *
     * @param eventPhotoBean
     */
    public ProductValidation(ProductPhotoBean eventPhotoBean) {
        this.eventPhotoBean = eventPhotoBean;
    }
    
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateAddProduct() throws Exception {
       
        if ( productBean.getProductName().trim().length() == 0 ){
                  throw new Exception("1075");
            }else if ( productBean.getDescription().trim().length() == 0 ){
                  throw new Exception("1019");
            }else if ( (Integer)productBean.getUserID()!= null && 
                    String.valueOf(productBean.getUserID()).trim().length() == 0  ){
                  throw new Exception("1075");
            }else if ( (Integer)productBean.getCatID()!= null &&
                    String.valueOf(productBean.getCatID()).trim().length() == 0  ){
                  throw new Exception("1108");
            }else if ( (Integer)productBean.getQuantity()!= null && 
                    String.valueOf(productBean.getQuantity()).trim().length() == 0  ){
                  throw new Exception("1109");
            }else if ( (Double)productBean.getPrice()!= null &&
                    String.valueOf(productBean.getPrice()).trim().length() == 0  ){
                  throw new Exception("1109");
            }
          
        return true;
    }
     
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateUpdateProduct() throws Exception {
       
        
         if ( (Integer)productBean.getProductID()!= null && String.valueOf(productBean.getProductID()).trim().length() == 0  ){
                  throw new Exception("1109");
            }else if ( productBean.getProductName().trim().length() == 0 ){
                  throw new Exception("1075");
            }else if ( productBean.getDescription().trim().length() == 0 ){
                  throw new Exception("1019");
            }else if ( (Integer)productBean.getCatID()!= null && String.valueOf(productBean.getCatID()).trim().length() == 0  ){
                  throw new Exception("1108");
            }else if ( (Integer)productBean.getQuantity()!= null && String.valueOf(productBean.getQuantity()).trim().length() == 0  ){
                  throw new Exception("1109");
            }else if ( (Double)productBean.getPrice()!= null && String.valueOf(productBean.getPrice()).trim().length() == 0  ){
                  throw new Exception("1109");
            }
        
            
        return true;
    }
     
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateGetProducts() throws Exception {
       
        if ( (Integer)productBean.getCatID()!= null && String.valueOf(productBean.getCatID()).trim().length() == 0 ){
                       throw new Exception("1024");
                  
            }
        
        return true;
    }
     
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateGetStoreProducts() throws Exception {
       
        if ( (Integer)productBean.getUserID()!= null && String.valueOf(productBean.getUserID()).trim().length() == 0 ){
                       throw new Exception("1075");
                  
            }
        
        return true;
    }
     
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateGetProductInfo() throws Exception {
       
        if ( (Integer)productBean.getProductID()!= null && String.valueOf(productBean.getProductID()).trim().length() == 0 ){
                  throw new Exception("1099");
            }
        
        return true;
    }
     
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateGetOrderProducts() throws Exception {
       
        if ( (Integer)productBean.getProductID()!= null && String.valueOf(productBean.getProductID()).trim().length() == 0 ){
                  throw new Exception("1039");
            }
        
        return true;
    }
     
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateUploadProductPhoto() throws Exception {
       
        if ( (Integer)eventPhotoBean.getProductID()!= null && String.valueOf(eventPhotoBean.getProductID()).trim().length() == 0 ){
                  throw new Exception("1023");
            }
        
        return true;
    }
     
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateGetProductPhotos() throws Exception {
       
        if ( (Integer)eventPhotoBean.getProductID()!= null && String.valueOf(eventPhotoBean.getProductID()).trim().length() == 0 ){
                  throw new Exception("1023");
            }
        return true;
    }
     
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateDeleteProductPhoto() throws Exception {
       
        if ( (Integer)eventPhotoBean.getPhotoID()!= null && String.valueOf(eventPhotoBean.getPhotoID()).trim().length() == 0 ){
                  throw new Exception("1085");
            }
        
        return true;
    }
     
    /**
     *
     * @return
     * @throws Exception
     */
    public boolean validateDeleteProduct() throws Exception {
       
        if ( (Integer)productBean.getProductID()!= null && String.valueOf(productBean.getProductID()).trim().length() == 0 ){
                  throw new Exception("1023");
            }
        
        return true;
    }
     
}
