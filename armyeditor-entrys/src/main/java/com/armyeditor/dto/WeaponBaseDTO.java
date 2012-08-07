/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util. ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dmitry
 */
public class WeaponBaseDTO implements java.io.Serializable  {
    private String  id;
    private WeaponTypeDTO type;
    private String name;
    private String description;
    private String range;
    private String AP;
    private String STR;
    private String fireCount;
    private  ArrayList<OptionDTO> options=new ArrayList<OptionDTO>();
    
    private FractionDTO fraction;

    public FractionDTO getFraction() {
        return fraction;
    }

    public void setFraction(FractionDTO fraction) {
        this.fraction = fraction;
    }

    public WeaponBaseDTO() {
    }

    public String getFireCount() {
        return fireCount;
    }

    public void setFireCount(String fireCount) {
        this.fireCount = fireCount;
    }

    
    public String getAP() {
        return AP;
    }

    public void setAP(String AP) {
        this.AP = AP;
    }

    public String getSTR() {
        return STR;
    }

    public void setSTR(String STR) {
        this.STR = STR;
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
    
    public  ArrayList<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions( ArrayList<OptionDTO> options) {
        this.options = options;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public WeaponTypeDTO getType() {
        return type;
    }

    public void setType(WeaponTypeDTO type) {
        this.type = type;
    }

	@Override
	public String toString() {
		return "WeaponBase{" + "id=" + id + ", type=" + type + ", name=" + name + ", description=" + description + ", range=" + range + ", AP=" + AP + ", STR=" + STR + ", fireCount=" + fireCount + ", options=" + options + '}';
	}
	
	
	

}
