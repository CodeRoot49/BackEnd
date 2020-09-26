
package com.access.dao.empleado;

import com.access.DBCONNECTION.DBCONNECTION;
import com.access.exception.DatosNotFound;
import com.access.models.empleado.Empleado;
import com.access.models.empleado.Empleado_update;
import com.access.models.empleado.Empleado_update_foto;
import com.access.models.empleado.Empleados;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mili
 */
public class Empleado_dao {
    private PreparedStatement ps; 
    private ResultSet rs;
    DBCONNECTION con;
    public Empleado_dao() {
        con = new DBCONNECTION();
    }
    
    //es un FindAll Que obtiene todos los empleados
    public List<Empleados> getEmpleados(){
        try{
            String SQL = "SELECT  numDoc, CONCAT(nombres,\" \",apellidos) AS nombreCompleto,ntelefono,email, cargo FROM empleados;";
            ps = con.getConnetion().prepareStatement(SQL);
            rs = ps.executeQuery();
            List<Empleados> lista = new ArrayList<>();
            while(rs.next()){
                Empleados empleado = new Empleados();
                empleado.setDocumento(rs.getString("numDoc"));
                empleado.setNombre_completo(rs.getString("nombreCompleto"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setTelefono(rs.getString("ntelefono"));
                empleado.setCorreo(rs.getString("email"));
                lista.add(empleado);
            }
            return lista;
        }catch(SQLException e){
            throw new DatosNotFound("SQL ERROR  - "+e.getMessage());
        }
        
    }
    
    //findSearch Obtiene la busqueda de algún empleado
    public List<Empleados> getEmpleadosBuscar(String buscar){
        try{
            String SQL = "SELECT  numDoc, CONCAT(nombres,\" \",apellidos) AS nombreCompleto,ntelefono,email, cargo FROM empleados WHERE numDoc LIKE ? OR nombres LIKE ? OR apellidos LIKE ? OR cargo LIKE ?;";
            ps = con.getConnetion().prepareStatement(SQL);
            ps.setString(1, "%"+buscar+"%");
            ps.setString(2, "%"+buscar+"%");
            ps.setString(3, "%"+buscar+"%");
            ps.setString(4, "%"+buscar+"%");
            rs = ps.executeQuery();
            List<Empleados> lista = new ArrayList<>();
            while(rs.next()){
                System.out.println(rs.getString("numDoc"));
                Empleados empleado = new Empleados();
                empleado.setDocumento(rs.getString("numDoc"));
                empleado.setNombre_completo(rs.getString("nombreCompleto"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setTelefono(rs.getString("ntelefono"));
                empleado.setCorreo(rs.getString("email"));
                lista.add(empleado);
            }
            
            if(lista.isEmpty())
                throw new DatosNotFound("Empleado no encontrado");
            return lista;
        }catch(SQLException e){
           throw new DatosNotFound("SQL ERROR  - "+e.getMessage());
        }
    }
    
    //findOne Obtiene a un empleado
    public Empleado getEmpleado(String documento){
        try{
           String SQL = "SELECT numDoc,tipoDoc, CONCAT(nombres,\" \",apellidos) AS nombre_completo ,fechaNac,email,rh,departamento,municipio,direccion,ntelefono,cargo,foto FROM empleados WHERE numDoc = ?";
           ps = con.getConnetion().prepareStatement(SQL); 
           ps.setString(1, documento);
           rs = ps.executeQuery();
           Empleado empleado = null;
           if(rs.next()){
               empleado = new Empleado();
               empleado.setDocumento(rs.getString("numDoc"));
               empleado.setTipoDocumento(rs.getString("tipoDoc"));
               empleado.setNombreCompleto(rs.getString("nombre_completo"));
               empleado.setFechaNacimiento(rs.getString("fechaNac"));
               empleado.setCorreo(rs.getString("email"));
               empleado.setRh(rs.getString("rh"));
               empleado.setDepartamento(rs.getString("departamento"));
               empleado.setMunicipio(rs.getString("municipio"));
               empleado.setDireccion(rs.getString("direccion"));
               empleado.setTelefono(rs.getString("ntelefono"));
               empleado.setCargo(rs.getString("cargo"));
               empleado.setFoto(rs.getString("foto"));
           }else{
               throw new DatosNotFound("Empleado no existe");
           }
               
           return empleado;
        }catch(SQLException e){
            throw new DatosNotFound("SQL ERROR  - "+e.getMessage());
        }
    }
    
    //actualiza varios datos del empleado
    public boolean setUpdateEmpleado(String documento, Empleado_update empleado){
        try{
            String SQL = "UPDATE empleados SET numDoc = ?, nombres = ?, apellidos= ? ,email = ?,departamento = ?,municipio = ?,direccion = ?,ntelefono = ?,cargo = ?  WHERE numDoc = ?";
            ps = con.getConnetion().prepareStatement(SQL); 
            ps.setString(1, empleado.getNDocumento());
            ps.setString(2, empleado.getNombres());
            ps.setString(3, empleado.getApellidos());
            ps.setString(4, empleado.getCorreo());
            ps.setString(5, empleado.getDepartamento());
            ps.setString(6, empleado.getMunicipio()); 
            ps.setString(7, empleado.getDireccion());
            ps.setString(8, empleado.getTelefono());
            ps.setString(9, empleado.getCargo());
            ps.setString(10, documento);
            int a = ps.executeUpdate();
            if(a==0)
                throw new DatosNotFound("Empleado no existe");
            return a==1;
        }catch(SQLException e){
           throw new DatosNotFound("SQL ERROR  - "+e.getMessage());
        }
    }
    
    
    //actualiza la foto del empleado
    public boolean setUpdatePictureEmpleado(String documento, Empleado_update_foto empleado){
        try{
            String SQL = "UPDATE empleados SET  foto = ?  WHERE numDoc = ?";
            ps = con.getConnetion().prepareStatement(SQL); 
            ps.setString(1, empleado.getFoto_update());
            ps.setString(2, documento);
            int a = ps.executeUpdate();
            if(a==0)
                throw new DatosNotFound("Error al actualizar foto empleado");
            return a==1;
        }catch(SQLException e){
           throw new DatosNotFound("SQL ERROR  - "+e.getMessage());
        }
    }
    
    //Elimina al empleado definitamente con todos sus reportes
    public boolean deleteEmpleado(String documento){
        try{
            String SQL = "DELETE FROM empleados WHERE numDoc = ?";
            ps = con.getConnetion().prepareStatement(SQL);
            ps.setString(1, documento);
            int a = ps.executeUpdate();
            if(a==0)
                 throw new DatosNotFound("Empleado no existe");
            
            
            return true;
        }catch(SQLException e){
            throw new DatosNotFound("SQL ERROR  - "+e.getMessage());
        }
    }
    
}
