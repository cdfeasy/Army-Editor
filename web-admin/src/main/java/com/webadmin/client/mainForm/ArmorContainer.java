package com.webadmin.client.mainForm;

import com.armyeditor.entrys.Armor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.sencha.gxt.core.client.IdentityValueProvider;
import com.sencha.gxt.core.client.Style;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.data.shared.ListStore;
import com.sencha.gxt.widget.core.client.Dialog;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.HorizontalLayoutContainer;
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
import com.webadmin.client.services.CommonService;
import com.webadmin.client.services.CommonServiceAsync;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: tau
 * Date: 02.08.12
 * Time: 13:43
 * To change this template use File | Settings | File Templates.
 */
public class ArmorContainer extends HorizontalLayoutContainer {
    private final CommonServiceAsync commonService = GWT.create(CommonService.class);
    VerticalLayoutContainer gridContainer;
    Grid<Armor> armorGrid;
    ColumnModel<Armor> cm;
    ListStore<Armor> store;
    TextButton updateBtn;
    TextButton delSelBtn;
    final ArmorFieds armorFieds = new ArmorFieds();

    public ArmorContainer() {
        ArmorProperties props = GWT.create(ArmorProperties.class);
        IdentityValueProvider<Armor> identity = new IdentityValueProvider<Armor>();
        final CheckBoxSelectionModel<Armor> sm = new CheckBoxSelectionModel<Armor>(identity);
        ColumnConfig<Armor, String> idColumn = new ColumnConfig<Armor, String>(props.id(), 50, "id");
        ColumnConfig<Armor, String> nameColumn = new ColumnConfig<Armor, String>(props.name(), 150, "name");
        ColumnConfig<Armor, String> descripColumn = new ColumnConfig<Armor, String>(props.description(), 150, "description");

        List<ColumnConfig<Armor, ?>> l = new ArrayList<ColumnConfig<Armor, ?>>();
        l.add(sm.getColumn());
        l.add(idColumn);
        l.add(nameColumn);
        l.add(descripColumn);
        cm = new ColumnModel<Armor>(l);
        sm.setSelectionMode(Style.SelectionMode.MULTI);
        store = new ListStore<Armor>(props.key());
        armorGrid= new Grid<Armor>(store, cm);
        updateStore();
        armorGrid.setSelectionModel(sm);

        updateBtn = new TextButton("Update", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                updateStore();
            }
        });
        delSelBtn = new TextButton("Delete Selection", new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                List selectList = armorGrid.getSelectionModel().getSelectedItems();
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
        gridContainer.add(armorGrid);
        gridContainer.add(updateBtn);
        gridContainer.add(delSelBtn);
        this.add(gridContainer);
        VerticalLayoutContainer vc = new VerticalLayoutContainer();
        vc.add(armorFieds, new VerticalLayoutContainer.VerticalLayoutData(350,200,new Margins(5,5,5,5)));
        this.add(vc);
        initHandlers();
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

    void initHandlers() {
        armorGrid.addRowClickHandler(new RowClickEvent.RowClickHandler() {
            @Override
            public void onRowClick(RowClickEvent event) {
                int row = event.getRowIndex();
                Armor a =(Armor) event.getSource().getStore().get(row);
                armorFieds.getIdFld().setText(a.getId());
                armorFieds.getNameFld().setText(a.getName());
                armorFieds.getDescripFld().setText(a.getDescription());
            }
        });
        armorFieds.getSaveBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Armor a = new Armor();
                a.setId(armorFieds.getIdFld().getText());
                a.setName(armorFieds.getNameFld().getText());
                a.setDescription(armorFieds.getDescripFld().getText());
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
        armorFieds.getSaveNewBtn().addSelectHandler(new SelectEvent.SelectHandler() {
            @Override
            public void onSelect(SelectEvent event) {
                Armor a = new Armor();
                a.setId(armorFieds.getIdFld().getText());
                a.setName(armorFieds.getNameFld().getText());
                a.setDescription(armorFieds.getDescripFld().getText());
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



    }

}
