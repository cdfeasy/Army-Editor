package army.client.main;


import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.Composite;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;


/**
 * Created by IntelliJ IDEA.
 * User: tau
 * Date: 03.01.12
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public class MainForm extends Composite {

    @UiField
    Label field;

    interface MainFormUiBinder extends
            UiBinder<Component, MainForm> {
    };

    private static MainFormUiBinder uiBinder = GWT
            .create(MainFormUiBinder.class);

    public MainForm() {
        super();
        initComponent(uiBinder.createAndBindUi(this));
    }

}
