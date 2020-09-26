
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
public class DatosNotFoundException implements ExceptionMapper<DatosNotFound> {
    
    @Override
    public Response toResponse(DatosNotFound e) {
        MessageModel msg = new MessageModel();
        msg.setMessage(e.getMessage());
        msg.setStatus(404);
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(msg)
                       .build();
    }
}
