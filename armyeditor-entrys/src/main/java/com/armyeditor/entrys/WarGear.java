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
    private List<ItemBase> items=new ArrayList<ItemBase>();

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
    public List<ItemBase> getItems() {
        return items;
    }

    public void setItems(List<ItemBase> items) {
        this.items = items;
    }
}
