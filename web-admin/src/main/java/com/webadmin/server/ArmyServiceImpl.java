/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webadmin.server;

import com.armyeditor.HibernateUtil;
import com.armyeditor.dto.CodexDTO;
import com.armyeditor.dto.SquadPartBaseDTO;
import com.armyeditor.dto.SquadPartDTO;
import com.armyeditor.dto.UnitDTO;
import com.armyeditor.entrys.*;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.webadmin.client.services.ArmyException;
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
    public CodexDTO getCodex() throws ArmyException {
        try {
            Session ses = HibernateUtil.getSessionFactory().openSession();
            Query query = ses.createQuery("select codex from Codex codex");
            List<Codex> itemlist = query.list();
            List<CodexDTO> сodexDTOList = new ArrayList<CodexDTO>();
            for (Codex u : itemlist) {
                сodexDTOList.add(new CodexDTO(u));
            }
            ses.close();
            return сodexDTOList.get(0);
        } catch (Throwable th) {
            throw new ArmyException(th);
        }
    }

    @Override
    public SquadPartBaseDTO getSquadPart(String id) throws ArmyException {
          try {
            Session ses = HibernateUtil.getSessionFactory().openSession();
            SquadPartBase squad = (SquadPartBase)ses.get(SquadPartBase.class, id);
            SquadPartBaseDTO ss=new SquadPartBaseDTO(squad,null);
            ses.close();
            return ss;
        } catch (Throwable th) {
            throw new ArmyException(th);
        }
    }
}
