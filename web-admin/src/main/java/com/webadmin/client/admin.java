package com.webadmin.client;

import com.armyeditor.entrys.Armor;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.TabPanel;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.form.FieldLabel;
import com.sencha.gxt.widget.core.client.form.TextField;
import com.sencha.gxt.widget.core.client.grid.CheckBoxSelectionModel;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;
import com.sencha.gxt.widget.core.client.grid.ColumnModel;
import com.sencha.gxt.widget.core.client.grid.Grid;
import com.sencha.gxt.widget.core.client.info.Info;
import com.webadmin.client.mainForm.ArmorFieds;
import com.webadmin.client.mainForm.ArmorProperties;
import com.webadmin.client.mainForm.AttackTypeContainer;
import com.webadmin.client.mainForm.AttackTypeFields;
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

	@UiField
	String txt = "ваааааа";
	/**
	 * This is the entry point method.
	 */
    final ArmorFieds fields = new ArmorFieds();
    @UiField
    VBoxLayoutContainer fldsCon;
    @UiField
    HorizontalLayoutContainer attackTyteGridContainer;
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
        Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");

    }

    public void configGrid(){
        ArmorProperties props = GWT.create(ArmorProperties.class);
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
        con.add(armorGrid);
        attackTyteGridContainer.add(new AttackTypeContainer());
    }

	void initHandlers() {
        armorGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                Armor a =(Armor) event.getSource().getStore().get(row);
                fields.getIdFld().setText(a.getId());
                fields.getNameFld().setText(a.getName());
                fields.getDescripFld().setText(a.getDescription());
            }
        });
        fields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Armor a = new Armor();
                a.setId(fields.getIdFld().getText());
                a.setName(fields.getNameFld().getText());
                a.setDescription(fields.getDescripFld().getText());
                commonService.changeArmor(a, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore();
                    }
                });
            }
        });
        fields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Armor a = new Armor();
                a.setId(fields.getIdFld().getText());
                a.setName(fields.getNameFld().getText());
                a.setDescription(fields.getDescripFld().getText());
                commonService.addArmor(a, new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore();
                    }
                });
            }
        });

        delSelBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Info.display("Click", ((TextButton) event.getSource()).getText() + " clicked");
                List selectList = armorGrid.getSelectionModel().getSelectedItems();
                commonService.delArmors(selectList,new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println("Запрос упал " + throwable.getMessage());
                    }

                    @Override
                    public void onSuccess(Void aVoid) {
                        updateStore();
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
    }

    public Widget asWidget() {
        Widget d=uiBinder.createAndBindUi(this);
        configGrid();
        initHandlers();
        fldsCon.add(fields);
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
                store.clear();
                for (Armor a : result) {
                    store.add(a);
                }
                armorGrid.reconfigure(store,cm);
            }
        });
    }

	@UiHandler(value = {"folder"})
	void onSelection(SelectionEvent<Widget> event) {
		TabPanel panel = (TabPanel) event.getSource();
		Widget w = event.getSelectedItem();
		TabItemConfig config = panel.getConfig(w);
		Info.display("Message", "'" + config.getText() + "' Selected");
	}
}
