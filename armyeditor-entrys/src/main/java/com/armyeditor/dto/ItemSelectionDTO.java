/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.dto;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author dmitry
 */

public class ItemSelectionDTO implements java.io.Serializable{

    private Long id;
    private List<ItemDTO> item;
    private String condition;

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public List<ItemDTO> getItem() {
        return item;
    }

    public void setItem(List<ItemDTO> item) {
        this.item = item;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
