package com.webadmin.client.services;

import com.armyeditor.entrys.Armor;
import com.armyeditor.entrys.Unit;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 31.03.12
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
@RemoteServiceRelativePath("server/BaseService")
public interface CommonService extends RemoteService {
    List<Unit> getUnits();
    List<Armor> getArmors();
}
