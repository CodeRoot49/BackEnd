
package com.access.models.admin;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SessionToken {
    private String rol;
    private boolean tokenB;

    public SessionToken() {
    }

    public boolean isTokenB() {
        return tokenB;
    }

    public void setTokenB(boolean tokenB) {
        this.tokenB = tokenB;
    }

   
 

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
}
