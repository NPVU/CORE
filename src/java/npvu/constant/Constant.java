/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.constant;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author npvu
 */
@ManagedBean (name="Constant")
public class Constant {
    
    /* Hằng số cho java bean */
    public static final int MIN_TENDANGNHAP                = 6;
    public static final int MAX_TENDANGNHAP                = 32;
    public static final int MIN_TENDANHMUC                 = 3;
    public static final int MAX_TENDANHMUC                 = 64;
    
    public static final int MIN_MATKHAU                    = 8;
    public static final int MAX_MATKHAU                    = 32;
    
    public static final int ROLE_DEFAULT                   = 100;
    public static final int ROLE_GUEST                     = 100;
    public static final int ROLE_FULL                      = 1;
    public static final int ROLE_ADMIN_TAIKHOAN            = 2;
    public static final int ROLE_ADMIN_BAIVIET             = 3;
    public static final int ROLE_ADMIN_HETHONG             = 4;
    
    public static final String URL_DANGNHAP                = "/dang-nhap/";     /* Chú ý dấu "/" ở 2 đầu */
    
    public static final int    CODE_ERROR_401              = 401;
    public static final int    CODE_ERROR_403              = 403;
    public static final int    CODE_ERROR_404              = 404;
    public static final int    CODE_ERROR_500              = 500;
    public static final String URL_ERROR_401               = "/error/401/";
    public static final String URL_ERROR_403               = "/error/403/";
    public static final String URL_ERROR_404               = "/error/404/";
    public static final String URL_ERROR_500               = "/error/500/";
    
    /* Hằng số cho xhtml (Phải có getter) */
    private final int min_tendangnhap                      = 6;
    private final int max_tendangnhap                      = 32;
    private final int min_tendanhmuc                       = 3;
    private final int max_tendanhmuc                       = 64;
    
    private final int min_matkhau                          = 8;
    private final int max_matkhau                          = 32;
        
    
    
    /* Getter */

    public int getMin_tendangnhap() {
        return min_tendangnhap;
    }

    public int getMax_tendangnhap() {
        return max_tendangnhap;
    }

    public int getMin_matkhau() {
        return min_matkhau;
    }

    public int getMax_matkhau() {
        return max_matkhau;
    }

    public int getMin_tendanhmuc() {
        return min_tendanhmuc;
    }

    public int getMax_tendanhmuc() {
        return max_tendanhmuc;
    }
   
}
