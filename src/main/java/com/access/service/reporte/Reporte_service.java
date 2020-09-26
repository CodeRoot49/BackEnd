
package com.access.service.reporte;

import com.access.dao.admin.Admin_Dao;
import com.access.dao.reporte.Reporte_dao;
import com.access.exception.DatosNoUnauthorized;
import com.access.models.admin.SessionToken;
import com.access.models.reporte.Asistencia_empleado;
import com.access.models.reporte.Reporte_day;
import com.access.models.reporte.Reporte_quincenal_finAll;
import com.access.models.reporte.Reportes_findAll;
import java.util.List;

/**
 *
 * @author mili
 */
public class Reporte_service {
    private final Admin_Dao Adao;
    private final Reporte_dao Pdao;
    private boolean tokenBoolean = false;
    private String rolAdmin;
    public Reporte_service() {
        this.Adao = new Admin_Dao();
        this.Pdao = new Reporte_dao();
    }
    
    public void validateToken(String token) {
        SessionToken tokenValue = Adao.tokenValidator(token);
        this.rolAdmin = tokenValue.getRol();
        this.tokenBoolean = tokenValue.isTokenB();
        Adao.Disconnect();
    }
     //Obtiene todos los reportes quincenales 
    public List<Reporte_quincenal_finAll> ReporteQuincenalFindAll(String fecha){
        if (!tokenBoolean && !rolAdmin.equals("adminReporte")) 
            throw new DatosNoUnauthorized("No esta autorizado");
        
        List<Reporte_quincenal_finAll> retorno= Pdao.ReporteQuincenalFindAll(fecha);
        Adao.Disconnect();
        return retorno;
    }
    
    
    //Obtiene los 15 reportes quincenales
    public List<Reportes_findAll>  get15reportes(String inicio, String fin){
        if (!tokenBoolean && !rolAdmin.equals("adminReporte")) 
            throw new DatosNoUnauthorized("No esta autorizado");
        
        List<Reportes_findAll> retorno =  Pdao.get15reportes(inicio, fin);
        Adao.Disconnect();
        return retorno;
    }
    
      //Obtiene todos los reportes creados
    public List<Reportes_findAll> reportes_findAll(String fecha) {
        if (!tokenBoolean && !rolAdmin.equals("adminReporte")) 
            throw new DatosNoUnauthorized("No esta autorizado");
        
        
        List<Reportes_findAll> retorno =  Pdao.reportes_findAll(fecha);
        Adao.Disconnect();
        return retorno;
    }
    
    //Se obtinen todas las asistencias de ese día por mediode la fecha
    public Reporte_day getAsistencias(String fecha) {
        if (!tokenBoolean && !rolAdmin.equals("adminReporte")) 
            throw new DatosNoUnauthorized("No esta autorizado");
        
        
        Reporte_day retorno = Pdao.getAsistencias(fecha);
        Adao.Disconnect();
        return retorno;
    }
    
    
    // se obtiene el nombre y la asistencia del empleado
    public Asistencia_empleado getAsistencianow(String documento) {
        if (!tokenBoolean && !rolAdmin.equals("adminReporte")) 
            throw new DatosNoUnauthorized("No esta autorizado");
        
        Asistencia_empleado retorno = Pdao.getAsistencianow(documento);
        Adao.Disconnect();
        return retorno;
    }
  
    
}
