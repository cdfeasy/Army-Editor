package com.webadmin.client.mainForm.unitTypeTab;

import com.armyeditor.dto.UnitTypeDTO;
import com.armyeditor.entrys.UnitType;
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
 * Time: 19:28
 * To change this template use File | Settings | File Templates.
 */
public class UnitTypeContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<UnitTypeDTO> specialRuleGrid;
    ColumnModel<UnitTypeDTO> cm;
    ListStore<UnitTypeDTO> store;
    TextButton updateBtn = new TextButton("Update");
    TextButton delSelBtn = new TextButton("Delete Selection");
    final UnitTypeFields unitTypeFields = new UnitTypeFields();

    public UnitTypeContainer(){
        UnitTypeProperties props = GWT.create(UnitTypeProperties.class);
        IdentityValueProvider<UnitTypeDTO> identity = new IdentityValueProvider<UnitTypeDTO>();
        final CheckBoxSelectionModel<UnitTypeDTO> sm = new CheckBoxSelectionModel<UnitTypeDTO>(identity);
        ColumnConfig<UnitTypeDTO, String> idColumn = new ColumnConfig<UnitTypeDTO, String>(props.id(), 50, "id");
        ColumnConfig<UnitTypeDTO, String> nameColumn = new ColumnConfig<UnitTypeDTO, String>(props.name(), 150, "name");
        List<ColumnConfig<UnitTypeDTO, ?>> l = new ArrayList<ColumnConfig<UnitTypeDTO, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        cm = new ColumnModel<UnitTypeDTO>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<UnitTypeDTO>(props.key());
        specialRuleGrid= new Grid<UnitTypeDTO>(store, cm);
        updateStore();
        specialRuleGrid.setSelectionModel(sm);
        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(specialRuleGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(unitTypeFields, new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
    }

    public void updateStore(){
        commonService.getUnitType(new AsyncCallback<List<UnitTypeDTO>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<UnitTypeDTO> result) {
                store.clear();
                for (UnitTypeDTO a : result) {
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
                UnitTypeDTO a =(UnitTypeDTO) event.getSource().getStore().get(row);
                unitTypeFields.getIdFld().setText(a.getId());
                unitTypeFields.getNameFld().setText(a.getName());
            }
        });
        unitTypeFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                UnitTypeDTO a = new UnitTypeDTO();
                a.setId(unitTypeFields.getIdFld().getText());
                a.setName(unitTypeFields.getNameFld().getText());
                commonService.changeUnitType(a, new AsyncCallback<Void>() {
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
        unitTypeFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                UnitTypeDTO a = new UnitTypeDTO();
                a.setId(unitTypeFields.getIdFld().getText());
                a.setName(unitTypeFields.getNameFld().getText());
                commonService.addUnitType(a, new AsyncCallback<Void>() {
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
                commonService.delUnitTypes(selectList, new AsyncCallback<Void>() {
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
