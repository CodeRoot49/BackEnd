package com.access;

import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Configures JAX-RS for the application.
 * @author Juneau
 */
@ApplicationPath("rest")
public class JAXRSConfiguration extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }
     private void addRestResourceClasses(Set<Class<?>> resources) {
            resources.add(com.access.exception.DatosNoUnauthorizedException.class);
        resources.add(com.access.exception.DatosNotFoundException.class);
        resources.add(com.access.exception.ExceptionGeneric.class);
        resources.add(com.access.resources.AdminResource.class);
        resources.add(com.access.resources.EmpleadoResource.class);
        resources.add(com.access.resources.ReporteResource.class);
            
    }
}
