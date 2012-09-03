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
public class SquadPart implements java.io.Serializable {
    private String  id;
    private SquadPartBase base;
    private int squadsize;
    private List<Item> Items=new ArrayList<Item>();
    private List<Weapon> Weapons=new ArrayList<Weapon>();

    public SquadPart() {
    }

    
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Item> getItems() {
        return Items;
    }

    public void setItems(List<Item> Items) {
        this.Items = Items;
    }
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Weapon> getWeapons() {
        return Weapons;
    }

    public void setWeapons(List<Weapon> Weapons) {
        this.Weapons = Weapons;
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="SquadPartBase_fk")
    public SquadPartBase getBase() {
        return base;
    }

    public void setBase(SquadPartBase base) {
        this.base = base;
    }
    @Id 
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
