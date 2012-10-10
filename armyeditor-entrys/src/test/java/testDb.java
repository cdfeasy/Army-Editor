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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class testDb {
  //  @Before
    public void init() throws IOException{
         Session ses= HibernateUtil.getSessionFactory().openSession();
         Transaction tr= ses.beginTransaction();
          Codex codex=new Codex();
         codex.setDescription("ork 5 red");
         codex.setId("ork 5 red");
         codex.setName("ork 5 red");
         ses.save(codex);
         
         Fraction fr = new Fraction();
         fr.setName("orks");
         fr.setId("orks");
         fr.getCodexes().add(codex);
         ses.save(fr);
        
         
         final SquadBase squadboys =new SquadBase();
         squadboys.setId("squadboys");
         codex.setSquads(new ArrayList<SquadBase>(){{add(squadboys);}});
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
         ses.save(troops);
         
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
         slagga.setAp("6");
         slagga.setDescription("slagga");
         slagga.setCodex(codex);
         slagga.setName("slagga");
         slagga.setRange("12");
         slagga.setStr("4");
         slagga.setType(assault);
         
         final WeaponBase bigshuta=new WeaponBase();
         bigshuta.setId("bigshoota");
         bigshuta.setAp("4");
         bigshuta.setDescription("bigshuta");
         bigshuta.setCodex(codex);
         bigshuta.setName("bigshuta");
         bigshuta.setRange("36");
         bigshuta.setStr("5");
         bigshuta.setFireCount("3");
         bigshuta.setType(assault);
		 
		 final WeaponBase rokkit=new WeaponBase();
         rokkit.setId("rokkit");
         rokkit.setAp("3");
         rokkit.setDescription("rokkit");
         rokkit.setCodex(codex);
         rokkit.setName("rokkit");
         rokkit.setRange("24");
         rokkit.setStr("8");
         rokkit.setFireCount("1");
         rokkit.setType(assault);
         
         final WeaponBase shuta=new WeaponBase();
         shuta.setId("shoota");
         shuta.setAp("4");
         shuta.setDescription("shuta");
         shuta.setCodex(codex);
         shuta.setName("shuta");
         shuta.setRange("18");
         shuta.setStr("4");
         shuta.setFireCount("2");
         shuta.setType(assault);
         
           
         final WeaponBase choppa=new WeaponBase();
         choppa.setId("choppa");
         choppa.setAp("-");
         choppa.setDescription("choppa");
         choppa.setCodex(codex);
         choppa.setName("choppa");
         choppa.setRange("-");
         choppa.setStr("-");
         choppa.setType(melee);
         
         final WeaponBase claws=new WeaponBase();
         claws.setId("claws");
         claws.setAp("-");
         claws.setDescription("power claws");
         claws.setCodex(codex);
         claws.setName("power claws");
         claws.setRange("-");
         claws.setStr("-");
         claws.setType(powerclaws);
         ses.save(slagga);
         ses.save(choppa);
         ses.save(claws);
         ses.save(shuta);
         ses.save(rokkit);
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
		 
		 final Weapon rokkitc=new  Weapon();
         rokkitc.setWeapon(rokkit);
         rokkitc.setCost(6);
         
         final Weapon slaggac=new  Weapon();
         slaggac.setWeapon(slagga);
         slaggac.setCost(0);
         
         final Weapon choppac=new  Weapon();
         choppac.setWeapon(choppa);
         choppac.setCost(0);
         
         ses.save(slaggac);
		 ses.save(rokkitc);
         ses.save(choppac);
         ses.save(clawsc);
         ses.save(shutac);
         ses.save(bigshutac);
         
         
         final ItemBase grenade=new ItemBase();
         grenade.setId("grenade");
         grenade.setCodex(codex);
         grenade.setDescription("Grenade");
         grenade.setName("grenade");
         grenade.setOptions(new ArrayList(){{add(assaultGrenades);}});
         final Item grenadeCost=new  Item();
         grenadeCost.setItemBase(grenade);
         grenadeCost.setCost(1);
         ses.save(grenade);
		 ses.save(grenadeCost);
        
         UnitBase ork=new UnitBase();
         ork.setId("ork");
         ork.setA(2);
         ork.setBs(2);
         ork.setCost(6);
         ork.setCodex(codex);
         ork.setI(2);
         ork.setLd(7);
         ork.setOptions(new ArrayList(){{add(waagh);add(mob);}});
         ork.setS(3);
         ork.setSv("6+");
         ork.setT(4);
         ork.setUnitType(troops);
         ork.setW(1);
         ork.setWs(4);
         ork.setWeapons(new ArrayList<Weapon>(){{add(slaggac);add(choppac);}});
         
         UnitBase nob=new UnitBase();
         nob.setId("nob");
         nob.setA(3);
         nob.setBs(2);
         nob.setCost(16);
         nob.setCodex(codex);
         nob.setI(2);
         nob.setLd(7);
         nob.setOptions(new ArrayList(){{add(waagh);add(mob);}});
         nob.setS(4);
         nob.setSv("6+");
         nob.setT(4);
         nob.setUnitType(troops);
         nob.setW(2);
         nob.setWs(4);
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
         bigshuttacond.setWeapon(new ArrayList<Weapon>(){{add(bigshutac);add(rokkitc);}});
         bigshuttacond.setCondition("replace slagga;count:1 for 10");
         
         final WeaponSelection clawscond=new WeaponSelection();
         clawscond.setWeapon(new ArrayList<Weapon>(){{add(clawsc);}});
         clawscond.setCondition("replace slagga;");
         
         ses.save(boysIS);
         ses.save(shuttacond);
         ses.save(bigshuttacond);
         ses.save(clawscond);
         
         
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
         ses.save(codex);
         tr.commit();
         ses.close();
         
         // ObjectMapper mapper = new ObjectMapper();
         //String json = mapper.writeValueAsString(f) ; 
         System.out.println(codex.marshall());
         
         
         
         
         
         
         
    }
    @Test
    public void testDb() throws IOException{
        init();
        Session ses= HibernateUtil.getSessionFactory().openSession();
    //      ses.beginTransaction();
        Query query = ses.createQuery("select wt from WeaponType wt");
        List<WeaponType> itemlist=query.list();
        Assert.assertEquals(itemlist.size(),3);
        ses.close();  
    }
    
   //  @Test
    public void testDb1() throws IOException{
        init();
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Transaction trans=ses.beginTransaction();
    //      ses.beginTransaction();
         UnitType troops=new UnitType();
         troops.setName("troops");
         troops.setId("troops");
         ses.merge(troops);
         trans.commit();
        ses.close();  
        
           Session ses1= HibernateUtil.getSessionFactory().openSession();
       // Query query = ses.createQuery("select unittype from UnitType unittype").setMaxResults(10);
      
        List<UnitType> itemlist=  ses1.createCriteria(UnitType.class).list();
        
        ses1.close();
        System.out.println(itemlist.get(0).getOptions());
    }
    
}
