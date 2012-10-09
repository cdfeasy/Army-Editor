/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.armyeditor.dto;

import com.armyeditor.entrys.Item;
import com.armyeditor.entrys.Option;
import com.armyeditor.entrys.VenicleBase;
import com.armyeditor.entrys.Weapon;
import com.armyeditor.entrys.classes.Description;
//import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author dmitry
 */
public class VenicleBaseDTO implements Serializable {
    private String id;
    @Description(textRus="ws")
    private int ws;
    private int bs;
    private int s;
    private int w;
    private int i;
    private int a;
    private int front;
    private int side;
    private int rear;
    private int cost;
    private ArrayList<OptionDTO> options=new ArrayList<OptionDTO>();
    private UnitTypeDTO unitType;
    private ArrayList<WeaponDTO> weapons=new ArrayList<WeaponDTO>();
    private ArrayList<ItemDTO> items=new ArrayList<ItemDTO>();
    private CodexDTO codex;

    public VenicleBaseDTO(){

    }

    public VenicleBaseDTO(VenicleBase venicleBase){
        this.id = venicleBase.getId();
        this.ws = venicleBase.getWS();
        this.bs = venicleBase.getBS();
        this.s = venicleBase.getS();
        this.w = venicleBase.getW();
        this.i = venicleBase.getI();
        this.a = venicleBase.getA();
        this.front = venicleBase.getFront();
        this.side = venicleBase.getSide();
        this.rear = venicleBase.getRear();
        this.cost = venicleBase.getCost();
        for (Option o:venicleBase.getOptions()){
            options.add(new OptionDTO(o));
        }
        this.unitType = new UnitTypeDTO(venicleBase.getUnitType());
        for (Weapon w:venicleBase.getWeapons()){
            weapons.add(new WeaponDTO(w));
        }
        for (Item i:venicleBase.getItems()){
            items.add(new ItemDTO(i));
        }
        if(venicleBase.getCodex()!=null)
        this.codex = new CodexDTO(venicleBase.getCodex(),false);
    }

    public VenicleBase toVenicleBase(){
        VenicleBase venicleBase = new VenicleBase();
        venicleBase.setId(id);
        venicleBase.setWS(ws);
        venicleBase.setBS(bs);
        venicleBase.setS(s);
        venicleBase.setW(w);
        venicleBase.setI(i);
        venicleBase.setA(a);
        venicleBase.setFront(front);
        venicleBase.setSide(side);
        venicleBase.setRear(rear);
        venicleBase.setCost(cost);
        for (OptionDTO o:options){
            venicleBase.getOptions().add(o.toOption());
        }
        venicleBase.setUnitType(unitType.toUnitType());
        for (WeaponDTO w:weapons){
            venicleBase.getWeapons().add(w.toWeapon());
        }
        for (ItemDTO i:items){
            venicleBase.getItems().add(i.toItem());
        }
        if(codex!=null)
        venicleBase.setCodex(codex.toCodex(true));
        return venicleBase;
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

    public int getFront() {
        return front;
    }

    public void setFront(int Front) {
        this.front = Front;
    }

    public int getI() {
        return i;
    }

    public void setI(int I) {
        this.i = I;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int Rear) {
        this.rear = Rear;
    }

    public int getS() {
        return s;
    }

    public void setS(int S) {
        this.s = S;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int Side) {
        this.side = Side;
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
    
    
}
