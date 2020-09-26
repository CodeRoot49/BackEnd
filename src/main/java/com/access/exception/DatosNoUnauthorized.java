package com.access.exception;
public class DatosNoUnauthorized extends RuntimeException{
    public DatosNoUnauthorized(String mensaje){
         super(mensaje);
     }
}
