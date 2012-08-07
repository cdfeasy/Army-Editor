/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util. ArrayList;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Dmitry
 */
public class ArmyDTO  implements Serializable {
    private Long id;
    private String name;
    private String description;
    private  ArrayList<SquadDTO> squads=new ArrayList<SquadDTO>();

    public ArmyDTO() {
    }

     
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public  ArrayList<SquadDTO> getSquads() {
        return squads;
    }

    public void setSquads( ArrayList<SquadDTO> squads) {
        this.squads = squads;
    }
    
    public void parseParts(String offset, StringBuilder smain,SquadPartBaseDTO part){
        smain.append(offset).append(part.getId()).append("\n");
        smain.append(offset).append(part.getUnit().toString()).append("\n");
        smain.append(offset).append("minsize=").append(part.getMinSize()).append(" maxsize=").append(part.getMaxSize()).append("\n");
        for(SquadPartBaseDTO localpart:part.getModifications()){
            smain.append("[").append("\n");
            parseParts(offset+"\t",smain,localpart);
            smain.append("]").append("\n");
        }
    }
    public String marshall(){
        StringBuilder smain=new StringBuilder();
        Set<OptionDTO> lst=new HashSet<OptionDTO>();
        StringBuilder sopt=new StringBuilder(); 
        StringBuilder sweapons=new StringBuilder(); 
        StringBuilder sunits=new StringBuilder(); 
        
        smain.append("[\nid=").append(id).append("\n");
        smain.append("name=").append(name).append("\n");
        smain.append("description=").append(description).append("\n");
        smain.append("Squads:[\n");
        String offset="\t";
        for(SquadDTO s:squads){
             smain.append(s.getId()).append("\n");
             smain.append(offset).append(s.getSquadBase().getId()).append("\n");
             smain.append(offset).append(s.getSquadBase().getName()).append("\n");
             smain.append(offset).append(s.getSquadBase().getDescription()).append("\n");
             smain.append(offset).append("options:[").append("\n");
             offset="\t\t";
             for(OptionDTO opt:s.getSquadBase().getOptions()){
                 smain.append(offset).append(opt.getId()).append("\n");
                 lst.add(opt);
             }
             offset="\t";
             smain.append(offset).append("]").append("\n");
             parseParts(offset,smain,s.getSquadBase().getSquadPartBase());
             
        }
		smain.append("]\n");
        return smain.toString();
        
    }

}
