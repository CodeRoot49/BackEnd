
package com.access.dao.admin;

import com.access.DBCONNECTION.DBCONNECTION;
import com.access.exception.DatosNotFound;
import com.access.functions.Generate_token;
import com.access.models.admin.Admin;
import com.access.models.admin.ChangePassword;
import com.access.models.admin.ConfirmPassAndId;
import com.access.models.admin.Login;
import com.access.models.admin.SessionToken;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mili
 */
public class Admin_Dao {
   private PreparedStatement ps; 
    private ResultSet rs;
    DBCONNECTION con;
    public Admin_Dao(){
    con = new DBCONNECTION();
    }
    //iniciar session del administrador
    public Admin getSession(Login login){
        String SQL = "SELECT idAdmin, nombre, correo,rol  FROM administradores WHERE correo = ? AND pass = aes_encrypt(?,'xyz123');";
        Admin admin = null;
        try{
          ps = con.getConnetion().prepareStatement(SQL);  
          ps.setString(1, login.getCorreo());
          ps.setString(2, login.getPassword());
          rs = ps.executeQuery();
          
          if(rs.next()){
              admin=  new Admin();
              admin.setIdAdmin(rs.getInt("idAdmin"));
              admin.setNombre(rs.getString("nombre"));
              admin.setCorreo(rs.getString("correo"));
              admin.setRol(rs.getString("rol"));
          }else{
              throw new DatosNotFound("credenciales no validas");
          }
          return admin;
        }catch(SQLException e){
            throw new DatosNotFound("SQL ERROR"+e.getMessage());
        }
    }
    
    
    //inicializa el token para que pueda obtener la demás información
    public String setSessionToken(String token, Date fechaInit, String hora,  int idAdmin, long  exp){
        try{
            String SQL = "INSERT INTO token(idToken, fechaLogin, idAdministrador, hora, expTime) VALUES (?,?,?,?,?);";
            ps = con.getConnetion().prepareStatement(SQL);
            ps.setString(1, token);
            ps.setDate(2, (java.sql.Date) fechaInit);
            ps.setInt(3, idAdmin);
            ps.setString(4, hora);
            ps.setLong(5,exp);
            int a = ps.executeUpdate();
            if(a==1){
                return token;  
            }else{
                 throw new DatosNotFound("error intentar insertar token");
            }
        }catch(SQLException e){
            throw new DatosNotFound("error intentar insertar token - "+e.getMessage());
        }
    }
    //Cierra las sessiones y termina los token
    public boolean sessionOut(String token){
        try{
            String SQL ="DELETE FROM token WHERE idToken = ?";
            ps = con.getConnetion().prepareStatement(SQL);  
            ps.setString(1, token);
            int a = ps.executeUpdate();
            if(a==0)
                throw new DatosNotFound("Token no valido");
            return a==1;
        }catch(SQLException e){
            throw new DatosNotFound("Token no valido");
        }
    }
    
    
    //validar contraseñas para pasarlas realizar alguna acción como eliminar empleado o actualizar
    public boolean ConfirmDataAdminPass(ConfirmPassAndId adminDates){
         try{
            String SQL = "SELECT * FROM administradores WHERE pass = aes_encrypt(?,'xyz123') AND idAdmin = ?; ";
            ps = con.getConnetion().prepareStatement(SQL);
            ps.setString(1, adminDates.getPassword());
            ps.setInt(2, adminDates.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            throw new DatosNotFound("Contraseña no valida");
        }
    }
    
    
    //Cambiar contraseña del administrador
     public boolean changePassword(ChangePassword cAdmin){
         try{
            String SQL = "UPDATE administradores SET  pass = aes_encrypt(?,'xyz123') WHERE idAdmin = ? AND pass = aes_encrypt(?,'xyz123') ";
            ps = con.getConnetion().prepareStatement(SQL);
            ps.setString(1, cAdmin.getNewPassword());
            ps.setInt(2, cAdmin.getId());
            ps.setString(3, cAdmin.getPassword());
            int a = ps.executeUpdate();
            return a ==1;
        }catch(SQLException e){
           throw new DatosNotFound("Contraseña no valida");
        }
    }
     
     
     // valida el token para mostrar la informacion  de los empleados
    public SessionToken tokenValidator(String token){
        try{
            String SQL = "SELECT  expTime, rol FROM token AS tk JOIN administradores AS ad ON tk.idAdministrador = ad.idAdmin WHERE idToken = ?";
            ps = con.getConnetion().prepareStatement(SQL);
            ps.setString(1, token);
            rs= ps.executeQuery();
            SessionToken tokenValue = new  SessionToken();
            if(rs.next()){
                tokenValue.setRol(rs.getString("rol"));
                boolean tk = new Generate_token().validate(rs.getLong("expTime"), token);
                if(!tk)
                    throw new DatosNotFound("¡El token ha expirado!");
                tokenValue.setTokenB(tk);
            }else{
                throw new DatosNotFound("Token no valido is null");
            }
            return tokenValue;
        }catch(SQLException e){
            throw new DatosNotFound("Token no valido"+e.getMessage());
        }
    }
    
    //Realiza las desconexiones que se hiciron 
    public void Disconnect(){
        con.Disconnect();
    }
}
