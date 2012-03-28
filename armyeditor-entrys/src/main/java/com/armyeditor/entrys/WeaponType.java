/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import javax.persistence.Id;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class WeaponType implements java.io.Serializable  {
    private Long id;
    private String nameRus;
    private String nameEng;

    public WeaponType() {
    }

    @Id @javax.persistence.GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEng() {
        return nameEng;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }
}
