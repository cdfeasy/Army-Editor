/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class ItemBase implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;
    private Codex codex;
     private List<Option> options=new ArrayList<Option>();
   //  @JsonIgnore
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="Codex_fk")
    public Codex getCodex() {
        return codex;
    }

    public void setCodex(Codex codex) {
        this.codex = codex;
    }
    
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public ItemBase() {
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

	@Override
	public String toString() {
		return "ItemBase{" + "id=" + id + ", name=" + name + ", description=" + description + ", options=" + options + '}';
	}
	
	

}
