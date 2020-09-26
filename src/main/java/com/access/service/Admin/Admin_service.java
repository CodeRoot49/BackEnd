
package com.access.service.Admin;

import com.access.dao.admin.Admin_Dao;
import com.access.exception.DatosNoUnauthorized;
import com.access.exception.DatosNotFound;
import com.access.functions.Generate_token;
import com.access.functions.ValidateData;
import com.access.models.admin.Admin;
import com.access.models.admin.ChangePassword;
import com.access.models.admin.ConfirmPassAndId;
import com.access.models.admin.Login;
import com.access.models.message.MessageModel;
import java.util.Base64;

/**
 *
 * @author mili
 */
public class Admin_service {
    private final Generate_token tk = new Generate_token();
    private final ValidateData validated = new ValidateData();
    private final Admin_Dao dao;
    public Admin_service() {
        dao = new Admin_Dao();
        
    }
    /* Se obtienen las sessiones y se introduce el token en la DB con la fecha y la hora del inicio*/
    public Admin getSession(Login login){
        //se valida el correo del Administrador
        if(!validated.getValidateEmail(login.getCorreo()))
            throw new DatosNoUnauthorized("Correo no valido");
        
        
        
        Admin logged = dao.getSession(login);
        //cierra la conexion de cada peticion 
        String base64Name =  Base64.getEncoder().encodeToString(logged.getNombre().getBytes());
        String token =tk.getToken()+"."+base64Name;
        dao.setSessionToken(token, tk.getFecha(),tk.getHota(),logged.getIdAdmin(), tk.getExpDate());
        
        logged.setToken(token);
        //cierra la conexion de cada peticion 
        dao.Disconnect();
        return logged;
    }
    
    // se termina las session poniendo la fecha y la hora de la finalziación en la DB dejando el token inhabilitado
    public MessageModel sessionOut(String token){
        
        //valida que el token sea mayor a 60 caractaeres
        if(token.length() < 30)
            throw new DatosNotFound("Token lenght Error <Number> ");
        
        boolean sessionOut = dao.sessionOut(token);
         //cierra la conexion de cada peticion 
        dao.Disconnect();
        //Valida de que se haya terminado la session correctamente
        if(!sessionOut)
            throw new DatosNotFound("Not found");
        
        MessageModel msg = new MessageModel();
        msg.setMessage("Session termina");
        return msg;
    }
    
    //clase contraseña regresando los valores de confirmación o una exception
    public MessageModel confirmPass(ConfirmPassAndId data){
        
        boolean confirmpass = dao.ConfirmDataAdminPass(data);
         //cierra la conexion de cada peticion 
        dao.Disconnect();
        //valida si la la devolucion del dao es false para lanzar la exception
        if(!confirmpass)
            throw new DatosNoUnauthorized("Contraseña incorecta no confirmada");
        
        //Envia la respuesta 
        MessageModel msg = new MessageModel();
        msg.setMessage("Contraseña correcta");
        return msg;
    }
    
    
    //pasar los datos y validar si cambia la contraseña o lanza exception
    public MessageModel changePassword(ChangePassword password){
        
        
        boolean change = dao.changePassword(password);
         //cierra la conexion de cada peticion 
        dao.Disconnect();
        //valida si la la devolucion del dao es false para lanzar la exception
        
        if(!change)
            throw new DatosNoUnauthorized("Contraseña incorecta");
        
        //Envia la respuesta 
        MessageModel msg = new MessageModel();
        msg.setMessage("Contraseña cambiada ");
        return msg;
    }
}
