/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.dto;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author dmitry
 */
public class VenicleDTO implements java.io.Serializable{
   private Long id;
    private VenicleBaseDTO unit;
    private WarGearDTO warGear;
      
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VenicleBaseDTO getUnit() {
        return unit;
    }

    public void setUnit(VenicleBaseDTO unit) {
        this.unit = unit;
    }

    public WarGearDTO getWarGear() {
        return warGear;
    }

    public void setWarGear(WarGearDTO warGear) {
        this.warGear = warGear;
    }
    
    
}
