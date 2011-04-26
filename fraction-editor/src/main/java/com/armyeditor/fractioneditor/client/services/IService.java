/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.armyeditor.fractioneditor.client.services;

import com.armyeditor.fractioneditor.client.CellTableModel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import java.util.List;

/**
 *
 * @author Dmitry
 */
@RemoteServiceRelativePath("Service")
public interface IService extends RemoteService {
        List<CellTableModel> getItems(int startIndex, int maxCount);
	int getCount();
        String greetServer(String name) throws IllegalArgumentException;
}
