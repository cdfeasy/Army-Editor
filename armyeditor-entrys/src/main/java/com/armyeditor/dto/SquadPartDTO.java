/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.Armor;
import com.armyeditor.entrys.Item;
import com.armyeditor.entrys.SquadPart;
import com.armyeditor.entrys.Weapon;

import java.util.ArrayList;

/**
 *
 * @author Dmitry
 */
public class SquadPartDTO implements java.io.Serializable {
    private String  id;
    private SquadPartBaseDTO base;
    private int squadsize;
    private  ArrayList<ItemDTO> items =new ArrayList<ItemDTO>();
    private  ArrayList<WeaponDTO> weapons =new ArrayList<WeaponDTO>();
    private  ArrayList<ArmorDTO> armors =new ArrayList<ArmorDTO>();

    public SquadPartDTO() {
    }

    public SquadPartDTO(SquadPart squadPart){
        this.id = squadPart.getId();
        this.base = new SquadPartBaseDTO(squadPart.getBase());
        this.squadsize = squadPart.getSquadsize();
        for (Item i:squadPart.getItems()){
           items.add(new ItemDTO(i));
        }
        for (Weapon w:squadPart.getWeapons()){
           weapons.add(new WeaponDTO(w));
        }
        for (Armor a:squadPart.getArmors()){
            armors.add(new ArmorDTO(a));
        }
    }
    
    public  ArrayList<ArmorDTO> getArmors() {
        return armors;
    }

    public void setArmors( ArrayList<ArmorDTO> Armors) {
        this.armors = Armors;
    }
    
    public  ArrayList<ItemDTO> getItems() {
        return items;
    }

    public void setItems( ArrayList<ItemDTO> Items) {
        this.items = Items;
    }
    
    public  ArrayList<WeaponDTO> getWeapons() {
        return weapons;
    }

    public void setWeapons( ArrayList<WeaponDTO> Weapons) {
        this.weapons = Weapons;
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
