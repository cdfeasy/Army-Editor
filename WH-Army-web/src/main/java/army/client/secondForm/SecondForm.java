package army.client.secondForm;

import com.extjs.gxt.ui.client.widget.VerticalPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 04.04.12
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 */
public class SecondForm extends Composite {
    interface SecondFormUiBinder extends UiBinder<Widget, SecondForm> {
    }

    private static SecondFormUiBinder uiBinder = GWT.create(SecondFormUiBinder.class);

    @UiField
    ListBox listBox;

    public SecondForm() {
        super();
        initWidget(uiBinder.createAndBindUi(this));
        listBox.addItem("пыщ");
//        RootPanel.get().add(new VerticalPanel());
    }
}