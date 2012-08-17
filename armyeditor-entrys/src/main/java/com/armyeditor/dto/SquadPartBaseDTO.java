/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.armyeditor.entrys.ItemSelection;
import com.armyeditor.entrys.SquadPartBase;
import com.armyeditor.entrys.WeaponSelection;

import java.util.ArrayList;
import java.util. ArrayList;
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
    private ArrayList<ItemSelectionDTO> itemSelection=new ArrayList<ItemSelectionDTO>();
    private ArrayList<WeaponSelectionDTO> weaponSelection=new ArrayList<WeaponSelectionDTO>();
    private ArrayList<SquadPartBaseDTO> modifications=new ArrayList<SquadPartBaseDTO>();
    private SquadPartBaseDTO parent;
    private String conditions;

    public SquadPartBaseDTO() {
    }

    public SquadPartBaseDTO(SquadPartBase squadPartBase, SquadPartBaseDTO parent){
        this.id = squadPartBase.getId();
        this.unit = new UnitBaseDTO(squadPartBase.getUnit());
        this.minSize = squadPartBase.getMinSize();
        this.maxSize = squadPartBase.getMaxSize();
        for(ItemSelection i:squadPartBase.getItemSelection()){
            itemSelection.add(new ItemSelectionDTO(i));
        }
        for (WeaponSelection w:squadPartBase.getWeaponSelection()){
            weaponSelection.add(new WeaponSelectionDTO(w));
        }
        for (SquadPartBase s:squadPartBase.getModifications()){
            modifications.add(new SquadPartBaseDTO(s,this));
        }
        this.parent = parent;
        this.conditions = squadPartBase.getConditions();
    }

    public SquadPartBase toSquadPartBase(){
        SquadPartBase squadPartBase = new SquadPartBase();
        squadPartBase.setId(id);
        squadPartBase.setUnit(unit.toUnitBase());
        squadPartBase.setMinSize(minSize);
        squadPartBase.setMaxSize(maxSize);
        for (ItemSelectionDTO i:itemSelection){
            squadPartBase.getItemSelection().add(i.toItemSelection());
        }
        for (WeaponSelectionDTO w:weaponSelection){
            squadPartBase.getWeaponSelection().add(w.toWeaponSelection());
        }
        for (SquadPartBaseDTO s:modifications){
            squadPartBase.getModifications().add(s.toSquadPartBase());
        }
        squadPartBase.setParent(parent.toSquadPartBase());
        squadPartBase.setConditions(conditions);
        return squadPartBase;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }
    
    public  ArrayList<ItemSelectionDTO> getItemSelection() {
        return itemSelection;
    }

    public void setItemSelection( ArrayList<ItemSelectionDTO> itemSelection) {
        this.itemSelection = itemSelection;
    }
   
    public  ArrayList<WeaponSelectionDTO> getWeaponSelection() {
        return weaponSelection;
    }

    public void setWeaponSelection( ArrayList<WeaponSelectionDTO> weaponSelection) {
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
    
    public  ArrayList<SquadPartBaseDTO> getModifications() {
        return modifications;
    }

    public void setModifications( ArrayList<SquadPartBaseDTO> modifications) {
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
