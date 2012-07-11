/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class Squad implements java.io.Serializable {
    private Long id;
    private SquadBase squadBase;

    public Squad() {
    }

    @Id @javax.persistence.GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    public SquadBase getSquadBase() {
        return squadBase;
    }

    public void setSquadBase(SquadBase squadBase) {
        this.squadBase = squadBase;
    }
}
