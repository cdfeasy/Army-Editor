package com.webadmin.client.mainForm.weaponTypeTab;

import com.armyeditor.entrys.WeaponType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.event.RowClickEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
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
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 20:17
 * To change this template use File | Settings | File Templates.
 */
public class WeaponTypeContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<WeaponType> specialRuleGrid;
    ColumnModel<WeaponType> cm;
    ListStore<WeaponType> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    final WeaponTypeFields weaponTypeFields = new WeaponTypeFields();

    public WeaponTypeContainer() {
        WeaponTypeProperties props = GWT.create(WeaponTypeProperties.class);
        IdentityValueProvider<WeaponType> identity = new IdentityValueProvider<WeaponType>();
        final CheckBoxSelectionModel<WeaponType> sm = new CheckBoxSelectionModel<WeaponType>(identity);
        ColumnConfig<WeaponType, String> idColumn = new ColumnConfig<WeaponType, String>(props.id(), 50, "id");
        ColumnConfig<WeaponType, String> nameColumn = new ColumnConfig<WeaponType, String>(props.name(), 150, "name");
        ColumnConfig<WeaponType, String> descripColumn = new ColumnConfig<WeaponType, String>(props.description(), 150, "description");

        List<ColumnConfig<WeaponType, ?>> l = new ArrayList<ColumnConfig<WeaponType, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descripColumn);
        cm = new ColumnModel<WeaponType>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<WeaponType>(props.key());
        specialRuleGrid= new Grid<WeaponType>(store, cm);
        updateStore();
        specialRuleGrid.setSelectionModel(sm);
        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(specialRuleGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(weaponTypeFields, new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
    }

    public void updateStore(){
        commonService.getWeaponType(new AsyncCallback<List<WeaponType>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<WeaponType> result) {
                store.clear();
                for (WeaponType a : result) {
                    store.add(a);
                }
                specialRuleGrid.reconfigure(store, cm);
            }
        });
    }

    void initHandlers() {
        specialRuleGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                WeaponType a =(WeaponType) event.getSource().getStore().get(row);
                weaponTypeFields.getIdFld().setText(a.getId());
                weaponTypeFields.getNameFld().setText(a.getName());
                weaponTypeFields.getDescripFld().setText(a.getDescription());
            }
        });
        weaponTypeFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                WeaponType a = new WeaponType();
                a.setId(weaponTypeFields.getIdFld().getText());
                a.setName(weaponTypeFields.getNameFld().getText());
                a.setDescription(weaponTypeFields.getDescripFld().getText());
                commonService.changeWeaponType(a, new AsyncCallback<Void>() {
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
        weaponTypeFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                WeaponType a = new WeaponType();
                a.setId(weaponTypeFields.getIdFld().getText());
                a.setName(weaponTypeFields.getNameFld().getText());
                a.setDescription(weaponTypeFields.getDescripFld().getText());
                commonService.addWeaponType(a, new AsyncCallback<Void>() {
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
                List selectList = specialRuleGrid.getSelectionModel().getSelectedItems();
                commonService.delSpecialRules(selectList, new AsyncCallback<Void>() {
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

        updateBtn.addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                updateStore();
            }
        });

    }
}