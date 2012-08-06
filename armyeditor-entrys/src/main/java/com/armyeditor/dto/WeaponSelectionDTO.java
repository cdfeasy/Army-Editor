/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.dto;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author dmitry
 */

public class WeaponSelectionDTO implements java.io.Serializable{

    private Long id;
    private List<WeaponDTO> weapon;
    private String condition;

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    public List<WeaponDTO> getWeapon() {
        return weapon;
    }

    public void setWeapon(List<WeaponDTO> weapon) {
        this.weapon = weapon;
    }
}
