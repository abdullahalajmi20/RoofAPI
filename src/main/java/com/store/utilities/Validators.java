/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.utilities;



/**
 *s
 * @author
 */
public class Validators {

    /**
     *
     * @param number
     * @return
     */
    public static boolean isMobileNumber(String number) {
      // Check for these
      if (number==null) return(false);
      if (number.isEmpty()) return(false);
      if (number.length()!=8) return(false);
      
      /*if( !(number.charAt(0)=='5' || number.charAt(0) =='9' || number.charAt(0)=='6'))
        return (false);
      */
      
      try{
        Integer.parseInt(number);
        return(true);
      } catch (NumberFormatException e) {
        return(false);
      }
    }

    /**
     *
     * @param number
     * @return
     */
    public static boolean isNumber(String number) {
      // Check for these
      if (number==null) return(false);
      if (number.isEmpty()) return(false);
      
      /*if( !(number.charAt(0)=='5' || number.charAt(0) =='9' || number.charAt(0)=='6'))
        return (false);
      */
      
      try{
        Integer.parseInt(number);
        return(true);
      } catch (NumberFormatException e) {
        return(false);
      }
    }
    
    /**
     *
     * @param number
     * @return
     */
    public static boolean isInteger(String number)  {
      try {
        Integer.parseInt(number);
        return(true);
      } catch (NumberFormatException e) {
        return(false);
      }
    }

    /**
     *
     * @param value
     * @return
     */
    public static String normalize(String value) {
        value = value.trim();
        if (value.indexOf('-')!=-1) { value = value.replace("-", ""); }
        if (value.indexOf('(')!=-1) { value = value.replace("(", ""); }
        if (value.indexOf(')')!=-1) { value = value.replace(")", ""); }
        if (value.indexOf(' ')!=-1) { value = value.replace(" ", ""); }

        if (value.startsWith("+")) {
            String ptn = "^\\+";  // Regex
            String rpltxt = "";  // Replacement string
            value = value.replaceFirst(ptn, rpltxt);
        }
        if (value.startsWith("00")) {
            String ptn = "^00";  // Regex
            String rpltxt = "";  // Replacement string
            value = value.replaceFirst(ptn, rpltxt);
        }

        if (value.startsWith("965")) {
            String ptn = "^965";  // Regex
            String rpltxt = "";  // Replacement string
            value = value.replaceFirst(ptn, rpltxt);
        }             
        value = value.trim();

        return value;
    }

    /**
     *
     * @param uuid
     * @return
     */
    public static boolean isUUID(String uuid) {
      // Check for these
      if (uuid==null) return(false);
      if (uuid.isEmpty()) return(false);
      if (uuid.length()!=36) return(false);

      String[] components = uuid.split("-");
      if (components.length != 5) return(false);
      
      return(true);
  }
}