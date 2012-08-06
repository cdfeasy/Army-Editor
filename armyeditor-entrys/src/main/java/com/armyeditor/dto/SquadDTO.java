/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Dmitry
 */
public class SquadDTO implements java.io.Serializable {
    private Long id;
    private SquadBaseDTO squadBase;

    public SquadDTO() {
    }

     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    public SquadBaseDTO getSquadBase() {
        return squadBase;
    }

    public void setSquadBase(SquadBaseDTO squadBase) {
        this.squadBase = squadBase;
    }
}
