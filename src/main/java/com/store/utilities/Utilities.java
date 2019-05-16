
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.utilities;


import com.store.beans.ErrorBean;
import com.store.beans.ErrorDetailsBean;
import com.store.beans.ExtraErrorsBean;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Abdullah
 */
public class Utilities {
    
    /**
     *
     * @param req
     * @param key
     * @return
     */
    public static String translate(HttpServletRequest req, String key) {
    Language language = decodeLanguage(req);
         System.out.println("translate >>>>>>>>>> language = "+language);
    return(translate(language, key));
  }

    /**
     *
     * @param language
     * @param key
     * @return
     */
    public static String translate(Language language, String key) {
    return Localization.getMessage(key, language.name().toLowerCase());
  }
  
    /**
     *
     */
    public enum Language {

        /**
         *
         */
        EN,

        /**
         *
         */
        AR
    }
   
    /**
     *
     * @param req
     * @return
     */
    public static Language decodeLanguage(
    HttpServletRequest req
  ) {
    String lang = req.getHeader("Accept-Language");
    
    if (lang!=null) {
        if (lang.equals("en")) {
          return(Language.EN);
        } else if (lang.equals("ar")) {
          return(Language.AR);
        } else {
          return(Language.EN);
        }
    } else {
      return(Language.EN);  
    }
  }

    /**
     *
     * @param lang
     * @return
     */
    public static Language decodeLanguage(
    String lang
  ) {
    if (lang.equals("en")) {
      return(Language.EN);
    } else if (lang.equals("ar")) {
      return(Language.AR);
    } else {
      return(Language.EN);
    }
  }

    /**
     *
     * @param language
     * @return
     */
    public static String languageToStr(Language language) {
    String result = "en";
    switch(language) {
      case EN:
        result = "en";
        break;
      case AR:
        result = "ar";
        break;
    }
    return(result);
  }
  
    /**
     *
     * @return
     */
    public static String getCurrentMonthName(){
    String[] monthName = { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL",
        "AUG", "SEP", "OCT", "NOV", "DEC" };

    Calendar cal = Calendar.getInstance();
    String month = monthName[cal.get(Calendar.MONTH)];
     
    return month;
}
  
    /**
     *
     * @return
     */
    public static int getCurrentYear(){

    Calendar cal = Calendar.getInstance();
    cal.get(Calendar.YEAR);
     
    return Integer.parseInt(String.valueOf(cal.get(Calendar.YEAR)).substring(2));
}

    /**
     *
     * @param monthName
     * @return
     */
    public static int getMonthNo(String monthName){
    
    int monthNo = 0;
    
    String[] monthNameArray = {"", "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL",
        "AUG", "SEP", "OCT", "NOV", "DEC" };
    
    for(int i=1;i<monthNameArray.length;i++){
        if(monthNameArray[i].equals(monthName)){
            monthNo = i;
        }
    }
    
    return monthNo;
}

    /**
     *
     * @param inDate
     * @param pattern
     * @return
     */
    public static boolean isValidDate(String inDate, String pattern) {
    
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        dateFormat.setLenient(false);
        try {
          Date date = dateFormat.parse(inDate.trim());
          if (dateFormat.format(date).equals(inDate.trim())) {
            return true;
          }else{
              return false;
          }

        } catch (ParseException pe) {
          return false;
        }

  }

    /**
     *
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    /**
     *
     * @param emailStr
     * @return
     */
    public static boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return matcher.find();
    }
    
    /**
     *
     * @param mimeType
     * @return
     */
    public static boolean checkIOSCertExt(String mimeType){
        
        ArrayList <String> certMimeTypes = new ArrayList<String>();
        System.out.println("mimeType = "+mimeType);
        certMimeTypes.add("application/x-pkcs12");
//        certMimeTypes.add("application/octet-stream");
        return certMimeTypes.contains(mimeType);
        
    }
    
    /**
     *
     * @param mimeType
     * @return
     */
    public static String getCertificationExt(String mimeType){
        String ext="";
        if(mimeType.equals("application/x-pkcs12")){
            ext="p12";
        }
//        if(mimeType.equals("application/octet-stream")){
//            ext="pem";
//        }
      
      return ext;
    }
    
    /**
     *
     * @param mimeType
     * @return
     */
    public static boolean checkImageMimeType(String mimeType){
        
        ArrayList <String> imageMimeTypes = new ArrayList<String>();
        imageMimeTypes.add("image/png");
        imageMimeTypes.add("image/bmp");
        imageMimeTypes.add("image/gif");
        imageMimeTypes.add("image/jpeg");
        imageMimeTypes.add("image/jpg");
        return imageMimeTypes.contains(mimeType);
        
    }
    
    /**
     *
     * @param time
     * @return
     */
    public static long unixTimeConversion(String time)
    {
        long unixtime = 0;
        DateFormat unixDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS"); 
        unixDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+5:30"));//Specify your timezone 
        try{ 
             if(!time.isEmpty() && time != null){ 
                unixtime = unixDateFormat.parse(time).getTime();  
                unixtime=unixtime/1000;
               }else{
                return 0;
            }
        }catch (ParseException e) {
            e.printStackTrace();
        }
       return unixtime;
    }
    
    /**
     *
     * @param req
     * @param message
     * @param errorCode
     * @return
     */
    public static ErrorBean getErrorBean(HttpServletRequest req, String message, int errorCode){
           LocalProperties local = new LocalProperties();
           String apiVersion = local.getString("apiVersion");
           ErrorBean errorBean = null;
           ErrorDetailsBean errorDetailsBean = null;
           ArrayList<ExtraErrorsBean> extraErrorsBean = new ArrayList<ExtraErrorsBean>();

           String errorMessage = Utilities.translate(req, message);
                   
            extraErrorsBean.add(new ExtraErrorsBean("", "", errorMessage, "", "", "", ""));
             Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE,
                "{0}"+ "errorExceptionCode2>>>>>" + "  ", errorCode);
            errorDetailsBean = new ErrorDetailsBean(errorCode, errorMessage, extraErrorsBean.toArray(new ExtraErrorsBean[extraErrorsBean.size()]));
            errorBean = new ErrorBean(apiVersion, errorDetailsBean);
                    
       return errorBean;
   }
    
    /**
     *
     * @param json
     * @return
     */
    public static boolean isValidJSONArray(String json){
        try {
            new JSONArray(json);
            return true;
        } catch (JSONException ex1) {
            return false;
        }
    }
    
    /**
     *
     * @param args
     */
    public static void main(String [] args){
        String array = "[{ \"G7I2v06wMa5cRbOm4qKq2_R2jm_vkXzKCpyKOrQYdiqSl6BkEgo6mNDL2jJrGrLMnQQPaDLkJMHRypeATLK798fF7f0jwQtZxqKmi7OzSbluMcnQIH32s4-T-HY84NgfciwXoQ\"}]";
        String array2="[{ \"key\": 1,     \"value\": G7I2v06wMa5cRbOm4qKq2_R2jm_vkXzKCpyKOrQYdiqSl6BkEgo6mNDL2jJrGrLMnQQPaDLkJMHRypeATLK798fF7f0jwQtZxqKmi7OzSbluMcnQIH32s4-T-HY84NgfciwXoQ }]";
        
        boolean test = isValidDate("25/11/201", "dd/MM/yyyy");
           
        System.out.println("Test = "+test);
    }
}
