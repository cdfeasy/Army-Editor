package com.webadmin.client;

import com.armyeditor.entrys.Armor;
import com.armyeditor.entrys.Unit;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiFactory;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;
import java.util.ArrayList;

import java.util.List;

/**
 * Entry point classes define
 * <code>onModuleLoad()</code>.
 */
public class admin implements EntryPoint {

	interface MyUiBinder extends UiBinder<Widget, admin> {
	}
	private static MyUiBinder uiBinder = GWT.create(MyUiBinder.class);
	private final CommonServiceAsync commonService = GWT.create(CommonService.class);
	@UiField(provided = true)
	String txt = "ваааааа";
	/**
	 * This is the entry point method.
	 */
	@UiField
	Grid<Armor> armorGrid;
	@UiField
	ColumnModel<Armor> cm;
	@UiField
	ListStore<Armor> store;
    @UiField
    TextButton updateBtn;
    @UiHandler({"updateBtn"})
    public void onButtonClick(SelectEvent event) {
        updateStore();
        Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");

    }
	@UiFactory
	ColumnModel<Armor> createColumnModel() {
		return cm;
	}

	@UiFactory
	ListStore<Armor> createListStore() {
		return store;
	}

	public Widget asWidget() {
		PostProperties props = GWT.create(PostProperties.class);

		ColumnConfig<Armor, String> nameColumn = new ColumnConfig<Armor, String>(props.name(), 150, "name");
        ColumnConfig<Armor, String> descripColumn = new ColumnConfig<Armor, String>(props.description(), 150, "description");

		List<ColumnConfig<Armor, ?>> l = new ArrayList<ColumnConfig<Armor, ?>>();
		l.add(nameColumn);
        l.add(descripColumn);
		cm = new ColumnModel<Armor>(l);

        store = new ListStore<Armor>(props.id());



		return uiBinder.createAndBindUi(this);
	}

	public void onModuleLoad() {
        updateStore();
        RootPanel.get().add(asWidget());
	}

    public void updateStore(){
        commonService.getArmors(new AsyncCallback<List<Armor>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<Armor> result) {
                for (Armor a : result) {
                    store.add(a.getId().intValue(), a);
                    armorGrid.reconfigure(store,cm);
                }
            }
        });
    }

	@UiHandler(value = {"folder", "panel"})
	void onSelection(SelectionEvent<Widget> event) {
		TabPanel panel = (TabPanel) event.getSource();
		Widget w = event.getSelectedItem();
		TabItemConfig config = panel.getConfig(w);
		Info.display("Message", "'" + config.getText() + "' Selected");
	}
}
