
package com.access.models.reporte;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mili
 */
@XmlRootElement
public class Reportes_findAll {
     private String fecha;
    private int id;

    public Reportes_findAll() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    } 
     
     
}
