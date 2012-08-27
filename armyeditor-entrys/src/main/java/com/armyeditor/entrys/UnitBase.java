/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.entrys;

import com.armyeditor.entrys.classes.Description;
//import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Description(textRus="ws")
    private int ws;
    private int bs;
    private int s;
    private int t;
    private int w;
    private int i;
    private int a;
    private int ld;
    private int sv;
    private int cost;
    private List<Option> options=new ArrayList<Option>();
    private UnitType unitType;
    private List<Weapon>  weapons=new ArrayList<Weapon>();
    private List<Item>  items=new ArrayList<Item>();
    private Fraction fraction;

    public UnitBase() {
    }

    public int getA() {
        return a;
    }

    public void setA(int A) {
        this.a = A;
    }

    public int getBs() {
        return bs;
    }

    public void setBs(int bs) {
        this.bs = bs;
    }


    public int getI() {
        return i;
    }

    public void setI(int I) {
        this.i = I;
    }

    public int getLd() {
        return ld;
    }

    public void setLd(int ld) {
        this.ld = ld;
    }

    public int getS() {
        return s;
    }

    public void setS(int S) {
        this.s = S;
    }

    public int getSv() {
        return sv;
    }

    public void setSv(int sv) {
        this.sv = sv;
    }

    public int getT() {
        return t;
    }

    public void setT(int T) {
        this.t = T;
    }

    public int getW() {
        return w;
    }

    public void setW(int W) {
        this.w = W;
    }

    public int getWs() {
        return ws;
    }

    public void setWs(int ws) {
        this.ws = ws;
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

    @Override
    public String toString() {
        return "UnitBase{" + "id=" + id + ", ws=" + ws + ", bs=" + bs + ", s=" + s + ", t=" + t + ", w=" + w + ", i=" + i + ", a=" + a + ", ld=" + ld + ", sv=" + sv + ", cost=" + cost + ", options=" + options + ", unitType=" + unitType + ", weapons=" + weapons + ", items=" + items + ", fraction=" + fraction + '}';
    }
    
    
}
