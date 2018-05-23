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
import npvu.dataprovider.BaiVietDataProvider;
import npvu.dataprovider.ConfigWebsiteDataProvider;
import npvu.dataprovider.TapTinDataProvider;
import npvu.model.BaiVietModel;
import npvu.model.ConfigWebsiteModel;
import npvu.model.DanhMucBaiVietModel;
import npvu.model.TapTinModel;
import npvu.session.SessionBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author b1203
 */
@ManagedBean(name = "ThongTinController")
@ViewScoped
public class ThongTinController implements Serializable{
    
    private static final Logger log = LoggerFactory.getLogger(ThongTinController.class);

    private List<DanhMucBaiVietModel> dsDMBaiViet   = new ArrayList<>();
    
    private List<BaiVietModel> dsBaiViet   = new ArrayList<>();
    
    private final ConfigWebsiteDataProvider confWebProvider = new ConfigWebsiteDataProvider();
    
    private final BaiVietDataProvider bvProvider    = new BaiVietDataProvider();
    
    private final TapTinDataProvider ttProvider     = new TapTinDataProvider();
            
    private ConfigWebsiteModel confWebsite;
    
    private TapTinModel tapTinBanner;
    
    private BaiVietModel objBaiViet;
        
    /**
     * Creates a new instance of ThongTinController
     */
    public ThongTinController() {
        actionGetDanhSachDanhMucBaiViet();
        actionGetBaiVietHienThiTrangChu();
        if(objBaiViet != null){
            actionGetBaiVietLienQuan(objBaiViet);
        }                
        confWebsite = confWebProvider.getConfigWebsite();
        tapTinBanner = ttProvider.getTapTin(confWebsite.getTapTinID());
    }

    private void actionGetDanhSachDanhMucBaiViet(){
        dsDMBaiViet = bvProvider.getDanhSachDanhMucBaiViet(null, -1);
    }
    
    private void actionGetBaiVietHienThiTrangChu(){
        objBaiViet = bvProvider.getBaiVietHienThiTrangChu();
    }
    
    private void actionGetBaiVietLienQuan(BaiVietModel baiViet){
        dsBaiViet  = bvProvider.getBaiVietLienQuan(baiViet);
    }       
    
    public List<BaiVietModel> actionGetDanhSachBaiVietByDanhMucID(int danhMucID){
        return bvProvider.getDanhSachBaiVietByDanhMucID(danhMucID);
    }
    
    public void actionViewBaiViet(int baiVietID){
        objBaiViet = bvProvider.getBaiVietByID(baiVietID);
        actionGetBaiVietLienQuan(objBaiViet);
    }
    
    /* Getter && Setter */
    
    public List<DanhMucBaiVietModel> getDsDMBaiViet() {
        return dsDMBaiViet;
    }

    public void setDsDMBaiViet(List<DanhMucBaiVietModel> dsDMBaiViet) {
        this.dsDMBaiViet = dsDMBaiViet;
    }

    public BaiVietModel getObjBaiViet() {
        return objBaiViet;
    }

    public void setObjBaiViet(BaiVietModel objBaiViet) {
        this.objBaiViet = objBaiViet;
    }

    public List<BaiVietModel> getDsBaiViet() {
        return dsBaiViet;
    }

    public void setDsBaiViet(List<BaiVietModel> dsBaiViet) {
        this.dsBaiViet = dsBaiViet;
    }

    public ConfigWebsiteModel getConfWebsite() {
        return confWebsite;
    }

    public void setConfWebsite(ConfigWebsiteModel confWebsite) {
        this.confWebsite = confWebsite;
    }

    public TapTinModel getTapTinBanner() {
        return tapTinBanner;
    }

    public void setTapTinBanner(TapTinModel tapTinBanner) {
        this.tapTinBanner = tapTinBanner;
    }
    
}
