/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

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
public class SquadPartDTO implements java.io.Serializable {
    private String  id;
    private SquadPartBaseDTO base;
    private int squadsize;
    private  ArrayList<ItemDTO> Items=new ArrayList<ItemDTO>();
    private  ArrayList<WeaponDTO> Weapons=new ArrayList<WeaponDTO>();
    private  ArrayList<ArmorDTO> Armors=new ArrayList<ArmorDTO>();

    public SquadPartDTO() {
    }

    
    public  ArrayList<ArmorDTO> getArmors() {
        return Armors;
    }

    public void setArmors( ArrayList<ArmorDTO> Armors) {
        this.Armors = Armors;
    }
    
    public  ArrayList<ItemDTO> getItems() {
        return Items;
    }

    public void setItems( ArrayList<ItemDTO> Items) {
        this.Items = Items;
    }
    
    public  ArrayList<WeaponDTO> getWeapons() {
        return Weapons;
    }

    public void setWeapons( ArrayList<WeaponDTO> Weapons) {
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
