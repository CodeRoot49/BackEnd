
package com.access.resources;

import com.access.models.empleado.Empleado;
import com.access.models.empleado.Empleado_update;
import com.access.models.empleado.Empleado_update_foto;
import com.access.models.empleado.Empleados;
import com.access.models.message.MessageModel;
import com.access.service.empleado.Empleado_service;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("api/e/{token}")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpleadoResource {
    private final Empleado_service service = new Empleado_service();

    public EmpleadoResource() {
    }
    
    
    
    //url Obtener todos  http://localhost:8080/access.inspection/rest/api/e/{token}/empleados?buscar=vacio para obtener todos 
    //url buscar empleado  http://localhost:8080/access.inspection/rest/api/e/{token}/empleados?buscar= la busqueda
    @GET
    @Path("/empleados")
    public Response findAll(@QueryParam("buscar") String buscar, @PathParam("token") String token) {
       service.validateToken(token);
       if(buscar.length() > 0 ){
            List<Empleados> json = service.findSearchEmpleados(buscar);
            return Response.ok(json).build();
        }
        List<Empleados> json = service.findAllEmpleados();
        return Response.ok(json).build();
    }
    
    
    //url Obtener un  empleado  http://localhost:8080/access.inspection/rest/api/e/{token}/empleado/{número de documento}
    @GET
    @Path("/empleado/{documento}")
    public Response findOne(@PathParam("token") String token, @PathParam("documento") String documento){
       service.validateToken(token);
       Empleado json = service.findOneEmpleado(documento);
       return Response.ok(json).build();
    }
    
    
    
    //url actualizar empleado  http://localhost:8080/access.inspection/rest/api/e/{token}/empleado/{número de documento}/update
    @PUT
    @Path("/empleado/{documento}/update")
    public Response updateOne(Empleado_update update, @PathParam("documento") String documento, @PathParam("token") String token) {
        service.validateToken(token);
        MessageModel json = service.updateOneEmpleado(update, documento);
        return Response.status(Response.Status.ACCEPTED)
               .entity(json)
               .build();
    }
    
    
     //url actualizar foto de empleado  http://localhost:8080/access.inspection/rest/api/e/{token}/empleado/{número de documento}/foto
    @PUT
    @Path("/empleado/{documento}/foto")
    public Response updateOneFoto(@PathParam("documento") String documento, @PathParam("token") String token, Empleado_update_foto update_picture ){
        service.validateToken(token);
        MessageModel json = service.updateFoto(update_picture, documento);
        return Response.status(Response.Status.ACCEPTED)
               .entity(json)
               .build();
    }
    
    //url actualizar empleado  http://localhost:8080/access.inspection/rest/api/e/{token}/empleado/{número de documento}/delete
    @DELETE
    @Path("/empleado/{documento}/delete")
    public Response deleteOne(@PathParam("documento") String documento, @PathParam("token") String token){
        service.validateToken(token);
        MessageModel json = service.deleteOneEmpleado(documento);
        return Response.status(Response.Status.ACCEPTED)
               .entity(json)
               .build();
    }
}
