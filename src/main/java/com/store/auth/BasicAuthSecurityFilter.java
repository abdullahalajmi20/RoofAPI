/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.auth;


import com.store.beans.ErrorBean;
import com.store.utilities.LocalProperties;
import com.store.utilities.Utilities;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.POST;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import org.glassfish.jersey.internal.util.Base64;

/**
 *
 * @author Abdullah
 */
@Provider
public class BasicAuthSecurityFilter implements ContainerRequestFilter {

    @Context
    private HttpServletRequest request;

    private static final String AUTHORIZATION_HEADER_KEY = "Authorization";
    private static final String LANGUAGE_HEADER_KEY = "Accept-Language";
    private static final String AUTHORIZATION_HEADER_PREFIX = "Basic ";
    private static final String CONTENT_HEADER_PREFIX = "Content-Type";
    private static final String SECURED_URL_PREFIX = "resources";

    ErrorBean errorBean = null;
    private String authUser = "";
    private String authPassword = "";

    LocalProperties local = new LocalProperties();

    // Filter request by basic authentication
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {

        try {
            requestContext.setProperty("myObject", requestContext);
            HttpSession session = request.getSession();
            
            
            if (requestContext.getUriInfo().getAbsolutePath().getPath().contains(SECURED_URL_PREFIX)) {
                List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
                List<String> langHeader = requestContext.getHeaders().get(LANGUAGE_HEADER_KEY);
                
                List<String> contentType = requestContext.getHeaders().get(CONTENT_HEADER_PREFIX);
                if (authHeader != null && authHeader.size() > 0) {
                    authUser = local.getString("basicAuthUser");
                    authPassword = local.getString("basicAuthPassword");
                    String authToken = authHeader.get(0);
                    authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
                    String decodedString = Base64.decodeAsString(authToken);
                    StringTokenizer tokenizer = new StringTokenizer(decodedString, ":");
                    String username = tokenizer.nextToken();
                    String password = tokenizer.nextToken();
                    
                    if (authUser.equals(username) && authPassword.equals(password)) {
                         String language = langHeader.get(0);
                         Logger.getLogger(BasicAuthSecurityFilter.class.getName()).log(Level.SEVERE, "Accept language Basic Authentication>>>>>" + language);
                         language = (language!=null)?language:"en-us";
                        if (language.contains(",")) {
                            language = language.split(",")[0];
                        }

                        if (language != null && !language.equals("")) {
                            language = language.toLowerCase();
                            session.setAttribute("LANGUAGE", language);
                        } else {
                            session.setAttribute("LANGUAGE", "en-us");
                        }

                        return;
                    }

                }
                String errorMessage = "1113";
                errorBean = Utilities.getErrorBean(request, errorMessage, 401);

                Response unauthorizedStatus = Response
                        .status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON)
                        .entity(errorBean)
                        .build();

                requestContext.abortWith(unauthorizedStatus);

            }
        } catch (Exception e) {

            Logger.getLogger(BasicAuthSecurityFilter.class.getName()).log(Level.SEVERE, "Exception Basic Authentication >>>>>" + e);

            String errorMessage = "User cannot access the resource.";
            errorBean = Utilities.getErrorBean(request, errorMessage, 401);

            Response unauthorizedStatus = Response
                    .status(Response.Status.UNAUTHORIZED).type(MediaType.APPLICATION_JSON)
                    .entity(errorBean)
                    .build();

            requestContext.abortWith(unauthorizedStatus);
        }

    }

    /**
     *
     * @param crc
     * @return
     */
    @POST
    public Response getResponse(@Context ContainerRequestContext crc) {
        return Response.ok(crc.getProperty("myObject")).build();
    }

    /**
     *
     * @param myObject
     * @return
     */
    @GET
    public Response getTest(@Context ContainerRequestContext myObject) {
        return Response.ok(myObject.getClass()).build();
    }

}
