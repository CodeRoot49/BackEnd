
package com.access.models.reporte;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mili
 */
@XmlRootElement
public class Asistencia_empleado {
    String nombres,
         Ndocumento;
    List<Asistencia_empleado_> reporteModel = new ArrayList<>();

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getNdocumento() {
        return Ndocumento;
    }

    public void setNdocumento(String Ndocumento) {
        this.Ndocumento = Ndocumento;
    }

    public List<Asistencia_empleado_> getReporteModel() {
        return reporteModel;
    }

    public void setReporteModel(List<Asistencia_empleado_> reporteModel) {
        this.reporteModel = reporteModel;
    }
    
    
}
