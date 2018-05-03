/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author npvu
 */
@Entity
@Table(name = "baiviet")
public class BaiVietModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "baiviet_baiviet_id_seq")
    @SequenceGenerator(name = "baiviet_baiviet_id_seq", sequenceName = "dbaiviet_baiviet_id_seq", allocationSize = 1)
    @Column(name = "baiviet_id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "baiviet_tieude")
    private String tieuDe;
    
    @Column(name = "baiviet_icon_url")
    private String iconUrl;
    
    @Column(name = "baiviet_noidung")
    private String noiDung;
    
    @Column(name = "baiviet_nguoitao")
    private String tacGia;
    
    @Column(name = "baiviet_ngaytao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayTao;
    
    @Column(name = "baiviet_ngayxuatban")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayXuatBan;
    
    @Column(name = "baiviet_xuatban")
    private boolean xuatBan;
    
    @Column(name = "danhmuc_baiviet_id")
    private int danhmuc_baiviet_id;
    
    @Column(name = "taikhoan_id")
    private long taiKhoanID;

    @Transient
    private String tenDanhMuc;
    
    /* Getter && Setter */
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayXuatBan() {
        return ngayXuatBan;
    }

    public void setNgayXuatBan(Date ngayXuatBan) {
        this.ngayXuatBan = ngayXuatBan;
    }

    public int getDanhmuc_baiviet_id() {
        return danhmuc_baiviet_id;
    }

    public void setDanhmuc_baiviet_id(int danhmuc_baiviet_id) {
        this.danhmuc_baiviet_id = danhmuc_baiviet_id;
    }

    public long getTaiKhoanID() {
        return taiKhoanID;
    }

    public void setTaiKhoanID(long taiKhoanID) {
        this.taiKhoanID = taiKhoanID;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public boolean isXuatBan() {
        return xuatBan;
    }

    public void setXuatBan(boolean xuatBan) {
        this.xuatBan = xuatBan;
    }

}
