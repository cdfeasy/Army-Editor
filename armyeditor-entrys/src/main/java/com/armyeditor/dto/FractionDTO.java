/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.Codex;
import com.armyeditor.entrys.Fraction;

import java.util.ArrayList;

/**
 *
 * @author Dmitry
 */
public class FractionDTO implements java.io.Serializable  {
    private String  id;
    private String name;
    private String description;
    private ArrayList<CodexDTO> codexes =new ArrayList<CodexDTO>();

    public FractionDTO() {
    }

    public FractionDTO(Fraction fraction, boolean isRoot) {
        this.id = fraction.getId();
        this.name = fraction.getName();
        this.description = fraction.getDescription();
        if (isRoot) {
            for (Codex c : fraction.getCodexes()) {
                codexes.add(new CodexDTO(c));
            }
        }
    }

    public Fraction toFraction(){
        Fraction fraction = new Fraction();
        fraction.setId(id);
        fraction.setName(name);
        fraction.setDescription(description);
        for (CodexDTO c:codexes){
            fraction.getCodexes().add(c.toCodex(true)); //<--- убираем рекурсивность
        }
        return fraction;
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
