/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.dataprovider;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import npvu.model.DanhMucBaiVietModel;
import npvu.util.HibernateUtil;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author npvu
 */
public class BaiVietDataProvider implements Serializable{
    private static final Logger log = LoggerFactory.getLogger(BaiVietDataProvider.class); 
    
    public List<DanhMucBaiVietModel> getDanhSachDanhMucBaiViet(String tenDanhMucFilter, Boolean trangThaiFilter){
        Session session = HibernateUtil.currentSession();
        List<DanhMucBaiVietModel> dsDMBaiViet = new ArrayList();
        String where = "";
        if(tenDanhMucFilter != null){
            where += " AND danhmuc_baiviet_ten like '%"+tenDanhMucFilter+"%' ";
        }
        if(trangThaiFilter != null){
            where += " AND danhmuc_baiviet_trangthai = "+trangThaiFilter;
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
}
