/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.Option;
import com.armyeditor.entrys.UnitType;
import java.util.ArrayList;
import java.util. ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Dmitry
 */
public class UnitTypeDTO implements java.io.Serializable{
    private String  id;
    private String name;
    private  ArrayList<OptionDTO> options = new ArrayList<OptionDTO>();;

    public UnitTypeDTO() {
    }

    public UnitTypeDTO(UnitType type) {
        this.id = type.getId();
        this.name = type.getName();
		for(Option opt:type.getOptions()){
			options.add(new OptionDTO(opt));
		}
    }
    public UnitType toUnitType(){
        UnitType ut=new UnitType();
        ut.setId(id);
        ut.setName(name);
		for(OptionDTO opt:options){
			ut.getOptions().add(opt.getOption());
		}
		
        return ut;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  ArrayList<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions( ArrayList<OptionDTO> options) {
        this.options = options;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
