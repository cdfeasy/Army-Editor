/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.entrys;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author dmitry
 */
public class WeaponSelection implements java.io.Serializable{

    private Long id;
    private List<Weapon> weapon;
    private String condition;

    @Id
    @javax.persistence.GeneratedValue
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
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Weapon> getWeapon() {
        return weapon;
    }

    public void setWeapon(List<Weapon> weapon) {
        this.weapon = weapon;
    }
}
