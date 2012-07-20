/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class Codex implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;
    private List<SquadBase> squads=new ArrayList<SquadBase>();

    public Codex(){

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
    @OneToMany(cascade=CascadeType.REFRESH)
    @JoinColumn(name="Codex_ID")
    public List<SquadBase> getSquads() {
        return squads;
    }

    public void setSquads(List<SquadBase> squads) {
        this.squads = squads;
    }
	
	public void parseUnits(String offset, StringBuilder smain,UnitBase unit,Set<Option> lstopt,Set<WeaponBase> lstweap,Set<UnitBase> lstunits,Set<ItemBase> lstitems){
		lstunits.add(unit);
		smain.append(offset).append("unitId=").append(unit.getId()).append("\n");
		smain.append(offset).append("options:[");
		for(Option opt:unit.getOptions()){
			smain.append(opt.getId()).append(";");
		}
		smain.append("]").append("\n");
		smain.append(offset).append("Weapon:[");
		for(Weapon weap:unit.getWeapons()){
			smain.append(weap.getWeapon().getId()).append(";");
			lstweap.add(weap.getWeapon());
		}
		smain.append("]").append("\n");
		
		smain.append(offset).append("item:[");
		for(Item weap:unit.getItems()){
			smain.append(weap.getId()).append(";");
			lstitems.add(weap.getItemBase());
		}
		smain.append("]").append("\n");
	}
    
    
    public void parseParts(String offset, StringBuilder smain,SquadPartBase part,Set<Option> lstopt,Set<WeaponBase> lstweap,Set<UnitBase> lstunits,Set<ItemBase> lstitems){
        smain.append(offset).append("SquadPartBase=").append(part.getId()).append("\n");
		smain.append(offset).append("maxsize=").append(part.getMaxSize()).append(";minsize=").append(part.getMinSize()).append(";unit=").append(id).append("\n");
		parseUnits(offset+"\t",smain,part.getUnit(),lstopt,lstweap,lstunits,lstitems);
		smain.append(offset).append("weaponSelection [").append("\n");
		for(WeaponSelection wp:part.getWeaponSelection()){
			
			smain.append(offset+"\t").append("condition=").append(wp.getCondition()).append(";");
			smain.append("weaponlist[");
			for(Weapon w:wp.getWeapon()){
				lstweap.add(w.getWeapon());
				smain.append("weaponBase=").append(w.getWeapon().getId()).append(",");
				smain.append("cost=").append(w.getCost()).append(";");
			}
			smain.append("]").append("\n");
		}
		
		for(ItemSelection wp:part.getItemSelection()){
			
			smain.append(offset+"\t").append("condition=").append(wp.getCondition()).append(";");
			smain.append("Itemlist[");
			for(Item w:wp.getItem()){
				lstitems.add(w.getItemBase());
				smain.append("weaponBase=").append(w.getItemBase().getId()).append(",");
				smain.append("cost=").append(w.getCost()).append(";");
			}
			smain.append("]").append("\n");
		}
		
		smain.append(offset).append("]").append("\n");
        for(SquadPartBase localpart:part.getModifications()){
             parseParts(offset+"\t",smain,localpart,lstopt,lstweap,lstunits,lstitems);
        }
		
    }
    public String marshall(){
        StringBuilder smain=new StringBuilder();
        Set<Option> lstopt=new HashSet<Option>();
		Set<WeaponBase> lstweap=new HashSet<WeaponBase>();
		Set<UnitBase> lstunits=new HashSet<UnitBase>();
		Set<ItemBase> lstitems=new HashSet<ItemBase>();
        StringBuilder sopt=new StringBuilder(); 
        StringBuilder sweapons=new StringBuilder(); 
        StringBuilder sunits=new StringBuilder(); 
		StringBuilder sitems=new StringBuilder(); 
        
        smain.append("[\nid=").append(id).append("\n");
        smain.append("name=").append(name).append("\n");
        smain.append("description=").append(description).append("\n");
        smain.append("Squads[\n");
        String offset="\t";
        for(SquadBase s:squads){
             smain.append(offset).append("SquadBase id=").append(s.getId()).append("\n");
             smain.append(offset).append("SquadBase name=").append(s.getName()).append("\n");
             smain.append(offset).append("SquadBase Description=").append(s.getDescription()).append("\n");
             smain.append(offset).append("options:[");
             offset="\t\t";
             for(Option opt:s.getOptions()){
                 smain.append(opt.getId()).append(";");
                 lstopt.add(opt);
             }
             offset="\t";
             smain.append("]").append("\n");
             parseParts(offset,smain,s.getSquadPartBase(),lstopt,lstweap,lstunits,lstitems);
             
        }
		smain.append("]");
		for(WeaponBase w:lstweap){
			for(Option opt:w.getOptions()){
				lstopt.add(opt);
			}
		}
		for(ItemBase w:lstitems){
			for(Option opt:w.getOptions()){
				lstopt.add(opt);
			}
		}
		for(UnitBase w:lstunits){
			for(Option opt:w.getOptions()){
				lstopt.add(opt);
			}
		}
		sopt.append("common options[\n");
		for(Option opt:lstopt){
			sopt.append("\t").append(opt.toString()).append("\n");
		}
		sopt.append("]\n");
		
		sweapons.append("weapons[\n");
		for(WeaponBase opt:lstweap){
			sweapons.append("\t").append(opt.toString()).append("\n");

		}
		sweapons.append("]\n");
		
		sitems.append("items[\n");
		for(ItemBase opt:lstitems){
			sitems.append("\t").append(opt.toString()).append("\n");

		}
		sitems.append("]\n");
		smain.append(sopt);
		smain.append(sweapons);
		smain.append(sitems);
		
		
        return smain.toString();
        
    }

}
