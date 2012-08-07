/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

//import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private FractionDTO fraction;
     private  ArrayList<OptionDTO> options=new ArrayList<OptionDTO>();

    public FractionDTO getFraction() {
        return fraction;
    }

    public void setFraction(FractionDTO fraction) {
        this.fraction = fraction;
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
