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
        smain.append("Squads:\n");
        String offset="\t";
        for(SquadBase s:squads){
             smain.append(s.getId()).append("\n");
             smain.append(offset).append(s.getId()).append("\n");
             smain.append(offset).append(s.getName()).append("\n");
             smain.append(offset).append(s.getDescription()).append("\n");
             smain.append(offset).append("options:[").append("\n");
             offset="\t\t";
             for(Option opt:s.getOptions()){
                 smain.append(offset).append(opt.getId()).append("\n");
                 lst.add(opt);
             }
             offset="\t";
             smain.append(offset).append("]").append("\n");
             parseParts(offset,smain,s.getSquadPartBase());
             
        }
        return smain.toString();
        
    }

}
