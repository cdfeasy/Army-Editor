/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webadmin.client.services;

import com.armyeditor.dto.CodexDTO;
import com.armyeditor.dto.SquadPartBaseDTO;
import com.armyeditor.dto.SquadPartDTO;
import com.armyeditor.dto.UnitDTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author dmitry
 */
@RemoteServiceRelativePath("server/ArmyService")
public interface ArmyService extends RemoteService {
     CodexDTO getCodex() throws ArmyException;
     SquadPartBaseDTO getSquadPart(String id) throws ArmyException;
}
