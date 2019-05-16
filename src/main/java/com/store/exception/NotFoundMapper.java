/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.exception;

import com.store.beans.ErrorBean;
import com.store.beans.ErrorDetailsBean;
import com.store.beans.ExtraErrorsBean;
import com.store.utilities.LocalProperties;

import java.util.ArrayList;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Abdullah
 */
@Provider
public class NotFoundMapper implements ExceptionMapper<NotFoundException> {

    // Get Not found Error
   public Response toResponse(NotFoundException e) {
       
       System.out.println("In NotFoundMapper");
      LocalProperties local = new LocalProperties();
           String apiVersion = local.getString("apiVersion");

           ErrorBean errorBean = null;
           ErrorDetailsBean errorDetailsBean = null;
           ArrayList<ExtraErrorsBean> extraErrorsBean = new ArrayList<ExtraErrorsBean>();

           String errorMessage = "Not found resource.";
            extraErrorsBean.add(new ExtraErrorsBean("", "", errorMessage, "", "", "", ""));
            errorDetailsBean = new ErrorDetailsBean(404, errorMessage, extraErrorsBean.toArray(new ExtraErrorsBean[extraErrorsBean.size()]));

            errorBean = new ErrorBean(apiVersion, errorDetailsBean);

    		return Response.status(Response.Status.NOT_FOUND)
    				.entity(errorBean)
    				.type(MediaType.APPLICATION_JSON).
    				build();
   }

}
