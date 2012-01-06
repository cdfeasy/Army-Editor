package army.client;

import army.client.main.MainForm;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Created by IntelliJ IDEA.
 * User: tau
 * Date: 03.01.12
 * Time: 15:11
 * To change this template use File | Settings | File Templates.
 */
public class Army implements EntryPoint {

    @Override
    public void onModuleLoad() {
        MainForm form = new MainForm();
        RootPanel.get().add((IsWidget) form);
    }
}
