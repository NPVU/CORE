/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import npvu.constant.Constant;
import npvu.util.RoleUtils;
import npvu.util.ShowGrowlUtils;
import npvu.util.ValidateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author npvu
 */
@ManagedBean(name = "CauHinhChungController")
@ViewScoped
public class CauHinhChungController implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(CauHinhChungController.class);
    
    private final ShowGrowlUtils showGrowl          = new ShowGrowlUtils();
    
    private final RoleUtils roleUtils               = new RoleUtils();
    
    private final ValidateUtils valiUtils           = new ValidateUtils();
    
    private int viewMode;
    /**
     * Creates a new instance of CauHinhChungController
     */
    public CauHinhChungController() {
        if(roleUtils.checkRole(Constant.ROLE_ADMIN_HETHONG)){            
            viewMode = 0;
        }  else {
            viewMode = Constant.CODE_ERROR_500;
        }
    }

    public int getViewMode() {
        return viewMode;
    }

    public void setViewMode(int viewMode) {
        this.viewMode = viewMode;
    }
    
}
