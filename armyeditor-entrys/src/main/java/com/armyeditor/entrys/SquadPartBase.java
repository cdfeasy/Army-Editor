/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private String  id;
    private UnitBase unit;
    private int minSize;
    private int maxSize;
    private List<ItemSelection> itemSelection;
    private List<WeaponSelection> weaponSelection;
    private List<SquadPartBase> modifications=new ArrayList<SquadPartBase>();
    private SquadPartBase parent;
    private String conditions;

    public SquadPartBase() {
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<ItemSelection> getItemSelection() {
        return itemSelection;
    }

    public void setItemSelection(List<ItemSelection> itemSelection) {
        this.itemSelection = itemSelection;
    }
   @ManyToMany(cascade = CascadeType.REFRESH)
    public List<WeaponSelection> getWeaponSelection() {
        return weaponSelection;
    }

    public void setWeaponSelection(List<WeaponSelection> weaponSelection) {
        this.weaponSelection = weaponSelection;
    }
  
    
    @Id 
    public String getId() {
        return id;
    }

    public void setId(String id) {
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
    @JsonIgnore
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
    public UnitBase getUnit() {
        return unit;
    }

    public void setUnit(UnitBase unit) {
        this.unit = unit;
    }
}
