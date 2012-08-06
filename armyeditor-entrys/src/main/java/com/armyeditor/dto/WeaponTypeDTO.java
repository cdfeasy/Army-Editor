/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import javax.persistence.Id;

/**
 *
 * @author Dmitry
 */
public class WeaponTypeDTO implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;

    public WeaponTypeDTO() {
    }

    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
