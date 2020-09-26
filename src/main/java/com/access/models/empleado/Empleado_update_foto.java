
package com.access.models.empleado;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mili
 */

@XmlRootElement
public class Empleado_update_foto {
    private String foto_update;

    public Empleado_update_foto() {
    }

    public String getFoto_update() {
        return foto_update;
    }

    public void setFoto_update(String foto_update) {
        this.foto_update = foto_update;
    }
    
}
