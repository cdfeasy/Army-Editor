/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import java.util.ArrayList;
import java.util. ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Dmitry
 */
public class FractionDTO implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;
    private  ArrayList<CodexDTO> codexes =new ArrayList<CodexDTO>();

    public FractionDTO() {
    }

    
   
    public  ArrayList<CodexDTO> getCodexes() {
        return codexes;
    }

    public void setCodexes( ArrayList<CodexDTO> codexes) {
        this.codexes = codexes;
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
