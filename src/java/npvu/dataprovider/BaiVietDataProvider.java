/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.dataprovider;

import java.io.Serializable;
import java.math.BigInteger;
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
    
    public DanhMucBaiVietModel getDanhMucBaiVietByID(int danhMucID){
        Session session = HibernateUtil.currentSession();
        DanhMucBaiVietModel objDMBaiViet = new DanhMucBaiVietModel();
        try {
            session.beginTransaction();
            objDMBaiViet = (DanhMucBaiVietModel) session.createSQLQuery("SELECT *"
                    + " FROM danhmuc_baiviet"                   
                    + " WHERE danhmuc_baiviet_id = " + danhMucID)
                    .addEntity(DanhMucBaiVietModel.class)
                    .uniqueResult();
            session.getTransaction().commit();
	} catch (Exception e) {
            log.error("Lỗi get danh mục bài viết ID: "+danhMucID+" {}", e);
	} finally {
            session.close();
	}
        return objDMBaiViet;
    }
    
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
    
    public boolean deleteDanhMucBaiViet(DanhMucBaiVietModel objDMBaiViet){
        Session session = HibernateUtil.currentSession();
        try {
            session.beginTransaction();
            session.delete(objDMBaiViet);
            session.getTransaction().commit();
	} catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Lỗi delete danh mục bài viết <<" + objDMBaiViet.getTen() + ">> {}", e);
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
	} catch(ClassCastException cce){
            BigInteger intResult = (BigInteger) session.createSQLQuery("SELECT EXISTS("
                    + "SELECT * FROM danhmuc_baiviet WHERE danhmuc_baiviet_ten = '"+tenDanhMuc+"')")
                    .uniqueResult();
            if(intResult.intValue() == 0){
                result = false;
            }
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
    
    public BaiVietModel getBaiVietByID(long baiVietID){
        Session session = HibernateUtil.currentSession();
        BaiVietModel objBaiViet = new BaiVietModel();        
        try {
            session.beginTransaction();
            objBaiViet = (BaiVietModel) session.createSQLQuery(" SELECT * "
                    + " FROM baiviet "
                    + " WHERE baiviet_id = "+baiVietID)
                    .addEntity(BaiVietModel.class)
                    .uniqueResult();
            session.getTransaction().commit();
	} catch (Exception e) {
            log.error("Lỗi get bài viết ID: "+baiVietID+" {}", e);
	} finally {
            session.close();
	}
        return objBaiViet;
    }
    
//    public List<Map> getDanhSachBaiVietByDanhMucID(int danhMucID){
//        Session session = HibernateUtil.currentSession();
//        List<Map> mapBaiViet = new ArrayList<>();        
//        try {
//            session.beginTransaction();
//            mapBaiViet = session.createSQLQuery(" SELECT * "
//                    + " FROM baiviet bv "
//                    + " LEFT JOIN taptin tt "
//                    + " ON tt.taptin_id = bv.taptin_id "
//                    + " WHERE bv.danhmuc_baiviet_id = "+danhMucID
//                    + " AND baiviet_xuatban = 1 "
//                    + " ORDER BY baiviet_id DESC ")
//                    .setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP)
//                    .list();
//            session.getTransaction().commit();
//	} catch (Exception e) {
//            log.error("Lỗi get list map bài viết của danh mục ID: "+danhMucID+" {}", e);
//	} finally {
//            session.close();
//	}
//        return mapBaiViet;
//    }
    
    public List<BaiVietModel> getDanhSachBaiVietByDanhMucID(int danhMucID){
        Session session = HibernateUtil.currentSession();
        List<BaiVietModel> dsBaiViet = new ArrayList<>();        
        try {
            session.beginTransaction();
            dsBaiViet = session.createSQLQuery(" SELECT * "
                    + " FROM baiviet bv "
                    + " WHERE bv.danhmuc_baiviet_id = "+danhMucID
                    + " AND baiviet_xuatban = 1 "
                    + " ORDER BY baiviet_id DESC ")
                    .addEntity(BaiVietModel.class)
                    .list();
            session.getTransaction().commit();
	} catch (Exception e) {
            log.error("Lỗi get list bài viết của danh mục ID: "+danhMucID+" {}", e);
	} finally {
            session.close();
	}
        return dsBaiViet;
    }
    
    public BaiVietModel getBaiVietMoiNhatByDanhMucID(int danhMucID){
        Session session = HibernateUtil.currentSession();
        BaiVietModel objBaiViet = new BaiVietModel();        
        try {
            session.beginTransaction();
            objBaiViet = (BaiVietModel) session.createSQLQuery(" SELECT * "
                    + " FROM baiviet bv "
                    + " LEFT JOIN danhmuc_baiviet dmbv "
                    + " ON dmbv.danhmuc_baiviet_id = bv.danhmuc_baiviet_id "
                    + " WHERE dmbv.danhmuc_baiviet_id = "+danhMucID
                    + " AND baiviet_xuatban = 1 "
                    + " ORDER BY baiviet_id DESC LIMIT 1")
                    .addEntity(BaiVietModel.class)
                    .uniqueResult();
            session.getTransaction().commit();
	} catch (Exception e) {
            log.error("Lỗi get bài viết mới nhất của danh mục ID: "+danhMucID+" {}", e);
	} finally {
            session.close();
	}
        return objBaiViet;
    }
    
    public BaiVietModel getBaiVietHienThiTrangChu(){
        Session session = HibernateUtil.currentSession();
        BaiVietModel objBaiViet = new BaiVietModel();        
        try {
            session.beginTransaction();
            objBaiViet = (BaiVietModel) session.createSQLQuery(" SELECT * "
                    + " FROM baiviet bv "
                    + " LEFT JOIN danhmuc_baiviet dmbv "
                    + " ON dmbv.danhmuc_baiviet_id = bv.danhmuc_baiviet_id "
                    + " WHERE baiviet_hienthi_trangchu = 1"
                    + " AND baiviet_xuatban = 1 ")
                    .addEntity(BaiVietModel.class)
                    .uniqueResult();
            session.getTransaction().commit();
	} catch (Exception e) {
            log.error("Lỗi get bài viết hiển thị trang chủ: {}", e);
	} finally {
            session.close();
	}
        return objBaiViet;
    }
    
    public List<BaiVietModel> getBaiVietLienQuan(BaiVietModel baiViet){
        Session session = HibernateUtil.currentSession();
        List<BaiVietModel> dsBaiViet = new ArrayList<>();        
        try {
            session.beginTransaction();
            dsBaiViet = session.createSQLQuery(" SELECT * "
                    + " FROM baiviet"                   
                    + " WHERE danhmuc_baiviet_id = "+baiViet.getDanhmuc_baiviet_id()
                    + " AND baiviet_id != "+baiViet.getId()
                    + " AND baiviet_xuatban = 1 "
                    + " ORDER BY baiviet_id DESC")
                    .addEntity(BaiVietModel.class)
                    .list();
            session.getTransaction().commit();
	} catch (Exception e) {
            log.error("Lỗi get bài viết liên quan : "+baiViet.getTieuDe()+" {}", e);
	} finally {
            session.close();
	}
        return dsBaiViet;
    }
    
    public boolean updateBaiViet(BaiVietModel objBaiViet){
        Session session = HibernateUtil.currentSession();       
        try {
            session.beginTransaction();
             if(objBaiViet.getHienThiTrangChu() == 1){
                session.createSQLQuery("UPDATE baiviet SET baiviet_hienthi_trangchu = 0").executeUpdate();
            }
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
    
    public boolean deleteBaiViet(BaiVietModel objBaiViet){
        Session session = HibernateUtil.currentSession();
        try {
            session.beginTransaction();
            session.delete(objBaiViet);
            session.getTransaction().commit();
	} catch (Exception e) {
            session.getTransaction().rollback();
            log.error("Lỗi delete bài viết <<" + objBaiViet.getTieuDe() + ">> {}", e);
            return false;
	} finally {
            session.close();
	}
        return true;
    }       
}
