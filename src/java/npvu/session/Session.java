/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.session;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author npvu
 */
@ManagedBean(name="Session")
@SessionScoped
public class Session implements Serializable{
    public static Boolean statusUpload;
    public static String pathFile;
    public static String fileName;
    public static String fileRealName;
    
}
