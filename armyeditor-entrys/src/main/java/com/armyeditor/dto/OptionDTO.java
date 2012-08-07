/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.Option;
import javax.persistence.Id;

/**
 *
 * @author Dmitry
 */
public class OptionDTO implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;

    public OptionDTO() {
    }

	public OptionDTO(Option option) {
		this.id = option.getId();
		this.name =  option.getName();
		this.description = option.getDescription();
	}
	
	public Option getOption(){
		Option option=new Option();
		option.setId(id);
		option.setName(name);
		option.setDescription(description);
		return option;
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
		return "Option{" + "id=" + id + ", name=" + name + ", description=" + description + '}';
	}
	
	
}
