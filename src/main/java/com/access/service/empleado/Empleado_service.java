package com.access.service.empleado;

import com.access.dao.admin.Admin_Dao;
import com.access.dao.empleado.Empleado_dao;
import com.access.exception.DatosNoUnauthorized;
import com.access.functions.ValidateData;
import com.access.models.admin.SessionToken;
import com.access.models.empleado.Empleado;
import com.access.models.empleado.Empleado_update;
import com.access.models.empleado.Empleado_update_foto;
import com.access.models.empleado.Empleados;
import com.access.models.message.MessageModel;
import java.util.List;

public class Empleado_service {

    //constructor variables utilizadas para conexión y verificación
    private final Admin_Dao Adao;
    private final Empleado_dao Edao;

    private final ValidateData validateData = new ValidateData();
    //private final ReplaceSignos replace = new ReplaceSignos();
    private boolean tokenBoolean = false;
    private String rolAdmin;

    public Empleado_service() {
        this.Adao = new Admin_Dao();
        this.Edao = new Empleado_dao();
    }

    public void validateToken(String token) {
        SessionToken tokenValue = Adao.tokenValidator(token);
        this.rolAdmin = tokenValue.getRol();
        this.tokenBoolean = tokenValue.isTokenB();
        Adao.Disconnect();
    }

    //Obtiene todos los empelado FindAll
    public List<Empleados> findAllEmpleados() {
        if (!tokenBoolean) 
            throw new DatosNoUnauthorized("No esta autorizado");
        
        return Edao.getEmpleados();
    }

    //Obtiene a los empleados por busqueda findSearch
    public List<Empleados> findSearchEmpleados(String search) {
        if (!tokenBoolean) 
            throw new DatosNoUnauthorized("No esta autorizado");
        
        return Edao.getEmpleadosBuscar(search);
    }

    //Obtiene a un empleado por documento findOne
    public Empleado findOneEmpleado(String documento) {
        if (!tokenBoolean) 
            throw new DatosNoUnauthorized("No esta autorizado");
        

        return Edao.getEmpleado(documento);
    }
    
    //Actializa al empleado especifico con updateOne
    public MessageModel updateOneEmpleado(Empleado_update update, String documento){
        if (!tokenBoolean && !rolAdmin.equals("adminUser")) 
            throw new DatosNoUnauthorized("No esta autorizado");
        
        if(!validateData.getValidateDocumento(update.getNDocumento()))
                throw new DatosNoUnauthorized("El documento sólo puede ser número");
            if(update.getNDocumento() == null || update.getNDocumento().length() < 8)
                throw new DatosNoUnauthorized("El  documento no puede ser menor a 8 o Estar vacio");
            if(!validateData.getValidateTelefono(update.getTelefono()))
                throw new DatosNoUnauthorized("El telefono sólo puede ser número");
            if(update.getTelefono() == null || update.getTelefono().length() < 6)
                throw new DatosNoUnauthorized("No telefono puede ser menor a 6 o Estar vacio");
            if(!validateData.getValidateText(update.getNombres()))
                throw new DatosNoUnauthorized("Los/El nombre no puede estar vacio o contener caracteres ilegales o números");
            if(!validateData.getValidateText(update.getApellidos()))
                throw new DatosNoUnauthorized("Los/El apellidos no puede estar vacio o contener caracteres ilegales o números");
            if(!validateData.getValidateEmail(update.getCorreo()))
                throw new DatosNoUnauthorized("Email introducido no corresponde");
             if(update.getCargo().length() < 2 )
                throw new DatosNoUnauthorized("El Cargo no puede estar vacio o contener caracteres ilegales o números");
             if(update.getDepartamento().length() <2)
                throw new DatosNoUnauthorized("El Departamento no puede estar vacio o contener caracteres ilegales o números");
             if(update.getMunicipio().length() < 2)
                throw new DatosNoUnauthorized("El Municipio no puede estar vacio o contener caracteres ilegales o números");
            
             
           boolean updateB = Edao.setUpdateEmpleado(documento, update);
           
           if(!updateB)
               throw new DatosNoUnauthorized("No se pudo actualizar hay algún error");
           
           MessageModel msg = new MessageModel();
           msg.setMessage("Actualizado correctamente");
           return msg;
    }
    
    
    //actualiza foto empleado
    public MessageModel updateFoto(Empleado_update_foto foto,String documento){
        if (!tokenBoolean && !rolAdmin.equals("adminUser")) 
            throw new DatosNoUnauthorized("No esta autorizado");
        
        boolean fotoB  = Edao.setUpdatePictureEmpleado(documento, foto);
        
        if(!fotoB)
            throw new DatosNoUnauthorized("Hubo un error al actualizar las fotos");
        
        
        MessageModel msg = new MessageModel();
        msg.setMessage("Actualizado correctamente");
        return msg;
    }
    
    //Eliminar a un empleado con todos sus reportes deleteOne
    public MessageModel deleteOneEmpleado(String documento) {
            
         if (!tokenBoolean && !rolAdmin.equals("adminUser")) 
            throw new DatosNoUnauthorized("No esta autorizado");
        
         boolean deleteB = Edao.deleteEmpleado(documento);
           
           if(!deleteB)
               throw new DatosNoUnauthorized("No se pudo Eliminar hay algún error");
        
    
        MessageModel msg = new MessageModel();
        msg.setMessage("Eliminado correctamente");
        return msg;
    }
}
