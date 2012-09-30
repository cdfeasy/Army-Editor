/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import com.armyeditor.dto.CodexDTO;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class Option implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;
    private String action;
    private Codex codex;

    public Option() {
    }
    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH} )
    @JoinColumn(name="Codex_fk")
    public Codex getCodex() {
        return codex;
    }

    public void setCodex(Codex codex) {
        this.codex = codex;
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Option{" + "id=" + id + ", name=" + name + ", description=" + description + ", action=" + action + '}';
    }
    

	
	
}
