/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.dto;

import com.armyeditor.entrys.Item;
import com.armyeditor.entrys.Option;
import com.armyeditor.entrys.UnitBase;
import com.armyeditor.entrys.Weapon;
import com.armyeditor.entrys.classes.Description;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;

/**
 *
 * @author Dmitry
 */
public class UnitBaseDTO implements java.io.Serializable  {
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
    private String sv;
    private int cost;
    private ArrayList<OptionDTO> options=new ArrayList<OptionDTO>();
    private UnitTypeDTO unitType;
    private ArrayList<WeaponDTO>  weapons=new ArrayList<WeaponDTO>();
    private ArrayList<ItemDTO>  items=new ArrayList<ItemDTO>();
    private CodexDTO codex;

    public UnitBaseDTO() {
    }

    public UnitBaseDTO(UnitBase unit){
        this.id = unit.getId();
        this.ws = unit.getWs();
        this.bs = unit.getBs();
        this.s = unit.getS();
        this.t = unit.getT();
        this.w = unit.getW();
        this.i = unit.getI();
        this.a = unit.getA();
        this.ld = unit.getLd();
        this.sv = unit.getSv();
        this.cost = unit.getCost();
        for (Option o:unit.getOptions()){
            options.add(new OptionDTO(o));
        }
        this.unitType = new UnitTypeDTO(unit.getUnitType());
        for (Weapon w:unit.getWeapons()){
            weapons.add(new WeaponDTO(w));
        }
        for (Item i:unit.getItems()){
            items.add(new ItemDTO(i));
        }
        if(unit.getCodex()!=null)
        this.codex = new CodexDTO(unit.getCodex(),false);
    }

    public UnitBase toUnitBase(){
        UnitBase unitBase = new UnitBase();
        unitBase.setId(id);
        unitBase.setWs(ws);
        unitBase.setBs(bs);
        unitBase.setS(s);
        unitBase.setT(t);
        unitBase.setW(w);
        unitBase.setI(i);
        unitBase.setA(a);
        unitBase.setLd(ld);
        unitBase.setSv(sv);
        unitBase.setCost(cost);
        for (OptionDTO o:options){
            unitBase.getOptions().add(o.toOption());
        }
        unitBase.setUnitType(unitType.toUnitType());
        for (WeaponDTO w:weapons){
            unitBase.getWeapons().add(w.toWeapon());
        }
        for (ItemDTO i:items){
            unitBase.getItems().add(i.toItem());
        }
        if(codex!=null)
        unitBase.setCodex(codex.toCodex(true));
        return unitBase;
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

    public String getSv() {
        return sv;
    }

    public void setSv(String sv) {
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

    public CodexDTO getCodex() {
        return codex;
    }

    public void setCodex(CodexDTO codex) {
        this.codex = codex;
    }
     
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public  ArrayList<ItemDTO> getItems() {
        return items;
    }

    public void setItems( ArrayList<ItemDTO> items) {
        this.items = items;
    }
    
    public  ArrayList<OptionDTO> getOptions() {
        return options;
    }

    public void setOptions( ArrayList<OptionDTO> options) {
        this.options = options;
    }

    public UnitTypeDTO getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitTypeDTO unitType) {
        this.unitType = unitType;
    }
    
    public  ArrayList<WeaponDTO> getWeapons() {
        return weapons;
    }

    public void setWeapons( ArrayList<WeaponDTO> weapons) {
        this.weapons = weapons;
    }

    @Override
    public String toString() {
        return "UnitBase{" + "id=" + id + ", ws=" + ws + ", bs=" + bs + ", s=" + s + ", t=" + t + ", w=" + w + ", i=" + i + ", a=" + a + ", ld=" + ld + ", sv=" + sv + ", cost=" + cost + ", options=" + options + ", unitType=" + unitType + ", weapons=" + weapons + ", items=" + items + ", codex=" + codex + '}';
    }
    
    
}
