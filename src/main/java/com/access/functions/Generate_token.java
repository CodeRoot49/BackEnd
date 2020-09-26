
package com.access.functions;

import com.access.dao.admin.Admin_Dao;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 *
 * @author mili
 */
public class Generate_token {
    private final String token;
    private final long time = System.currentTimeMillis();
    public Generate_token() {
        this.token =UUID.randomUUID().toString();
    }
    
    public String  getToken() {
        return token;
    }
    public Date getFecha(){
        return new Date(time);
    }
    public String getHota(){
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("HH:mm:ss"); // El formato es HH:mm:ss
        return  formateador.format(LocalDateTime.now());
    }
    public long getExpDate(){
        return time+7200000;
    }
    public boolean validate(long tokenFecha, String token){
        if(time >= tokenFecha){
            new Admin_Dao().sessionOut(token);
            return false; 
        }
        return true;
    }
}
