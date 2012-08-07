/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util. ArrayList;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Dmitry
 */
public class SquadBaseDTO  implements java.io.Serializable {
    private String  id;
    private SquadPartBaseDTO squadPartBase;
    private  ArrayList<OptionDTO> options=new ArrayList<OptionDTO>();
    private String name;
    private String description;
	/**
	 * 0-hq,1-Elite,2-troops,3-fast,4-heavy,5-fortifications
	 */
	private int foqType;

    public SquadBaseDTO() {
    }

	public int getFoqType() {
		return foqType;
	}

	public void setFoqType(int foqType) {
		this.foqType = foqType;
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

    public SquadPartBaseDTO getSquadPartBase() {
        return squadPartBase;
    }

    public void setSquadPartBase(SquadPartBaseDTO squadPartBase) {
        this.squadPartBase = squadPartBase;
    }

}
