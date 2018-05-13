/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author npvu
 */
@Entity
@Table(name = "config_website")
public class ConfigWebsiteModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "config_website_config_website_id_seq")
    @SequenceGenerator(name = "config_website_config_website_id_seq", sequenceName = "config_website_config_website_id_seq", allocationSize = 1)
    @Column(name = "config_website_id", unique = true, nullable = false)
    private int id;
    
    @Column(name = "taptin_id")
    private long tapTinID;
    
    @Column(name = "config_website_name")
    private String websiteName;
    
    @Column(name = "config_website_title")
    private String websiteTitle;
    
    @Column(name = "config_website_phone")
    private String websitePhone;
    
    @Column(name = "config_website_address")
    private String websiteAddress;
    
    @Column(name = "config_website_email")
    private String websiteEmail;
    
    @Column(name = "config_website_facebook")
    private String websiteFaceBook;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getWebsiteTitle() {
        return websiteTitle;
    }

    public void setWebsiteTitle(String websiteTitle) {
        this.websiteTitle = websiteTitle;
    }

    public String getWebsitePhone() {
        return websitePhone;
    }

    public void setWebsitePhone(String websitePhone) {
        this.websitePhone = websitePhone;
    }

    public String getWebsiteAddress() {
        return websiteAddress;
    }

    public void setWebsiteAddress(String websiteAddress) {
        this.websiteAddress = websiteAddress;
    }

    public String getWebsiteEmail() {
        return websiteEmail;
    }

    public void setWebsiteEmail(String websiteEmail) {
        this.websiteEmail = websiteEmail;
    }

    public String getWebsiteFaceBook() {
        return websiteFaceBook;
    }

    public void setWebsiteFaceBook(String websiteFaceBook) {
        this.websiteFaceBook = websiteFaceBook;
    }

    public long getTapTinID() {
        return tapTinID;
    }

    public void setTapTinID(long tapTinID) {
        this.tapTinID = tapTinID;
    }
    
}
