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
public class UnitType implements java.io.Serializable  {
    private Long id;
    private String name;
    private String nameRus;

    public UnitType() {
    }

    @Id @javax.persistence.GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }
}
