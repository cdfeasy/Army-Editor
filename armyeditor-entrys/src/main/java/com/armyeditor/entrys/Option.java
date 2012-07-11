/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import javax.persistence.Id;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class Option implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;

    public Option() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
