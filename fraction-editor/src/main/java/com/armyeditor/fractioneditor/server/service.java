/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.server;

import com.armyeditor.entrys.Item;
import com.armyeditor.fractioneditor.client.CellTableModel;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.armyeditor.fractioneditor.client.services.IService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Dmitry
 */
public class service extends RemoteServiceServlet implements IService {

    public String greetServer(String name) throws IllegalArgumentException {
        Session ses=HibernateUtil.getSessionFactory().openSession();
        ses.beginTransaction();
        com.armyeditor.entrys.Item it=new com.armyeditor.entrys.Item();
        it.setMaxCount(10);
        ses.persist(it);
     //   ses.flush();
        ses.getTransaction().commit();
        ses.close();
        return "dsf3434jhj ";
    }

    public List<CellTableModel> getItems(int startIndex, int maxCount) {
        Session ses=HibernateUtil.getSessionFactory().openSession();
    //      ses.beginTransaction();
        Query query = ses.createQuery("select it from Item it").setFirstResult(startIndex).setMaxResults(maxCount);
        List<Item> itemlist=query.list();
        ArrayList<CellTableModel> res=new  ArrayList<CellTableModel>();
        for(Item i:itemlist)
        {
            res.add(new CellTableModel(i.getId(),Integer.toString(i.getMaxCount())));
        }
  //       ses.getTransaction().commit();
         ses.close();
	return res;
    }

    public int getCount() {
        Session ses=HibernateUtil.getSessionFactory().openSession();
     //   ses.beginTransaction();
        Integer cnt=ses.createCriteria(com.armyeditor.entrys.Item.class).list().size();
     //   ses.getTransaction().commit();
        ses.close();
        
        return cnt;
    }

}
