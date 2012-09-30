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
    @Description(textRus="WS")
    private int WS;
    private int BS;
    private int S;
    private int W;
    private int I;
    private int A;
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
        this.WS = venicleBase.getWS();
        this.BS = venicleBase.getBS();
        this.S = venicleBase.getS();
        this.W = venicleBase.getW();
        this.I = venicleBase.getI();
        this.A = venicleBase.getA();
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
        venicleBase.setWS(WS);
        venicleBase.setBS(BS);
        venicleBase.setS(S);
        venicleBase.setW(W);
        venicleBase.setI(I);
        venicleBase.setA(A);
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
        venicleBase.setCodex(codex.toCodex());
        return venicleBase;
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

    public int getFront() {
        return front;
    }

    public void setFront(int Front) {
        this.front = Front;
    }

    public int getI() {
        return I;
    }

    public void setI(int I) {
        this.I = I;
    }

    public int getRear() {
        return rear;
    }

    public void setRear(int Rear) {
        this.rear = Rear;
    }

    public int getS() {
        return S;
    }

    public void setS(int S) {
        this.S = S;
    }

    public int getSide() {
        return side;
    }

    public void setSide(int Side) {
        this.side = Side;
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
