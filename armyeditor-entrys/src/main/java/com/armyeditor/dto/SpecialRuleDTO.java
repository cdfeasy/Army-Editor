/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.SpecialRule;

import javax.persistence.Id;

/**
 *
 * @author Dmitry
 */
public class SpecialRuleDTO  implements java.io.Serializable {
    private String  id;
    private String name;
    private String description;

    public SpecialRuleDTO() {
    }

    public SpecialRuleDTO(SpecialRule specialRule){
        this.id = specialRule.getId();
        this.name = specialRule.getName();
        this.description = specialRule.getDescription();
    }

    public SpecialRule toSpecialRule(){
        SpecialRule specialRule = new SpecialRule();
        specialRule.setId(id);
        specialRule.setName(name);
        specialRule.setDescription(description);
        return specialRule;
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

}
