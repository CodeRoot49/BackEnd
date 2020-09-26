
package com.access.dao.reporte;

import com.access.DBCONNECTION.DBCONNECTION;
import com.access.exception.DatosNotFound;
import com.access.models.reporte.Asistencia_empleado;
import com.access.models.reporte.Asistencia_empleado_;
import com.access.models.reporte.Asistencia_findAll;
import com.access.models.reporte.Reporte_day;
import com.access.models.reporte.Reporte_quincenal_finAll;
import com.access.models.reporte.Reportes_findAll;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mili
 */
public class Reporte_dao {
     private PreparedStatement ps; 
    private ResultSet rs;
    DBCONNECTION con;
    public Reporte_dao() {
        con = new DBCONNECTION();
    }
    
    
    
    //Obtiene todos los reportes quincenales 
    public List<Reporte_quincenal_finAll> ReporteQuincenalFindAll(String fecha) {
        try {
            if(fecha.length() > 5){
                String SQL = "SELECT `id_rp_quincenal` AS 'id de reporte',\n"
                    + "		`fecha_report_quince_inicio` AS 'inicio de quincena',\n"
                    + "        `fecha_report_quince_fin` AS 'fin de quincena' FROM reporte_quincenal WHERE ? BETWEEN fecha_report_quince_inicio AND fecha_report_quince_fin ORDER BY `fecha_report_quince_inicio` DESC;";
                ps = con.getConnetion().prepareStatement(SQL);
                ps.setString(1, fecha);
                rs = ps.executeQuery();
            } else{
                String SQL = "SELECT `id_rp_quincenal` AS 'id de reporte',\n"
                    + "		`fecha_report_quince_inicio` AS 'inicio de quincena',\n"
                    + "        `fecha_report_quince_fin` AS 'fin de quincena' FROM reporte_quincenal ORDER BY `fecha_report_quince_inicio` DESC;";
                ps = con.getConnetion().prepareStatement(SQL);
                rs = ps.executeQuery();
            }
            
            
            List<Reporte_quincenal_finAll> listaQuincenal = new ArrayList<>();
            Reporte_quincenal_finAll quincenal = null;
            // resulset 2 
            while (rs.next()) {
                quincenal = new Reporte_quincenal_finAll();
                quincenal.setId(rs.getInt("id de reporte"));
                quincenal.setInicioS(rs.getString("inicio de quincena"));
                quincenal.setFinS(rs.getString("fin de quincena"));
                listaQuincenal.add(quincenal);
            }
            if(listaQuincenal.isEmpty())
                throw new DatosNotFound("Datos no encontrado");
            
            return listaQuincenal;
        } catch (SQLException e) {
            throw new DatosNotFound("SQL ERROR : "+e.getMessage());
        }
    }
    
    //Obtiene los 15 reportes quincenales
    public List<Reportes_findAll>  get15reportes(String inicio, String fin){
        try{
            String SQL = "SELECT id_rp_diario AS id, fecha_reporte AS fecha FROM reporte_diario WHERE  fecha_reporte  between  ? AND ? ORDER BY fecha_reporte DESC;";
            ps = con.getConnetion().prepareStatement(SQL);
            ps.setString(1, inicio);
            ps.setString(2, fin);
            rs = ps.executeQuery();
            List<Reportes_findAll> lisreportes = new ArrayList<>();
            Reportes_findAll reportes = null;
            while (rs.next()) {
                reportes = new Reportes_findAll();
                reportes.setId(rs.getInt("id"));
                reportes.setFecha(rs.getString("fecha"));
                lisreportes.add(reportes);
            }
            if(reportes==null)
                throw new DatosNotFound("Datos no encontrado");
            if(lisreportes.isEmpty())
                throw new DatosNotFound("Datos no encontrado");
                
            return lisreportes;
        } catch (SQLException e) {
            throw new DatosNotFound("SQL ERROR : "+e.getMessage());
        }
    }
    
