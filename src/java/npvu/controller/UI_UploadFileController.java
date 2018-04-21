/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import npvu.constant.FileConstant;
import npvu.model.TapTinModel;
import npvu.util.ClassCommon;


/**
 *
 * @author npvu
 */
@ManagedBean(name="UI_UploadFileController")
@ViewScoped
@MultipartConfig(maxFileSize=100,maxRequestSize=200)
public class UI_UploadFileController {

    private TapTinModel objTapTin = new TapTinModel();
    
    private Part file;
    
    public UI_UploadFileController() {
        
    }

    public void actionUpload( ) throws IOException{
        String pathUpload = ClassCommon.getRealPath()+FileConstant.PATH_UPLOAD_TEMP;
        File checkFolder  = new File(pathUpload);
        if(!checkFolder.exists()){
            checkFolder.mkdirs();
        }
        InputStream inputStream = file.getInputStream();        
        FileOutputStream outputStream = new FileOutputStream(pathUpload+getFileName(file));
         
        byte[] buffer = new byte[4096];        
        int bytesRead = 0;
        while(true) {                        
            bytesRead = inputStream.read(buffer);
            if(bytesRead > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }else {
                break;
            }                       
        }
        outputStream.close();
        inputStream.close();
    }
    
    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
            }
        }
        return null;
    }
    /* Getter & Setter */
    
    public TapTinModel getObjTapTin() {
        return objTapTin;
    }

    public void setObjTapTin(TapTinModel objTapTin) {
        this.objTapTin = objTapTin;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
    
}
