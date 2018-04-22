/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.dataprovider;

import java.io.Serializable;
import npvu.model.TapTinModel;
import npvu.util.HibernateUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author npvu
 */
public class TapTinDataProvider implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(TapTinDataProvider.class); 
    
    
    public boolean updateTapTin(TapTinModel objTapTin){
        Session session = HibernateUtil.currentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(objTapTin);
            session.getTransaction().commit();
	} catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Lỗi update tập tin <<" + objTapTin.getTen() + ">> {}", e);
            return false;
	} finally {
            session.close();
	}
        return true;
    }
    
    public TapTinModel getTapTin(long tapTinID){
        Session session = HibernateUtil.currentSession();
        TapTinModel objTapTin = null;
        try {
            session.beginTransaction();
            objTapTin = (TapTinModel) session.createQuery("FROM TapTinModel WHERE id ="+tapTinID)
                    .uniqueResult();
            session.getTransaction().commit();
	} catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Lỗi lấy tập tin <<" + tapTinID + ">> {}", e);
	} finally {
            session.close();
	}
        return objTapTin;
    }
}
