/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dmitry
 */

import com.armyeditor.HibernateUtil;
import com.armyeditor.entrys.Fraction;
import com.armyeditor.entrys.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

public class testDb {
    @Before
    public void init() throws IOException{
         Session ses= HibernateUtil.getSessionFactory().openSession();
         Fraction f=new Fraction();
         f.setName("orks");
         f.setId("orks");
         ses.save(f);
         
         final Codex c=new Codex();
         c.setDescription("ork 5 red");
         c.setId("ork 5 red");
         f.setCodexes(new ArrayList<Codex>(){{add(c);}});
         c.setName("ork 5 red");
         ses.save(c);
         
         final SquadBase squadboys =new SquadBase();
         squadboys.setId("squadboys");
         c.setSquads(new ArrayList<SquadBase>(){{add(squadboys);}});
         squadboys.setDescription("ork boys");
         squadboys.setName("ork boys");
         
         final Option waagh=new Option();
         final Option assaultGrenades=new Option();
         final Option mob=new Option();
         waagh.setId("waagh");
         assaultGrenades.setId("assaultGrenades");
         mob.setId("mob");
         
         waagh.setName("waagh rules");
         waagh.setDescription("waagh rules");
         mob.setName("mob rules");
         mob.setDescription("mob rules");
         assaultGrenades.setName("assaultGrenades");
         assaultGrenades.setDescription("assaultGrenades");
         ses.save(waagh);
         ses.save(mob);
         ses.save(assaultGrenades);
         
         UnitType troops=new UnitType();
         troops.setName("troops");
         troops.setId("troops");
         
         WeaponType melee=new WeaponType();
         melee.setId("melee");
         melee.setName("melee");
         melee.setDescription("melee");
         WeaponType powerclaws=new WeaponType();
         powerclaws.setId("klaw");
         powerclaws.setName("power claws");
         powerclaws.setDescription("powerclaws");
         WeaponType assault=new WeaponType();
         assault.setId("assault");
         assault.setName("assault");
         assault.setDescription("assault");
         ses.save(melee);
         ses.save(powerclaws);
         ses.save(assault);
         
         final WeaponBase slagga=new WeaponBase();
         slagga.setId("slagga");
         slagga.setAP("6");
         slagga.setDescription("slagga");
         slagga.setFraction(f);
         slagga.setName("slagga");
         slagga.setRange("12");
         slagga.setSTR("4");
         slagga.setType(assault);
         
         final WeaponBase bigshuta=new WeaponBase();
         bigshuta.setId("bigshoota");
         bigshuta.setAP("4");
         bigshuta.setDescription("bigshuta");
         bigshuta.setFraction(f);
         bigshuta.setName("bigshuta");
         bigshuta.setRange("36");
         bigshuta.setSTR("5");
         bigshuta.setFireCount("3");
         bigshuta.setType(assault);
         
         final WeaponBase shuta=new WeaponBase();
         shuta.setId("shoota");
         shuta.setAP("4");
         shuta.setDescription("shuta");
         shuta.setFraction(f);
         shuta.setName("shuta");
         shuta.setRange("18");
         shuta.setSTR("4");
         shuta.setFireCount("2");
         shuta.setType(assault);
         
           
         final WeaponBase choppa=new WeaponBase();
         choppa.setId("choppa");
         choppa.setAP("-");
         choppa.setDescription("choppa");
         choppa.setFraction(f);
         choppa.setName("choppa");
         choppa.setRange("-");
         choppa.setSTR("-");
         choppa.setType(melee);
         
         final WeaponBase claws=new WeaponBase();
         claws.setId("claws");
         claws.setAP("-");
         claws.setDescription("power claws");
         claws.setFraction(f);
         claws.setName("power claws");
         claws.setRange("-");
         claws.setSTR("-");
         claws.setType(powerclaws);
         ses.save(slagga);
         ses.save(choppa);
         ses.save(claws);
         ses.save(shuta);
         ses.save(bigshuta);
         final Weapon shutac=new  Weapon();
         shutac.setWeapon(shuta);
         shutac.setCost(0);
         
         final Weapon clawsc=new  Weapon();
         clawsc.setWeapon(claws);
         clawsc.setCost(25);
         
         final Weapon bigshutac=new  Weapon();
         bigshutac.setWeapon(bigshuta);
         bigshutac.setCost(5);
         
         final Weapon slaggac=new  Weapon();
         slaggac.setWeapon(slagga);
         slaggac.setCost(0);
         
         final Weapon choppac=new  Weapon();
         choppac.setWeapon(choppa);
         choppac.setCost(0);
         
         ses.save(slaggac);
         ses.save(choppac);
         ses.save(clawsc);
         ses.save(shutac);
         ses.save(bigshutac);
         
         
         final ItemBase grenade=new ItemBase();
         grenade.setId("grenade");
         grenade.setFraction(f);
         grenade.setDescription("Grenade");
         grenade.setName("grenade");
         grenade.setOptions(new ArrayList(){{add(assaultGrenades);}});
         final Item grenadeCost=new  Item();
         grenadeCost.setItemBase(grenade);
         grenadeCost.setCost(1);
        
         UnitBase ork=new UnitBase();
         ork.setId("ork");
         ork.setA(2);
         ork.setBS(2);
         ork.setCost(6);
         ork.setFraction(f);
         ork.setI(2);
         ork.setLD(7);
         ork.setOptions(new ArrayList(){{add(waagh);add(mob);}});
         ork.setS(3);
         ork.setSV(6);
         ork.setT(4);
         ork.setUnitType(troops);
         ork.setW(1);
         ork.setWS(4);
         ork.setWeapons(new ArrayList<Weapon>(){{add(slaggac);add(choppac);}});
         
         UnitBase nob=new UnitBase();
         nob.setId("nob");
         nob.setA(3);
         nob.setBS(2);
         nob.setCost(16);
         nob.setFraction(f);
         nob.setI(2);
         nob.setLD(7);
         nob.setOptions(new ArrayList(){{add(waagh);add(mob);}});
         nob.setS(4);
         nob.setSV(6);
         nob.setT(4);
         nob.setUnitType(troops);
         nob.setW(2);
         nob.setWS(4);
         nob.setWeapons(new ArrayList<Weapon>(){{add(slaggac);add(choppac);}});
         ses.save(nob);
         ses.save(ork);
         
         final ItemSelection boysIS=new ItemSelection();
         boysIS.setItem(new ArrayList<Item>(){{add(grenadeCost);}});
         boysIS.setCondition("for all;");
         
         final WeaponSelection shuttacond=new WeaponSelection();
         shuttacond.setWeapon(new ArrayList<Weapon>(){{add(shutac);}});
         shuttacond.setCondition("for all;replace;");
         
         final WeaponSelection bigshuttacond=new WeaponSelection();
         bigshuttacond.setWeapon(new ArrayList<Weapon>(){{add(bigshutac);}});
         bigshuttacond.setCondition("replace slagga;count:1 for 10");
         
         final WeaponSelection clawscond=new WeaponSelection();
         clawscond.setWeapon(new ArrayList<Weapon>(){{add(clawsc);}});
         clawscond.setCondition("replace slagga;");
         
         
         SquadPartBase boysPart=new SquadPartBase();
         boysPart.setId("boyspart");
         boysPart.setMinSize(10);
         boysPart.setMaxSize(30);
         boysPart.setItemSelection(new ArrayList<ItemSelection>(){{add(boysIS);}});
         boysPart.setWeaponSelection(new ArrayList<WeaponSelection>(){{add(shuttacond);add(bigshuttacond);}});
         boysPart.setUnit(ork);
         ses.save(boysPart);
         
         final SquadPartBase nobPart=new SquadPartBase();
         nobPart.setId("nobPart");
         nobPart.setMinSize(0);
         nobPart.setMaxSize(1);
         nobPart.setConditions("replace");
         nobPart.setWeaponSelection(new ArrayList<WeaponSelection>(){{add(clawscond);}});
         nobPart.setUnit(nob);
         nobPart.setParent(boysPart);
         boysPart.setModifications(new ArrayList<SquadPartBase>(){{add(nobPart);}} );
         squadboys.setSquadPartBase(boysPart);
         ses.save(nobPart);
         ses.save(boysPart);
         ses.save(squadboys);
         ses.close();
         
         // ObjectMapper mapper = new ObjectMapper();
         //String json = mapper.writeValueAsString(f) ; 
         System.out.println(c.marshall());
         
         
         
         
         
         
         
    }
    @Test
    public void testDb(){
       // init();
        Session ses= HibernateUtil.getSessionFactory().openSession();
    //      ses.beginTransaction();
        Query query = ses.createQuery("select wt from WeaponType wt");
        List<WeaponType> itemlist=query.list();
        Assert.assertEquals(itemlist.size(),3);
        ses.close();  
    }
    
}
