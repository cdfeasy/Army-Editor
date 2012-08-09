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
import java.util. ArrayList;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

/**
 *
 * @author Dmitry
 */
public class UnitBaseDTO implements java.io.Serializable  {
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
    private  ArrayList<OptionDTO> options=new ArrayList<OptionDTO>();
    private UnitTypeDTO unitType;
    private  ArrayList<WeaponDTO>  weapons=new ArrayList<WeaponDTO>();
    private  ArrayList<ItemDTO>  items=new ArrayList<ItemDTO>();
    private FractionDTO fraction;

    public UnitBaseDTO() {
    }

    public UnitBaseDTO(UnitBase unit){
        this.id = unit.getId();
        this.WS = unit.getWS();
        this.BS = unit.getBS();
        this.S = unit.getS();
        this.T = unit.getT();
        this.W = unit.getW();
        this.I = unit.getI();
        this.A = unit.getA();
        this.LD = unit.getLD();
        this.SV = unit.getSV();
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
        this.fraction = new FractionDTO(unit.getFraction());
    }

    public UnitBase toUnitBase(){
        UnitBase unitBase = new UnitBase();
        unitBase.setId(id);
        unitBase.setWS(WS);
        unitBase.setBS(BS);
        unitBase.setS(S);
        unitBase.setT(T);
        unitBase.setW(W);
        unitBase.setI(I);
        unitBase.setA(A);
        unitBase.setLD(LD);
        unitBase.setSV(SV);
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
        unitBase.setFraction(fraction.toFraction());
        return unitBase;
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
        return "UnitBase{" + "id=" + id + ", WS=" + WS + ", BS=" + BS + ", S=" + S + ", T=" + T + ", W=" + W + ", I=" + I + ", A=" + A + ", LD=" + LD + ", SV=" + SV + ", cost=" + cost + ", options=" + options + ", unitType=" + unitType + ", weapons=" + weapons + ", items=" + items + ", fraction=" + fraction + '}';
    }
    
    
}
