package army.client.editBase;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 04.04.12
 * Time: 23:07
 * To change this template use File | Settings | File Templates.
 */
public class EditBase extends Composite {
    interface SecondFormUiBinder extends UiBinder<Widget, EditBase> {
    }

    private static SecondFormUiBinder uiBinder = GWT.create(SecondFormUiBinder.class);

    @UiField
    ListBox listBox;

    public EditBase() {
        super();
        initWidget(uiBinder.createAndBindUi(this));
        listBox.addItem("пыщ");
//        RootPanel.get().add(new VerticalPanel());
    }
}