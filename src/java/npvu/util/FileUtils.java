/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.util;

/**
 *
 * @author npvu
 */
public class FileUtils {
    
    public static String getExtension(String path){
        String extension = null;
        int index = path.lastIndexOf(".");
        if(index > 0){
            extension = path.substring(index);
        }
        return extension;
    }
}
