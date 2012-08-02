package com.webadmin.server;

import com.armyeditor.HibernateUtil;
import com.armyeditor.entrys.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webadmin.client.services.CommonService;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    public List<Unit> getUnits() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select unit from Unit unit").setMaxResults(10);
        List<Unit> itemlist=query.list();
        ses.close();
        return itemlist;
    }

    @Override
    public List<Armor> getArmors() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select armor from Armor armor").setMaxResults(10);
        List<Armor> itemlist=query.list();
        ses.close();
        return itemlist;
    }

    @Override
    public void delArmors(List<Armor> list) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        for(Armor a:list){
            a= (Armor) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addArmor(Armor a) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        ses.save(a);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeArmor(Armor a) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        ses.merge(a);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<AttackType> getAttackTypes() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select attacktype from AttackType attacktype").setMaxResults(10);
        List<AttackType> itemlist=query.list();
        ses.close();
        return itemlist;
    }

    @Override
    public void delAttackTypes(List<AttackType> list) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        for(AttackType a:list){
            a= (AttackType) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addAttackType(AttackType a) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        ses.save(a);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeAttackType(AttackType a) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        ses.merge(a);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<SpecialRule> getSpecialRule() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select specialrule from SpecialRule specialrule").setMaxResults(10);
        List<SpecialRule> itemlist=query.list();
        ses.close();
        return itemlist;
    }

    @Override
    public void delSpecialRules(List<SpecialRule> list) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        for(SpecialRule a:list){
            a = (SpecialRule) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addSpecialRule(SpecialRule s) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        ses.save(s);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeSpecialRule(SpecialRule s) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        ses.merge(s);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<UnitType> getUnitType() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select unittype from UnitType unittype").setMaxResults(10);
        List<UnitType> itemlist=query.list();
        ses.close();
        return itemlist;
    }

    @Override
    public void delUnitTypes(List<UnitType> list) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        for(UnitType a:list){
            a = (UnitType) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addUnitType(UnitType u) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        ses.save(u);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeUnitType(UnitType u) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        ses.merge(u);
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public List<WeaponType> getWeaponType() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select weapontype from WeaponType weapontype").setMaxResults(10);
        List<WeaponType> itemlist=query.list();
        ses.close();
        return itemlist;
    }

    @Override
    public void delWeaponTypes(List<WeaponType> list) {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
        for(WeaponType a:list){
            a = (WeaponType) ses.merge(a);
            ses.delete(a);
        }
        ses.flush();
        trans.commit();
        ses.close();
    }

    @Override
    public void addWeaponType(WeaponType w) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        ses.save(w);
        trans.commit();
        ses.close();
    }

    @Override
    public void changeWeaponType(WeaponType w) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = ses.beginTransaction();
        ses.merge(w);
        ses.flush();
        trans.commit();
        ses.close();
    }

}
