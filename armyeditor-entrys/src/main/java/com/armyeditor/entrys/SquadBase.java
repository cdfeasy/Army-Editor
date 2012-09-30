/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
 */@javax.persistence.Entity
public class SquadBase  implements java.io.Serializable {
    private String  id;
    private SquadPartBase squadPartBase;
    private List<Option> options=new ArrayList<Option>();
    private String name;
    private String description;
    private Codex codex;
	/**
	 * 0-hq,1-Elite,2-troops,3-fast,4-heavy,5-fortifications
	 */
	private int foqType;

    public SquadBase() {
    }
    @ManyToOne( cascade = {CascadeType.MERGE, CascadeType.REFRESH} )
    @JoinColumn(name="Codex_fk")
    public Codex getCodex() {
        return codex;
    }

    public void setCodex(Codex codex) {
        this.codex = codex;
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
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="SquadPartBase_fk")
    public SquadPartBase getSquadPartBase() {
        return squadPartBase;
    }

    public void setSquadPartBase(SquadPartBase squadPartBase) {
        this.squadPartBase = squadPartBase;
    }

}
