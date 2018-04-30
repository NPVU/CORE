/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package npvu.util;

/**
 *
 * @author npvu
 */
public class ValidateUtils {
    
    private String[] specialChar    = {"`","~","!","@","#","$","%","^","&","*","(",")","_","-","=","+","{","}",
                                       "[","]","\\","|",";",":","'","\"",",","<",".",">","/","?"};
    
    private String[] specialCharSQL = {"&","=","<",">",";",":","(",")","{","}","*","|","'","\"","?"};
    
    private String strSpecialChar   = "` ~ ! @ # $ % ^ & * ( ) - _ + = { } [ ] \\ | : ; ' \" < > , . / ?";
    
    public boolean checkSpecialChar(String str){
        for (String speChar : specialChar) {
            if (str.indexOf(speChar) > 0) {                
                return true;
            }
        }
        return false;
    }

    public String[] getSpecialChar() {
        return specialChar;
    }

    public void setSpecialChar(String[] specialChar) {
        this.specialChar = specialChar;
    }

    public String[] getSpecialCharSQL() {
        return specialCharSQL;
    }

    public void setSpecialCharSQL(String[] specialCharSQL) {
        this.specialCharSQL = specialCharSQL;
    }

    public String getStrSpecialChar() {
        return strSpecialChar;
    }

    public void setStrSpecialChar(String strSpecialChar) {
        this.strSpecialChar = strSpecialChar;
    }

}
