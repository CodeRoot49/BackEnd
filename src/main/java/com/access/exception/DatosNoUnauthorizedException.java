
package com.access.exception;

import com.access.models.message.MessageModel;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DatosNoUnauthorizedException  implements ExceptionMapper<DatosNoUnauthorized> {
    @Override
    public Response toResponse(DatosNoUnauthorized e) {
        MessageModel msg = new MessageModel();
        msg.setMessage(e.getMessage());
        msg.setStatus(404);
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(msg)
                       .build();
    }
}
