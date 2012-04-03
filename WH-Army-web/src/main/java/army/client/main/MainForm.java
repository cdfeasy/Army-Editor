package army.client.main;


import army.client.services.UnitService;
import army.client.services.UnitServiceAsync;
import com.armyeditor.entrys.Unit;
import com.extjs.gxt.ui.client.data.BaseModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
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

    /*@UiFactory
    public FormDataProvider getStore() {
        return new FormDataProvider() {
            @Override
            public ListStore unitStore() {
                final ListStore<ModelData> store = new ListStore<ModelData>(); //пустое хранилище
                unitService.getUnits(new AsyncCallback<List<Unit>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        System.out.println("Запрос упал");
                    }

                    @Override
                    public void onSuccess(List<Unit> result) {

                        for(Unit u:result){
                            ModelData modelData = new BaseModel();
                            modelData.set("id", u);
                            store.add(modelData);
                        }
                    }
                });
                return store;
            }
        };

    }*/

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
    }

    /*public static interface FormDataProvider {
        ListStore unitStore();
    }*/

}
