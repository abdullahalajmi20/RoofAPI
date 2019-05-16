package com.store.utilities;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author
 */
public class LocalProperties {
  final static String PROPERTIES_FILENAME = "application.properties";

    /**
     * Get Message String
     * @param keyName
     * @return
     */
    public String getString(String keyName) {
    InputStream in = null;
    try {
      Properties prop = new Properties();
      in = getClass().getResourceAsStream(PROPERTIES_FILENAME);
      prop.load(in);
      in.close();

      return(prop.getProperty(keyName).trim());
    } catch (IOException ex) {
      Logger.getLogger(LocalProperties.class.getName()).log(Level.SEVERE, null, ex);
      return("");
    }
  }

    /**
     * Convert String to double
     * @param keyName
     * @return
     */
    public double getDouble(String keyName) {
    return Double.parseDouble(this.getString(keyName));
  }

    /**
     * Convert String to integer
     * @param keyName
     * @return
     */
    public int getInteger(String keyName) {
    return Integer.parseInt(this.getString(keyName));
  }

    /**
     * Convert String to boolean
     * @param keyName
     * @return
     */
    public boolean getBoolean(String keyName) {
    return Boolean.parseBoolean(this.getString(keyName));
  }

    /**
     * Convert String to String
     * @param keyName
     * @return
     */
    public static String getString2(String keyName) {
    FileInputStream in = null;
    try {
      // create and load default properties
      Properties defaultProps = new Properties();
      in = new FileInputStream(PROPERTIES_FILENAME);
      //InputStream in = getClass().getResourceAsStream(PROPERTIES_FILENAME);
      defaultProps.load(in);

      return(defaultProps.getProperty(keyName));
    } catch (FileNotFoundException ex) {
      Logger.getLogger(LocalProperties.class.getName()).log(Level.SEVERE, null, ex);
      return("");
    } catch (IOException ex) {
      Logger.getLogger(LocalProperties.class.getName()).log(Level.SEVERE, null, ex);
      return("");
    } finally {
      try {
        in.close();
      } catch (IOException ex) {
        Logger.getLogger(LocalProperties.class.getName()).log(Level.SEVERE, null, ex);
      }
    }
  }
}
