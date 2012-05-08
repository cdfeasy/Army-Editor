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
public class WeaponType implements java.io.Serializable  {
    private Long id;
    private String name;
    private String description;

    public WeaponType() {
    }

    @Id @javax.persistence.GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
