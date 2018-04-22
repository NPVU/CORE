/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.util;

import javax.faces.context.FacesContext;

/**
 *
 * @author npvu
 */
public class ClassCommon {
    
    public static String getRealPath(){
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
    }
    
    public static String getPathResources(){
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/");
    }
}
