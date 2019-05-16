/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.implementation;

import com.store.beans.ProductBean;
import com.store.beans.ProductPhotoBean;
import static com.store.implementation.SuperImplementation.con;
import com.store.utilities.LocalProperties;
import com.store.utilities.StoreFCMNotification;
import com.store.utilities.RandomString;
import com.store.utilities.UploadFile;
import java.io.InputStream;
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
import org.glassfish.jersey.media.multipart.FormDataBodyPart;

/**
 *
 * @author Abdullah
 */
public class ProductImplementation extends SuperImplementation{
    
    
    public HttpServletRequest req;
    public String functionName;
    public ProductBean productBean;
    public ProductPhotoBean productPhotoBean;
    public InputStream uploadedInputStream;
    public FormDataBodyPart body;
    private String language;
    private HttpSession session = null;
    DateFormat dateFormat = null;
   
    public ProductImplementation() {
       super();
       dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    }

    /**
     * Product Implementation Constructor
     * @param req
     * @param functionName
     */
    public ProductImplementation(HttpServletRequest req, String functionName) {
        super();
        dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        this.req = req;
        this.functionName = functionName;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language.equals(""))
            language="en-us";
    }
                                             
    /**
     * Product Implementation Constructor
     * @param req
     * @param functionName
     * @param productBean
     */
    public ProductImplementation(HttpServletRequest req, String functionName, ProductBean productBean) {
        super();
        dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        this.req = req;
        this.functionName = functionName;
        this.productBean = productBean;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language == null)
            language="en-us";
    }

    /**
     * Product Implementation Constructor
     * @param req
     * @param functionName
     * @param productPhotoBean
     */
    public ProductImplementation(HttpServletRequest req, String functionName, ProductPhotoBean productPhotoBean) {
        super();
        dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        this.req = req;
        this.functionName = functionName;
        this.productPhotoBean = productPhotoBean;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language == null)
            language="en-us";
    }
    
    /**
     * Product Implementation Constructor
     * @param req
     * @param functionName
     * @param productPhotoBean
     * @param uploadedInputStream
     * @param body
     */
    public ProductImplementation(HttpServletRequest req, String functionName, ProductPhotoBean productPhotoBean, InputStream uploadedInputStream, FormDataBodyPart body) {
        super();
        dateFormat = new SimpleDateFormat("dd MMMM yyyy");
        this.req = req;
        this.functionName = functionName;
        this.uploadedInputStream = uploadedInputStream;
        this.body = body;
        this.productPhotoBean = productPhotoBean;
        session = req.getSession();
        language = (String) session.getAttribute("LANGUAGE");
        if(language == null)
            language="en-us";
    }
    
    // get connection to database
    private void getConnection() {
        con = this.dbc.getMySQLConnection();
        Logger.getLogger(ProductImplementation.class.getName()).log(Level.SEVERE, "Connection Basic >>>>>" + con);
    }
    
   // Add New Product
    private ProductBean addProduct() throws SQLException, Exception {
        getConnection();
        ProductBean result = null;
        String successMsg = "";
        int successId = 0;
        //Call Database Stored Procedure
        statement = con.prepareCall("{call spAddProduct(?,?,?,?,?,?,?,?,?,?)}");
        
        statement.setString(1, productBean.getProductName());
        statement.setString(2, productBean.getDescription());
        statement.setInt(3, productBean.getCatID());
        statement.setDouble(4, productBean.getPrice());
        statement.setInt(5, productBean.getQuantity());
        statement.setInt(6, productBean.getUserID());
        statement.setInt(7, productBean.getDeliveryDuration());
        statement.setString(8, productBean.getDeliveryType());
        
        statement.registerOutParameter(9, Types.INTEGER);
        statement.registerOutParameter(10, Types.VARCHAR);

        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(9);
        successMsg = statement.getString(10); 

        if(successId > 0)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
                while (rs.next()) {
                  Date createdDate = rs.getDate("CreatedDate"); 
                  
                  String createdDateStr = dateFormat.format(createdDate);
                  
                  result = new ProductBean(rs.getInt("ProductID"),rs.getInt("UserID"),rs.getString("StoreName"),rs.getString("ProductName"),rs.getString("Description"),
                                       rs.getInt("CatID"),rs.getDouble("Price"),rs.getInt("Quantity"),rs.getString("PhotoURL"),rs.getInt("Status"),rs.getInt("DeliveryDuration"),rs.getString("DeliveryType"),"",createdDateStr);
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
    
    // Update Product
    private ProductBean updateProduct() throws SQLException, Exception {
        getConnection();
        ProductBean result = null;
        String successMsg = "";
        int successId = 0;
         //Call Database Stored Procedure
        statement = con.prepareCall("{call spUpdateProduct(?,?,?,?,?,?,?,?,?,?)}");
        
        statement.setInt(1, productBean.getProductID());
       statement.setString(2, productBean.getProductName());
        statement.setString(3, productBean.getDescription());
        statement.setInt(4, productBean.getCatID());
        statement.setDouble(5, productBean.getPrice());
        statement.setInt(6, productBean.getQuantity());
        statement.setInt(7, productBean.getDeliveryDuration());
        statement.setString(8, productBean.getDeliveryType());
        
        statement.registerOutParameter(9, Types.INTEGER);
        statement.registerOutParameter(10, Types.VARCHAR);

        boolean hasResults = statement.execute();
 	        
        successId = statement.getInt(9);
        successMsg = statement.getString(10); 

        if(successId > 0)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();

                while (rs.next()) {
                  Date createdDate = rs.getDate("CreatedDate"); 
                  
                  String createdDateStr = dateFormat.format(createdDate);
                  
                  result = new ProductBean(rs.getInt("ProductID"),rs.getInt("UserID"),rs.getString("StoreName"),rs.getString("ProductName"),rs.getString("Description"),
                                       rs.getInt("CatID"),rs.getDouble("Price"),rs.getInt("Quantity"),rs.getString("PhotoURL"),rs.getInt("Status"),rs.getInt("DeliveryDuration"),rs.getString("DeliveryType"),"",createdDateStr);
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
    
    //Search Products
    private ArrayList<ProductBean> getProducts() throws SQLException, Exception {
        
        getConnection();
        ArrayList<ProductBean> result = new ArrayList<ProductBean>();
        
        String successMsg = "";
        int successId=0;
    //Call Database Stored Procedure
        statement = con.prepareCall("{call spGetProducts(?,?,?)}");
        
        statement.setInt(1, productBean.getCatID());
        
        statement.registerOutParameter(2, Types.INTEGER);
        statement.registerOutParameter(3, Types.VARCHAR);

        boolean hasResults = statement.execute();
        
        successId = statement.getInt(2);
        successMsg = statement.getString(3); 
        
         if(hasResults)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate");  
                  
                String createdDateStr = dateFormat.format(createdDate);
                
                result.add(new ProductBean(rs.getInt("ProductID"),rs.getInt("UserID"),rs.getString("StoreName"),rs.getString("ProductName"),rs.getString("Description"),
                                       rs.getInt("CatID"),rs.getDouble("Price"),rs.getInt("Quantity"),rs.getString("PhotoURL"),rs.getInt("Status"),rs.getInt("DeliveryDuration"),rs.getString("DeliveryType"),"",createdDateStr) );
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
    
    //Get Home Products
    private ArrayList<ProductBean> getHomeProducts() throws SQLException, Exception {
        
        getConnection();
        ArrayList<ProductBean> result = new ArrayList<ProductBean>();
        
        String successMsg = "";
        int successId=0;
    //Call Database Stored Procedure
        statement = con.prepareCall("{call spGetHomeProducts(?,?,?)}");
        
        statement.setString(1, productBean.getStoreName());
        
        statement.registerOutParameter(2, Types.INTEGER);
        statement.registerOutParameter(3, Types.VARCHAR);

        boolean hasResults = statement.execute();
        
        successId = statement.getInt(2);
        successMsg = statement.getString(3); 
        
         if(hasResults)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate");  
                  
                String createdDateStr = dateFormat.format(createdDate);
                
                result.add(new ProductBean(rs.getInt("ProductID"),rs.getInt("UserID"),rs.getString("StoreName"),rs.getString("ProductName"),rs.getString("Description"),
                                       rs.getInt("CatID"),rs.getDouble("Price"),rs.getInt("Quantity"),rs.getString("PhotoURL"),rs.getInt("Status"),rs.getInt("DeliveryDuration"),rs.getString("DeliveryType"),"",createdDateStr) );
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
    
    // Get User/Store Order Items
    private ArrayList<ProductBean> getOrderProducts() throws SQLException, Exception {
        
        getConnection();
        ArrayList<ProductBean> result = new ArrayList<ProductBean>();
        
        String successMsg = "";
        int successId=0;
    //Call Database Stored Procedure
        statement = con.prepareCall("{call spGetOrderProducts(?,?,?)}");
        
        statement.setInt(1, productBean.getProductID());
        
        statement.registerOutParameter(2, Types.INTEGER);
        statement.registerOutParameter(3, Types.VARCHAR);

        boolean hasResults = statement.execute();
        
        successId = statement.getInt(2);
        successMsg = statement.getString(3); 
        
         if(hasResults)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate");  
                  
                String createdDateStr = dateFormat.format(createdDate);
                
                result.add(new ProductBean(rs.getInt("ProductID"),rs.getInt("UserID"),rs.getString("StoreName"),rs.getString("ProductName"),rs.getString("Description"),
                        rs.getInt("CatID"),rs.getDouble("Price"),rs.getInt("Quantity"),rs.getString("PhotoURL"),rs.getInt("Status"),
                        rs.getInt("DeliveryDuration"),rs.getString("DeliveryType"),rs.getString("SpecialOrder"),createdDateStr) );
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
    
    // Search Items by name
    private ArrayList<ProductBean> searchProducts() throws SQLException, Exception {
        
        getConnection();
        ArrayList<ProductBean> result = new ArrayList<ProductBean>();
        
        String successMsg = "";
        int successId=0;
    
        //Call Database Stored Procedure
        statement = con.prepareCall("{call spSearchProducts(?,?,?)}");
        
        statement.setString(1, productBean.getProductName());
        
        statement.registerOutParameter(2, Types.INTEGER);
        statement.registerOutParameter(3, Types.VARCHAR);

        boolean hasResults = statement.execute();
        
        successId = statement.getInt(2);
        successMsg = statement.getString(3); 
        
         if(hasResults)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                    Date createdDate = rs.getDate("CreatedDate"); 

                    String createdDateStr = dateFormat.format(createdDate);

                    result.add(new ProductBean(rs.getInt("ProductID"),rs.getInt("UserID"),rs.getString("StoreName"),rs.getString("ProductName"),rs.getString("Description"),
                                           rs.getInt("CatID"),rs.getDouble("Price"),rs.getInt("Quantity"),rs.getString("PhotoURL"),rs.getInt("Status"),rs.getInt("DeliveryDuration"),rs.getString("DeliveryType"),"",createdDateStr) );
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
    
    //Get store items
    private ArrayList<ProductBean> getStoreProducts() throws SQLException, Exception {
        
        getConnection();
        ArrayList<ProductBean> result = new ArrayList<ProductBean>();
        
        String successMsg = "";
        int successId=0;
    //Call Database Stored Procedure
        statement = con.prepareCall("{call spGetStoreProducts(?,?,?,?)}");
        
        statement.setInt(1, productBean.getUserID());
        statement.setString(2, productBean.getProductName());
        
        statement.registerOutParameter(3, Types.INTEGER);
        statement.registerOutParameter(4, Types.VARCHAR);

        boolean hasResults = statement.execute();
        
        successId = statement.getInt(3);
        successMsg = statement.getString(4); 
        
         if(hasResults)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                    Date createdDate = rs.getDate("CreatedDate"); 

                    String createdDateStr = dateFormat.format(createdDate);

                    result.add(new ProductBean(rs.getInt("ProductID"),rs.getInt("UserID"),rs.getString("StoreName"),
                            rs.getString("ProductName"),rs.getString("Description"),rs.getInt("CatID"),rs.getDouble("Price"),
                            rs.getInt("Quantity"),rs.getString("PhotoURL"),rs.getInt("Status"),rs.getInt("DeliveryDuration"),
                            rs.getString("DeliveryType"),"",createdDateStr) );
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
    
    // Get Product information
     private ProductBean getProductInfo() throws SQLException, Exception {
        
        getConnection();
        ProductBean result = null;
        
        String successMsg = "";
        int successId=0;
    
        //Call Database Stored Procedure
        statement = con.prepareCall("{call spGetProductInfo(?,?,?)}");
        
        statement.setInt(1, productBean.getProductID());
        
        statement.registerOutParameter(2, Types.INTEGER);
        statement.registerOutParameter(3, Types.VARCHAR);

        boolean hasResults = statement.execute();
        
        successId = statement.getInt(2);
        successMsg = statement.getString(3); 
        
         if(hasResults)
        {
            while (hasResults) 
            {
              rs = statement.getResultSet();
              while (rs.next()) {
                Date createdDate = rs.getDate("CreatedDate");  

                String createdDateStr = dateFormat.format(createdDate);

                result = new ProductBean(rs.getInt("ProductID"),rs.getInt("UserID"),rs.getString("StoreName"),rs.getString("ProductName"),rs.getString("Description"),
                                       rs.getInt("CatID"),rs.getDouble("Price"),rs.getInt("Quantity"),rs.getString("PhotoURL"),rs.getInt("Status"),rs.getInt("DeliveryDuration"),rs.getString("DeliveryType"),"",createdDateStr) ;
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
     
     // Upload item photo
     private ArrayList<ProductPhotoBean> uploadPhoto() throws SQLException, Exception {

        getConnection();
        ArrayList<ProductPhotoBean> result = new ArrayList<ProductPhotoBean>();
        String successMsg = "";
        int successId = 0;
        
        String uploadedPath = "";
        String uploadedPathRelative = "";
        String photoName = "";

        if( uploadedInputStream != null && body != null  ){
            String mimeType = body.getMediaType().toString();
            System.out.println("mimeType = "+mimeType);

            RandomString  r = new RandomString(20);
            photoName = r.nextString();
            photoName = photoName+".jpg";
            LocalProperties local = new LocalProperties();
            String path= local.getString("uploadedPath");
            uploadedPathRelative = local.getString("uploadedPathRelative");
            uploadedPath = path;  
       }
        
        if(!uploadedPath.equals("")){
            UploadFile upload = new UploadFile();
            upload.uploadFile(photoName, uploadedPath, uploadedInputStream);
            productPhotoBean.setPhotoURL(uploadedPathRelative+photoName);
        }

//Call Database Stored Procedure
        statement = con.prepareCall("{call spUploadProductPhoto(?,?,?,?)}");
        
        statement.setInt(1, productPhotoBean.getProductID());
        statement.setString(2, productPhotoBean.getPhotoURL());
        
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
                  result.add(new ProductPhotoBean(rs.getInt("PhotoID"),rs.getInt("ProductID"),rs.getString("PhotoURL")) );
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
    
     // Get Item photos
    private ArrayList<ProductPhotoBean> getProductPhotos() throws SQLException, Exception {

        getConnection();
        ArrayList<ProductPhotoBean> result = new ArrayList<ProductPhotoBean>();
        String successMsg = "";
        int successId = 0;

        //Call Database Stored Procedure
        statement = con.prepareCall("{call spGetProductPhotos(?,?,?)}");
        
        statement.setInt(1, productPhotoBean.getProductID());
        
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
                  result.add(new ProductPhotoBean(rs.getInt("PhotoID"),rs.getInt("ProductID"),
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
    
    //Delete item photo
    private ArrayList<ProductPhotoBean> deletePhoto() throws SQLException, Exception {

        getConnection();
        ArrayList<ProductPhotoBean> result = new ArrayList<ProductPhotoBean>();
        String successMsg = "";
        int successId = 0;

        //Call Database Stored Procedure
        statement = con.prepareCall("{call spDeleteProductPhoto(?,?,?)}");
        
        statement.setInt(1, productPhotoBean.getPhotoID());
        
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
                  result.add(new ProductPhotoBean(rs.getInt("PhotoID"),rs.getInt("ProductID"),rs.getString("PhotoURL")) );
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
    
    //Delete item
    private ArrayList<ProductBean> deleteProduct() throws SQLException, Exception {

        getConnection();
        ArrayList<ProductBean> result = new ArrayList<ProductBean>();
        String successMsg = "";
        int successId = 0;
//Call Database Stored Procedure
        statement = con.prepareCall("{call spDeleteProduct(?,?,?)}");
        
        statement.setInt(1, productBean.getProductID());
        
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

                result.add (new ProductBean(rs.getInt("ProductID"),rs.getInt("UserID"),rs.getString("StoreName"),rs.getString("ProductName"),rs.getString("Description"),
                                       rs.getInt("CatID"),rs.getDouble("Price"),rs.getInt("Quantity"),rs.getString("PhotoURL"),rs.getInt("Status"),rs.getInt("DeliveryDuration"),rs.getString("DeliveryType"),"",createdDateStr) );
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
     * Call API methods
     * @return
     * @throws Exception
     */
    public Object implementProductMethod() throws Exception {
        Object result;
        result = this.generalSkelton(this.req, (Callable) () -> {
            switch (functionName) {
                case "add":
                    return (ProductBean) addProduct();
                case "update":
                    return (ProductBean) updateProduct();
                case "products":
                    return (ArrayList<ProductBean>) getProducts();
                case "home":
                    return (ArrayList<ProductBean>) getHomeProducts();
                case "order":
                    return (ArrayList<ProductBean>) getOrderProducts();
                case "search":
                    return (ArrayList<ProductBean>) searchProducts();
                case "store":
                    return (ArrayList<ProductBean>) getStoreProducts();
                case "info":
                    return (ProductBean) getProductInfo();
                case "photos":
                    return (ArrayList<ProductPhotoBean>) getProductPhotos();
                case "upload":
                    return (ArrayList<ProductPhotoBean>) uploadPhoto(); 
                case "deletePhoto":
                    return (ArrayList<ProductPhotoBean>) deletePhoto();
                case "deleteProduct":
                    return (ArrayList<ProductBean>) deleteProduct();
                default:
                    return null;

            }
        });
        return result;
    }
}
