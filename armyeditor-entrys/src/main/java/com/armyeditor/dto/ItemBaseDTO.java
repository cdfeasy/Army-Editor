/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.armyeditor.entrys.ItemBase;
import com.armyeditor.entrys.Option;

import java.util. ArrayList;
import javax.persistence.*;

/**
 *
 * @author Dmitry
 */
public class ItemBaseDTO implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;
    private CodexDTO codex;
    private ArrayList<OptionDTO> options=new ArrayList<OptionDTO>();

    public ItemBaseDTO(ItemBase itemBase) {
        this.id = itemBase.getId();
        this.name = itemBase.getName();
        this.description = itemBase.getDescription();
        if(itemBase.getCodex()!=null)
        this.codex = new CodexDTO(itemBase.getCodex(),false);
        for (Option o:itemBase.getOptions()){
            options.add(new OptionDTO(o));
        }
    }

    public ItemBase toItemBase(){
        ItemBase itemBase = new ItemBase();
        itemBase.setId(id);
        itemBase.setName(name);
        itemBase.setDescription(description);
        if(codex!=null)
        itemBase.setCodex(codex.toCodex());
        for (OptionDTO o:options){
            itemBase.getOptions().add(o.toOption());
        }
        return itemBase;
    }

    public CodexDTO getCodex() {
        return codex;
    }

    public void setCodex(CodexDTO codex) {
        this.codex = codex;
    }
    
    
    public  ArrayList<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions( ArrayList<OptionDTO> options) {
        this.options = options;
    }

    public ItemBaseDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     
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
