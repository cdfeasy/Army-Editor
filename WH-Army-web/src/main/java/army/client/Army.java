package army.client;

import army.client.main.MainForm;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.google.gwt.core.client.EntryPoint;
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
        Viewport vp=new Viewport();
        MainForm form = new MainForm();
        vp.add(form);
        RootPanel.get().add(vp);
    }
}
