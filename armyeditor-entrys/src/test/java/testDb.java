/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dmitry
 */

import com.armyeditor.HibernateUtil;
import com.armyeditor.entrys.WeaponType;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Test;

import java.util.List;

public class testDb {
    @Test
    public void testDb(){
        Session ses= HibernateUtil.getSessionFactory().openSession();
    //      ses.beginTransaction();
        Query query = ses.createQuery("select wt from WeaponType wt").setMaxResults(10);
        List<WeaponType> itemlist=query.list();
        ses.close();  
    }
    
}
