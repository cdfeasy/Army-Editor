/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webadmin.server;

import com.armyeditor.HibernateUtil;
import com.armyeditor.dto.CodexDTO;
import com.armyeditor.dto.UnitDTO;
import com.armyeditor.entrys.Codex;
import com.armyeditor.entrys.Unit;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webadmin.client.services.ArmyService;
import com.webadmin.client.services.CommonService;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author dmitry
 */
public class ArmyServiceImpl extends RemoteServiceServlet implements ArmyService {

    @Override
    public CodexDTO getCodex() {
        Session ses= HibernateUtil.getSessionFactory().openSession();
        Query query = ses.createQuery("select codex from Codex codex");
        List<Codex> itemlist=query.list();
        List<CodexDTO> сodexDTOList = new ArrayList<CodexDTO>();
        for (Codex u:itemlist){
            сodexDTOList.add(new CodexDTO(u));
        }
        ses.close();
        return сodexDTOList.get(0);
    }
    
}