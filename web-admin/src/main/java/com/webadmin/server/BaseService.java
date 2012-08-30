package com.webadmin.server;

import com.armyeditor.HibernateUtil;
import com.armyeditor.dto.*;
import com.armyeditor.entrys.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webadmin.client.services.CommonService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 31.03.12
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
public class BaseService extends RemoteServiceServlet implements CommonService {
    @Override
    public List<UnitDTO> getUnits() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select unit from Unit unit");
        List<Unit> itemlist=query.list();
        List<UnitDTO> unitDTOList = new ArrayList<UnitDTO>();
        for (Unit u:itemlist){
            unitDTOList.add(new UnitDTO(u));
        }
        ses.close();
        return unitDTOList;
    }

    @Override
    public List<ArmorDTO> getArmors() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select armor from Armor armor");
        List<Armor> itemlist=query.list();
        List<ArmorDTO> armorDTOList = new ArrayList<ArmorDTO>();
        for (Armor a:itemlist){
            armorDTOList.add(new ArmorDTO(a));
        }
        ses.close();
        return armorDTOList;
    }

    @Override
    public void delArmors(List<ArmorDTO> list) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        List<Armor> itemlist = new ArrayList<Armor>();
        for (ArmorDTO a:list){
            itemlist.add(a.toArmor());
        }
        for(Armor a:itemlist){
            a= (Armor) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addArmor(ArmorDTO a) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        Armor b = a.toArmor();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeArmor(ArmorDTO a) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        Armor b = a.toArmor();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<AttackTypeDTO> getAttackTypes() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select attacktype from AttackType attacktype").setMaxResults(10);
        List<AttackType> itemlist=query.list();
        List<AttackTypeDTO> attackTypeDTOList = new ArrayList<AttackTypeDTO>();
        for (AttackType a:itemlist){
            attackTypeDTOList.add(new AttackTypeDTO(a));
        }
        ses.close();
        return attackTypeDTOList;
    }

    @Override
    public void delAttackTypes(List<AttackTypeDTO> list) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        List<AttackType> itemlist = new ArrayList<AttackType>();
        for (AttackTypeDTO a:list){
            itemlist.add(a.toAttackType());
        }
        for(AttackType a:itemlist){
            a= (AttackType) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addAttackType(AttackTypeDTO a) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        AttackType b = a.toAttackType();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeAttackType(AttackTypeDTO a) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        AttackType b = a.toAttackType();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<SpecialRuleDTO> getSpecialRule() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select specialrule from SpecialRule specialrule").setMaxResults(10);
        List<SpecialRule> itemlist=query.list();
        List<SpecialRuleDTO> specialRuleDTOList = new ArrayList<SpecialRuleDTO>();
        for (SpecialRule s:itemlist){
            specialRuleDTOList.add(new SpecialRuleDTO(s));
        }
        ses.close();
        return specialRuleDTOList;
    }

    @Override
    public void delSpecialRules(List<SpecialRuleDTO> list) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        List<SpecialRule> itemlist = new ArrayList<SpecialRule>();
        for (SpecialRuleDTO s:list){
            itemlist.add(s.toSpecialRule());
        }
        for(SpecialRule a:itemlist){
            a = (SpecialRule) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addSpecialRule(SpecialRuleDTO s) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        SpecialRule b = s.toSpecialRule();
        ses.save(s);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeSpecialRule(SpecialRuleDTO s) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        SpecialRule b = s.toSpecialRule();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<UnitTypeDTO> getUnitType() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select unittype from UnitType unittype");
        List<UnitType> itemlist=query.list();
        List<UnitTypeDTO> unitTypeDTOList = new ArrayList<UnitTypeDTO>();
        for (UnitType u:itemlist){
            unitTypeDTOList.add(new UnitTypeDTO(u));
        }
        ses.close();
        return unitTypeDTOList;
    }

    @Override
    public void delUnitTypes(List<UnitTypeDTO> list) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        List<UnitType> itemlist = new ArrayList<UnitType>();
        for (UnitTypeDTO u:list){
            itemlist.add(u.toUnitType());
        }
        for(UnitType a:itemlist){
            a = (UnitType) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addUnitType(UnitTypeDTO u) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitType b = u.toUnitType();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeUnitType(UnitTypeDTO u) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitType b = u.toUnitType();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<OptionDTO> getOptionsByUnit(String id) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitType unitType = (UnitType)ses.get(UnitType.class, id);
        List<Option> list = unitType.getOptions();
        List<OptionDTO> itemlist = new ArrayList<OptionDTO>();
        for (Option o:list){
            itemlist.add(new OptionDTO(o));
        }
        return itemlist;
    }

    @Override
    public List<WeaponTypeDTO> getWeaponType() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select weapontype from WeaponType weapontype");
        List<WeaponType> itemlist=query.list();
        List<WeaponTypeDTO> weaponTypeDTOList = new ArrayList<WeaponTypeDTO>();
        for (WeaponType w:itemlist){
            weaponTypeDTOList.add(new WeaponTypeDTO(w));
        }
        ses.close();
        return weaponTypeDTOList;
    }

    @Override
    public void delWeaponTypes(List<WeaponTypeDTO> list) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        List<WeaponType> itemlist = new ArrayList<WeaponType>();
        for (WeaponTypeDTO w:list){
            itemlist.add(w.toWeaponType());
        }
        for(WeaponType a:itemlist){
            a = (WeaponType) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addWeaponType(WeaponTypeDTO w) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        WeaponType b = w.toWeaponType();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeWeaponType(WeaponTypeDTO w) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        WeaponType b = w.toWeaponType();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<UnitBaseDTO> getUnitBase(String id1) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        Query query = ses.createQuery("select unitbase from UnitBase unitbase where fraction.id=:id");
        query.setParameter("id",id1);
        List<UnitBase> itemlist=query.list();
        List<UnitBaseDTO> unitBaseDTOs = new ArrayList<UnitBaseDTO>();
        for (UnitBase w:itemlist){
            unitBaseDTOs.add(new UnitBaseDTO(w));
        }
        ses.close();
        return unitBaseDTOs;
    }

    @Override
    public void delUnitBases(List<UnitBaseDTO> list) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        List<UnitBase> itemlist = new ArrayList<UnitBase>();
        for (UnitBaseDTO w:list){
            itemlist.add(w.toUnitBase());
        }

        for(UnitBase a:itemlist){
            a.getOptions().clear();
            a.getWeapons().clear();
            a.getItems().clear();
            a.setFraction(null);
            ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addUnitBase(UnitBaseDTO u) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitBase b = u.toUnitBase();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeUnitBase(UnitBaseDTO u) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitBase b = u.toUnitBase();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public UnitBaseDTO getUnitById(String id) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitBase unitBase = (UnitBase)ses.get(UnitBase.class, id);
        UnitBaseDTO unitBaseDTO = new UnitBaseDTO(unitBase);
        return unitBaseDTO;
    }

    @Override
    public List<FractionDTO> getFractions() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select fraction from Fraction fraction");
        List<Fraction> itemlist=query.list();
        List<FractionDTO> fractionDTOList = new ArrayList<FractionDTO>();
        for (Fraction w:itemlist){
            fractionDTOList.add(new FractionDTO(w,false));
        }
        ses.close();
        return fractionDTOList;
    }

    @Override
    public List<OptionDTO> getOptions() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select option from Option option");
        List<Option> itemlist=query.list();
        List<OptionDTO> optionDTOList = new ArrayList<OptionDTO>();
        for (Option w:itemlist){
            optionDTOList.add(new OptionDTO(w));
        }
        ses.close();
        return optionDTOList;
    }

}
