package com.webadmin.client.services;

import com.armyeditor.entrys.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.List;

public interface CommonServiceAsync {
    void getUnits(AsyncCallback<List<Unit>> async);
}
