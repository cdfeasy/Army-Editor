/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class ItemBase implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;
    private Fraction fraction;
     private List<Option> options=new ArrayList<Option>();

    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="Fraction_fk")
    public Fraction getFraction() {
        return fraction;
    }

    public void setFraction(Fraction fraction) {
        this.fraction = fraction;
    }
    
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public ItemBase() {
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
