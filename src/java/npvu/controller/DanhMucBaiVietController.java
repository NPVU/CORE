/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import npvu.constant.Constant;
import npvu.dataprovider.BaiVietDataProvider;
import npvu.model.DanhMucBaiVietModel;
import npvu.util.RoleUtils;
import npvu.util.ShowGrowlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author npvu
 */
@ManagedBean (name="DanhMucBaiVietController")
@ViewScoped
public class DanhMucBaiVietController implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(DanhMucBaiVietController.class);
    
    private UIComponent uicTenDanhMuc;
    
    private UIComponent uicTrangThai;
    
    private List<DanhMucBaiVietModel> dsDMBaiViet   = new ArrayList<>();;
    
    private DanhMucBaiVietModel objDMBaiViet;
    
    private final BaiVietDataProvider bvProvider    = new BaiVietDataProvider();
    
    private final ShowGrowlUtils showGrowl          = new ShowGrowlUtils();
    
    private final RoleUtils roleUtils               = new RoleUtils();
    
    private int viewMode;                                             // 0 là form danh sách, 1 là form cập nhật
    
    private boolean editMode;
    
    private String tenDanhMucFilter;
    
    private Boolean trangThaiFilter;
    
    public DanhMucBaiVietController() {
        if(roleUtils.checkRole(Constant.ROLE_ADMIN_BAIVIET)){
            actionGetDanhSachDanhMucBaiViet();
            viewMode = 0;
        }  else {
            viewMode = Constant.CODE_ERROR_401;
        }
    }
    
    private void actionGetDanhSachDanhMucBaiViet(){
        dsDMBaiViet = bvProvider.getDanhSachDanhMucBaiViet(null, null);
    }

    public void actionGetDanhSachDanhMucBaiVietFilter(){
        dsDMBaiViet = bvProvider.getDanhSachDanhMucBaiViet(tenDanhMucFilter, trangThaiFilter);
    }
    
    public void preActionThemDanhMuc(){
        objDMBaiViet = new DanhMucBaiVietModel();
        editMode = false;
        viewMode = 1;
    }
    
    public void actionChangeViewMode(int mode){
        viewMode = mode;
    }
    
    /* Getter && Setter */
    
    public UIComponent getUicTenDanhMuc() {
        return uicTenDanhMuc;
    }

    public void setUicTenDanhMuc(UIComponent uicTenDanhMuc) {
        this.uicTenDanhMuc = uicTenDanhMuc;
    }

    public UIComponent getUicTrangThai() {
        return uicTrangThai;
    }

    public void setUicTrangThai(UIComponent uicTrangThai) {
        this.uicTrangThai = uicTrangThai;
    }

    public int getViewMode() {
        return viewMode;
    }

    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;
    }

    public String getTenDanhMucFilter() {
        return tenDanhMucFilter;
    }

    public void setTenDanhMucFilter(String tenDanhMucFilter) {
        this.tenDanhMucFilter = tenDanhMucFilter;
    }

    public List<DanhMucBaiVietModel> getDsDMBaiViet() {
        return dsDMBaiViet;
    }

    public void setDsDMBaiViet(List<DanhMucBaiVietModel> dsDMBaiViet) {
        this.dsDMBaiViet = dsDMBaiViet;
    }

    public Boolean getTrangThaiFilter() {
        return trangThaiFilter;
    }

    public void setTrangThaiFilter(Boolean trangThaiFilter) {
        this.trangThaiFilter = trangThaiFilter;
    }

    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }

    public DanhMucBaiVietModel getObjDMBaiViet() {
        return objDMBaiViet;
    }

    public void setObjDMBaiViet(DanhMucBaiVietModel objDMBaiViet) {
        this.objDMBaiViet = objDMBaiViet;
    }
    
    
}
