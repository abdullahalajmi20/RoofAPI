/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.resources.setting;

import com.store.beans.CategoryBean;
import com.store.beans.KeyValueBean;
import java.util.ArrayList;
import javax.ejb.Stateful;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import lombok.NonNull;

/**
 *
 * @author Abdullah
 */
@Stateful
@Path("/setting")
public class SettingResources {
    
    /**
     *
     * @param req
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getGovernorate")
    public KeyValueBean [] getGovernorate(
                                @Context HttpServletRequest req
                               ) throws Exception {
        ArrayList<KeyValueBean> schoolTypeArray = null;
        
        SettingManager  serviceManager = new SettingManager();
        
        schoolTypeArray = serviceManager.getGovernorate(null, req);
    return  schoolTypeArray.toArray(new KeyValueBean[schoolTypeArray.size()]) ;
        
    }
    
    /**
     *
     * @param req
     * @param govID
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getAreas/{GOV_ID}")
    public KeyValueBean [] getAreas(
                                @Context HttpServletRequest req,
                                @PathParam("GOV_ID") @NonNull Integer govID
                               ) throws Exception {
        ArrayList<KeyValueBean> areasArray = null;
        
        KeyValueBean keyValueBean = new KeyValueBean();
        keyValueBean.setServiceID(govID);
        
        SettingManager  serviceManager = new SettingManager();
        
        areasArray = serviceManager.getAreas(keyValueBean, req);
    return  areasArray.toArray(new KeyValueBean[areasArray.size()]) ;
        
    }
    
    /**
     *
     * @param req
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getProCategories")
    public CategoryBean [] getProCategories(
                                @Context HttpServletRequest req
                               ) throws Exception {
        ArrayList<CategoryBean> proCategoryArray = null;
        
        SettingManager  serviceManager = new SettingManager();
        
        proCategoryArray = serviceManager.getProCategories(null, req);
    return  proCategoryArray.toArray(new CategoryBean[proCategoryArray.size()]) ;
        
    }
    
    /**
     *
     * @param req
     * @return
     * @throws Exception
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Path("/getInvCategories")
    public KeyValueBean [] getInvCategories(
                                @Context HttpServletRequest req
                               ) throws Exception {
        ArrayList<KeyValueBean> invCategoryArray = null;
        
        SettingManager  serviceManager = new SettingManager();
        
        invCategoryArray = serviceManager.getInvCategories(null, req);
    return  invCategoryArray.toArray(new KeyValueBean[invCategoryArray.size()]) ;
        
    }
}
