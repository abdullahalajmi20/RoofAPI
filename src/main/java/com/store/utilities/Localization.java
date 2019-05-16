/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.utilities;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Abdullah
 */
public class Localization {
    
    /**
     *
     * @param code
     * @param language
     * @return
     */
    public static String getMessage(String code,String language){
      
        String message = "";

        Locale currentLocale;
        ResourceBundle messages;
      try{
        currentLocale = new Locale(language);
          System.out.println("currentLocale = "+currentLocale.getLanguage());
        messages = ResourceBundle.getBundle("resources/MessagesBundle",currentLocale);

        message = messages.getString(code);

        if(message.equals("")){
            return "Error Code ("+code+", "+language+")";
        }

        return messages.getString(code);
      }catch(Exception ex){
        currentLocale = new Locale("en_US");

        messages = ResourceBundle.getBundle("resources/MessagesBundle",currentLocale);

        message = messages.getString(code);

        if(message.equals("")){
            return "Error Code ("+code+", "+language+")";
        }
        return messages.getString(code);
      }
      
    }
    
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
       System.out.println("Current Locale: " + Locale.getDefault());
        ResourceBundle mybundle = ResourceBundle.getBundle("resources/MessagesBundle");

        // read MyLabels_en_US.properties
        System.out.println("Message1: " + mybundle.getString("1100"));

        Locale.setDefault(new Locale("ar", "KW"));
        
        // read MyLabels_ms_MY.properties
        System.out.println("Current Locale: " + Locale.getDefault());
        mybundle = ResourceBundle.getBundle("resources/MessagesBundle");
        System.out.println("Message: " + mybundle.getString("1101"));

        System.out.println("Message55 : "+getMessage("1111","en"));
	}
    
}