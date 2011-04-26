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
 */@javax.persistence.Entity
public class Weapon  implements java.io.Serializable {
    private Long id;
    private WeaponBase weapon;
    private int maxCount;
    private int cost;
    private boolean replace=true;

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

    public int getMaxCount() {
        return maxCount;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
    }

    public boolean isReplace() {
        return replace;
    }

    public void setReplace(boolean replace) {
        this.replace = replace;
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="WeaponBase_fk")
    public WeaponBase getWeapon() {
        return weapon;
    }

    public void setWeapon(WeaponBase weapon) {
        this.weapon = weapon;
    }

}
