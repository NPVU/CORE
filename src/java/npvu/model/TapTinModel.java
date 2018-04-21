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
@Table(name = "taikhoan")
public class TapTinModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "taptin_taptin_id_seq")
    @SequenceGenerator(name = "taptin_taptin_id_seq", sequenceName = "taptin_taptin_id_seq", allocationSize = 1)
    @Column(name = "taptin_id", unique = true, nullable = false)
    private long id;
    
    @Column(name = "taptin_ten")
    private String ten;
    
    @Column(name = "taptin_url")
    private String url;
    
    @Column(name = "taptin_type")
    private String type;
    
    @Column(name = "taptin_size")
    private long size;
    
    @Column(name = "taptin_ngaytao")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date ngayTao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
}
