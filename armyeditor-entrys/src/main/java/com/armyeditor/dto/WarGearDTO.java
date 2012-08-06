/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Dmitry
 */
public class WarGearDTO implements java.io.Serializable  {
    private Long id;
    private List<ItemDTO> items=new ArrayList<ItemDTO>();
    private List<WeaponDTO> weapon=new ArrayList<WeaponDTO>();

    public WarGearDTO() {
    }

     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
    
    public List<WeaponDTO> getWeapon() {
        return weapon;
    }

    public void setWeapon(List<WeaponDTO> weapon) {
        this.weapon = weapon;
    }
    
    
}
