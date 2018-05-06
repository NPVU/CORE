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
import npvu.constant.FileConstant;
import npvu.constant.MessageConstant;
import npvu.dataprovider.TaiKhoanDataProvider;
import npvu.dataprovider.TapTinDataProvider;
import npvu.model.TaiKhoanModel;
import npvu.model.TapTinModel;
import npvu.session.SessionBean;
import npvu.util.EncryptionUtils;
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
    
    private final TapTinDataProvider ttProvider = new TapTinDataProvider();
    
    private final ShowGrowlUtils showGrowl = new ShowGrowlUtils();
    
    private final RoleUtils roleUtils               = new RoleUtils();
    
    private UIComponent uicTenHienThi;
    
    private UIComponent uicMatKhau;
    
    private UIComponent uicReMatKhau;
    
    private TaiKhoanModel objTaiKhoan;
    
    private TapTinModel objTapTin;
    
    private String matKhau;
    
    private String reMatKhau;
    
    private int viewMode = 0;
    
    private int tabIndex = 0;
    
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
            objTapTin = ttProvider.getTapTin(objTaiKhoan.getTapTinID());
        }
    }
    
    public void actionUpdateThongTinTaiKhoan(){
        tabIndex = 0;
        matKhau = reMatKhau = null;
        if(vaildFormThongTinTaiKhoan()){
            long tapTinID = 0;
            if(SessionBean.statusUpload != null && SessionBean.statusUpload == true){
                tapTinID = uiUploadFile.actionUpdateTapTin(FileConstant.PATH_UPLOAD_AVATAR);
                objTaiKhoan.setTapTinID(tapTinID);
            }
            if(tkProvider.updateTaiKhoan(objTaiKhoan)){
                Login lg = new Login();
                lg.refreshTaiKhoan(objTaiKhoan.getTenDangNhap());
                if(tapTinID != 0){
                    objTapTin = ttProvider.getTapTin(tapTinID);
                }
                showGrowl.showMessageSuccess(MessageConstant.MESSAGE_SUCCESS_UPDATE);
            } else {
                showGrowl.showMessageFatal(MessageConstant.MESSAGE_ERROR_UPDATE);
            }
        }
    }
    
    public boolean vaildFormThongTinTaiKhoan(){
        boolean vaild = true;
        if(objTaiKhoan.getTenHienThi() == null || objTaiKhoan.getTenHienThi().isEmpty()){
            showGrowl.showMessageError("Vui lòng nhập tên hiển thị !", uicTenHienThi);
            vaild = false;
        }
        return vaild;
    }
    
    public void actionUpdateMatKhau(){
        tabIndex = 1;
        if(vaildFormDoiMatKhau()){
            objTaiKhoan.setMatKhau(EncryptionUtils.encryptMatKhau(matKhau));
            if(tkProvider.updateTaiKhoan(objTaiKhoan)){
                showGrowl.showMessageSuccess(MessageConstant.MESSAGE_SUCCESS_UPDATE);
            } else {
                showGrowl.showMessageFatal(MessageConstant.MESSAGE_ERROR_UPDATE);
            }
        }
    }
    
    public boolean vaildFormDoiMatKhau(){
        boolean vaild = true;
        if(matKhau == null || matKhau.isEmpty()){
            showGrowl.showMessageError("Vui lòng nhập mật khẩu mới !", uicMatKhau);
            vaild = false;
        } else if(matKhau.length() < Constant.MIN_MATKHAU || matKhau.length() > Constant.MAX_MATKHAU){
            showGrowl.showMessageError("Mật khẩu có độ dài từ "+Constant.MIN_MATKHAU+" đến "+Constant.MAX_MATKHAU+" !", uicMatKhau);
            vaild = false;
        }
        if(reMatKhau == null || reMatKhau.isEmpty()){
            showGrowl.showMessageError("Vui lòng nhập lại mật khẩu mới !", uicReMatKhau);
            vaild = false;
        } else if(reMatKhau.length() < Constant.MIN_MATKHAU || reMatKhau.length() > Constant.MAX_MATKHAU){
            showGrowl.showMessageError("Mật khẩu có độ dài từ "+Constant.MIN_MATKHAU+" đến "+Constant.MAX_MATKHAU+" !", uicReMatKhau);
            vaild = false;
        }
        if(vaild){
            if(!matKhau.equals(reMatKhau)){
                showGrowl.showMessageError(MessageConstant.MESSAGE_PASSWORD_NOT_SAME, uicMatKhau);
                showGrowl.showMessageError(MessageConstant.MESSAGE_PASSWORD_NOT_SAME, uicReMatKhau);
                matKhau = reMatKhau = null;
                vaild = false;
            }
        }
        return vaild;
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

    public int getViewMode() {
        return viewMode;
    }

    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getReMatKhau() {
        return reMatKhau;
    }

    public void setReMatKhau(String reMatKhau) {
        this.reMatKhau = reMatKhau;
    }

    public UIComponent getUicTenHienThi() {
        return uicTenHienThi;
    }

    public void setUicTenHienThi(UIComponent uicTenHienThi) {
        this.uicTenHienThi = uicTenHienThi;
    }

    public int getTabIndex() {
        return tabIndex;
    }

    public void setTabIndex(int tabIndex) {
        this.tabIndex = tabIndex;
    }

    public TapTinModel getObjTapTin() {
        return objTapTin;
    }

    public void setObjTapTin(TapTinModel objTapTin) {
        this.objTapTin = objTapTin;
    }
    
}
