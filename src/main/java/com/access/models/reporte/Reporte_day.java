
package com.access.models.reporte;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mili
 */
@XmlRootElement
public class Reporte_day {
    private String fecha;
    private  List<Asistencia_findAll> asistencias = new ArrayList<>();

    public Reporte_day() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<Asistencia_findAll> getAsistencias() {
        return asistencias;
    }

    public void setAsistencias(List<Asistencia_findAll> asistencias) {
        this.asistencias = asistencias;
    }
    
    
}
