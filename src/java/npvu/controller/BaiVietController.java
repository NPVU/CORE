/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import npvu.constant.Constant;
import npvu.constant.FileConstant;
import npvu.constant.MessageConstant;
import npvu.dataprovider.BaiVietDataProvider;
import npvu.model.BaiVietModel;
import npvu.session.Session;
import npvu.util.DateUtils;
import npvu.util.RoleUtils;
import npvu.util.ShowGrowlUtils;
import npvu.util.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author npvu
 */
@ManagedBean (name = "BaiVietController")
@ViewScoped
public class BaiVietController implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(BaiVietController.class);
    
    @ManagedProperty(value="#{UI_UploadFileController}")
    private UI_UploadFileController uiUploadFile;
    
    private UIComponent uicTieuDe;
    
    private final BaiVietDataProvider bvProvider    = new BaiVietDataProvider();
    
    private final ShowGrowlUtils showGrowl          = new ShowGrowlUtils();
    
    private final RoleUtils roleUtils               = new RoleUtils();
    
    private final ValidateUtils valiUtils           = new ValidateUtils();
    
    private List<BaiVietModel> dsBaiViet            = new ArrayList();
    
    private BaiVietModel objBaiViet;
    
    private int viewMode;                                             // 0 là form danh sách, 1 là form cập nhật
    
    private boolean editMode;
    
    private int dmIDFilter;
    
    private String tieuDeFilter;
    
    private Date taoTuNgayFilter;
    
    private Date taoDenNgayFilter;
    
    private Date xuatBanTuNgayFilter;
    
    private Date xuatBanDenNgayFilter;
    
    public BaiVietController() {
        if(roleUtils.checkRole(Constant.ROLE_ADMIN_BAIVIET)){
            actionGetDanhSachBaiViet();
            viewMode = 0;
        }  else {
            viewMode = Constant.CODE_ERROR_500;
        }
    }
    
    private void actionGetDanhSachBaiViet(){
        dsBaiViet = bvProvider.getDanhSachBaiViet(null, null, null, null);
    }
    
    
    
    public void preActionThemBaiViet(){
        objBaiViet = new BaiVietModel();
        viewMode = 1;
        editMode = false;
    }
    
    public void actionUpdateBaiViet(){
        if(!editMode){ // Thêm mới
            objBaiViet.setNgayTao(DateUtils.getCurrentDate());
            if(objBaiViet.isXuatBan()){
                objBaiViet.setNgayXuatBan(DateUtils.getCurrentDate());
            }
        } else { // Chỉnh sửa
            if(objBaiViet.isXuatBan() && objBaiViet.getNgayXuatBan() == null){
                objBaiViet.setNgayXuatBan(DateUtils.getCurrentDate());
            }
        }
        
        long tapTinID = 0;
        if(Session.statusUpload != null && Session.statusUpload == true){
                tapTinID = uiUploadFile.actionUpdateTapTin(FileConstant.PATH_UPLOAD_IMAGE);
                objBaiViet.setTapTinID(tapTinID);
        }
        if(bvProvider.updateBaiViet(objBaiViet)){
            showGrowl.showMessageSuccess(MessageConstant.MESSAGE_SUCCESS_UPDATE);
        } else{
            showGrowl.showMessageFatal(MessageConstant.MESSAGE_ERROR_UPDATE);
        }
        UI_UploadFileController.resetValueFileToSession();
        actionGetDanhSachBaiViet();
        editMode = false;
        viewMode = 0;        
    }

    public int getViewMode() {
        return viewMode;
    }

    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public List<BaiVietModel> getDsBaiViet() {
        return dsBaiViet;
    }

    public void setDsBaiViet(List<BaiVietModel> dsBaiViet) {
        this.dsBaiViet = dsBaiViet;
    }

    public int getDmIDFilter() {
        return dmIDFilter;
    }

    public void setDmIDFilter(int dmIDFilter) {
        this.dmIDFilter = dmIDFilter;
    }

    public String getTieuDeFilter() {
        return tieuDeFilter;
    }

    public void setTieuDeFilter(String tieuDeFilter) {
        this.tieuDeFilter = tieuDeFilter;
    }

    public Date getTaoTuNgayFilter() {
        return taoTuNgayFilter;
    }

    public void setTaoTuNgayFilter(Date taoTuNgayFilter) {
        this.taoTuNgayFilter = taoTuNgayFilter;
    }

    public Date getTaoDenNgayFilter() {
        return taoDenNgayFilter;
    }

    public void setTaoDenNgayFilter(Date taoDenNgayFilter) {
        this.taoDenNgayFilter = taoDenNgayFilter;
    }

    public Date getXuatBanTuNgayFilter() {
        return xuatBanTuNgayFilter;
    }

    public void setXuatBanTuNgayFilter(Date xuatBanTuNgayFilter) {
        this.xuatBanTuNgayFilter = xuatBanTuNgayFilter;
    }

    public Date getXuatBanDenNgayFilter() {
        return xuatBanDenNgayFilter;
    }

    public void setXuatBanDenNgayFilter(Date xuatBanDenNgayFilter) {
        this.xuatBanDenNgayFilter = xuatBanDenNgayFilter;
    }

    public BaiVietModel getObjBaiViet() {
        return objBaiViet;
    }

    public void setObjBaiViet(BaiVietModel objBaiViet) {
        this.objBaiViet = objBaiViet;
    }

    public UIComponent getUicTieuDe() {
        return uicTieuDe;
    }

    public void setUicTieuDe(UIComponent uicTieuDe) {
        this.uicTieuDe = uicTieuDe;
    }

    public UI_UploadFileController getUiUploadFile() {
        return uiUploadFile;
    }

    public void setUiUploadFile(UI_UploadFileController uiUploadFile) {
        this.uiUploadFile = uiUploadFile;
    }
    
}
