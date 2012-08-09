/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.WeaponType;

import javax.persistence.Id;

/**
 *
 * @author Dmitry
 */
public class WeaponTypeDTO implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;

    public WeaponTypeDTO() {
    }

    public WeaponTypeDTO(WeaponType weaponType){
        this.id = weaponType.getId();
        this.name = weaponType.getName();
        this.description = weaponType.getDescription();
    }

    public WeaponType toWeaponType(){
        WeaponType w = new WeaponType();
        w.setId(id);
        w.setName(name);
        w.setDescription(description);
        return w;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
