/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.client.services;

import com.armyeditor.entrys.WeaponType;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author Dmitry
 */
public interface IBaseServiceAsync {

    public void getWeaponTypes(AsyncCallback<List<WeaponType>> asyncCallback);

}
