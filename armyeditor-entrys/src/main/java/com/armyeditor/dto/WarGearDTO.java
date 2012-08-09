/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.Item;
import com.armyeditor.entrys.WarGear;
import com.armyeditor.entrys.Weapon;

import java.util.ArrayList;
import java.util. ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Dmitry
 */
public class WarGearDTO implements java.io.Serializable  {
    private Long id;
    private ArrayList<ItemDTO> items=new ArrayList<ItemDTO>();
    private ArrayList<WeaponDTO> weapon=new ArrayList<WeaponDTO>();

    public WarGearDTO() {
    }

    public WarGearDTO(WarGear warGear){
        this.id = warGear.getId();
        for (Item i:warGear.getItems()){
            items.add(new ItemDTO(i));
        }
        for (Weapon w:warGear.getWeapon()){
            weapon.add(new WeaponDTO(w));
        }
    }

    public WarGear toWarGear(){
        WarGear warGear = new WarGear();
        warGear.setId(id);
        for (ItemDTO i:items){
            warGear.getItems().add(i.toItem());
        }
        for (WeaponDTO w:weapon){
            warGear.getWeapon().add(w.toWeapon());
        }
        return warGear;
    }
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public  ArrayList<ItemDTO> getItems() {
        return items;
    }

    public void setItems( ArrayList<ItemDTO> items) {
        this.items = items;
    }
    
    public  ArrayList<WeaponDTO> getWeapon() {
        return weapon;
    }

    public void setWeapon( ArrayList<WeaponDTO> weapon) {
        this.weapon = weapon;
    }
    
    
}
