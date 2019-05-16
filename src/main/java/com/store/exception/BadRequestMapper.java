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
import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Abdullah
 */

@Provider
public class BadRequestMapper  implements ExceptionMapper<BadRequestException> {

    // Get Bad request Error
    @Override
   public Response toResponse(BadRequestException e) {
      System.out.println("In BadRequestMapper");
               
      LocalProperties local = new LocalProperties();
           String apiVersion = local.getString("apiVersion");

           ErrorBean errorBean = null;
           ErrorDetailsBean errorDetailsBean = null;
           ArrayList<ExtraErrorsBean> extraErrorsBean = new ArrayList<ExtraErrorsBean>();

           String errorMessage = "Bad request.";
            extraErrorsBean.add(new ExtraErrorsBean("", "", errorMessage, "", "", "", ""));
            errorDetailsBean = new ErrorDetailsBean(400, errorMessage, extraErrorsBean.toArray(new ExtraErrorsBean[extraErrorsBean.size()]));

            errorBean = new ErrorBean(apiVersion, errorDetailsBean);

    		return Response.status(Response.Status.BAD_REQUEST)
    				.entity(errorBean)
    				.type(MediaType.APPLICATION_JSON).
    				build();
   }

}
