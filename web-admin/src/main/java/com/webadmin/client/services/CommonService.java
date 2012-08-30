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
    List<UnitDTO> getUnits();
    List<ArmorDTO> getArmors();
    void delArmors(List<ArmorDTO> list);
    void addArmor(ArmorDTO a);
    void changeArmor(ArmorDTO a);

    List<AttackTypeDTO> getAttackTypes();
    void delAttackTypes(List<AttackTypeDTO> list);
    void addAttackType(AttackTypeDTO a);
    void changeAttackType(AttackTypeDTO a);

    List<SpecialRuleDTO> getSpecialRule();
    void delSpecialRules(List<SpecialRuleDTO> list);
    void addSpecialRule(SpecialRuleDTO s);
    void changeSpecialRule(SpecialRuleDTO s);

    List<UnitTypeDTO> getUnitType();
    void delUnitTypes(List<UnitTypeDTO> list);
    void addUnitType(UnitTypeDTO u);
    void changeUnitType(UnitTypeDTO u);
    List<OptionDTO> getOptionsByUnit(String id);

    List<WeaponTypeDTO> getWeaponType();
    void delWeaponTypes(List<WeaponTypeDTO> list);
    void addWeaponType(WeaponTypeDTO w);
    void changeWeaponType(WeaponTypeDTO w);

    List<UnitBaseDTO> getUnitBase(String id);
    void delUnitBases(List<UnitBaseDTO> list);
    void addUnitBase(UnitBaseDTO u);
    void changeUnitBase(UnitBaseDTO u);
    UnitBaseDTO getUnitById(String id); //получаем отсюда 3 списка

    List<FractionDTO> getFractions();

    List<OptionDTO> getOptions();
}
