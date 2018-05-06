/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.controller;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import npvu.constant.Constant;
import npvu.constant.MessageConstant;
import npvu.dataprovider.TaiKhoanDataProvider;
import npvu.model.TaiKhoanModel;
import npvu.util.RoleUtils;
import npvu.util.ShowGrowlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author npvu
 */
@ManagedBean(name="ThongTinTaiKhoanController")
@ViewScoped
public class ThongTinTaiKhoanController implements Serializable{

    private static final Logger log = LoggerFactory.getLogger(ThongTinTaiKhoanController.class);
    
    @ManagedProperty(value="#{UI_UploadFileController}")
    private UI_UploadFileController uiUploadFile;
    
    private final TaiKhoanDataProvider tkProvider = new TaiKhoanDataProvider();
    
    private final ShowGrowlUtils showGrowl = new ShowGrowlUtils();
    
    private final RoleUtils roleUtils               = new RoleUtils();
    
    private UIComponent uicResultThongTin;
    
    private UIComponent uicMatKhau;
    
    private UIComponent uicReMatKhau;
    
    private TaiKhoanModel objTaiKhoan;
    
    private int viewMode = 0;
    
    public ThongTinTaiKhoanController() {
        if(!Login.logined){
            try {
                ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
                if(!ec.isResponseCommitted()){
                    ec.redirect(ec.getRequestContextPath() + Constant.URL_DANGNHAP);
                } 
            } catch (IOException ex) {;               
                log.error("<<< Chưa đăng nhập >>>");
            }
        } else {
            objTaiKhoan = tkProvider.getTaiKhoanByTenDangNhap(Login.objTaiKhoan.getTenDangNhap());
        }
    }
    
    public void actionUpdateThongTinTaiKhoan(){
        if(tkProvider.updateTaiKhoan(objTaiKhoan)){
            Login lg = new Login();
            lg.refreshTaiKhoan(objTaiKhoan.getTenDangNhap());
            showGrowl.showMessageSuccess(MessageConstant.MESSAGE_SUCCESS_UPDATE);
        } else {
            showGrowl.showMessageFatal(MessageConstant.MESSAGE_ERROR_UPDATE);
        }
    }

    public UI_UploadFileController getUiUploadFile() {
        return uiUploadFile;
    }

    public void setUiUploadFile(UI_UploadFileController uiUploadFile) {
        this.uiUploadFile = uiUploadFile;
    }

    public UIComponent getUicMatKhau() {
        return uicMatKhau;
    }

    public void setUicMatKhau(UIComponent uicMatKhau) {
        this.uicMatKhau = uicMatKhau;
    }

    public UIComponent getUicReMatKhau() {
        return uicReMatKhau;
    }

    public void setUicReMatKhau(UIComponent uicReMatKhau) {
        this.uicReMatKhau = uicReMatKhau;
    }

    public TaiKhoanModel getObjTaiKhoan() {
        return objTaiKhoan;
    }

    public void setObjTaiKhoan(TaiKhoanModel objTaiKhoan) {
        this.objTaiKhoan = objTaiKhoan;
    }

    public UIComponent getUicResultThongTin() {
        return uicResultThongTin;
    }

    public void setUicResultThongTin(UIComponent uicResultThongTin) {
        this.uicResultThongTin = uicResultThongTin;
    }

    public int getViewMode() {
        return viewMode;
    }

    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;
    }
    
}
