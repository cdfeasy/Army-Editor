/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class WeaponBase implements java.io.Serializable  {
    private String  id;
    private WeaponType type;
    private String name;
    private String description;
    private String range;
    private String ap;
    private String str;
    private String fireCount;
    private List<Option> options=new ArrayList<Option>();
    private Codex codex;

   // @JsonIgnore
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="Codex_fk")
    public Codex getCodex() {
        return codex;
    }

    public void setCodex(Codex codex) {
        this.codex = codex;
    }

    public WeaponBase() {
    }

    public String getFireCount() {
        return fireCount;
    }

    public void setFireCount(String fireCount) {
        this.fireCount = fireCount;
    }

    
    public String getAp() {
        return ap;
    }

    public void setAp(String ap) {
        this.ap = ap;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
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

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="WeaponType_fk")
    public WeaponType getType() {
        return type;
    }

    public void setType(WeaponType type) {
        this.type = type;
    }

	@Override
	public String toString() {
		return "WeaponBase{" + "id=" + id + ", type=" + type + ", name=" + name + ", description=" + description + ", range=" + range + ", ap=" + ap + ", str=" + str + ", fireCount=" + fireCount + ", options=" + options + '}';
	}
	
	
	

}
