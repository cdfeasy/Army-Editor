/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.entrys;

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
public class VenicleBase implements Serializable {
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
    private List<Option> options=new ArrayList<Option>();
    private UnitType unitType;
    private List<Weapon>  weapons=new ArrayList<Weapon>();
    private List<Item>  items=new ArrayList<Item>();
    private Codex codex;

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
    //@JsonIgnore
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="Fraction_fk")
    public Codex getCodex() {
        return codex;
    }

    public void setCodex(Codex codex) {
        this.codex = codex;
    }
     @Id 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Item> getItems() {
        return items;
    }

    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="Unit_fk")
    public void setItems(List<Item> items) {
        this.items = items;
    }
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="UnitType_fk")
    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Weapon> getWeapons() {
        return weapons;
    }

    public void setWeapons(List<Weapon> weapons) {
        this.weapons = weapons;
    }
    
    
}
