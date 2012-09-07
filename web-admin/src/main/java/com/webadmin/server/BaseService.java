package com.webadmin.server;

import com.armyeditor.HibernateUtil;
import com.armyeditor.dto.*;
import com.armyeditor.entrys.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webadmin.client.services.ArmyException;
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
    public List<UnitDTO> getUnits() throws ArmyException {
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
    public List<AttackTypeDTO> getAttackTypes() throws ArmyException {
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
    public void delAttackTypes(List<AttackTypeDTO> list)throws ArmyException {
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
    public void addAttackType(AttackTypeDTO a)throws ArmyException  {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        AttackType b = a.toAttackType();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeAttackType(AttackTypeDTO a)throws ArmyException  {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        AttackType b = a.toAttackType();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<SpecialRuleDTO> getSpecialRule() throws ArmyException {
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
    public void delSpecialRules(List<SpecialRuleDTO> list)throws ArmyException  {
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
    public void addSpecialRule(SpecialRuleDTO s)throws ArmyException  {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        SpecialRule b = s.toSpecialRule();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeSpecialRule(SpecialRuleDTO s)throws ArmyException  {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        SpecialRule b = s.toSpecialRule();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<UnitTypeDTO> getUnitType() throws ArmyException {
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
    public void delUnitTypes(List<UnitTypeDTO> list)throws ArmyException  {
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
    public void addUnitType(UnitTypeDTO u)throws ArmyException  {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitType b = u.toUnitType();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeUnitType(UnitTypeDTO u) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitType b = u.toUnitType();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<OptionDTO> getOptionsByUnit(String id) throws ArmyException {
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
    public List<WeaponTypeDTO> getWeaponType() throws ArmyException {
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
    public void delWeaponTypes(List<WeaponTypeDTO> list) throws ArmyException {
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
    public void addWeaponType(WeaponTypeDTO w)throws ArmyException  {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        WeaponType b = w.toWeaponType();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeWeaponType(WeaponTypeDTO w) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        WeaponType b = w.toWeaponType();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<UnitBaseDTO> getUnitBase(String id1) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        Query query = ses.createQuery("select unitbase from UnitBase unitbase where fraction.id=:id");
        query.setParameter("id",id1);
        List<UnitBase> itemlist=query.list();
        List<UnitBaseDTO> unitBaseDTOs = new ArrayList<UnitBaseDTO>();
        for (UnitBase w:itemlist){
            unitBaseDTOs.add(new UnitBaseDTO(w));
        }
        trans.commit();
        ses.close();
        return unitBaseDTOs;
    }

    @Override
    public void delUnitBases(List<UnitBaseDTO> list) throws ArmyException {
        try {
            Session ses = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ses.beginTransaction();
            Query query = ses.createQuery("select squad from SquadPartBase squad where squad.unit.id=:unitid");
            for (UnitBaseDTO w : list) {
                query.setParameter("unitid", w.getId());
                if (!query.list().isEmpty()) {
                    ses.close();
                    throw new ArmyException("cannot delete unit base,SquadPartBase already exist");
                }
            }
            trans.commit();
            trans = ses.beginTransaction();
            List<UnitBase> itemlist = new ArrayList<UnitBase>();
            for (UnitBaseDTO w : list) {
                itemlist.add(w.toUnitBase());
            }

            for (UnitBase a : itemlist) {
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
        } catch (ArmyException ex) {
            ex.printStackTrace();
            throw ex;
        } catch (Throwable tx) {
            tx.printStackTrace();
            throw new ArmyException(tx);
        }
    }

    @Override
    public void addUnitBase(UnitBaseDTO u) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitBase b = u.toUnitBase();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeUnitBase(UnitBaseDTO u) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitBase b = u.toUnitBase();
        List<Item> itemList = new ArrayList<Item>();
        for(Item i:b.getItems()){
            itemList.add((Item) ses.merge(i));
        }
        b.setItems(itemList);
        List<Weapon> weaponList = new ArrayList<Weapon>();
        for(Weapon w:b.getWeapons()) {
            weaponList.add((Weapon) ses.merge(w));
        }
        b.setWeapons(weaponList);
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public UnitBaseDTO getUnitById(String id) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitBase unitBase = (UnitBase)ses.get(UnitBase.class, id);
        UnitBaseDTO unitBaseDTO = new UnitBaseDTO(unitBase);
        trans.commit();
        ses.close();
        return unitBaseDTO;
    }

    @Override
    public List<FractionDTO> getFractions() throws ArmyException {
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
    public List<OptionDTO> getOptions() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select option from Option option");
        List<Option> itemlist=query.list();
        List<OptionDTO> optionDTOList = new ArrayList<OptionDTO>();
        for (Option w:itemlist){
            optionDTOList.add(new OptionDTO(w));
        }
        ses.close();
        return optionDTOList;
    }

    @Override
    public List<WeaponDTO> getWeapons() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select weapon from Weapon weapon");
        List<Weapon> itemlist = query.list();
        List<WeaponDTO> weaponDTOList = new ArrayList<WeaponDTO>();
        for (Weapon w:itemlist){
            weaponDTOList.add(new WeaponDTO(w));
        }
        ses.close();
        return weaponDTOList;
    }

    @Override
    public void delWeapons(List<WeaponDTO> list) throws ArmyException {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        List<Weapon> itemlist = new ArrayList<Weapon>();
        for (WeaponDTO w:list){
            itemlist.add(w.toWeapon());
        }
        for(Weapon a:itemlist){
            a = (Weapon) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addWeapon(WeaponDTO w) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        Weapon b = w.toWeapon();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeWeapon(WeaponDTO w) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        Weapon b = w.toWeapon();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<ItemDTO> getItems() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select item from Item item");
        List<Item> itemlist = query.list();
        List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
        for (Item w:itemlist){
            itemDTOList.add(new ItemDTO(w));
        }
        ses.close();
        return itemDTOList;
    }

    @Override
    public void delItems(List<ItemDTO> list) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        List<Item> itemlist = new ArrayList<Item>();
        for (ItemDTO w:list){
            itemlist.add(w.toItem());
        }
        for(Item a:itemlist){
            a = (Item) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addItem(ItemDTO i) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        Item b = i.toItem();
        ses.save(b);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeItem(ItemDTO i) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        Item b = i.toItem();
        ses.merge(b);
        ses.flush();
        trans.commit();
        ses.close();
    }

}
