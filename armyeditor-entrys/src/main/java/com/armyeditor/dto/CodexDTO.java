/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

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
 */
public class CodexDTO implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;
    private List<SquadBaseDTO> squads=new ArrayList<SquadBaseDTO>();

    public CodexDTO(){

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
    
    public List<SquadBaseDTO> getSquads() {
        return squads;
    }

    public void setSquads(List<SquadBaseDTO> squads) {
        this.squads = squads;
    }
	
	public void parseUnits(String offset, StringBuilder smain,UnitBaseDTO unit,Set<OptionDTO> lstopt,Set<WeaponBaseDTO> lstweap,Set<UnitBaseDTO> lstunits,Set<ItemBaseDTO> lstitems){
		lstunits.add(unit);
		smain.append(offset).append("unitId=").append(unit.getId()).append("\n");
		smain.append(offset).append("options:[");
		for(OptionDTO opt:unit.getOptions()){
			smain.append(opt.getId()).append(";");
		}
		smain.append("]").append("\n");
		smain.append(offset).append("Weapon:[");
		for(WeaponDTO weap:unit.getWeapons()){
			smain.append(weap.getWeapon().getId()).append(";");
			lstweap.add(weap.getWeapon());
		}
		smain.append("]").append("\n");
		
		smain.append(offset).append("item:[");
		for(ItemDTO weap:unit.getItems()){
			smain.append(weap.getId()).append(";");
			lstitems.add(weap.getItemBase());
		}
		smain.append("]").append("\n");
	}
    
    
    public void parseParts(String offset, StringBuilder smain,SquadPartBaseDTO part,Set<OptionDTO> lstopt,Set<WeaponBaseDTO> lstweap,Set<UnitBaseDTO> lstunits,Set<ItemBaseDTO> lstitems){
        smain.append(offset).append("SquadPartBase=").append(part.getId()).append("\n");
		smain.append(offset).append("maxsize=").append(part.getMaxSize()).append(";minsize=").append(part.getMinSize()).append(";unit=").append(id).append("\n");
		parseUnits(offset+"\t",smain,part.getUnit(),lstopt,lstweap,lstunits,lstitems);
		smain.append(offset).append("weaponSelection [").append("\n");
		for(WeaponSelectionDTO wp:part.getWeaponSelection()){
			
			smain.append(offset+"\t").append("condition=").append(wp.getCondition()).append(";");
			smain.append("weaponlist[");
			for(WeaponDTO w:wp.getWeapon()){
				lstweap.add(w.getWeapon());
				smain.append("weaponBase=").append(w.getWeapon().getId()).append(",");
				smain.append("cost=").append(w.getCost()).append(";");
			}
			smain.append("]").append("\n");
		}
		
		for(ItemSelectionDTO wp:part.getItemSelection()){
			
			smain.append(offset+"\t").append("condition=").append(wp.getCondition()).append(";");
			smain.append("Itemlist[");
			for(ItemDTO w:wp.getItem()){
				lstitems.add(w.getItemBase());
				smain.append("weaponBase=").append(w.getItemBase().getId()).append(",");
				smain.append("cost=").append(w.getCost()).append(";");
			}
			smain.append("]").append("\n");
		}
		
		smain.append(offset).append("]").append("\n");
        for(SquadPartBaseDTO localpart:part.getModifications()){
             parseParts(offset+"\t",smain,localpart,lstopt,lstweap,lstunits,lstitems);
        }
		
    }
    public String marshall(){
        StringBuilder smain=new StringBuilder();
        Set<OptionDTO> lstopt=new HashSet<OptionDTO>();
		Set<WeaponBaseDTO> lstweap=new HashSet<WeaponBaseDTO>();
		Set<UnitBaseDTO> lstunits=new HashSet<UnitBaseDTO>();
		Set<ItemBaseDTO> lstitems=new HashSet<ItemBaseDTO>();
        StringBuilder sopt=new StringBuilder(); 
        StringBuilder sweapons=new StringBuilder(); 
        StringBuilder sunits=new StringBuilder(); 
		StringBuilder sitems=new StringBuilder(); 
        
        smain.append("[\nid=").append(id).append("\n");
        smain.append("name=").append(name).append("\n");
        smain.append("description=").append(description).append("\n");
        smain.append("Squads[\n");
        String offset="\t";
        for(SquadBaseDTO s:squads){
             smain.append(offset).append("SquadBase id=").append(s.getId()).append("\n");
             smain.append(offset).append("SquadBase name=").append(s.getName()).append("\n");
             smain.append(offset).append("SquadBase Description=").append(s.getDescription()).append("\n");
             smain.append(offset).append("options:[");
             offset="\t\t";
             for(OptionDTO opt:s.getOptions()){
                 smain.append(opt.getId()).append(";");
                 lstopt.add(opt);
             }
             offset="\t";
             smain.append("]").append("\n");
             parseParts(offset,smain,s.getSquadPartBase(),lstopt,lstweap,lstunits,lstitems);
             
        }
		smain.append("]");
		for(WeaponBaseDTO w:lstweap){
			for(OptionDTO opt:w.getOptions()){
				lstopt.add(opt);
			}
		}
		for(ItemBaseDTO w:lstitems){
			for(OptionDTO opt:w.getOptions()){
				lstopt.add(opt);
			}
		}
		for(UnitBaseDTO w:lstunits){
			for(OptionDTO opt:w.getOptions()){
				lstopt.add(opt);
			}
		}
		sopt.append("common options[\n");
		for(OptionDTO opt:lstopt){
			sopt.append("\t").append(opt.toString()).append("\n");
		}
		sopt.append("]\n");
		
		sweapons.append("weapons[\n");
		for(WeaponBaseDTO opt:lstweap){
			sweapons.append("\t").append(opt.toString()).append("\n");

		}
		sweapons.append("]\n");
		
		sitems.append("items[\n");
		for(ItemBaseDTO opt:lstitems){
			sitems.append("\t").append(opt.toString()).append("\n");

		}
		sitems.append("]\n");
		smain.append(sopt);
		smain.append(sweapons);
		smain.append(sitems);
		
		
        return smain.toString();
        
    }

}
