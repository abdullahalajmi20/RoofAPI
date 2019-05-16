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
import com.store.utilities.Localization;
import com.store.utilities.Validators;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.media.multipart.FormDataParamException;
import org.glassfish.jersey.server.ParamException.FormParamException;
import org.glassfish.jersey.server.ParamException.PathParamException;
import org.glassfish.jersey.server.ParamException.QueryParamException;

/**
 *
 * @author Abdullah
 */

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Context
    HttpServletRequest request;

    // Get Generic Error
    @Override
    public Response toResponse(Throwable ex) {
          
        System.out.println("In GenericExceptionMapper");
        int errorCode;
        HttpSession session = request.getSession();
        String sessionMessage = (String) session.getAttribute("ERROR_MESSAGE");
        String errorCodeStr = (String) session.getAttribute("ERROR_CODE");
        String language = (String) session.getAttribute("LANGUAGE");

        System.out.println("sessionMessage = "+sessionMessage+", errorCodeStr = "+errorCodeStr+", language = "+language);
            ex.printStackTrace();
        if (ex instanceof FormParamException
                || ex instanceof PathParamException
                || ex instanceof QueryParamException
                || ex instanceof FormDataParamException) {
            sessionMessage = "1101";
            errorCodeStr = "400";
        } else if (ex instanceof NullPointerException) {
            sessionMessage = "1102";
            errorCodeStr = "400";
        } else if (ex instanceof NumberFormatException) {
            sessionMessage = "1101";
            errorCodeStr = "400";
        } else if (ex instanceof Exception) {
            System.out.println("instanceof Exception");
            sessionMessage = ex.getMessage().toUpperCase().replace("HTTP ", "").trim();
            errorCodeStr = ex.getMessage().toUpperCase().replace("HTTP ", "").trim();
        }

        if (errorCodeStr == null || errorCodeStr.equals("") || errorCodeStr.equals("null")) {
            errorCodeStr = "400";
            sessionMessage = "1100";
        }

        if (Validators.isNumber(errorCodeStr)) {
            errorCode = Integer.parseInt(errorCodeStr);
        } else {
            errorCode = 1100;
            sessionMessage = "1100";
        }

        sessionMessage = Localization.getMessage(sessionMessage, language);

        LocalProperties local = new LocalProperties();
        String apiVersion = local.getString("apiVersion");

        ErrorBean errorBean = null;
        ErrorDetailsBean errorDetailsBean = null;
        ArrayList<ExtraErrorsBean> extraErrorsBean = new ArrayList<ExtraErrorsBean>();

        extraErrorsBean.add(new ExtraErrorsBean("", "", sessionMessage, "", "", "", ""));
        errorDetailsBean = new ErrorDetailsBean(errorCode, sessionMessage, extraErrorsBean.toArray(new ExtraErrorsBean[extraErrorsBean.size()]));

        errorBean = new ErrorBean(apiVersion, errorDetailsBean);
        
        if (errorCode >= 1000) {
            errorCode = 412;//error in precondition 
        }

        return Response.status(errorCode)
                .entity(errorBean)
                .type(MediaType.APPLICATION_JSON).
                build();

    }

}
