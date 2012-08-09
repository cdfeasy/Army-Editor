/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.Item;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dmitry
 */

public class ItemDTO implements java.io.Serializable {
    private Long  id;
    private ItemBaseDTO itemBase;
    private int cost;

    public ItemDTO() {
    }

    public ItemDTO(Item item){
        this.id = item.getId();
        this.itemBase = new ItemBaseDTO(item.getItemBase());
        this.cost = item.getCost();
    }

    public Item toItem(){
        Item item = new Item();
        item.setId(id);
        item.setItemBase(itemBase.toItemBase());
        item.setCost(cost);
        return item;
    }

    public ItemBaseDTO getItemBase() {
        return itemBase;
    }

    public void setItemBase(ItemBaseDTO itemBase) {
        this.itemBase = itemBase;
    }
    
    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
