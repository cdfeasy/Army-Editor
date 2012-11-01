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
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<UnitDTO> unitDTOList = new ArrayList<UnitDTO>();
        try {
            Query query = ses.createQuery("select unit from Unit unit");
            List<Unit> itemlist = query.list();
            for (Unit u : itemlist) {
                unitDTOList.add(new UnitDTO(u));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ses.close();
        return unitDTOList;
    }

    @Override
    public List<AttackTypeDTO> getAttackTypes() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<AttackTypeDTO> attackTypeDTOList = new ArrayList<AttackTypeDTO>();
        try {
            Query query = ses.createQuery("select attacktype from AttackType attacktype").setMaxResults(10);
            List<AttackType> itemlist = query.list();

            for (AttackType a : itemlist) {
                attackTypeDTOList.add(new AttackTypeDTO(a));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ses.close();
        return attackTypeDTOList;
    }

    @Override
    public void delAttackTypes(List<AttackTypeDTO> list) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            List<AttackType> itemlist = new ArrayList<AttackType>();
            for (AttackTypeDTO a : list) {
                itemlist.add(a.toAttackType());
            }
            for (AttackType a : itemlist) {
                a = (AttackType) ses.merge(a);
                ses.delete(a);
            }
            ses.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void addAttackType(AttackTypeDTO a) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            AttackType b = a.toAttackType();
            ses.save(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void changeAttackType(AttackTypeDTO a) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            AttackType b = a.toAttackType();
            ses.merge(b);
            ses.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public List<SpecialRuleDTO> getSpecialRule() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<SpecialRuleDTO> specialRuleDTOList = new ArrayList<SpecialRuleDTO>();
        try {
            Query query = ses.createQuery("select specialrule from SpecialRule specialrule").setMaxResults(10);
            List<SpecialRule> itemlist = query.list();

            for (SpecialRule s : itemlist) {
                specialRuleDTOList.add(new SpecialRuleDTO(s));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ses.close();
        return specialRuleDTOList;
    }

    @Override
    public void delSpecialRules(List<SpecialRuleDTO> list) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            List<SpecialRule> itemlist = new ArrayList<SpecialRule>();
            for (SpecialRuleDTO s : list) {
                itemlist.add(s.toSpecialRule());
            }
            for (SpecialRule a : itemlist) {
                a = (SpecialRule) ses.merge(a);
                ses.delete(a);
            }
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void addSpecialRule(SpecialRuleDTO s) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            SpecialRule b = s.toSpecialRule();
            ses.save(b);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void changeSpecialRule(SpecialRuleDTO s) throws ArmyException {
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
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<UnitTypeDTO> unitTypeDTOList = new ArrayList<UnitTypeDTO>();
        try {
            Query query = ses.createQuery("select unittype from UnitType unittype");
            List<UnitType> itemlist = query.list();
            for (UnitType u : itemlist) {
                unitTypeDTOList.add(new UnitTypeDTO(u));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return unitTypeDTOList;
    }

    @Override
    public void delUnitTypes(List<UnitTypeDTO> list) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            List<UnitType> itemlist = new ArrayList<UnitType>();
            for (UnitTypeDTO u : list) {
                itemlist.add(u.toUnitType());
            }
            for (UnitType a : itemlist) {
                a = (UnitType) ses.merge(a);
                ses.delete(a);
            }
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void addUnitType(UnitTypeDTO u) throws ArmyException {
        try {
            Session ses = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ses.beginTransaction();
            UnitType b = u.toUnitType();
            ses.save(b);
            trans.commit();
            ses.close();
        } catch (Throwable th) {

            th.printStackTrace();
            throw new ArmyException(th);
        }
    }

    @Override
    public void changeUnitType(UnitTypeDTO u) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            UnitType b = u.toUnitType();
            ses.merge(b);
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public List<OptionDTO> getOptionsByUnit(String id) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        List<OptionDTO> itemlist = new ArrayList<OptionDTO>();
        try {
            UnitType unitType = (UnitType) ses.get(UnitType.class, id);
            List<Option> list = unitType.getOptions();
            for (Option o : list) {
                itemlist.add(new OptionDTO(o));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
        return itemlist;
    }

    @Override
    public List<WeaponTypeDTO> getWeaponType() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<WeaponTypeDTO> weaponTypeDTOList = new ArrayList<WeaponTypeDTO>();
        try {
            Query query = ses.createQuery("select weapontype from WeaponType weapontype");
            List<WeaponType> itemlist = query.list();
            for (WeaponType w : itemlist) {
                weaponTypeDTOList.add(new WeaponTypeDTO(w));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return weaponTypeDTOList;
    }

    @Override
    public void delWeaponTypes(List<WeaponTypeDTO> list) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            List<WeaponType> itemlist = new ArrayList<WeaponType>();
            for (WeaponTypeDTO w : list) {
                itemlist.add(w.toWeaponType());
            }
            for (WeaponType a : itemlist) {
                a = (WeaponType) ses.merge(a);
                ses.delete(a);
            }
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void addWeaponType(WeaponTypeDTO w) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            WeaponType b = w.toWeaponType();
            ses.save(b);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void changeWeaponType(WeaponTypeDTO w) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            WeaponType b = w.toWeaponType();
            ses.merge(b);
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public List<UnitBaseDTO> getUnitBase(String id1) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        List<UnitBaseDTO> unitBaseDTOs = new ArrayList<UnitBaseDTO>();
        try {
            Query query = ses.createQuery("select unitbase from UnitBase unitbase where codex.id=:id");
            query.setParameter("id", id1);
            List<UnitBase> itemlist = query.list();
            for (UnitBase w : itemlist) {
                unitBaseDTOs.add(new UnitBaseDTO(w));
            }
        } catch (Throwable e) {
            e.printStackTrace();
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
                a.setCodex(null);
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
        try {
            UnitBase b = u.toUnitBase();
            ses.save(b);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void changeUnitBase(UnitBaseDTO u) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            UnitBase b = u.toUnitBase();
            UnitBase b1 = (UnitBase) ses.get(UnitBase.class, b.getId());
            List<Item> newItemList = b.getItems();
            List<Item> oldItemList = b1.getItems();
            for (Item i : newItemList) {
                if (!oldItemList.contains(i)) ses.persist(i);
            }
            for (Item i : oldItemList) {
                if (!newItemList.contains(i)) ses.delete(i);
            }
            List<Weapon> newWeaponList = b.getWeapons();
            List<Weapon> oldWeaponList = b1.getWeapons();
            for (Weapon w : newWeaponList) {
                if (!oldWeaponList.contains(w)) ses.persist(w);
            }
            for (Weapon w : oldWeaponList) {
                if (!newWeaponList.contains(w)) ses.delete(w);
            }
            List<Option> newOptionList = b.getOptions();
            List<Option> oldOptionList = b1.getOptions();
            for (Option w : newOptionList) {
                if (!oldOptionList.contains(w)) ses.persist(w);
            }
            for (Option w : oldOptionList) {
                if (!newOptionList.contains(w)) ses.delete(w);
            }
            ses.update(b);
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public UnitBaseDTO getUnitById(String id) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        UnitBaseDTO unitBaseDTO = new UnitBaseDTO();
        try {
            UnitBase unitBase = (UnitBase) ses.get(UnitBase.class, id);
            unitBaseDTO = new UnitBaseDTO(unitBase);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
        return unitBaseDTO;
    }

    @Override
    public List<FractionDTO> getFractions() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<FractionDTO> fractionDTOList = new ArrayList<FractionDTO>();
        try {
            Query query = ses.createQuery("select fraction from Fraction fraction");
            List<Fraction> itemlist = query.list();
            for (Fraction w : itemlist) {
                fractionDTOList.add(new FractionDTO(w, false));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return fractionDTOList;
    }

    @Override
    public void delFractions(List<FractionDTO> list) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            List<Fraction> itemlist = new ArrayList<Fraction>();
            for (FractionDTO w : list) {
                itemlist.add(w.toFraction());
            }
            for (Fraction a : itemlist) {
                a = (Fraction) ses.merge(a);
                ses.delete(a);
            }
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void addFraction(FractionDTO f) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            Fraction b = f.toFraction();
            ses.save(b);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void changeFraction(FractionDTO f) throws ArmyException {
        try {
            Session ses = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ses.beginTransaction();
            Fraction b = f.toFraction();
            ses.merge(b);
            ses.flush();
            trans.commit();
            ses.close();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new ArmyException(e);
        }

    }

    @Override
    public List<CodexDTO> getCodexByFraction(String id) throws ArmyException {
        List<CodexDTO> list = new ArrayList<CodexDTO>();
        try {
            Session ses = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ses.beginTransaction();
            Fraction f = (Fraction) ses.get(Fraction.class, id);
            FractionDTO fractionDTO = new FractionDTO(f, true);
            list = fractionDTO.getCodexes();
            trans.commit();
            ses.close();
        } catch (Throwable e) {
            e.printStackTrace();
            throw new ArmyException(e);
        }
        return list;
    }

    @Override
    public List<VenicleBaseDTO> getVenicleBase(String id) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<VenicleBaseDTO> venicleBaseDTOs = new ArrayList<VenicleBaseDTO>();
        try {
            Query query = ses.createQuery("select veniclebase from VenicleBase veniclebase where veniclebase.codex.id=:id1");
            query.setParameter("id1", id);
            List<VenicleBase> itemlist = query.list();
            for (VenicleBase u : itemlist) {
                venicleBaseDTOs.add(new VenicleBaseDTO(u));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return venicleBaseDTOs;
    }

    @Override
    public void delVenicleBase(List<VenicleBaseDTO> list) throws ArmyException {
        try {
            Session ses = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ses.beginTransaction();
            Query query = ses.createQuery("select squadronbase from SquadronBase squadronbase where squadronbase.venicle.id=:unitid");
            for (VenicleBaseDTO w : list) {
                query.setParameter("unitid", w.getId());
                if (!query.list().isEmpty()) {
                    ses.close();
                    throw new ArmyException("cannot delete unit base,SquadPartBase already exist");
                }
            }
            trans.commit();
            trans = ses.beginTransaction();
            List<VenicleBase> itemlist = new ArrayList<VenicleBase>();
            for (VenicleBaseDTO w : list) {
                itemlist.add(w.toVenicleBase());
            }

            for (VenicleBase a : itemlist) {
                a.getOptions().clear();
                a.getWeapons().clear();
                a.getItems().clear();
                a.setCodex(null);
                a = (VenicleBase) ses.merge(a);
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
    public void addVenicleBase(VenicleBaseDTO v) throws ArmyException {
        try {
            Session ses = HibernateUtil.getSessionFactory().openSession();
            Transaction trans = ses.beginTransaction();
            VenicleBase b = v.toVenicleBase();
            ses.save(b);
            trans.commit();
            ses.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void changeVenicleBase(VenicleBaseDTO v) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            VenicleBase b = v.toVenicleBase();
            ses.merge(b);
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public VenicleBaseDTO getVenicleById(String id) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        VenicleBaseDTO venicleBaseDTO = new VenicleBaseDTO();
        try {
            VenicleBase venicleBase = (VenicleBase) ses.get(VenicleBase.class, id);
            venicleBaseDTO = new VenicleBaseDTO(venicleBase);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
        return venicleBaseDTO;
    }

    @Override
    public List<OptionDTO> getOptions() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<OptionDTO> optionDTOList = new ArrayList<OptionDTO>();
        try {
            Query query = ses.createQuery("select option from Option option");
            List<Option> itemlist = query.list();
            for (Option w : itemlist) {
                optionDTOList.add(new OptionDTO(w));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return optionDTOList;
    }

    @Override
    public List<WeaponDTO> getWeapons() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<WeaponDTO> weaponDTOList = new ArrayList<WeaponDTO>();
        try {
            Query query = ses.createQuery("select weapon from Weapon weapon");
            List<Weapon> itemlist = query.list();
            for (Weapon w : itemlist) {
                weaponDTOList.add(new WeaponDTO(w));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return weaponDTOList;
    }

    @Override
    public void delWeapons(List<WeaponDTO> list) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            List<Weapon> itemlist = new ArrayList<Weapon>();
            for (WeaponDTO w : list) {
                itemlist.add(w.toWeapon());
            }
            for (Weapon a : itemlist) {
                a = (Weapon) ses.merge(a);
                ses.delete(a);
            }
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void addWeapon(WeaponDTO w) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            Weapon b = w.toWeapon();
            ses.save(b);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void changeWeapon(WeaponDTO w) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            Weapon b = w.toWeapon();
            ses.merge(b);
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public List<ItemDTO> getItems() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<ItemDTO> itemDTOList = new ArrayList<ItemDTO>();
        try {
            Query query = ses.createQuery("select item from Item item");
            List<Item> itemlist = query.list();
            for (Item w : itemlist) {
                itemDTOList.add(new ItemDTO(w));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return itemDTOList;
    }

    @Override
    public void delItems(List<ItemDTO> list) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            List<Item> itemlist = new ArrayList<Item>();
            for (ItemDTO w : list) {
                itemlist.add(w.toItem());
            }
            for (Item a : itemlist) {
                a = (Item) ses.merge(a);
                ses.delete(a);
            }
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void addItem(ItemDTO i) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            Item b = i.toItem();
            ses.save(b);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void changeItem(ItemDTO i) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            Item b = i.toItem();
            ses.merge(b);
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public List<WeaponBaseDTO> getWeaponBases() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<WeaponBaseDTO> weaponBaseDTOList = new ArrayList<WeaponBaseDTO>();
        try {
            Query query = ses.createQuery("select weaponbase from WeaponBase weaponbase");
            List<WeaponBase> itemlist = query.list();
            for (WeaponBase w : itemlist) {
                weaponBaseDTOList.add(new WeaponBaseDTO(w));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return weaponBaseDTOList;
    }

    @Override
    public void delWeaponBase(List<WeaponBaseDTO> list) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            List<WeaponBase> itemlist = new ArrayList<WeaponBase>();
            for (WeaponBaseDTO w : list) {
                itemlist.add(w.toWeaponBase());
            }
            for (WeaponBase a : itemlist) {
                a = (WeaponBase) ses.merge(a);
                ses.delete(a);
            }
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void addWeaponBase(WeaponBaseDTO w) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            WeaponBase b = w.toWeaponBase();
            ses.save(b);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void changeWeaponBase(WeaponBaseDTO w) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            WeaponBase b = w.toWeaponBase();
            ses.merge(b);
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public List<WeaponBaseDTO> getWeaponBaseById(String id) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<WeaponBaseDTO> weaponBaseDTOs = new ArrayList<WeaponBaseDTO>();
        try {
            Query query = ses.createQuery("select weaponbase from WeaponBase weaponbase where weaponbase.codex.id=:id");
            query.setParameter("id", id);
            List<WeaponBase> itemlist = query.list();
            for (WeaponBase w : itemlist) {
                weaponBaseDTOs.add(new WeaponBaseDTO(w));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return weaponBaseDTOs;
    }

    @Override
    public List<OptionDTO> getOptionsByWeaponBase(String id) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<OptionDTO> list = new ArrayList<OptionDTO>();
        try {
            WeaponBase weaponBase = (WeaponBase) ses.get(WeaponBase.class, id);
            WeaponBaseDTO dto = new WeaponBaseDTO(weaponBase);
            list = dto.getOptions();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<ItemBaseDTO> getItemBases() throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<ItemBaseDTO> itemBaseDTOList = new ArrayList<ItemBaseDTO>();
        try {
            Query query = ses.createQuery("select itembase from ItemBase itembase");
            List<ItemBase> itemlist = query.list();
            for (ItemBase w : itemlist) {
                itemBaseDTOList.add(new ItemBaseDTO(w));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return itemBaseDTOList;
    }

    @Override
    public void delItemBase(List<ItemBaseDTO> list) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            List<ItemBase> itemlist = new ArrayList<ItemBase>();
            for (ItemBaseDTO w : list) {
                itemlist.add(w.toItemBase());
            }
            for (ItemBase a : itemlist) {
                a = (ItemBase) ses.merge(a);
                ses.delete(a);
            }
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void addItemBase(ItemBaseDTO i) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            ItemBase b = i.toItemBase();
            ses.save(b);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void changeItemBase(ItemBaseDTO i) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            ItemBase b = i.toItemBase();
            ses.merge(b);
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public List<ItemBaseDTO> getItemBaseById(String id) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<ItemBaseDTO> itemBaseDTOs = new ArrayList<ItemBaseDTO>();
        try {
            Query query = ses.createQuery("select itembase from ItemBase itembase where itembase.codex.id=:id");
            query.setParameter("id", id);
            List<ItemBase> itemlist = query.list();
            for (ItemBase w : itemlist) {
                itemBaseDTOs.add(new ItemBaseDTO(w));
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return itemBaseDTOs;
    }

    @Override
    public List<OptionDTO> getOptionsByItemBase(String id) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<OptionDTO> list = new ArrayList<OptionDTO>();
        try {
            ItemBase itemBase = (ItemBase) ses.get(ItemBase.class, id);
            ItemBaseDTO dto = new ItemBaseDTO(itemBase);
            list = dto.getOptions();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        ses.close();
        return list;
    }

    @Override
    public List<CodexDTO> getCodexs() throws ArmyException {
        try {
            Session ses = HibernateUtil.getSessionFactory().openSession();
            Query query = ses.createQuery("select codex from Codex codex");
            List<Codex> itemlist = query.list();
            List<CodexDTO> codexDTOList = new ArrayList<CodexDTO>();
            for (Codex w : itemlist) {
                codexDTOList.add(new CodexDTO(w));
            }
            ses.close();
            return codexDTOList;
        } catch (Throwable th) {

            th.printStackTrace();
            throw new ArmyException(th);
        }
    }

    @Override
    public void delCodexs(List<CodexDTO> list) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            List<Codex> itemlist = new ArrayList<Codex>();
            for (CodexDTO w : list) {
                itemlist.add(w.toCodex());
            }
            for (Codex a : itemlist) {
                a = (Codex) ses.merge(a);
                ses.delete(a);
            }
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void addCodex(CodexDTO c) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            Codex b = c.toCodex();
            ses.save(b);
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

    @Override
    public void changeCodex(CodexDTO c) throws ArmyException {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        try {
            Codex b = c.toCodex();
            ses.merge(b);
            ses.flush();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        trans.commit();
        ses.close();
    }

}
