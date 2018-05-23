/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.session;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import npvu.model.ConfigWebsiteModel;

/**
 *
 * @author npvu
 */
@ManagedBean(name="SessionBean")
@SessionScoped
public class SessionBean implements Serializable{
    public static Boolean statusUpload = false;
    public static String pathFile;
    public static String fileName;
    public static String fileRealName;
    
    public static void resetSession(){
        statusUpload = false;
        pathFile = null;
        fileName = null;
        fileRealName = null;
    }
    
    public String getFileName(){
        return fileName;
    }
    
    public String getPathFile(){
        return pathFile;
    }
    
    public boolean isStatusUpload() {
        return statusUpload;
    }
}
