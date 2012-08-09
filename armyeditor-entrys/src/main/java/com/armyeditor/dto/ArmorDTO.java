/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.Armor;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Dmitry
 */

public class ArmorDTO implements Serializable {
    private String  id;
    private String name;
    private String description;

    public ArmorDTO() {
    }

    public ArmorDTO(Armor armor){
        this.id = armor.getId();
        this.name = armor.getName();
        this.description = armor.getDescription();
    }

    public Armor toArmor(){
        Armor armor = new Armor();
        armor.setId(id);
        armor.setName(name);
        armor.setDescription(description);
        return armor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
