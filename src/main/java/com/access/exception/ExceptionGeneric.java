
package com.access.exception;

import com.access.models.message.MessageModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author mili
 */
@Provider
public class ExceptionGeneric implements ExceptionMapper<Throwable>{
   

    @Override
    public Response toResponse(Throwable e) {
       MessageModel msg = new MessageModel();
       msg.setMessage(e.getMessage()+" - "+e.getCause());
       msg.setStatus(500);
       return Response.status(Response.Status.NOT_FOUND).entity(msg).build();
    }
}
