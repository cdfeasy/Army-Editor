/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.UnitType;
import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

/**
 *
 * @author Dmitry
 */
public class UnitTypeDTO implements IsSerializable, java.io.Serializable{
    private String  id;
    private String name;
    private List<OptionDTO> options = new ArrayList<OptionDTO>();;

    public UnitTypeDTO() {
    }

    public UnitTypeDTO(UnitType type) {
        this.id = type.getId();
        this.name = type.getName();
    }
    public UnitType toUnitType(){
        UnitType ut=new UnitType();
        ut.setId(id);
        ut.setName(name);
        return ut;
    }
    
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
