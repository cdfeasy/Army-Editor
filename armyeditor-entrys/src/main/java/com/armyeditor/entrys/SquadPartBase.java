/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class SquadPartBase implements java.io.Serializable  {
    private Long id;
    private Unit unit;
    private int minSize;
    private int maxSize;
    private List<Item> availibleItem=new ArrayList<Item>();
    private List<Weapon> availibleWeapon=new ArrayList<Weapon>();
    private List<Option> options=new ArrayList<Option>();
    private List<SquadPartBase> modifications=new ArrayList<SquadPartBase>();
    private SquadPartBase parent;

    public SquadPartBase() {
    }

    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Item> getAvailibleItem() {
        return availibleItem;
    }

    public void setAvailibleItem(List<Item> availibleItem) {
        this.availibleItem = availibleItem;
    }
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Weapon> getAvailibleWeapon() {
        return availibleWeapon;
    }

    public void setAvailibleWeapon(List<Weapon> availibleWeapon) {
        this.availibleWeapon = availibleWeapon;
    }
    @Id @javax.persistence.GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public int getMinSize() {
        return minSize;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<SquadPartBase> getModifications() {
        return modifications;
    }

    public void setModifications(List<SquadPartBase> modifications) {
        this.modifications = modifications;
    }
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="SquadPartBase_fk")
    public SquadPartBase getParent() {
        return parent;
    }

    public void setParent(SquadPartBase parent) {
        this.parent = parent;
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="Unit_fk")
    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
