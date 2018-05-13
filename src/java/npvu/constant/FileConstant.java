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
    public static final String PATH_UPLOAD_IMAGE       = "/data/upload/images/";
    public static final String[] ALLOW_EXTENSION        = {".doc",".docx",".xls",".xlsx",".pdf",".png",".jpg"};
    public static final String[] ALLOW_EXTENSION_IMAGE  = {".png",".jpg",".git",".ico",".icon"};
    
    public static final String DAUCACH_TENFILE          = "_";
    
    private String path_upload_temp                     = PATH_UPLOAD_TEMP;
    private String path_upload_avatar                   = PATH_UPLOAD_AVATAR;
    private String path_upload_image                    = PATH_UPLOAD_IMAGE;
    
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

    public String getPath_upload_image() {
        return path_upload_image;
    }

    public void setPath_upload_image(String path_upload_image) {
        this.path_upload_image = path_upload_image;
    }
}
