/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.entrys;

import java.util.List;
import javax.persistence.*;

/**
 *
 * @author dmitry
 */
@javax.persistence.Entity
public class ItemSelection implements java.io.Serializable{

    private Long id;
    private List<Item> item;
    private String condition;

    @Id
    @javax.persistence.GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
}
