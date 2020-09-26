
package com.access.resources;

import com.access.models.reporte.Asistencia_empleado;
import com.access.models.reporte.Reporte_day;
import com.access.models.reporte.Reporte_quincenal_finAll;
import com.access.models.reporte.Reportes_findAll;
import com.access.service.reporte.Reporte_service;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author mili
 */
@Path("api/r/{token}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ReporteResource {
 private final Reporte_service service =new Reporte_service();
   
    public ReporteResource() {
    }

   
    //url Obtener todos los reportes quincenales  http://localhost:8080/access.inspection/rest/api/r/{token}/quincenal?fecha=vacio para obtener todos 
    //url buscar reporte quincenal por fecha   http://localhost:8080/access.inspection/rest/api/r/{token}/quincenal?fecha={fecha} para obtenerla totalmente exacta
    @GET
    @Path("/quincenal")
    public Response ReporteQuincenalFindAll(@PathParam("token") String token, @QueryParam("fecha") String fecha) {
        service.validateToken(token);
        
        List<Reporte_quincenal_finAll> json = service.ReporteQuincenalFindAll(fecha);
        return Response.ok(json).build();
    }
    
    
        //url Obtener 15 reportes  quincenales  http://localhost:8080/access.inspection/rest/api/r/{token}/quincenal/fechas/{fecha inicio}/{fecha fin}
    @GET
    @Path("/quincenal/fechas/{inicio}/{fin}")
    public Response get15Reportes(@PathParam("token") String token, @PathParam("inicio") String inicio, @PathParam("fin") String fin){
        service.validateToken(token);
        
        List<Reportes_findAll> json = service.get15reportes(inicio, fin);
        for(Reportes_findAll r : json ){
            System.out.println(r.getFecha());
        }
        return Response.ok(json).build();
    }
    
    
    //url Obtener todos los reportes quincenales  http://localhost:8080/access.inspection/rest/api/r/{token}/reportes/all?fech=vacio para obtener todos 
    //url buscar reporte quincenal por fecha   http://localhost:8080/access.inspection/rest/api/r/{token}/reportes/all?fech={fecha} para obtenerla totalmente exacta
    
    @GET
    @Path("/reportes/all")
     public Response findAll(@PathParam("token") String token, @QueryParam("fech") String fecha){
         
        service.validateToken(token);
        
        List<Reportes_findAll> json = service.reportes_findAll(fecha);
        return Response.ok(json).build();
     }
    
    
     //url Obtener todas las asistencias quincenales  http://localhost:8080/access.inspection/rest/api/r/{token}/asistencias/all?data=vacio para obtener todos 
    //url buscar asistencias   por fecha   http://localhost:8080/access.inspection/rest/api/r/{token}/asistencias/all?data={fecha} para obtenerla totalmente exacta
    
    @GET
    @Path("/asistencias/all")
     public Response AsistenciafindAll(@PathParam("token") String token, @QueryParam("data") String fecha){
         
        service.validateToken(token);
        
        Reporte_day json = service.getAsistencias(fecha);
        return Response.ok(json).build();
     }
     
     
      //url Obtener todas las sistencias de una persona   http://localhost:8080/access.inspection/rest/api/r/{token}/asistencia/{documento} para obtener todos 
    @GET
    @Path("/asistencia/{documento}")
     public Response AsistenciafindOne(@PathParam("token") String token, @PathParam("documento") String documento){
         
        service.validateToken(token);
        
        Asistencia_empleado json = service.getAsistencianow(documento);
        return Response.ok(json).build();
     }
}
