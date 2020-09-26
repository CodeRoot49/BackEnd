
package com.access.models.message;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mili
 */
@XmlRootElement
public class MessageModel {
    private String message;
    private int status;
    public MessageModel(){
        
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    
}
