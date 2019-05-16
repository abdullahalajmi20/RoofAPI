/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.store.utilities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author Abdullah
 */
public class UploadFile {
    
    /**
     *
     * @param fileName
     * @param path
     * @param uploadedInputStream
     * @return
     */
    public String uploadFile(String fileName, String path, InputStream uploadedInputStream) {
    
        System.out.println("fileName = "+fileName+", path = "+path);
        String uploadedPath = null;
        try{
                path = path+ "/"+fileName;
                writeToFile(uploadedInputStream, path);
                uploadedPath = path;
                uploadedInputStream.close();
         }catch(Exception e){
             e.printStackTrace();
         }	
    
    return uploadedPath;

}

// save uploaded file to new location
private void writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {

    try {
          OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
        int read = 0;
        byte[] bytes = new byte[1024];

        out = new FileOutputStream(new File(uploadedFileLocation));
        
        while ((read = uploadedInputStream.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
        out.flush();
        out.close();
        
        uploadedInputStream.close();
    } catch (IOException e) {
        System.out.println("Error = "+e.getMessage());
        e.printStackTrace();
    }catch (Exception e) {
        System.out.println("Error = "+e.getMessage());
        e.printStackTrace();
    }

}
}
