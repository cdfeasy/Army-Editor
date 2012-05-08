/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class UnitType implements java.io.Serializable  {
    private Long id;
    private String name;
    private List<Option> options;

    public UnitType() {
    }

    @Id @javax.persistence.GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
     @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
