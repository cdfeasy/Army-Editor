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
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.ValueProvider;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.data.shared.ModelKeyProvider;
import com.sencha.gxt.data.shared.PropertyAccess;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
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
    VerticalLayoutContainer gridContainer;
    @UiField
    VerticalLayoutContainer con;
    Grid<Armor> armorGrid;
	ColumnModel<Armor> cm;
	ListStore<Armor> store;
    @UiField
    TextButton delSelBtn;
    @UiField
    TextButton addBtn;
    @UiField
    TextButton updateBtn;
    @UiHandler({"updateBtn"})
    public void onButtonClick(SelectEvent event) {
        updateStore();
        armorGrid.reconfigure(store,cm);
        Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");

    }

    public void configGrid(){
        PostProperties props = GWT.create(PostProperties.class);
        IdentityValueProvider<Armor> identity = new IdentityValueProvider<Armor>();
        final CheckBoxSelectionModel<Armor> sm = new CheckBoxSelectionModel<Armor>(identity);
        ColumnConfig<Armor, String> nameColumn = new ColumnConfig<Armor, String>(props.name(), 150, "name");
        ColumnConfig<Armor, String> descripColumn = new ColumnConfig<Armor, String>(props.description(), 150, "description");

        List<ColumnConfig<Armor, ?>> l = new ArrayList<ColumnConfig<Armor, ?>>();
        l.add(sm.getColumn());
        l.add(nameColumn);
        l.add(descripColumn);
        cm = new ColumnModel<Armor>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<Armor>(props.id());
        armorGrid= new Grid<Armor>(store, cm);
        updateStore();
        armorGrid.setSelectionModel(sm);
//        armorGrid.setHeight(500);
        con.add(armorGrid);
    }

	public Widget asWidget() {
        Widget d=uiBinder.createAndBindUi(this);
        configGrid();
        delSelBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");
                List selectList = new ArrayList();
                selectList = armorGrid.getSelectionModel().getSelectedItems();
                commonService.delArmors(selectList,new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore();
//                        armorGrid.reconfigure(store,cm);
                    }
                });
            }
        });
        addBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                final Dialog addDialog = new Dialog();
                addDialog.setHeadingText("Add new item");
                VerticalLayoutContainer layoutContainer = new VerticalLayoutContainer();
                addDialog.add(layoutContainer);
                final TextField id = new TextField();
                layoutContainer.add(new FieldLabel(id,"ID"));
                final TextField name = new TextField();
                layoutContainer.add(new FieldLabel(name,"Name"));
                final TextField descrip = new TextField();
                layoutContainer.add(new FieldLabel(descrip,"Description"));
                addDialog.setPredefinedButtons(Dialog.PredefinedButton.CANCEL, Dialog.PredefinedButton.OK);
                TextButton saveBtn = addDialog.getButtonById("OK");
                addDialog.getButtonById("OK").setText("Save");
                saveBtn.addSelectHandler(new SelectEvent.SelectHandler() {
                    @Override
                    public void onSelect(SelectEvent event) {
                        Armor arm = new Armor();
                        arm.setId(id.getText());
                        arm.setName(name.getText());
                        arm.setDescription(descrip.getText());
                        commonService.addArmor(arm, new AsyncCallback<Void>() {
                            @Override
                            public void onFailure(Throwable throwable) {
                                System.out.println("Запрос упал " + throwable.getMessage());
                            }

                            @Override
                            public void onSuccess(Void aVoid) {
                                updateStore();
                                addDialog.hide();
                            }
                        });
                    }
                });
                addDialog.show();
            }
        });
        return d;
	}

	public void onModuleLoad() {
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
                    store.clear();
                    store.add(a);
                }
                armorGrid.reconfigure(store,cm);
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
