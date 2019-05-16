/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.resources.product;


import com.store.beans.ProductBean;
import com.store.beans.ProductPhotoBean;
import com.store.validation.ProductValidation;
import java.io.InputStream;
import java.util.ArrayList;
import javax.ejb.Stateful;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author Abdullah
 */
@Stateful
@Path("/product")
public class ProductResources {
    
    /**
     * Add new product
     * @param req
     * @param productName
     * @param description
     * @param catID
     * @param price
     * @param quantity
     * @param userID
     * @param deliveryDuration
     * @param deliveryType
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/addProduct")
    public ProductBean addProduct(
                    @Context HttpServletRequest req,
                    @FormParam("productName") @NonNull String productName,
                    @FormParam("description") @NonNull String description,
                    @FormParam("catID") @NonNull Integer catID,
                    @FormParam("price") @NonNull Double price,
                    @FormParam("quantity") @NonNull Integer quantity,
                    @FormParam("userID") @NonNull Integer userID,
                    @FormParam("deliveryDuration") int deliveryDuration,
                    @FormParam("deliveryType") String deliveryType
           ) throws Exception {
        
        ProductManager  productManager = new ProductManager();
        ProductBean productBean = new ProductBean();
        productBean.setUserID(userID);
        productBean.setProductName(productName);
        productBean.setDescription(description);
        productBean.setCatID(catID);
        productBean.setPrice(price);
        productBean.setQuantity(quantity);
        productBean.setDeliveryDuration(deliveryDuration);
        productBean.setDeliveryType(deliveryType);
        
        ProductValidation productValidation = new ProductValidation(productBean);
        productValidation.validateAddProduct();
        
        return  productManager.addProduct(productBean, req);
    }
    
    /**
     *
     * @param req
     * @param productID
     * @param productName
     * @param description
     * @param catID
     * @param price
     * @param quantity
     * @param deliveryDuration
     * @param deliveryType
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/updateProduct")
    public ProductBean updateProduct(
                    @Context HttpServletRequest req,
                    @FormParam("productID") @NonNull Integer productID,
                    @FormParam("productName") @NonNull String productName,
                    @FormParam("description") @NonNull String description,
                    @FormParam("catID") @NonNull Integer catID,
                    @FormParam("price") @NonNull Double price,
                    @FormParam("quantity") @NonNull Integer quantity,
                    @FormParam("deliveryDuration") int deliveryDuration,
                    @FormParam("deliveryType") String deliveryType
           ) throws Exception {
        
        ProductManager  productManager = new ProductManager();
        ProductBean productBean = new ProductBean();
        productBean.setProductID(productID);
        productBean.setProductName(productName);
        productBean.setDescription(description);
        productBean.setCatID(catID);
        productBean.setPrice(price);
        productBean.setQuantity(quantity);
        productBean.setDeliveryDuration(deliveryDuration);
        productBean.setDeliveryType(deliveryType);
        
        ProductValidation productValidation = new ProductValidation(productBean);
        productValidation.validateUpdateProduct();
        
        return  productManager.updateProduct(productBean, req);
    }
    
    /**
     *
     * @param req
     * @param catID
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getProducts")
    public ProductBean[]  getProducts(
      @Context HttpServletRequest req,
      @QueryParam("catID") @NonNull Integer catID

    ) throws Exception {
         ArrayList<ProductBean> productsArray = null;
        
        ProductManager  productManager = new ProductManager();
        ProductBean productBean = new ProductBean();
        productBean.setCatID(catID);
        
        ProductValidation productValidation = new ProductValidation(productBean);
        productValidation.validateGetProducts();
        
        productsArray = productManager.getProducts(productBean, req);
        return  productsArray.toArray(new ProductBean[productsArray.size()]) ;
    }
    
    /**
     *
     * @param req
     * @param order
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getHomeProducts")
    public ProductBean[]  getHomeProducts(
      @Context HttpServletRequest req,
      @QueryParam("order") @NonNull String order

    ) throws Exception {
         ArrayList<ProductBean> productsArray = null;
        
        ProductManager  productManager = new ProductManager();
        ProductBean productBean = new ProductBean();
        productBean.setStoreName(order);
        
        productsArray = productManager.getHomeProducts(productBean, req);
        return  productsArray.toArray(new ProductBean[productsArray.size()]) ;
    }
    
    /**
     *
     * @param req
     * @param storeID
     * @param productName
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getStoreProducts")
    public ProductBean[]  getStoreProducts(
      @Context HttpServletRequest req,
      @QueryParam("storeID") @NonNull Integer storeID,
      @QueryParam("productName") String productName

    ) throws Exception {
         ArrayList<ProductBean> productsArray = null;
        
        ProductManager  productManager = new ProductManager();
        ProductBean productBean = new ProductBean();
        productBean.setUserID(storeID);
        productBean.setProductName(productName);
        
        ProductValidation productValidation = new ProductValidation(productBean);
        productValidation.validateGetStoreProducts();
        
        productsArray = productManager.getStoreProducts(productBean, req);
        return  productsArray.toArray(new ProductBean[productsArray.size()]) ;
    }
    
    /**
     *
     * @param req
     * @param orderID
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getOrderProducts")
    public ProductBean[]  getOrderProducts(
      @Context HttpServletRequest req,
      @QueryParam("orderID") @NonNull Integer orderID

    ) throws Exception {
         ArrayList<ProductBean> productsArray = null;
        
        ProductManager  productManager = new ProductManager();
        ProductBean productBean = new ProductBean();
        productBean.setProductID(orderID);
        
        ProductValidation productValidation = new ProductValidation(productBean);
        productValidation.validateGetOrderProducts();
        
        productsArray = productManager.getOrderProducts(productBean, req);
        return  productsArray.toArray(new ProductBean[productsArray.size()]) ;
    }
    
    /**
     *
     * @param req
     * @param productName
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/searchProducts")
    public ProductBean[]  searchProducts(
        @Context HttpServletRequest req,
        @QueryParam("productName") @NonNull String productName

    ) throws Exception {
         ArrayList<ProductBean> productsArray = null;
        
        ProductManager  productManager = new ProductManager();
        ProductBean productBean = new ProductBean();
        productBean.setProductName(productName);
        
        productsArray = productManager.searchProducts(productBean, req);
        return  productsArray.toArray(new ProductBean[productsArray.size()]) ;
    }
    
    /**
     *
     * @param req
     * @param productID
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getProductInfo/{PRODUCT_ID}")
    public ProductBean  getProductInfo(
      @Context HttpServletRequest req,
      @PathParam("PRODUCT_ID") @NonNull Integer productID

    ) throws Exception {
        
        ProductManager  productManager = new ProductManager();
        ProductBean productBean = new ProductBean();
        productBean.setProductID(productID);
        
        ProductValidation productValidation = new ProductValidation(productBean);
        productValidation.validateGetProductInfo();
        
        return  productManager.getProductInfo(productBean, req);
    }
    
    /**
     *
     * @param req
     * @param productID
     * @param uploadedInputStream
     * @param body
     * @return
     * @throws Exception
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Path("/uploadPhoto")
    public ProductPhotoBean[] uploadPhoto(
                    @Context HttpServletRequest req,
                    @FormDataParam("productID") @NonNull Integer productID,
                    @FormDataParam("photo") InputStream uploadedInputStream,
                    @FormDataParam("photo") final FormDataBodyPart body
                    
           ) throws Exception {
        
        ArrayList<ProductPhotoBean> photosArray = null;
        
        ProductManager  productManager = new ProductManager();
        ProductPhotoBean eventPhotoBean = new ProductPhotoBean();
        eventPhotoBean.setProductID(productID);
        
        ProductValidation productValidation = new ProductValidation(eventPhotoBean);
        productValidation.validateUploadProductPhoto();
        
        photosArray = productManager.uploadPhoto(eventPhotoBean, req,uploadedInputStream,body);
        return  photosArray.toArray(new ProductPhotoBean[photosArray.size()]) ;
    }
    
    /**
     *
     * @param req
     * @param productID
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getProductPhotos/{PRODUCT_ID}")
    public ProductPhotoBean[]  getProductPhotos(
      @Context HttpServletRequest req,
      @PathParam("PRODUCT_ID") @NonNull Integer productID

    ) throws Exception {
         ArrayList<ProductPhotoBean> photosArray = null;
        
        ProductManager  productManager = new ProductManager();
        ProductPhotoBean prodcutPhotoBean = new ProductPhotoBean();
        prodcutPhotoBean.setProductID(productID);
        
        ProductValidation productValidation = new ProductValidation(prodcutPhotoBean);
        productValidation.validateGetProductPhotos();
        
        photosArray = productManager.getProductPhotos(prodcutPhotoBean, req);
        return  photosArray.toArray(new ProductPhotoBean[photosArray.size()]) ;
    }
    
    /**
     *
     * @param req
     * @param photoID
     * @return
     * @throws Exception
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/deleteProductPhoto/{PHOTO_ID}")
    public ProductPhotoBean[] deleteProductPhoto(
                     @Context HttpServletRequest req,
                     @PathParam("PHOTO_ID") @NonNull Integer photoID    
           ) throws Exception {
        
        ArrayList<ProductPhotoBean> photosArray = null;
        
        ProductManager  productManager = new ProductManager();
        ProductPhotoBean productPhotoBean = new ProductPhotoBean();
        productPhotoBean.setPhotoID(photoID);
        
        ProductValidation productValidation = new ProductValidation(productPhotoBean);
        productValidation.validateDeleteProductPhoto();
        
        photosArray = productManager.deleteProductPhoto(productPhotoBean, req);
        return  photosArray.toArray(new ProductPhotoBean[photosArray.size()]) ;
    }
    
    /**
     *
     * @param req
     * @param productID
     * @return
     * @throws Exception
     */
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/deleteProduct/{PRODUCT_ID}")
    public ProductBean[] deleteProduct(
                     @Context HttpServletRequest req,
                     @PathParam("PRODUCT_ID") @NonNull Integer productID    
           ) throws Exception {
        
        ArrayList<ProductBean> productsArray = null;
        
        ProductManager  productManager = new ProductManager();
        ProductBean productBean = new ProductBean();
        productBean.setProductID(productID);
        
        ProductValidation productValidation = new ProductValidation(productBean);
        productValidation.validateDeleteProduct();
        
        productsArray = productManager.deleteProduct(productBean, req);
        return  productsArray.toArray(new ProductBean[productsArray.size()]) ;
    }
}
