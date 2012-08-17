/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.dto;

import com.armyeditor.entrys.Item;
import com.armyeditor.entrys.ItemSelection;

import java.util. ArrayList;
import javax.persistence.*;

/**
 *
 * @author dmitry
 */

public class ItemSelectionDTO implements java.io.Serializable{

    private Long id;
    private ArrayList<ItemDTO> item=new ArrayList<ItemDTO>();
    private String condition;

    public ItemSelectionDTO(){

    }

    public ItemSelectionDTO(ItemSelection itemSelection){
        this.id = itemSelection.getId();
        for (Item i:itemSelection.getItem()){
            item.add(new ItemDTO(i));
        }
        this.condition = itemSelection.getCondition();
    }

    public ItemSelection toItemSelection(){
        ItemSelection itemSelection = new ItemSelection();
        itemSelection.setId(id);
        for (ItemDTO i:item){
            itemSelection.getItem().add(i.toItem());
        }
        itemSelection.setCondition(condition);
        return itemSelection;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public  ArrayList<ItemDTO> getItem() {
        return item;
    }

    public void setItem( ArrayList<ItemDTO> item) {
        this.item = item;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
