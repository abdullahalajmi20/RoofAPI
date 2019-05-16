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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ValidationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Abdullah
 */

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException>{

     @Context
        HttpServletRequest request;
    
     // Get Validation Error
        @Override
        public Response toResponse(ValidationException ex) {
            System.out.println("In ValidationExceptionMapper");
            HttpSession session = request.getSession();
            
            String sessionMessage = "Validation Exception";

            
            int errorCode = Integer.parseInt("400");
               LocalProperties local = new LocalProperties();
                String apiVersion = local.getString("apiVersion");

                ErrorBean errorBean = null;
                ErrorDetailsBean errorDetailsBean = null;
                ArrayList<ExtraErrorsBean> extraErrorsBean = new ArrayList<ExtraErrorsBean>();

                extraErrorsBean.add(new ExtraErrorsBean("", "", sessionMessage, "", "", "", ""));
                errorDetailsBean = new ErrorDetailsBean(errorCode, sessionMessage, extraErrorsBean.toArray(new ExtraErrorsBean[extraErrorsBean.size()]));

                errorBean = new ErrorBean(apiVersion, errorDetailsBean);

                     return Response.status(errorCode)
                                     .entity(errorBean)
                                     .type(MediaType.APPLICATION_JSON).
                                     build();
        
        }
    
}
