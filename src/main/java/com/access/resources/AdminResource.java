package com.access.resources;

import com.access.models.admin.Admin;
import com.access.models.admin.ChangePassword;
import com.access.models.admin.ConfirmPassAndId;
import com.access.models.admin.Login;
import com.access.models.message.MessageModel;
import com.access.service.Admin.Admin_service;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("api")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AdminResource {
    
    
    private final Admin_service service = new Admin_service();
    
    
    
    //url iniciar sesion http://localhost:8080/access.inspection/rest/api/auth
    @POST
    @Path("/auth")
    public Response Session(Login login){
        Admin logged = service.getSession(login);   
        return Response.status(Response.Status.ACCEPTED)
                .entity(logged)
                .build();
    }
    
    
    //url cambiar contraseña http://localhost:8080/access.inspection/rest/api/auth/p_change
    @PUT
    @Path("/auth/p_change")
    public Response updatePassword(ChangePassword passwords){
        MessageModel json = service.changePassword(passwords);
        return Response.status(Response.Status.ACCEPTED)
               .entity(json)
               .build();
    }
    
    
    //url Eliminar la session  http://localhost:8080/access.inspection/rest/api/auth/{aqui va el token ya generado}
    @GET
    @Path("/auth/{token}")
    public Response sessionClose(@PathParam("token") String token){
        MessageModel json = service.sessionOut(token);
        return Response.status(Response.Status.ACCEPTED)
               .entity(json)
               .build();
    }
    
    
   //url cambiar contraseña http://localhost:8080/access.inspection/rest/api/auth/confirm/pass
   @POST
   @Path("/auth/confirm/pass")
   public Response ValidateConfirmPassword(ConfirmPassAndId data){
       MessageModel json = service.confirmPass(data);
       return Response.status(Response.Status.ACCEPTED)
                      .entity(json)
                      .build();  
   }
   
}
