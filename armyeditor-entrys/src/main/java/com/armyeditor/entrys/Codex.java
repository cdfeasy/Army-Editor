/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class Codex implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;
    private List<SquadBase> squads=new ArrayList<SquadBase>();

    public Codex(){

    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Id 
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
    @OneToMany(cascade=CascadeType.REFRESH)
    @JoinColumn(name="Codex_ID")
    public List<SquadBase> getSquads() {
        return squads;
    }

    public void setSquads(List<SquadBase> squads) {
        this.squads = squads;
    }

}
