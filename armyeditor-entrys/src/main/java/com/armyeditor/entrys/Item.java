/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dmitry
 */
@javax.persistence.Entity
public class Item implements java.io.Serializable {
    private Long id;
    private ItemBase itemBase;
    private int cost;

    public Item() {
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="ItemBase_fk")
    public ItemBase getItemBase() {
        return itemBase;
    }

    public void setItemBase(ItemBase itemBase) {
        this.itemBase = itemBase;
    }
    
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    @Id @javax.persistence.GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
