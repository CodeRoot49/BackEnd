package com.access.exception;
public class DatosNotFound extends RuntimeException{
    public DatosNotFound(String mensaje){
         super(mensaje);
     }
}
