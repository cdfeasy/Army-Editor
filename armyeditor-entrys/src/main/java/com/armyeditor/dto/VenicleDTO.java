/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.dto;

import com.armyeditor.entrys.Venicle;

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

    public VenicleDTO(){

    }

    public VenicleDTO(Venicle venicle){
        this.id = venicle.getId();
        this.unit = new VenicleBaseDTO(venicle.getUnit());
        this.warGear = new WarGearDTO(venicle.getWarGear());
    }

    public Venicle toVenicle(){
        Venicle venicle = new Venicle();
        venicle.setId(id);
        venicle.setUnit(unit.toVenicleBase());
        venicle.setWarGear(warGear.toWarGear());
        return venicle;
    }

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
