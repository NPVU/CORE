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

/**
 *
 * @author npvu
 */
@Entity
@Table(name = "danhmuc_baiviet")
public class DanhMucBaiVietModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "danhmuc_baiviet_danhmuc_baiviet_id_seq")
    @SequenceGenerator(name = "danhmuc_baiviet_danhmuc_baiviet_id_seq", sequenceName = "danhmuc_baiviet_danhmuc_baiviet_id_seq", allocationSize = 1)
    @Column(name = "danhmuc_baiviet_id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "danhmuc_baiviet_ten")
    private String ten;
    
    @Column(name = "danhmuc_baiviet_trangthai")
    private boolean trangThai;
    
    @Column(name = "danhmuc_baiviet_ngaytao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayTao;

    
    
    /* Getter & Setter */
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}
