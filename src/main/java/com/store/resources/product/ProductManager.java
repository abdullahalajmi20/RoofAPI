/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.resources.product;

import com.store.beans.ProductBean;
import com.store.beans.ProductBean;
import com.store.beans.ProductPhotoBean;
import com.store.implementation.ProductImplementation;
import java.io.InputStream;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

/**
 *
 * @author Abdullah
 */
public class ProductManager {
    
    /**
     * Add Product
     * @param productBean
     * @param req
     * @return
     * @throws Exception
     */
    public ProductBean addProduct(ProductBean productBean, HttpServletRequest req) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"add", productBean);
                                 
        return (ProductBean) productImplementation.implementProductMethod();
    }
     
    /**
     * update Product
     * @param productBean
     * @param req
     * @return
     * @throws Exception
     */
    public ProductBean updateProduct(ProductBean productBean, HttpServletRequest req) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"update", productBean);
                                 
        return (ProductBean) productImplementation.implementProductMethod();
    }
    
    /**
     * get Products
     * @param productBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<ProductBean> getProducts(ProductBean productBean, HttpServletRequest req) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"products", productBean);
                                 
        return (ArrayList<ProductBean> ) productImplementation.implementProductMethod();
    }
     
    /**
     * get Home screen Products
     * @param productBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<ProductBean> getHomeProducts(ProductBean productBean, HttpServletRequest req) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"home", productBean);
                                 
        return (ArrayList<ProductBean> ) productImplementation.implementProductMethod();
    }
     
    /**
     * get Store Products
     * @param productBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<ProductBean> getStoreProducts(ProductBean productBean, HttpServletRequest req) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"store", productBean);
                                 
        return (ArrayList<ProductBean> ) productImplementation.implementProductMethod();
    }
     
    /**
     * get Order Products
     * @param productBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<ProductBean> getOrderProducts(ProductBean productBean, HttpServletRequest req) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"order", productBean);
                                 
        return (ArrayList<ProductBean> ) productImplementation.implementProductMethod();
    }
     
    /**
     * search Products
     * @param productBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<ProductBean> searchProducts(ProductBean productBean, HttpServletRequest req) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"search", productBean);
                                 
        return (ArrayList<ProductBean> ) productImplementation.implementProductMethod();
    }
    
    /**
     * get Product Info
     * @param productBean
     * @param req
     * @return
     * @throws Exception
     */
    public ProductBean getProductInfo(ProductBean productBean, HttpServletRequest req) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"info", productBean);
                                 
        return (ProductBean) productImplementation.implementProductMethod();
    }
    
    /**
     * get Product Photos
     * @param eventPhotoBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<ProductPhotoBean> getProductPhotos(ProductPhotoBean eventPhotoBean, HttpServletRequest req) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"photos", eventPhotoBean);
                                 
        return (ArrayList<ProductPhotoBean> ) productImplementation.implementProductMethod();
    }
    
    /**
     * upload Photo
     * @param eventPhotoBean
     * @param req
     * @param uploadedInputStream
     * @param body
     * @return
     * @throws Exception
     */
    public ArrayList<ProductPhotoBean>  uploadPhoto(ProductPhotoBean eventPhotoBean, HttpServletRequest req,InputStream uploadedInputStream, FormDataBodyPart body) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"upload", eventPhotoBean,uploadedInputStream, body);
                                 
        return (ArrayList<ProductPhotoBean>) productImplementation.implementProductMethod();
    }
    
    /**
     * delete Product Photo
     * @param eventPhotoBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<ProductPhotoBean>  deleteProductPhoto(ProductPhotoBean eventPhotoBean, HttpServletRequest req) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"deletePhoto", eventPhotoBean);
                                 
        return (ArrayList<ProductPhotoBean>) productImplementation.implementProductMethod();
    }
    
    /**
     * delete Product
     * @param productBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<ProductBean>  deleteProduct(ProductBean productBean, HttpServletRequest req) throws Exception 
    {
        ProductImplementation productImplementation = new ProductImplementation(req,"deleteProduct", productBean);
                                 
        return (ArrayList<ProductBean>) productImplementation.implementProductMethod();
    }
    
}
