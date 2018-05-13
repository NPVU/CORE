/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

/**
 *
 * @author cuscsoft
 */
@ManagedBean(name="ShowGrowlUtils")
@ViewScoped
public class ShowGrowlUtils implements Serializable{
    
    public ShowGrowlUtils() {
    }
        
    
    public void showMessageSuccess(String ms){
        System.out.println(">> showMessageSuccess");
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(ms);
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(null, msg);
    }
    
    public void showMessageError(String ms){
        System.out.println(">> showMessageError");
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(ms);
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(null, msg);
    }
    
    public void showMessageError(String ms, UIComponent uic){
        System.out.println(">> showMessageError");
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(ms);
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        context.addMessage(uic.getClientId(context), msg);
    }
    
    public void showMessageSuccess(String ms, UIComponent uic){
        System.out.println(">> showMessageSucess");
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(ms);
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        context.addMessage(uic.getClientId(context), msg);
    }
    
    public void showMessageFatal(String ms){
        System.out.println(">> showMessageFatal");
        
        FacesContext context = FacesContext.getCurrentInstance();
        FacesMessage msg = new FacesMessage(ms);
        msg.setSeverity(FacesMessage.SEVERITY_FATAL);
        context.addMessage(null, msg);
    }
    
}
