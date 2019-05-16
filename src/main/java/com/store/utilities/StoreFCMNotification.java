package com.store.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.json.JSONObject;

/**
 *
 * @author Abdullah
 */
public class StoreFCMNotification {

    /**
     *
     */
    public static final String deviceToken ="djFW73Yi77M:APA91bEMYhBseSvBMLFpYu0fP9pquqy-WmzHkefNDP_VS2-laeJAnL4ibyEHGMrWRiT5Cg79jKAvQvbtHs0ANOGoT2hmc_wjN1_xzsuu-YVHpu4i9uzP2661JNWGbSDEepOtwrhNiywN";
	
    /**
     *
     * @param args
     */
    public static void main(String[] args) {

	
		try {
			pushFCMNotification(deviceToken,"Hello Bro");
	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Exception e="+e);
		}
	}

    /**
     *
     * @param deviceToken
     * @param message
     * @throws Exception
     */
    public static void pushFCMNotification(String deviceToken,String message) throws Exception {
		
	    String authKey = "AAAA4rzoGCI:APA91bGLDMs2f8MnGwLQ5850CAM3zwn1eBQejkkZ4Kmr3XVg5zmKHBhBtEJRnNZa0uU0NKSIO6oij65R_tAnzAvAx-oWJnkE6vqtEvq92oJXxjEREHPhjRgz60CRCX14S_8xnvCvHfbw";
	    // You FCM AUTH key
	    String FMCurl = "https://fcm.googleapis.com/fcm/send";
	
	    URL url = new URL(FMCurl);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
	    conn.setUseCaches(false);
	    conn.setDoInput(true);
	    conn.setDoOutput(true);
	
	    conn.setRequestMethod("POST");
	    conn.setRequestProperty("Authorization", "key=" + authKey);
	    conn.setRequestProperty("Content-Type", "application/json");
	
	    JSONObject data = new JSONObject();
	    data.put("to", deviceToken);
	    JSONObject info = new JSONObject();
	    info.put("message", message); // Message
	    data.put("data", info);
	    
	
	    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	    wr.write(data.toString());
	    wr.flush();
	    wr.close();
	
	    int responseCode = conn.getResponseCode();
	    System.out.println("Response Code : " + responseCode);
	
	    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();
	
	    while ((inputLine = in.readLine()) != null) {
	        response.append(inputLine);
	    }
	    in.close();
	    System.out.println("Response Code : " + response);
	}
	
	}
