/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.constant;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author npvu
 */
@ManagedBean (name="FileConstant")
public class FileConstant {
    
    public static final String PATH_UPLOAD_TEMP         = "/data/upload/temp/";
    public static final String PATH_UPLOAD_AVATAR       = "/data/upload/avatar/";
    public static final String[] ALLOW_EXTENSION        = {".doc",".docx",".xls",".xlsx",".pdf",".png",".jpg"};
    
    
    private String path_upload_temp                      = PATH_UPLOAD_TEMP;

    public String getPath_upload_temp() {
        return path_upload_temp;
    }

    public void setPath_upload_temp(String path_upload_temp) {
        this.path_upload_temp = path_upload_temp;
    }
}
