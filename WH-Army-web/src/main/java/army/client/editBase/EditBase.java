package army.client.editBase;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
//import com.sencha.gxt.examples.resources.client.TestData;
//import com.sencha.gxt.explorer.client.model.Example.Detail;
import com.sencha.gxt.widget.core.client.Composite;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.info.Info;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 04.04.12
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 */
public class EditBase extends Composite {
    interface MyUiBinder extends UiBinder<Widget, EditBase> {
    }

    private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);

//    @UiField(provided = true)
//    String txt = TestData.DUMMY_TEXT_SHORT;

    public Widget asWidget() {
        return uiBinder.createAndBindUi(this);
    }

    public void onModuleLoad() {
        RootPanel.get().add(asWidget());
    }

    @UiHandler(value = {"folder", "panel"})
    void onSelection(SelectionEvent<Widget> event) {
        TabPanel panel = (TabPanel) event.getSource();
        Widget w = event.getSelectedItem();
        TabItemConfig config = panel.getConfig(w);
        Info.display("Message", "'" + config.getText() + "' Selected");
    }
}