
package com.access.models.admin;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class ConfirmPassAndId {
    private String password;
    private int id;

    public ConfirmPassAndId() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
}
