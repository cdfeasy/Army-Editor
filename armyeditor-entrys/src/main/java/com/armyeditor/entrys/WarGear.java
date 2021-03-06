/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class WarGear implements java.io.Serializable  {
    private Long id;
    private List<Item> items=new ArrayList<Item>();
    private List<Weapon> weapon=new ArrayList<Weapon>();

    public WarGear() {
    }

    @Id @javax.persistence.GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Weapon> getWeapon() {
        return weapon;
    }

    public void setWeapon(List<Weapon> weapon) {
        this.weapon = weapon;
    }
    
    
}
