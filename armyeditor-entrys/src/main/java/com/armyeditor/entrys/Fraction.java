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
import javax.persistence.OneToMany;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class Fraction implements java.io.Serializable  {
    private Long id;
    private String name;
    private String description;
    private List<Codex> codexes =new ArrayList<Codex>();

    public Fraction() {
    }

    @OneToMany(cascade=CascadeType.REFRESH)
    @JoinColumn(name="Faction_ID")
    public List<Codex> getCodexes() {
        return codexes;
    }

    public void setCodexes(List<Codex> codexes) {
        this.codexes = codexes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Id @javax.persistence.GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
