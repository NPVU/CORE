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
    public static final String[] ALLOW_EXTENSION_IMAGE  = {".png",".jpg",".git",".ico",".icon"};
    
    
    private String path_upload_temp                     = PATH_UPLOAD_TEMP;
    private String path_upload_avatar                   = PATH_UPLOAD_AVATAR;
    public String getPath_upload_temp() {
        return path_upload_temp;
    }

    public void setPath_upload_temp(String path_upload_temp) {
        this.path_upload_temp = path_upload_temp;
    }

    public String getPath_upload_avatar() {
        return path_upload_avatar;
    }

    public void setPath_upload_avatar(String path_upload_avatar) {
        this.path_upload_avatar = path_upload_avatar;
    }
}
