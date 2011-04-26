/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.client.services;


import com.armyeditor.entrys.WeaponType;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author Dmitry
 */
@RemoteServiceRelativePath("BaseServiceBean")
public interface IBaseService extends RemoteService {
    List<WeaponType> getWeaponTypes();

}