    //Obtiene todos los reportes creados
    public List<Reportes_findAll> reportes_findAll(String fecha) {
        try {
            System.out.println(fecha.length());
            if (fecha.length() > 4) {
                String SQL = "SELECT id_rp_diario AS id, fecha_reporte AS fecha FROM reporte_diario WHERE fecha_reporte= ? ORDER BY fecha_reporte DESC;";
                ps = con.getConnetion().prepareStatement(SQL);
                ps.setString(1, fecha);
            } else {
                String SQL = "SELECT id_rp_diario AS id, fecha_reporte AS fecha FROM reporte_diario ORDER BY fecha_reporte DESC;";
                ps = con.getConnetion().prepareStatement(SQL);
            }

            rs = ps.executeQuery();
            List<Reportes_findAll> lisreportes = new ArrayList<>();
            Reportes_findAll reportes = null;
            while (rs.next()) {
                reportes = new Reportes_findAll();
                reportes.setId(rs.getInt("id"));
                reportes.setFecha(rs.getString("fecha"));
                lisreportes.add(reportes);
            }
            if(lisreportes.isEmpty())
                throw new DatosNotFound("Datos no encontrado");
            return lisreportes;
        } catch (SQLException e) {
            throw new DatosNotFound("SQL ERROR : "+e.getMessage());
        }
    }
    
    //Se obtinen todas las asistencias de ese día por mediode la fecha
    public Reporte_day getAsistencias(String fecha) {
        try {
            System.out.println(fecha);
            String SQL = "Select distinct concat(em.nombres,' ', em.apellidos) AS `Nombre completo`, em.numDoc AS Documento, entrada.fecha_e_fk AS `Fecha`, entrada.hora_entrada as `hora de entrada`, salida.hora_salida AS `hora de salida` FROM empleados  AS em  JOIN asistencia_entrada AS entrada ON em.numDoc = entrada.documento JOIN asistencia_salida AS salida ON em.numDoc = salida.documento_fk  WHERE entrada.fecha_e_fk = ? order by  entrada.fecha_e_fk desc ;";
            ps = con.getConnetion().prepareStatement(SQL);
            ps.setString(1, fecha);
            rs = ps.executeQuery();

            Reporte_day reporteFecha = new Reporte_day();

            List<Asistencia_findAll> reporteList = new ArrayList<>();
            Asistencia_findAll reporte = null;
            while (rs.next()) {
                reporte = new Asistencia_findAll();
                reporteFecha.setFecha(rs.getString("Fecha"));
                reporte.setDocumentos(rs.getString("Documento"));
                reporte.setNombres(rs.getString("Nombre completo"));
                reporte.setEntrada(rs.getString("hora de entrada"));
                reporte.setSalida(rs.getString("hora de salida"));
                reporteList.add(reporte);
            }
            reporteFecha.setAsistencias(reporteList);
            
            if(reporteFecha==null)
                throw new DatosNotFound("Datos no encontrado");
            return reporteFecha;
        } catch (SQLException e) {
            throw new DatosNotFound("SQL ERROR : "+e.getMessage());
        }
    }
    
    
    // se obtiene el nombre y la asistencia del empleado
    public Asistencia_empleado getAsistencianow(String documento) {
        try {
            String SQL = "SELECT DISTINCT  fe.fecha_e_fk AS `fecha de asistencia`,   fe.hora_entrada AS `hora entrada`, fs.hora_salida AS `hora salida`, CONCAT(em.nombres,\" \",em.apellidos) AS `nombre completo`, em.numDoc AS documento FROM asistencia_salida  AS fs JOIN empleados AS em ON em.numDoc = fs.documento_fk JOIN asistencia_entrada AS fe ON em.numDoc = fe.documento  WHERE documento = ?  and fe.fecha_e_fk = fs.fecha_s_fk ORDER BY fe.fecha_e_fk desc;";
            ps = con.getConnetion().prepareStatement(SQL);
            ps.setString(1, documento);
            rs = ps.executeQuery();
            List<Asistencia_empleado_> listAsistencia = new ArrayList<>();
            Asistencia_empleado reporteName = null;
            Asistencia_empleado_ reporte = null;
            while (rs.next()) {
                reporteName = new Asistencia_empleado();
                reporteName.setNombres(rs.getString("nombre completo"));
                reporteName.setNdocumento(rs.getString("documento"));
                reporte = new Asistencia_empleado_();
                reporte.setFecha(rs.getString("fecha de asistencia"));
                reporte.setHoraEntrada(rs.getString("hora entrada"));
                reporte.setHoraSalida(rs.getString("hora salida"));
                listAsistencia.add(reporte);
            }
            
            reporteName.setReporteModel(listAsistencia);
            if(reporte == null)
                throw new DatosNotFound("Datos no encontrado");
            return reporteName;
        } catch (SQLException e) {
            throw new DatosNotFound("SQL ERROR : "+e.getMessage());
        } 
    }  
}
