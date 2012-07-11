/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import com.armyeditor.entrys.classes.Description;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dmitry
 */@javax.persistence.Entity
public class UnitBase implements java.io.Serializable  {
    private String  id;
    @Description(textRus="WS")
    private int WS;
    private int BS;
    private int S;
    private int T;
    private int W;
    private int I;
    private int A;
    private int LD;
    private int SV;
    private int cost;
    private List<Option> options=new ArrayList<Option>();
    private UnitType unitType;
    private List<Weapon>  weapons=new ArrayList<Weapon>();
    private List<Item>  items=new ArrayList<Item>();
    private Fraction fraction;

    public UnitBase() {
    }

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


    public int getI() {
        return I;
    }

    public void setI(int I) {
        this.I = I;
    }

    public int getLD() {
        return LD;
    }

    public void setLD(int LD) {
        this.LD = LD;
    }

  

    public int getS() {
        return S;
    }

    public void setS(int S) {
        this.S = S;
    }

    public int getSV() {
        return SV;
    }

    public void setSV(int SV) {
        this.SV = SV;
    }

   

    public int getT() {
        return T;
    }

    public void setT(int T) {
        this.T = T;
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
    @ManyToOne( cascade = {CascadeType.REFRESH} )
    @JoinColumn(name="Fraction_fk")
    public Fraction getFraction() {
        return fraction;
    }

    public void setFraction(Fraction fraction) {
        this.fraction = fraction;
    }
    @Id 
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @ManyToMany(cascade = CascadeType.REFRESH)
    public List<Item> getItems() {
        return items;
    }

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
