/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

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
public class SquadPartDTO implements java.io.Serializable {
    private String  id;
    private SquadPartBaseDTO base;
    private int squadsize;
    private List<ItemDTO> Items=new ArrayList<ItemDTO>();
    private List<WeaponDTO> Weapons=new ArrayList<WeaponDTO>();
    private List<ArmorDTO> Armors=new ArrayList<ArmorDTO>();

    public SquadPartDTO() {
    }

    
    public List<ArmorDTO> getArmors() {
        return Armors;
    }

    public void setArmors(List<ArmorDTO> Armors) {
        this.Armors = Armors;
    }
    
    public List<ItemDTO> getItems() {
        return Items;
    }

    public void setItems(List<ItemDTO> Items) {
        this.Items = Items;
    }
    
    public List<WeaponDTO> getWeapons() {
        return Weapons;
    }

    public void setWeapons(List<WeaponDTO> Weapons) {
        this.Weapons = Weapons;
    }

    public SquadPartBaseDTO getBase() {
        return base;
    }

    public void setBase(SquadPartBaseDTO base) {
        this.base = base;
    }
     
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSquadsize() {
        return squadsize;
    }

    public void setSquadsize(int size) {
        this.squadsize = size;
    }
}
