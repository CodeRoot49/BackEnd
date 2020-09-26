
package com.access.functions;

/**
 *
 * @author mili
 */
public class ValidateData {

    public ValidateData() {
    }
    //Valida los numeros de documento
    public boolean  getValidateDocumento(String document){
        return document.matches("^[0-9]+$");
    }
    //los numero de telefonos
    public boolean  getValidateTelefono(String telefono){
        return telefono.matches("^[0-9]+$");
    }
    //valida los textos como nombre
    public boolean  getValidateText(String nombres){
        return nombres.matches("^([A-Z]{1}[a-z]+[ ]*){1,3}$");
    }
     public boolean  getValidateEmail(String nombres){
        return nombres.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$");
    }
}
