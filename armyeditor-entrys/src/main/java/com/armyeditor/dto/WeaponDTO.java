/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.dto;

import com.armyeditor.entrys.Weapon;

import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dmitry
 */

public class WeaponDTO implements java.io.Serializable {
    private Long  id;
    private WeaponBaseDTO weapon;
    private int cost;

    public WeaponDTO() {
    }

    public WeaponDTO(Weapon w){
        this.id = w.getId();
        this.weapon = new WeaponBaseDTO(w.getWeapon());
        this.cost = w.getCost();
    }

    public Weapon toWeapon(){
        Weapon w = new Weapon();
        w.setId(id);
        w.setWeapon(weapon.toWeaponBase());
        w.setCost(cost);
        return w;
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

    @ManyToOne(cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "WeaponBase_fk")
    public WeaponBaseDTO getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponBaseDTO weapon) {
        this.weapon = weapon;
    }
}
