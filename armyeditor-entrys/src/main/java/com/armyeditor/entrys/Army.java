/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class Army  implements Serializable {
    private Long id;
    private String name;
    private String description;
    private List<Squad> squads=new ArrayList<Squad>();

    public Army() {
    }

    @Id @javax.persistence.GeneratedValue
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
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Squad> getSquads() {
        return squads;
    }

    public void setSquads(List<Squad> squads) {
        this.squads = squads;
    }
    
    public void parseParts(String offset, StringBuilder smain,SquadPartBase part){
        smain.append(offset).append(part.getId()).append("\n");
        smain.append(offset).append(part.getUnit().toString()).append("\n");
        smain.append(offset).append("minsize=").append(part.getMinSize()).append(" maxsize=").append(part.getMaxSize()).append("\n");
        for(SquadPartBase localpart:part.getModifications()){
            smain.append("[").append("\n");
            parseParts(offset+"\t",smain,localpart);
            smain.append("]").append("\n");
        }
    }
    public String marshall(){
        StringBuilder smain=new StringBuilder();
        Set<Option> lst=new HashSet<Option>();
        StringBuilder sopt=new StringBuilder(); 
        StringBuilder sweapons=new StringBuilder(); 
        StringBuilder sunits=new StringBuilder(); 
        
        smain.append("[\nid=").append(id).append("\n");
        smain.append("name=").append(name).append("\n");
        smain.append("description=").append(description).append("\n");
        smain.append("Squads:[\n");
        String offset="\t";
        for(Squad s:squads){
             smain.append(s.getId()).append("\n");
             smain.append(offset).append(s.getSquadBase().getId()).append("\n");
             smain.append(offset).append(s.getSquadBase().getName()).append("\n");
             smain.append(offset).append(s.getSquadBase().getDescription()).append("\n");
             smain.append(offset).append("options:[").append("\n");
             offset="\t\t";
             for(Option opt:s.getSquadBase().getOptions()){
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
