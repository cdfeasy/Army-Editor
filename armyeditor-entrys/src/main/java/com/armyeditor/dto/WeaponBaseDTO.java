/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.armyeditor.entrys.Option;
import com.armyeditor.entrys.WeaponBase;

import java.util.ArrayList;

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
    private String ap;
    private String str;
    private String fireCount;
    private ArrayList<OptionDTO> options=new ArrayList<OptionDTO>();
    private CodexDTO codex;

    public WeaponBaseDTO() {
    }

    public WeaponBaseDTO(WeaponBase weaponBase){
        this.id = weaponBase.getId();
        this.type = new WeaponTypeDTO(weaponBase.getType());
        this.name = weaponBase.getName();
        this.description = weaponBase.getDescription();
        this.range = weaponBase.getRange();
        this.ap = weaponBase.getAp();
        this.str = weaponBase.getStr();
        this.fireCount = weaponBase.getFireCount();
        for(Option o:weaponBase.getOptions()){
            options.add(new OptionDTO(o));
        }
        if(weaponBase.getCodex()!=null)
        this.codex = new CodexDTO(weaponBase.getCodex(),false);
        this.type = new WeaponTypeDTO(weaponBase.getType());
    }

    public WeaponBase toWeaponBase(){
        WeaponBase w = new WeaponBase();
        w.setId(id);
        w.setType(type.toWeaponType());
        w.setName(name);
        w.setDescription(description);
        w.setRange(range);
        w.setAp(ap);
        w.setStr(str);
        w.setFireCount(fireCount);
        for(OptionDTO o:options){
            w.getOptions().add(o.toOption());
        }
         if(codex!=null)
        w.setCodex(codex.toCodex(true));
        return w;
    }

    public CodexDTO getCodex() {
        return codex;
    }

    public void setCodex(CodexDTO codex) {
        this.codex = codex;
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
		return "WeaponBase{" + "id=" + id + ", type=" + type + ", name=" + name + ", description=" + description + ", range=" + range + ", ap=" + ap + ", str=" + str + ", fireCount=" + fireCount + ", options=" + options + '}';
	}
	
	
	

}
