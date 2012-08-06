/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.dto;

import com.armyeditor.entrys.classes.Description;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author dmitry
 */
public class VenicleBaseDTO implements Serializable {
    private String  id;
    @Description(textRus="WS")
    private int WS;
    private int BS;
    private int S;
    private int W;
    private int I;
    private int A;
    private int Front;
    private int Side;
    private int Rear;
    private int cost;
    private List<OptionDTO> options=new ArrayList<OptionDTO>();
    private UnitTypeDTO unitType;
    private List<WeaponDTO>  weapons=new ArrayList<WeaponDTO>();
    private List<ItemDTO>  items=new ArrayList<ItemDTO>();
    private FractionDTO fraction;

    public int getA() {
        return A;
    }

    public void setA(int A) {
        this.A = A;
    }

    public int getBS() {
        return BS;
    }

    public void setBS(int BS) {
        this.BS = BS;
    }

    public int getFront() {
        return Front;
    }

    public void setFront(int Front) {
        this.Front = Front;
    }

    public int getI() {
        return I;
    }

    public void setI(int I) {
        this.I = I;
    }

    public int getRear() {
        return Rear;
    }

    public void setRear(int Rear) {
        this.Rear = Rear;
    }

    public int getS() {
        return S;
    }

    public void setS(int S) {
        this.S = S;
    }

    public int getSide() {
        return Side;
    }

    public void setSide(int Side) {
        this.Side = Side;
    }

    public int getW() {
        return W;
    }

    public void setW(int W) {
        this.W = W;
    }

    public int getWS() {
        return WS;
    }

    public void setWS(int WS) {
        this.WS = WS;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public FractionDTO getFraction() {
        return fraction;
    }

    public void setFraction(FractionDTO fraction) {
        this.fraction = fraction;
    }
      
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ItemDTO> getItems() {
        return items;
    }

    public void setItems(List<ItemDTO> items) {
        this.items = items;
    }
    
    public List<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDTO> options) {
        this.options = options;
    }

    public UnitTypeDTO getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitTypeDTO unitType) {
        this.unitType = unitType;
    }
    
    public List<WeaponDTO> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<WeaponDTO> weapons) {
        this.weapons = weapons;
    }
    
    
}
