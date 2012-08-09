/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.Unit;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dmitry
 */
public class UnitDTO  implements java.io.Serializable {
    private Long id;
    private UnitBaseDTO unit;
    private WarGearDTO warGear;

    public UnitDTO() {
    }

    public UnitDTO(Unit u){
        this.id = u.getId();
        this.unit = new UnitBaseDTO(u.getUnit());
        this.warGear = new WarGearDTO(u.getWarGear());
    }
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UnitBaseDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitBaseDTO unit) {
        this.unit = unit;
    }

    public WarGearDTO getWarGear() {
        return warGear;
    }

    public void setWarGear(WarGearDTO warGear) {
        this.warGear = warGear;
    }

}
