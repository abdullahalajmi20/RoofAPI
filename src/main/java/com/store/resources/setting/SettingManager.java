/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.resources.setting;

import com.store.beans.CategoryBean;
import com.store.beans.KeyValueBean;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import com.store.implementation.SettingImplementation;

/**
 *
 * @author Abdullah
 */
public class SettingManager {
    
    /**
     *
     * @param keyValueBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<KeyValueBean> getGovernorate(KeyValueBean keyValueBean, HttpServletRequest req) throws Exception 
    {
        SettingImplementation serviceImplementation = new SettingImplementation(req,"governorate", keyValueBean);
                                 
        return (ArrayList<KeyValueBean> ) serviceImplementation.implementSettingMethod();
    }
    
    /**
     *
     * @param keyValueBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<KeyValueBean> getAreas(KeyValueBean keyValueBean, HttpServletRequest req) throws Exception 
    {
        SettingImplementation serviceImplementation = new SettingImplementation(req,"Area", keyValueBean);
                                 
        return (ArrayList<KeyValueBean> ) serviceImplementation.implementSettingMethod();
    }
    
    /**
     *
     * @param keyValueBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<KeyValueBean> getInvCategories(KeyValueBean keyValueBean, HttpServletRequest req) throws Exception 
    {
        SettingImplementation serviceImplementation = new SettingImplementation(req,"invoiceCat", keyValueBean);
                                 
        return (ArrayList<KeyValueBean> ) serviceImplementation.implementSettingMethod();
    }
    
    /**
     *
     * @param keyValueBean
     * @param req
     * @return
     * @throws Exception
     */
    public ArrayList<CategoryBean> getProCategories(KeyValueBean keyValueBean, HttpServletRequest req) throws Exception 
    {
        SettingImplementation serviceImplementation = new SettingImplementation(req,"productCat", keyValueBean);
                                 
        return (ArrayList<CategoryBean> ) serviceImplementation.implementSettingMethod();
    }
    
    
    
}
