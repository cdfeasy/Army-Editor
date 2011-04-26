/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class Unit  implements java.io.Serializable {
    private Long id;
    private UnitBase unit;
    private WarGear warGear;
    @Id @javax.persistence.GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="Unit_fk")
    public UnitBase getUnit() {
        return unit;
    }

    public void setUnit(UnitBase unit) {
        this.unit = unit;
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="WarGear_fk")
    public WarGear getWarGear() {
        return warGear;
    }

    public void setWarGear(WarGear warGear) {
        this.warGear = warGear;
    }

}
