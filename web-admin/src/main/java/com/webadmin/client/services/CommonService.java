package com.webadmin.client.services;

import com.armyeditor.dto.*;
import com.armyeditor.entrys.*;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 31.03.12
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
@RemoteServiceRelativePath("server/BaseService")
public interface CommonService extends RemoteService {
    List<UnitDTO> getUnits() throws ArmyException;
   
    List<AttackTypeDTO> getAttackTypes() throws ArmyException;
    void delAttackTypes(List<AttackTypeDTO> list) throws ArmyException;
    void addAttackType(AttackTypeDTO a) throws ArmyException;
    void changeAttackType(AttackTypeDTO a) throws ArmyException;

    List<SpecialRuleDTO> getSpecialRule() throws ArmyException;
    void delSpecialRules(List<SpecialRuleDTO> list) throws ArmyException;
    void addSpecialRule(SpecialRuleDTO s) throws ArmyException;
    void changeSpecialRule(SpecialRuleDTO s) throws ArmyException;

    List<UnitTypeDTO> getUnitType() throws ArmyException;
    void delUnitTypes(List<UnitTypeDTO> list) throws ArmyException;
    void addUnitType(UnitTypeDTO u) throws ArmyException;
    void changeUnitType(UnitTypeDTO u) throws ArmyException;
    List<OptionDTO> getOptionsByUnit(String id) throws ArmyException;

    List<WeaponTypeDTO> getWeaponType() throws ArmyException;
    void delWeaponTypes(List<WeaponTypeDTO> list) throws ArmyException;
    void addWeaponType(WeaponTypeDTO w) throws ArmyException;
    void changeWeaponType(WeaponTypeDTO w) throws ArmyException;

    List<UnitBaseDTO> getUnitBase(String id) throws ArmyException;
    void delUnitBases(List<UnitBaseDTO> list) throws ArmyException;
    void addUnitBase(UnitBaseDTO u) throws ArmyException;
    void changeUnitBase(UnitBaseDTO u) throws ArmyException;
    UnitBaseDTO getUnitById(String id) throws ArmyException; //получаем отсюда 3 списка

    List<OptionDTO> getOptions() throws ArmyException;

    List<WeaponDTO> getWeapons() throws ArmyException;
    void delWeapons(List<WeaponDTO> list) throws ArmyException;
    void addWeapon(WeaponDTO w) throws ArmyException;
    void changeWeapon(WeaponDTO w) throws ArmyException;

    List<ItemDTO> getItems() throws ArmyException;
    void delItems(List<ItemDTO> list) throws ArmyException;
    void addItem(ItemDTO i) throws ArmyException;
    void changeItem(ItemDTO i) throws ArmyException;

    List<WeaponBaseDTO> getWeaponBases() throws ArmyException;

    List<ItemBaseDTO> getItemBases() throws ArmyException;

    List<CodexDTO> getCodexs() throws ArmyException;
    void delCodexs(List<CodexDTO> list) throws ArmyException;
    void addCodex(CodexDTO c) throws ArmyException;
    void changeCodex(CodexDTO c) throws ArmyException;

    List<FractionDTO> getFractions() throws ArmyException;
    void delFractions(List<FractionDTO> list) throws ArmyException;
    void addFraction(FractionDTO f) throws ArmyException;
    void changeFraction(FractionDTO f) throws ArmyException;
    List<CodexDTO> getCodexByFraction(String id) throws ArmyException;

    List<VenicleBaseDTO> getVenicleBase(String id) throws ArmyException;
    void delVenicleBase(List<VenicleBaseDTO> list) throws ArmyException;
    void addVenicleBase(VenicleBaseDTO v) throws ArmyException;
    void changeVenicleBase(VenicleBaseDTO v) throws ArmyException;
    VenicleBaseDTO getVenicleById(String id) throws ArmyException;
}
