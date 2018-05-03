/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;
import npvu.constant.FileConstant;
import npvu.constant.MessageConstant;
import npvu.dataprovider.TapTinDataProvider;
import npvu.model.TapTinModel;
import npvu.session.Session;
import npvu.util.ClassCommon;
import npvu.util.DateUtils;
import npvu.util.FileUtils;
import npvu.util.ShowGrowlUtils;


/**
 *
 * @author npvu
 */
@ManagedBean(name="UI_UploadFileController")
@ViewScoped
@MultipartConfig(maxFileSize=100,maxRequestSize=200)
public class UI_UploadFileController implements Serializable{

    private UIComponent uicFile;
    
    private TapTinModel objTapTin;
    
    private final TapTinDataProvider tapTinProvider = new TapTinDataProvider();
    
    private final ShowGrowlUtils showGrowl = new ShowGrowlUtils();
    
    private Part file;
    
    private String pathFile;
    
    private String fileName;
    
    private String fileRealName;
    
    private boolean statusUpload;
    
    public UI_UploadFileController() {
        // Kiểm tra các thư mục upload file
        File f1 = new File(ClassCommon.getPathResources()+FileConstant.PATH_UPLOAD_TEMP);
        if(!f1.exists()){
            f1.mkdirs();
        }
        
        File f2 = new File(ClassCommon.getPathResources()+FileConstant.PATH_UPLOAD_AVATAR);
        if(!f2.exists()){
            f2.mkdirs();
        }
        
        File f3 = new File(ClassCommon.getPathResources()+FileConstant.PATH_UPLOAD_IMAGE);
        if(!f3.exists()){
            f3.mkdirs();
        }
    }

    public void actionUploadAvatar( ) throws IOException{
        String pathUpload = ClassCommon.getPathResources()+FileConstant.PATH_UPLOAD_TEMP;        
        long ranNumber = DateUtils.getCurrentDate().getTime();
        fileRealName   = getFileName(file);
        fileName       = ranNumber + FileConstant.DAUCACH_TENFILE + getFileName(file);
        pathFile       = pathUpload + fileName; 
        if(FileUtils.checkExtension(FileUtils.getExtension(fileRealName), FileConstant.ALLOW_EXTENSION_IMAGE)){
            InputStream inputStream = file.getInputStream();        
            FileOutputStream outputStream = new FileOutputStream(pathFile);
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
            statusUpload = true;
            Session.statusUpload = true;
            setValueFileToSession();            
        } else {   
            statusUpload = false;
            Session.statusUpload = false;       
            showGrowl.showMessageError(MessageConstant.MESSAGE_ERROR_EXTENSION_AVATAR, uicFile);
        }
    }
    
    public void actionUploadImage( ) throws IOException{
        String pathUpload = ClassCommon.getPathResources()+FileConstant.PATH_UPLOAD_TEMP;        
        long ranNumber = DateUtils.getCurrentDate().getTime();
        fileRealName   = getFileName(file);
        fileName       = ranNumber + FileConstant.DAUCACH_TENFILE + getFileName(file);
        pathFile       = pathUpload + fileName; 
        if(FileUtils.checkExtension(FileUtils.getExtension(fileRealName), FileConstant.ALLOW_EXTENSION_IMAGE)){
            InputStream inputStream = file.getInputStream();        
            FileOutputStream outputStream = new FileOutputStream(pathFile);
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
            statusUpload = true;
            Session.statusUpload = true;
            setValueFileToSession();            
        } else {   
            statusUpload = false;
            Session.statusUpload = false;       
            showGrowl.showMessageError(MessageConstant.MESSAGE_ERROR_EXTENSION_AVATAR, uicFile);
        }
    }
    
    public void showMessageStatusUploadFile(){
        if(Session.statusUpload != null){
            if(Session.statusUpload){
                showGrowl.showMessageSuccess(MessageConstant.MESSAGE_UPLOAD_SUCCESS);
            } else {
                showGrowl.showMessageFatal(MessageConstant.MESSAGE_ERROR_EXTENSION_AVATAR);
            }
        }         
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
    
    public long actionUpdateTapTin(String path){
        objTapTin = new TapTinModel();        
        File f1 = new File(Session.pathFile);
        if(f1.exists()){
            File f2 = new File(ClassCommon.getPathResources()+path+Session.fileName);
            f1.renameTo(f2);
            try {
                InputStream ip = new FileInputStream(f2);
                objTapTin.setTen(Session.fileRealName);
                objTapTin.setTenLuuTru(Session.fileName);
                objTapTin.setSize(f2.length());
                objTapTin.setExtension(FileUtils.getExtension(Session.pathFile));
                objTapTin.setPath(f2.getAbsolutePath());
                objTapTin.setNgayTao(DateUtils.getCurrentDate());
                tapTinProvider.updateTapTin(objTapTin);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(UI_UploadFileController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return objTapTin.getId();
    }
    
    public void setValueFileToSession(){        
        Session.fileName     = fileName;
        Session.pathFile     = pathFile;
        Session.fileRealName = fileRealName;
    }
    
    public static void resetValueFileToSession(){
        Session.statusUpload = null;
        Session.fileName     = null;
        Session.pathFile     = null;
        Session.fileRealName = null;
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

    public String getPathFile() {
        return pathFile;
    }

    public void setPathFile(String pathFile) {
        this.pathFile = pathFile;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

    public UIComponent getUicFile() {
        return uicFile;
    }

    public void setUicFile(UIComponent uicFile) {
        this.uicFile = uicFile;
    }

    public boolean isStatusUpload() {
        return statusUpload;
    }

    public void setStatusUpload(boolean statusUpload) {
        this.statusUpload = statusUpload;
    }
    
}
