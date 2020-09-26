
package com.access.models.reporte;

import java.sql.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mili
 */
@XmlRootElement
public class Reporte_quincenal_finAll {
     private int id;
    private String InicioS, finS;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getInicioS() {
        return InicioS;
    }

    public void setInicioS(String InicioS) {
        this.InicioS = InicioS;
    }

    public String getFinS() {
        return finS;
    }

    public void setFinS(String finS) {
        this.finS = finS;
    }
    
    
}
