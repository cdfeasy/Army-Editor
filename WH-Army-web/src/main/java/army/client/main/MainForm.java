package army.client.main;


import army.client.editBase.EditBase;
import army.client.services.UnitService;
import army.client.services.UnitServiceAsync;
import com.armyeditor.entrys.Unit;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;

import java.util.List;

/**
 * User: tau
 * Date: 03.01.12
 * Time: 15:25
 * To change this template use File | Settings | File Templates.
 */
public class MainForm extends Composite {

    @UiField
    ListBox unitList;
    @UiField
    MenuBar mainMenu;

    private final UnitServiceAsync unitService = GWT.create(UnitService.class);

    interface MainFormUiBinder extends
            UiBinder<Widget, MainForm> {
    };

    private static MainFormUiBinder uiBinder = GWT
            .create(MainFormUiBinder.class);

    public MainForm() {
        super();
        initWidget(uiBinder.createAndBindUi(this));
//        initComponent(uiBinder.createAndBindUi(this));
        unitService.getUnits(new AsyncCallback<List<Unit>>() {
            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал "+caught.getMessage());
            }

            @Override
            public void onSuccess(List<Unit> result) {
                for(Unit u:result){
                    unitList.addItem(u.getId().toString());
                }
            }
        });
        initMenu();
    }

    private void initMenu(){
        MenuBar gwtMenu = new MenuBar(true);
        mainMenu.addItem(new MenuItem("GWT", true, gwtMenu));
        gwtMenu.addItem("Edit Base", new Command() {
            @Override
            public void execute() {
                RootPanel.get().add((IsWidget) new EditBase());
//                new EditBase();
//                Window.Location.replace("http://google.com");
            }
        });
    }

}
