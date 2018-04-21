/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import npvu.model.TapTinModel;


/**
 *
 * @author npvu
 */
@ManagedBean(name="UI_UploadFileController")
@ViewScoped
public class UI_UploadFileController implements Serializable{

    private TapTinModel objTapTin = new TapTinModel();
    
    
    public UI_UploadFileController() {
    }

    public void actionUploadAvatar( ){

    }
    
    /* Getter & Setter */
    
    public TapTinModel getObjTapTin() {
        return objTapTin;
    }

    public void setObjTapTin(TapTinModel objTapTin) {
        this.objTapTin = objTapTin;
    }
    
}
