/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import npvu.constant.Constant;
import npvu.constant.FileConstant;
import npvu.constant.MessageConstant;
import npvu.dataprovider.ConfigWebsiteDataProvider;
import npvu.dataprovider.TapTinDataProvider;
import npvu.model.ConfigWebsiteModel;
import npvu.model.TapTinModel;
import npvu.session.SessionBean;
import npvu.util.RoleUtils;
import npvu.util.ShowGrowlUtils;
import npvu.util.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author npvu
 */
@ManagedBean(name = "ConfigWebsite")
@ViewScoped
public class ConfigWebsite implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(ConfigWebsite.class);
    
    @ManagedProperty(value="#{UI_UploadFileController}")
    private UI_UploadFileController uiUploadFile;
    
    private final ShowGrowlUtils showGrowl          = new ShowGrowlUtils();
    
    private final RoleUtils roleUtils               = new RoleUtils();
    
    private final ValidateUtils valiUtils           = new ValidateUtils();
    
    private ConfigWebsiteModel confWebsite          = new ConfigWebsiteModel();
    
    private final ConfigWebsiteDataProvider confWebProvider = new ConfigWebsiteDataProvider();
    
    private final TapTinDataProvider ttProvider     = new TapTinDataProvider();
    
    private TapTinModel objBanner                   = new TapTinModel();
    
    private int viewMode;
    /**
     * Creates a new instance of CauHinhChungController
     */
    public ConfigWebsite() {
        if(roleUtils.checkRole(Constant.ROLE_ADMIN_HETHONG)){  
            actionGetConfigWebsite();
            viewMode = 0;
        }  else {
            viewMode = Constant.CODE_ERROR_500;
        }
    }
    
    private void actionGetConfigWebsite(){
        confWebsite = confWebProvider.getConfigWebsite();
        if(confWebsite != null){
            objBanner   = ttProvider.getTapTin(confWebsite.getTapTinID());
        } else {
            confWebsite = new ConfigWebsiteModel();
        }    
    }
    
    public void actionUpdateConfigWebsite(){
        long tapTinID;
        if(SessionBean.statusUpload != null && SessionBean.statusUpload == true){
            tapTinID = uiUploadFile.actionUpdateTapTin(FileConstant.PATH_UPLOAD_IMAGE);
            confWebsite.setTapTinID(tapTinID);
        }
        if(confWebProvider.updateConfigWebsite(confWebsite)){
            showGrowl.showMessageSuccess(MessageConstant.MESSAGE_SUCCESS_UPDATE);
        } else {
            showGrowl.showMessageFatal(MessageConstant.MESSAGE_ERROR_UPDATE);
        }
        actionGetConfigWebsite();
    }

    public int getViewMode() {
        return viewMode;
    }

    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;
    }

    public ConfigWebsiteModel getConfWebsite() {
        return confWebsite;
    }

    public void setConfWebsite(ConfigWebsiteModel confWebsite) {
        this.confWebsite = confWebsite;
    }

    public UI_UploadFileController getUiUploadFile() {
        return uiUploadFile;
    }

    public void setUiUploadFile(UI_UploadFileController uiUploadFile) {
        this.uiUploadFile = uiUploadFile;
    }

    public TapTinModel getObjBanner() {
        return objBanner;
    }

    public void setObjBanner(TapTinModel objBanner) {
        this.objBanner = objBanner;
    }
    
}
