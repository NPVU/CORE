/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.dataprovider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import npvu.model.BaiVietModel;
import npvu.model.DanhMucBaiVietModel;
import npvu.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author npvu
 */
public class BaiVietDataProvider implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(BaiVietDataProvider.class); 
    
    public List<DanhMucBaiVietModel> getDanhSachDanhMucBaiViet(String tenDanhMucFilter, int trangThaiFilter){
        Session session = HibernateUtil.currentSession();
        List<DanhMucBaiVietModel> dsDMBaiViet = new ArrayList();
        String where = "";
        if(tenDanhMucFilter != null){
            where += " AND danhmuc_baiviet_ten like '%"+tenDanhMucFilter+"%' ";
        }
        if(trangThaiFilter == 1){
            where += " AND danhmuc_baiviet_trangthai = true";
        }else if(trangThaiFilter == 0){
            where += " AND danhmuc_baiviet_trangthai = false";
        }
        try {
            session.beginTransaction();
            dsDMBaiViet = session.createSQLQuery("SELECT *"
                    + " FROM danhmuc_baiviet"                   
                    + " WHERE 1 = 1 " + where).addEntity(DanhMucBaiVietModel.class).list();
            session.getTransaction().commit();
	} catch (Exception e) {
            log.error("Lỗi get danh sách danh mục bài viết {}", e);
	} finally {
            session.close();
	}
        return dsDMBaiViet;
    }
    
    public boolean updateDanhMucBaiViet(DanhMucBaiVietModel objDanhMuc){
        Session session = HibernateUtil.currentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(objDanhMuc);           
            session.getTransaction().commit();
	} catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Lỗi update danh mục bài viết <<" + objDanhMuc.getTen() + ">> {}", e);
            return false;
	} finally {
            session.close();
	}
        return true;
    }
    
    public boolean checkExistTenDanhMuc(String tenDanhMuc){
        Session session = HibernateUtil.currentSession();
        boolean result = true;
        try {
            session.beginTransaction();
            result = (boolean) session.createSQLQuery("SELECT EXISTS("
                    + "SELECT * FROM danhmuc_baiviet WHERE danhmuc_baiviet_ten = '"+tenDanhMuc+"')")
                    .uniqueResult();
            session.getTransaction().commit();
	} catch (Exception e) {
            log.error("Lỗi Kiểm tra sự tồn tại danh mục <<" + tenDanhMuc + ">> {}", e);
            return false;
	} finally {
            session.close();
	}
        return result;
    }
    
    
    public List<Map> getDanhSachBaiViet(int dmIDFilter, String tieuDeFilter, 
            int xuatBanFilter, Date taoTuNgayFilter, Date taoDenNgayFilter){
        Session session = HibernateUtil.currentSession();
        List<Map> dsBaiViet = new ArrayList();
        String where = "";
        if(dmIDFilter != 0){
            where += " AND bv.danhmuc_baiviet_id = "+dmIDFilter;
        }
        if(tieuDeFilter != null && !tieuDeFilter.isEmpty()){
            where += " AND bv.baiviet_tieude like '%"+tieuDeFilter+"%'";
        }
        if(xuatBanFilter == 1){
            where += " AND bv.baiviet_xuatban = true";
        } else if(xuatBanFilter == 0){
            where += " AND bv.baiviet_xuatban = false";
        }
        try {
            session.beginTransaction();
            dsBaiViet = session.createSQLQuery("SELECT bv.*, dmbv.danhmuc_baiviet_ten,"
                    + " tk.taikhoan_tenhienthi "
                    + " FROM baiviet bv "
                    + " LEFT JOIN taikhoan tk "
                    + " ON tk.taikhoan_id = bv.taikhoan_id "
                    + " LEFT JOIN danhmuc_baiviet dmbv "
                    + " ON dmbv.danhmuc_baiviet_id = bv.danhmuc_baiviet_id "                   
                    + " WHERE 1 = 1 " + where)
                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
                    .list();
            session.getTransaction().commit();
	} catch (Exception e) {
            log.error("Lỗi get danh sách bài viết {}", e);
	} finally {
            session.close();
	}
        return dsBaiViet;
    }
    
    public boolean updateBaiViet(BaiVietModel objBaiViet){
        Session session = HibernateUtil.currentSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(objBaiViet);           
            session.getTransaction().commit();
	} catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Lỗi update bài viết <<" + objBaiViet.getTieuDe() + ">> {}", e);
            return false;
	} finally {
            session.close();
	}
        return true;
    }
}
