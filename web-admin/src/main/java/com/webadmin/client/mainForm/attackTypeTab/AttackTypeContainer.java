package com.webadmin.client.mainForm.attackTypeTab;

import com.armyeditor.entrys.AttackType;
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
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 28.07.12
 * Time: 12:19
 * To change this template use File | Settings | File Templates.
 */
public class AttackTypeContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<AttackType> attackTypeGrid;
    ColumnModel<AttackType> cm;
    ListStore<AttackType> store;
    TextButton updateBtn;
    TextButton delSelBtn;
    final AttackTypeFields attackTypeFields = new AttackTypeFields();

    public AttackTypeContainer() {
        AttackTypeProperties props = GWT.create(AttackTypeProperties.class);
        IdentityValueProvider<AttackType> identity = new IdentityValueProvider<AttackType>();
        final CheckBoxSelectionModel<AttackType> sm = new CheckBoxSelectionModel<AttackType>(identity);
        ColumnConfig<AttackType, String> idColumn = new ColumnConfig<AttackType, String>(props.id(), 50, "id");
        ColumnConfig<AttackType, String> nameColumn = new ColumnConfig<AttackType,String>(props.name(),150,"name");
        ColumnConfig<AttackType, String> descripColumn = new ColumnConfig<AttackType,String>(props.description(),150,"description");

        List<ColumnConfig<AttackType, ?>> l = new ArrayList<ColumnConfig<AttackType, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descripColumn);
        cm = new ColumnModel<AttackType>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<AttackType>(props.key());
        updateStore();
        attackTypeGrid = new Grid<AttackType>(store,cm);
        attackTypeGrid.setSelectionModel(sm);
        updateBtn = new TextButton("Update", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                updateStore();
            }
        });
        delSelBtn = new TextButton("Delete Selection", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                List selectList = attackTypeGrid.getSelectionModel().getSelectedItems();
                commonService.delAttackTypes(selectList, new AsyncCallback<Void>() {
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

        gridContainer = new VerticalLayoutContainer();
        gridContainer.add(attackTypeGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(attackTypeFields, new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
    }

    void initHandlers() {
        attackTypeGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                AttackType a =(AttackType) event.getSource().getStore().get(row);
                attackTypeFields.getIdFld().setText(a.getId());
                attackTypeFields.getNameFld().setText(a.getName());
                attackTypeFields.getDescripFld().setText(a.getDescription());
            }
        });

        attackTypeFields.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                AttackType a = new AttackType();
                a.setId(attackTypeFields.getIdFld().getText());
                a.setName(attackTypeFields.getNameFld().getText());
                a.setDescription(attackTypeFields.getDescripFld().getText());
                commonService.changeAttackType(a, new AsyncCallback<Void>() {
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

        attackTypeFields.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                AttackType a = new AttackType();
                a.setId(attackTypeFields.getIdFld().getText());
                a.setName(attackTypeFields.getNameFld().getText());
                a.setDescription(attackTypeFields.getDescripFld().getText());
                commonService.addAttackType(a, new AsyncCallback<Void>() {
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
    }

    public void updateStore(){
        commonService.getAttackTypes(new AsyncCallback<List<AttackType>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("Запрос упал " + caught.getMessage());
            }

            @Override
            public void onSuccess(List<AttackType> result) {
                store.clear();
                for (AttackType a : result) {
                    store.add(a);
                }
                attackTypeGrid.reconfigure(store, cm);
            }
        });
    }
}
