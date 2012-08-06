/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

//import com.fasterxml.jackson.annotation.JsonIgnore;
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
 */
public class SquadPartBaseDTO implements java.io.Serializable  {
    private String  id;
    private UnitBaseDTO unit;
    private int minSize;
    private int maxSize;
    private List<ItemSelectionDTO> itemSelection=new ArrayList<ItemSelectionDTO>();
    private List<WeaponSelectionDTO> weaponSelection=new ArrayList<WeaponSelectionDTO>();
    private List<SquadPartBaseDTO> modifications=new ArrayList<SquadPartBaseDTO>();
    private SquadPartBaseDTO parent;
    private String conditions;

    public SquadPartBaseDTO() {
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
    
    public List<ItemSelectionDTO> getItemSelection() {
        return itemSelection;
    }

    public void setItemSelection(List<ItemSelectionDTO> itemSelection) {
        this.itemSelection = itemSelection;
    }
   
    public List<WeaponSelectionDTO> getWeaponSelection() {
        return weaponSelection;
    }

    public void setWeaponSelection(List<WeaponSelectionDTO> weaponSelection) {
        this.weaponSelection = weaponSelection;
    }
  
    
     
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
    
    public List<SquadPartBaseDTO> getModifications() {
        return modifications;
    }

    public void setModifications(List<SquadPartBaseDTO> modifications) {
        this.modifications = modifications;
    }

    public SquadPartBaseDTO getParent() {
        return parent;
    }

    public void setParent(SquadPartBaseDTO parent) {
        this.parent = parent;
    }

    public UnitBaseDTO getUnit() {
        return unit;
    }

    public void setUnit(UnitBaseDTO unit) {
        this.unit = unit;
    }
}
