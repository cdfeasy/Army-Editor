/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.server;

import com.armyeditor.entrys.WeaponType;
import com.armyeditor.fractioneditor.client.services.IBaseService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Dmitry
 */
public class BaseServiceBean  extends RemoteServiceServlet implements IBaseService {

    public List<WeaponType> getWeaponTypes() {
        Session ses=HibernateUtil.getSessionFactory().openSession();
    //      ses.beginTransaction();
        Query query = ses.createQuery("select wt from WeaponType wt").setMaxResults(10);
        List<WeaponType> itemlist=query.list();
        ses.close();
	return itemlist;
    }

 

}
