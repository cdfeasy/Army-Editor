package army.client.services;

import com.armyeditor.entrys.Unit;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import java.util.List;

public interface UnitServiceAsync {
    void getUnits(AsyncCallback<List<Unit>> async);
}
