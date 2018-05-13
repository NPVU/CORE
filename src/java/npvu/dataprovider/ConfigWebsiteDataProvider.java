/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.dataprovider;

import java.io.Serializable;
import npvu.model.ConfigWebsiteModel;
import npvu.util.HibernateUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author npvu
 */
public class ConfigWebsiteDataProvider implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(ConfigWebsiteDataProvider.class);
    
    public ConfigWebsiteModel getConfigWebsite(){
        Session session = HibernateUtil.currentSession();
        ConfigWebsiteModel confWebsite = new ConfigWebsiteModel();
        try {
            session.beginTransaction();
            confWebsite = (ConfigWebsiteModel) session.createSQLQuery("SELECT * FROM config_website")
                    .addEntity(ConfigWebsiteModel.class)
                    .uniqueResult();
            session.getTransaction().commit();
	} catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Lỗi get Cấu hình website {}", e);
            confWebsite.setWebsiteName("Chưa có cấu hình thông tin website");
	} finally {
            session.close();
	}
        return confWebsite;
    }
    
    
    public boolean updateConfigWebsite(ConfigWebsiteModel confWebsite){
        Session session = HibernateUtil.currentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(confWebsite);                        
            session.getTransaction().commit();
	} catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Lỗi update Cấu hình website {}", e);
            return false;
	} finally {
            session.close();
	}
        return true;
    }
}
